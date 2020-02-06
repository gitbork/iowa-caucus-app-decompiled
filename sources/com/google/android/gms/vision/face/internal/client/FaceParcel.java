package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@UsedByNative("wrapper.cc")
@Class(creator = "FaceParcelCreator")
public class FaceParcel extends AbstractSafeParcelable {
    public static final Creator<FaceParcel> CREATOR = new zzd();
    @Field(mo15127id = 3)
    public final float centerX;
    @Field(mo15127id = 4)
    public final float centerY;
    @Field(mo15127id = 6)
    public final float height;
    @Field(mo15127id = 2)

    /* renamed from: id */
    public final int f91id;
    @VersionField(mo15133id = 1)
    private final int versionCode;
    @Field(mo15127id = 5)
    public final float width;
    @Field(mo15127id = 10)
    public final float zzcg;
    @Field(mo15127id = 11)
    public final float zzch;
    @Field(mo15127id = 12)
    public final float zzci;
    @Field(mo15127id = 7)
    public final float zzdb;
    @Field(mo15127id = 8)
    public final float zzdc;
    @Field(mo15127id = 14)
    public final float zzdd;
    @Field(mo15127id = 9)
    public final LandmarkParcel[] zzde;
    @Field(mo15127id = 13)
    public final zza[] zzdf;

    @Constructor
    public FaceParcel(@Param(mo15130id = 1) int i, @Param(mo15130id = 2) int i2, @Param(mo15130id = 3) float f, @Param(mo15130id = 4) float f2, @Param(mo15130id = 5) float f3, @Param(mo15130id = 6) float f4, @Param(mo15130id = 7) float f5, @Param(mo15130id = 8) float f6, @Param(mo15130id = 14) float f7, @Param(mo15130id = 9) LandmarkParcel[] landmarkParcelArr, @Param(mo15130id = 10) float f8, @Param(mo15130id = 11) float f9, @Param(mo15130id = 12) float f10, @Param(mo15130id = 13) zza[] zzaArr) {
        this.versionCode = i;
        this.f91id = i2;
        this.centerX = f;
        this.centerY = f2;
        this.width = f3;
        this.height = f4;
        this.zzdb = f5;
        this.zzdc = f6;
        this.zzdd = f7;
        this.zzde = landmarkParcelArr;
        this.zzcg = f8;
        this.zzch = f9;
        this.zzci = f10;
        this.zzdf = zzaArr;
    }

    @UsedByNative("wrapper.cc")
    public FaceParcel(@Param(mo15130id = 1) int i, @Param(mo15130id = 2) int i2, @Param(mo15130id = 3) float f, @Param(mo15130id = 4) float f2, @Param(mo15130id = 5) float f3, @Param(mo15130id = 6) float f4, @Param(mo15130id = 7) float f5, @Param(mo15130id = 8) float f6, @Param(mo15130id = 9) LandmarkParcel[] landmarkParcelArr, @Param(mo15130id = 10) float f7, @Param(mo15130id = 11) float f8, @Param(mo15130id = 12) float f9) {
        this(i, i2, f, f2, f3, f4, f5, f6, 0.0f, landmarkParcelArr, f7, f8, f9, new zza[0]);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.f91id);
        SafeParcelWriter.writeFloat(parcel, 3, this.centerX);
        SafeParcelWriter.writeFloat(parcel, 4, this.centerY);
        SafeParcelWriter.writeFloat(parcel, 5, this.width);
        SafeParcelWriter.writeFloat(parcel, 6, this.height);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzdb);
        SafeParcelWriter.writeFloat(parcel, 8, this.zzdc);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzde, i, false);
        SafeParcelWriter.writeFloat(parcel, 10, this.zzcg);
        SafeParcelWriter.writeFloat(parcel, 11, this.zzch);
        SafeParcelWriter.writeFloat(parcel, 12, this.zzci);
        SafeParcelWriter.writeTypedArray(parcel, 13, this.zzdf, i, false);
        SafeParcelWriter.writeFloat(parcel, 14, this.zzdd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}