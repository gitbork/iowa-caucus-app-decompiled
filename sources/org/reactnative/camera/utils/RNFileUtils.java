package org.reactnative.camera.utils;

import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RNFileUtils {
    public static File ensureDirExists(File file) throws IOException {
        if (file.isDirectory() || file.mkdirs()) {
            return file;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Couldn't create directory '");
        sb.append(file);
        sb.append("'");
        throw new IOException(sb.toString());
    }

    public static String getOutputFilePath(File file, String str) throws IOException {
        ensureDirExists(file);
        String uuid = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(file);
        sb.append(File.separator);
        sb.append(uuid);
        sb.append(str);
        return sb.toString();
    }

    public static Uri uriFromFile(File file) {
        return Uri.fromFile(file);
    }
}
