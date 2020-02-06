package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ViewProps;
import com.google.logging.type.LogSeverity;

class FontData {
    static final double DEFAULT_FONT_SIZE = 12.0d;
    private static final double DEFAULT_KERNING = 0.0d;
    private static final double DEFAULT_LETTER_SPACING = 0.0d;
    private static final double DEFAULT_WORD_SPACING = 0.0d;
    static final FontData Defaults = new FontData();
    private static final String FONT_DATA = "fontData";
    private static final String FONT_FEATURE_SETTINGS = "fontFeatureSettings";
    private static final String FONT_VARIANT_LIGATURES = "fontVariantLigatures";
    private static final String FONT_VARIATION_SETTINGS = "fontVariationSettings";
    private static final String KERNING = "kerning";
    private static final String LETTER_SPACING = "letterSpacing";
    private static final String TEXT_ANCHOR = "textAnchor";
    private static final String TEXT_DECORATION = "textDecoration";
    private static final String WORD_SPACING = "wordSpacing";
    int absoluteFontWeight;
    final ReadableMap fontData;
    final String fontFamily;
    final String fontFeatureSettings;
    final double fontSize;
    final FontStyle fontStyle;
    final FontVariantLigatures fontVariantLigatures;
    final String fontVariationSettings;
    FontWeight fontWeight;
    final double kerning;
    final double letterSpacing;
    final boolean manualKerning;
    final TextAnchor textAnchor;
    private final TextDecoration textDecoration;
    final double wordSpacing;

    static class AbsoluteFontWeight {
        private static final FontWeight[] WEIGHTS = {FontWeight.w100, FontWeight.w100, FontWeight.w200, FontWeight.w300, FontWeight.Normal, FontWeight.w500, FontWeight.w600, FontWeight.Bold, FontWeight.w800, FontWeight.w900, FontWeight.w900};
        private static final int[] absoluteFontWeights = {400, 700, 100, LogSeverity.INFO_VALUE, 300, 400, 500, LogSeverity.CRITICAL_VALUE, 700, 800, 900};
        static final int normal = 400;

        private static int bolder(int i) {
            if (i < 350) {
                return 400;
            }
            if (i < 550) {
                return 700;
            }
            if (i < 900) {
                return 900;
            }
            return i;
        }

        private static int lighter(int i) {
            if (i < 100) {
                return i;
            }
            if (i < 550) {
                return 100;
            }
            return i < 750 ? 400 : 700;
        }

        AbsoluteFontWeight() {
        }

        static FontWeight nearestFontWeight(int i) {
            return WEIGHTS[Math.round(((float) i) / 100.0f)];
        }

        static int from(FontWeight fontWeight, FontData fontData) {
            if (fontWeight == FontWeight.Bolder) {
                return bolder(fontData.absoluteFontWeight);
            }
            if (fontWeight == FontWeight.Lighter) {
                return lighter(fontData.absoluteFontWeight);
            }
            return absoluteFontWeights[fontWeight.ordinal()];
        }
    }

    private FontData() {
        this.fontData = null;
        String str = "";
        this.fontFamily = str;
        this.fontStyle = FontStyle.normal;
        this.fontWeight = FontWeight.Normal;
        this.absoluteFontWeight = 400;
        this.fontFeatureSettings = str;
        this.fontVariationSettings = str;
        this.fontVariantLigatures = FontVariantLigatures.normal;
        this.textAnchor = TextAnchor.start;
        this.textDecoration = TextDecoration.None;
        this.manualKerning = false;
        this.kerning = 0.0d;
        this.fontSize = DEFAULT_FONT_SIZE;
        this.wordSpacing = 0.0d;
        this.letterSpacing = 0.0d;
    }

    private double toAbsolute(ReadableMap readableMap, String str, double d, double d2, double d3) {
        if (readableMap.getType(str) == ReadableType.Number) {
            return readableMap.getDouble(str);
        }
        return PropHelper.fromRelative(readableMap.getString(str), d3, d, d2);
    }

