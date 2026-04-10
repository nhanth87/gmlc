// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events;

import net.java.slee.resource.diameter.base.events.avp.Address;
import net.java.slee.resource.diameter.base.events.avp.AuthSessionStateType;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.events.avp.*;

public interface ProvideLocationRequest {

    void setUserName(String userName);

    void setMSISDN(byte[] msisdn);

    void setLCSEPSClientName(LCSEPSClientNameAvp name);

    void setLCSClientType(LCSClientType type);

    void setSLgLocationType(SLgLocationType locationType);

    void setLCSRequestorName(LCSRequestorNameAvp name);

    void setLCSQoS(LCSQoSAvp qos);

    void setLCSPrivacyCheckNonSession(LCSPrivacyCheckNonSessionAvp privacyCheck);

    void setLCSPrivacyCheckSession(LCSPrivacyCheckSessionAvp privacyCheck);

    void setDeferredLocationType(Long deferredLocationType);

    void setVelocityRequested(VelocityRequested velocityRequested);

    void setAreaEventInfo(AreaEventInfoAvp areaEventInfo);

    void setPeriodicLDRInformation(PeriodicLDRInfoAvp periodicLDRInfo);

    void setMotionEventInfo(MotionEventInfoAvp motionEventInfo);

    void setReportingPLMNList(ReportingPLMNListAvp reportingPLMNList);

    void setLCSReferenceNumber(byte[] lcsReferenceNumber);

    void setIMEI(String imei);

    void setLCSSupportedGADShapes(Long shapes);

    void setLCSCodeword(String codeword);

    void setServiceSelection(String serviceSelection);

    void setGMLCAddress(Address address);

    void setAuthSessionState(AuthSessionStateType authSessionState);

    void setPLRFlags(long flags);

    PeriodicLDRInfoAvp getPeriodicLDRInformation();

    String getSessionId();

    DiameterIdentity getDestinationHost();

    DiameterIdentity getDestinationRealm();
}
