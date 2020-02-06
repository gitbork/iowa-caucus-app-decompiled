package p006io.grpc.internal;

import java.util.Map;
import p006io.grpc.LoadBalancer;
import p006io.grpc.LoadBalancer.Helper;
import p006io.grpc.LoadBalancerProvider;
import p006io.grpc.NameResolver.ConfigOrError;

/* renamed from: io.grpc.internal.PickFirstLoadBalancerProvider */
public final class PickFirstLoadBalancerProvider extends LoadBalancerProvider {
    private static final String NO_CONFIG = "no service config";

    public String getPolicyName() {
        return GrpcUtil.DEFAULT_LB_POLICY;
    }

    public int getPriority() {
        return 5;
    }

    public boolean isAvailable() {
        return true;
    }

    public LoadBalancer newLoadBalancer(Helper helper) {
        return new PickFirstLoadBalancer(helper);
    }

    public ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        return ConfigOrError.fromConfig(NO_CONFIG);
    }
}
