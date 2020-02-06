package p006io.sentry.marshaller.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p006io.sentry.event.Breadcrumb;
import p006io.sentry.event.Event;
import p006io.sentry.event.Event.Level;
import p006io.sentry.event.Sdk;
import p006io.sentry.event.interfaces.SentryInterface;
import p006io.sentry.marshaller.Marshaller;
import p006io.sentry.marshaller.Marshaller.UncloseableOutputStream;
import p006io.sentry.util.Util;

/* renamed from: io.sentry.marshaller.json.JsonMarshaller */
public class JsonMarshaller implements Marshaller {
    public static final String BREADCRUMBS = "breadcrumbs";
    public static final String CHECKSUM = "checksum";
    public static final String CONTEXTS = "contexts";
    public static final String CULPRIT = "culprit";
    public static final int DEFAULT_MAX_MESSAGE_LENGTH = 1000;
    public static final String DIST = "dist";
    public static final String ENVIRONMENT = "environment";
    public static final String EVENT_ID = "event_id";
    public static final String EXTRA = "extra";
    public static final String FINGERPRINT = "fingerprint";
    private static final ThreadLocal<DateFormat> ISO_FORMAT = new ThreadLocal<DateFormat>() {
        /* access modifiers changed from: protected */
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };
    public static final String LEVEL = "level";
    public static final String LOGGER = "logger";
    public static final String MESSAGE = "message";
    public static final String MODULES = "modules";
    public static final String PLATFORM = "platform";
    public static final String RELEASE = "release";
    public static final String SDK = "sdk";
    public static final String SERVER_NAME = "server_name";
    public static final String TAGS = "tags";
    public static final String TIMESTAMP = "timestamp";
    public static final String TRANSACTION = "transaction";
    private static final Logger logger = LoggerFactory.getLogger(JsonMarshaller.class);
    private boolean compression;
    private final Map<Class<? extends SentryInterface>, InterfaceBinding<?>> interfaceBindings;
    private final JsonFactory jsonFactory;
    private final int maxMessageLength;

    /* renamed from: io.sentry.marshaller.json.JsonMarshaller$2 */
    static /* synthetic */ class C23562 {
        static final /* synthetic */ int[] $SwitchMap$io$sentry$event$Event$Level = new int[Level.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                io.sentry.event.Event$Level[] r0 = p006io.sentry.event.Event.Level.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$sentry$event$Event$Level = r0
                int[] r0 = $SwitchMap$io$sentry$event$Event$Level     // Catch:{ NoSuchFieldError -> 0x0014 }
                io.sentry.event.Event$Level r1 = p006io.sentry.event.Event.Level.DEBUG     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$io$sentry$event$Event$Level     // Catch:{ NoSuchFieldError -> 0x001f }
                io.sentry.event.Event$Level r1 = p006io.sentry.event.Event.Level.FATAL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$io$sentry$event$Event$Level     // Catch:{ NoSuchFieldError -> 0x002a }
                io.sentry.event.Event$Level r1 = p006io.sentry.event.Event.Level.WARNING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$io$sentry$event$Event$Level     // Catch:{ NoSuchFieldError -> 0x0035 }
                io.sentry.event.Event$Level r1 = p006io.sentry.event.Event.Level.INFO     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$io$sentry$event$Event$Level     // Catch:{ NoSuchFieldError -> 0x0040 }
                io.sentry.event.Event$Level r1 = p006io.sentry.event.Event.Level.ERROR     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p006io.sentry.marshaller.json.JsonMarshaller.C23562.<clinit>():void");
        }
    }

    public String getContentType() {
        return "application/json";
    }

    public JsonMarshaller() {
        this(1000);
    }

    public JsonMarshaller(int i) {
        this.jsonFactory = new JsonFactory();
        this.interfaceBindings = new HashMap();
        this.compression = true;
        this.maxMessageLength = i;
    }

