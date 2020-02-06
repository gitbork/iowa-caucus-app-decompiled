package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import java.util.HashMap;
import java.util.Map;

public class ReactFontManager {
    private static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static final String FONTS_ASSET_PATH = "fonts/";
    private static ReactFontManager sReactFontManagerInstance;
    private final Map<String, Typeface> mCustomTypefaceCache = new HashMap();
    private final Map<String, FontFamily> mFontCache = new HashMap();

    private static class FontFamily {
        private SparseArray<Typeface> mTypefaceSparseArray;

        private FontFamily() {
            this.mTypefaceSparseArray = new SparseArray<>(4);
        }

        public Typeface getTypeface(int i) {
            return (Typeface) this.mTypefaceSparseArray.get(i);
        }

        public void setTypeface(int i, Typeface typeface) {
            this.mTypefaceSparseArray.put(i, typeface);
        }
    }

    private ReactFontManager() {
    }

    public static ReactFontManager getInstance() {
        if (sReactFontManagerInstance == null) {
            sReactFontManagerInstance = new ReactFontManager();
        }
        return sReactFontManagerInstance;
    }

    @Nullable
    public Typeface getTypeface(String str, int i, AssetManager assetManager) {
        return getTypeface(str, i, 0, assetManager);
    }

    @Nullable
    public Typeface getTypeface(String str, int i, int i2, AssetManager assetManager) {
        if (this.mCustomTypefaceCache.containsKey(str)) {
            Typeface typeface = (Typeface) this.mCustomTypefaceCache.get(str);
            if (VERSION.SDK_INT < 28 || i2 < 100 || i2 > 1000) {
                return Typeface.create(typeface, i);
            }
            return Typeface.create(typeface, i2, (i & 2) != 0);
        }
        FontFamily fontFamily = (FontFamily) this.mFontCache.get(str);
        if (fontFamily == null) {
            fontFamily = new FontFamily();
            this.mFontCache.put(str, fontFamily);
        }
        Typeface typeface2 = fontFamily.getTypeface(i);
        if (typeface2 == null) {
            typeface2 = createTypeface(str, i, assetManager);
            if (typeface2 != null) {
                fontFamily.setTypeface(i, typeface2);
            }
        }
        return typeface2;
    }

    public void addCustomFont(@NonNull Context context, @NonNull String str, int i) {
        Typeface font = ResourcesCompat.getFont(context, i);
        if (font != null) {
            this.mCustomTypefaceCache.put(str, font);
        }
    }

    public void setTypeface(String str, int i, Typeface typeface) {
        if (typeface != null) {
            FontFamily fontFamily = (FontFamily) this.mFontCache.get(str);
            if (fontFamily == null) {
                fontFamily = new FontFamily();
                this.mFontCache.put(str, fontFamily);
            }
            fontFamily.setTypeface(i, typeface);
        }
    }

    @Nullable
    private static Typeface createTypeface(String str, int i, AssetManager assetManager) {
        String str2 = EXTENSIONS[i];
        String[] strArr = FILE_EXTENSIONS;
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            String str3 = strArr[i2];
            StringBuilder sb = new StringBuilder();
            sb.append(FONTS_ASSET_PATH);
            sb.append(str);
            sb.append(str2);
            sb.append(str3);
            try {
                return Typeface.createFromAsset(assetManager, sb.toString());
            } catch (RuntimeException unused) {
                i2++;
            }
        }
        return Typeface.create(str, i);
    }
}
