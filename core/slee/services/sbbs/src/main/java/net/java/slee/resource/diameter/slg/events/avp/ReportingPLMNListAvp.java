// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface ReportingPLMNListAvp {

    void setPLMNIDList(PLMNIDListAvp plmnIdList);

    void setPrioritizedListIndicator(PrioritizedListIndicator indicator);

    void setPrioritizedListIndicator(int indicator);
}
