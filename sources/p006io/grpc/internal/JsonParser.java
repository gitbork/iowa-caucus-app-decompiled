package p006io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: io.grpc.internal.JsonParser */
public final class JsonParser {
    private static final Logger logger = Logger.getLogger(JsonParser.class.getName());

    private JsonParser() {
    }

    public static Object parse(String str) throws IOException {
        String str2 = "Failed to close";
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            Object parseRecursive = parseRecursive(jsonReader);
            try {
            } catch (IOException e) {
                logger.log(Level.WARNING, str2, e);
            }
            return parseRecursive;
        } finally {
            try {
                jsonReader.close();
            } catch (IOException e2) {
                logger.log(Level.WARNING, str2, e2);
            }
        }
    }

    private static Object parseRecursive(JsonReader jsonReader) throws IOException {
        Preconditions.checkState(jsonReader.hasNext(), "unexpected end of JSON");
        switch (jsonReader.peek()) {
            case BEGIN_ARRAY:
                return parseJsonArray(jsonReader);
            case BEGIN_OBJECT:
                return parseJsonObject(jsonReader);
            case STRING:
                return jsonReader.nextString();
            case NUMBER:
                return Double.valueOf(jsonReader.nextDouble());
            case BOOLEAN:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case NULL:
                return parseJsonNull(jsonReader);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Bad token: ");
                sb.append(jsonReader.getPath());
                throw new IllegalStateException(sb.toString());
        }
    }

    private static Map<String, ?> parseJsonObject(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (jsonReader.hasNext()) {
            linkedHashMap.put(jsonReader.nextName(), parseRecursive(jsonReader));
        }
        boolean z = jsonReader.peek() == JsonToken.END_OBJECT;
        StringBuilder sb = new StringBuilder();
        sb.append("Bad token: ");
        sb.append(jsonReader.getPath());
        Preconditions.checkState(z, sb.toString());
        jsonReader.endObject();
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static List<?> parseJsonArray(JsonReader jsonReader) throws IOException {
        jsonReader.beginArray();
        ArrayList arrayList = new ArrayList();
        while (jsonReader.hasNext()) {
            arrayList.add(parseRecursive(jsonReader));
        }
        boolean z = jsonReader.peek() == JsonToken.END_ARRAY;
        StringBuilder sb = new StringBuilder();
        sb.append("Bad token: ");
        sb.append(jsonReader.getPath());
        Preconditions.checkState(z, sb.toString());
        jsonReader.endArray();
        return Collections.unmodifiableList(arrayList);
    }

    private static Void parseJsonNull(JsonReader jsonReader) throws IOException {
        jsonReader.nextNull();
        return null;
    }
}
