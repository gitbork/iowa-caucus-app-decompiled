package p006io.grpc;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: io.grpc.ClientInterceptor */
public interface ClientInterceptor {
    <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel);
}
