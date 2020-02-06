package org.reactnative.camera.utils;

import android.content.Context;
import java.io.File;

public class ScopedContext {
    private File cacheDirectory = null;

    public ScopedContext(Context context) {
        createCacheDirectory(context);
    }

    public void createCacheDirectory(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getCacheDir());
        sb.append("/Camera/");
        this.cacheDirectory = new File(sb.toString());
    }

    public File getCacheDirectory() {
        return this.cacheDirectory;
    }
}
