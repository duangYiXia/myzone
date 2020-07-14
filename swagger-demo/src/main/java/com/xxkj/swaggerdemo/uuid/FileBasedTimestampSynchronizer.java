package com.xxkj.swaggerdemo.uuid;

import java.io.File;
import java.io.IOException;

public class FileBasedTimestampSynchronizer extends TimestampSynchronizer {
	private final static long DEFAULT_UPDATE_INTERVAL = 10L * 1000L;
	private final static String DEFAULT_LOCK_FILE_NAME1 = "puid1.lck";
	private final static String DEFAULT_LOCK_FILE_NAME2 = "puid2.lck";
	private long mInterval = DEFAULT_UPDATE_INTERVAL;
	protected final LockedFile mLocked1, mLocked2;

	private boolean mFirstActive = false;

	public FileBasedTimestampSynchronizer() throws IOException {
		this(new File(DEFAULT_LOCK_FILE_NAME1), new File(DEFAULT_LOCK_FILE_NAME2), DEFAULT_UPDATE_INTERVAL);
	}

	public FileBasedTimestampSynchronizer(File file1, File file2) throws IOException {
		this(file1, file2, DEFAULT_UPDATE_INTERVAL);
	}

	public FileBasedTimestampSynchronizer(File file1, File file2, long interval) throws IOException {
		mInterval = interval;
		mLocked1 = new LockedFile(file1);

		boolean ok = false;
		try {
			mLocked2 = new LockedFile(file2);
			ok = true;
		} finally {
			if (!ok) {
				mLocked1.deactivate();
			}
		}
	}

	public void setUpdateInterval(long interval) {
		if (interval < 1L) {
			throw new IllegalArgumentException("Illegal value (" + interval + "); has to be a positive integer value");
		}
		mInterval = interval;
	}

	@Override
	protected long initialize() throws IOException {
		long ts1 = mLocked1.readStamp();
		long ts2 = mLocked2.readStamp();
		long result;

		if (ts1 > ts2) {
			mFirstActive = true;
			result = ts1;
		} else {
			mFirstActive = false;
			result = ts2;
		}

		return result;
	}

	@Override
	protected void deactivate() throws IOException {
		doDeactivate(mLocked1, mLocked2);
	}

	@Override
	protected long update(long now) throws IOException {
		long nextAllowed = now + mInterval;

		if (mFirstActive) {
			mLocked2.writeStamp(nextAllowed);
		} else {
			mLocked1.writeStamp(nextAllowed);
		}

		mFirstActive = !mFirstActive;
		return nextAllowed;
	}

	protected static void doDeactivate(LockedFile lf1, LockedFile lf2) {
		if (lf1 != null) {
			lf1.deactivate();
		}
		if (lf2 != null) {
			lf2.deactivate();
		}
	}
}
