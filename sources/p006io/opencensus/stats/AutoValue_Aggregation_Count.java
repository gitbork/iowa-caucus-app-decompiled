package p006io.opencensus.stats;

import p006io.opencensus.stats.Aggregation.Count;

/* renamed from: io.opencensus.stats.AutoValue_Aggregation_Count */
final class AutoValue_Aggregation_Count extends Count {
    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "Count{}";
    }

    AutoValue_Aggregation_Count() {
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Count);
    }
}