    public void marshall(Event event, OutputStream outputStream) throws IOException {
        JsonGenerator createJsonGenerator;
        String str = "An exception occurred while serialising the event.";
        OutputStream uncloseableOutputStream = new UncloseableOutputStream(outputStream);
        OutputStream gZIPOutputStream = this.compression ? new GZIPOutputStream(uncloseableOutputStream) : uncloseableOutputStream;
        try {
            createJsonGenerator = createJsonGenerator(gZIPOutputStream);
            writeContent(createJsonGenerator, event);
            if (createJsonGenerator != null) {
                createJsonGenerator.close();
            }
            try {
                gZIPOutputStream.close();
                return;
            } catch (IOException e) {
                logger.error(str, (Throwable) e);
                return;
            }
        } catch (IOException e2) {
            try {
                logger.error(str, (Throwable) e2);
                return;
            } finally {
                try {
                    gZIPOutputStream.close();
                } catch (IOException e3) {
                    logger.error(str, (Throwable) e3);
                }
            }
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public JsonGenerator createJsonGenerator(OutputStream outputStream) throws IOException {
        return new SentryJsonGenerator(this.jsonFactory.createGenerator(outputStream));
    }

    public String getContentEncoding() {
        if (isCompressed()) {
            return "gzip";
        }
        return null;
    }

    private void writeContent(JsonGenerator jsonGenerator, Event event) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(EVENT_ID, formatId(event.getId()));
        jsonGenerator.writeStringField(MESSAGE, Util.trimString(event.getMessage(), this.maxMessageLength));
        jsonGenerator.writeStringField("timestamp", ((DateFormat) ISO_FORMAT.get()).format(event.getTimestamp()));
        jsonGenerator.writeStringField("level", formatLevel(event.getLevel()));
        jsonGenerator.writeStringField(LOGGER, event.getLogger());
        jsonGenerator.writeStringField(PLATFORM, event.getPlatform());
        jsonGenerator.writeStringField(CULPRIT, event.getCulprit());
        jsonGenerator.writeStringField(TRANSACTION, event.getTransaction());
        writeSdk(jsonGenerator, event.getSdk());
        writeTags(jsonGenerator, event.getTags());
        writeBreadcumbs(jsonGenerator, event.getBreadcrumbs());
        writeContexts(jsonGenerator, event.getContexts());
        jsonGenerator.writeStringField(SERVER_NAME, event.getServerName());
        jsonGenerator.writeStringField("release", event.getRelease());
        jsonGenerator.writeStringField("dist", event.getDist());
        jsonGenerator.writeStringField("environment", event.getEnvironment());
        writeExtras(jsonGenerator, event.getExtra());
        writeCollection(jsonGenerator, FINGERPRINT, event.getFingerprint());
        jsonGenerator.writeStringField(CHECKSUM, event.getChecksum());
        writeInterfaces(jsonGenerator, event.getSentryInterfaces());
        jsonGenerator.writeEndObject();
    }

    private void writeInterfaces(JsonGenerator jsonGenerator, Map<String, SentryInterface> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            SentryInterface sentryInterface = (SentryInterface) entry.getValue();
            if (this.interfaceBindings.containsKey(sentryInterface.getClass())) {
                jsonGenerator.writeFieldName((String) entry.getKey());
                getInterfaceBinding(sentryInterface).writeInterface(jsonGenerator, (SentryInterface) entry.getValue());
            } else {
                logger.error("Couldn't parse the content of '{}' provided in {}.", entry.getKey(), (Object) sentryInterface);
            }
        }
    }

    private <T extends SentryInterface> InterfaceBinding<? super T> getInterfaceBinding(T t) {
        return (InterfaceBinding) this.interfaceBindings.get(t.getClass());
    }

    private void writeExtras(JsonGenerator jsonGenerator, Map<String, Object> map) throws IOException {
        jsonGenerator.writeObjectFieldStart("extra");
        for (Entry entry : map.entrySet()) {
            jsonGenerator.writeFieldName((String) entry.getKey());
            jsonGenerator.writeObject(entry.getValue());
        }
        jsonGenerator.writeEndObject();
    }

