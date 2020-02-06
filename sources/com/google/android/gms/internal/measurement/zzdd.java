package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzdd<T> implements zzcz<T>, Serializable {
    @NullableDecl
    private final T zza;

    zzdd(@NullableDecl T t) {
        this.zza = t;
    }

    public final T zza() {
        return this.zza;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof zzdd)) {
            return false;
        }
        zzdd zzdd = (zzdd) obj;
        T t = this.zza;
        T t2 = zzdd.zza;
        if (t == t2 || (t != null && t.equals(t2))) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("Suppliers.ofInstance(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
