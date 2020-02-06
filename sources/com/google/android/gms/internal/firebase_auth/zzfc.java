package com.google.android.gms.internal.firebase_auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "ProviderUserInfoCreator")
@Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzfc extends AbstractSafeParcelable {
    public static final Creator<zzfc> CREATOR = new zzfb();
    @Field(getter = "getFederatedId", mo15127id = 2)
    private String zza;
    @Field(getter = "getDisplayName", mo15127id = 3)
    private String zzb;
    @Field(getter = "getPhotoUrl", mo15127id = 4)
    private String zzc;
    @Field(getter = "getProviderId", mo15127id = 5)
    private String zzd;
    @Field(getter = "getRawUserInfo", mo15127id = 6)
    private String zze;
    @Field(getter = "getPhoneNumber", mo15127id = 7)
    private String zzf;
    @Field(getter = "getEmail", mo15127id = 8)
    private String zzg;

    public zzfc() {
    }

    @Constructor
    zzfc(@Param(mo15130id = 2) String str, @Param(mo15130id = 3) String str2, @Param(mo15130id = 4) String str3, @Param(mo15130id = 5) String str4, @Param(mo15130id = 6) String str5, @Param(mo15130id = 7) String str6, @Param(mo15130id = 8) String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = str6;
        this.zzg = str7;
    }

    public final String zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    @Nullable
    public final Uri zzc() {
        if (!TextUtils.isEmpty(this.zzc)) {
            return Uri.parse(this.zzc);
        }
        return null;
    }

    public final String zzd() {
        return this.zzd;
    }

    public final String zze() {
        return this.zzf;
    }

    public final void zza(String str) {
        this.zze = str;
    }

    @Nullable
    public final String zzf() {
        return this.zze;
    }

    @Nullable
    public final String zzg() {
        return this.zzg;
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
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
