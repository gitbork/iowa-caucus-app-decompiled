package p006io.sentry;

import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p006io.sentry.event.Event.Level;
import p006io.sentry.event.EventBuilder;
import p006io.sentry.event.interfaces.ExceptionInterface;

/* renamed from: io.sentry.SentryUncaughtExceptionHandler */
public class SentryUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(SentryClientFactory.class);
    private UncaughtExceptionHandler defaultExceptionHandler;
    private volatile Boolean enabled = Boolean.valueOf(true);

    public SentryUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.defaultExceptionHandler = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (this.enabled.booleanValue()) {
            logger.trace("Uncaught exception received.");
            try {
                Sentry.capture(new EventBuilder().withMessage(th.getMessage()).withLevel(Level.FATAL).withSentryInterface(new ExceptionInterface(th)));
            } catch (Exception e) {
                logger.error("Error sending uncaught exception to Sentry.", (Throwable) e);
            }
        }
        UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else if (!(th instanceof ThreadDeath)) {
            PrintStream printStream = System.err;
            StringBuilder sb = new StringBuilder();
            sb.append("Exception in thread \"");
            sb.append(thread.getName());
            sb.append("\" ");
            printStream.print(sb.toString());
            th.printStackTrace(System.err);
        }
    }

    public static SentryUncaughtExceptionHandler setup() {
        logger.debug("Configuring uncaught exception handler.");
        UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            Logger logger2 = logger;
            StringBuilder sb = new StringBuilder();
            sb.append("default UncaughtExceptionHandler class='");
            sb.append(defaultUncaughtExceptionHandler.getClass().getName());
            sb.append("'");
            logger2.debug(sb.toString());
        }
        SentryUncaughtExceptionHandler sentryUncaughtExceptionHandler = new SentryUncaughtExceptionHandler(defaultUncaughtExceptionHandler);
        Thread.setDefaultUncaughtExceptionHandler(sentryUncaughtExceptionHandler);
        return sentryUncaughtExceptionHandler;
    }

    public void disable() {
        this.enabled = Boolean.valueOf(false);
        if (Thread.getDefaultUncaughtExceptionHandler() == this) {
            Thread.setDefaultUncaughtExceptionHandler(this.defaultExceptionHandler);
        }
    }

    public Boolean isEnabled() {
        return this.enabled;
    }
}
