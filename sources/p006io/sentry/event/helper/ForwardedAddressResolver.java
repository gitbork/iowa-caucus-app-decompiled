package p006io.sentry.event.helper;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import p006io.sentry.util.Util;

/* renamed from: io.sentry.event.helper.ForwardedAddressResolver */
public class ForwardedAddressResolver implements RemoteAddressResolver {
    private BasicRemoteAddressResolver basicRemoteAddressResolver = new BasicRemoteAddressResolver();

    private static String firstAddress(String str) {
        return ((String) Arrays.asList(str.split(",")).get(0)).trim();
    }

    public String getRemoteAddress(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (!Util.isNullOrEmpty(header)) {
            return firstAddress(header);
        }
        return this.basicRemoteAddressResolver.getRemoteAddress(httpServletRequest);
    }
}
