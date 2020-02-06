package p006io.invertase.firebase.interfaces;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.ReactContext;

/* renamed from: io.invertase.firebase.interfaces.ContextProvider */
public interface ContextProvider {
    Activity getActivity();

    Context getApplicationContext();

    ReactContext getContext();
}
