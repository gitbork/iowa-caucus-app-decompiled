package p006io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/2861")
@ThreadSafe
/* renamed from: io.grpc.ClientStreamTracer */
public abstract class ClientStreamTracer extends StreamTracer {

    /* renamed from: io.grpc.ClientStreamTracer$Factory */
    public static abstract class Factory {
        @Deprecated
        public ClientStreamTracer newClientStreamTracer(CallOptions callOptions, Metadata metadata) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public ClientStreamTracer newClientStreamTracer(StreamInfo streamInfo, Metadata metadata) {
            return newClientStreamTracer(streamInfo.getCallOptions(), metadata);
        }
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/2861")
    /* renamed from: io.grpc.ClientStreamTracer$StreamInfo */
    public static final class StreamInfo {
        private final CallOptions callOptions;
        private final Attributes transportAttrs;

        /* renamed from: io.grpc.ClientStreamTracer$StreamInfo$Builder */
        public static final class Builder {
            private CallOptions callOptions = CallOptions.DEFAULT;
            private Attributes transportAttrs = Attributes.EMPTY;

            Builder() {
            }

            public Builder setTransportAttrs(Attributes attributes) {
                this.transportAttrs = (Attributes) Preconditions.checkNotNull(attributes, "transportAttrs cannot be null");
                return this;
            }

            public Builder setCallOptions(CallOptions callOptions2) {
                this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions cannot be null");
                return this;
            }

            public StreamInfo build() {
                return new StreamInfo(this.transportAttrs, this.callOptions);
            }
        }

        StreamInfo(Attributes attributes, CallOptions callOptions2) {
            this.transportAttrs = (Attributes) Preconditions.checkNotNull(attributes, "transportAttrs");
            this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions");
        }

        public Attributes getTransportAttrs() {
            return this.transportAttrs;
        }

        public CallOptions getCallOptions() {
            return this.callOptions;
        }

        public Builder toBuilder() {
            Builder builder = new Builder();
            builder.setTransportAttrs(this.transportAttrs);
            builder.setCallOptions(this.callOptions);
            return builder;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("transportAttrs", (Object) this.transportAttrs).add("callOptions", (Object) this.callOptions).toString();
        }
    }

    public void inboundHeaders() {
    }

    public void inboundTrailers(Metadata metadata) {
    }

    public void outboundHeaders() {
    }
}
