package com.google.cloud.datastore.core.number;

/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public final class NumberParts {
    private static final int DOUBLE_EXPONENT_BIAS = 1023;
    private static final int DOUBLE_MIN_EXPONENT = -1022;
    private static final int DOUBLE_SIGNIFICAND_BITS = 52;
    private static final long DOUBLE_SIGN_BIT = Long.MIN_VALUE;
    static final int NEGATIVE_INFINITE_EXPONENT = Integer.MIN_VALUE;
    static final int POSITIVE_INFINITE_EXPONENT = Integer.MAX_VALUE;
    static final int SIGNIFICAND_BITS = 64;
    private final int exponent;
    private final boolean negative;
    private final long significand;

    private static String doubleRepresentationError() {
        return null;
    }

    private NumberParts(boolean z, int i, long j) {
        this.negative = z;
        this.exponent = i;
        this.significand = j;
    }

    public boolean negative() {
        return this.negative;
    }

    public int exponent() {
        return this.exponent;
    }

    public long significand() {
        return this.significand;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NumberParts)) {
            return false;
        }
        NumberParts numberParts = (NumberParts) obj;
        if (!(this.negative == numberParts.negative && this.exponent == numberParts.exponent && this.significand == numberParts.significand)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = (((this.negative ? 1 : 0) * true) + this.exponent) * 31;
        long j = this.significand;
        return i + ((int) (j ^ (j >>> 32)));
    }

    public boolean isZero() {
        return exponent() == Integer.MIN_VALUE && significand() == 0;
    }

    public boolean isNaN() {
        return exponent() == Integer.MAX_VALUE && significand() != 0;
    }

    public boolean isInfinite() {
        return exponent() == Integer.MAX_VALUE && significand() == 0;
    }

    public static NumberParts create(boolean z, int i, long j) {
        if (i != Integer.MAX_VALUE || j == 0 || (z && j == 1)) {
            return new NumberParts(z, i, j);
        }
        throw new IllegalArgumentException("Invalid number parts: non-normalized NaN");
    }

    public static NumberParts fromLong(long j) {
        boolean z = false;
        if (j == 0) {
            return create(false, Integer.MIN_VALUE, 0);
        }
        if (j < 0) {
            j = -j;
            z = true;
        }
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j);
        int i = 63 - numberOfLeadingZeros;
        return create(z, i, (j & ((1 << i) ^ -1)) << (numberOfLeadingZeros + 1));
    }

    public static NumberParts fromDouble(double d) {
        long j;
        NumberParts numberParts;
        long doubleToLongBits = Double.doubleToLongBits(d);
        boolean z = d < 0.0d;
        int i = ((int) ((doubleToLongBits >>> 52) & 2047)) - DOUBLE_EXPONENT_BIAS;
        long j2 = doubleToLongBits & 4503599627370495L;
        if (i < DOUBLE_MIN_EXPONENT) {
            if (j2 == 0) {
                return create(false, Integer.MIN_VALUE, 0);
            }
            int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2);
            j = (j2 & ((1 << (63 - numberOfLeadingZeros)) ^ -1)) << (numberOfLeadingZeros + 1);
            i -= numberOfLeadingZeros - 12;
        } else if (i > DOUBLE_EXPONENT_BIAS) {
            if (j2 != 0) {
                numberParts = create(true, Integer.MAX_VALUE, 1);
            } else if (z) {
                numberParts = create(true, Integer.MAX_VALUE, 0);
            } else {
                numberParts = create(false, Integer.MAX_VALUE, 0);
            }
            return numberParts;
        } else {
            j = j2 << 12;
        }
        return create(z, i, j);
    }

    public NumberParts negate() {
        return (isZero() || isNaN()) ? this : create(!negative(), exponent(), significand());
    }

    public boolean representableAsDouble() {
        return doubleRepresentationError() == null;
    }

    public boolean representableAsLong() {
        return longRepresentationError() == null;
    }

    public double asDouble() {
        long j;
        long j2;
        String doubleRepresentationError = doubleRepresentationError();
        if (doubleRepresentationError != null) {
            throw new IllegalArgumentException(doubleRepresentationError);
        } else if (isZero()) {
            return 0.0d;
        } else {
            if (isInfinite()) {
                return negative() ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else if (isNaN()) {
                return Double.NaN;
            } else {
                long exponent2 = (long) exponent();
                long significand2 = significand() >>> 12;
                long j3 = 0;
                if (exponent2 >= -1022) {
                    j2 = significand2;
                    j = exponent2 + 1023;
                } else {
                    int exponent3 = -1022 - exponent();
                    j2 = (significand2 >>> exponent3) | (1 << (52 - exponent3));
                    j = 0;
                }
                long j4 = j2 | (j << 52);
                if (negative()) {
                    j3 = Long.MIN_VALUE;
                }
                return Double.longBitsToDouble(j4 | j3);
            }
        }
    }

    public long asLong() {
        String longRepresentationError = longRepresentationError();
        if (longRepresentationError != null) {
            throw new IllegalArgumentException(longRepresentationError);
        } else if (isZero()) {
            return 0;
        } else {
            if (exponent() == 63) {
                return Long.MIN_VALUE;
            }
            long significand2 = (significand() >>> ((63 - exponent()) + 1)) ^ (1 << exponent());
            if (negative()) {
                significand2 = -significand2;
            }
            return significand2;
        }
    }

    private String longRepresentationError() {
        if (isZero()) {
            return null;
        }
        String str = "Invalid encoded long ";
        if (isInfinite()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(this);
            sb.append(": Infinity is not a long");
            return sb.toString();
        } else if (isNaN()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(this);
            sb2.append(": NaN is not a long");
            return sb2.toString();
        } else if (exponent() == 63) {
            if (significand() == 0 && negative()) {
                return null;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(this);
            sb3.append(": overflow");
            return sb3.toString();
        } else if (exponent() < 0 || exponent() > 63) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append(this);
            sb4.append(": exponent ");
            sb4.append(exponent());
            sb4.append(" too large");
            return sb4.toString();
        } else {
            if (exponent() >= 64 - Long.numberOfTrailingZeros(significand())) {
                return null;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str);
            sb5.append(this);
            sb5.append(": contains fractional part");
            return sb5.toString();
        }
    }
}
