package p006io.sentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p006io.sentry.config.Lookup;
import p006io.sentry.dsn.Dsn;
import p006io.sentry.util.Util;

/* renamed from: io.sentry.SentryClientFactory */
public abstract class SentryClientFactory {
    private static final Logger logger = LoggerFactory.getLogger(SentryClientFactory.class);

    public abstract SentryClient createSentryClient(Dsn dsn);

    public static SentryClient sentryClient() {
        return sentryClient(null, null);
    }

    public static SentryClient sentryClient(String str) {
        return sentryClient(str, null);
    }

    public static SentryClient sentryClient(String str, SentryClientFactory sentryClientFactory) {
        Dsn resolveDsn = resolveDsn(str);
        if (sentryClientFactory == null) {
            String lookup = Lookup.lookup("factory", resolveDsn);
            if (Util.isNullOrEmpty(lookup)) {
                sentryClientFactory = new DefaultSentryClientFactory();
            } else {
                try {
                    sentryClientFactory = (SentryClientFactory) Class.forName(lookup).newInstance();
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    Logger logger2 = logger;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error creating SentryClient using factory class: '");
                    sb.append(lookup);
                    sb.append("'.");
                    logger2.error(sb.toString(), e);
                    return null;
                }
            }
        }
        return sentryClientFactory.createSentryClient(resolveDsn);
    }

    private static Dsn resolveDsn(String str) {
        try {
            if (Util.isNullOrEmpty(str)) {
                str = Dsn.dsnLookup();
            }
            return new Dsn(str);
        } catch (Exception e) {
            logger.error("Error creating valid DSN from: '{}'.", (Object) str, (Object) e);
            throw e;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SentryClientFactory{name='");
        sb.append(getClass().getName());
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }
}
