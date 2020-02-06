package p006io.sentry.time;

import java.util.Date;

/* renamed from: io.sentry.time.SystemClock */
public class SystemClock implements Clock {
    public long millis() {
        return System.currentTimeMillis();
    }

    public Date date() {
        return new Date();
    }
}
