package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.internal.firebase_auth.zzed;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zzab;
import com.google.firebase.auth.zzae;
import com.google.firebase.auth.zzg;
import com.google.firebase.auth.zzy;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "DefaultMultiFactorResolverCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzu extends zzab {
    public static final Creator<zzu> CREATOR = new zzt();
    @Field(getter = "getPhoneMultiFactorInfoList", mo15127id = 1)
    private final List<zzae> zza = new ArrayList();
    @Field(getter = "getSession", mo15127id = 2)
    private final zzw zzb;
    @Field(getter = "getFirebaseAppName", mo15127id = 3)
    private final String zzc;
    @Field(getter = "getDefaultOAuthCredential", mo15127id = 4)
    private final zzg zzd;
    @Field(getter = "getReauthUser", mo15127id = 5)
    private final zzn zze;

    @Constructor
    public zzu(@Param(mo15130id = 1) List<zzae> list, @Param(mo15130id = 2) zzw zzw, @Param(mo15130id = 3) String str, @Param(mo15130id = 4) @Nullable zzg zzg, @Param(mo15130id = 5) @Nullable zzn zzn) {
        for (zzy zzy : list) {
            if (zzy instanceof zzae) {
                this.zza.add((zzae) zzy);
            }
        }
        this.zzb = (zzw) Preconditions.checkNotNull(zzw);
        this.zzc = Preconditions.checkNotEmpty(str);
        this.zzd = zzg;
        this.zze = zzn;
    }

    public static zzu zza(zzed zzed, FirebaseAuth firebaseAuth, @Nullable FirebaseUser firebaseUser) {
        List<zzy> zzc2 = zzed.zzc();
        ArrayList arrayList = new ArrayList();
        for (zzy zzy : zzc2) {
            if (zzy instanceof zzae) {
                arrayList.add((zzae) zzy);
            }
        }
        zzu zzu = new zzu(arrayList, zzw.zza(zzed.zzc(), zzed.zza()), firebaseAuth.zzb().getName(), zzed.zzb(), (zzn) firebaseUser);
        return zzu;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
