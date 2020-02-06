package p006io.sentry.event.helper;

import javax.servlet.http.HttpServletRequest;
import p006io.sentry.event.EventBuilder;
import p006io.sentry.event.interfaces.HttpInterface;
import p006io.sentry.event.interfaces.UserInterface;
import p006io.sentry.servlet.SentryServletRequestListener;

/* renamed from: io.sentry.event.helper.HttpEventBuilderHelper */
public class HttpEventBuilderHelper implements EventBuilderHelper {
    private final RemoteAddressResolver remoteAddressResolver;

    public HttpEventBuilderHelper() {
        this.remoteAddressResolver = new BasicRemoteAddressResolver();
    }

    public HttpEventBuilderHelper(RemoteAddressResolver remoteAddressResolver2) {
        this.remoteAddressResolver = remoteAddressResolver2;
    }

    public void helpBuildingEvent(EventBuilder eventBuilder) {
        HttpServletRequest servletRequest = SentryServletRequestListener.getServletRequest();
        if (servletRequest != null) {
            addHttpInterface(eventBuilder, servletRequest);
            addUserInterface(eventBuilder, servletRequest);
        }
    }

    private void addHttpInterface(EventBuilder eventBuilder, HttpServletRequest httpServletRequest) {
        eventBuilder.withSentryInterface(new HttpInterface(httpServletRequest, this.remoteAddressResolver), false);
    }

    private void addUserInterface(EventBuilder eventBuilder, HttpServletRequest httpServletRequest) {
        eventBuilder.withSentryInterface(new UserInterface(null, httpServletRequest.getUserPrincipal() != null ? httpServletRequest.getUserPrincipal().getName() : null, this.remoteAddressResolver.getRemoteAddress(httpServletRequest), null), false);
    }

    public RemoteAddressResolver getRemoteAddressResolver() {
        return this.remoteAddressResolver;
    }
}
