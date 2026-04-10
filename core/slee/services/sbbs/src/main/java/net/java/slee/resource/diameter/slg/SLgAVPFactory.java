// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg;

import net.java.slee.resource.diameter.slg.events.avp.*;

public interface SLgAVPFactory {

    LCSRequestorNameAvp createLCSRequestorName();

    LCSQoSAvp createLCSQoS();

    LCSEPSClientNameAvp createLCSEPSClientName();

    AreaAvp createArea();

    AdditionalAreaAvp createAdditionalArea();

    AreaDefinitionAvp createAreaDefinition();

    AreaEventInfoAvp createAreaEventInfo();

    LCSPrivacyCheckNonSessionAvp createLCSPrivacyCheckNonSession();

    LCSPrivacyCheckSessionAvp createLCSPrivacyCheckSession();

    PeriodicLDRInfoAvp createPeriodicLDRInformation();

    MotionEventInfoAvp createMotionEventInfo();

    PLMNIDListAvp createPLMNIDList();

    ReportingPLMNListAvp createReportingPLMNList();
}
