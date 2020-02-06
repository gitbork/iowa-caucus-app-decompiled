package p006io.sentry.buffer;

import java.util.Iterator;
import p006io.sentry.event.Event;

/* renamed from: io.sentry.buffer.Buffer */
public interface Buffer {
    void add(Event event);

    void discard(Event event);

    Iterator<Event> getEvents();
}
