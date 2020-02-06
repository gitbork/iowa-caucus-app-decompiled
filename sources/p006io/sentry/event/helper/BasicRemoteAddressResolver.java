package p006io.sentry.event.helper;

import javax.servlet.http.HttpServletRequest;

/* renamed from: io.sentry.event.helper.BasicRemoteAddressResolver */
public class BasicRemoteAddressResolver implements RemoteAddressResolver {
    public String getRemoteAddress(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRemoteAddr();
    }
}
