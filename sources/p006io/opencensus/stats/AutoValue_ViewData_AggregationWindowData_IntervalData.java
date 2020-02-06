package p006io.opencensus.stats;

import javax.annotation.concurrent.Immutable;
import p006io.opencensus.common.Timestamp;
import p006io.opencensus.stats.ViewData.AggregationWindowData.IntervalData;

@Immutable
@Deprecated
/* renamed from: io.opencensus.stats.AutoValue_ViewData_AggregationWindowData_IntervalData */
final class AutoValue_ViewData_AggregationWindowData_IntervalData extends IntervalData {
    private final Timestamp end;

    AutoValue_ViewData_AggregationWindowData_IntervalData(Timestamp timestamp) {
        if (timestamp != null) {
            this.end = timestamp;
            return;
        }
        throw new NullPointerException("Null end");
    }

    public Timestamp getEnd() {
        return this.end;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IntervalData{end=");
        sb.append(this.end);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntervalData)) {
            return false;
        }
        return this.end.equals(((IntervalData) obj).getEnd());
    }

    public int hashCode() {
        return this.end.hashCode() ^ 1000003;
    }
}
