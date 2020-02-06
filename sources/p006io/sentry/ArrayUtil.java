package p006io.sentry;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: io.sentry.ArrayUtil */
public class ArrayUtil {
    public static JSONArray toJSONArray(ReadableArray readableArray) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    jSONArray.put(i, null);
                    break;
                case Boolean:
                    jSONArray.put(i, readableArray.getBoolean(i));
                    break;
                case Number:
                    jSONArray.put(i, readableArray.getDouble(i));
                    break;
                case String:
                    jSONArray.put(i, readableArray.getString(i));
                    break;
                case Map:
                    jSONArray.put(i, MapUtil.toJSONObject(readableArray.getMap(i)));
                    break;
                case Array:
                    jSONArray.put(i, toJSONArray(readableArray.getArray(i)));
                    break;
            }
        }
        return jSONArray;
    }

    public static Object[] toArray(JSONArray jSONArray) throws JSONException {
        Object[] objArr = new Object[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONObject) {
                obj = MapUtil.toMap((JSONObject) obj);
            }
            if (obj instanceof JSONArray) {
                obj = toArray((JSONArray) obj);
            }
            objArr[i] = obj;
        }
        return objArr;
    }

    public static Object[] toArray(ReadableArray readableArray) {
        Object[] objArr = new Object[readableArray.size()];
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    objArr[i] = null;
                    break;
                case Boolean:
                    objArr[i] = Boolean.valueOf(readableArray.getBoolean(i));
                    break;
                case Number:
                    objArr[i] = Double.valueOf(readableArray.getDouble(i));
                    break;
                case String:
                    objArr[i] = readableArray.getString(i);
                    break;
                case Map:
                    objArr[i] = MapUtil.toMap(readableArray.getMap(i));
                    break;
                case Array:
                    objArr[i] = toArray(readableArray.getArray(i));
                    break;
            }
        }
        return objArr;
    }

    public static WritableArray toWritableArray(Object[] objArr) {
        WritableArray createArray = Arguments.createArray();
        for (Object[] objArr2 : objArr) {
            if (objArr2 == null) {
                createArray.pushNull();
            }
            if (objArr2 instanceof Boolean) {
                createArray.pushBoolean(((Boolean) objArr2).booleanValue());
            }
            if (objArr2 instanceof Double) {
                createArray.pushDouble(((Double) objArr2).doubleValue());
            }
            if (objArr2 instanceof Integer) {
                createArray.pushInt(((Integer) objArr2).intValue());
            }
            if (objArr2 instanceof String) {
                createArray.pushString((String) objArr2);
            }
            if (objArr2 instanceof Map) {
                createArray.pushMap(MapUtil.toWritableMap((Map) objArr2));
            }
            if (objArr2.getClass().isArray()) {
                createArray.pushArray(toWritableArray(objArr2));
            }
        }
        return createArray;
    }
}
