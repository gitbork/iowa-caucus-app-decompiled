package p006io.grpc.internal;

import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p006io.grpc.Attributes;
import p006io.grpc.BinaryLog;
import p006io.grpc.ClientInterceptor;
import p006io.grpc.CompressorRegistry;
import p006io.grpc.DecompressorRegistry;
import p006io.grpc.EquivalentAddressGroup;
import p006io.grpc.InternalChannelz;
import p006io.grpc.ManagedChannel;
import p006io.grpc.ManagedChannelBuilder;
import p006io.grpc.NameResolver;
import p006io.grpc.NameResolver.Args;
import p006io.grpc.NameResolver.Factory;
import p006io.grpc.NameResolver.Listener2;
import p006io.grpc.NameResolver.ResolutionResult;
import p006io.grpc.NameResolverRegistry;
import p006io.grpc.ProxyDetector;
import p006io.grpc.internal.AbstractManagedChannelImplBuilder;
import p006io.grpc.internal.ExponentialBackoffPolicy.Provider;
import p006io.opencensus.trace.Tracing;

/* renamed from: io.grpc.internal.AbstractManagedChannelImplBuilder */
public abstract class AbstractManagedChannelImplBuilder<T extends AbstractManagedChannelImplBuilder<T>> extends ManagedChannelBuilder<T> {
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final Factory DEFAULT_NAME_RESOLVER_FACTORY = NameResolverRegistry.getDefaultRegistry().asFactory();
    private static final long DEFAULT_PER_RPC_BUFFER_LIMIT_IN_BYTES = 1048576;
    private static final long DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES = 16777216;
    private static final String DIRECT_ADDRESS_SCHEME = "directaddress";
    @VisibleForTesting
    static final long IDLE_MODE_DEFAULT_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(IDLE_MODE_MAX_TIMEOUT_DAYS);
    @VisibleForTesting
    static final long IDLE_MODE_MAX_TIMEOUT_DAYS = 30;
    static final long IDLE_MODE_MIN_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(1);
    @Nullable
    @VisibleForTesting
    String authorityOverride;
    @Nullable
    BinaryLog binlog;
    @Nullable
    private CensusStatsModule censusStatsOverride;
    InternalChannelz channelz = InternalChannelz.instance();
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    String defaultLbPolicy = GrpcUtil.DEFAULT_LB_POLICY;
    @Nullable
    Map<String, ?> defaultServiceConfig;
    @Nullable
    private final SocketAddress directServerAddress;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    boolean fullStreamDecompression;
    long idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
    private final List<ClientInterceptor> interceptors = new ArrayList();
    boolean lookUpServiceConfig = true;
    int maxHedgedAttempts = 5;
    private int maxInboundMessageSize = 4194304;
    int maxRetryAttempts = 5;
    int maxTraceEvents;
    private Factory nameResolverFactory = DEFAULT_NAME_RESOLVER_FACTORY;
    long perRpcBufferLimit = 1048576;
    @Nullable
    ProxyDetector proxyDetector;
    private boolean recordFinishedRpcs = true;
    private boolean recordRealTimeMetrics = false;
    private boolean recordStartedRpcs = true;
    long retryBufferSize = DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES;
    boolean retryEnabled = false;
    private boolean statsEnabled = true;
    final String target;
    boolean temporarilyDisableRetry;
    private boolean tracingEnabled = true;
    protected TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();
    @Nullable
    String userAgent;

    /* renamed from: io.grpc.internal.AbstractManagedChannelImplBuilder$DirectAddressNameResolverFactory */
    private static class DirectAddressNameResolverFactory extends Factory {
        final SocketAddress address;
        final String authority;

        public String getDefaultScheme() {
            return AbstractManagedChannelImplBuilder.DIRECT_ADDRESS_SCHEME;
        }

        DirectAddressNameResolverFactory(SocketAddress socketAddress, String str) {
            this.address = socketAddress;
            this.authority = str;
        }

        public NameResolver newNameResolver(URI uri, Args args) {
            return new NameResolver() {
                public void shutdown() {
                }

                public String getServiceAuthority() {
                    return DirectAddressNameResolverFactory.this.authority;
                }

                public void start(Listener2 listener2) {
                    listener2.onResult(ResolutionResult.newBuilder().setAddresses(Collections.singletonList(new EquivalentAddressGroup(DirectAddressNameResolverFactory.this.address))).setAttributes(Attributes.EMPTY).build());
                }
            };
        }
    }

