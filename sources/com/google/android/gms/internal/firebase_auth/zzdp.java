package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.firebase.auth.zzae;

@Class(creator = "StartMfaPhoneNumberSignInAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzdp extends AbstractSafeParcelable {
    public static final Creator<zzdp> CREATOR = new zzds();
    @Field(getter = "getPhoneMultiFactorInfo", mo15127id = 1)
    private zzae zza;
    @Field(getter = "getPendingCredential", mo15127id = 2)
    private final String zzb;
    @Field(getter = "getLocaleHeader", mo15127id = 3)
    @Nullable
    private final String zzc;
    @Field(getter = "getTimeoutInSeconds", mo15127id = 4)
    private final long zzd;
    @Field(getter = "getForceNewSmsVerificationSession", mo15127id = 5)
    private final boolean zze;
    @Field(getter = "getRequireSmsVerification", mo15127id = 6)
    private final boolean zzf;

    @Constructor
    public zzdp(@Param(mo15130id = 1) zzae zzae, @Param(mo15130id = 2) String str, @Param(mo15130id = 3) @Nullable String str2, @Param(mo15130id = 4) long j, @Param(mo15130id = 5) boolean z, @Param(mo15130id = 6) boolean z2) {
        this.zza = zzae;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = j;
        this.zze = z;
        this.zzf = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
