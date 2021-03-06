package p006io.opencensus.trace;

import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import p006io.opencensus.internal.Utils;

@Immutable
/* renamed from: io.opencensus.trace.TraceOptions */
public final class TraceOptions {
    private static final int BASE16_SIZE = 2;
    public static final TraceOptions DEFAULT = fromByte(0);
    private static final byte DEFAULT_OPTIONS = 0;
    private static final byte IS_SAMPLED = 1;
    public static final int SIZE = 1;
    private final byte options;

    /* renamed from: io.opencensus.trace.TraceOptions$Builder */
    public static final class Builder {
        private byte options;

        private Builder(byte b) {
            this.options = b;
        }

        @Deprecated
        public Builder setIsSampled() {
            return setIsSampled(true);
        }

        public Builder setIsSampled(boolean z) {
            if (z) {
                this.options = (byte) (this.options | 1);
            } else {
                this.options = (byte) (this.options & -2);
            }
            return this;
        }

        public TraceOptions build() {
            return TraceOptions.fromByte(this.options);
        }
    }

    private TraceOptions(byte b) {
        this.options = b;
    }

    @Deprecated
    public static TraceOptions fromBytes(byte[] bArr) {
        Utils.checkNotNull(bArr, "buffer");
        Utils.checkArgument(bArr.length == 1, "Invalid size: expected %s, got %s", Integer.valueOf(1), Integer.valueOf(bArr.length));
        return fromByte(bArr[0]);
    }

    @Deprecated
    public static TraceOptions fromBytes(byte[] bArr, int i) {
        Utils.checkIndex(i, bArr.length);
        return fromByte(bArr[i]);
    }

    public static TraceOptions fromByte(byte b) {
        return new TraceOptions(b);
    }

    public static TraceOptions fromLowerBase16(CharSequence charSequence, int i) {
        return new TraceOptions(BigendianEncoding.byteFromBase16String(charSequence, i));
    }

    public byte getByte() {
        return this.options;
    }

    @Deprecated
    public byte[] getBytes() {
        return new byte[]{this.options};
    }

    public void copyBytesTo(byte[] bArr, int i) {
        Utils.checkIndex(i, bArr.length);
        bArr[i] = this.options;
    }

    public void copyLowerBase16To(char[] cArr, int i) {
        BigendianEncoding.byteToBase16String(this.options, cArr, i);
    }

    public String toLowerBase16() {
        char[] cArr = new char[2];
        copyLowerBase16To(cArr, 0);
        return new String(cArr);
    }

    public static Builder builder() {
        return new Builder(0);
    }

    public static Builder builder(TraceOptions traceOptions) {
        return new Builder(traceOptions.options);
    }

    public boolean isSampled() {
        return hasOption(1);
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceOptions)) {
            return false;
        }
        if (this.options != ((TraceOptions) obj).options) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(new byte[]{this.options});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TraceOptions{sampled=");
        sb.append(isSampled());
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public byte getOptions() {
        return this.options;
    }

    private boolean hasOption(int i) {
        return (i & this.options) != 0;
    }
}
