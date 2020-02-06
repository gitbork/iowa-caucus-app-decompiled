package p006io.sentry.event.interfaces;

import java.util.Map;
import java.util.Objects;

/* renamed from: io.sentry.event.interfaces.UserInterface */
public class UserInterface implements SentryInterface {
    public static final String USER_INTERFACE = "sentry.interfaces.User";
    private final Map<String, Object> data;
    private final String email;

    /* renamed from: id */
    private final String f480id;
    private final String ipAddress;
    private final String username;

    public String getInterfaceName() {
        return USER_INTERFACE;
    }

    public UserInterface(String str, String str2, String str3, String str4, Map<String, Object> map) {
        this.f480id = str;
        this.username = str2;
        this.ipAddress = str3;
        this.email = str4;
        this.data = map;
    }

    public UserInterface(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null);
    }

    public String getId() {
        return this.f480id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getEmail() {
        return this.email;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserInterface userInterface = (UserInterface) obj;
        if (!Objects.equals(this.f480id, userInterface.f480id) || !Objects.equals(this.username, userInterface.username) || !Objects.equals(this.ipAddress, userInterface.ipAddress) || !Objects.equals(this.email, userInterface.email) || !Objects.equals(this.data, userInterface.data)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.f480id, this.username, this.ipAddress, this.email, this.data});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserInterface{id='");
        sb.append(this.f480id);
        sb.append('\'');
        sb.append(", username='");
        sb.append(this.username);
        sb.append('\'');
        sb.append(", ipAddress='");
        sb.append(this.ipAddress);
        sb.append('\'');
        sb.append(", email='");
        sb.append(this.email);
        sb.append('\'');
        sb.append(", data=");
        sb.append(this.data);
        sb.append('}');
        return sb.toString();
    }
}
