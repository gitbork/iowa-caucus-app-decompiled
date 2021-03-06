package com.adobe.xmp.impl;

import com.google.common.base.Ascii;

public class Base64 {
    private static final byte EQUAL = -3;
    private static final byte INVALID = -1;
    private static final byte WHITESPACE = -2;
    private static byte[] ascii = new byte[255];
    private static byte[] base64 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    static {
        int i = 0;
        for (int i2 = 0; i2 < 255; i2++) {
            ascii[i2] = -1;
        }
        while (true) {
            byte[] bArr = base64;
            if (i < bArr.length) {
                ascii[bArr[i]] = (byte) i;
                i++;
            } else {
                byte[] bArr2 = ascii;
                bArr2[9] = WHITESPACE;
                bArr2[10] = WHITESPACE;
                bArr2[13] = WHITESPACE;
                bArr2[32] = WHITESPACE;
                bArr2[61] = EQUAL;
                return;
            }
        }
    }

    public static final String decode(String str) {
        return new String(decode(str.getBytes()));
    }

    public static final byte[] decode(byte[] bArr) throws IllegalArgumentException {
        int i = 0;
        int i2 = 0;
        for (byte b : bArr) {
            byte b2 = ascii[b];
            if (b2 >= 0) {
                int i3 = i2 + 1;
                bArr[i2] = b2;
                i2 = i3;
            } else if (b2 == -1) {
                throw new IllegalArgumentException("Invalid base 64 string");
            }
        }
        while (i2 > 0 && bArr[i2 - 1] == -3) {
            i2--;
        }
        byte[] bArr2 = new byte[((i2 * 3) / 4)];
        int i4 = 0;
        while (i < bArr2.length - 2) {
            int i5 = i4 + 1;
            bArr2[i] = (byte) (((bArr[i4] << 2) & 255) | ((bArr[i5] >>> 4) & 3));
            int i6 = i4 + 2;
            bArr2[i + 1] = (byte) (((bArr[i5] << 4) & 255) | ((bArr[i6] >>> 2) & 15));
            bArr2[i + 2] = (byte) (((bArr[i6] << 6) & 255) | (bArr[i4 + 3] & 63));
            i4 += 4;
            i += 3;
        }
        if (i < bArr2.length) {
            bArr2[i] = (byte) (((bArr[i4] << 2) & 255) | ((bArr[i4 + 1] >>> 4) & 3));
        }
        int i7 = i + 1;
        if (i7 < bArr2.length) {
            bArr2[i7] = (byte) (((bArr[i4 + 2] >>> 2) & 15) | ((bArr[i4 + 1] << 4) & 255));
        }
        return bArr2;
    }

    public static final String encode(String str) {
        return new String(encode(str.getBytes()));
    }

    public static final byte[] encode(byte[] bArr) {
        return encode(bArr, 0);
    }

    public static final byte[] encode(byte[] bArr, int i) {
        int i2 = (i / 4) * 4;
        if (i2 < 0) {
            i2 = 0;
        }
        int length = ((bArr.length + 2) / 3) * 4;
        if (i2 > 0) {
            length += (length - 1) / i2;
        }
        byte[] bArr2 = new byte[length];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 + 3 <= bArr.length) {
            int i6 = i3 + 1;
            int i7 = i6 + 1;
            int i8 = ((bArr[i3] & 255) << Ascii.DLE) | ((bArr[i6] & 255) << 8);
            int i9 = i7 + 1;
            int i10 = i8 | ((bArr[i7] & 255) << 0);
            int i11 = (i10 & 16515072) >> 18;
            int i12 = i4 + 1;
            byte[] bArr3 = base64;
            bArr2[i4] = bArr3[i11];
            int i13 = i12 + 1;
            bArr2[i12] = bArr3[(i10 & 258048) >> 12];
            int i14 = i13 + 1;
            bArr2[i13] = bArr3[(i10 & 4032) >> 6];
            i4 = i14 + 1;
            bArr2[i14] = bArr3[i10 & 63];
            i5 += 4;
            if (i4 < length && i2 > 0 && i5 % i2 == 0) {
                int i15 = i4 + 1;
                bArr2[i4] = 10;
                i4 = i15;
            }
            i3 = i9;
        }
        if (bArr.length - i3 == 2) {
            int i16 = ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3] & 255) << Ascii.DLE);
            int i17 = (i16 & 16515072) >> 18;
            int i18 = i4 + 1;
            byte[] bArr4 = base64;
            bArr2[i4] = bArr4[i17];
            int i19 = i18 + 1;
            bArr2[i18] = bArr4[(i16 & 258048) >> 12];
            int i20 = i19 + 1;
            bArr2[i19] = bArr4[(i16 & 4032) >> 6];
            bArr2[i20] = 61;
        } else if (bArr.length - i3 == 1) {
            int i21 = (bArr[i3] & 255) << Ascii.DLE;
            int i22 = (i21 & 16515072) >> 18;
            int i23 = i4 + 1;
            byte[] bArr5 = base64;
            bArr2[i4] = bArr5[i22];
            int i24 = i23 + 1;
            bArr2[i23] = bArr5[(i21 & 258048) >> 12];
            int i25 = i24 + 1;
            bArr2[i24] = 61;
            bArr2[i25] = 61;
        }
        return bArr2;
    }
}
