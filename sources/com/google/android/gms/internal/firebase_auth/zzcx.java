package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "SendVerificationCodeAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzcx extends AbstractSafeParcelable {
    public static final Creator<zzcx> CREATOR = new zzda();
    @Field(getter = "getSendVerificationCodeRequest", mo15127id = 1)
    private final zzfk zza;

    @Constructor
    public zzcx(@Param(mo15130id = 1) zzfk zzfk) {
        this.zza = zzfk;
    }

    public final zzfk zza() {
        return this.zza;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
