package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.auth.zzg;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzef implements Creator<zzed> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzed[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        List list = null;
        zzg zzg = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 2) {
                list = SafeParcelReader.createTypedList(parcel, readHeader, zzfa.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzg = (zzg) SafeParcelReader.createParcelable(parcel, readHeader, zzg.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzed(str, list, zzg);
    }
}
