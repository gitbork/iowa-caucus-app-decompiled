package p006io.sentry.event;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.sentry.event.UserBuilder */
public class UserBuilder {
    private Map<String, Object> data;
    private String email;

    /* renamed from: id */
    private String f479id;
    private String ipAddress;
    private String username;

    public UserBuilder setId(String str) {
        this.f479id = str;
        return this;
    }

    public UserBuilder setUsername(String str) {
        this.username = str;
        return this;
    }

    public UserBuilder setIpAddress(String str) {
        this.ipAddress = str;
        return this;
    }

    public UserBuilder setEmail(String str) {
        this.email = str;
        return this;
    }

    public UserBuilder setData(Map<String, Object> map) {
        this.data = map;
        return this;
    }

    public UserBuilder withData(String str, Object obj) {
        if (this.data == null) {
            this.data = new HashMap();
        }
        this.data.put(str, obj);
        return this;
    }

    public User build() {
        User user = new User(this.f479id, this.username, this.ipAddress, this.email, this.data);
        return user;
    }
}
