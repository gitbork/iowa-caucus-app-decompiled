package p006io.sentry.event.helper;

import p006io.sentry.event.Event;

/* renamed from: io.sentry.event.helper.ShouldSendEventCallback */
public interface ShouldSendEventCallback {
    boolean shouldSend(Event event);
}
