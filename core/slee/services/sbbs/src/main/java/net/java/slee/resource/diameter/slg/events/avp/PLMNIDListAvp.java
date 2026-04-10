// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface PLMNIDListAvp {

    void setVisitedPLMNId(byte[] plmnId);

    void setPeriodicLocationSupportIndicator(PeriodicLocationSupportIndicator indicator);

    void setPeriodicLocationSupportIndicator(int indicator);
}
