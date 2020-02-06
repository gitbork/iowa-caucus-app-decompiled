package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "InitializationParamsCreator")
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.0.1 */
public final class zzx extends AbstractSafeParcelable {
    public static final Creator<zzx> CREATOR = new zzw();
    @Field(mo15127id = 1)
    public final long zza;
    @Field(mo15127id = 2)
    public final long zzb;
    @Field(mo15127id = 3)
    public final boolean zzc;
    @Field(mo15127id = 4)
    public final String zzd;
    @Field(mo15127id = 5)
    public final String zze;
    @Field(mo15127id = 6)
    public final String zzf;
    @Field(mo15127id = 7)
    public final Bundle zzg;

    @Constructor
    public zzx(@Param(mo15130id = 1) long j, @Param(mo15130id = 2) long j2, @Param(mo15130id = 3) boolean z, @Param(mo15130id = 4) String str, @Param(mo15130id = 5) String str2, @Param(mo15130id = 6) String str3, @Param(mo15130id = 7) Bundle bundle) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = z;
        this.zzd = str;
        this.zze = str2;
        this.zzf = str3;
        this.zzg = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
