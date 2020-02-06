package p006io.grpc;

import javax.annotation.concurrent.ThreadSafe;
import p006io.grpc.ServerCall.Listener;

@ThreadSafe
/* renamed from: io.grpc.ServerInterceptor */
public interface ServerInterceptor {
    <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler);
}
