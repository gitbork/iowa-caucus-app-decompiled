package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24;
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f201c;

    /* renamed from: d */
    private final int f202d;

    /* renamed from: k0 */
    private final long f203k0;

    /* renamed from: k1 */
    private final long f204k1;

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f205b = 0;

        /* renamed from: c */
        private final int f206c;

        /* renamed from: d */
        private final int f207d;
        private long finalM = 0;

        /* renamed from: v0 */
        private long f208v0 = 8317987319222330741L;

        /* renamed from: v1 */
        private long f209v1 = 7237128888997146477L;

        /* renamed from: v2 */
        private long f210v2 = 7816392313619706465L;

        /* renamed from: v3 */
        private long f211v3 = 8387220255154660723L;

        SipHasher(int i, int i2, long j, long j2) {
            super(8);
            this.f206c = i;
            this.f207d = i2;
            this.f208v0 ^= j;
            this.f209v1 ^= j2;
            this.f210v2 ^= j;
            this.f211v3 ^= j2;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer byteBuffer) {
            this.f205b += 8;
            processM(byteBuffer.getLong());
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer byteBuffer) {
            this.f205b += (long) byteBuffer.remaining();
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (((long) byteBuffer.get()) & 255) << i;
                i += 8;
            }
        }

        public HashCode makeHash() {
            this.finalM ^= this.f205b << 56;
            processM(this.finalM);
            this.f210v2 ^= 255;
            sipRound(this.f207d);
            return HashCode.fromLong(((this.f208v0 ^ this.f209v1) ^ this.f210v2) ^ this.f211v3);
        }

        private void processM(long j) {
            this.f211v3 ^= j;
            sipRound(this.f206c);
            this.f208v0 = j ^ this.f208v0;
        }

        private void sipRound(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                long j = this.f208v0;
                long j2 = this.f209v1;
                this.f208v0 = j + j2;
                this.f210v2 += this.f211v3;
                this.f209v1 = Long.rotateLeft(j2, 13);
                this.f211v3 = Long.rotateLeft(this.f211v3, 16);
                long j3 = this.f209v1;
                long j4 = this.f208v0;
                this.f209v1 = j3 ^ j4;
                this.f211v3 ^= this.f210v2;
                this.f208v0 = Long.rotateLeft(j4, 32);
                long j5 = this.f210v2;
                long j6 = this.f209v1;
                this.f210v2 = j5 + j6;
                this.f208v0 += this.f211v3;
                this.f209v1 = Long.rotateLeft(j6, 17);
                this.f211v3 = Long.rotateLeft(this.f211v3, 21);
                long j7 = this.f209v1;
                long j8 = this.f210v2;
                this.f209v1 = j7 ^ j8;
                this.f211v3 ^= this.f208v0;
                this.f210v2 = Long.rotateLeft(j8, 32);
            }
        }
    }

    public int bits() {
        return 64;
    }

    static {
        SipHashFunction sipHashFunction = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
        SIP_HASH_24 = sipHashFunction;
    }

    SipHashFunction(int i, int i2, long j, long j2) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i);
        if (i2 <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i2);
        this.f201c = i;
        this.f202d = i2;
        this.f203k0 = j;
        this.f204k1 = j2;
    }

    public Hasher newHasher() {
        SipHasher sipHasher = new SipHasher(this.f201c, this.f202d, this.f203k0, this.f204k1);
        return sipHasher;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hashing.sipHash");
        sb.append(this.f201c);
        sb.append("");
        sb.append(this.f202d);
        sb.append("(");
        sb.append(this.f203k0);
        sb.append(", ");
        sb.append(this.f204k1);
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        if (this.f201c == sipHashFunction.f201c && this.f202d == sipHashFunction.f202d && this.f203k0 == sipHashFunction.f203k0 && this.f204k1 == sipHashFunction.f204k1) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.f201c) ^ this.f202d)) ^ this.f203k0) ^ this.f204k1);
    }
}
