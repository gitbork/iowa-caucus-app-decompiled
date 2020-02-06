package com.google.firebase.firestore.util;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public class FileUtil {

    @TargetApi(26)
    /* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
    private static class DefaultFileDeleter {
        private DefaultFileDeleter() {
        }

        public static void delete(File file) throws IOException {
            try {
                Files.deleteIfExists(file.toPath());
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to delete file ");
                sb.append(file);
                sb.append(": ");
                sb.append(e);
                throw new IOException(sb.toString());
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
    private static class LegacyFileDeleter {
        private LegacyFileDeleter() {
        }

        public static void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to delete file ");
                sb.append(file);
                throw new IOException(sb.toString());
            }
        }
    }

    public static void delete(File file) throws IOException {
        if (VERSION.SDK_INT >= 26) {
            DefaultFileDeleter.delete(file);
        } else {
            LegacyFileDeleter.delete(file);
        }
    }
}
