package p006io.grpc.internal;

import java.util.Map;
import p006io.grpc.Attributes;
import p006io.grpc.Attributes.Key;
import p006io.grpc.SecurityLevel;

/* renamed from: io.grpc.internal.GrpcAttributes */
public final class GrpcAttributes {
    public static final Key<Attributes> ATTR_CLIENT_EAG_ATTRS = Key.create("io.grpc.internal.GrpcAttributes.clientEagAttrs");
    public static final Key<String> ATTR_LB_ADDR_AUTHORITY = Key.create("io.grpc.grpclb.lbAddrAuthority");
    public static final Key<Boolean> ATTR_LB_PROVIDED_BACKEND = Key.create("io.grpc.grpclb.lbProvidedBackend");
    public static final Key<SecurityLevel> ATTR_SECURITY_LEVEL = Key.create("io.grpc.internal.GrpcAttributes.securityLevel");
    public static final Key<Map<String, ?>> NAME_RESOLVER_SERVICE_CONFIG = Key.create("service-config");

    private GrpcAttributes() {
    }
}
