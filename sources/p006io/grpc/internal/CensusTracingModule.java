package p006io.grpc.internal;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p006io.grpc.CallOptions;
import p006io.grpc.Channel;
import p006io.grpc.ClientCall;
import p006io.grpc.ClientCall.Listener;
import p006io.grpc.ClientInterceptor;
import p006io.grpc.ClientStreamTracer;
import p006io.grpc.ClientStreamTracer.Factory;
import p006io.grpc.ClientStreamTracer.StreamInfo;
import p006io.grpc.Context;
import p006io.grpc.ForwardingClientCall.SimpleForwardingClientCall;
import p006io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener;
import p006io.grpc.Metadata;
import p006io.grpc.Metadata.BinaryMarshaller;
import p006io.grpc.Metadata.Key;
import p006io.grpc.MethodDescriptor;
import p006io.grpc.ServerStreamTracer;
import p006io.grpc.ServerStreamTracer.ServerCallInfo;
import p006io.grpc.Status;
import p006io.opencensus.trace.BlankSpan;
import p006io.opencensus.trace.EndSpanOptions;
import p006io.opencensus.trace.MessageEvent;
import p006io.opencensus.trace.MessageEvent.Builder;
import p006io.opencensus.trace.MessageEvent.Type;
import p006io.opencensus.trace.Span;
import p006io.opencensus.trace.SpanContext;
import p006io.opencensus.trace.Tracer;
import p006io.opencensus.trace.propagation.BinaryFormat;
import p006io.opencensus.trace.unsafe.ContextUtils;

/* renamed from: io.grpc.internal.CensusTracingModule */
final class CensusTracingModule {
    /* access modifiers changed from: private */
    @Nullable
    public static final AtomicIntegerFieldUpdater<ClientCallTracer> callEndedUpdater;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(CensusTracingModule.class.getName());
    /* access modifiers changed from: private */
    @Nullable
    public static final AtomicIntegerFieldUpdater<ServerTracer> streamClosedUpdater;
    /* access modifiers changed from: private */
    public final Tracer censusTracer;
    private final TracingClientInterceptor clientInterceptor = new TracingClientInterceptor();
    private final ServerTracerFactory serverTracerFactory = new ServerTracerFactory();
    @VisibleForTesting
    final Key<SpanContext> tracingHeader;

    @VisibleForTesting
    /* renamed from: io.grpc.internal.CensusTracingModule$ClientCallTracer */
    final class ClientCallTracer extends Factory {
        volatile int callEnded;
        private final boolean isSampledToLocalTracing;
        private final Span span;

        ClientCallTracer(@Nullable Span span2, MethodDescriptor<?, ?> methodDescriptor) {
            Preconditions.checkNotNull(methodDescriptor, Param.METHOD);
            this.isSampledToLocalTracing = methodDescriptor.isSampledToLocalTracing();
            this.span = CensusTracingModule.this.censusTracer.spanBuilderWithExplicitParent(CensusTracingModule.generateTraceSpanName(false, methodDescriptor.getFullMethodName()), span2).setRecordEvents(true).startSpan();
        }

        public ClientStreamTracer newClientStreamTracer(StreamInfo streamInfo, Metadata metadata) {
            if (this.span != BlankSpan.INSTANCE) {
                metadata.discardAll(CensusTracingModule.this.tracingHeader);
                metadata.put(CensusTracingModule.this.tracingHeader, this.span.getContext());
            }
            return new ClientTracer(this.span);
        }

        /* access modifiers changed from: 0000 */
        public void callEnded(Status status) {
            if (CensusTracingModule.callEndedUpdater != null) {
                if (CensusTracingModule.callEndedUpdater.getAndSet(this, 1) != 0) {
                    return;
                }
            } else if (this.callEnded == 0) {
                this.callEnded = 1;
            } else {
                return;
            }
            this.span.end(CensusTracingModule.createEndSpanOptions(status, this.isSampledToLocalTracing));
        }
    }

    /* renamed from: io.grpc.internal.CensusTracingModule$ClientTracer */
    private static final class ClientTracer extends ClientStreamTracer {
        private final Span span;

        ClientTracer(Span span2) {
            this.span = (Span) Preconditions.checkNotNull(span2, "span");
        }

        public void outboundMessageSent(int i, long j, long j2) {
            CensusTracingModule.recordMessageEvent(this.span, Type.SENT, i, j, j2);
        }

