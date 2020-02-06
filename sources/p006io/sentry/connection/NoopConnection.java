package p006io.sentry.connection;

import java.io.IOException;
import p006io.sentry.event.Event;

/* renamed from: io.sentry.connection.NoopConnection */
public class NoopConnection extends AbstractConnection {
    public void close() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void doSend(Event event) throws ConnectionException {
    }

    public NoopConnection() {
        super(null, null);
    }
}
