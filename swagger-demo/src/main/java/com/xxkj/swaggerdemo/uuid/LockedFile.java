package com.xxkj.swaggerdemo.uuid;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

class LockedFile {
	private final static int DEFAULT_LENGTH = 22;
	private final static long READ_ERROR = 0L;
	private final static String HEX_DIGITS = "0123456789abcdef";

	private final File mFile;
	private RandomAccessFile mRAFile;
	private FileChannel mChannel;
	private FileLock mLock;
	private ByteBuffer mWriteBuffer = null;

	private boolean mWeirdSize;
	private long mLastTimestamp = 0L;

	LockedFile(File f) throws IOException {
		mFile = f;

		RandomAccessFile raf = null;
		FileChannel channel = null;
		FileLock lock = null;
		boolean ok = false;

		try {
			raf = new RandomAccessFile(f, "rwd");
			channel = raf.getChannel();
			if (channel == null) {
				throw new IOException("Failed to access channel for '" + f + "'");
			}
			lock = channel.tryLock();
			if (lock == null) {
				throw new IOException("Failed to lock '" + f + "' (another JVM running PUIDGenerator?)");
			}
			ok = true;
		} finally {
			if (!ok) {
				doDeactivate(f, raf, lock);
			}
		}

		mRAFile = raf;
		mChannel = channel;
		mLock = lock;
	}

	public void deactivate() {
		RandomAccessFile raf = mRAFile;
		mRAFile = null;
		FileLock lock = mLock;
		mLock = null;
		doDeactivate(mFile, raf, lock);
	}

	public long readStamp() {
		int size;

		try {
			size = (int) mChannel.size();
		} catch (IOException e) {
			return READ_ERROR;
		}

		mWeirdSize = (size != DEFAULT_LENGTH);

		if (size == 0) {
			return READ_ERROR;
		}

		if (size > 100) {
			size = 100;
		}
		byte[] data = new byte[size];
		try {
			mRAFile.readFully(data);
		} catch (IOException e) {
			return READ_ERROR;
		}

		char[] cdata = new char[size];
		for (int i = 0; i < size; i++) {
			cdata[i] = (char) (data[i] & 0xFF);
		}
		String dataStr = new String(cdata);
		dataStr = dataStr.trim();

		long result = -1;
		String err = null;

		if (!dataStr.startsWith("[0") || dataStr.length() < 3 || Character.toLowerCase(dataStr.charAt(2)) != 'x') {
			err = "does not start with '[0x' prefix";
		} else {
			int ix = dataStr.indexOf(']', 3);
			if (ix <= 0) {
				err = "does not end with ']' marker";
			} else {
				String hex = dataStr.substring(3, ix);
				if (hex.length() > 16) {
					err = "length of the (hex) timestamp too long; expected 16, had " + hex.length() + " ('" + hex
							+ "')";
				} else {
					try {
						result = Long.parseLong(hex, 16);
					} catch (NumberFormatException nex) {
						err = "does not contain a valid hex timestamp; got '" + hex + "' (parse error: " + nex + ")";
					}
				}
			}
		}

		if (result < 0L || err != null) {
			return READ_ERROR;
		}

		mLastTimestamp = result;
		return result;
	}

	public void writeStamp(long stamp) throws IOException {
		if (stamp <= mLastTimestamp) {
			if (stamp == mLastTimestamp) {
				return;
			}
			throw new IOException("" + getFileDesc() + " trying to overwrite existing value (" + mLastTimestamp
					+ ") with an earlier timestamp (" + stamp + ")");
		}

		if (mWriteBuffer == null) {
			mWriteBuffer = ByteBuffer.allocate(DEFAULT_LENGTH);
			mWriteBuffer.put(0, (byte) '[');
			mWriteBuffer.put(1, (byte) '0');
			mWriteBuffer.put(2, (byte) 'x');
			mWriteBuffer.put(19, (byte) ']');
			mWriteBuffer.put(20, (byte) '\r');
			mWriteBuffer.put(21, (byte) '\n');
		}

		for (int i = 18; i >= 3; --i) {
			int val = (((int) stamp) & 0x0F);
			mWriteBuffer.put(i, (byte) HEX_DIGITS.charAt(val));
			stamp = (stamp >> 4);
		}

		mWriteBuffer.position(0);
		mChannel.write(mWriteBuffer, 0L);
		if (mWeirdSize) {
			mRAFile.setLength(DEFAULT_LENGTH);
			mWeirdSize = false;
		}

		mChannel.force(false);
	}

	protected String getFileDesc() {
		return mFile.toString();
	}

	private static void doDeactivate(File f, RandomAccessFile raf, FileLock lock) {
		if (lock != null) {
			try {
				lock.release();
			} catch (Throwable t) {
				// Ignore
			}
		}

		if (raf != null) {
			try {
				raf.close();
			} catch (Throwable t) {
				// Ignore
			}
		}
	}
}
