package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.auth.zzae;
import com.google.firebase.auth.zzg;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzt implements Creator<zzu> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzu[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        List list = null;
        zzw zzw = null;
        String str = null;
        zzg zzg = null;
        zzn zzn = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                list = SafeParcelReader.createTypedList(parcel, readHeader, zzae.CREATOR);
            } else if (fieldId == 2) {
                zzw = (zzw) SafeParcelReader.createParcelable(parcel, readHeader, zzw.CREATOR);
            } else if (fieldId == 3) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 4) {
                zzg = (zzg) SafeParcelReader.createParcelable(parcel, readHeader, zzg.CREATOR);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzn = (zzn) SafeParcelReader.createParcelable(parcel, readHeader, zzn.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        zzu zzu = new zzu(list, zzw, str, zzg, zzn);
        return zzu;
    }
}
