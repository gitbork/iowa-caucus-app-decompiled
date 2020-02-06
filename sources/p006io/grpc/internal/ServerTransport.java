package p006io.grpc.internal;

import java.util.concurrent.ScheduledExecutorService;
import p006io.grpc.InternalChannelz.SocketStats;
import p006io.grpc.InternalInstrumented;
import p006io.grpc.Status;

/* renamed from: io.grpc.internal.ServerTransport */
public interface ServerTransport extends InternalInstrumented<SocketStats> {
    ScheduledExecutorService getScheduledExecutorService();

    void shutdown();

    void shutdownNow(Status status);
}
