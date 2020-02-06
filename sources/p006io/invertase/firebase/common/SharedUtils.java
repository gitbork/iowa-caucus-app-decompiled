package p006io.invertase.firebase.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;
import androidx.core.p003os.EnvironmentCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p006io.sentry.marshaller.json.JsonMarshaller;

/* renamed from: io.invertase.firebase.common.SharedUtils */
public class SharedUtils {
    private static final String EXPO_CORE_PACKAGE = "expo.core";
    private static final String EXPO_REGISTRY_CLASS = "ModuleRegistry";
    private static final String FLUTTER_CORE_PACKAGE = "io.flutter.plugin.common";
    private static final String FLUTTER_REGISTRY_CLASS = "PluginRegistry";
    private static final String REACT_NATIVE_CORE_PACKAGE = "com.facebook.react.bridge";
    private static final String REACT_NATIVE_REGISTRY_CLASS = "NativeModuleRegistry";
    private static final String RN_DEVSUPPORT_CLASS = "DevSupportManagerImpl";
    private static final String RN_DEVSUPPORT_PACKAGE = "com.facebook.react.devsupport";
    private static final String TAG = "Utils";

    public static int[] rectToIntArray(@Nullable Rect rect) {
        if (rect == null || rect.isEmpty()) {
            return new int[0];
        }
        return new int[]{rect.left, rect.top, rect.right, rect.bottom};
    }

    public static int[] pointToIntArray(@Nullable Point point) {
        if (point == null) {
            return new int[0];
        }
        return new int[]{point.x, point.y};
    }

