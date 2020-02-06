package p006io.sentry.event.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* renamed from: io.sentry.event.interfaces.MessageInterface */
public class MessageInterface implements SentryInterface {
    public static final String MESSAGE_INTERFACE = "sentry.interfaces.Message";
    private final String formatted;
    private final String message;
    private final List<String> parameters;

    public String getInterfaceName() {
        return MESSAGE_INTERFACE;
    }

    public MessageInterface(String str) {
        this(str, Collections.emptyList());
    }

    public MessageInterface(String str, String... strArr) {
        this(str, Arrays.asList(strArr));
    }

    public MessageInterface(String str, List<String> list) {
        this(str, list, null);
    }

    public MessageInterface(String str, List<String> list, String str2) {
        this.message = str;
        this.parameters = Collections.unmodifiableList(new ArrayList(list));
        this.formatted = str2;
    }

    public String getMessage() {
        return this.message;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public String getFormatted() {
        return this.formatted;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MessageInterface{message='");
        sb.append(this.message);
        sb.append('\'');
        sb.append(", parameters=");
        sb.append(this.parameters);
        sb.append(", formatted=");
        sb.append(this.formatted);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageInterface messageInterface = (MessageInterface) obj;
        if (!Objects.equals(this.message, messageInterface.message) || !Objects.equals(this.parameters, messageInterface.parameters) || !Objects.equals(this.formatted, messageInterface.formatted)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.message, this.parameters, this.formatted});
    }
}
