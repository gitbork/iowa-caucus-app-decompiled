package p006io.sentry;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p006io.sentry.android.AndroidSentryClientFactory;
import p006io.sentry.android.event.helper.AndroidEventBuilderHelper;
import p006io.sentry.event.Event;
import p006io.sentry.event.Event.Level;
import p006io.sentry.event.EventBuilder;
import p006io.sentry.event.UserBuilder;
import p006io.sentry.event.helper.ShouldSendEventCallback;
import p006io.sentry.event.interfaces.ExceptionInterface;
import p006io.sentry.event.interfaces.SentryException;
import p006io.sentry.event.interfaces.StackTraceInterface;

@ReactModule(name = "RNSentry")
/* renamed from: io.sentry.RNSentryModule */
public class RNSentryModule extends ReactContextBaseJavaModule {
    public static final String NAME = "RNSentry";
    private static AndroidEventBuilderHelper androidHelper;
    static final Logger logger = Logger.getLogger("react-native-sentry");
    private static final Pattern mJsModuleIdPattern = Pattern.compile("(?:^|[/\\\\])(\\d+\\.js)$");
    private static PackageInfo packageInfo;
    private static SentryClient sentryClient;

    public String getName() {
        return NAME;
    }

    public RNSentryModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        packageInfo = getPackageInfo(reactApplicationContext);
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        Boolean valueOf = Boolean.valueOf(true);
        hashMap.put("nativeClientAvailable", valueOf);
        hashMap.put("nativeTransport", valueOf);
        return hashMap;
    }

    @ReactMethod
    public void startWithDsnString(String str, ReadableMap readableMap, Promise promise) {
        if (sentryClient != null) {
            logger.info(String.format("Already started, use existing client '%s'", new Object[]{str}));
            promise.resolve(Boolean.valueOf(false));
            return;
        }
        try {
            sentryClient = Sentry.init(str, new AndroidSentryClientFactory(getReactApplicationContext()));
            androidHelper = new AndroidEventBuilderHelper(getReactApplicationContext());
            sentryClient.addShouldSendEventCallback(new ShouldSendEventCallback() {
                public boolean shouldSend(Event event) {
                    Map sentryInterfaces = event.getSentryInterfaces();
                    String str = ExceptionInterface.EXCEPTION_INTERFACE;
                    return !sentryInterfaces.containsKey(str) || !((SentryException) ((ExceptionInterface) event.getSentryInterfaces().get(str)).getExceptions().getFirst()).getExceptionClassName().contains("JavascriptException");
                }
            });
            String str2 = "environment";
            if (readableMap.hasKey(str2) && readableMap.getString(str2) != null) {
                sentryClient.environment = readableMap.getString(str2);
            }
            String str3 = "release";
            if (readableMap.hasKey(str3) && readableMap.getString(str3) != null) {
                sentryClient.release = readableMap.getString(str3);
            }
            String str4 = "dist";
            if (readableMap.hasKey(str4) && readableMap.getString(str4) != null) {
                sentryClient.dist = readableMap.getString(str4);
            }
            logger.info(String.format("startWithDsnString '%s'", new Object[]{str}));
            promise.resolve(Boolean.valueOf(true));
        } catch (Exception e) {
            Logger logger2 = logger;
            StringBuilder sb = new StringBuilder();
            sb.append("Catching on startWithDsnString, calling callback");
            sb.append(e.getMessage());
            logger2.info(String.format(sb.toString(), new Object[0]));
            promise.reject("SentryError", "Error during init", (Throwable) e);
        }
    }

    @ReactMethod
    public void setLogLevel(int i) {
        logger.setLevel(logLevel(i));
    }

    @ReactMethod
    public void crash() {
        throw new RuntimeException("TEST - Sentry Client Crash");
    }

    @ReactMethod
    public void fetchRelease(Promise promise) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("id", packageInfo.packageName);
        createMap.putString("version", packageInfo.versionName);
        createMap.putString("build", String.valueOf(packageInfo.versionCode));
        promise.resolve(createMap);
    }

    @ReactMethod
    public void deviceContexts(Promise promise) {
        EventBuilder eventBuilder = new EventBuilder();
        androidHelper.helpBuildingEvent(eventBuilder);
        Event build = eventBuilder.build();
        WritableMap createMap = Arguments.createMap();
        for (Entry entry : build.getContexts().entrySet()) {
            createMap.putMap((String) entry.getKey(), MapUtil.toWritableMap((Map) entry.getValue()));
        }
        promise.resolve(createMap);
    }

    @ReactMethod
    public void extraUpdated(ReadableMap readableMap) {
        String str = "__sentry_release";
        if (readableMap.hasKey(str)) {
            sentryClient.release = readableMap.getString(str);
        }
        String str2 = "__sentry_dist";
        if (readableMap.hasKey(str2)) {
            sentryClient.dist = readableMap.getString(str2);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:9|(1:11)|12|(3:16|17|18)|20|(3:24|25|26)|27|28|(4:49|58|(2:60|142)(2:61|141)|62)(9:32|(5:35|(2:37|38)(1:39)|40|41|33)|143|42|(3:44|(1:46)|47)|48|58|(0)(0)|62)|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0131, code lost:
        r17 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0134, code lost:
        r17 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x00bc */
    /* JADX WARNING: Missing exception handler attribute for start block: B:70:0x0177 */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0154  */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendEvent(com.facebook.react.bridge.ReadableMap r26, com.facebook.react.bridge.Promise r27) {
        /*
            r25 = this;
            r1 = r25
            r2 = r26
            java.lang.String r0 = "status_code"
            java.lang.String r3 = "data"
            r4 = r2
            com.facebook.react.bridge.ReadableNativeMap r4 = (com.facebook.react.bridge.ReadableNativeMap) r4
            java.lang.String r5 = "event_id"
            boolean r6 = r2.hasKey(r5)
            if (r6 == 0) goto L_0x0031
            java.lang.String r5 = r2.getString(r5)
            java.lang.String r6 = "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)"
            java.lang.String r7 = "$1-$2-$3-$4-$5"
            java.lang.String r5 = r5.replaceFirst(r6, r7)
            java.util.UUID r5 = java.util.UUID.fromString(r5)
            io.sentry.event.EventBuilder r6 = new io.sentry.event.EventBuilder
            r6.<init>(r5)
            io.sentry.event.Event$Level r5 = r1.eventLevel(r4)
            io.sentry.event.EventBuilder r5 = r6.withLevel(r5)
            goto L_0x0045
        L_0x0031:
            java.util.logging.Logger r5 = logger
            java.lang.String r6 = "Event has no event_id"
            r5.info(r6)
            io.sentry.event.EventBuilder r5 = new io.sentry.event.EventBuilder
            r5.<init>()
            io.sentry.event.Event$Level r6 = r1.eventLevel(r4)
            io.sentry.event.EventBuilder r5 = r5.withLevel(r6)
        L_0x0045:
            io.sentry.android.event.helper.AndroidEventBuilderHelper r6 = androidHelper
            r6.helpBuildingEvent(r5)
            java.lang.String r6 = "breadcrumbs"
            boolean r7 = r2.hasKey(r6)
            java.lang.String r9 = ""
            java.lang.String r11 = "type"
            java.lang.String r12 = "message"
            if (r7 == 0) goto L_0x016d
            com.facebook.react.bridge.ReadableArray r6 = r2.getArray(r6)
            com.facebook.react.bridge.ReadableNativeArray r6 = (com.facebook.react.bridge.ReadableNativeArray) r6
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r13 = 0
        L_0x0064:
            int r14 = r6.size()
            if (r13 >= r14) goto L_0x0164
            com.facebook.react.bridge.ReadableNativeMap r14 = r6.getMap(r13)
            io.sentry.event.BreadcrumbBuilder r15 = new io.sentry.event.BreadcrumbBuilder
            r15.<init>()
            java.lang.String r8 = "category"
            boolean r16 = r14.hasKey(r8)
            if (r16 == 0) goto L_0x0082
            java.lang.String r8 = r14.getString(r8)
            r15.setCategory(r8)
        L_0x0082:
            boolean r8 = r14.hasKey(r11)
            if (r8 == 0) goto L_0x009f
            java.lang.String r8 = r14.getString(r11)
            if (r8 == 0) goto L_0x009f
            java.lang.String r8 = r14.getString(r11)
            java.lang.String r8 = r8.toUpperCase()
            io.sentry.event.Breadcrumb$Type r8 = p006io.sentry.event.Breadcrumb.Type.valueOf(r8)     // Catch:{ IllegalArgumentException -> 0x009e }
            r15.setType(r8)     // Catch:{ IllegalArgumentException -> 0x009e }
            goto L_0x009f
        L_0x009e:
        L_0x009f:
            java.lang.String r8 = "level"
            boolean r16 = r14.hasKey(r8)
            if (r16 == 0) goto L_0x00bc
            java.lang.String r16 = r14.getString(r8)
            if (r16 == 0) goto L_0x00bc
            java.lang.String r8 = r14.getString(r8)
            java.lang.String r8 = r8.toUpperCase()
            io.sentry.event.Breadcrumb$Level r8 = p006io.sentry.event.Breadcrumb.Level.valueOf(r8)     // Catch:{ IllegalArgumentException -> 0x00bc }
            r15.setLevel(r8)     // Catch:{ IllegalArgumentException -> 0x00bc }
        L_0x00bc:
            boolean r8 = r14.hasKey(r3)     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            if (r8 == 0) goto L_0x012e
            com.facebook.react.bridge.ReadableNativeMap r8 = r14.getMap(r3)     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            if (r8 == 0) goto L_0x012e
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            r8.<init>()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            com.facebook.react.bridge.ReadableNativeMap r16 = r14.getMap(r3)     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            java.util.HashMap r16 = r16.toHashMap()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            java.util.Set r16 = r16.entrySet()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            java.util.Iterator r16 = r16.iterator()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
        L_0x00dd:
            boolean r17 = r16.hasNext()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            if (r17 == 0) goto L_0x010b
            java.lang.Object r17 = r16.next()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            java.util.Map$Entry r17 = (java.util.Map.Entry) r17     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            java.lang.Object r10 = r17.getKey()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            java.lang.Object r18 = r17.getValue()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            if (r18 == 0) goto L_0x0102
            java.lang.Object r17 = r17.getValue()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            java.lang.String r17 = r17.toString()     // Catch:{ UnexpectedNativeTypeException -> 0x0134, ClassCastException -> 0x0131 }
            r24 = r17
            r17 = r3
            r3 = r24
            goto L_0x0105
        L_0x0102:
            r17 = r3
            r3 = 0
        L_0x0105:
            r8.put(r10, r3)     // Catch:{ UnexpectedNativeTypeException -> 0x013f, ClassCastException -> 0x0137 }
            r3 = r17
            goto L_0x00dd
        L_0x010b:
            r17 = r3
            boolean r3 = r8.containsKey(r0)     // Catch:{ UnexpectedNativeTypeException -> 0x013f, ClassCastException -> 0x0137 }
            if (r3 == 0) goto L_0x012a
            java.lang.Object r3 = r8.get(r0)     // Catch:{ UnexpectedNativeTypeException -> 0x013f, ClassCastException -> 0x0137 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ UnexpectedNativeTypeException -> 0x013f, ClassCastException -> 0x0137 }
            java.lang.String r10 = ".0"
            boolean r10 = r3.endsWith(r10)     // Catch:{ UnexpectedNativeTypeException -> 0x013f, ClassCastException -> 0x0137 }
            if (r10 == 0) goto L_0x0127
            java.lang.String r10 = ".0"
            java.lang.String r3 = r3.replace(r10, r9)     // Catch:{ UnexpectedNativeTypeException -> 0x013f, ClassCastException -> 0x0137 }
        L_0x0127:
            r8.put(r0, r3)     // Catch:{ UnexpectedNativeTypeException -> 0x013f, ClassCastException -> 0x0137 }
        L_0x012a:
            r15.setData(r8)     // Catch:{ UnexpectedNativeTypeException -> 0x013f, ClassCastException -> 0x0137 }
            goto L_0x0146
        L_0x012e:
            r17 = r3
            goto L_0x0146
        L_0x0131:
            r17 = r3
            goto L_0x0137
        L_0x0134:
            r17 = r3
            goto L_0x013f
        L_0x0137:
            java.util.logging.Logger r3 = logger
            java.lang.String r8 = "Discarded breadcrumb.data since it was not an object"
            r3.warning(r8)
            goto L_0x0146
        L_0x013f:
            java.util.logging.Logger r3 = logger
            java.lang.String r8 = "Discarded breadcrumb.data since it was not an object"
            r3.warning(r8)
        L_0x0146:
            boolean r3 = r14.hasKey(r12)
            if (r3 == 0) goto L_0x0154
            java.lang.String r3 = r14.getString(r12)
            r15.setMessage(r3)
            goto L_0x0157
        L_0x0154:
            r15.setMessage(r9)
        L_0x0157:
            io.sentry.event.Breadcrumb r3 = r15.build()
            r7.add(r13, r3)
            int r13 = r13 + 1
            r3 = r17
            goto L_0x0064
        L_0x0164:
            int r0 = r7.size()
            if (r0 <= 0) goto L_0x016d
            r5.withBreadcrumbs(r7)
        L_0x016d:
            boolean r0 = r2.hasKey(r12)
            if (r0 == 0) goto L_0x018d
            java.lang.String r9 = r2.getString(r12)     // Catch:{ UnexpectedNativeTypeException | ClassCastException -> 0x0177, all -> 0x0180 }
        L_0x0177:
            com.facebook.react.bridge.ReadableMap r0 = r2.getMap(r12)     // Catch:{ UnexpectedNativeTypeException | ClassCastException -> 0x018a }
            java.lang.String r9 = r0.toString()     // Catch:{ UnexpectedNativeTypeException | ClassCastException -> 0x018a }
            goto L_0x018a
        L_0x0180:
            r0 = move-exception
            r3 = r0
            com.facebook.react.bridge.ReadableMap r0 = r2.getMap(r12)     // Catch:{ UnexpectedNativeTypeException | ClassCastException -> 0x0189 }
            r0.toString()     // Catch:{ UnexpectedNativeTypeException | ClassCastException -> 0x0189 }
        L_0x0189:
            throw r3
        L_0x018a:
            r5.withMessage(r9)
        L_0x018d:
            java.lang.String r0 = "logger"
            boolean r3 = r2.hasKey(r0)
            if (r3 == 0) goto L_0x019c
            java.lang.String r0 = r2.getString(r0)
            r5.withLogger(r0)
        L_0x019c:
            java.lang.String r0 = "user"
            boolean r3 = r2.hasKey(r0)
            if (r3 == 0) goto L_0x01cc
            com.facebook.react.bridge.ReadableMap r0 = r2.getMap(r0)
            io.sentry.event.UserBuilder r0 = r1.getUserBuilder(r0)
            io.sentry.event.User r0 = r0.build()
            io.sentry.event.interfaces.UserInterface r3 = new io.sentry.event.interfaces.UserInterface
            java.lang.String r19 = r0.getId()
            java.lang.String r20 = r0.getUsername()
            r21 = 0
            java.lang.String r22 = r0.getEmail()
            java.util.Map r23 = r0.getData()
            r18 = r3
            r18.<init>(r19, r20, r21, r22, r23)
            r5.withSentryInterface(r3)
        L_0x01cc:
            java.lang.String r0 = "extra"
            boolean r3 = r4.hasKey(r0)
            if (r3 == 0) goto L_0x01fe
            com.facebook.react.bridge.ReadableNativeMap r0 = r4.getMap(r0)
            java.util.HashMap r0 = r0.toHashMap()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x01e4:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x01fe
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r6 = r3.getKey()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r3 = r3.getValue()
            r5.withExtra(r6, r3)
            goto L_0x01e4
        L_0x01fe:
            java.lang.String r0 = "fingerprint"
            boolean r0 = r2.hasKey(r0)
            if (r0 == 0) goto L_0x0229
            java.lang.String r0 = "fingerprint"
            com.facebook.react.bridge.ReadableArray r0 = r2.getArray(r0)
            java.util.ArrayList r3 = new java.util.ArrayList
            int r6 = r0.size()
            r3.<init>(r6)
            r6 = 0
        L_0x0216:
            int r7 = r0.size()
            if (r6 >= r7) goto L_0x0226
            java.lang.String r7 = r0.getString(r6)
            r3.add(r6, r7)
            int r6 = r6 + 1
            goto L_0x0216
        L_0x0226:
            r5.withFingerprint(r3)
        L_0x0229:
            java.lang.String r0 = "tags"
            boolean r0 = r4.hasKey(r0)
            if (r0 == 0) goto L_0x026a
            java.lang.String r0 = "tags"
            com.facebook.react.bridge.ReadableNativeMap r0 = r4.getMap(r0)
            java.util.HashMap r0 = r0.toHashMap()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0243:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x026a
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getValue()
            if (r4 == 0) goto L_0x025e
            java.lang.Object r4 = r3.getValue()
            java.lang.String r4 = r4.toString()
            goto L_0x0260
        L_0x025e:
            java.lang.String r4 = "INVALID_TAG"
        L_0x0260:
            java.lang.Object r3 = r3.getKey()
            java.lang.String r3 = (java.lang.String) r3
            r5.withTag(r3, r4)
            goto L_0x0243
        L_0x026a:
            java.lang.String r0 = "exception"
            boolean r0 = r2.hasKey(r0)
            if (r0 == 0) goto L_0x02bd
            java.lang.String r0 = "exception"
            com.facebook.react.bridge.ReadableMap r0 = r2.getMap(r0)
            java.lang.String r3 = "values"
            com.facebook.react.bridge.ReadableArray r0 = r0.getArray(r3)
            com.facebook.react.bridge.ReadableNativeArray r0 = (com.facebook.react.bridge.ReadableNativeArray) r0
            r3 = 0
            com.facebook.react.bridge.ReadableNativeMap r0 = r0.getMap(r3)
            java.lang.String r4 = "stacktrace"
            boolean r4 = r0.hasKey(r4)
            if (r4 == 0) goto L_0x02be
            java.lang.String r4 = "stacktrace"
            com.facebook.react.bridge.ReadableNativeMap r4 = r0.getMap(r4)
            java.lang.String r6 = "frames"
            com.facebook.react.bridge.ReadableArray r4 = r4.getArray(r6)
            com.facebook.react.bridge.ReadableNativeArray r4 = (com.facebook.react.bridge.ReadableNativeArray) r4
            java.lang.String r6 = "value"
            boolean r6 = r0.hasKey(r6)
            if (r6 == 0) goto L_0x02b1
            java.lang.String r6 = r0.getString(r11)
            java.lang.String r7 = "value"
            java.lang.String r0 = r0.getString(r7)
            addExceptionInterface(r5, r6, r0, r4)
            goto L_0x02be
        L_0x02b1:
            java.lang.String r6 = r0.getString(r11)
            java.lang.String r0 = r0.getString(r11)
            addExceptionInterface(r5, r6, r0, r4)
            goto L_0x02be
        L_0x02bd:
            r3 = 0
        L_0x02be:
            java.lang.String r0 = "environment"
            boolean r0 = r2.hasKey(r0)
            if (r0 == 0) goto L_0x02cf
            java.lang.String r0 = "environment"
            java.lang.String r0 = r2.getString(r0)
            r5.withEnvironment(r0)
        L_0x02cf:
            java.lang.String r0 = "release"
            boolean r0 = r2.hasKey(r0)
            if (r0 == 0) goto L_0x02e2
            java.lang.String r0 = "release"
            java.lang.String r0 = r2.getString(r0)
            r5.withRelease(r0)
            r0 = 0
            goto L_0x02e6
        L_0x02e2:
            r0 = 0
            r5.withRelease(r0)
        L_0x02e6:
            java.lang.String r4 = "dist"
            boolean r4 = r2.hasKey(r4)
            if (r4 == 0) goto L_0x02f8
            java.lang.String r0 = "dist"
            java.lang.String r0 = r2.getString(r0)
            r5.withDist(r0)
            goto L_0x02fb
        L_0x02f8:
            r5.withDist(r0)
        L_0x02fb:
            io.sentry.event.Event r0 = r5.build()
            java.lang.String r4 = "sdk"
            boolean r4 = r2.hasKey(r4)
            if (r4 == 0) goto L_0x0348
            java.lang.String r4 = "sdk"
            com.facebook.react.bridge.ReadableMap r2 = r2.getMap(r4)
            com.facebook.react.bridge.ReadableNativeMap r2 = (com.facebook.react.bridge.ReadableNativeMap) r2
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.lang.String r5 = "integrations"
            boolean r5 = r2.hasKey(r5)
            if (r5 == 0) goto L_0x0334
            java.lang.String r5 = "integrations"
            com.facebook.react.bridge.ReadableArray r5 = r2.getArray(r5)
            com.facebook.react.bridge.ReadableNativeArray r5 = (com.facebook.react.bridge.ReadableNativeArray) r5
        L_0x0324:
            int r6 = r5.size()
            if (r3 >= r6) goto L_0x0334
            java.lang.String r6 = r5.getString(r3)
            r4.add(r6)
            int r3 = r3 + 1
            goto L_0x0324
        L_0x0334:
            io.sentry.event.Sdk r3 = new io.sentry.event.Sdk
            java.lang.String r5 = "name"
            java.lang.String r5 = r2.getString(r5)
            java.lang.String r6 = "version"
            java.lang.String r2 = r2.getString(r6)
            r3.<init>(r5, r2, r4)
            r0.setSdk(r3)
        L_0x0348:
            p006io.sentry.Sentry.capture(r0)
            r0 = 1
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r2 = r27
            r2.resolve(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p006io.sentry.RNSentryModule.sendEvent(com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Promise):void");
    }

    private UserBuilder getUserBuilder(ReadableMap readableMap) {
        UserBuilder userBuilder = new UserBuilder();
        String str = "email";
        if (readableMap.hasKey(str)) {
            userBuilder.setEmail(readableMap.getString(str));
        }
        String str2 = "userID";
        if (readableMap.hasKey(str2)) {
            userBuilder.setId(readableMap.getString(str2));
        } else {
            String str3 = "userId";
            if (readableMap.hasKey(str3)) {
                userBuilder.setId(readableMap.getString(str3));
            } else {
                String str4 = "id";
                if (readableMap.hasKey(str4)) {
                    userBuilder.setId(readableMap.getString(str4));
                }
            }
        }
        String str5 = "username";
        if (readableMap.hasKey(str5)) {
            userBuilder.setUsername(readableMap.getString(str5));
        }
        String str6 = "extra";
        if (readableMap.hasKey(str6)) {
            userBuilder.setData(((ReadableNativeMap) readableMap.getMap(str6)).toHashMap());
        }
        return userBuilder;
    }

    private static void addExceptionInterface(EventBuilder eventBuilder, String str, String str2, ReadableNativeArray readableNativeArray) {
        StackTraceInterface stackTraceInterface = new StackTraceInterface(convertToNativeStacktrace(readableNativeArray));
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(new SentryException(str2, str, "", stackTraceInterface));
        eventBuilder.withSentryInterface(new ExceptionInterface((Deque<SentryException>) arrayDeque));
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static p006io.sentry.event.interfaces.SentryStackTraceElement[] convertToNativeStacktrace(com.facebook.react.bridge.ReadableNativeArray r15) {
        /*
            java.util.ArrayDeque r0 = new java.util.ArrayDeque
            r0.<init>()
            r1 = 0
            r2 = 0
        L_0x0007:
            int r3 = r15.size()
            if (r2 >= r3) goto L_0x00d1
            com.facebook.react.bridge.ReadableNativeMap r3 = r15.getMap(r2)
            java.lang.String r4 = "file"
            boolean r5 = r3.hasKey(r4)
            java.lang.String r6 = ""
            if (r5 == 0) goto L_0x0021
            java.lang.String r4 = r3.getString(r4)
        L_0x001f:
            r13 = r4
            goto L_0x002f
        L_0x0021:
            java.lang.String r4 = "filename"
            boolean r5 = r3.hasKey(r4)
            if (r5 == 0) goto L_0x002e
            java.lang.String r4 = r3.getString(r4)
            goto L_0x001f
        L_0x002e:
            r13 = r6
        L_0x002f:
            java.lang.String r4 = "methodName"
            boolean r5 = r3.hasKey(r4)
            if (r5 == 0) goto L_0x003c
            java.lang.String r6 = r3.getString(r4)
            goto L_0x0048
        L_0x003c:
            java.lang.String r4 = "function"
            boolean r5 = r3.hasKey(r4)
            if (r5 == 0) goto L_0x0048
            java.lang.String r6 = r3.getString(r4)
        L_0x0048:
            r9 = r6
            java.lang.String r4 = "lineNumber"
            boolean r5 = r3.hasKey(r4)
            if (r5 == 0) goto L_0x0065
            boolean r5 = r3.isNull(r4)
            if (r5 != 0) goto L_0x0065
            com.facebook.react.bridge.ReadableType r5 = r3.getType(r4)
            com.facebook.react.bridge.ReadableType r6 = com.facebook.react.bridge.ReadableType.Number
            if (r5 != r6) goto L_0x0065
            int r4 = r3.getInt(r4)
        L_0x0063:
            r11 = r4
            goto L_0x0081
        L_0x0065:
            java.lang.String r4 = "lineno"
            boolean r5 = r3.hasKey(r4)
            if (r5 == 0) goto L_0x0080
            boolean r5 = r3.isNull(r4)
            if (r5 != 0) goto L_0x0080
            com.facebook.react.bridge.ReadableType r5 = r3.getType(r4)
            com.facebook.react.bridge.ReadableType r6 = com.facebook.react.bridge.ReadableType.Number
            if (r5 != r6) goto L_0x0080
            int r4 = r3.getInt(r4)
            goto L_0x0063
        L_0x0080:
            r11 = 0
        L_0x0081:
            java.lang.String r4 = "column"
            boolean r5 = r3.hasKey(r4)
            if (r5 == 0) goto L_0x009c
            boolean r5 = r3.isNull(r4)
            if (r5 != 0) goto L_0x009c
            com.facebook.react.bridge.ReadableType r5 = r3.getType(r4)
            com.facebook.react.bridge.ReadableType r6 = com.facebook.react.bridge.ReadableType.Number
            if (r5 != r6) goto L_0x009c
            int r4 = r3.getInt(r4)
            goto L_0x00b8
        L_0x009c:
            java.lang.String r4 = "colno"
            boolean r5 = r3.hasKey(r4)
            if (r5 == 0) goto L_0x00b7
            boolean r5 = r3.isNull(r4)
            if (r5 != 0) goto L_0x00b7
            com.facebook.react.bridge.ReadableType r5 = r3.getType(r4)
            com.facebook.react.bridge.ReadableType r6 = com.facebook.react.bridge.ReadableType.Number
            if (r5 != r6) goto L_0x00b7
            int r4 = r3.getInt(r4)
            goto L_0x00b8
        L_0x00b7:
            r4 = 0
        L_0x00b8:
            io.sentry.event.interfaces.SentryStackTraceElement r5 = new io.sentry.event.interfaces.SentryStackTraceElement
            java.lang.String r10 = stackFrameToModuleId(r3)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r4)
            java.lang.String r8 = ""
            java.lang.String r14 = "javascript"
            r7 = r5
            r7.<init>(r8, r9, r10, r11, r12, r13, r14)
            r0.add(r5)
            int r2 = r2 + 1
            goto L_0x0007
        L_0x00d1:
            int r15 = r0.size()
            io.sentry.event.interfaces.SentryStackTraceElement[] r15 = new p006io.sentry.event.interfaces.SentryStackTraceElement[r15]
            java.util.Iterator r0 = r0.descendingIterator()
        L_0x00db:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00ec
            java.lang.Object r2 = r0.next()
            io.sentry.event.interfaces.SentryStackTraceElement r2 = (p006io.sentry.event.interfaces.SentryStackTraceElement) r2
            r15[r1] = r2
            int r1 = r1 + 1
            goto L_0x00db
        L_0x00ec:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: p006io.sentry.RNSentryModule.convertToNativeStacktrace(com.facebook.react.bridge.ReadableNativeArray):io.sentry.event.interfaces.SentryStackTraceElement[]");
    }

    private static String stackFrameToModuleId(ReadableMap readableMap) {
        String str = UriUtil.LOCAL_FILE_SCHEME;
        if (readableMap.hasKey(str) && !readableMap.isNull(str) && readableMap.getType(str) == ReadableType.String) {
            Matcher matcher = mJsModuleIdPattern.matcher(readableMap.getString(str));
            if (matcher.find()) {
                StringBuilder sb = new StringBuilder();
                sb.append(matcher.group(1));
                sb.append(":");
                return sb.toString();
            }
        }
        return "";
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException unused) {
            logger.info("Error getting package info.");
            return null;
        }
    }

    private Level eventLevel(ReadableNativeMap readableNativeMap) {
        String str = "level";
        String string = readableNativeMap.hasKey(str) ? readableNativeMap.getString(str) : "";
        char c = 65535;
        switch (string.hashCode()) {
            case 3237038:
                if (string.equals("info")) {
                    c = 2;
                    break;
                }
                break;
            case 95458899:
                if (string.equals("debug")) {
                    c = 3;
                    break;
                }
                break;
            case 97203460:
                if (string.equals("fatal")) {
                    c = 0;
                    break;
                }
                break;
            case 1124446108:
                if (string.equals("warning")) {
                    c = 1;
                    break;
                }
                break;
        }
        if (c == 0) {
            return Level.FATAL;
        }
        if (c == 1) {
            return Level.WARNING;
        }
        if (c == 2) {
            return Level.INFO;
        }
        if (c != 3) {
            return Level.ERROR;
        }
        return Level.DEBUG;
    }

    private java.util.logging.Level logLevel(int i) {
        if (i == 1) {
            return java.util.logging.Level.SEVERE;
        }
        if (i == 2) {
            return java.util.logging.Level.INFO;
        }
        if (i != 3) {
            return java.util.logging.Level.OFF;
        }
        return java.util.logging.Level.ALL;
    }
}
