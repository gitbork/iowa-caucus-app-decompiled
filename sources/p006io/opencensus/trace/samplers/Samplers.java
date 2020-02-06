package p006io.opencensus.trace.samplers;

import p006io.opencensus.trace.Sampler;

/* renamed from: io.opencensus.trace.samplers.Samplers */
public final class Samplers {
    private static final Sampler ALWAYS_SAMPLE = new AlwaysSampleSampler();
    private static final Sampler NEVER_SAMPLE = new NeverSampleSampler();

    private Samplers() {
    }

    public static Sampler alwaysSample() {
        return ALWAYS_SAMPLE;
    }

    public static Sampler neverSample() {
        return NEVER_SAMPLE;
    }

    public static Sampler probabilitySampler(double d) {
        return ProbabilitySampler.create(d);
    }
}
