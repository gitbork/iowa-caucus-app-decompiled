package p006io.opencensus.trace.samplers;

import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import p006io.opencensus.trace.Sampler;
import p006io.opencensus.trace.Span;
import p006io.opencensus.trace.SpanContext;
import p006io.opencensus.trace.SpanId;
import p006io.opencensus.trace.TraceId;

@Immutable
/* renamed from: io.opencensus.trace.samplers.AlwaysSampleSampler */
final class AlwaysSampleSampler extends Sampler {
    public boolean shouldSample(@Nullable SpanContext spanContext, @Nullable Boolean bool, TraceId traceId, SpanId spanId, String str, List<Span> list) {
        return true;
    }

    public String toString() {
        return "AlwaysSampleSampler";
    }

    AlwaysSampleSampler() {
    }

    public String getDescription() {
        return toString();
    }
}
