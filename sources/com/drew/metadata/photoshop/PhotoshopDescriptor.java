package com.drew.metadata.photoshop;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class PhotoshopDescriptor extends TagDescriptor<PhotoshopDirectory> {
    public PhotoshopDescriptor(@NotNull PhotoshopDirectory photoshopDirectory) {
        super(photoshopDirectory);
    }

    public String getDescription(int i) {
        if (i != 1002) {
            if (i == 1005) {
                return getResolutionInfoDescription();
            }
            if (i == 1028) {
                return getBinaryDataString(i);
            }
            if (i == 1030) {
                return getJpegQualityString();
            }
            if (!(i == 1044 || i == 1054)) {
                if (i == 1057) {
                    return getVersionDescription();
                }
                if (i == 1062) {
                    return getPrintScaleDescription();
                }
                if (i == 1064) {
                    return getPixelAspectRatioString();
                }
                if (i == 2999) {
                    return getClippingPathNameString(i);
                }
                if (i != 1049) {
                    if (i == 1050) {
                        return getSlicesDescription();
                    }
                    switch (i) {
                        case PhotoshopDirectory.TAG_THUMBNAIL_OLD /*1033*/:
                        case PhotoshopDirectory.TAG_THUMBNAIL /*1036*/:
                            return getThumbnailDescription(i);
                        case PhotoshopDirectory.TAG_COPYRIGHT /*1034*/:
                            return getBooleanString(i);
                        case PhotoshopDirectory.TAG_URL /*1035*/:
                            break;
                        case 1037:
                            break;
                        default:
                            if (i < 2000 || i > 2998) {
                                return super.getDescription(i);
                            }
                            return getPathString(i);
                    }
                }
            }
            return get32BitNumberString(i);
        }
        return getSimpleString(i);
    }

    @Nullable
    public String getJpegQualityString() {
        String str;
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(1030);
            if (byteArray == null) {
                return ((PhotoshopDirectory) this._directory).getString(1030);
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int uInt16 = byteArrayReader.getUInt16(0);
            int uInt162 = byteArrayReader.getUInt16(2);
            int uInt163 = byteArrayReader.getUInt16(4);
            int i = (uInt16 > 65535 || uInt16 < 65533) ? uInt16 <= 8 ? uInt16 + 4 : uInt16 : uInt16 - 65532;
            switch (uInt16) {
                case 0:
                    str = "Low";
                    break;
                case 1:
                case 2:
                case 3:
                    str = "Medium";
                    break;
                case 4:
                case 5:
                    str = "High";
                    break;
                case 6:
                case 7:
                case 8:
                    str = "Maximum";
                    break;
                default:
                    switch (uInt16) {
                        case 65533:
                        case 65534:
                        case 65535:
                            break;
                        default:
                            str = "Unknown";
                            break;
                    }
                    str = "Low";
                    break;
            }
            String str2 = "Unknown 0x%04X";
            String str3 = uInt162 != 0 ? uInt162 != 1 ? uInt162 != 257 ? String.format(str2, new Object[]{Integer.valueOf(uInt162)}) : "Progressive" : "Optimised" : "Standard";
            return String.format("%d (%s), %s format, %s scans", new Object[]{Integer.valueOf(i), str, str3, (uInt163 < 1 || uInt163 > 3) ? String.format(str2, new Object[]{Integer.valueOf(uInt163)}) : String.format("%d", new Object[]{Integer.valueOf(uInt163 + 2)})});
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public String getPixelAspectRatioString() {
        String str = null;
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_PIXEL_ASPECT_RATIO);
            if (byteArray == null) {
                return null;
            }
            str = Double.toString(new ByteArrayReader(byteArray).getDouble64(4));
            return str;
        } catch (Exception unused) {
        }
    }

    @Nullable
    public String getPrintScaleDescription() {
        String str = null;
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_PRINT_SCALE);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(0);
            float float32 = byteArrayReader.getFloat32(2);
            float float322 = byteArrayReader.getFloat32(6);
            float float323 = byteArrayReader.getFloat32(10);
            if (int32 == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Centered, Scale ");
                sb.append(float323);
                str = sb.toString();
                return str;
            } else if (int32 == 1) {
                return "Size to fit";
            } else {
                if (int32 != 2) {
                    return String.format("Unknown %04X, X:%s Y:%s, Scale:%s", new Object[]{Integer.valueOf(int32), Float.valueOf(float32), Float.valueOf(float322), Float.valueOf(float323)});
                }
                return String.format("User defined, X:%s Y:%s, Scale:%s", new Object[]{Float.valueOf(float32), Float.valueOf(float322), Float.valueOf(float323)});
            }
        } catch (Exception unused) {
        }
    }

    @Nullable
    public String getResolutionInfoDescription() {
        String str = null;
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_RESOLUTION_INFO);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            float s15Fixed16 = byteArrayReader.getS15Fixed16(0);
            float s15Fixed162 = byteArrayReader.getS15Fixed16(8);
            DecimalFormat decimalFormat = new DecimalFormat("0.##");
            StringBuilder sb = new StringBuilder();
            sb.append(decimalFormat.format((double) s15Fixed16));
            sb.append("x");
            sb.append(decimalFormat.format((double) s15Fixed162));
            sb.append(" DPI");
            str = sb.toString();
            return str;
        } catch (Exception unused) {
        }
    }

    @Nullable
    public String getVersionDescription() {
        String str = "UTF-16";
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_VERSION);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(0);
            int int322 = byteArrayReader.getInt32(5) * 2;
            String string = byteArrayReader.getString(9, int322, str);
            int i = 9 + int322;
            int int323 = byteArrayReader.getInt32(i);
            int i2 = i + 4;
            int i3 = int323 * 2;
            return String.format("%d (%s, %s) %d", new Object[]{Integer.valueOf(int32), string, byteArrayReader.getString(i2, i3, str), Integer.valueOf(byteArrayReader.getInt32(i2 + i3))});
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public String getSlicesDescription() {
        String str = null;
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_SLICES);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(20) * 2;
            str = String.format("%s (%d,%d,%d,%d) %d Slices", new Object[]{byteArrayReader.getString(24, int32, "UTF-16"), Integer.valueOf(byteArrayReader.getInt32(4)), Integer.valueOf(byteArrayReader.getInt32(8)), Integer.valueOf(byteArrayReader.getInt32(12)), Integer.valueOf(byteArrayReader.getInt32(16)), Integer.valueOf(byteArrayReader.getInt32(int32 + 24))});
            return str;
        } catch (IOException unused) {
        }
    }

    @Nullable
    public String getThumbnailDescription(int i) {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(0);
            int int322 = byteArrayReader.getInt32(4);
            int int323 = byteArrayReader.getInt32(8);
            int int324 = byteArrayReader.getInt32(16);
            int int325 = byteArrayReader.getInt32(20);
            int int326 = byteArrayReader.getInt32(24);
            String str = "%s, %dx%d, Decomp %d bytes, %d bpp, %d bytes";
            Object[] objArr = new Object[6];
            objArr[0] = int32 == 1 ? "JpegRGB" : "RawRGB";
            objArr[1] = Integer.valueOf(int322);
            objArr[2] = Integer.valueOf(int323);
            objArr[3] = Integer.valueOf(int324);
            objArr[4] = Integer.valueOf(int326);
            objArr[5] = Integer.valueOf(int325);
            return String.format(str, objArr);
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    private String getBooleanString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null || byteArray.length == 0) {
            return null;
        }
        return byteArray[0] == 0 ? "No" : "Yes";
    }

    @Nullable
    private String get32BitNumberString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
        try {
            return String.format("%d", new Object[]{Integer.valueOf(byteArrayReader.getInt32(0))});
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    private String getSimpleString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        return new String(byteArray);
    }

    @Nullable
    private String getBinaryDataString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        return String.format("%d bytes binary data", new Object[]{Integer.valueOf(byteArray.length)});
    }

    @Nullable
    public String getClippingPathNameString(int i) {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            return new String(byteArrayReader.getBytes(1, byteArrayReader.getByte(0)), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public String getPathString(int i) {
        String str;
        String str2;
        String str3;
        int i2;
        ByteArrayReader byteArrayReader;
        ArrayList arrayList;
        String str4;
        Subpath subpath;
        ArrayList arrayList2;
        Knot knot;
        Knot knot2;
        String str5 = ")";
        String str6 = ",";
        String str7 = " (";
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader2 = new ByteArrayReader(byteArray);
            short s = 1;
            int length = ((int) ((byteArrayReader2.getLength() - ((long) byteArrayReader2.getByte(((int) byteArrayReader2.getLength()) - 1))) - 1)) / 26;
            Subpath subpath2 = new Subpath();
            Subpath subpath3 = new Subpath();
            ArrayList arrayList3 = new ArrayList();
            String str8 = null;
            Subpath subpath4 = subpath3;
            Subpath subpath5 = subpath2;
            int i3 = 0;
            while (i3 < length) {
                int i4 = i3 * 26;
                try {
                    short int16 = byteArrayReader2.getInt16(i4);
                    Subpath subpath6 = subpath4;
                    String str9 = "Linked";
                    String str10 = "Unlinked";
                    switch (int16) {
                        case 0:
                            str3 = str5;
                            str2 = str6;
                            i2 = length;
                            byteArrayReader = byteArrayReader2;
                            arrayList = arrayList3;
                            str4 = str8;
                            Subpath subpath7 = subpath6;
                            if (subpath5.size() != 0) {
                                arrayList.add(subpath5);
                            }
                            subpath4 = subpath7;
                            subpath5 = new Subpath("Closed Subpath");
                            break;
                        case 1:
                        case 2:
                            str3 = str5;
                            str2 = str6;
                            i2 = length;
                            arrayList2 = arrayList3;
                            str4 = str8;
                            subpath = subpath6;
                            if (int16 == 1) {
                                knot = new Knot(str9);
                            } else {
                                knot = new Knot(str10);
                            }
                            int i5 = 0;
                            while (i5 < 6) {
                                int i6 = i5 * 4;
                                double int8 = (double) byteArrayReader2.getInt8(i6 + 2 + i4);
                                double int24 = (double) byteArrayReader2.getInt24(i6 + 3 + i4);
                                ByteArrayReader byteArrayReader3 = byteArrayReader2;
                                int i7 = i4;
                                double pow = Math.pow(2.0d, 24.0d);
                                Double.isNaN(int24);
                                double d = int24 / pow;
                                Double.isNaN(int8);
                                knot.setPoint(i5, int8 + d);
                                i5++;
                                byteArrayReader2 = byteArrayReader3;
                                i4 = i7;
                            }
                            byteArrayReader = byteArrayReader2;
                            subpath5.add(knot);
                            break;
                        case 3:
                            str3 = str5;
                            str2 = str6;
                            i2 = length;
                            ArrayList arrayList4 = arrayList3;
                            str4 = str8;
                            Subpath subpath8 = subpath6;
                            if (subpath8.size() != 0) {
                                arrayList = arrayList4;
                                arrayList.add(subpath8);
                            } else {
                                arrayList = arrayList4;
                            }
                            subpath4 = new Subpath("Open Subpath");
                            byteArrayReader = byteArrayReader2;
                            break;
                        case 4:
                        case 5:
                            if (int16 == 4) {
                                knot2 = new Knot(str9);
                            } else {
                                knot2 = new Knot(str10);
                            }
                            int i8 = 0;
                            while (i8 < 6) {
                                int i9 = i8 * 4;
                                ArrayList arrayList5 = arrayList3;
                                double int82 = (double) byteArrayReader2.getInt8(i9 + 2 + i4);
                                int i10 = length;
                                double int242 = (double) byteArrayReader2.getInt24(i9 + 3 + i4);
                                String str11 = str5;
                                String str12 = str6;
                                String str13 = str8;
                                double pow2 = Math.pow(2.0d, 24.0d);
                                Double.isNaN(int242);
                                double d2 = int242 / pow2;
                                Double.isNaN(int82);
                                knot2.setPoint(i8, int82 + d2);
                                i8++;
                                arrayList3 = arrayList5;
                                length = i10;
                                str5 = str11;
                                str6 = str12;
                                str8 = str13;
                            }
                            str3 = str5;
                            str2 = str6;
                            i2 = length;
                            ArrayList arrayList6 = arrayList3;
                            str4 = str8;
                            subpath = subpath6;
                            subpath.add(knot2);
                            byteArrayReader = byteArrayReader2;
                            arrayList2 = arrayList6;
                            break;
                        case 8:
                            str3 = str5;
                            str2 = str6;
                            str8 = byteArrayReader2.getInt16(i4 + 2) == s ? "with all pixels" : "without all pixels";
                            i2 = length;
                            byteArrayReader = byteArrayReader2;
                            arrayList = arrayList3;
                            subpath4 = subpath6;
                            continue;
                        default:
                            str3 = str5;
                            str2 = str6;
                            i2 = length;
                            byteArrayReader = byteArrayReader2;
                            arrayList2 = arrayList3;
                            str4 = str8;
                            subpath = subpath6;
                            break;
                    }
                    subpath4 = subpath;
                    str8 = str4;
                    i3++;
                    arrayList3 = arrayList;
                    byteArrayReader2 = byteArrayReader;
                    length = i2;
                    str5 = str3;
                    str6 = str2;
                    s = 1;
                } catch (Exception unused) {
                    str = null;
                    return str;
                }
            }
            String str14 = str5;
            String str15 = str6;
            ByteArrayReader byteArrayReader4 = byteArrayReader2;
            ArrayList arrayList7 = arrayList3;
            Subpath subpath9 = subpath4;
            String str16 = str8;
            if (subpath5.size() != 0) {
                arrayList7.add(subpath5);
            }
            if (subpath9.size() != 0) {
                arrayList7.add(subpath9);
            }
            byte b = byteArrayReader4.getByte(((int) byteArrayReader4.getLength()) - 1);
            String string = byteArrayReader4.getString((((int) byteArrayReader4.getLength()) - b) - 1, (int) b, Charsets.ASCII);
            StringBuilder sb = new StringBuilder();
            sb.append('\"');
            sb.append(string);
            sb.append('\"');
            sb.append(" having ");
            if (str16 != null) {
                sb.append("initial fill rule \"");
                sb.append(str16);
                sb.append("\" and ");
            }
            sb.append(arrayList7.size());
            sb.append(arrayList7.size() == 1 ? " subpath:" : " subpaths:");
            Iterator it = arrayList7.iterator();
            while (it.hasNext()) {
                Subpath subpath10 = (Subpath) it.next();
                sb.append("\n- ");
                sb.append(subpath10.getType());
                sb.append(" with ");
                sb.append(arrayList7.size());
                sb.append(arrayList7.size() == 1 ? " knot:" : " knots:");
                for (Knot knot3 : subpath10.getKnots()) {
                    sb.append("\n  - ");
                    sb.append(knot3.getType());
                    sb.append(str7);
                    sb.append(knot3.getPoint(0));
                    String str17 = str15;
                    sb.append(str17);
                    sb.append(knot3.getPoint(1));
                    String str18 = str14;
                    sb.append(str18);
                    sb.append(str7);
                    sb.append(knot3.getPoint(2));
                    sb.append(str17);
                    sb.append(knot3.getPoint(3));
                    sb.append(str18);
                    sb.append(str7);
                    sb.append(knot3.getPoint(4));
                    sb.append(str17);
                    sb.append(knot3.getPoint(5));
                    sb.append(str18);
                    str15 = str17;
                    str14 = str18;
                }
            }
            return sb.toString();
        } catch (Exception unused2) {
            str = null;
            return str;
        }
    }
}
