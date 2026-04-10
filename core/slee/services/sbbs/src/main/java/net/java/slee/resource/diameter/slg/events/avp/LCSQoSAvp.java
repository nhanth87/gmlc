// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface LCSQoSAvp {

    void setLCSQoSClass(LCSQoSClass qosClass);

    void setHorizontalAccuracy(int accuracy);

    void setVerticalAccuracy(int accuracy);

    void setVerticalCoordinateRequested(VerticalRequested requested);

    void setVerticalRequested(VerticalRequested requested);

    void setVerticalRequested(int requested);

    void setResponseTime(ResponseTime responseTime);
}
