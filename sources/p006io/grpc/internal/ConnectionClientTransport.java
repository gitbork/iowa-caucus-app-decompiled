package p006io.grpc.internal;

import javax.annotation.concurrent.ThreadSafe;
import p006io.grpc.Attributes;

@ThreadSafe
/* renamed from: io.grpc.internal.ConnectionClientTransport */
public interface ConnectionClientTransport extends ManagedClientTransport {
    Attributes getAttributes();
}
