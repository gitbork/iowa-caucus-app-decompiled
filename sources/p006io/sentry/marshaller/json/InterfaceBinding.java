package p006io.sentry.marshaller.json;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import p006io.sentry.event.interfaces.SentryInterface;

/* renamed from: io.sentry.marshaller.json.InterfaceBinding */
public interface InterfaceBinding<T extends SentryInterface> {
    void writeInterface(JsonGenerator jsonGenerator, T t) throws IOException;
}
