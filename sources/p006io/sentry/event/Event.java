package p006io.sentry.event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p006io.sentry.event.interfaces.SentryInterface;

/* renamed from: io.sentry.event.Event */
public class Event implements Serializable {
    private static final Logger _logger = LoggerFactory.getLogger(Event.class);
    private List<Breadcrumb> breadcrumbs = new ArrayList();
    private String checksum;
    private Map<String, Map<String, Object>> contexts = new HashMap();
    private String culprit;
    private String dist;
    private String environment;
    private transient Map<String, Object> extra = new HashMap();
    private List<String> fingerprint;

    /* renamed from: id */
    private final UUID f477id;
    private Level level;
    private String logger;
    private String message;
    private String platform;
    private String release;
    private Sdk sdk;
    private Map<String, SentryInterface> sentryInterfaces = new HashMap();
    private String serverName;
    private Map<String, String> tags = new HashMap();
    private Date timestamp;
    private String transaction;

    /* renamed from: io.sentry.event.Event$Level */
    public enum Level {
        FATAL,
        ERROR,
        WARNING,
        INFO,
        DEBUG
    }

    Event(UUID uuid) {
        if (uuid != null) {
            this.f477id = uuid;
            return;
        }
        throw new IllegalArgumentException("The id can't be null");
    }

    public UUID getId() {
        return this.f477id;
    }

    public String getMessage() {
        return this.message;
    }

    /* access modifiers changed from: 0000 */
    public void setMessage(String str) {
        this.message = str;
    }

    public Date getTimestamp() {
        Date date = this.timestamp;
        if (date != null) {
            return (Date) date.clone();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public Level getLevel() {
        return this.level;
    }

    /* access modifiers changed from: 0000 */
    public void setLevel(Level level2) {
        this.level = level2;
    }

    public String getLogger() {
        return this.logger;
    }

    /* access modifiers changed from: 0000 */
    public void setLogger(String str) {
        this.logger = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    /* access modifiers changed from: 0000 */
    public void setPlatform(String str) {
        this.platform = str;
    }

    public Sdk getSdk() {
        return this.sdk;
    }

    public void setSdk(Sdk sdk2) {
        this.sdk = sdk2;
    }

    public String getCulprit() {
        return this.culprit;
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    public void setCulprit(String str) {
        this.culprit = str;
    }

    public String getTransaction() {
        return this.transaction;
    }

    /* access modifiers changed from: 0000 */
    public void setTransaction(String str) {
        this.transaction = str;
    }

    public List<Breadcrumb> getBreadcrumbs() {
        return this.breadcrumbs;
    }

    /* access modifiers changed from: 0000 */
    public void setBreadcrumbs(List<Breadcrumb> list) {
        this.breadcrumbs = list;
    }

    public Map<String, Map<String, Object>> getContexts() {
        return this.contexts;
    }

    public void setContexts(Map<String, Map<String, Object>> map) {
        this.contexts = map;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    /* access modifiers changed from: 0000 */
    public void setTags(Map<String, String> map) {
        this.tags = map;
    }

    public String getServerName() {
        return this.serverName;
    }

    /* access modifiers changed from: 0000 */
    public void setServerName(String str) {
        this.serverName = str;
    }

    public String getRelease() {
        return this.release;
    }

    /* access modifiers changed from: 0000 */
    public void setRelease(String str) {
        this.release = str;
    }

    public String getDist() {
        return this.dist;
    }

    public void setDist(String str) {
        this.dist = str;
    }

    public String getEnvironment() {
        return this.environment;
    }

    /* access modifiers changed from: 0000 */
    public void setEnvironment(String str) {
        this.environment = str;
    }

    public Map<String, Object> getExtra() {
        if (this.extra == null) {
            this.extra = new HashMap();
            _logger.warn("`extra` field was null, deserialization must not have been called, please check your ProGuard (or other obfuscation) configuration.");
        }
        return this.extra;
    }

    /* access modifiers changed from: 0000 */
    public void setExtra(Map<String, Object> map) {
        this.extra = map;
    }

    public List<String> getFingerprint() {
        return this.fingerprint;
    }

    public void setFingerprint(List<String> list) {
        this.fingerprint = list;
    }

    public String getChecksum() {
        return this.checksum;
    }

    /* access modifiers changed from: 0000 */
    public void setChecksum(String str) {
        this.checksum = str;
    }

    public Map<String, SentryInterface> getSentryInterfaces() {
        return this.sentryInterfaces;
    }

    /* access modifiers changed from: 0000 */
    public void setSentryInterfaces(Map<String, SentryInterface> map) {
        this.sentryInterfaces = map;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.extra = (Map) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(convertToSerializable(this.extra));
    }

    private static HashMap<String, ? super Serializable> convertToSerializable(Map<String, Object> map) {
        HashMap<String, ? super Serializable> hashMap = new HashMap<>(map.size());
        for (Entry entry : map.entrySet()) {
            if (entry.getValue() == null) {
                hashMap.put(entry.getKey(), null);
            } else if (entry.getValue() instanceof Serializable) {
                hashMap.put(entry.getKey(), (Serializable) entry.getValue());
            } else {
                hashMap.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f477id.equals(((Event) obj).f477id);
    }

    public int hashCode() {
        return this.f477id.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{level=");
        sb.append(this.level);
        sb.append(", message='");
        sb.append(this.message);
        sb.append('\'');
        sb.append(", logger='");
        sb.append(this.logger);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }
}
