package p006io.opencensus.common;

import java.io.Closeable;

@Deprecated
/* renamed from: io.opencensus.common.NonThrowingCloseable */
public interface NonThrowingCloseable extends Closeable {
    void close();
}
