package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class OlympusImageProcessingMakernoteDescriptor extends TagDescriptor<OlympusImageProcessingMakernoteDirectory> {
    public OlympusImageProcessingMakernoteDescriptor(@NotNull OlympusImageProcessingMakernoteDirectory olympusImageProcessingMakernoteDirectory) {
        super(olympusImageProcessingMakernoteDirectory);
    }

    @Nullable
    public String getDescription(int i) {
        if (i == 0) {
            return getImageProcessingVersionDescription();
        }
        if (i == 512) {
            return getColorMatrixDescription();
        }
        if (i == 4124) {
            return getMultipleExposureModeDescription();
        }
        if (i == 4370) {
            return getAspectRatioDescription();
        }
        if (i == 6400) {
            return getKeystoneCompensationDescription();
        }
        if (i == 6401) {
            return getKeystoneDirectionDescription();
        }
        switch (i) {
            case 4112:
                return getNoiseReduction2Description();
            case 4113:
                return getDistortionCorrection2Description();
            case 4114:
                return getShadingCompensation2Description();
            default:
                return super.getDescription(i);
        }
    }

    @Nullable
    public String getImageProcessingVersionDescription() {
        return getVersionBytesDescription(0, 4);
    }

    @Nullable
    public String getColorMatrixDescription() {
        int[] intArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getIntArray(512);
        if (intArray == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            sb.append((short) intArray[i]);
        }
        return sb.toString();
    }

    @Nullable
    public String getNoiseReduction2Description() {
        Integer integer = ((OlympusImageProcessingMakernoteDirectory) this._directory).getInteger(4112);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        short shortValue = integer.shortValue();
        if ((shortValue & 1) != 0) {
            sb.append("Noise Reduction, ");
        }
        if (((shortValue >> 1) & 1) != 0) {
            sb.append("Noise Filter, ");
        }
        if (((shortValue >> 2) & 1) != 0) {
            sb.append("Noise Filter (ISO Boost), ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String getDistortionCorrection2Description() {
        return getIndexedDescription(4113, "Off", "On");
    }

    @Nullable
    public String getShadingCompensation2Description() {
        return getIndexedDescription(4114, "Off", "On");
    }

    @Nullable
    public String getMultipleExposureModeDescription() {
        int[] intArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getIntArray(4124);
        if (intArray == null) {
            Integer integer = ((OlympusImageProcessingMakernoteDirectory) this._directory).getInteger(4124);
            if (integer == null) {
                return null;
            }
            intArray = new int[]{integer.intValue()};
        }
        if (intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        short s = (short) intArray[0];
        if (s == 0) {
            sb.append("Off");
        } else if (s == 2) {
            sb.append("On (2 frames)");
        } else if (s != 3) {
            sb.append("Unknown (");
            sb.append((short) intArray[0]);
            sb.append(")");
        } else {
            sb.append("On (3 frames)");
        }
        if (intArray.length > 1) {
            sb.append("; ");
            sb.append((short) intArray[1]);
        }
        return sb.toString();
    }

    @Nullable
    public String getAspectRatioDescription() {
        String str;
        byte[] byteArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getByteArray(OlympusImageProcessingMakernoteDirectory.TagAspectRatio);
        if (byteArray == null || byteArray.length < 2) {
            return null;
        }
        String format = String.format("%d %d", new Object[]{Byte.valueOf(byteArray[0]), Byte.valueOf(byteArray[1])});
        if (format.equals("1 1")) {
            str = "4:3";
        } else if (format.equals("1 4")) {
            str = "1:1";
        } else if (format.equals("2 1")) {
            str = "3:2 (RAW)";
        } else if (format.equals("2 2")) {
            str = "3:2";
        } else if (format.equals("3 1")) {
            str = "16:9 (RAW)";
        } else if (format.equals("3 3")) {
            str = "16:9";
        } else if (format.equals("4 1")) {
            str = "1:1 (RAW)";
        } else if (format.equals("4 4")) {
            str = "6:6";
        } else if (format.equals("5 5")) {
            str = "5:4";
        } else if (format.equals("6 6")) {
            str = "7:6";
        } else if (format.equals("7 7")) {
            str = "6:5";
        } else if (format.equals("8 8")) {
            str = "7:5";
        } else if (format.equals("9 1")) {
            str = "3:4 (RAW)";
        } else if (format.equals("9 9")) {
            str = "3:4";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown (");
            sb.append(format);
            sb.append(")");
            str = sb.toString();
        }
        return str;
    }

    @Nullable
    public String getKeystoneCompensationDescription() {
        String str;
        byte[] byteArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getByteArray(OlympusImageProcessingMakernoteDirectory.TagKeystoneCompensation);
        if (byteArray == null || byteArray.length < 2) {
            return null;
        }
        String format = String.format("%d %d", new Object[]{Byte.valueOf(byteArray[0]), Byte.valueOf(byteArray[1])});
        if (format.equals("0 0")) {
            str = "Off";
        } else if (format.equals("0 1")) {
            str = "On";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown (");
            sb.append(format);
            sb.append(")");
            str = sb.toString();
        }
        return str;
    }

    @Nullable
    public String getKeystoneDirectionDescription() {
        return getIndexedDescription(OlympusImageProcessingMakernoteDirectory.TagKeystoneDirection, "Vertical", "Horizontal");
    }
}
