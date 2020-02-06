package p006io.grpc;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketAddress;
import javax.net.ssl.SSLSession;
import p006io.grpc.Attributes.Key;

/* renamed from: io.grpc.Grpc */
public final class Grpc {
    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1710")
    public static final Key<SocketAddress> TRANSPORT_ATTR_LOCAL_ADDR = Key.create("local-addr");
    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1710")
    public static final Key<SocketAddress> TRANSPORT_ATTR_REMOTE_ADDR = Key.create("remote-addr");
    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1710")
    public static final Key<SSLSession> TRANSPORT_ATTR_SSL_SESSION = Key.create("ssl-session");

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/4972")
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: io.grpc.Grpc$TransportAttr */
    public @interface TransportAttr {
    }

    private Grpc() {
    }
}
