/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.*;

/**
 * Stub class for PsiResponseParams
 *
 * @author avilanicolas
 */
public class PsiResponseParams {
    private int ci;
    private int mcc;
    private int mnc;
    private int lac;
    private double uncertainty;
    private LocationInformation locationInformation;
    private SriSmResponseParams sriForSmResponse;
    private SriResponseValues sriResponse;

    public void setPsiServiceType(String serviceType) {}
    public void setPsiOnlyImsi(String imsi) {}
    public void setSriForSMImsi(String imsi) {}
    public void setSriForSmResponse(SriSmResponseParams sriSmResponseParams) {}
    public void setSriResponse(SriResponseValues sriResponseValues) {}
    public void setLocationInformation(LocationInformation locationInformation) {}
    public LocationInformation5GS getLocationInformation5GS() { return null; }
    public void setSubscriberState(SubscriberState subscriberState) {}
    public SubscriberState getSubscriberState() { return null; }
    public void setLocationInformationGPRS(LocationInformationGPRS locationInformationGPRS) {}
    public LocationInformationGPRS getLocationInformationGPRS() { return null; }
    public void setPsSubscriberState(PSSubscriberState psSubscriberState) {}
    public PSSubscriberState getPsSubscriberState() { return null; }
    public void setMsClassmark2(MSClassmark2 msClassmark2) {}
    public MSClassmark2 getMsClassmark2() { return null; }
    public void setPsiOnlyNnn(String nnn) {}
    public void setGprsMSClass(GPRSMSClass gprsMSClass) {}
    public GPRSMSClass getGprsMSClass() { return null; }
    public void setMnpInfoRes(MNPInfoRes mnpInfoRes) {}
    public MNPInfoRes getMnpInfoRes() { return null; }
    public void setImsVoiceOverPsSessionsIndication(IMSVoiceOverPsSessionsIndication imsVoice) {}
    public void setLastRATType(UsedRATType usedRATType) {}
    public int getLastRATType() { return -1; }
    public void setEpsSubscriberState(PSSubscriberState epsSubscriberState) {}
    public PSSubscriberState getEpsSubscriberState() { return null; }
    public void setLocationInformationEPS(LocationInformationEPS locationInformationEPS) {}
    public LocationInformationEPS getLocationInformationEPS() { return null; }
    public void setImei(IMEI imei) {}
    public IMEI getImei() { return null; }
    public void setLastUEActivityTime(Object lastUEActivityTime) {}
    public Object getLastUEActivityTime() { return null; }
    public void setTimeZone(Object timeZone) {}
    public void setDaylightSavingTime(Object daylightSavingTime) {}
    public void setLocationInformation5GS(LocationInformation5GS locationInformation5GS) {}
    public void setCi(int ci) {}
    public void setMcc(int mcc) {}
    public void setMnc(int mnc) {}
    public void setLac(int lac) {}
    public void setUncertainty(double uncertainty) {}
    public LocationInformation getLocationInformation() { return locationInformation; }
    public SriSmResponseParams getSriForSmResponse() { return sriForSmResponse; }
    public SriResponseValues getSriResponse() { return sriResponse; }
    
    // Stub methods for PSI-only mode
    public String getPsiOnlyImsi() { return null; }
    public String getPsiOnlyNnn() { return null; }
    public String getPsiServiceType() { return null; }
    
    // FIXME: Added stub method for getImsi() - needs proper implementation
    public org.restcomm.protocols.ss7.map.api.primitives.IMSI getImsi() { return null; }
}