    private void writeCollection(JsonGenerator jsonGenerator, String str, Collection<String> collection) throws IOException {
        if (collection != null && !collection.isEmpty()) {
            jsonGenerator.writeArrayFieldStart(str);
            for (String writeString : collection) {
                jsonGenerator.writeString(writeString);
            }
            jsonGenerator.writeEndArray();
        }
    }

    private void writeSdk(JsonGenerator jsonGenerator, Sdk sdk) throws IOException {
        jsonGenerator.writeObjectFieldStart(SDK);
        jsonGenerator.writeStringField(ConditionalUserProperty.NAME, sdk.getName());
        jsonGenerator.writeStringField("version", sdk.getVersion());
        if (sdk.getIntegrations() != null && !sdk.getIntegrations().isEmpty()) {
            jsonGenerator.writeArrayFieldStart("integrations");
            for (String writeString : sdk.getIntegrations()) {
                jsonGenerator.writeString(writeString);
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }

    private void writeTags(JsonGenerator jsonGenerator, Map<String, String> map) throws IOException {
        jsonGenerator.writeObjectFieldStart("tags");
        for (Entry entry : map.entrySet()) {
            jsonGenerator.writeStringField((String) entry.getKey(), (String) entry.getValue());
        }
        jsonGenerator.writeEndObject();
    }

    private void writeBreadcumbs(JsonGenerator jsonGenerator, List<Breadcrumb> list) throws IOException {
        if (!list.isEmpty()) {
            jsonGenerator.writeObjectFieldStart(BREADCRUMBS);
            jsonGenerator.writeArrayFieldStart("values");
            for (Breadcrumb breadcrumb : list) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("timestamp", breadcrumb.getTimestamp().getTime() / 1000);
                if (breadcrumb.getType() != null) {
                    jsonGenerator.writeStringField("type", breadcrumb.getType().getValue());
                }
                if (breadcrumb.getLevel() != null) {
                    jsonGenerator.writeStringField("level", breadcrumb.getLevel().getValue());
                }
                if (breadcrumb.getMessage() != null) {
                    jsonGenerator.writeStringField(MESSAGE, breadcrumb.getMessage());
                }
                if (breadcrumb.getCategory() != null) {
                    jsonGenerator.writeStringField("category", breadcrumb.getCategory());
                }
                if (breadcrumb.getData() != null && !breadcrumb.getData().isEmpty()) {
                    jsonGenerator.writeObjectFieldStart("data");
                    for (Entry entry : breadcrumb.getData().entrySet()) {
                        jsonGenerator.writeStringField((String) entry.getKey(), (String) entry.getValue());
                    }
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }
    }

    private void writeContexts(JsonGenerator jsonGenerator, Map<String, Map<String, Object>> map) throws IOException {
        if (!map.isEmpty()) {
            jsonGenerator.writeObjectFieldStart(CONTEXTS);
            for (Entry entry : map.entrySet()) {
                jsonGenerator.writeObjectFieldStart((String) entry.getKey());
                for (Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                    jsonGenerator.writeObjectField((String) entry2.getKey(), entry2.getValue());
                }
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndObject();
        }
    }

    private String formatId(UUID uuid) {
        return uuid.toString().replaceAll("-", "");
    }

    private String formatLevel(Level level) {
        if (level == null) {
            return null;
        }
        int i = C23562.$SwitchMap$io$sentry$event$Event$Level[level.ordinal()];
        if (i == 1) {
            return "debug";
        }
        if (i == 2) {
            return "fatal";
        }
        if (i == 3) {
            return "warning";
        }
        if (i == 4) {
            return "info";
        }
        if (i == 5) {
            return "error";
        }
        logger.error("The level '{}' isn't supported, this should NEVER happen, contact Sentry developers", (Object) level.name());
        return null;
    }

    public <T extends SentryInterface, F extends T> void addInterfaceBinding(Class<F> cls, InterfaceBinding<T> interfaceBinding) {
        this.interfaceBindings.put(cls, interfaceBinding);
    }

    public void setCompression(boolean z) {
        this.compression = z;
    }

    public boolean isCompressed() {
        return this.compression;
    }
}