    public static List<int[]> pointsToIntsList(@Nullable Point[] pointArr) {
        if (pointArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(pointArr.length);
        for (Point pointToIntArray : pointArr) {
            arrayList.add(pointToIntArray(pointToIntArray));
        }
        return arrayList;
    }

    public static Uri getUri(String str) {
        Uri parse = Uri.parse(str);
        if (parse.getScheme() == null || parse.getScheme().isEmpty()) {
            return Uri.fromFile(new File(str));
        }
        return parse;
    }

    public static WritableMap getExceptionMap(Exception exc) {
        WritableMap createMap = Arguments.createMap();
        String message = exc.getMessage();
        String str = EnvironmentCompat.MEDIA_UNKNOWN;
        createMap.putString("code", str);
        createMap.putString("nativeErrorCode", str);
        createMap.putString(JsonMarshaller.MESSAGE, message);
        createMap.putString("nativeErrorMessage", message);
        return createMap;
    }

    public static String timestampToUTC(long j) {
        Date date = new Date((j + ((long) Calendar.getInstance().getTimeZone().getOffset(j))) * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }

    public static void sendEvent(ReactContext reactContext, String str, Object obj) {
        if (reactContext != null) {
            ((RCTDeviceEventEmitter) reactContext.getJSModule(RCTDeviceEventEmitter.class)).emit(str, obj);
        } else {
            Log.d(TAG, "Missing context - cannot send event!");
        }
    }

    public static boolean isAppInForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                boolean z = true;
                try {
                    if (((ReactContext) context).getLifecycleState() != LifecycleState.RESUMED) {
                        z = false;
                    }
                } catch (ClassCastException unused) {
                }
                return z;
            }
        }
        return false;
    }

    public static int getResId(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        if (identifier == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("resource ");
            sb.append(str);
            sb.append(" could not be found");
            Log.e(TAG, sb.toString());
        }
        return identifier;
    }

    public static Boolean reactNativeHasDevSupport() {
        return hasPackageClass(RN_DEVSUPPORT_PACKAGE, RN_DEVSUPPORT_CLASS);
    }

    public static Boolean isExpo() {
        return hasPackageClass(EXPO_CORE_PACKAGE, EXPO_REGISTRY_CLASS);
    }

    public static Boolean isFlutter() {
        return hasPackageClass(FLUTTER_CORE_PACKAGE, FLUTTER_REGISTRY_CLASS);
    }

    public static Boolean isReactNative() {
        return Boolean.valueOf(!isExpo().booleanValue() && hasPackageClass(REACT_NATIVE_CORE_PACKAGE, REACT_NATIVE_REGISTRY_CLASS).booleanValue());
    }

    public static Boolean hasPackageClass(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(".");
        sb.append(str2);
        try {
            Class.forName(sb.toString());
            return Boolean.valueOf(true);
        } catch (Exception unused) {
            return Boolean.valueOf(false);
        }
    }

    public static WritableMap jsonObjectToWritableMap(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        WritableMap createMap = Arguments.createMap();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                createMap.putDouble(str, jSONObject.getDouble(str));
            } else if (obj instanceof Number) {
                createMap.putInt(str, jSONObject.getInt(str));
            } else if (obj instanceof String) {
                createMap.putString(str, jSONObject.getString(str));
            } else if (obj instanceof JSONObject) {
                createMap.putMap(str, jsonObjectToWritableMap(jSONObject.getJSONObject(str)));
            } else if (obj instanceof JSONArray) {
                createMap.putArray(str, jsonArrayToWritableArray(jSONObject.getJSONArray(str)));
            } else if (obj == JSONObject.NULL) {
                createMap.putNull(str);
            }
        }
        return createMap;
    }

    public static WritableArray jsonArrayToWritableArray(JSONArray jSONArray) throws JSONException {
        WritableArray createArray = Arguments.createArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                createArray.pushDouble(jSONArray.getDouble(i));
            } else if (obj instanceof Number) {
                createArray.pushInt(jSONArray.getInt(i));
            } else if (obj instanceof String) {
                createArray.pushString(jSONArray.getString(i));
            } else if (obj instanceof JSONObject) {
                createArray.pushMap(jsonObjectToWritableMap(jSONArray.getJSONObject(i)));
            } else if (obj instanceof JSONArray) {
                createArray.pushArray(jsonArrayToWritableArray(jSONArray.getJSONArray(i)));
            } else if (obj == JSONObject.NULL) {
                createArray.pushNull();
            }
        }
        return createArray;
    }

    public static WritableMap mapToWritableMap(Map<String, Object> map) {
        WritableMap createMap = Arguments.createMap();
        for (Entry entry : map.entrySet()) {
            mapPutValue((String) entry.getKey(), entry.getValue(), createMap);
        }
        return createMap;
    }

    private static WritableArray listToWritableArray(List<Object> list) {
        WritableArray createArray = Arguments.createArray();
        for (Object arrayPushValue : list) {
            arrayPushValue(arrayPushValue, createArray);
        }
        return createArray;
    }

    public static void arrayPushValue(@Nullable Object obj, WritableArray writableArray) {
        if (obj == null || obj == JSONObject.NULL) {
            writableArray.pushNull();
            return;
        }
        String name = obj.getClass().getName();
        char c = 65535;
        switch (name.hashCode()) {
            case -2056817302:
                if (name.equals("java.lang.Integer")) {
                    c = 4;
                    break;
                }
                break;
            case -527879800:
                if (name.equals("java.lang.Float")) {
                    c = 2;
                    break;
                }
                break;
            case 146015330:
                if (name.equals("org.json.JSONArray$1")) {
                    c = 7;
                    break;
                }
                break;
            case 344809556:
                if (name.equals("java.lang.Boolean")) {
                    c = 0;
                    break;
                }
                break;
            case 398795216:
                if (name.equals("java.lang.Long")) {
                    c = 1;
                    break;
                }
                break;
            case 761287205:
                if (name.equals("java.lang.Double")) {
                    c = 3;
                    break;
                }
                break;
            case 1195259493:
                if (name.equals("java.lang.String")) {
                    c = 5;
                    break;
                }
                break;
            case 1614941136:
                if (name.equals("org.json.JSONObject$1")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                writableArray.pushBoolean(((Boolean) obj).booleanValue());
                break;
            case 1:
                writableArray.pushDouble((double) ((Long) obj).longValue());
                break;
            case 2:
                writableArray.pushDouble((double) ((Float) obj).floatValue());
                break;
            case 3:
                writableArray.pushDouble(((Double) obj).doubleValue());
                break;
            case 4:
                writableArray.pushInt(((Integer) obj).intValue());
                break;
            case 5:
                writableArray.pushString((String) obj);
                break;
            case 6:
                try {
                    writableArray.pushMap(jsonObjectToWritableMap((JSONObject) obj));
                    break;
                } catch (JSONException unused) {
                    writableArray.pushNull();
                    break;
                }
            case 7:
                try {
                    writableArray.pushArray(jsonArrayToWritableArray((JSONArray) obj));
                    break;
                } catch (JSONException unused2) {
                    writableArray.pushNull();
                    break;
                }
            default:
                if (!List.class.isAssignableFrom(obj.getClass())) {
                    if (!Map.class.isAssignableFrom(obj.getClass())) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("utils:arrayPushValue:unknownType:");
                        sb.append(name);
                        Log.d(TAG, sb.toString());
                        writableArray.pushNull();
                        break;
                    } else {
                        writableArray.pushMap(mapToWritableMap((Map) obj));
                        break;
                    }
                } else {
                    writableArray.pushArray(listToWritableArray((List) obj));
                    break;
                }
        }
    }

    public static void mapPutValue(String str, @Nullable Object obj, WritableMap writableMap) {
        if (obj == null || obj == JSONObject.NULL) {
            writableMap.putNull(str);
            return;
        }
        String name = obj.getClass().getName();
        char c = 65535;
        switch (name.hashCode()) {
            case -2056817302:
                if (name.equals("java.lang.Integer")) {
                    c = 4;
                    break;
                }
                break;
            case -527879800:
                if (name.equals("java.lang.Float")) {
                    c = 2;
                    break;
                }
                break;
            case 146015330:
                if (name.equals("org.json.JSONArray$1")) {
                    c = 7;
                    break;
                }
                break;
            case 344809556:
                if (name.equals("java.lang.Boolean")) {
                    c = 0;
                    break;
                }
                break;
            case 398795216:
                if (name.equals("java.lang.Long")) {
                    c = 1;
                    break;
                }
                break;
            case 761287205:
                if (name.equals("java.lang.Double")) {
                    c = 3;
                    break;
                }
                break;
            case 1195259493:
                if (name.equals("java.lang.String")) {
                    c = 5;
                    break;
                }
                break;
            case 1614941136:
                if (name.equals("org.json.JSONObject$1")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                writableMap.putBoolean(str, ((Boolean) obj).booleanValue());
                break;
            case 1:
                writableMap.putDouble(str, (double) ((Long) obj).longValue());
                break;
            case 2:
                writableMap.putDouble(str, (double) ((Float) obj).floatValue());
                break;
            case 3:
                writableMap.putDouble(str, ((Double) obj).doubleValue());
                break;
            case 4:
                writableMap.putInt(str, ((Integer) obj).intValue());
                break;
            case 5:
                writableMap.putString(str, (String) obj);
                break;
            case 6:
                try {
                    writableMap.putMap(str, jsonObjectToWritableMap((JSONObject) obj));
                    break;
                } catch (JSONException unused) {
                    writableMap.putNull(str);
                    break;
                }
            case 7:
                try {
                    writableMap.putArray(str, jsonArrayToWritableArray((JSONArray) obj));
                    break;
                } catch (JSONException unused2) {
                    writableMap.putNull(str);
                    break;
                }
            default:
                if (!List.class.isAssignableFrom(obj.getClass())) {
                    if (!Map.class.isAssignableFrom(obj.getClass())) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("utils:mapPutValue:unknownType:");
                        sb.append(name);
                        Log.d(TAG, sb.toString());
                        writableMap.putNull(str);
                        break;
                    } else {
                        writableMap.putMap(str, mapToWritableMap((Map) obj));
                        break;
                    }
                } else {
                    writableMap.putArray(str, listToWritableArray((List) obj));
                    break;
                }
        }
    }

    public static WritableMap readableMapToWritableMap(ReadableMap readableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.merge(readableMap);
        return createMap;
    }
}
