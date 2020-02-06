package p006io.sentry.connection;

import p006io.sentry.event.Event;

/* renamed from: io.sentry.connection.EventSampler */
public interface EventSampler {
    boolean shouldSendEvent(Event event);
}
