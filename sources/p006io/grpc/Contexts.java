package p006io.grpc;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeoutException;
import p006io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener;
import p006io.grpc.ServerCall.Listener;
import p006io.grpc.Status.Code;

/* renamed from: io.grpc.Contexts */
public final class Contexts {

    /* renamed from: io.grpc.Contexts$ContextualizedServerCallListener */
    private static class ContextualizedServerCallListener<ReqT> extends SimpleForwardingServerCallListener<ReqT> {
        private final Context context;

        public ContextualizedServerCallListener(Listener<ReqT> listener, Context context2) {
            super(listener);
            this.context = context2;
        }

        public void onMessage(ReqT reqt) {
            Context attach = this.context.attach();
            try {
                super.onMessage(reqt);
            } finally {
                this.context.detach(attach);
            }
        }

        public void onHalfClose() {
            Context attach = this.context.attach();
            try {
                super.onHalfClose();
            } finally {
                this.context.detach(attach);
            }
        }

        public void onCancel() {
            Context attach = this.context.attach();
            try {
                super.onCancel();
            } finally {
                this.context.detach(attach);
            }
        }

        public void onComplete() {
            Context attach = this.context.attach();
            try {
                super.onComplete();
            } finally {
                this.context.detach(attach);
            }
        }

        public void onReady() {
            Context attach = this.context.attach();
            try {
                super.onReady();
            } finally {
                this.context.detach(attach);
            }
        }
    }

    private Contexts() {
    }

    public static <ReqT, RespT> Listener<ReqT> interceptCall(Context context, ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        Context attach = context.attach();
        try {
            return new ContextualizedServerCallListener(serverCallHandler.startCall(serverCall, metadata), context);
        } finally {
            context.detach(attach);
        }
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1975")
    public static Status statusFromCancelled(Context context) {
        Preconditions.checkNotNull(context, "context must not be null");
        if (!context.isCancelled()) {
            return null;
        }
        Throwable cancellationCause = context.cancellationCause();
        if (cancellationCause == null) {
            return Status.CANCELLED.withDescription("io.grpc.Context was cancelled without error");
        }
        if (cancellationCause instanceof TimeoutException) {
            return Status.DEADLINE_EXCEEDED.withDescription(cancellationCause.getMessage()).withCause(cancellationCause);
        }
        Status fromThrowable = Status.fromThrowable(cancellationCause);
        if (!Code.UNKNOWN.equals(fromThrowable.getCode()) || fromThrowable.getCause() != cancellationCause) {
            return fromThrowable.withCause(cancellationCause);
        }
        return Status.CANCELLED.withDescription("Context cancelled").withCause(cancellationCause);
    }
}
