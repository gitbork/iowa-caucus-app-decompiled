package com.drew.metadata.mp4.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;

public class Mp4VideoDescriptor extends TagDescriptor<Mp4VideoDirectory> {
    public Mp4VideoDescriptor(@NotNull Mp4VideoDirectory mp4VideoDirectory) {
        super(mp4VideoDirectory);
    }

    public String getDescription(int i) {
        if (i == 104 || i == 105) {
            return getPixelDescription(i);
        }
        if (i == 109) {
            return getDepthDescription();
        }
        if (i == 111) {
            return getGraphicsModeDescription();
        }
        if (i != 113) {
            return super.getDescription(i);
        }
        return getColorTableDescription();
    }

    private String getPixelDescription(int i) {
        String string = ((Mp4VideoDirectory) this._directory).getString(i);
        if (string == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" pixels");
        return sb.toString();
    }

    private String getDepthDescription() {
        Integer integer = ((Mp4VideoDirectory) this._directory).getInteger(109);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 34 || intValue == 36 || intValue == 40) {
            StringBuilder sb = new StringBuilder();
            sb.append(integer.intValue() - 32);
            sb.append("-bit grayscale");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Unknown (");
        sb2.append(integer);
        sb2.append(")");
        return sb2.toString();
    }

    private String getColorTableDescription() {
        Integer integer = ((Mp4VideoDirectory) this._directory).getInteger(113);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == -1) {
            Integer integer2 = ((Mp4VideoDirectory) this._directory).getInteger(109);
            String str = "None";
            return (integer2 != null && integer2.intValue() < 16) ? "Default" : str;
        } else if (intValue == 0) {
            return "Color table within file";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown (");
            sb.append(integer);
            sb.append(")");
            return sb.toString();
        }
    }

    private String getGraphicsModeDescription() {
        Integer integer = ((Mp4VideoDirectory) this._directory).getInteger(111);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 0) {
            return "Copy";
        }
        if (intValue == 32) {
            return "Blend";
        }
        if (intValue == 36) {
            return "Transparent";
        }
        if (intValue == 64) {
            return "Dither copy";
        }
        switch (intValue) {
            case 256:
                return "Straight alpha";
            case 257:
                return "Premul white alpha";
            case 258:
                return "Premul black alpha";
            case 259:
                return "Composition (dither copy)";
            case 260:
                return "Straight alpha blend";
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown (");
                sb.append(integer);
                sb.append(")");
                return sb.toString();
        }
    }
}
