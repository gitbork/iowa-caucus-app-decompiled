package p006io.sentry;

import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p006io.sentry.context.Context;
import p006io.sentry.event.Breadcrumb;
import p006io.sentry.event.Event;
import p006io.sentry.event.EventBuilder;
import p006io.sentry.event.User;

/* renamed from: io.sentry.Sentry */
public final class Sentry {
    private static AtomicBoolean autoInitAttempted = new AtomicBoolean(false);
    private static final Logger logger = LoggerFactory.getLogger(Sentry.class);
    private static volatile SentryClient storedClient = null;

    private Sentry() {
    }

    public static SentryClient init() {
        return init(null, null);
    }

    public static SentryClient init(SentryClientFactory sentryClientFactory) {
        return init(null, sentryClientFactory);
    }

    public static SentryClient init(String str) {
        return init(str, null);
    }

    public static SentryClient init(String str, SentryClientFactory sentryClientFactory) {
        SentryClient sentryClient = SentryClientFactory.sentryClient(str, sentryClientFactory);
        setStoredClient(sentryClient);
        return sentryClient;
    }

    public static SentryClient getStoredClient() {
        if (storedClient != null) {
            return storedClient;
        }
        synchronized (Sentry.class) {
            if (storedClient == null && !autoInitAttempted.get()) {
                autoInitAttempted.set(true);
                init();
            }
        }
        return storedClient;
    }

    public static Context getContext() {
        return getStoredClient().getContext();
    }

    public static void clearContext() {
        getStoredClient().clearContext();
    }

    public static void setStoredClient(SentryClient sentryClient) {
        if (storedClient != null) {
            logger.warn("Overwriting statically stored SentryClient instance {} with {}.", (Object) storedClient, (Object) sentryClient);
        }
        storedClient = sentryClient;
    }

    public static void capture(Event event) {
        getStoredClient().sendEvent(event);
    }

    public static void capture(Throwable th) {
        getStoredClient().sendException(th);
    }

    public static void capture(String str) {
        getStoredClient().sendMessage(str);
    }

    public static void capture(EventBuilder eventBuilder) {
        getStoredClient().sendEvent(eventBuilder);
    }

    @Deprecated
    public static void record(Breadcrumb breadcrumb) {
        getStoredClient().getContext().recordBreadcrumb(breadcrumb);
    }

    @Deprecated
    public static void setUser(User user) {
        getStoredClient().getContext().setUser(user);
    }

    public static void close() {
        if (storedClient != null) {
            storedClient.closeConnection();
            storedClient = null;
            autoInitAttempted.set(false);
        }
    }
}
