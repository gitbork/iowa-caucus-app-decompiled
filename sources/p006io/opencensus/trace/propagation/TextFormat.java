package p006io.opencensus.trace.propagation;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import p006io.opencensus.internal.Utils;
import p006io.opencensus.trace.SpanContext;

/* renamed from: io.opencensus.trace.propagation.TextFormat */
public abstract class TextFormat {
    private static final NoopTextFormat NOOP_TEXT_FORMAT = new NoopTextFormat();

    /* renamed from: io.opencensus.trace.propagation.TextFormat$Getter */
    public static abstract class Getter<C> {
        @Nullable
        public abstract String get(C c, String str);
    }

    /* renamed from: io.opencensus.trace.propagation.TextFormat$NoopTextFormat */
    private static final class NoopTextFormat extends TextFormat {
        private NoopTextFormat() {
        }

        public List<String> fields() {
            return Collections.emptyList();
        }

        public <C> void inject(SpanContext spanContext, C c, Setter<C> setter) {
            Utils.checkNotNull(spanContext, "spanContext");
            Utils.checkNotNull(c, "carrier");
            Utils.checkNotNull(setter, "setter");
        }

        public <C> SpanContext extract(C c, Getter<C> getter) {
            Utils.checkNotNull(c, "carrier");
            Utils.checkNotNull(getter, "getter");
            return SpanContext.INVALID;
        }
    }

    /* renamed from: io.opencensus.trace.propagation.TextFormat$Setter */
    public static abstract class Setter<C> {
        public abstract void put(C c, String str, String str2);
    }

    public abstract <C> SpanContext extract(C c, Getter<C> getter) throws SpanContextParseException;

    public abstract List<String> fields();

    public abstract <C> void inject(SpanContext spanContext, C c, Setter<C> setter);

    static TextFormat getNoopTextFormat() {
        return NOOP_TEXT_FORMAT;
    }
}
