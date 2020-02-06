package com.google.firebase.storage.internal;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.firebase:firebase-storage@@18.1.1 */
public class Slashes {
    @NonNull
    public static String preserveSlashEncode(@Nullable String str) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return slashize(Uri.encode(str));
    }

    @NonNull
    public static String slashize(@NonNull String str) {
        Preconditions.checkNotNull(str);
        return str.replace("%2F", "/");
    }

    @NonNull
    public static String unSlashize(@NonNull String str) {
        Preconditions.checkNotNull(str);
        return str.replace("/", "%2F");
    }

    @NonNull
    public static String normalizeSlashes(@NonNull String str) {
        String[] split;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = "/";
        if (!str.startsWith(str2) && !str.endsWith(str2) && !str.contains("//")) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str3 : str.split(str2, -1)) {
            if (!TextUtils.isEmpty(str3)) {
                if (sb.length() > 0) {
                    sb.append(str2);
                    sb.append(str3);
                } else {
                    sb.append(str3);
                }
            }
        }
        return sb.toString();
    }
}
