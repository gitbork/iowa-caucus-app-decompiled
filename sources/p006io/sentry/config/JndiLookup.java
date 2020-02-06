package p006io.sentry.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.NoInitialContextException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* renamed from: io.sentry.config.JndiLookup */
public final class JndiLookup {
    private static final String JNDI_PREFIX = "java:comp/env/sentry/";
    private static final Logger logger = LoggerFactory.getLogger(JndiLookup.class);

    private JndiLookup() {
    }

    public static String jndiLookup(String str) {
        try {
            InitialContext initialContext = new InitialContext();
            StringBuilder sb = new StringBuilder();
            sb.append(JNDI_PREFIX);
            sb.append(str);
            return (String) initialContext.lookup(sb.toString());
        } catch (NoInitialContextException unused) {
            logger.trace("JNDI not configured for Sentry (NoInitialContextEx)");
        } catch (NamingException unused2) {
            Logger logger2 = logger;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("No /sentry/");
            sb2.append(str);
            sb2.append(" in JNDI");
            logger2.trace(sb2.toString());
        } catch (RuntimeException e) {
            logger.warn("Odd RuntimeException while testing for JNDI", (Throwable) e);
        }
        return null;
    }
}
