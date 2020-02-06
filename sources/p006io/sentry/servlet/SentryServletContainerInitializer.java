package p006io.sentry.servlet;

import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/* renamed from: io.sentry.servlet.SentryServletContainerInitializer */
public class SentryServletContainerInitializer implements ServletContainerInitializer {
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        servletContext.addListener(SentryServletRequestListener.class);
    }
}
