package p006io.opencensus.metrics.export;

import javax.annotation.Nullable;
import p006io.opencensus.metrics.export.Summary.Snapshot;

/* renamed from: io.opencensus.metrics.export.AutoValue_Summary */
final class AutoValue_Summary extends Summary {
    private final Long count;
    private final Snapshot snapshot;
    private final Double sum;

    AutoValue_Summary(@Nullable Long l, @Nullable Double d, Snapshot snapshot2) {
        this.count = l;
        this.sum = d;
        if (snapshot2 != null) {
            this.snapshot = snapshot2;
            return;
        }
        throw new NullPointerException("Null snapshot");
    }

    @Nullable
    public Long getCount() {
        return this.count;
    }

    @Nullable
    public Double getSum() {
        return this.sum;
    }

    public Snapshot getSnapshot() {
        return this.snapshot;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Summary{count=");
        sb.append(this.count);
        sb.append(", sum=");
        sb.append(this.sum);
        sb.append(", snapshot=");
        sb.append(this.snapshot);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        if (r4.snapshot.equals(r5.getSnapshot()) != false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof p006io.opencensus.metrics.export.Summary
            r2 = 0
            if (r1 == 0) goto L_0x0044
            io.opencensus.metrics.export.Summary r5 = (p006io.opencensus.metrics.export.Summary) r5
            java.lang.Long r1 = r4.count
            if (r1 != 0) goto L_0x0016
            java.lang.Long r1 = r5.getCount()
            if (r1 != 0) goto L_0x0042
            goto L_0x0020
        L_0x0016:
            java.lang.Long r3 = r5.getCount()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0042
        L_0x0020:
            java.lang.Double r1 = r4.sum
            if (r1 != 0) goto L_0x002b
            java.lang.Double r1 = r5.getSum()
            if (r1 != 0) goto L_0x0042
            goto L_0x0035
        L_0x002b:
            java.lang.Double r3 = r5.getSum()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0042
        L_0x0035:
            io.opencensus.metrics.export.Summary$Snapshot r1 = r4.snapshot
            io.opencensus.metrics.export.Summary$Snapshot r5 = r5.getSnapshot()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x0042
            goto L_0x0043
        L_0x0042:
            r0 = 0
        L_0x0043:
            return r0
        L_0x0044:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p006io.opencensus.metrics.export.AutoValue_Summary.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Long l = this.count;
        int i = 0;
        int hashCode = ((l == null ? 0 : l.hashCode()) ^ 1000003) * 1000003;
        Double d = this.sum;
        if (d != null) {
            i = d.hashCode();
        }
        return ((hashCode ^ i) * 1000003) ^ this.snapshot.hashCode();
    }
}
