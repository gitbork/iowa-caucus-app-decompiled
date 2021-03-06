package p006io.invertase.firebase.common;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.MainThread;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import p006io.invertase.firebase.interfaces.NativeEvent;

/* renamed from: io.invertase.firebase.common.ReactNativeFirebaseEventEmitter */
public class ReactNativeFirebaseEventEmitter {
    private static ReactNativeFirebaseEventEmitter sharedInstance = new ReactNativeFirebaseEventEmitter();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private int jsListenerCount;
    private final HashMap<String, Integer> jsListeners = new HashMap<>();
    private Boolean jsReady = Boolean.valueOf(false);
    private final List<NativeEvent> queuedEvents = new ArrayList();
    private ReactContext reactContext;

    public static ReactNativeFirebaseEventEmitter getSharedInstance() {
        return sharedInstance;
    }

    public void attachReactContext(ReactContext reactContext2) {
        this.handler.post(new Runnable(reactContext2) {
            private final /* synthetic */ ReactContext f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ReactNativeFirebaseEventEmitter.this.lambda$attachReactContext$0$ReactNativeFirebaseEventEmitter(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$attachReactContext$0$ReactNativeFirebaseEventEmitter(ReactContext reactContext2) {
        this.reactContext = reactContext2;
        sendQueuedEvents();
    }

    public void notifyJsReady(Boolean bool) {
        this.handler.post(new Runnable(bool) {
            private final /* synthetic */ Boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ReactNativeFirebaseEventEmitter.this.lambda$notifyJsReady$1$ReactNativeFirebaseEventEmitter(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$notifyJsReady$1$ReactNativeFirebaseEventEmitter(Boolean bool) {
        this.jsReady = bool;
        sendQueuedEvents();
    }

    public void sendEvent(NativeEvent nativeEvent) {
        this.handler.post(new Runnable(nativeEvent) {
            private final /* synthetic */ NativeEvent f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ReactNativeFirebaseEventEmitter.this.lambda$sendEvent$2$ReactNativeFirebaseEventEmitter(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$sendEvent$2$ReactNativeFirebaseEventEmitter(NativeEvent nativeEvent) {
        synchronized (this.jsListeners) {
            if (!this.jsListeners.containsKey(nativeEvent.getEventName()) || !emit(nativeEvent)) {
                this.queuedEvents.add(nativeEvent);
            }
        }
    }

    public void addListener(String str) {
        synchronized (this.jsListeners) {
            this.jsListenerCount++;
            if (!this.jsListeners.containsKey(str)) {
                this.jsListeners.put(str, Integer.valueOf(1));
            } else {
                this.jsListeners.put(str, Integer.valueOf(((Integer) this.jsListeners.get(str)).intValue() + 1));
            }
        }
        this.handler.post(new Runnable() {
            public final void run() {
                ReactNativeFirebaseEventEmitter.this.sendQueuedEvents();
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeListener(java.lang.String r6, java.lang.Boolean r7) {
        /*
            r5 = this;
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.jsListeners
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, java.lang.Integer> r1 = r5.jsListeners     // Catch:{ all -> 0x0041 }
            boolean r1 = r1.containsKey(r6)     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x003f
            java.util.HashMap<java.lang.String, java.lang.Integer> r1 = r5.jsListeners     // Catch:{ all -> 0x0041 }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x0041 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x0041 }
            int r1 = r1.intValue()     // Catch:{ all -> 0x0041 }
            r2 = 1
            if (r1 <= r2) goto L_0x002d
            boolean r3 = r7.booleanValue()     // Catch:{ all -> 0x0041 }
            if (r3 == 0) goto L_0x0021
            goto L_0x002d
        L_0x0021:
            java.util.HashMap<java.lang.String, java.lang.Integer> r3 = r5.jsListeners     // Catch:{ all -> 0x0041 }
            int r4 = r1 + -1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0041 }
            r3.put(r6, r4)     // Catch:{ all -> 0x0041 }
            goto L_0x0032
        L_0x002d:
            java.util.HashMap<java.lang.String, java.lang.Integer> r3 = r5.jsListeners     // Catch:{ all -> 0x0041 }
            r3.remove(r6)     // Catch:{ all -> 0x0041 }
        L_0x0032:
            int r6 = r5.jsListenerCount     // Catch:{ all -> 0x0041 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x0041 }
            if (r7 == 0) goto L_0x003b
            goto L_0x003c
        L_0x003b:
            r1 = 1
        L_0x003c:
            int r6 = r6 - r1
            r5.jsListenerCount = r6     // Catch:{ all -> 0x0041 }
        L_0x003f:
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            return
        L_0x0041:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p006io.invertase.firebase.common.ReactNativeFirebaseEventEmitter.removeListener(java.lang.String, java.lang.Boolean):void");
    }

    public WritableMap getListenersMap() {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap.putInt("listeners", this.jsListenerCount);
        createMap.putInt("queued", this.queuedEvents.size());
        synchronized (this.jsListeners) {
            for (Entry entry : this.jsListeners.entrySet()) {
                createMap2.putInt((String) entry.getKey(), ((Integer) entry.getValue()).intValue());
            }
        }
        createMap.putMap("events", createMap2);
        return createMap;
    }

    /* access modifiers changed from: private */
    @MainThread
    public void sendQueuedEvents() {
        synchronized (this.jsListeners) {
            Iterator it = new ArrayList(this.queuedEvents).iterator();
            while (it.hasNext()) {
                NativeEvent nativeEvent = (NativeEvent) it.next();
                if (this.jsListeners.containsKey(nativeEvent.getEventName())) {
                    this.queuedEvents.remove(nativeEvent);
                    sendEvent(nativeEvent);
                }
            }
        }
    }

    @MainThread
    private boolean emit(NativeEvent nativeEvent) {
        if (this.jsReady.booleanValue()) {
            ReactContext reactContext2 = this.reactContext;
            if (reactContext2 != null && reactContext2.hasActiveCatalystInstance()) {
                try {
                    RCTDeviceEventEmitter rCTDeviceEventEmitter = (RCTDeviceEventEmitter) this.reactContext.getJSModule(RCTDeviceEventEmitter.class);
                    StringBuilder sb = new StringBuilder();
                    sb.append("rnfb_");
                    sb.append(nativeEvent.getEventName());
                    rCTDeviceEventEmitter.emit(sb.toString(), nativeEvent.getEventBody());
                    return true;
                } catch (Exception e) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Error sending Event ");
                    sb2.append(nativeEvent.getEventName());
                    Log.wtf("RNFB_EMITTER", sb2.toString(), e);
                }
            }
        }
        return false;
    }
}