    private void setInheritedWeight(FontData fontData2) {
        this.absoluteFontWeight = fontData2.absoluteFontWeight;
        this.fontWeight = fontData2.fontWeight;
    }

    private void handleNumericWeight(FontData fontData2, double d) {
        long round = Math.round(d);
        if (round < 1 || round > 1000) {
            setInheritedWeight(fontData2);
            return;
        }
        this.absoluteFontWeight = (int) round;
        this.fontWeight = AbsoluteFontWeight.nearestFontWeight(this.absoluteFontWeight);
    }

    FontData(ReadableMap readableMap, FontData fontData2, double d) {
        double d2 = fontData2.fontSize;
        if (readableMap.hasKey(ViewProps.FONT_SIZE)) {
            this.fontSize = toAbsolute(readableMap, ViewProps.FONT_SIZE, 1.0d, d2, d2);
        } else {
            this.fontSize = d2;
        }
        String str = ViewProps.FONT_WEIGHT;
        if (!readableMap.hasKey(str)) {
            setInheritedWeight(fontData2);
        } else if (readableMap.getType(str) == ReadableType.Number) {
            handleNumericWeight(fontData2, readableMap.getDouble(str));
        } else {
            String string = readableMap.getString(str);
            if (FontWeight.hasEnum(string)) {
                this.absoluteFontWeight = AbsoluteFontWeight.from(FontWeight.get(string), fontData2);
                this.fontWeight = AbsoluteFontWeight.nearestFontWeight(this.absoluteFontWeight);
            } else if (string != null) {
                handleNumericWeight(fontData2, Double.parseDouble(string));
            } else {
                setInheritedWeight(fontData2);
            }
        }
        String str2 = FONT_DATA;
        this.fontData = readableMap.hasKey(str2) ? readableMap.getMap(str2) : fontData2.fontData;
        String str3 = ViewProps.FONT_FAMILY;
        this.fontFamily = readableMap.hasKey(str3) ? readableMap.getString(str3) : fontData2.fontFamily;
        String str4 = ViewProps.FONT_STYLE;
        this.fontStyle = readableMap.hasKey(str4) ? FontStyle.valueOf(readableMap.getString(str4)) : fontData2.fontStyle;
        String str5 = FONT_FEATURE_SETTINGS;
        this.fontFeatureSettings = readableMap.hasKey(str5) ? readableMap.getString(str5) : fontData2.fontFeatureSettings;
        String str6 = FONT_VARIATION_SETTINGS;
        this.fontVariationSettings = readableMap.hasKey(str6) ? readableMap.getString(str6) : fontData2.fontVariationSettings;
        String str7 = FONT_VARIANT_LIGATURES;
        this.fontVariantLigatures = readableMap.hasKey(str7) ? FontVariantLigatures.valueOf(readableMap.getString(str7)) : fontData2.fontVariantLigatures;
        String str8 = TEXT_ANCHOR;
        this.textAnchor = readableMap.hasKey(str8) ? TextAnchor.valueOf(readableMap.getString(str8)) : fontData2.textAnchor;
        String str9 = TEXT_DECORATION;
        this.textDecoration = readableMap.hasKey(str9) ? TextDecoration.getEnum(readableMap.getString(str9)) : fontData2.textDecoration;
        boolean hasKey = readableMap.hasKey(KERNING);
        this.manualKerning = hasKey || fontData2.manualKerning;
        this.kerning = hasKey ? toAbsolute(readableMap, KERNING, d, this.fontSize, 0.0d) : fontData2.kerning;
        this.wordSpacing = readableMap.hasKey(WORD_SPACING) ? toAbsolute(readableMap, WORD_SPACING, d, this.fontSize, 0.0d) : fontData2.wordSpacing;
        this.letterSpacing = readableMap.hasKey("letterSpacing") ? toAbsolute(readableMap, "letterSpacing", d, this.fontSize, 0.0d) : fontData2.letterSpacing;
    }
}
