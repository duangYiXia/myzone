package com.xxkj.swaggerdemo.uuid;

import java.io.IOException;
import java.util.Random;

public class PUIDTimer {
    /**
     * Since System.longTimeMillis() returns time from january 1st 1970,
     * and PUIDs need time from the beginning of gregorian calendar
     * (15-oct-1582), need to apply the offset:
     */
    private final static long kClockOffset = 0x01b21dd213814000L;

    /**
     * Also, instead of getting time in units of 100nsecs, we get something
     * with max resolution of 1 msec... and need the multiplier as well
     */
    private final static long kClockMultiplier = 10000;

    private final static long kClockMultiplierL = 10000L;

    /**
     * Let's allow "virtual" system time to advance at most 100 milliseconds
     * beyond actual physical system time, before adding delays.
     */
    private final static long kMaxClockAdvance = 100L;
    private final static int MAX_WAIT_COUNT = 50;

    protected final TimestampSynchronizer syncer;
    protected final Random random;

    private int clockSequence;
    private long lastSystemTimestamp = 0L;
    private long lastUsedTimestamp = 0L;
    private long firstUnsafeTimestamp = Long.MAX_VALUE;
    private int clockCounter = 0;

    public PUIDTimer(Random random, TimestampSynchronizer syncer) throws IOException {
        this.random = random;
        this.syncer = syncer;
        initCounters(this.random);
        this.lastSystemTimestamp = 0L;
        this.lastUsedTimestamp = 0L;

        if (syncer != null) {
            long lastSaved = syncer.initialize();
            if (lastSaved > this.lastUsedTimestamp) {
                this.lastUsedTimestamp = lastSaved;
            }
        }

        this.firstUnsafeTimestamp = 0L;
    }

    private void initCounters(Random random) {
        clockSequence = random.nextInt();
        clockCounter = (clockSequence >> 16) & 0xFF;
    }

    public synchronized long getTimestamp() {
        long systime = System.currentTimeMillis();
        if (systime < lastSystemTimestamp) {
            lastSystemTimestamp = systime;
        }

        if (systime <= lastUsedTimestamp) {
            if (clockCounter < kClockMultiplier) {
                systime = lastUsedTimestamp;
            } else {
                long actDiff = lastUsedTimestamp - systime;
                long origTime = systime;
                systime = lastUsedTimestamp + 1L;

                initCounters(random);

                if (actDiff >= kMaxClockAdvance) {
                    slowDown(origTime, actDiff);
                }
            }
        } else {
            clockCounter &= 0xFF;
        }

        lastUsedTimestamp = systime;

        if (syncer != null && systime >= firstUnsafeTimestamp) {
            try {
                firstUnsafeTimestamp = syncer.update(systime);
            } catch (IOException e) {
                throw new RuntimeException("Failed to synchronize timestamp", e);
            }
        }

        systime *= kClockMultiplierL;
        systime += kClockOffset;

        systime += clockCounter;
        ++clockCounter;

        return systime;
    }

    protected static void slowDown(long startTime, long actDiff) {
        long ratio = actDiff / kMaxClockAdvance;
        long delay;

        if (ratio < 2L) {
            delay = 1L;
        } else if (ratio < 10L) {
            delay = 2L;
        } else if (ratio < 600L) {
            delay = 3L;
        } else {
            delay = 5L;
        }

        long waitUntil = startTime + delay;
        int counter = 0;
        do {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                delay = 1L;
                if (++counter > MAX_WAIT_COUNT) {
                    break;
                }
            }
        } while (System.currentTimeMillis() < waitUntil);
    }
}
