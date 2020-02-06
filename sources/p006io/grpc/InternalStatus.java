package p006io.grpc;

import p006io.grpc.Metadata.Key;

@Internal
/* renamed from: io.grpc.InternalStatus */
public final class InternalStatus {
    @Internal
    public static final Key<Status> CODE_KEY = Status.CODE_KEY;
    @Internal
    public static final Key<String> MESSAGE_KEY = Status.MESSAGE_KEY;

    private InternalStatus() {
    }
}
