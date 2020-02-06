package p006io.invertase.firebase.app;

import androidx.annotation.Keep;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Collections;
import java.util.List;

@Keep
/* renamed from: io.invertase.firebase.app.ReactNativeFirebaseAppRegistrar */
public class ReactNativeFirebaseAppRegistrar implements ComponentRegistrar {
    public List<Component<?>> getComponents() {
        return Collections.singletonList(LibraryVersionComponent.create("react-native-firebase", "6.0.4"));
    }
}
