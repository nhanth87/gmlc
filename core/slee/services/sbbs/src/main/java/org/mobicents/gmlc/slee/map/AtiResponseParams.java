package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.primitives.Time;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.*;

public class AtiResponseParams {
    private int ci;
    private int mcc;
    private double uncertainty;
    private IMEI imei;
    private SubscriberState subscriberState;
    private PSSubscriberState psSubscriberState;
    private PSSubscriberState epsSubscriberState;
    private MSClassmark2 msClassmark2;
    private GPRSMSClass gprsMSClass;
    private MNPInfoRes mnpInfoRes;
    private Time lastUEActivityTime;
    private UsedRATType lastRATType;

    public void setLocationInformationGPRS(LocationInformationGPRS locInfoGPRS) {}
    public LocationInformationGPRS getLocationInformationGPRS() { return null; }
    public void setPsSubscriberState(PSSubscriberState psState) { this.psSubscriberState = psState; }
    public PSSubscriberState getPsSubscriberState() { return this.psSubscriberState; }
    public void setEpsSubscriberState(PSSubscriberState epsState) { this.epsSubscriberState = epsState; }
    public PSSubscriberState getEpsSubscriberState() { return this.epsSubscriberState; }
    public void setMsClassmark2(MSClassmark2 msClassmark2) { this.msClassmark2 = msClassmark2; }
    public MSClassmark2 getMsClassmark2() { return this.msClassmark2; }
    public void setGprsMSClass(GPRSMSClass gprsMSClass) { this.gprsMSClass = gprsMSClass; }
    public GPRSMSClass getGprsMSClass() { return this.gprsMSClass; }
    public void setMnpInfoRes(MNPInfoRes mnpInfoRes) { this.mnpInfoRes = mnpInfoRes; }
    public MNPInfoRes getMnpInfoRes() { return this.mnpInfoRes; }
    public void setImsVoiceOverPsSessionsIndication(IMSVoiceOverPsSessionsIndication imsVoice) {}
    public void setTimeZone(TimeZone timeZone) {}
    public void setDaylightSavingTime(DaylightSavingTime daylightSavingTime) {}
    public void setLocationInformation5GS(LocationInformation5GS locationInformation5GS) {}
    public void setLocationInformationEPS(LocationInformationEPS locationInformationEPS) {}
    public LocationInformationEPS getLocationInformationEPS() { return null; }
    public void setLastRATType(UsedRATType usedRATType) { this.lastRATType = usedRATType; }
    public UsedRATType getLastRATType() { return this.lastRATType; }
    public void setSubscriberState(SubscriberState subscriberState) { this.subscriberState = subscriberState; }
    public SubscriberState getSubscriberState() { return this.subscriberState; }
    public void setLocationInformation(LocationInformation locationInformation) {}
    public LocationInformation getLocationInformation() { return null; }
    public LocationInformation5GS getLocationInformation5GS() { return null; }
    public void setImei(IMEI imei) { this.imei = imei; }
    public IMEI getImei() { return this.imei; }
    public void setLastUEActivityTime(Time time) { this.lastUEActivityTime = time; }
    public Time getLastUEActivityTime() { return this.lastUEActivityTime; }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public void setUncertainty(double uncertainty) {
        this.uncertainty = uncertainty;
    }
}
