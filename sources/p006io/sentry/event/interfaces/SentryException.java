package p006io.sentry.event.interfaces;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import p006io.sentry.jvmti.FrameCache;

/* renamed from: io.sentry.event.interfaces.SentryException */
public final class SentryException implements Serializable {
    public static final String DEFAULT_PACKAGE_NAME = "(default)";
    private final String exceptionClassName;
    private final String exceptionMessage;
    private final String exceptionPackageName;
    private final StackTraceInterface stackTraceInterface;

    public SentryException(Throwable th, StackTraceElement[] stackTraceElementArr) {
        Package packageR = th.getClass().getPackage();
        String name = th.getClass().getName();
        this.exceptionMessage = th.getMessage();
        if (packageR != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(packageR.getName());
            sb.append(".");
            name = name.replace(sb.toString(), "");
        }
        this.exceptionClassName = name;
        this.exceptionPackageName = packageR != null ? packageR.getName() : null;
        this.stackTraceInterface = new StackTraceInterface(th.getStackTrace(), stackTraceElementArr, FrameCache.get(th));
    }

    public SentryException(String str, String str2, String str3, StackTraceInterface stackTraceInterface2) {
        this.exceptionMessage = str;
        this.exceptionClassName = str2;
        this.exceptionPackageName = str3;
        this.stackTraceInterface = stackTraceInterface2;
    }

    public static Deque<SentryException> extractExceptionQueue(Throwable th) {
        ArrayDeque arrayDeque = new ArrayDeque();
        HashSet hashSet = new HashSet();
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[0];
        while (th != null && hashSet.add(th)) {
            arrayDeque.add(new SentryException(th, stackTraceElementArr));
            stackTraceElementArr = th.getStackTrace();
            th = th.getCause();
        }
        return arrayDeque;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }

    public String getExceptionClassName() {
        return this.exceptionClassName;
    }

    public String getExceptionPackageName() {
        String str = this.exceptionPackageName;
        return str != null ? str : "(default)";
    }

    public StackTraceInterface getStackTraceInterface() {
        return this.stackTraceInterface;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SentryException{exceptionMessage='");
        sb.append(this.exceptionMessage);
        sb.append('\'');
        sb.append(", exceptionClassName='");
        sb.append(this.exceptionClassName);
        sb.append('\'');
        sb.append(", exceptionPackageName='");
        sb.append(this.exceptionPackageName);
        sb.append('\'');
        sb.append(", stackTraceInterface=");
        sb.append(this.stackTraceInterface);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SentryException sentryException = (SentryException) obj;
        if (!this.exceptionClassName.equals(sentryException.exceptionClassName)) {
            return false;
        }
        String str = this.exceptionMessage;
        if (str == null ? sentryException.exceptionMessage != null : !str.equals(sentryException.exceptionMessage)) {
            return false;
        }
        String str2 = this.exceptionPackageName;
        if (str2 == null ? sentryException.exceptionPackageName == null : str2.equals(sentryException.exceptionPackageName)) {
            return this.stackTraceInterface.equals(sentryException.stackTraceInterface);
        }
        return false;
    }

    public int hashCode() {
        String str = this.exceptionMessage;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.exceptionClassName.hashCode()) * 31;
        String str2 = this.exceptionPackageName;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }
}
