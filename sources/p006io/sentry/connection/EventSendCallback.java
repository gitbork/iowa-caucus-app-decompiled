package p006io.sentry.connection;

import p006io.sentry.event.Event;

/* renamed from: io.sentry.connection.EventSendCallback */
public interface EventSendCallback {
    void onFailure(Event event, Exception exc);

    void onSuccess(Event event);
}
