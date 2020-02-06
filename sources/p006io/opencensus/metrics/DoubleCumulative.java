package p006io.opencensus.metrics;

import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;
import p006io.opencensus.internal.Utils;

@ThreadSafe
/* renamed from: io.opencensus.metrics.DoubleCumulative */
public abstract class DoubleCumulative {

    /* renamed from: io.opencensus.metrics.DoubleCumulative$DoublePoint */
    public static abstract class DoublePoint {
        public abstract void add(double d);
    }

    /* renamed from: io.opencensus.metrics.DoubleCumulative$NoopDoubleCumulative */
    private static final class NoopDoubleCumulative extends DoubleCumulative {
        private final int labelKeysSize;

        /* renamed from: io.opencensus.metrics.DoubleCumulative$NoopDoubleCumulative$NoopDoublePoint */
        private static final class NoopDoublePoint extends DoublePoint {
            /* access modifiers changed from: private */
            public static final NoopDoublePoint INSTANCE = new NoopDoublePoint();

            public void add(double d) {
            }

            private NoopDoublePoint() {
            }
        }

        public void clear() {
        }

        static NoopDoubleCumulative create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopDoubleCumulative(str, str2, str3, list);
        }

        NoopDoubleCumulative(String str, String str2, String str3, List<LabelKey> list) {
            Utils.checkNotNull(str, ConditionalUserProperty.NAME);
            Utils.checkNotNull(str2, "description");
            Utils.checkNotNull(str3, "unit");
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        public NoopDoublePoint getOrCreateTimeSeries(List<LabelValue> list) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            return NoopDoublePoint.INSTANCE;
        }

        public NoopDoublePoint getDefaultTimeSeries() {
            return NoopDoublePoint.INSTANCE;
        }

        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }
    }

    public abstract void clear();

    public abstract DoublePoint getDefaultTimeSeries();

    public abstract DoublePoint getOrCreateTimeSeries(List<LabelValue> list);

    public abstract void removeTimeSeries(List<LabelValue> list);

    static DoubleCumulative newNoopDoubleCumulative(String str, String str2, String str3, List<LabelKey> list) {
        return NoopDoubleCumulative.create(str, str2, str3, list);
    }
}
