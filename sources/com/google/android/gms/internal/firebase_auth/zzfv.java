package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp.zzt;
import com.google.firebase.auth.api.internal.zzea;

@Class(creator = "VerifyCustomTokenResponseCreator")
@Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzfv extends AbstractSafeParcelable implements zzea<zzfv, zzt> {
    public static final Creator<zzfv> CREATOR = new zzfy();
    @Field(getter = "getIdToken", mo15127id = 2)
    private String zza;
    @Field(getter = "getRefreshToken", mo15127id = 3)
    private String zzb;
    @Field(getter = "getExpiresIn", mo15127id = 4)
    private long zzc;
    @Field(getter = "isNewUser", mo15127id = 5)
    private boolean zzd;

    public zzfv() {
    }

    @Constructor
    zzfv(@Param(mo15130id = 2) String str, @Param(mo15130id = 3) String str2, @Param(mo15130id = 4) long j, @Param(mo15130id = 5) boolean z) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = j;
        this.zzd = z;
    }

    public final String zzb() {
        return this.zza;
    }

    @NonNull
    public final String zzc() {
        return this.zzb;
    }

    public final long zzd() {
        return this.zzc;
    }

    public final boolean zze() {
        return this.zzd;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzjq<zzt> zza() {
        return zzt.zze();
    }

    public final /* synthetic */ zzea zza(zzjg zzjg) {
        if (zzjg instanceof zzt) {
            zzt zzt = (zzt) zzjg;
            this.zza = Strings.emptyToNull(zzt.zza());
            this.zzb = Strings.emptyToNull(zzt.zzb());
            this.zzc = zzt.zzc();
            this.zzd = zzt.zzd();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of VerifyCustomTokenResponse.");
    }
}
