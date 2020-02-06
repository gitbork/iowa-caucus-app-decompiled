package p006io.sentry.event.helper;

import javax.servlet.http.HttpServletRequest;

/* renamed from: io.sentry.event.helper.RemoteAddressResolver */
public interface RemoteAddressResolver {
    String getRemoteAddress(HttpServletRequest httpServletRequest);
}
