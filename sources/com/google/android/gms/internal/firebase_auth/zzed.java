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
import com.google.firebase.auth.internal.zzar;
import com.google.firebase.auth.zzg;
import com.google.firebase.auth.zzy;
import java.util.List;

@Class(creator = "OnFailedMfaSignInAidlResponseCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzed extends AbstractSafeParcelable {
    public static final Creator<zzed> CREATOR = new zzef();
    @Field(getter = "getMfaPendingCredential", mo15127id = 1)
    private String zza;
    @Field(getter = "getMfaInfoList", mo15127id = 2)
    private List<zzfa> zzb;
    @Field(getter = "getDefaultOAuthCredential", mo15127id = 3)
    private zzg zzc;

    @Constructor
    public zzed(@Param(mo15130id = 1) String str, @Param(mo15130id = 2) List<zzfa> list, @Param(mo15130id = 3) @Nullable zzg zzg) {
        this.zza = str;
        this.zzb = list;
        this.zzc = zzg;
    }

    public final String zza() {
        return this.zza;
    }

    public final zzg zzb() {
        return this.zzc;
    }

    public final List<zzy> zzc() {
        return zzar.zza(this.zzb);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
