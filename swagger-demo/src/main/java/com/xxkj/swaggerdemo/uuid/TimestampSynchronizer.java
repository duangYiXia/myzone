package com.xxkj.swaggerdemo.uuid;

import java.io.IOException;

public abstract class TimestampSynchronizer {
    protected TimestampSynchronizer() {}

    protected abstract long initialize() throws IOException;

    protected abstract void deactivate() throws IOException;

    protected abstract long update(long now) throws IOException;
}
