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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "EventParcelCreator")
@Reserved({1})
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
public final class zzai extends AbstractSafeParcelable {
    public static final Creator<zzai> CREATOR = new zzal();
    @Field(mo15127id = 2)
    public final String zza;
    @Field(mo15127id = 3)
    public final zzah zzb;
    @Field(mo15127id = 4)
    public final String zzc;
    @Field(mo15127id = 5)
    public final long zzd;

    @Constructor
    public zzai(@Param(mo15130id = 2) String str, @Param(mo15130id = 3) zzah zzah, @Param(mo15130id = 4) String str2, @Param(mo15130id = 5) long j) {
        this.zza = str;
        this.zzb = zzah;
        this.zzc = str2;
        this.zzd = j;
    }

    zzai(zzai zzai, long j) {
        Preconditions.checkNotNull(zzai);
        this.zza = zzai.zza;
        this.zzb = zzai.zzb;
        this.zzc = zzai.zzc;
        this.zzd = j;
    }

    public final String toString() {
        String str = this.zzc;
        String str2 = this.zza;
        String valueOf = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("origin=");
        sb.append(str);
        sb.append(",name=");
        sb.append(str2);
        sb.append(",params=");
        sb.append(valueOf);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
