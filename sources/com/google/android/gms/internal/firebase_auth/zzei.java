package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp.zzb;
import com.google.firebase.auth.api.internal.zzea;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "CreateAuthUriResponseCreator")
@Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzei extends AbstractSafeParcelable implements zzea<zzei, zzb> {
    public static final Creator<zzei> CREATOR = new zzeh();
    @Field(getter = "getAuthUri", mo15127id = 2)
    private String zza;
    @Field(getter = "isRegistered", mo15127id = 3)
    private boolean zzb;
    @Field(getter = "getProviderId", mo15127id = 4)
    private String zzc;
    @Field(getter = "isForExistingProvider", mo15127id = 5)
    private boolean zzd;
    @Field(getter = "getStringList", mo15127id = 6)
    private zzfp zze;
    @Field(getter = "getSignInMethods", mo15127id = 7)
    private List<String> zzf;

    public zzei() {
        this.zze = zzfp.zzb();
    }

    @Constructor
    public zzei(@Param(mo15130id = 2) String str, @Param(mo15130id = 3) boolean z, @Param(mo15130id = 4) String str2, @Param(mo15130id = 5) boolean z2, @Param(mo15130id = 6) zzfp zzfp, @Param(mo15130id = 7) List<String> list) {
        this.zza = str;
        this.zzb = z;
        this.zzc = str2;
        this.zzd = z2;
        this.zze = zzfp == null ? zzfp.zzb() : zzfp.zza(zzfp);
        this.zzf = list;
    }

    @Nullable
    public final List<String> zzb() {
        return this.zzf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzb);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zze, i, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzjq<zzb> zza() {
        return zzb.zzi();
    }

    public final /* synthetic */ zzea zza(zzjg zzjg) {
        zzfp zzfp;
        if (zzjg instanceof zzb) {
            zzb zzb2 = (zzb) zzjg;
            this.zza = Strings.emptyToNull(zzb2.zza());
            this.zzb = zzb2.zzd();
            this.zzc = Strings.emptyToNull(zzb2.zze());
            this.zzd = zzb2.zzf();
            if (zzb2.zzc() == 0) {
                zzfp = zzfp.zzb();
            } else {
                zzfp = new zzfp(1, new ArrayList(zzb2.zzb()));
            }
            this.zze = zzfp;
            this.zzf = zzb2.zzh() == 0 ? new ArrayList<>(0) : zzb2.zzg();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of CreateAuthUriResponse.");
    }
}
