package p006io.sentry;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: io.sentry.MapUtil */
public class MapUtil {
    public static JSONObject toJSONObject(ReadableMap readableMap) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (readableMap.getType(nextKey)) {
                case Null:
                    jSONObject.put(nextKey, null);
                    break;
                case Boolean:
                    jSONObject.put(nextKey, readableMap.getBoolean(nextKey));
                    break;
                case Number:
                    jSONObject.put(nextKey, readableMap.getDouble(nextKey));
                    break;
                case String:
                    jSONObject.put(nextKey, readableMap.getString(nextKey));
                    break;
                case Map:
                    jSONObject.put(nextKey, toJSONObject(readableMap.getMap(nextKey)));
                    break;
                case Array:
                    jSONObject.put(nextKey, ArrayUtil.toJSONArray(readableMap.getArray(nextKey)));
                    break;
            }
        }
        return jSONObject;
    }

    public static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            if (obj instanceof JSONArray) {
                obj = ArrayUtil.toArray((JSONArray) obj);
            }
            hashMap.put(str, obj);
        }
        return hashMap;
    }

    public static Map<String, Object> toMap(ReadableMap readableMap) {
        HashMap hashMap = new HashMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (readableMap.getType(nextKey)) {
                case Null:
                    hashMap.put(nextKey, null);
                    break;
                case Boolean:
                    hashMap.put(nextKey, Boolean.valueOf(readableMap.getBoolean(nextKey)));
                    break;
                case Number:
                    hashMap.put(nextKey, Double.valueOf(readableMap.getDouble(nextKey)));
                    break;
                case String:
                    hashMap.put(nextKey, readableMap.getString(nextKey));
                    break;
                case Map:
                    hashMap.put(nextKey, toMap(readableMap.getMap(nextKey)));
                    break;
                case Array:
                    hashMap.put(nextKey, ArrayUtil.toArray(readableMap.getArray(nextKey)));
                    break;
            }
        }
        return hashMap;
    }

    public static WritableMap toWritableMap(Map<String, Object> map) {
        WritableMap createMap = Arguments.createMap();
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                createMap.putNull((String) entry.getKey());
            } else if (value instanceof Boolean) {
                createMap.putBoolean((String) entry.getKey(), ((Boolean) value).booleanValue());
            } else if (value instanceof Double) {
                createMap.putDouble((String) entry.getKey(), ((Double) value).doubleValue());
            } else if (value instanceof Integer) {
                createMap.putInt((String) entry.getKey(), ((Integer) value).intValue());
            } else if (value instanceof String) {
                createMap.putString((String) entry.getKey(), (String) value);
            } else if (value instanceof Map) {
                createMap.putMap((String) entry.getKey(), toWritableMap((Map) value));
            } else if (value.getClass() != null && value.getClass().isArray()) {
                createMap.putArray((String) entry.getKey(), ArrayUtil.toWritableArray((Object[]) value));
            }
        }
        return createMap;
    }
}
