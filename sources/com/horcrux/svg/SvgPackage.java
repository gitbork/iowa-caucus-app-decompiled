package com.horcrux.svg;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;

public class SvgPackage implements ReactPackage {
    @Nonnull
    public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new ViewManager[]{new GroupViewManager(), new PathViewManager(), new CircleViewManager(), new EllipseViewManager(), new LineViewManager(), new RectViewManager(), new TextViewManager(), new TSpanViewManager(), new TextPathViewManager(), new ImageViewManager(), new ClipPathViewManager(), new DefsViewManager(), new UseViewManager(), new SymbolManager(), new LinearGradientManager(), new RadialGradientManager(), new PatternManager(), new MaskManager(), new MarkerManager(), new SvgViewManager()});
    }

    @Nonnull
    public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new NativeModule[]{new SvgViewModule(reactApplicationContext), new RNSVGRenderableManager(reactApplicationContext)});
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }
}
