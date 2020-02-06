package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.internal.firebase_auth.zzaz;
import com.google.firebase.auth.zzae;
import com.google.firebase.auth.zzy;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "MultiFactorInfoListCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzaq extends AbstractSafeParcelable {
    public static final Creator<zzaq> CREATOR = new zzat();
    @Field(getter = "getPhoneMultiFactorInfoList", mo15127id = 1)
    private final List<zzae> zza;

    @Constructor
    zzaq(@Param(mo15130id = 1) List<zzae> list) {
        if (list == null) {
            list = zzaz.zza();
        }
        this.zza = list;
    }

    public static zzaq zza(List<zzy> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (zzy zzy : list) {
            if (zzy instanceof zzae) {
                arrayList.add((zzae) zzy);
            }
        }
        return new zzaq(arrayList);
    }

    public final List<zzy> zza() {
        ArrayList arrayList = new ArrayList();
        for (zzae add : this.zza) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
