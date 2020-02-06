package com.drew.metadata.mov.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.mov.QuickTimeDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;

public class QuickTimeVideoDescriptor extends QuickTimeDescriptor {
    public QuickTimeVideoDescriptor(@NotNull QuickTimeVideoDirectory quickTimeVideoDirectory) {
        super(quickTimeVideoDirectory);
    }

    public String getDescription(int i) {
        if (i == 4 || i == 5) {
            return getPixelDescription(i);
        }
        if (i == 9) {
            return getDepthDescription(i);
        }
        if (i == 11) {
            return getGraphicsModeDescription();
        }
        if (i != 13) {
            return super.getDescription(i);
        }
        return getColorTableDescription(i);
    }

    private String getPixelDescription(int i) {
        String string = ((QuickTimeDirectory) this._directory).getString(i);
        if (string == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" pixels");
        return sb.toString();
    }

    private String getDepthDescription(int i) {
        Integer integer = ((QuickTimeDirectory) this._directory).getInteger(i);
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

    private String getColorTableDescription(int i) {
        Integer integer = ((QuickTimeDirectory) this._directory).getInteger(i);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == -1) {
            return ((QuickTimeDirectory) this._directory).getInteger(9).intValue() < 16 ? "Default" : "None";
        }
        if (intValue == 0) {
            return "Color table within file";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown (");
        sb.append(integer);
        sb.append(")");
        return sb.toString();
    }

    private String getGraphicsModeDescription() {
        Integer integer = ((QuickTimeDirectory) this._directory).getInteger(11);
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
