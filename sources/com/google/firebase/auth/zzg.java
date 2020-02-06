package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.internal.firebase_auth.zzfr;

@Class(creator = "DefaultOAuthCredentialCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public class zzg extends OAuthCredential {
    public static final Creator<zzg> CREATOR = new zzi();
    @Field(getter = "getProvider", mo15127id = 1)
    @Nullable
    private final String zza;
    @Field(getter = "getIdToken", mo15127id = 2)
    @Nullable
    private final String zzb;
    @Field(getter = "getAccessToken", mo15127id = 3)
    @Nullable
    private final String zzc;
    @Field(getter = "getWebSignInCredential", mo15127id = 4)
    @Nullable
    private final zzfr zzd;
    @Field(getter = "getPendingToken", mo15127id = 5)
    @Nullable
    private final String zze;
    @Field(getter = "getSecret", mo15127id = 6)
    @Nullable
    private final String zzf;

    @Constructor
    zzg(@Param(mo15130id = 1) @NonNull String str, @Param(mo15130id = 2) @Nullable String str2, @Param(mo15130id = 3) @Nullable String str3, @Param(mo15130id = 4) @Nullable zzfr zzfr, @Param(mo15130id = 5) @Nullable String str4, @Param(mo15130id = 6) @Nullable String str5) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzfr;
        this.zze = str4;
        this.zzf = str5;
    }

    public static zzg zza(String str, String str2, String str3) {
        return zza(str, str2, str3, null, null);
    }

    public static zzg zza(String str, String str2, String str3, @Nullable String str4, @Nullable String str5) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            zzg zzg = new zzg(str, str2, str3, null, str4, str5);
            return zzg;
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    public static zzg zza(@NonNull zzfr zzfr) {
        Preconditions.checkNotNull(zzfr, "Must specify a non-null webSignInCredential");
        zzg zzg = new zzg(null, null, null, zzfr, null, null);
        return zzg;
    }

    public String getProvider() {
        return this.zza;
    }

    public String getSignInMethod() {
        return this.zza;
    }

    @Nullable
    public String getIdToken() {
        return this.zzb;
    }

    @Nullable
    public String getAccessToken() {
        return this.zzc;
    }

    @Nullable
    public String getSecret() {
        return this.zzf;
    }

    public static zzfr zza(@NonNull zzg zzg, @Nullable String str) {
        Preconditions.checkNotNull(zzg);
        zzfr zzfr = zzg.zzd;
        if (zzfr != null) {
            return zzfr;
        }
        zzfr zzfr2 = new zzfr(zzg.getIdToken(), zzg.getAccessToken(), zzg.getProvider(), null, zzg.getSecret(), null, str, zzg.zze);
        return zzfr2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProvider(), false);
        SafeParcelWriter.writeString(parcel, 2, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 3, getAccessToken(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, getSecret(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
