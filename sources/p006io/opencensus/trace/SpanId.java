package p006io.opencensus.trace;

import java.util.Random;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import p006io.opencensus.internal.Utils;

@Immutable
/* renamed from: io.opencensus.trace.SpanId */
public final class SpanId implements Comparable<SpanId> {
    private static final int BASE16_SIZE = 16;
    public static final SpanId INVALID = new SpanId(0);
    private static final long INVALID_ID = 0;
    public static final int SIZE = 8;

    /* renamed from: id */
    private final long f473id;

    private SpanId(long j) {
        this.f473id = j;
    }

    public static SpanId fromBytes(byte[] bArr) {
        Utils.checkNotNull(bArr, "src");
        Utils.checkArgument(bArr.length == 8, "Invalid size: expected %s, got %s", Integer.valueOf(8), Integer.valueOf(bArr.length));
        return fromBytes(bArr, 0);
    }

    public static SpanId fromBytes(byte[] bArr, int i) {
        Utils.checkNotNull(bArr, "src");
        return new SpanId(BigendianEncoding.longFromByteArray(bArr, i));
    }

    public static SpanId fromLowerBase16(CharSequence charSequence) {
        Utils.checkNotNull(charSequence, "src");
        Utils.checkArgument(charSequence.length() == 16, "Invalid size: expected %s, got %s", Integer.valueOf(16), Integer.valueOf(charSequence.length()));
        return fromLowerBase16(charSequence, 0);
    }

    public static SpanId fromLowerBase16(CharSequence charSequence, int i) {
        Utils.checkNotNull(charSequence, "src");
        return new SpanId(BigendianEncoding.longFromBase16String(charSequence, i));
    }

    public static SpanId generateRandomId(Random random) {
        long nextLong;
        do {
            nextLong = random.nextLong();
        } while (nextLong == 0);
        return new SpanId(nextLong);
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[8];
        BigendianEncoding.longToByteArray(this.f473id, bArr, 0);
        return bArr;
    }

    public void copyBytesTo(byte[] bArr, int i) {
        BigendianEncoding.longToByteArray(this.f473id, bArr, i);
    }

    public void copyLowerBase16To(char[] cArr, int i) {
        BigendianEncoding.longToBase16String(this.f473id, cArr, i);
    }

    public boolean isValid() {
        return this.f473id != 0;
    }

    public String toLowerBase16() {
        char[] cArr = new char[16];
        copyLowerBase16To(cArr, 0);
        return new String(cArr);
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanId)) {
            return false;
        }
        if (this.f473id != ((SpanId) obj).f473id) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long j = this.f473id;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpanId{spanId=");
        sb.append(toLowerBase16());
        sb.append("}");
        return sb.toString();
    }

    public int compareTo(SpanId spanId) {
        long j = this.f473id;
        long j2 = spanId.f473id;
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }
}
