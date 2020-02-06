package com.google.android.gms.internal.firebase_auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.firebase.auth.zzg;
import java.util.List;

@Class(creator = "GetAccountInfoUserCreator")
@Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzes extends AbstractSafeParcelable {
    public static final Creator<zzes> CREATOR = new zzer();
    @Field(getter = "getLocalId", mo15127id = 2)
    private String zza;
    @Field(getter = "getEmail", mo15127id = 3)
    private String zzb;
    @Field(getter = "isEmailVerified", mo15127id = 4)
    private boolean zzc;
    @Field(getter = "getDisplayName", mo15127id = 5)
    private String zzd;
    @Field(getter = "getPhotoUrl", mo15127id = 6)
    private String zze;
    @Field(getter = "getProviderInfoList", mo15127id = 7)
    private zzfe zzf;
    @Field(getter = "getPassword", mo15127id = 8)
    private String zzg;
    @Field(getter = "getPhoneNumber", mo15127id = 9)
    private String zzh;
    @Field(getter = "getCreationTimestamp", mo15127id = 10)
    private long zzi;
    @Field(getter = "getLastSignInTimestamp", mo15127id = 11)
    private long zzj;
    @Field(getter = "isNewUser", mo15127id = 12)
    private boolean zzk;
    @Field(getter = "getDefaultOAuthCredential", mo15127id = 13)
    private zzg zzl;
    @Field(getter = "getMfaInfoList", mo15127id = 14)
    private List<zzfa> zzm;

    public zzes() {
        this.zzf = new zzfe();
    }

    @Constructor
    public zzes(@Param(mo15130id = 2) String str, @Param(mo15130id = 3) String str2, @Param(mo15130id = 4) boolean z, @Param(mo15130id = 5) String str3, @Param(mo15130id = 6) String str4, @Param(mo15130id = 7) zzfe zzfe, @Param(mo15130id = 8) String str5, @Param(mo15130id = 9) String str6, @Param(mo15130id = 10) long j, @Param(mo15130id = 11) long j2, @Param(mo15130id = 12) boolean z2, @Param(mo15130id = 13) zzg zzg2, @Param(mo15130id = 14) List<zzfa> list) {
        zzfe zzfe2;
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = str3;
        this.zze = str4;
        if (zzfe == null) {
            zzfe2 = new zzfe();
        } else {
            zzfe2 = zzfe.zza(zzfe);
        }
        this.zzf = zzfe2;
        this.zzg = str5;
        this.zzh = str6;
        this.zzi = j;
        this.zzj = j2;
        this.zzk = z2;
        this.zzl = zzg2;
        if (list == null) {
            list = zzaz.zza();
        }
        this.zzm = list;
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }

    public final boolean zzb() {
        return this.zzc;
    }

    @NonNull
    public final String zzc() {
        return this.zza;
    }

    @Nullable
    public final String zzd() {
        return this.zzd;
    }

    @Nullable
    public final Uri zze() {
        if (!TextUtils.isEmpty(this.zze)) {
            return Uri.parse(this.zze);
        }
        return null;
    }

    @Nullable
    public final String zzf() {
        return this.zzh;
    }

    public final long zzg() {
        return this.zzi;
    }

    public final long zzh() {
        return this.zzj;
    }

    public final boolean zzi() {
        return this.zzk;
    }

    @NonNull
    public final zzes zza(@Nullable String str) {
        this.zzb = str;
        return this;
    }

    @NonNull
    public final zzes zzb(@Nullable String str) {
        this.zzd = str;
        return this;
    }

    @NonNull
    public final zzes zzc(@Nullable String str) {
        this.zze = str;
        return this;
    }

    @NonNull
    public final zzes zzd(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzg = str;
        return this;
    }

    @NonNull
    public final zzes zza(List<zzfc> list) {
        Preconditions.checkNotNull(list);
        this.zzf = new zzfe();
        this.zzf.zza().addAll(list);
        return this;
    }

    public final zzes zza(boolean z) {
        this.zzk = z;
        return this;
    }

    @NonNull
    public final List<zzfc> zzj() {
        return this.zzf.zza();
    }

    public final zzfe zzk() {
        return this.zzf;
    }

    @Nullable
    public final zzg zzl() {
        return this.zzl;
    }

    @NonNull
    public final zzes zza(zzg zzg2) {
        this.zzl = zzg2;
        return this;
    }

    @NonNull
    public final List<zzfa> zzm() {
        return this.zzm;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzc);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzf, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzh, false);
        SafeParcelWriter.writeLong(parcel, 10, this.zzi);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzk);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzl, i, false);
        SafeParcelWriter.writeTypedList(parcel, 14, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
