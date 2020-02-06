package p006io.sentry.connection;

import java.io.Closeable;
import p006io.sentry.event.Event;

/* renamed from: io.sentry.connection.Connection */
public interface Connection extends Closeable {
    void addEventSendCallback(EventSendCallback eventSendCallback);

    void send(Event event) throws ConnectionException;
}
