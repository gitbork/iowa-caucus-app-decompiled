package p006io.grpc.util;

import com.google.common.base.MoreObjects;
import java.util.List;
import p006io.grpc.Attributes;
import p006io.grpc.ConnectivityStateInfo;
import p006io.grpc.EquivalentAddressGroup;
import p006io.grpc.ExperimentalApi;
import p006io.grpc.LoadBalancer;
import p006io.grpc.LoadBalancer.ResolvedAddresses;
import p006io.grpc.LoadBalancer.Subchannel;
import p006io.grpc.Status;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
/* renamed from: io.grpc.util.ForwardingLoadBalancer */
public abstract class ForwardingLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: protected */
    public abstract LoadBalancer delegate();

    @Deprecated
    public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        delegate().handleResolvedAddressGroups(list, attributes);
    }

    public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
        delegate().handleResolvedAddresses(resolvedAddresses);
    }

    public void handleNameResolutionError(Status status) {
        delegate().handleNameResolutionError(status);
    }

    public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        delegate().handleSubchannelState(subchannel, connectivityStateInfo);
    }

    public void shutdown() {
        delegate().shutdown();
    }

    public boolean canHandleEmptyAddressListFromNameResolution() {
        return delegate().canHandleEmptyAddressListFromNameResolution();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
