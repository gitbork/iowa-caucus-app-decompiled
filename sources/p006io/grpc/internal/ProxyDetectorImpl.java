package p006io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.io.IOException;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p006io.grpc.HttpConnectProxiedSocketAddress;
import p006io.grpc.HttpConnectProxiedSocketAddress.Builder;
import p006io.grpc.ProxiedSocketAddress;
import p006io.grpc.ProxyDetector;

/* renamed from: io.grpc.internal.ProxyDetectorImpl */
class ProxyDetectorImpl implements ProxyDetector {
    private static final AuthenticationProvider DEFAULT_AUTHENTICATOR = new AuthenticationProvider() {
        public PasswordAuthentication requestPasswordAuthentication(String str, InetAddress inetAddress, int i, String str2, String str3, String str4) {
            URL url;
            try {
                url = new URL(str2, str, i, "");
            } catch (MalformedURLException unused) {
                ProxyDetectorImpl.log.log(Level.WARNING, String.format("failed to create URL for Authenticator: %s %s", new Object[]{str2, str}));
                url = null;
            }
            return Authenticator.requestPasswordAuthentication(str, inetAddress, i, str2, str3, str4, url, RequestorType.PROXY);
        }
    };
    private static final Supplier<ProxySelector> DEFAULT_PROXY_SELECTOR = new Supplier<ProxySelector>() {
        public ProxySelector get() {
            return ProxySelector.getDefault();
        }
    };
    @Deprecated
    private static final String GRPC_PROXY_ENV_VAR = "GRPC_PROXY_EXP";
    static final String PROXY_SCHEME = "https";
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(ProxyDetectorImpl.class.getName());
    private final AuthenticationProvider authenticationProvider;
    private final InetSocketAddress overrideProxyAddress;
    private final Supplier<ProxySelector> proxySelector;

    /* renamed from: io.grpc.internal.ProxyDetectorImpl$AuthenticationProvider */
    interface AuthenticationProvider {
        PasswordAuthentication requestPasswordAuthentication(String str, InetAddress inetAddress, int i, String str2, String str3, String str4);
    }

    public ProxyDetectorImpl() {
        this(DEFAULT_PROXY_SELECTOR, DEFAULT_AUTHENTICATOR, System.getenv(GRPC_PROXY_ENV_VAR));
    }

    @VisibleForTesting
    ProxyDetectorImpl(Supplier<ProxySelector> supplier, AuthenticationProvider authenticationProvider2, @Nullable String str) {
        this.proxySelector = (Supplier) Preconditions.checkNotNull(supplier);
        this.authenticationProvider = (AuthenticationProvider) Preconditions.checkNotNull(authenticationProvider2);
        if (str != null) {
            this.overrideProxyAddress = overrideProxy(str);
        } else {
            this.overrideProxyAddress = null;
        }
    }

    @Nullable
    public ProxiedSocketAddress proxyFor(SocketAddress socketAddress) throws IOException {
        if (!(socketAddress instanceof InetSocketAddress)) {
            return null;
        }
        if (this.overrideProxyAddress != null) {
            return HttpConnectProxiedSocketAddress.newBuilder().setProxyAddress(this.overrideProxyAddress).setTargetAddress((InetSocketAddress) socketAddress).build();
        }
        return detectProxy((InetSocketAddress) socketAddress);
    }

    private ProxiedSocketAddress detectProxy(InetSocketAddress inetSocketAddress) throws IOException {
        String str = null;
        try {
            try {
                URI uri = new URI("https", null, GrpcUtil.getHost(inetSocketAddress), inetSocketAddress.getPort(), null, null, null);
                ProxySelector proxySelector2 = (ProxySelector) this.proxySelector.get();
                if (proxySelector2 == null) {
                    log.log(Level.FINE, "proxy selector is null, so continuing without proxy lookup");
                    return null;
                }
                List select = proxySelector2.select(uri);
                if (select.size() > 1) {
                    log.warning("More than 1 proxy detected, gRPC will select the first one");
                }
                Proxy proxy = (Proxy) select.get(0);
                if (proxy.type() == Type.DIRECT) {
                    return null;
                }
                InetSocketAddress inetSocketAddress2 = (InetSocketAddress) proxy.address();
                PasswordAuthentication requestPasswordAuthentication = this.authenticationProvider.requestPasswordAuthentication(GrpcUtil.getHost(inetSocketAddress2), inetSocketAddress2.getAddress(), inetSocketAddress2.getPort(), "https", "", null);
                if (inetSocketAddress2.isUnresolved()) {
                    inetSocketAddress2 = new InetSocketAddress(InetAddress.getByName(inetSocketAddress2.getHostName()), inetSocketAddress2.getPort());
                }
                Builder proxyAddress = HttpConnectProxiedSocketAddress.newBuilder().setTargetAddress(inetSocketAddress).setProxyAddress(inetSocketAddress2);
                if (requestPasswordAuthentication == null) {
                    return proxyAddress.build();
                }
                Builder username = proxyAddress.setUsername(requestPasswordAuthentication.getUserName());
                if (requestPasswordAuthentication.getPassword() != null) {
                    str = new String(requestPasswordAuthentication.getPassword());
                }
                return username.setPassword(str).build();
            } catch (URISyntaxException e) {
                log.log(Level.WARNING, "Failed to construct URI for proxy lookup, proceeding without proxy", e);
                return null;
            }
        } catch (Throwable th) {
            log.log(Level.WARNING, "Failed to get host for proxy lookup, proceeding without proxy", th);
            return null;
        }
    }

    private static InetSocketAddress overrideProxy(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":", 2);
        int i = 80;
        if (split.length > 1) {
            i = Integer.parseInt(split[1]);
        }
        log.warning("Detected GRPC_PROXY_EXP and will honor it, but this feature will be removed in a future release. Use the JVM flags \"-Dhttps.proxyHost=HOST -Dhttps.proxyPort=PORT\" to set the https proxy for this JVM.");
        return new InetSocketAddress(split[0], i);
    }
}
