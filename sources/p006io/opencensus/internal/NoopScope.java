package p006io.opencensus.internal;

import p006io.opencensus.common.Scope;

/* renamed from: io.opencensus.internal.NoopScope */
public final class NoopScope implements Scope {
    private static final Scope INSTANCE = new NoopScope();

    public void close() {
    }

    private NoopScope() {
    }

    public static Scope getInstance() {
        return INSTANCE;
    }
}
