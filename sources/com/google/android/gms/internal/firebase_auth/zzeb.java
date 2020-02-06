package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.firebase.auth.zzg;

@Class(creator = "OnFailedIdpSignInAidlResponseCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzeb extends AbstractSafeParcelable {
    public static final Creator<zzeb> CREATOR = new zzee();
    @Field(getter = "getStatus", mo15127id = 1)
    private final Status zza;
    @Field(getter = "getDefaultOAuthCredential", mo15127id = 2)
    private final zzg zzb;
    @Field(getter = "getEmail", mo15127id = 3)
    private final String zzc;
    @Field(getter = "getTenantId", mo15127id = 4)
    private final String zzd;

    @Constructor
    public zzeb(@Param(mo15130id = 1) Status status, @Param(mo15130id = 2) zzg zzg, @Param(mo15130id = 3) String str, @Param(mo15130id = 4) @Nullable String str2) {
        this.zza = status;
        this.zzb = zzg;
        this.zzc = str;
        this.zzd = str2;
    }

    public final Status zza() {
        return this.zza;
    }

    public final zzg zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzd;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
