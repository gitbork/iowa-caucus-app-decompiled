package p006io.grpc;

import javax.annotation.concurrent.ThreadSafe;
import p006io.grpc.ServerCall.Listener;

@ThreadSafe
/* renamed from: io.grpc.ServerCallHandler */
public interface ServerCallHandler<RequestT, ResponseT> {
    Listener<RequestT> startCall(ServerCall<RequestT, ResponseT> serverCall, Metadata metadata);
}
