package p006io.opencensus.metrics.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import p006io.opencensus.common.Function;
import p006io.opencensus.internal.Utils;
import p006io.opencensus.metrics.data.Exemplar;

@Immutable
/* renamed from: io.opencensus.metrics.export.Distribution */
public abstract class Distribution {

    @Immutable
    /* renamed from: io.opencensus.metrics.export.Distribution$Bucket */
    public static abstract class Bucket {
        public abstract long getCount();

        @Nullable
        public abstract Exemplar getExemplar();

        Bucket() {
        }

        public static Bucket create(long j) {
            Utils.checkArgument(j >= 0, "bucket count should be non-negative.");
            return new AutoValue_Distribution_Bucket(j, null);
        }

        public static Bucket create(long j, Exemplar exemplar) {
            Utils.checkArgument(j >= 0, "bucket count should be non-negative.");
            Utils.checkNotNull(exemplar, "exemplar");
            return new AutoValue_Distribution_Bucket(j, exemplar);
        }
    }

    @Immutable
    /* renamed from: io.opencensus.metrics.export.Distribution$BucketOptions */
    public static abstract class BucketOptions {

        @Immutable
        /* renamed from: io.opencensus.metrics.export.Distribution$BucketOptions$ExplicitOptions */
        public static abstract class ExplicitOptions extends BucketOptions {
            public abstract List<Double> getBucketBoundaries();

            ExplicitOptions() {
                super();
            }

            public final <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2) {
                return function.apply(this);
            }

            /* access modifiers changed from: private */
            public static ExplicitOptions create(List<Double> list) {
                Utils.checkNotNull(list, "bucketBoundaries");
                List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
                checkBucketBoundsAreSorted(unmodifiableList);
                return new AutoValue_Distribution_BucketOptions_ExplicitOptions(unmodifiableList);
            }

            private static void checkBucketBoundsAreSorted(List<Double> list) {
                if (list.size() >= 1) {
                    String str = "bucketBoundary";
                    double doubleValue = ((Double) Utils.checkNotNull(list.get(0), str)).doubleValue();
                    Utils.checkArgument(doubleValue > 0.0d, "bucket boundary should be > 0");
                    int i = 1;
                    while (i < list.size()) {
                        double doubleValue2 = ((Double) Utils.checkNotNull(list.get(i), str)).doubleValue();
                        Utils.checkArgument(doubleValue < doubleValue2, "bucket boundaries not sorted.");
                        i++;
                        doubleValue = doubleValue2;
                    }
                }
            }
        }

        public abstract <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2);

        private BucketOptions() {
        }

        public static BucketOptions explicitOptions(List<Double> list) {
            return ExplicitOptions.create(list);
        }
    }

    @Nullable
    public abstract BucketOptions getBucketOptions();

    public abstract List<Bucket> getBuckets();

    public abstract long getCount();

    public abstract double getSum();

    public abstract double getSumOfSquaredDeviations();

    Distribution() {
    }

    public static Distribution create(long j, double d, double d2, BucketOptions bucketOptions, List<Bucket> list) {
        boolean z = true;
        Utils.checkArgument(j >= 0, "count should be non-negative.");
        Utils.checkArgument(d2 >= 0.0d, "sum of squared deviations should be non-negative.");
        if (j == 0) {
            Utils.checkArgument(d == 0.0d, "sum should be 0 if count is 0.");
            if (d2 != 0.0d) {
                z = false;
            }
            Utils.checkArgument(z, "sum of squared deviations should be 0 if count is 0.");
        }
        BucketOptions bucketOptions2 = bucketOptions;
        Utils.checkNotNull(bucketOptions2, "bucketOptions");
        List unmodifiableList = Collections.unmodifiableList(new ArrayList((Collection) Utils.checkNotNull(list, "buckets")));
        Utils.checkListElementNotNull(unmodifiableList, "bucket");
        AutoValue_Distribution autoValue_Distribution = new AutoValue_Distribution(j, d, d2, bucketOptions2, unmodifiableList);
        return autoValue_Distribution;
    }
}
