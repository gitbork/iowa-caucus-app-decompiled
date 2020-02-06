package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.firebase.auth.PhoneAuthCredential;

@Class(creator = "FinalizeMfaEnrollmentAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzcd extends AbstractSafeParcelable {
    public static final Creator<zzcd> CREATOR = new zzcg();
    @Field(getter = "getPhoneAuthCredential", mo15127id = 1)
    private final PhoneAuthCredential zza;
    @Field(getter = "getCachedTokenState", mo15127id = 2)
    private final String zzb;
    @Field(getter = "getDisplayName", mo15127id = 3)
    private final String zzc;

    @Constructor
    public zzcd(@Param(mo15130id = 1) PhoneAuthCredential phoneAuthCredential, @Param(mo15130id = 2) String str, @Param(mo15130id = 3) String str2) {
        this.zza = phoneAuthCredential;
        this.zzb = str;
        this.zzc = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
