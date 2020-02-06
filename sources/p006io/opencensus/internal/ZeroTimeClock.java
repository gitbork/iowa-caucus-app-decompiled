package p006io.opencensus.internal;

import javax.annotation.concurrent.Immutable;
import p006io.opencensus.common.Clock;
import p006io.opencensus.common.Timestamp;

@Immutable
/* renamed from: io.opencensus.internal.ZeroTimeClock */
public final class ZeroTimeClock extends Clock {
    private static final ZeroTimeClock INSTANCE = new ZeroTimeClock();
    private static final Timestamp ZERO_TIMESTAMP = Timestamp.create(0, 0);

    public long nowNanos() {
        return 0;
    }

    private ZeroTimeClock() {
    }

    public static ZeroTimeClock getInstance() {
        return INSTANCE;
    }

    public Timestamp now() {
        return ZERO_TIMESTAMP;
    }
}
