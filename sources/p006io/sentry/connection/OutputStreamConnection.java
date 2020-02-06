package p006io.sentry.connection;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import p006io.sentry.event.Event;
import p006io.sentry.marshaller.Marshaller;

/* renamed from: io.sentry.connection.OutputStreamConnection */
public class OutputStreamConnection extends AbstractConnection {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private Marshaller marshaller;
    private final OutputStream outputStream;

    public OutputStreamConnection(OutputStream outputStream2) {
        super(null, null);
        this.outputStream = outputStream2;
    }

    /* access modifiers changed from: protected */
    public synchronized void doSend(Event event) throws ConnectionException {
        try {
            this.outputStream.write("Sentry event:\n".getBytes(UTF_8));
            this.marshaller.marshall(event, this.outputStream);
            this.outputStream.write(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE.getBytes(UTF_8));
            this.outputStream.flush();
        } catch (IOException e) {
            throw new ConnectionException("Couldn't sent the event properly", e);
        }
    }

    public void close() throws IOException {
        this.outputStream.close();
    }

    public void setMarshaller(Marshaller marshaller2) {
        this.marshaller = marshaller2;
    }
}
