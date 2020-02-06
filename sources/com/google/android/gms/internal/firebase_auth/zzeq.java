package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.firebase_auth.zzp.zzg;
import com.google.firebase.auth.api.internal.zzea;
import java.util.List;

@Class(creator = "GetAccountInfoResponseCreator")
@Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzeq extends AbstractSafeParcelable implements zzea<zzeq, zzg> {
    public static final Creator<zzeq> CREATOR = new zzep();
    @Field(getter = "getUserList", mo15127id = 2)
    private zzeu zza;

    public zzeq() {
    }

    @Constructor
    zzeq(@Param(mo15130id = 2) zzeu zzeu) {
        zzeu zzeu2;
        if (zzeu == null) {
            zzeu2 = new zzeu();
        } else {
            zzeu2 = zzeu.zza(zzeu);
        }
        this.zza = zzeu2;
    }

    public final List<zzes> zzb() {
        return this.zza.zza();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzjq<zzg> zza() {
        return zzg.zzb();
    }

    public final /* synthetic */ zzea zza(zzjg zzjg) {
        if (zzjg instanceof zzg) {
            zzg zzg = (zzg) zzjg;
            if (zzg.zza() == 0) {
                this.zza = new zzeu();
            } else {
                this.zza = zzeu.zza(zzg);
            }
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of GetAccountInfoResponse.");
    }
}
