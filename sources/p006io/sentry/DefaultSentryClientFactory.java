package p006io.sentry;

import java.io.File;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p006io.sentry.buffer.Buffer;
import p006io.sentry.buffer.DiskBuffer;
import p006io.sentry.config.Lookup;
import p006io.sentry.connection.AsyncConnection;
import p006io.sentry.connection.Connection;
import p006io.sentry.connection.HttpConnection;
import p006io.sentry.connection.OutputStreamConnection;
import p006io.sentry.connection.ProxyAuthenticator;
import p006io.sentry.connection.RandomEventSampler;
import p006io.sentry.context.ContextManager;
import p006io.sentry.context.ThreadLocalContextManager;
import p006io.sentry.dsn.Dsn;
import p006io.sentry.event.interfaces.DebugMetaInterface;
import p006io.sentry.event.interfaces.ExceptionInterface;
import p006io.sentry.event.interfaces.HttpInterface;
import p006io.sentry.event.interfaces.MessageInterface;
import p006io.sentry.event.interfaces.StackTraceInterface;
import p006io.sentry.event.interfaces.UserInterface;
import p006io.sentry.jvmti.FrameCache;
import p006io.sentry.marshaller.Marshaller;
import p006io.sentry.marshaller.json.DebugMetaInterfaceBinding;
import p006io.sentry.marshaller.json.ExceptionInterfaceBinding;
import p006io.sentry.marshaller.json.HttpInterfaceBinding;
import p006io.sentry.marshaller.json.JsonMarshaller;
import p006io.sentry.marshaller.json.MessageInterfaceBinding;
import p006io.sentry.marshaller.json.StackTraceInterfaceBinding;
import p006io.sentry.marshaller.json.UserInterfaceBinding;
import p006io.sentry.util.Util;

/* renamed from: io.sentry.DefaultSentryClientFactory */
public class DefaultSentryClientFactory extends SentryClientFactory {
    public static final String ASYNC_GRACEFUL_SHUTDOWN_OPTION = "async.gracefulshutdown";
    public static final String ASYNC_OPTION = "async";
    public static final String ASYNC_PRIORITY_OPTION = "async.priority";
    public static final String ASYNC_QUEUE_DISCARDNEW = "discardnew";
    public static final String ASYNC_QUEUE_DISCARDOLD = "discardold";
    public static final String ASYNC_QUEUE_OVERFLOW_DEFAULT = "discardold";
    public static final String ASYNC_QUEUE_OVERFLOW_OPTION = "async.queue.overflow";
    public static final String ASYNC_QUEUE_SIZE_OPTION = "async.queuesize";
    public static final String ASYNC_QUEUE_SYNC = "sync";
    public static final long ASYNC_SHUTDOWN_TIMEOUT_DEFAULT = TimeUnit.SECONDS.toMillis(1);
    public static final String ASYNC_SHUTDOWN_TIMEOUT_OPTION = "async.shutdowntimeout";
    public static final String ASYNC_THREADS_OPTION = "async.threads";
    public static final String BUFFER_DIR_OPTION = "buffer.dir";
    public static final boolean BUFFER_ENABLED_DEFAULT = true;
    public static final String BUFFER_ENABLED_OPTION = "buffer.enabled";
    public static final long BUFFER_FLUSHTIME_DEFAULT = 60000;
    public static final String BUFFER_FLUSHTIME_OPTION = "buffer.flushtime";
    public static final String BUFFER_GRACEFUL_SHUTDOWN_OPTION = "buffer.gracefulshutdown";
    public static final long BUFFER_SHUTDOWN_TIMEOUT_DEFAULT = TimeUnit.SECONDS.toMillis(1);
    public static final String BUFFER_SHUTDOWN_TIMEOUT_OPTION = "buffer.shutdowntimeout";
    public static final int BUFFER_SIZE_DEFAULT = 10;
    public static final String BUFFER_SIZE_OPTION = "buffer.size";
    public static final String COMPRESSION_OPTION = "compression";
    public static final int CONNECTION_TIMEOUT_DEFAULT = ((int) TimeUnit.SECONDS.toMillis(1));
    public static final String CONNECTION_TIMEOUT_OPTION = "timeout";
    public static final String DIST_OPTION = "dist";
    public static final String ENVIRONMENT_OPTION = "environment";
    @Deprecated
    public static final String EXTRATAGS_OPTION = "extratags";
    public static final String EXTRA_OPTION = "extra";
    private static final String FALSE = Boolean.FALSE.toString();
    public static final String HIDE_COMMON_FRAMES_OPTION = "stacktrace.hidecommon";
    public static final String HTTP_PROXY_HOST_OPTION = "http.proxy.host";
    public static final String HTTP_PROXY_PASS_OPTION = "http.proxy.password";
    public static final int HTTP_PROXY_PORT_DEFAULT = 80;
    public static final String HTTP_PROXY_PORT_OPTION = "http.proxy.port";
    public static final String HTTP_PROXY_USER_OPTION = "http.proxy.user";
    public static final String IN_APP_FRAMES_OPTION = "stacktrace.app.packages";
    public static final String MAX_MESSAGE_LENGTH_OPTION = "maxmessagelength";
    public static final String MDCTAGS_OPTION = "mdctags";
    public static final String NAIVE_PROTOCOL = "naive";
    public static final int QUEUE_SIZE_DEFAULT = 50;
    public static final int READ_TIMEOUT_DEFAULT = ((int) TimeUnit.SECONDS.toMillis(5));
    public static final String READ_TIMEOUT_OPTION = "readtimeout";
    private static final Map<String, RejectedExecutionHandler> REJECT_EXECUTION_HANDLERS = new HashMap();
    public static final String RELEASE_OPTION = "release";
    public static final String SAMPLE_RATE_OPTION = "sample.rate";
    public static final String SERVERNAME_OPTION = "servername";
    public static final String TAGS_OPTION = "tags";
    public static final String UNCAUGHT_HANDLER_ENABLED_OPTION = "uncaught.handler.enabled";
    private static final Logger logger = LoggerFactory.getLogger(DefaultSentryClientFactory.class);

