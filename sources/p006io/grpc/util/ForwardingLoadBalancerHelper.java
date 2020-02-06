package p006io.grpc.util;

import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import p006io.grpc.Attributes;
import p006io.grpc.ChannelLogger;
import p006io.grpc.ConnectivityState;
import p006io.grpc.EquivalentAddressGroup;
import p006io.grpc.ExperimentalApi;
import p006io.grpc.LoadBalancer.Helper;
import p006io.grpc.LoadBalancer.Subchannel;
import p006io.grpc.LoadBalancer.SubchannelPicker;
import p006io.grpc.ManagedChannel;
import p006io.grpc.NameResolver.Factory;
import p006io.grpc.SynchronizationContext;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
/* renamed from: io.grpc.util.ForwardingLoadBalancerHelper */
public abstract class ForwardingLoadBalancerHelper extends Helper {
    /* access modifiers changed from: protected */
    public abstract Helper delegate();

    public Subchannel createSubchannel(List<EquivalentAddressGroup> list, Attributes attributes) {
        return delegate().createSubchannel(list, attributes);
    }

    public void updateSubchannelAddresses(Subchannel subchannel, List<EquivalentAddressGroup> list) {
        delegate().updateSubchannelAddresses(subchannel, list);
    }

    public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
        return delegate().createOobChannel(equivalentAddressGroup, str);
    }

    public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
        delegate().updateOobChannelAddresses(managedChannel, equivalentAddressGroup);
    }

    public ManagedChannel createResolvingOobChannel(String str) {
        return delegate().createResolvingOobChannel(str);
    }

    public void updateBalancingState(ConnectivityState connectivityState, SubchannelPicker subchannelPicker) {
        delegate().updateBalancingState(connectivityState, subchannelPicker);
    }

    public void refreshNameResolution() {
        delegate().refreshNameResolution();
    }

    @Deprecated
    public void runSerialized(Runnable runnable) {
        delegate().runSerialized(runnable);
    }

    @Deprecated
    public Factory getNameResolverFactory() {
        return delegate().getNameResolverFactory();
    }

    public String getAuthority() {
        return delegate().getAuthority();
    }

    public SynchronizationContext getSynchronizationContext() {
        return delegate().getSynchronizationContext();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return delegate().getScheduledExecutorService();
    }

    public ChannelLogger getChannelLogger() {
        return delegate().getChannelLogger();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
