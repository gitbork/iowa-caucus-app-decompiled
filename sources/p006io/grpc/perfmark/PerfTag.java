package p006io.grpc.perfmark;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: io.grpc.perfmark.PerfTag */
public final class PerfTag {
    private static final long NULL_NUMERIC_TAG = 0;
    /* access modifiers changed from: private */
    public static final String NULL_STRING_TAG = null;
    private final long numericTag;
    private final String stringTag;

    /* renamed from: io.grpc.perfmark.PerfTag$TagFactory */
    static final class TagFactory {
        private TagFactory() {
            throw new AssertionError("nope");
        }

        public static PerfTag create(long j, String str) {
            return new PerfTag(j, str);
        }

        public static PerfTag create(String str) {
            return new PerfTag(0, str);
        }

        public static PerfTag create(long j) {
            return new PerfTag(j, PerfTag.NULL_STRING_TAG);
        }

        static PerfTag create() {
            return new PerfTag(0, PerfTag.NULL_STRING_TAG);
        }
    }

    private PerfTag(long j, @Nullable String str) {
        this.numericTag = j;
        this.stringTag = str;
    }

    public long getNumericTag() {
        return this.numericTag;
    }

    @Nullable
    public String getStringTag() {
        return this.stringTag;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tag(numericTag=");
        sb.append(this.numericTag);
        sb.append(",stringTag='");
        sb.append(this.stringTag);
        sb.append("')");
        return sb.toString();
    }

    public int hashCode() {
        long j = this.numericTag;
        int i = (int) (j ^ (j >>> 32));
        String str = this.stringTag;
        return i + (str != null ? str.hashCode() : 31);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof PerfTag)) {
            return false;
        }
        PerfTag perfTag = (PerfTag) obj;
        if (this.numericTag == perfTag.numericTag) {
            String str = this.stringTag;
            String str2 = perfTag.stringTag;
            if (str == str2 || (str != null && str.equals(str2))) {
                z = true;
            }
        }
        return z;
    }
}
