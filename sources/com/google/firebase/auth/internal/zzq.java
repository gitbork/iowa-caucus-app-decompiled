package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.firebase_auth.zzey;
import com.google.firebase.auth.zzg;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzq implements Creator<zzn> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzn[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzey zzey = null;
        zzj zzj = null;
        String str = null;
        String str2 = null;
        List list = null;
        List list2 = null;
        String str3 = null;
        Boolean bool = null;
        zzp zzp = null;
        zzg zzg = null;
        zzaq zzaq = null;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzey = (zzey) SafeParcelReader.createParcelable(parcel2, readHeader, zzey.CREATOR);
                    break;
                case 2:
                    zzj = (zzj) SafeParcelReader.createParcelable(parcel2, readHeader, zzj.CREATOR);
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 5:
                    list = SafeParcelReader.createTypedList(parcel2, readHeader, zzj.CREATOR);
                    break;
                case 6:
                    list2 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 7:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 8:
                    bool = SafeParcelReader.readBooleanObject(parcel2, readHeader);
                    break;
                case 9:
                    zzp = (zzp) SafeParcelReader.createParcelable(parcel2, readHeader, zzp.CREATOR);
                    break;
                case 10:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 11:
                    zzg = (zzg) SafeParcelReader.createParcelable(parcel2, readHeader, zzg.CREATOR);
                    break;
                case 12:
                    zzaq = (zzaq) SafeParcelReader.createParcelable(parcel2, readHeader, zzaq.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        zzn zzn = new zzn(zzey, zzj, str, str2, list, list2, str3, bool, zzp, z, zzg, zzaq);
        return zzn;
    }
}