        public void inboundMessageRead(int i, long j, long j2) {
            CensusTracingModule.recordMessageEvent(this.span, Type.RECEIVED, i, j, j2);
        }
    }

    /* renamed from: io.grpc.internal.CensusTracingModule$ServerTracer */
    private final class ServerTracer extends ServerStreamTracer {
        volatile boolean isSampledToLocalTracing;
        private final Span span;
        volatile int streamClosed;

        ServerTracer(String str, @Nullable SpanContext spanContext) {
            Preconditions.checkNotNull(str, "fullMethodName");
            this.span = CensusTracingModule.this.censusTracer.spanBuilderWithRemoteParent(CensusTracingModule.generateTraceSpanName(true, str), spanContext).setRecordEvents(true).startSpan();
        }

        public void serverCallStarted(ServerCallInfo<?, ?> serverCallInfo) {
            this.isSampledToLocalTracing = serverCallInfo.getMethodDescriptor().isSampledToLocalTracing();
        }

        public void streamClosed(Status status) {
            if (CensusTracingModule.streamClosedUpdater != null) {
                if (CensusTracingModule.streamClosedUpdater.getAndSet(this, 1) != 0) {
                    return;
                }
            } else if (this.streamClosed == 0) {
                this.streamClosed = 1;
            } else {
                return;
            }
            this.span.end(CensusTracingModule.createEndSpanOptions(status, this.isSampledToLocalTracing));
        }

        public Context filterContext(Context context) {
            return ContextUtils.withValue(context, this.span);
        }

        public void outboundMessageSent(int i, long j, long j2) {
            CensusTracingModule.recordMessageEvent(this.span, Type.SENT, i, j, j2);
        }

        public void inboundMessageRead(int i, long j, long j2) {
            CensusTracingModule.recordMessageEvent(this.span, Type.RECEIVED, i, j, j2);
        }
    }

    @VisibleForTesting
    /* renamed from: io.grpc.internal.CensusTracingModule$ServerTracerFactory */
    final class ServerTracerFactory extends ServerStreamTracer.Factory {
        ServerTracerFactory() {
        }

        public ServerStreamTracer newServerStreamTracer(String str, Metadata metadata) {
            SpanContext spanContext = (SpanContext) metadata.get(CensusTracingModule.this.tracingHeader);
            if (spanContext == SpanContext.INVALID) {
                spanContext = null;
            }
            return new ServerTracer(str, spanContext);
        }
    }

