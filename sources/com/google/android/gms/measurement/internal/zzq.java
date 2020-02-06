package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "ConditionalUserPropertyParcelCreator")
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
public final class zzq extends AbstractSafeParcelable {
    public static final Creator<zzq> CREATOR = new zzt();
    @Field(mo15127id = 2)
    public String zza;
    @Field(mo15127id = 3)
    public String zzb;
    @Field(mo15127id = 4)
    public zzjw zzc;
    @Field(mo15127id = 5)
    public long zzd;
    @Field(mo15127id = 6)
    public boolean zze;
    @Field(mo15127id = 7)
    public String zzf;
    @Field(mo15127id = 8)
    public zzai zzg;
    @Field(mo15127id = 9)
    public long zzh;
    @Field(mo15127id = 10)
    public zzai zzi;
    @Field(mo15127id = 11)
    public long zzj;
    @Field(mo15127id = 12)
    public zzai zzk;

    zzq(zzq zzq) {
        Preconditions.checkNotNull(zzq);
        this.zza = zzq.zza;
        this.zzb = zzq.zzb;
        this.zzc = zzq.zzc;
        this.zzd = zzq.zzd;
        this.zze = zzq.zze;
        this.zzf = zzq.zzf;
        this.zzg = zzq.zzg;
        this.zzh = zzq.zzh;
        this.zzi = zzq.zzi;
        this.zzj = zzq.zzj;
        this.zzk = zzq.zzk;
    }

    @Constructor
    zzq(@Param(mo15130id = 2) String str, @Param(mo15130id = 3) String str2, @Param(mo15130id = 4) zzjw zzjw, @Param(mo15130id = 5) long j, @Param(mo15130id = 6) boolean z, @Param(mo15130id = 7) String str3, @Param(mo15130id = 8) zzai zzai, @Param(mo15130id = 9) long j2, @Param(mo15130id = 10) zzai zzai2, @Param(mo15130id = 11) long j3, @Param(mo15130id = 12) zzai zzai3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzjw;
        this.zzd = j;
        this.zze = z;
        this.zzf = str3;
        this.zzg = zzai;
        this.zzh = j2;
        this.zzi = zzai2;
        this.zzj = j3;
        this.zzk = zzai3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
