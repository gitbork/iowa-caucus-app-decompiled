package p006io.grpc.okhttp.internal;

import java.io.IOException;

/* renamed from: io.grpc.okhttp.internal.Protocol */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    GRPC_EXP("grpc-exp");
    
    private final String protocol;

    private Protocol(String str) {
        this.protocol = str;
    }

    public static Protocol get(String str) throws IOException {
        if (str.equals(HTTP_1_0.protocol)) {
            return HTTP_1_0;
        }
        if (str.equals(HTTP_1_1.protocol)) {
            return HTTP_1_1;
        }
        if (str.equals(HTTP_2.protocol)) {
            return HTTP_2;
        }
        if (str.equals(GRPC_EXP.protocol)) {
            return GRPC_EXP;
        }
        if (str.equals(SPDY_3.protocol)) {
            return SPDY_3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected protocol: ");
        sb.append(str);
        throw new IOException(sb.toString());
    }

    public String toString() {
        return this.protocol;
    }
}
