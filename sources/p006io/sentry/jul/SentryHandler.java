package p006io.sentry.jul;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import org.slf4j.MDC;
import p006io.sentry.BuildConfig;
import p006io.sentry.Sentry;
import p006io.sentry.environment.SentryEnvironment;
import p006io.sentry.event.Event.Level;
import p006io.sentry.event.EventBuilder;
import p006io.sentry.event.interfaces.ExceptionInterface;
import p006io.sentry.event.interfaces.MessageInterface;

/* renamed from: io.sentry.jul.SentryHandler */
public class SentryHandler extends Handler {
    public static final String THREAD_ID = "Sentry-ThreadId";
    protected boolean printfStyle;

    /* renamed from: io.sentry.jul.SentryHandler$DropSentryFilter */
    private class DropSentryFilter implements Filter {
        private DropSentryFilter() {
        }

        public boolean isLoggable(LogRecord logRecord) {
            String loggerName = logRecord.getLoggerName();
            return loggerName == null || !loggerName.startsWith(BuildConfig.APPLICATION_ID);
        }
    }

    public void flush() {
    }

    public SentryHandler() {
        retrieveProperties();
        setFilter(new DropSentryFilter());
    }

    protected static Level getLevel(java.util.logging.Level level) {
        if (level.intValue() >= java.util.logging.Level.SEVERE.intValue()) {
            return Level.ERROR;
        }
        if (level.intValue() >= java.util.logging.Level.WARNING.intValue()) {
            return Level.WARNING;
        }
        if (level.intValue() >= java.util.logging.Level.INFO.intValue()) {
            return Level.INFO;
        }
        if (level.intValue() >= java.util.logging.Level.ALL.intValue()) {
            return Level.DEBUG;
        }
        return null;
    }

    protected static List<String> formatMessageParameters(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            arrayList.add(obj != null ? obj.toString() : null);
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void retrieveProperties() {
        LogManager logManager = LogManager.getLogManager();
        String name = SentryHandler.class.getName();
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(".printfStyle");
        setPrintfStyle(Boolean.valueOf(logManager.getProperty(sb.toString())).booleanValue());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(name);
        sb2.append(".level");
        setLevel(parseLevelOrDefault(logManager.getProperty(sb2.toString())));
    }

    private java.util.logging.Level parseLevelOrDefault(String str) {
        try {
            return java.util.logging.Level.parse(str.trim());
        } catch (Exception unused) {
            return java.util.logging.Level.WARNING;
        }
    }

    public void publish(LogRecord logRecord) {
        if (isLoggable(logRecord) && !SentryEnvironment.isManagingThread()) {
            SentryEnvironment.startManagingThread();
            try {
                Sentry.capture(createEventBuilder(logRecord));
            } catch (Exception e) {
                reportError("An exception occurred while creating a new event in Sentry", e, 1);
            } catch (Throwable th) {
                SentryEnvironment.stopManagingThread();
                throw th;
            }
            SentryEnvironment.stopManagingThread();
        }
    }

    /* access modifiers changed from: protected */
    public EventBuilder createEventBuilder(LogRecord logRecord) {
        String str;
        String str2;
        EventBuilder withLogger = new EventBuilder().withSdkIntegration("java.util.logging").withLevel(getLevel(logRecord.getLevel())).withTimestamp(new Date(logRecord.getMillis())).withLogger(logRecord.getLoggerName());
        String message = logRecord.getMessage();
        if (logRecord.getResourceBundle() != null && logRecord.getResourceBundle().containsKey(logRecord.getMessage())) {
            message = logRecord.getResourceBundle().getString(logRecord.getMessage());
        }
        if (logRecord.getParameters() == null) {
            withLogger.withSentryInterface(new MessageInterface(message));
        } else {
            List formatMessageParameters = formatMessageParameters(logRecord.getParameters());
            try {
                str2 = formatMessage(message, logRecord.getParameters());
                str = str2;
            } catch (Exception unused) {
                str2 = null;
                str = message;
            }
            withLogger.withSentryInterface(new MessageInterface(message, formatMessageParameters, str2));
            message = str;
        }
        withLogger.withMessage(message);
        Throwable thrown = logRecord.getThrown();
        if (thrown != null) {
            withLogger.withSentryInterface(new ExceptionInterface(thrown));
        }
        Map copyOfContextMap = MDC.getMDCAdapter().getCopyOfContextMap();
        if (copyOfContextMap != null) {
            for (Entry entry : copyOfContextMap.entrySet()) {
                if (Sentry.getStoredClient().getMdcTags().contains(entry.getKey())) {
                    withLogger.withTag((String) entry.getKey(), (String) entry.getValue());
                } else {
                    withLogger.withExtra((String) entry.getKey(), entry.getValue());
                }
            }
        }
        withLogger.withExtra(THREAD_ID, Integer.valueOf(logRecord.getThreadID()));
        return withLogger;
    }

    /* access modifiers changed from: protected */
    public String formatMessage(String str, Object[] objArr) {
        if (this.printfStyle) {
            return String.format(str, objArr);
        }
        return MessageFormat.format(str, objArr);
    }

    public void close() throws SecurityException {
        SentryEnvironment.startManagingThread();
        try {
            Sentry.close();
        } catch (Exception e) {
            reportError("An exception occurred while closing the Sentry connection", e, 3);
        } catch (Throwable th) {
            SentryEnvironment.stopManagingThread();
            throw th;
        }
        SentryEnvironment.stopManagingThread();
    }

    public void setPrintfStyle(boolean z) {
        this.printfStyle = z;
    }
}
