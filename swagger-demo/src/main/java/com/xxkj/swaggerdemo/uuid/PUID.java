package com.xxkj.swaggerdemo.uuid;

import java.io.IOException;
import java.util.Random;

public class PUID {

	private static PUIDTimer sharedTimer;

	public static long generate() {
		PUIDTimer timer = sharedTimer();
		return timer.getTimestamp();
	}

	private static synchronized PUIDTimer sharedTimer() {
		if (sharedTimer == null) {
			try {
				sharedTimer = new PUIDTimer(new Random(System.currentTimeMillis()), null);
			} catch (IOException e) {
				throw new IllegalArgumentException(
						"Failed to create PUIDTimer with specified synchronizer: " + e.getMessage(), e);
			}
		}
		return sharedTimer;
	}
}
