package p006io.grpc.internal;

import javax.annotation.Nullable;
import p006io.grpc.Attributes;
import p006io.grpc.Decompressor;
import p006io.grpc.Metadata;
import p006io.grpc.Status;

/* renamed from: io.grpc.internal.ServerStream */
public interface ServerStream extends Stream {
    void cancel(Status status);

    void close(Status status, Metadata metadata);

    Attributes getAttributes();

    @Nullable
    String getAuthority();

    void setDecompressor(Decompressor decompressor);

    void setListener(ServerStreamListener serverStreamListener);

    StatsTraceContext statsTraceContext();

    void writeHeaders(Metadata metadata);
}
