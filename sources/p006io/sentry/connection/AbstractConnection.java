package p006io.sentry.connection;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p006io.sentry.environment.SentryEnvironment;
import p006io.sentry.event.Event;
import p006io.sentry.util.Util;

/* renamed from: io.sentry.connection.AbstractConnection */
public abstract class AbstractConnection implements Connection {
    public static final String SENTRY_PROTOCOL_VERSION = "6";
    private static final Logger lockdownLogger;
    private static final Logger logger = LoggerFactory.getLogger(AbstractConnection.class);
    private final String authHeader;
    private Set<EventSendCallback> eventSendCallbacks = new HashSet();
    private LockdownManager lockdownManager = new LockdownManager();

    /* access modifiers changed from: protected */
    public abstract void doSend(Event event) throws ConnectionException;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(AbstractConnection.class.getName());
        sb.append(".lockdown");
        lockdownLogger = LoggerFactory.getLogger(sb.toString());
    }

    protected AbstractConnection(String str, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append("Sentry sentry_version=6,sentry_client=");
        sb.append(SentryEnvironment.getSentryName());
        sb.append(",");
        sb.append("sentry_key=");
        sb.append(str);
        if (!Util.isNullOrEmpty(str2)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(",sentry_secret=");
            sb2.append(str2);
            str3 = sb2.toString();
        } else {
            str3 = "";
        }
        sb.append(str3);
        this.authHeader = sb.toString();
    }

    /* access modifiers changed from: protected */
    public String getAuthHeader() {
        return this.authHeader;
    }

    public final void send(Event event) throws ConnectionException {
        try {
            if (!this.lockdownManager.isLockedDown()) {
                doSend(event);
                this.lockdownManager.unlock();
                for (EventSendCallback eventSendCallback : this.eventSendCallbacks) {
                    try {
                        eventSendCallback.onSuccess(event);
                    } catch (Exception e) {
                        Logger logger2 = logger;
                        StringBuilder sb = new StringBuilder();
                        sb.append("An exception occurred while running an EventSendCallback.onSuccess: ");
                        sb.append(eventSendCallback.getClass().getName());
                        logger2.warn(sb.toString(), (Throwable) e);
                    }
                }
                return;
            }
            throw new LockedDownException();
        } catch (ConnectionException e2) {
            for (EventSendCallback eventSendCallback2 : this.eventSendCallbacks) {
                try {
                    eventSendCallback2.onFailure(event, e2);
                } catch (Exception e3) {
                    Logger logger3 = logger;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("An exception occurred while running an EventSendCallback.onFailure: ");
                    sb2.append(eventSendCallback2.getClass().getName());
                    logger3.warn(sb2.toString(), (Throwable) e3);
                }
            }
            if (this.lockdownManager.lockdown(e2)) {
                Logger logger4 = lockdownLogger;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Initiated a temporary lockdown because of exception: ");
                sb3.append(e2.getMessage());
                logger4.warn(sb3.toString());
            }
            throw e2;
        }
    }

    public void addEventSendCallback(EventSendCallback eventSendCallback) {
        this.eventSendCallbacks.add(eventSendCallback);
    }
}
