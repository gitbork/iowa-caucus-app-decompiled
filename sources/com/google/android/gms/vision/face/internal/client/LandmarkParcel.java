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
@Class(creator = "LandmarkParcelCreator")
public final class LandmarkParcel extends AbstractSafeParcelable {
    public static final Creator<LandmarkParcel> CREATOR = new zzm();
    @Field(mo15127id = 4)
    public final int type;
    @VersionField(mo15133id = 1)
    private final int versionCode;
    @Field(mo15127id = 2)

    /* renamed from: x */
    public final float f92x;
    @Field(mo15127id = 3)

    /* renamed from: y */
    public final float f93y;

    @UsedByNative("wrapper.cc")
    @Constructor
    public LandmarkParcel(@Param(mo15130id = 1) int i, @Param(mo15130id = 2) float f, @Param(mo15130id = 3) float f2, @Param(mo15130id = 4) int i2) {
        this.versionCode = i;
        this.f92x = f;
        this.f93y = f2;
        this.type = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeFloat(parcel, 2, this.f92x);
        SafeParcelWriter.writeFloat(parcel, 3, this.f93y);
        SafeParcelWriter.writeInt(parcel, 4, this.type);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
