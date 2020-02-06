package p006io.invertase.firebase.app;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import p006io.invertase.firebase.utils.ReactNativeFirebaseUtilsModule;

/* renamed from: io.invertase.firebase.app.ReactNativeFirebaseAppPackage */
public class ReactNativeFirebaseAppPackage implements ReactPackage {
    @Nonnull
    public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactApplicationContext) {
        if (ReactNativeFirebaseApp.getApplicationContext() == null) {
            ReactNativeFirebaseApp.setApplicationContext(reactApplicationContext.getApplicationContext());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ReactNativeFirebaseAppModule(reactApplicationContext));
        arrayList.add(new ReactNativeFirebaseUtilsModule(reactApplicationContext));
        return arrayList;
    }

    @Nonnull
    public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
