package com.drew.metadata.exif.makernotes;

import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class OlympusFocusInfoMakernoteDescriptor extends TagDescriptor<OlympusFocusInfoMakernoteDirectory> {
    public OlympusFocusInfoMakernoteDescriptor(@NotNull OlympusFocusInfoMakernoteDirectory olympusFocusInfoMakernoteDirectory) {
        super(olympusFocusInfoMakernoteDirectory);
    }

    @Nullable
    public String getDescription(int i) {
        if (i == 0) {
            return getFocusInfoVersionDescription();
        }
        if (i == 521) {
            return getAutoFocusDescription();
        }
        if (i == 773) {
            return getFocusDistanceDescription();
        }
        if (i == 776) {
            return getAfPointDescription();
        }
        if (i == 4609) {
            return getExternalFlashDescription();
        }
        if (i == 5376) {
            return getSensorTemperatureDescription();
        }
        if (i == 5632) {
            return getImageStabilizationDescription();
        }
        if (i == 4612) {
            return getExternalFlashBounceDescription();
        }
        if (i == 4613) {
            return getExternalFlashZoomDescription();
        }
        if (i == 4617) {
            return getManualFlashDescription();
        }
        if (i != 4618) {
            return super.getDescription(i);
        }
        return getMacroLedDescription();
    }

    @Nullable
    public String getFocusInfoVersionDescription() {
        return getVersionBytesDescription(0, 4);
    }

    @Nullable
    public String getAutoFocusDescription() {
        return getIndexedDescription(521, "Off", "On");
    }

    @Nullable
    public String getFocusDistanceDescription() {
        Rational rational = ((OlympusFocusInfoMakernoteDirectory) this._directory).getRational(773);
        String str = "inf";
        if (rational == null || rational.getNumerator() == 4294967295L || rational.getNumerator() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        double numerator = (double) rational.getNumerator();
        Double.isNaN(numerator);
        sb.append(numerator / 1000.0d);
        sb.append(" m");
        return sb.toString();
    }

    @Nullable
    public String getAfPointDescription() {
        Integer integer = ((OlympusFocusInfoMakernoteDirectory) this._directory).getInteger(OlympusFocusInfoMakernoteDirectory.TagAfPoint);
        if (integer == null) {
            return null;
        }
        return integer.toString();
    }

    @Nullable
    public String getExternalFlashDescription() {
        int[] intArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getIntArray(4609);
        if (intArray == null || intArray.length < 2) {
            return null;
        }
        String format = String.format("%d %d", new Object[]{Short.valueOf((short) intArray[0]), Short.valueOf((short) intArray[1])});
        if (format.equals("0 0")) {
            return "Off";
        }
        if (format.equals("1 0")) {
            return "On";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown (");
        sb.append(format);
        sb.append(")");
        return sb.toString();
    }

    @Nullable
    public String getExternalFlashBounceDescription() {
        return getIndexedDescription(OlympusFocusInfoMakernoteDirectory.TagExternalFlashBounce, "Bounce or Off", "Direct");
    }

    @Nullable
    public String getExternalFlashZoomDescription() {
        int[] intArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getIntArray(OlympusFocusInfoMakernoteDirectory.TagExternalFlashZoom);
        if (intArray == null) {
            Integer integer = ((OlympusFocusInfoMakernoteDirectory) this._directory).getInteger(OlympusFocusInfoMakernoteDirectory.TagExternalFlashZoom);
            if (integer == null) {
                return null;
            }
            intArray = new int[]{integer.intValue()};
        }
        if (intArray.length == 0) {
            return null;
        }
        String str = "%d";
        String format = String.format(str, new Object[]{Short.valueOf((short) intArray[0])});
        if (intArray.length > 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(format);
            sb.append(" ");
            sb.append(String.format(str, new Object[]{Short.valueOf((short) intArray[1])}));
            format = sb.toString();
        }
        String str2 = "Off";
        if (format.equals("0")) {
            return str2;
        }
        String str3 = "On";
        if (format.equals("1")) {
            return str3;
        }
        if (format.equals("0 0")) {
            return str2;
        }
        if (format.equals("1 0")) {
            return str3;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Unknown (");
        sb2.append(format);
        sb2.append(")");
        return sb2.toString();
    }

    @Nullable
    public String getManualFlashDescription() {
        int[] intArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getIntArray(OlympusFocusInfoMakernoteDirectory.TagManualFlash);
        if (intArray == null) {
            return null;
        }
        if (((short) intArray[0]) == 0) {
            return "Off";
        }
        if (((short) intArray[1]) == 1) {
            return "Full";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("On (1/");
        sb.append((short) intArray[1]);
        sb.append(" strength)");
        return sb.toString();
    }

    @Nullable
    public String getMacroLedDescription() {
        return getIndexedDescription(OlympusFocusInfoMakernoteDirectory.TagMacroLed, "Off", "On");
    }

    @Nullable
    public String getSensorTemperatureDescription() {
        return ((OlympusFocusInfoMakernoteDirectory) this._directory).getString(OlympusFocusInfoMakernoteDirectory.TagSensorTemperature);
    }

    @Nullable
    public String getImageStabilizationDescription() {
        byte[] byteArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getByteArray(OlympusFocusInfoMakernoteDirectory.TagImageStabilization);
        if (byteArray == null) {
            return null;
        }
        if ((byteArray[0] | byteArray[1] | byteArray[2] | byteArray[3]) == 0) {
            return "Off";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("On, ");
        sb.append((byteArray[43] & 1) > 0 ? "Mode 1" : "Mode 2");
        return sb.toString();
    }
}