    /* renamed from: io.sentry.DefaultSentryClientFactory$DaemonThreadFactory */
    protected static final class DaemonThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final int priority;
        private final AtomicInteger threadNumber;

        private DaemonThreadFactory(int i) {
            this.threadNumber = new AtomicInteger(1);
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            StringBuilder sb = new StringBuilder();
            sb.append("sentry-pool-");
            sb.append(POOL_NUMBER.getAndIncrement());
            sb.append("-thread-");
            this.namePrefix = sb.toString();
            this.priority = i;
        }

        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.group;
            StringBuilder sb = new StringBuilder();
            sb.append(this.namePrefix);
            sb.append(this.threadNumber.getAndIncrement());
            Thread thread = new Thread(threadGroup, runnable, sb.toString(), 0);
            if (!thread.isDaemon()) {
                thread.setDaemon(true);
            }
            int priority2 = thread.getPriority();
            int i = this.priority;
            if (priority2 != i) {
                thread.setPriority(i);
            }
            return thread;
        }
    }

    static {
        REJECT_EXECUTION_HANDLERS.put("sync", new CallerRunsPolicy());
        REJECT_EXECUTION_HANDLERS.put(ASYNC_QUEUE_DISCARDNEW, new DiscardPolicy());
        REJECT_EXECUTION_HANDLERS.put("discardold", new DiscardOldestPolicy());
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p006io.sentry.SentryClient createSentryClient(p006io.sentry.dsn.Dsn r5) {
        /*
            r4 = this;
            io.sentry.SentryClient r0 = new io.sentry.SentryClient     // Catch:{ Exception -> 0x0038 }
            io.sentry.connection.Connection r1 = r4.createConnection(r5)     // Catch:{ Exception -> 0x0038 }
            io.sentry.context.ContextManager r2 = r4.getContextManager(r5)     // Catch:{ Exception -> 0x0038 }
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x0038 }
            java.lang.String r1 = "javax.servlet.ServletRequestListener"
            r2 = 0
            java.lang.Class r3 = r4.getClass()     // Catch:{ ClassNotFoundException -> 0x0024 }
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0024 }
            java.lang.Class.forName(r1, r2, r3)     // Catch:{ ClassNotFoundException -> 0x0024 }
            io.sentry.event.helper.HttpEventBuilderHelper r1 = new io.sentry.event.helper.HttpEventBuilderHelper     // Catch:{ ClassNotFoundException -> 0x0024 }
            r1.<init>()     // Catch:{ ClassNotFoundException -> 0x0024 }
            r0.addBuilderHelper(r1)     // Catch:{ ClassNotFoundException -> 0x0024 }
            goto L_0x002b
        L_0x0024:
            org.slf4j.Logger r1 = logger     // Catch:{ Exception -> 0x0038 }
            java.lang.String r2 = "The current environment doesn't provide access to servlets, or provides an unsupported version."
            r1.debug(r2)     // Catch:{ Exception -> 0x0038 }
        L_0x002b:
            io.sentry.event.helper.ContextBuilderHelper r1 = new io.sentry.event.helper.ContextBuilderHelper     // Catch:{ Exception -> 0x0038 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0038 }
            r0.addBuilderHelper(r1)     // Catch:{ Exception -> 0x0038 }
            io.sentry.SentryClient r5 = r4.configureSentryClient(r0, r5)     // Catch:{ Exception -> 0x0038 }
            return r5
        L_0x0038:
            r5 = move-exception
            org.slf4j.Logger r0 = logger
            java.lang.String r1 = "Failed to initialize sentry, falling back to no-op client"
            r0.error(r1, r5)
            io.sentry.SentryClient r5 = new io.sentry.SentryClient
            io.sentry.connection.NoopConnection r0 = new io.sentry.connection.NoopConnection
            r0.<init>()
            io.sentry.context.ThreadLocalContextManager r1 = new io.sentry.context.ThreadLocalContextManager
            r1.<init>()
            r5.<init>(r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p006io.sentry.DefaultSentryClientFactory.createSentryClient(io.sentry.dsn.Dsn):io.sentry.SentryClient");
    }

    /* access modifiers changed from: protected */
    public SentryClient configureSentryClient(SentryClient sentryClient, Dsn dsn) {
        String release = getRelease(dsn);
        if (release != null) {
            sentryClient.setRelease(release);
        }
        String dist = getDist(dsn);
        if (dist != null) {
            sentryClient.setDist(dist);
        }
        String environment = getEnvironment(dsn);
        if (environment != null) {
            sentryClient.setEnvironment(environment);
        }
        String serverName = getServerName(dsn);
        if (serverName != null) {
            sentryClient.setServerName(serverName);
        }
        Map tags = getTags(dsn);
        if (!tags.isEmpty()) {
            for (Entry entry : tags.entrySet()) {
                sentryClient.addTag((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Set<String> mdcTags = getMdcTags(dsn);
        if (!mdcTags.isEmpty()) {
            for (String addMdcTag : mdcTags) {
                sentryClient.addMdcTag(addMdcTag);
            }
        }
        Map extra = getExtra(dsn);
        if (!extra.isEmpty()) {
            for (Entry entry2 : extra.entrySet()) {
                sentryClient.addExtra((String) entry2.getKey(), entry2.getValue());
            }
        }
        if (getUncaughtHandlerEnabled(dsn)) {
            sentryClient.setupUncaughtExceptionHandler();
        }
        for (String addAppPackage : getInAppFrames(dsn)) {
            FrameCache.addAppPackage(addAppPackage);
        }
        return sentryClient;
    }

    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r2v0, types: [io.sentry.connection.Connection] */
    /* JADX WARNING: type inference failed for: r2v1, types: [io.sentry.connection.Connection] */
    /* JADX WARNING: type inference failed for: r2v2, types: [io.sentry.connection.Connection] */
    /* JADX WARNING: type inference failed for: r2v4, types: [io.sentry.connection.Connection] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v8, types: [io.sentry.connection.Connection] */
    /* JADX WARNING: type inference failed for: r0v12, types: [io.sentry.connection.NoopConnection] */
    /* JADX WARNING: type inference failed for: r0v14, types: [io.sentry.connection.Connection] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r0v16 */
    /* JADX WARNING: type inference failed for: r0v17 */
    /* JADX WARNING: type inference failed for: r0v18 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p006io.sentry.connection.Connection createConnection(p006io.sentry.dsn.Dsn r11) {
        /*
            r10 = this;
            java.lang.String r0 = r11.getProtocol()
            java.lang.String r1 = "http"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 != 0) goto L_0x005a
            java.lang.String r1 = "https"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x0015
            goto L_0x005a
        L_0x0015:
            java.lang.String r1 = "out"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x0029
            org.slf4j.Logger r0 = logger
            java.lang.String r1 = "Using StdOut to send events."
            r0.debug(r1)
            io.sentry.connection.Connection r0 = r10.createStdOutConnection(r11)
            goto L_0x0069
        L_0x0029:
            java.lang.String r1 = "noop"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x003e
            org.slf4j.Logger r0 = logger
            java.lang.String r1 = "Using noop to send events."
            r0.debug(r1)
            io.sentry.connection.NoopConnection r0 = new io.sentry.connection.NoopConnection
            r0.<init>()
            goto L_0x0069
        L_0x003e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Couldn't create a connection for the protocol '"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = "'"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r11.<init>(r0)
            throw r11
        L_0x005a:
            org.slf4j.Logger r1 = logger
            java.lang.String r0 = r0.toUpperCase()
            java.lang.String r2 = "Using an {} connection to Sentry."
            r1.debug(r2, r0)
            io.sentry.connection.Connection r0 = r10.createHttpConnection(r11)
        L_0x0069:
            r2 = r0
            r0 = 0
            boolean r1 = r10.getBufferEnabled(r11)
            if (r1 == 0) goto L_0x0093
            io.sentry.buffer.Buffer r3 = r10.getBuffer(r11)
            if (r3 == 0) goto L_0x0093
            long r4 = r10.getBufferFlushtime(r11)
            boolean r6 = r10.getBufferedConnectionGracefulShutdownEnabled(r11)
            long r0 = r10.getBufferedConnectionShutdownTimeout(r11)
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            io.sentry.connection.BufferedConnection r9 = new io.sentry.connection.BufferedConnection
            long r7 = r0.longValue()
            r1 = r9
            r1.<init>(r2, r3, r4, r6, r7)
            r0 = r9
            r2 = r0
        L_0x0093:
            boolean r1 = r10.getAsyncEnabled(r11)
            if (r1 == 0) goto L_0x009d
            io.sentry.connection.Connection r2 = r10.createAsyncConnection(r11, r2)
        L_0x009d:
            if (r0 == 0) goto L_0x00a3
            io.sentry.connection.Connection r2 = r0.wrapConnectionWithBufferWriter(r2)
        L_0x00a3:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p006io.sentry.DefaultSentryClientFactory.createConnection(io.sentry.dsn.Dsn):io.sentry.connection.Connection");
    }

    /* access modifiers changed from: protected */
    public Connection createAsyncConnection(Dsn dsn, Connection connection) {
        BlockingQueue blockingQueue;
        int asyncThreads = getAsyncThreads(dsn);
        int asyncPriority = getAsyncPriority(dsn);
        int asyncQueueSize = getAsyncQueueSize(dsn);
        if (asyncQueueSize == -1) {
            blockingQueue = new LinkedBlockingDeque();
        } else {
            blockingQueue = new LinkedBlockingDeque(asyncQueueSize);
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(asyncThreads, asyncThreads, 0, TimeUnit.MILLISECONDS, blockingQueue, new DaemonThreadFactory(asyncPriority), getRejectedExecutionHandler(dsn));
        AsyncConnection asyncConnection = new AsyncConnection(connection, threadPoolExecutor, getAsyncGracefulShutdownEnabled(dsn), getAsyncShutdownTimeout(dsn));
        return asyncConnection;
    }

    /* access modifiers changed from: protected */
    public Connection createHttpConnection(Dsn dsn) {
        Proxy proxy;
        URL sentryApiUrl = HttpConnection.getSentryApiUrl(dsn.getUri(), dsn.getProjectId());
        String proxyHost = getProxyHost(dsn);
        String proxyUser = getProxyUser(dsn);
        String proxyPass = getProxyPass(dsn);
        int proxyPort = getProxyPort(dsn);
        if (proxyHost != null) {
            Proxy proxy2 = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            if (!(proxyUser == null || proxyPass == null)) {
                Authenticator.setDefault(new ProxyAuthenticator(proxyUser, proxyPass));
            }
            proxy = proxy2;
        } else {
            proxy = null;
        }
        Double sampleRate = getSampleRate(dsn);
        HttpConnection httpConnection = new HttpConnection(sentryApiUrl, dsn.getPublicKey(), dsn.getSecretKey(), proxy, sampleRate != null ? new RandomEventSampler(sampleRate.doubleValue()) : null);
        httpConnection.setMarshaller(createMarshaller(dsn));
        httpConnection.setConnectionTimeout(getTimeout(dsn));
        httpConnection.setReadTimeout(getReadTimeout(dsn));
        httpConnection.setBypassSecurity(getBypassSecurityEnabled(dsn));
        return httpConnection;
    }

    /* access modifiers changed from: protected */
    public Connection createStdOutConnection(Dsn dsn) {
        OutputStreamConnection outputStreamConnection = new OutputStreamConnection(System.out);
        outputStreamConnection.setMarshaller(createMarshaller(dsn));
        return outputStreamConnection;
    }

    /* access modifiers changed from: protected */
    public Marshaller createMarshaller(Dsn dsn) {
        int maxMessageLength = getMaxMessageLength(dsn);
        JsonMarshaller createJsonMarshaller = createJsonMarshaller(maxMessageLength);
        StackTraceInterfaceBinding stackTraceInterfaceBinding = new StackTraceInterfaceBinding();
        stackTraceInterfaceBinding.setRemoveCommonFramesWithEnclosing(getHideCommonFramesEnabled(dsn));
        stackTraceInterfaceBinding.setInAppFrames(getInAppFrames(dsn));
        createJsonMarshaller.addInterfaceBinding(StackTraceInterface.class, stackTraceInterfaceBinding);
        createJsonMarshaller.addInterfaceBinding(ExceptionInterface.class, new ExceptionInterfaceBinding(stackTraceInterfaceBinding));
        createJsonMarshaller.addInterfaceBinding(MessageInterface.class, new MessageInterfaceBinding(maxMessageLength));
        createJsonMarshaller.addInterfaceBinding(UserInterface.class, new UserInterfaceBinding());
        createJsonMarshaller.addInterfaceBinding(DebugMetaInterface.class, new DebugMetaInterfaceBinding());
        createJsonMarshaller.addInterfaceBinding(HttpInterface.class, new HttpInterfaceBinding());
        createJsonMarshaller.setCompression(getCompressionEnabled(dsn));
        return createJsonMarshaller;
    }

    /* access modifiers changed from: protected */
    public JsonMarshaller createJsonMarshaller(int i) {
        return new JsonMarshaller(i);
    }

    /* access modifiers changed from: protected */
    public ContextManager getContextManager(Dsn dsn) {
        return new ThreadLocalContextManager();
    }

    /* access modifiers changed from: protected */
    public Collection<String> getInAppFrames(Dsn dsn) {
        String[] split;
        String lookup = Lookup.lookup(IN_APP_FRAMES_OPTION, dsn);
        if (Util.isNullOrEmpty(lookup)) {
            if (lookup == null) {
                logger.warn("No 'stacktrace.app.packages' was configured, this option is highly recommended as it affects stacktrace grouping and display on Sentry. See documentation: https://docs.sentry.io/clients/java/config/#in-application-stack-frames");
            }
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : lookup.split(",")) {
            if (!str.trim().equals("")) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public boolean getAsyncEnabled(Dsn dsn) {
        return !FALSE.equalsIgnoreCase(Lookup.lookup("async", dsn));
    }

    /* access modifiers changed from: protected */
    public RejectedExecutionHandler getRejectedExecutionHandler(Dsn dsn) {
        String lookup = Lookup.lookup(ASYNC_QUEUE_OVERFLOW_OPTION, dsn);
        String lowerCase = !Util.isNullOrEmpty(lookup) ? lookup.toLowerCase() : "discardold";
        RejectedExecutionHandler rejectedExecutionHandler = (RejectedExecutionHandler) REJECT_EXECUTION_HANDLERS.get(lowerCase);
        if (rejectedExecutionHandler != null) {
            return rejectedExecutionHandler;
        }
        String arrays = Arrays.toString(REJECT_EXECUTION_HANDLERS.keySet().toArray());
        StringBuilder sb = new StringBuilder();
        sb.append("RejectedExecutionHandler not found: '");
        sb.append(lowerCase);
        sb.append("', valid choices are: ");
        sb.append(arrays);
        throw new RuntimeException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public long getBufferedConnectionShutdownTimeout(Dsn dsn) {
        return Util.parseLong(Lookup.lookup(BUFFER_SHUTDOWN_TIMEOUT_OPTION, dsn), Long.valueOf(BUFFER_SHUTDOWN_TIMEOUT_DEFAULT)).longValue();
    }

    /* access modifiers changed from: protected */
    public boolean getBufferedConnectionGracefulShutdownEnabled(Dsn dsn) {
        return !FALSE.equalsIgnoreCase(Lookup.lookup(BUFFER_GRACEFUL_SHUTDOWN_OPTION, dsn));
    }

    /* access modifiers changed from: protected */
    public long getBufferFlushtime(Dsn dsn) {
        return Util.parseLong(Lookup.lookup(BUFFER_FLUSHTIME_OPTION, dsn), Long.valueOf(BUFFER_FLUSHTIME_DEFAULT)).longValue();
    }

    /* access modifiers changed from: protected */
    public long getAsyncShutdownTimeout(Dsn dsn) {
        return Util.parseLong(Lookup.lookup(ASYNC_SHUTDOWN_TIMEOUT_OPTION, dsn), Long.valueOf(ASYNC_SHUTDOWN_TIMEOUT_DEFAULT)).longValue();
    }

    /* access modifiers changed from: protected */
    public boolean getAsyncGracefulShutdownEnabled(Dsn dsn) {
        return !FALSE.equalsIgnoreCase(Lookup.lookup(ASYNC_GRACEFUL_SHUTDOWN_OPTION, dsn));
    }

    /* access modifiers changed from: protected */
    public int getAsyncQueueSize(Dsn dsn) {
        return Util.parseInteger(Lookup.lookup(ASYNC_QUEUE_SIZE_OPTION, dsn), Integer.valueOf(50)).intValue();
    }

    /* access modifiers changed from: protected */
    public int getAsyncPriority(Dsn dsn) {
        return Util.parseInteger(Lookup.lookup(ASYNC_PRIORITY_OPTION, dsn), Integer.valueOf(1)).intValue();
    }

    /* access modifiers changed from: protected */
    public int getAsyncThreads(Dsn dsn) {
        return Util.parseInteger(Lookup.lookup(ASYNC_THREADS_OPTION, dsn), Integer.valueOf(Runtime.getRuntime().availableProcessors())).intValue();
    }

    /* access modifiers changed from: protected */
    public boolean getBypassSecurityEnabled(Dsn dsn) {
        return dsn.getProtocolSettings().contains(NAIVE_PROTOCOL);
    }

    /* access modifiers changed from: protected */
    public Double getSampleRate(Dsn dsn) {
        return Util.parseDouble(Lookup.lookup(SAMPLE_RATE_OPTION, dsn), null);
    }

    /* access modifiers changed from: protected */
    public int getProxyPort(Dsn dsn) {
        return Util.parseInteger(Lookup.lookup(HTTP_PROXY_PORT_OPTION, dsn), Integer.valueOf(80)).intValue();
    }

    /* access modifiers changed from: protected */
    public String getProxyHost(Dsn dsn) {
        return Lookup.lookup(HTTP_PROXY_HOST_OPTION, dsn);
    }

    /* access modifiers changed from: protected */
    public String getProxyUser(Dsn dsn) {
        return Lookup.lookup(HTTP_PROXY_USER_OPTION, dsn);
    }

    /* access modifiers changed from: protected */
    public String getProxyPass(Dsn dsn) {
        return Lookup.lookup(HTTP_PROXY_PASS_OPTION, dsn);
    }

    /* access modifiers changed from: protected */
    public String getRelease(Dsn dsn) {
        return Lookup.lookup("release", dsn);
    }

    /* access modifiers changed from: protected */
    public String getDist(Dsn dsn) {
        return Lookup.lookup("dist", dsn);
    }

    /* access modifiers changed from: protected */
    public String getEnvironment(Dsn dsn) {
        return Lookup.lookup("environment", dsn);
    }

    /* access modifiers changed from: protected */
    public String getServerName(Dsn dsn) {
        return Lookup.lookup(SERVERNAME_OPTION, dsn);
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getTags(Dsn dsn) {
        return Util.parseTags(Lookup.lookup("tags", dsn));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public Set<String> getExtraTags(Dsn dsn) {
        return getMdcTags(dsn);
    }

    /* access modifiers changed from: protected */
    public Set<String> getMdcTags(Dsn dsn) {
        String lookup = Lookup.lookup(MDCTAGS_OPTION, dsn);
        if (Util.isNullOrEmpty(lookup)) {
            lookup = Lookup.lookup(EXTRATAGS_OPTION, dsn);
            if (!Util.isNullOrEmpty(lookup)) {
                logger.warn("The 'extratags' option is deprecated, please use the 'mdctags' option instead.");
            }
        }
        return Util.parseMdcTags(lookup);
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getExtra(Dsn dsn) {
        return Util.parseExtra(Lookup.lookup("extra", dsn));
    }

    /* access modifiers changed from: protected */
    public boolean getCompressionEnabled(Dsn dsn) {
        return !FALSE.equalsIgnoreCase(Lookup.lookup(COMPRESSION_OPTION, dsn));
    }

    /* access modifiers changed from: protected */
    public boolean getHideCommonFramesEnabled(Dsn dsn) {
        return !FALSE.equalsIgnoreCase(Lookup.lookup(HIDE_COMMON_FRAMES_OPTION, dsn));
    }

    /* access modifiers changed from: protected */
    public int getMaxMessageLength(Dsn dsn) {
        return Util.parseInteger(Lookup.lookup(MAX_MESSAGE_LENGTH_OPTION, dsn), Integer.valueOf(1000)).intValue();
    }

    /* access modifiers changed from: protected */
    public int getTimeout(Dsn dsn) {
        return Util.parseInteger(Lookup.lookup(CONNECTION_TIMEOUT_OPTION, dsn), Integer.valueOf(CONNECTION_TIMEOUT_DEFAULT)).intValue();
    }

    /* access modifiers changed from: protected */
    public int getReadTimeout(Dsn dsn) {
        return Util.parseInteger(Lookup.lookup(READ_TIMEOUT_OPTION, dsn), Integer.valueOf(READ_TIMEOUT_DEFAULT)).intValue();
    }

    /* access modifiers changed from: protected */
    public boolean getBufferEnabled(Dsn dsn) {
        String lookup = Lookup.lookup(BUFFER_ENABLED_OPTION, dsn);
        if (lookup != null) {
            return Boolean.parseBoolean(lookup);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public Buffer getBuffer(Dsn dsn) {
        String lookup = Lookup.lookup(BUFFER_DIR_OPTION, dsn);
        if (lookup != null) {
            return new DiskBuffer(new File(lookup), getBufferSize(dsn));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getBufferSize(Dsn dsn) {
        return Util.parseInteger(Lookup.lookup(BUFFER_SIZE_OPTION, dsn), Integer.valueOf(10)).intValue();
    }

    /* access modifiers changed from: protected */
    public boolean getUncaughtHandlerEnabled(Dsn dsn) {
        return !FALSE.equalsIgnoreCase(Lookup.lookup(UNCAUGHT_HANDLER_ENABLED_OPTION, dsn));
    }
}
