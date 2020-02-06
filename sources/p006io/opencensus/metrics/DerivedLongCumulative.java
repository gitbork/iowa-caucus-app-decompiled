package p006io.opencensus.metrics;

import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;
import p006io.opencensus.common.ToLongFunction;
import p006io.opencensus.internal.Utils;

@ThreadSafe
/* renamed from: io.opencensus.metrics.DerivedLongCumulative */
public abstract class DerivedLongCumulative {

    /* renamed from: io.opencensus.metrics.DerivedLongCumulative$NoopDerivedLongCumulative */
    private static final class NoopDerivedLongCumulative extends DerivedLongCumulative {
        private final int labelKeysSize;

        public void clear() {
        }

        static NoopDerivedLongCumulative create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopDerivedLongCumulative(str, str2, str3, list);
        }

        NoopDerivedLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
            Utils.checkNotNull(str, ConditionalUserProperty.NAME);
            Utils.checkNotNull(str2, "description");
            Utils.checkNotNull(str3, "unit");
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        public <T> void createTimeSeries(List<LabelValue> list, T t, ToLongFunction<T> toLongFunction) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            Utils.checkNotNull(toLongFunction, "function");
        }

        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }
    }

    public abstract void clear();

    public abstract <T> void createTimeSeries(List<LabelValue> list, T t, ToLongFunction<T> toLongFunction);

    public abstract void removeTimeSeries(List<LabelValue> list);

    static DerivedLongCumulative newNoopDerivedLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
        return NoopDerivedLongCumulative.create(str, str2, str3, list);
    }
}
