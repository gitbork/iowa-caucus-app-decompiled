package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.firebase_auth.zzp.zzq;
import com.google.android.gms.internal.firebase_auth.zzp.zzq.zza;
import com.google.firebase.auth.api.internal.zzfk;

@Class(creator = "VerifyAssertionRequestCreator")
@Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzfr extends AbstractSafeParcelable implements zzfk<zzq> {
    public static final Creator<zzfr> CREATOR = new zzfu();
    @Field(getter = "getRequestUri", mo15127id = 2)
    private String zza;
    @Field(getter = "getCurrentIdToken", mo15127id = 3)
    private String zzb;
    @Field(getter = "getIdToken", mo15127id = 4)
    private String zzc;
    @Field(getter = "getAccessToken", mo15127id = 5)
    private String zzd;
    @Field(getter = "getProviderId", mo15127id = 6)
    private String zze;
    @Field(getter = "getEmail", mo15127id = 7)
    @Nullable
    private String zzf;
    @Field(getter = "getPostBody", mo15127id = 8)
    private String zzg;
    @Field(getter = "getOauthTokenSecret", mo15127id = 9)
    private String zzh;
    @Field(getter = "getReturnSecureToken", mo15127id = 10)
    private boolean zzi;
    @Field(getter = "getAutoCreate", mo15127id = 11)
    private boolean zzj;
    @Field(getter = "getAuthCode", mo15127id = 12)
    private String zzk;
    @Field(getter = "getSessionId", mo15127id = 13)
    private String zzl;
    @Field(getter = "getIdpResponseUrl", mo15127id = 14)
    private String zzm;
    @Field(getter = "getTenantId", mo15127id = 15)
    private String zzn;
    @Field(getter = "getReturnIdpCredential", mo15127id = 16)
    private boolean zzo;
    @Field(getter = "getPendingToken", mo15127id = 17)
    @Nullable
    private String zzp;

    public zzfr() {
        this.zzi = true;
        this.zzj = true;
    }

    @Constructor
    zzfr(@Param(mo15130id = 2) String str, @Param(mo15130id = 3) String str2, @Param(mo15130id = 4) String str3, @Param(mo15130id = 5) String str4, @Param(mo15130id = 6) String str5, @Param(mo15130id = 7) String str6, @Param(mo15130id = 8) String str7, @Param(mo15130id = 9) String str8, @Param(mo15130id = 10) boolean z, @Param(mo15130id = 11) boolean z2, @Param(mo15130id = 12) String str9, @Param(mo15130id = 13) String str10, @Param(mo15130id = 14) String str11, @Param(mo15130id = 15) String str12, @Param(mo15130id = 16) boolean z3, @Param(mo15130id = 17) String str13) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = str6;
        this.zzg = str7;
        this.zzh = str8;
        this.zzi = z;
        this.zzj = z2;
        this.zzk = str9;
        this.zzl = str10;
        this.zzm = str11;
        this.zzn = str12;
        this.zzo = z3;
        this.zzp = str13;
    }

    public zzfr(@Nullable String str, @Nullable String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
        this.zza = "http://localhost";
        this.zzc = str;
        this.zzd = str2;
        this.zzh = str5;
        this.zzk = str6;
        this.zzn = str7;
        this.zzp = str8;
        this.zzi = true;
        if (!TextUtils.isEmpty(this.zzc) || !TextUtils.isEmpty(this.zzd) || !TextUtils.isEmpty(this.zzk)) {
            this.zze = Preconditions.checkNotEmpty(str3);
            this.zzf = null;
            StringBuilder sb = new StringBuilder();
            String str9 = "&";
            if (!TextUtils.isEmpty(this.zzc)) {
                sb.append("id_token=");
                sb.append(this.zzc);
                sb.append(str9);
            }
            if (!TextUtils.isEmpty(this.zzd)) {
                sb.append("access_token=");
                sb.append(this.zzd);
                sb.append(str9);
            }
            if (!TextUtils.isEmpty(this.zzf)) {
                sb.append("identifier=");
                sb.append(this.zzf);
                sb.append(str9);
            }
            if (!TextUtils.isEmpty(this.zzh)) {
                sb.append("oauth_token_secret=");
                sb.append(this.zzh);
                sb.append(str9);
            }
            if (!TextUtils.isEmpty(this.zzk)) {
                sb.append("code=");
                sb.append(this.zzk);
                sb.append(str9);
            }
            sb.append("providerId=");
            sb.append(this.zze);
            this.zzg = sb.toString();
            this.zzj = true;
            return;
        }
        throw new IllegalArgumentException("idToken, accessToken and authCode cannot all be null");
    }

    public final zzfr zza(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzfr zza(boolean z) {
        this.zzj = false;
        return this;
    }

    public final zzfr zzb(@Nullable String str) {
        this.zzn = str;
        return this;
    }

    public final zzfr zzb(boolean z) {
        this.zzi = true;
        return this;
    }

    public final zzfr zzc(boolean z) {
        this.zzo = true;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzh, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzj);
        SafeParcelWriter.writeString(parcel, 12, this.zzk, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzl, false);
        SafeParcelWriter.writeString(parcel, 14, this.zzm, false);
        SafeParcelWriter.writeString(parcel, 15, this.zzn, false);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzo);
        SafeParcelWriter.writeString(parcel, 17, this.zzp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ zzjg zza() {
        zza zzc2 = zzq.zza().zza(this.zzi).zzc(this.zzj);
        String str = this.zzb;
        if (str != null) {
            zzc2.zzd(str);
        }
        String str2 = this.zza;
        if (str2 != null) {
            zzc2.zza(str2);
        }
        String str3 = this.zzg;
        if (str3 != null) {
            zzc2.zzb(str3);
        }
        String str4 = this.zzn;
        if (str4 != null) {
            zzc2.zze(str4);
        }
        String str5 = this.zzp;
        if (str5 != null) {
            zzc2.zzf(str5);
        }
        if (!TextUtils.isEmpty(this.zzl)) {
            zzc2.zzc(this.zzl);
        }
        if (!TextUtils.isEmpty(this.zzm)) {
            zzc2.zza(this.zzm);
        }
        return (zzq) ((zzhx) zzc2.zzb(this.zzo).zzf());
    }
}
