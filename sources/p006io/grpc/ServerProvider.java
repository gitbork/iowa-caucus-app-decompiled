package p006io.grpc;

import java.util.Collections;
import p006io.grpc.ManagedChannelProvider.ProviderNotFoundException;
import p006io.grpc.ServiceProviders.PriorityAccessor;

@Internal
/* renamed from: io.grpc.ServerProvider */
public abstract class ServerProvider {
    private static final ServerProvider provider = ((ServerProvider) ServiceProviders.load(ServerProvider.class, Collections.emptyList(), ServerProvider.class.getClassLoader(), new PriorityAccessor<ServerProvider>() {
        public boolean isAvailable(ServerProvider serverProvider) {
            return serverProvider.isAvailable();
        }

        public int getPriority(ServerProvider serverProvider) {
            return serverProvider.priority();
        }
    }));

    /* access modifiers changed from: protected */
    public abstract ServerBuilder<?> builderForPort(int i);

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    public static ServerProvider provider() {
        ServerProvider serverProvider = provider;
        if (serverProvider != null) {
            return serverProvider;
        }
        throw new ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty or grpc-netty-shaded artifact");
    }
}
