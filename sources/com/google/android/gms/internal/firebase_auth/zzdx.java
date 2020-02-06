package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.firebase.auth.UserProfileChangeRequest;

@Class(creator = "UpdateProfileAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzdx extends AbstractSafeParcelable {
    public static final Creator<zzdx> CREATOR = new zzea();
    @Field(getter = "getUserProfileChangeRequest", mo15127id = 1)
    private final UserProfileChangeRequest zza;
    @Field(getter = "getCachedState", mo15127id = 2)
    private final String zzb;

    @Constructor
    public zzdx(@Param(mo15130id = 1) UserProfileChangeRequest userProfileChangeRequest, @Param(mo15130id = 2) String str) {
        this.zza = userProfileChangeRequest;
        this.zzb = str;
    }

    public final UserProfileChangeRequest zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
