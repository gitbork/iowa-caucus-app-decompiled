package p006io.grpc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import p006io.grpc.BindableService;
import p006io.grpc.ExperimentalApi;
import p006io.grpc.HandlerRegistry;
import p006io.grpc.MethodDescriptor;
import p006io.grpc.ServerMethodDefinition;
import p006io.grpc.ServerServiceDefinition;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/933")
@ThreadSafe
/* renamed from: io.grpc.util.MutableHandlerRegistry */
public final class MutableHandlerRegistry extends HandlerRegistry {
    private final ConcurrentMap<String, ServerServiceDefinition> services = new ConcurrentHashMap();

    @Nullable
    public ServerServiceDefinition addService(ServerServiceDefinition serverServiceDefinition) {
        return (ServerServiceDefinition) this.services.put(serverServiceDefinition.getServiceDescriptor().getName(), serverServiceDefinition);
    }

    @Nullable
    public ServerServiceDefinition addService(BindableService bindableService) {
        return addService(bindableService.bindService());
    }

    public boolean removeService(ServerServiceDefinition serverServiceDefinition) {
        return this.services.remove(serverServiceDefinition.getServiceDescriptor().getName(), serverServiceDefinition);
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/2222")
    public List<ServerServiceDefinition> getServices() {
        return Collections.unmodifiableList(new ArrayList(this.services.values()));
    }

    @Nullable
    public ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2) {
        String extractFullServiceName = MethodDescriptor.extractFullServiceName(str);
        if (extractFullServiceName == null) {
            return null;
        }
        ServerServiceDefinition serverServiceDefinition = (ServerServiceDefinition) this.services.get(extractFullServiceName);
        if (serverServiceDefinition == null) {
            return null;
        }
        return serverServiceDefinition.getMethod(str);
    }
}
