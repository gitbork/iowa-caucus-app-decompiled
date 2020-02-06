package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;

@Class(creator = "AppMetadataCreator")
@Reserved({1, 20})
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
public final class zzn extends AbstractSafeParcelable {
    public static final Creator<zzn> CREATOR = new zzm();
    @Field(mo15127id = 2)
    public final String zza;
    @Field(mo15127id = 3)
    public final String zzb;
    @Field(mo15127id = 4)
    public final String zzc;
    @Field(mo15127id = 5)
    public final String zzd;
    @Field(mo15127id = 6)
    public final long zze;
    @Field(mo15127id = 7)
    public final long zzf;
    @Field(mo15127id = 8)
    public final String zzg;
    @Field(defaultValue = "true", mo15127id = 9)
    public final boolean zzh;
    @Field(mo15127id = 10)
    public final boolean zzi;
    @Field(defaultValueUnchecked = "Integer.MIN_VALUE", mo15127id = 11)
    public final long zzj;
    @Field(mo15127id = 12)
    public final String zzk;
    @Field(mo15127id = 13)
    public final long zzl;
    @Field(mo15127id = 14)
    public final long zzm;
    @Field(mo15127id = 15)
    public final int zzn;
    @Field(defaultValue = "true", mo15127id = 16)
    public final boolean zzo;
    @Field(defaultValue = "true", mo15127id = 17)
    public final boolean zzp;
    @Field(mo15127id = 18)
    public final boolean zzq;
    @Field(mo15127id = 19)
    public final String zzr;
    @Field(mo15127id = 21)
    public final Boolean zzs;
    @Field(mo15127id = 22)
    public final long zzt;
    @Field(mo15127id = 23)
    public final List<String> zzu;

    zzn(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6, long j4, long j5, int i, boolean z3, boolean z4, boolean z5, String str7, Boolean bool, long j6, List<String> list) {
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = TextUtils.isEmpty(str2) ? null : str2;
        this.zzc = str3;
        this.zzj = j;
        this.zzd = str4;
        this.zze = j2;
        this.zzf = j3;
        this.zzg = str5;
        this.zzh = z;
        this.zzi = z2;
        this.zzk = str6;
        this.zzl = j4;
        this.zzm = j5;
        this.zzn = i;
        this.zzo = z3;
        this.zzp = z4;
        this.zzq = z5;
        this.zzr = str7;
        this.zzs = bool;
        this.zzt = j6;
        this.zzu = list;
    }

    @Constructor
    zzn(@Param(mo15130id = 2) String str, @Param(mo15130id = 3) String str2, @Param(mo15130id = 4) String str3, @Param(mo15130id = 5) String str4, @Param(mo15130id = 6) long j, @Param(mo15130id = 7) long j2, @Param(mo15130id = 8) String str5, @Param(mo15130id = 9) boolean z, @Param(mo15130id = 10) boolean z2, @Param(mo15130id = 11) long j3, @Param(mo15130id = 12) String str6, @Param(mo15130id = 13) long j4, @Param(mo15130id = 14) long j5, @Param(mo15130id = 15) int i, @Param(mo15130id = 16) boolean z3, @Param(mo15130id = 17) boolean z4, @Param(mo15130id = 18) boolean z5, @Param(mo15130id = 19) String str7, @Param(mo15130id = 21) Boolean bool, @Param(mo15130id = 22) long j6, @Param(mo15130id = 23) List<String> list) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzj = j3;
        this.zzd = str4;
        this.zze = j;
        this.zzf = j2;
        this.zzg = str5;
        this.zzh = z;
        this.zzi = z2;
        this.zzk = str6;
        this.zzl = j4;
        this.zzm = j5;
        this.zzn = i;
        this.zzo = z3;
        this.zzp = z4;
        this.zzq = z5;
        this.zzr = str7;
        this.zzs = bool;
        this.zzt = j6;
        this.zzu = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zze);
        SafeParcelWriter.writeLong(parcel, 7, this.zzf);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeString(parcel, 12, this.zzk, false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzl);
        SafeParcelWriter.writeLong(parcel, 14, this.zzm);
        SafeParcelWriter.writeInt(parcel, 15, this.zzn);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzo);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzp);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzq);
        SafeParcelWriter.writeString(parcel, 19, this.zzr, false);
        SafeParcelWriter.writeBooleanObject(parcel, 21, this.zzs, false);
        SafeParcelWriter.writeLong(parcel, 22, this.zzt);
        SafeParcelWriter.writeStringList(parcel, 23, this.zzu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
