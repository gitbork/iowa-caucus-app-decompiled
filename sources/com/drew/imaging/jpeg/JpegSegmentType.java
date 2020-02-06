package com.drew.imaging.jpeg;

import com.drew.lang.annotations.Nullable;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import java.util.ArrayList;
import java.util.Collection;

public enum JpegSegmentType {
    APP0(-32, true),
    APP1(-31, true),
    APP2(-30, true),
    APP3(-29, true),
    APP4(-28, true),
    APP5(-27, true),
    APP6(-26, true),
    APP7(-25, true),
    APP8(-24, true),
    APP9(-23, true),
    APPA(-22, true),
    APPB(-21, true),
    APPC(-20, true),
    APPD(-19, true),
    APPE(-18, true),
    APPF(ByteSourceJsonBootstrapper.UTF8_BOM_1, true),
    SOI(-40, false),
    DQT(-37, false),
    DNL(-36, false),
    DRI(-35, false),
    DHP(-34, false),
    EXP(-33, false),
    DHT(-60, false),
    DAC(-52, false),
    SOF0(-64, true),
    SOF1(-63, true),
    SOF2(-62, true),
    SOF3(-61, true),
    SOF5(-59, true),
    SOF6(-58, true),
    SOF7(-57, true),
    JPG(-56, true),
    SOF9(-55, true),
    SOF10(-54, true),
    SOF11(-53, true),
    SOF13(-51, true),
    SOF14(-50, true),
    SOF15(-49, true),
    COM(-2, true);
    
    public static final Collection<JpegSegmentType> canContainMetadataTypes = null;
    public final byte byteValue;
    public final boolean canContainMetadata;

    static {
        int i;
        JpegSegmentType[] jpegSegmentTypeArr;
        ArrayList arrayList = new ArrayList();
        for (JpegSegmentType jpegSegmentType : (JpegSegmentType[]) JpegSegmentType.class.getEnumConstants()) {
            if (jpegSegmentType.canContainMetadata) {
                arrayList.add(jpegSegmentType);
            }
        }
        canContainMetadataTypes = arrayList;
    }

    private JpegSegmentType(byte b, boolean z) {
        this.byteValue = b;
        this.canContainMetadata = z;
    }

    @Nullable
    public static JpegSegmentType fromByte(byte b) {
        JpegSegmentType[] jpegSegmentTypeArr;
        for (JpegSegmentType jpegSegmentType : (JpegSegmentType[]) JpegSegmentType.class.getEnumConstants()) {
            if (jpegSegmentType.byteValue == b) {
                return jpegSegmentType;
            }
        }
        return null;
    }
}