    @VisibleForTesting
    /* renamed from: io.grpc.internal.CensusTracingModule$TracingClientInterceptor */
    final class TracingClientInterceptor implements ClientInterceptor {
        TracingClientInterceptor() {
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
            final ClientCallTracer newClientCallTracer = CensusTracingModule.this.newClientCallTracer(ContextUtils.getValue(Context.current()), methodDescriptor);
            return new SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions.withStreamTracerFactory(newClientCallTracer))) {
                public void start(Listener<RespT> listener, Metadata metadata) {
                    delegate().start(new SimpleForwardingClientCallListener<RespT>(listener) {
                        public void onClose(Status status, Metadata metadata) {
                            newClientCallTracer.callEnded(status);
                            super.onClose(status, metadata);
                        }
                    }, metadata);
                }
            };
        }
    }

    static {
        AtomicIntegerFieldUpdater<ServerTracer> atomicIntegerFieldUpdater;
        AtomicIntegerFieldUpdater<ClientCallTracer> atomicIntegerFieldUpdater2 = null;
        try {
            AtomicIntegerFieldUpdater<ClientCallTracer> newUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientCallTracer.class, "callEnded");
            atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(ServerTracer.class, "streamClosed");
            atomicIntegerFieldUpdater2 = newUpdater;
        } catch (Throwable th) {
            logger.log(Level.SEVERE, "Creating atomic field updaters failed", th);
            atomicIntegerFieldUpdater = null;
        }
        callEndedUpdater = atomicIntegerFieldUpdater2;
        streamClosedUpdater = atomicIntegerFieldUpdater;
    }

    CensusTracingModule(Tracer tracer, final BinaryFormat binaryFormat) {
        this.censusTracer = (Tracer) Preconditions.checkNotNull(tracer, "censusTracer");
        Preconditions.checkNotNull(binaryFormat, "censusPropagationBinaryFormat");
        this.tracingHeader = Key.m371of("grpc-trace-bin", (BinaryMarshaller<T>) new BinaryMarshaller<SpanContext>() {
            public byte[] toBytes(SpanContext spanContext) {
                return binaryFormat.toByteArray(spanContext);
            }

            public SpanContext parseBytes(byte[] bArr) {
                try {
                    return binaryFormat.fromByteArray(bArr);
                } catch (Exception e) {
                    CensusTracingModule.logger.log(Level.FINE, "Failed to parse tracing header", e);
                    return SpanContext.INVALID;
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public ClientCallTracer newClientCallTracer(@Nullable Span span, MethodDescriptor<?, ?> methodDescriptor) {
        return new ClientCallTracer(span, methodDescriptor);
    }

    /* access modifiers changed from: 0000 */
    public ServerStreamTracer.Factory getServerTracerFactory() {
        return this.serverTracerFactory;
    }

    /* access modifiers changed from: 0000 */
    public ClientInterceptor getClientInterceptor() {
        return this.clientInterceptor;
    }

    @VisibleForTesting
    static p006io.opencensus.trace.Status convertStatus(Status status) {
        p006io.opencensus.trace.Status status2;
        switch (status.getCode()) {
            case OK:
                status2 = p006io.opencensus.trace.Status.f474OK;
                break;
            case CANCELLED:
                status2 = p006io.opencensus.trace.Status.CANCELLED;
                break;
            case UNKNOWN:
                status2 = p006io.opencensus.trace.Status.UNKNOWN;
                break;
            case INVALID_ARGUMENT:
                status2 = p006io.opencensus.trace.Status.INVALID_ARGUMENT;
                break;
            case DEADLINE_EXCEEDED:
                status2 = p006io.opencensus.trace.Status.DEADLINE_EXCEEDED;
                break;
            case NOT_FOUND:
                status2 = p006io.opencensus.trace.Status.NOT_FOUND;
                break;
            case ALREADY_EXISTS:
                status2 = p006io.opencensus.trace.Status.ALREADY_EXISTS;
                break;
            case PERMISSION_DENIED:
                status2 = p006io.opencensus.trace.Status.PERMISSION_DENIED;
                break;
            case RESOURCE_EXHAUSTED:
                status2 = p006io.opencensus.trace.Status.RESOURCE_EXHAUSTED;
                break;
            case FAILED_PRECONDITION:
                status2 = p006io.opencensus.trace.Status.FAILED_PRECONDITION;
                break;
            case ABORTED:
                status2 = p006io.opencensus.trace.Status.ABORTED;
                break;
            case OUT_OF_RANGE:
                status2 = p006io.opencensus.trace.Status.OUT_OF_RANGE;
                break;
            case UNIMPLEMENTED:
                status2 = p006io.opencensus.trace.Status.UNIMPLEMENTED;
                break;
            case INTERNAL:
                status2 = p006io.opencensus.trace.Status.INTERNAL;
                break;
            case UNAVAILABLE:
                status2 = p006io.opencensus.trace.Status.UNAVAILABLE;
                break;
            case DATA_LOSS:
                status2 = p006io.opencensus.trace.Status.DATA_LOSS;
                break;
            case UNAUTHENTICATED:
                status2 = p006io.opencensus.trace.Status.UNAUTHENTICATED;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unhandled status code ");
                sb.append(status.getCode());
                throw new AssertionError(sb.toString());
        }
        return status.getDescription() != null ? status2.withDescription(status.getDescription()) : status2;
    }

    /* access modifiers changed from: private */
    public static EndSpanOptions createEndSpanOptions(Status status, boolean z) {
        return EndSpanOptions.builder().setStatus(convertStatus(status)).setSampleToLocalSpanStore(z).build();
    }

    /* access modifiers changed from: private */
    public static void recordMessageEvent(Span span, Type type, int i, long j, long j2) {
        Builder builder = MessageEvent.builder(type, (long) i);
        if (j2 != -1) {
            builder.setUncompressedMessageSize(j2);
        }
        if (j != -1) {
            builder.setCompressedMessageSize(j);
        }
        span.addMessageEvent(builder.build());
    }

    @VisibleForTesting
    static String generateTraceSpanName(boolean z, String str) {
        String str2 = z ? "Recv" : "Sent";
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(".");
        sb.append(str.replace(JsonPointer.SEPARATOR, '.'));
        return sb.toString();
    }
}
