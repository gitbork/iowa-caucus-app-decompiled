package p006io.opencensus.trace;

import com.google.errorprone.annotations.MustBeClosed;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;
import p006io.opencensus.common.Scope;
import p006io.opencensus.internal.Utils;

/* renamed from: io.opencensus.trace.Tracer */
public abstract class Tracer {
    private static final NoopTracer noopTracer = new NoopTracer();

    /* renamed from: io.opencensus.trace.Tracer$NoopTracer */
    private static final class NoopTracer extends Tracer {
        public SpanBuilder spanBuilderWithExplicitParent(String str, @Nullable Span span) {
            return NoopSpanBuilder.createWithParent(str, span);
        }

        public SpanBuilder spanBuilderWithRemoteParent(String str, @Nullable SpanContext spanContext) {
            return NoopSpanBuilder.createWithRemoteParent(str, spanContext);
        }

        private NoopTracer() {
        }
    }

    public abstract SpanBuilder spanBuilderWithExplicitParent(String str, @Nullable Span span);

    public abstract SpanBuilder spanBuilderWithRemoteParent(String str, @Nullable SpanContext spanContext);

    static Tracer getNoopTracer() {
        return noopTracer;
    }

    public final Span getCurrentSpan() {
        Span currentSpan = CurrentSpanUtils.getCurrentSpan();
        return currentSpan != null ? currentSpan : BlankSpan.INSTANCE;
    }

    @MustBeClosed
    public final Scope withSpan(Span span) {
        return CurrentSpanUtils.withSpan((Span) Utils.checkNotNull(span, "span"), false);
    }

    public final Runnable withSpan(Span span, Runnable runnable) {
        return CurrentSpanUtils.withSpan(span, false, runnable);
    }

    public final <C> Callable<C> withSpan(Span span, Callable<C> callable) {
        return CurrentSpanUtils.withSpan(span, false, callable);
    }

    public final SpanBuilder spanBuilder(String str) {
        return spanBuilderWithExplicitParent(str, CurrentSpanUtils.getCurrentSpan());
    }

    protected Tracer() {
    }
}
