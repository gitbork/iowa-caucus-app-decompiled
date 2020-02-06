package p006io.opencensus.trace;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p006io.opencensus.common.Clock;
import p006io.opencensus.internal.Provider;
import p006io.opencensus.trace.config.TraceConfig;
import p006io.opencensus.trace.export.ExportComponent;
import p006io.opencensus.trace.propagation.PropagationComponent;

/* renamed from: io.opencensus.trace.Tracing */
public final class Tracing {
    private static final Logger logger = Logger.getLogger(Tracing.class.getName());
    private static final TraceComponent traceComponent = loadTraceComponent(TraceComponent.class.getClassLoader());

    public static Tracer getTracer() {
        return traceComponent.getTracer();
    }

    public static PropagationComponent getPropagationComponent() {
        return traceComponent.getPropagationComponent();
    }

    public static Clock getClock() {
        return traceComponent.getClock();
    }

    public static ExportComponent getExportComponent() {
        return traceComponent.getExportComponent();
    }

    public static TraceConfig getTraceConfig() {
        return traceComponent.getTraceConfig();
    }

    static TraceComponent loadTraceComponent(@Nullable ClassLoader classLoader) {
        try {
            return (TraceComponent) Provider.createInstance(Class.forName("io.opencensus.impl.trace.TraceComponentImpl", true, classLoader), TraceComponent.class);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Couldn't load full implementation for TraceComponent, now trying to load lite implementation.", e);
            try {
                return (TraceComponent) Provider.createInstance(Class.forName("io.opencensus.impllite.trace.TraceComponentImplLite", true, classLoader), TraceComponent.class);
            } catch (ClassNotFoundException e2) {
                logger.log(Level.FINE, "Couldn't load lite implementation for TraceComponent, now using default implementation for TraceComponent.", e2);
                return TraceComponent.newNoopTraceComponent();
            }
        }
    }

    private Tracing() {
    }
}
