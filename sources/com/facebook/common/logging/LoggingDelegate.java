package com.facebook.common.logging;

public interface LoggingDelegate {
    /* renamed from: d */
    void mo8623d(String str, String str2);

    /* renamed from: d */
    void mo8624d(String str, String str2, Throwable th);

    /* renamed from: e */
    void mo8625e(String str, String str2);

    /* renamed from: e */
    void mo8626e(String str, String str2, Throwable th);

    int getMinimumLoggingLevel();

    /* renamed from: i */
    void mo8628i(String str, String str2);

    /* renamed from: i */
    void mo8629i(String str, String str2, Throwable th);

    boolean isLoggable(int i);

    void log(int i, String str, String str2);

    void setMinimumLoggingLevel(int i);

    /* renamed from: v */
    void mo8634v(String str, String str2);

    /* renamed from: v */
    void mo8635v(String str, String str2, Throwable th);

    /* renamed from: w */
    void mo8636w(String str, String str2);

    /* renamed from: w */
    void mo8637w(String str, String str2, Throwable th);

    void wtf(String str, String str2);

    void wtf(String str, String str2, Throwable th);
}