    private T thisT() {
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract ClientTransportFactory buildTransportFactory();

    /* access modifiers changed from: protected */
    public int getDefaultPort() {
        return GrpcUtil.DEFAULT_PORT_SSL;
    }

    public static ManagedChannelBuilder<?> forAddress(String str, int i) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public static ManagedChannelBuilder<?> forTarget(String str) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public T maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "negative max");
        this.maxInboundMessageSize = i;
        return thisT();
    }

    /* access modifiers changed from: protected */
    public final int maxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    protected AbstractManagedChannelImplBuilder(String str) {
        this.target = (String) Preconditions.checkNotNull(str, TouchesHelper.TARGET_KEY);
        this.directServerAddress = null;
    }

    @VisibleForTesting
    static String makeTargetStringForDirectAddress(SocketAddress socketAddress) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("/");
            sb.append(socketAddress);
            return new URI(DIRECT_ADDRESS_SCHEME, "", sb.toString(), null).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected AbstractManagedChannelImplBuilder(SocketAddress socketAddress, String str) {
        this.target = makeTargetStringForDirectAddress(socketAddress);
        this.directServerAddress = socketAddress;
        this.nameResolverFactory = new DirectAddressNameResolverFactory(socketAddress, str);
    }

    public final T directExecutor() {
        return executor(MoreExecutors.directExecutor());
    }

    public final T executor(Executor executor) {
        if (executor != null) {
            this.executorPool = new FixedObjectPool(executor);
        } else {
            this.executorPool = DEFAULT_EXECUTOR_POOL;
        }
        return thisT();
    }

    public final T intercept(List<ClientInterceptor> list) {
        this.interceptors.addAll(list);
        return thisT();
    }

    public final T intercept(ClientInterceptor... clientInterceptorArr) {
        return intercept(Arrays.asList(clientInterceptorArr));
    }

    public final T nameResolverFactory(Factory factory) {
        Preconditions.checkState(this.directServerAddress == null, "directServerAddress is set (%s), which forbids the use of NameResolverFactory", (Object) this.directServerAddress);
        if (factory != null) {
            this.nameResolverFactory = factory;
        } else {
            this.nameResolverFactory = DEFAULT_NAME_RESOLVER_FACTORY;
        }
        return thisT();
    }

    public final T defaultLoadBalancingPolicy(String str) {
        boolean z = true;
        Preconditions.checkState(this.directServerAddress == null, "directServerAddress is set (%s), which forbids the use of load-balancing policy", (Object) this.directServerAddress);
        if (str == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "policy cannot be null");
        this.defaultLbPolicy = str;
        return thisT();
    }

    public final T enableFullStreamDecompression() {
        this.fullStreamDecompression = true;
        return thisT();
    }

    public final T decompressorRegistry(DecompressorRegistry decompressorRegistry2) {
        if (decompressorRegistry2 != null) {
            this.decompressorRegistry = decompressorRegistry2;
        } else {
            this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        }
        return thisT();
    }

    public final T compressorRegistry(CompressorRegistry compressorRegistry2) {
        if (compressorRegistry2 != null) {
            this.compressorRegistry = compressorRegistry2;
        } else {
            this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        }
        return thisT();
    }

    public final T userAgent(@Nullable String str) {
        this.userAgent = str;
        return thisT();
    }

    public final T overrideAuthority(String str) {
        this.authorityOverride = checkAuthority(str);
        return thisT();
    }

    public final T idleTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "idle timeout is %s, but must be positive", j);
        if (timeUnit.toDays(j) >= IDLE_MODE_MAX_TIMEOUT_DAYS) {
            this.idleTimeoutMillis = -1;
        } else {
            this.idleTimeoutMillis = Math.max(timeUnit.toMillis(j), IDLE_MODE_MIN_TIMEOUT_MILLIS);
        }
        return thisT();
    }

    public final T maxRetryAttempts(int i) {
        this.maxRetryAttempts = i;
        return thisT();
    }

    public final T maxHedgedAttempts(int i) {
        this.maxHedgedAttempts = i;
        return thisT();
    }

    public final T retryBufferSize(long j) {
        Preconditions.checkArgument(j > 0, "retry buffer size must be positive");
        this.retryBufferSize = j;
        return thisT();
    }

    public final T perRpcBufferLimit(long j) {
        Preconditions.checkArgument(j > 0, "per RPC buffer limit must be positive");
        this.perRpcBufferLimit = j;
        return thisT();
    }

    public final T disableRetry() {
        this.retryEnabled = false;
        return thisT();
    }

    public final T enableRetry() {
        this.retryEnabled = true;
        this.statsEnabled = false;
        this.tracingEnabled = false;
        return thisT();
    }

    public final T setBinaryLog(BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return thisT();
    }

    public T maxTraceEvents(int i) {
        Preconditions.checkArgument(i >= 0, "maxTraceEvents must be non-negative");
        this.maxTraceEvents = i;
        return thisT();
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final T overrideCensusStatsModule(CensusStatsModule censusStatsModule) {
        this.censusStatsOverride = censusStatsModule;
        return thisT();
    }

    public T proxyDetector(@Nullable ProxyDetector proxyDetector2) {
        this.proxyDetector = proxyDetector2;
        return thisT();
    }

    public T defaultServiceConfig(@Nullable Map<String, ?> map) {
        this.defaultServiceConfig = checkMapEntryTypes(map);
        return thisT();
    }

    @Nullable
    private static Map<String, ?> checkMapEntryTypes(@Nullable Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Entry entry : map.entrySet()) {
            Preconditions.checkArgument(entry.getKey() instanceof String, "The key of the entry '%s' is not of String type", (Object) entry);
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                linkedHashMap.put(str, null);
            } else if (value instanceof Map) {
                linkedHashMap.put(str, checkMapEntryTypes((Map) value));
            } else if (value instanceof List) {
                linkedHashMap.put(str, checkListEntryTypes((List) value));
            } else if (value instanceof String) {
                linkedHashMap.put(str, value);
            } else if (value instanceof Double) {
                linkedHashMap.put(str, value);
            } else if (value instanceof Boolean) {
                linkedHashMap.put(str, value);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("The value of the map entry '");
                sb.append(entry);
                sb.append("' is of type '");
                sb.append(value.getClass());
                sb.append("', which is not supported");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static List<?> checkListEntryTypes(List<?> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object next : list) {
            if (next == null) {
                arrayList.add(null);
            } else if (next instanceof Map) {
                arrayList.add(checkMapEntryTypes((Map) next));
            } else if (next instanceof List) {
                arrayList.add(checkListEntryTypes((List) next));
            } else if (next instanceof String) {
                arrayList.add(next);
            } else if (next instanceof Double) {
                arrayList.add(next);
            } else if (next instanceof Boolean) {
                arrayList.add(next);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("The entry '");
                sb.append(next);
                sb.append("' is of type '");
                sb.append(next.getClass());
                sb.append("', which is not supported");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public T disableServiceConfigLookUp() {
        this.lookUpServiceConfig = false;
        return thisT();
    }

    /* access modifiers changed from: protected */
    public void setStatsEnabled(boolean z) {
        this.statsEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void setStatsRecordStartedRpcs(boolean z) {
        this.recordStartedRpcs = z;
    }

    /* access modifiers changed from: protected */
    public void setStatsRecordFinishedRpcs(boolean z) {
        this.recordFinishedRpcs = z;
    }

    /* access modifiers changed from: protected */
    public void setStatsRecordRealTimeMetrics(boolean z) {
        this.recordRealTimeMetrics = z;
    }

    /* access modifiers changed from: protected */
    public void setTracingEnabled(boolean z) {
        this.tracingEnabled = z;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final long getIdleTimeoutMillis() {
        return this.idleTimeoutMillis;
    }

    /* access modifiers changed from: protected */
    public String checkAuthority(String str) {
        return GrpcUtil.checkAuthority(str);
    }

    public ManagedChannel build() {
        ManagedChannelImpl managedChannelImpl = new ManagedChannelImpl(this, buildTransportFactory(), new Provider(), SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR), GrpcUtil.STOPWATCH_SUPPLIER, getEffectiveInterceptors(), TimeProvider.SYSTEM_TIME_PROVIDER);
        return new ManagedChannelOrphanWrapper(managedChannelImpl);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final List<ClientInterceptor> getEffectiveInterceptors() {
        ArrayList arrayList = new ArrayList(this.interceptors);
        this.temporarilyDisableRetry = false;
        if (this.statsEnabled) {
            this.temporarilyDisableRetry = true;
            CensusStatsModule censusStatsModule = this.censusStatsOverride;
            if (censusStatsModule == null) {
                censusStatsModule = new CensusStatsModule(GrpcUtil.STOPWATCH_SUPPLIER, true, this.recordStartedRpcs, this.recordFinishedRpcs, this.recordRealTimeMetrics);
            }
            arrayList.add(0, censusStatsModule.getClientInterceptor());
        }
        if (this.tracingEnabled) {
            this.temporarilyDisableRetry = true;
            arrayList.add(0, new CensusTracingModule(Tracing.getTracer(), Tracing.getPropagationComponent().getBinaryFormat()).getClientInterceptor());
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public Factory getNameResolverFactory() {
        String str = this.authorityOverride;
        if (str == null) {
            return this.nameResolverFactory;
        }
        return new OverrideAuthorityNameResolverFactory(this.nameResolverFactory, str);
    }
}
