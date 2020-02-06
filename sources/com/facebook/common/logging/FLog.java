package com.facebook.common.logging;

public class FLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static LoggingDelegate sHandler = FLogDefaultLoggingDelegate.getInstance();

    public static void setLoggingDelegate(LoggingDelegate loggingDelegate) {
        if (loggingDelegate != null) {
            sHandler = loggingDelegate;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static boolean isLoggable(int i) {
        return sHandler.isLoggable(i);
    }

    public static void setMinimumLoggingLevel(int i) {
        sHandler.setMinimumLoggingLevel(i);
    }

    public static int getMinimumLoggingLevel() {
        return sHandler.getMinimumLoggingLevel();
    }

    /* renamed from: v */
    public static void m72v(String str, String str2) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(str, str2);
        }
    }

    /* renamed from: v */
    public static void m73v(String str, String str2, Object obj) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(str, formatString(str2, obj));
        }
    }

    /* renamed from: v */
    public static void m74v(String str, String str2, Object obj, Object obj2) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(str, formatString(str2, obj, obj2));
        }
    }

    /* renamed from: v */
    public static void m75v(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(str, formatString(str2, obj, obj2, obj3));
        }
    }

    /* renamed from: v */
    public static void m76v(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(str, formatString(str2, obj, obj2, obj3, obj4));
        }
    }

    /* renamed from: v */
    public static void m64v(Class<?> cls, String str) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(getTag(cls), str);
        }
    }

    /* renamed from: v */
    public static void m65v(Class<?> cls, String str, Object obj) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(getTag(cls), formatString(str, obj));
        }
    }

    /* renamed from: v */
    public static void m66v(Class<?> cls, String str, Object obj, Object obj2) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(getTag(cls), formatString(str, obj, obj2));
        }
    }

    /* renamed from: v */
    public static void m67v(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (isLoggable(2)) {
            m64v(cls, formatString(str, obj, obj2, obj3));
        }
    }

    /* renamed from: v */
    public static void m68v(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(getTag(cls), formatString(str, obj, obj2, obj3, obj4));
        }
    }

    /* renamed from: v */
    public static void m78v(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(str, formatString(str2, objArr));
        }
    }

    /* renamed from: v */
    public static void m79v(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8635v(str, formatString(str2, objArr), th);
        }
    }

    /* renamed from: v */
    public static void m70v(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8634v(getTag(cls), formatString(str, objArr));
        }
    }

    /* renamed from: v */
    public static void m71v(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8635v(getTag(cls), formatString(str, objArr), th);
        }
    }

    /* renamed from: v */
    public static void m77v(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8635v(str, str2, th);
        }
    }

    /* renamed from: v */
    public static void m69v(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo8635v(getTag(cls), str, th);
        }
    }

    /* renamed from: d */
    public static void m32d(String str, String str2) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(str, str2);
        }
    }

    /* renamed from: d */
    public static void m33d(String str, String str2, Object obj) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(str, formatString(str2, obj));
        }
    }

    /* renamed from: d */
    public static void m34d(String str, String str2, Object obj, Object obj2) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(str, formatString(str2, obj, obj2));
        }
    }

    /* renamed from: d */
    public static void m35d(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(str, formatString(str2, obj, obj2, obj3));
        }
    }

    /* renamed from: d */
    public static void m36d(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(str, formatString(str2, obj, obj2, obj3, obj4));
        }
    }

    /* renamed from: d */
    public static void m24d(Class<?> cls, String str) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(getTag(cls), str);
        }
    }

    /* renamed from: d */
    public static void m25d(Class<?> cls, String str, Object obj) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(getTag(cls), formatString(str, obj));
        }
    }

    /* renamed from: d */
    public static void m26d(Class<?> cls, String str, Object obj, Object obj2) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(getTag(cls), formatString(str, obj, obj2));
        }
    }

    /* renamed from: d */
    public static void m27d(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(getTag(cls), formatString(str, obj, obj2, obj3));
        }
    }

    /* renamed from: d */
    public static void m28d(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(getTag(cls), formatString(str, obj, obj2, obj3, obj4));
        }
    }

    /* renamed from: d */
    public static void m38d(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(3)) {
            m32d(str, formatString(str2, objArr));
        }
    }

    /* renamed from: d */
    public static void m39d(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(3)) {
            m37d(str, formatString(str2, objArr), th);
        }
    }

    /* renamed from: d */
    public static void m30d(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8623d(getTag(cls), formatString(str, objArr));
        }
    }

    /* renamed from: d */
    public static void m31d(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8624d(getTag(cls), formatString(str, objArr), th);
        }
    }

    /* renamed from: d */
    public static void m37d(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8624d(str, str2, th);
        }
    }

    /* renamed from: d */
    public static void m29d(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo8624d(getTag(cls), str, th);
        }
    }

    /* renamed from: i */
    public static void m56i(String str, String str2) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(str, str2);
        }
    }

    /* renamed from: i */
    public static void m57i(String str, String str2, Object obj) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(str, formatString(str2, obj));
        }
    }

    /* renamed from: i */
    public static void m58i(String str, String str2, Object obj, Object obj2) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(str, formatString(str2, obj, obj2));
        }
    }

    /* renamed from: i */
    public static void m59i(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(str, formatString(str2, obj, obj2, obj3));
        }
    }

    /* renamed from: i */
    public static void m60i(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(str, formatString(str2, obj, obj2, obj3, obj4));
        }
    }

    /* renamed from: i */
    public static void m48i(Class<?> cls, String str) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(getTag(cls), str);
        }
    }

    /* renamed from: i */
    public static void m49i(Class<?> cls, String str, Object obj) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(getTag(cls), formatString(str, obj));
        }
    }

    /* renamed from: i */
    public static void m50i(Class<?> cls, String str, Object obj, Object obj2) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(getTag(cls), formatString(str, obj, obj2));
        }
    }

    /* renamed from: i */
    public static void m51i(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(getTag(cls), formatString(str, obj, obj2, obj3));
        }
    }

    /* renamed from: i */
    public static void m52i(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(getTag(cls), formatString(str, obj, obj2, obj3, obj4));
        }
    }

    /* renamed from: i */
    public static void m62i(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(str, formatString(str2, objArr));
        }
    }

    /* renamed from: i */
    public static void m63i(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8629i(str, formatString(str2, objArr), th);
        }
    }

    /* renamed from: i */
    public static void m54i(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8628i(getTag(cls), formatString(str, objArr));
        }
    }

    /* renamed from: i */
    public static void m55i(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (isLoggable(4)) {
            sHandler.mo8629i(getTag(cls), formatString(str, objArr), th);
        }
    }

    /* renamed from: i */
    public static void m61i(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8629i(str, str2, th);
        }
    }

    /* renamed from: i */
    public static void m53i(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo8629i(getTag(cls), str, th);
        }
    }

    /* renamed from: w */
    public static void m84w(String str, String str2) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo8636w(str, str2);
        }
    }

    /* renamed from: w */
    public static void m80w(Class<?> cls, String str) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo8636w(getTag(cls), str);
        }
    }

    /* renamed from: w */
    public static void m86w(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo8636w(str, formatString(str2, objArr));
        }
    }

    /* renamed from: w */
    public static void m87w(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo8637w(str, formatString(str2, objArr), th);
        }
    }

    /* renamed from: w */
    public static void m82w(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo8636w(getTag(cls), formatString(str, objArr));
        }
    }

    /* renamed from: w */
    public static void m83w(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (isLoggable(5)) {
            m81w(cls, formatString(str, objArr), th);
        }
    }

    /* renamed from: w */
    public static void m85w(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo8637w(str, str2, th);
        }
    }

    /* renamed from: w */
    public static void m81w(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo8637w(getTag(cls), str, th);
        }
    }

    /* renamed from: e */
    public static void m44e(String str, String str2) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo8625e(str, str2);
        }
    }

    /* renamed from: e */
    public static void m40e(Class<?> cls, String str) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo8625e(getTag(cls), str);
        }
    }

    /* renamed from: e */
    public static void m46e(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo8625e(str, formatString(str2, objArr));
        }
    }

    /* renamed from: e */
    public static void m47e(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo8626e(str, formatString(str2, objArr), th);
        }
    }

    /* renamed from: e */
    public static void m42e(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo8625e(getTag(cls), formatString(str, objArr));
        }
    }

    /* renamed from: e */
    public static void m43e(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo8626e(getTag(cls), formatString(str, objArr), th);
        }
    }

    /* renamed from: e */
    public static void m45e(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo8626e(str, str2, th);
        }
    }

    /* renamed from: e */
    public static void m41e(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo8626e(getTag(cls), str, th);
        }
    }

    public static void wtf(String str, String str2) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(str, str2);
        }
    }

    public static void wtf(Class<?> cls, String str) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), str);
        }
    }

    public static void wtf(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(str, formatString(str2, objArr));
        }
    }

    public static void wtf(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(str, formatString(str2, objArr), th);
        }
    }

    public static void wtf(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), formatString(str, objArr));
        }
    }

    public static void wtf(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), formatString(str, objArr), th);
        }
    }

    public static void wtf(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(str, str2, th);
        }
    }

    public static void wtf(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), str, th);
        }
    }

    private static String formatString(String str, Object... objArr) {
        return String.format(null, str, objArr);
    }

    private static String getTag(Class<?> cls) {
        return cls.getSimpleName();
    }
}
