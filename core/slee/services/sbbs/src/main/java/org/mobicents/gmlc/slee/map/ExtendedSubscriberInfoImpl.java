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

import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.*;

import java.io.Serializable;

/**
 * Implementation of ExtendedSubscriberInfo that wraps a base SubscriberInfo
 * and provides additional methods not available in jSS7 9.2.5.
 * 
 * This implementation uses a delegation pattern:
 * - Methods available in the base SubscriberInfo are delegated to the wrapped object
 * - Extended methods return directly set values or null if not set
 * 
 * This class is immutable once constructed.
 */
public class ExtendedSubscriberInfoImpl implements SubscriberInfoFactory.ExtendedSubscriberInfo, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Base SubscriberInfo to delegate standard methods to
    private final SubscriberInfo baseSubscriberInfo;
    
    // Extended fields (may be set directly or extracted from base via reflection)
    private final TimeZone timeZone;
    private final DaylightSavingTime daylightSavingTime;
    private final IMSVoiceOverPsSessionsIndication imsVoiceOverPsSessionsIndication;
    private final UEActivityTime lastUEActivityTime;
    private final RATType lastRATType;
    private final EPSSubscriberState epsSubscriberState;
    private final LocationInformationEPS locationInformationEPS;
    private final LocationInformation5GS locationInformation5GS;
    private final NRLocationInformation nrLocationInformation;
    private final NRCellGlobalId nrCellGlobalId;
    private final NRTAId nrTAId;
    private final IMSI imsi;
    private final ISDNAddressString msisdn;
    
    // Directly stored fields (used when building without a base)
    private final LocationInformation locationInformation;
    private final SubscriberState subscriberState;
    private final MAPExtensionContainer extensionContainer;
    private final LocationInformationGPRS locationInformationGPRS;
    private final PSSubscriberState psSubscriberState;
    private final IMEI imei;
    private final MSClassmark2 msClassmark2;
    private final GPRSMSClass gprsMSClass;
    private final MNPInfoRes mnpInfoRes;
    
    /**
     * Constructs an ExtendedSubscriberInfoImpl that wraps a base SubscriberInfo.
     * Uses reflection to extract extended fields from the base if available.
     * 
     * @param base the base SubscriberInfo to wrap
     */
    public ExtendedSubscriberInfoImpl(SubscriberInfo base) {
        this.baseSubscriberInfo = base;
        
        // Try to extract extended fields from base using reflection
        this.timeZone = (TimeZone) invokeMethod(base, "getTimeZone");
        this.daylightSavingTime = (DaylightSavingTime) invokeMethod(base, "getDaylightSavingTime");
        this.imsVoiceOverPsSessionsIndication = (IMSVoiceOverPsSessionsIndication) invokeMethod(base, "getImsVoiceOverPsSessionsIndication");
        this.lastUEActivityTime = (UEActivityTime) invokeMethod(base, "getLastUEActivityTime");
        this.lastRATType = (RATType) invokeMethod(base, "getLastRATType");
        this.epsSubscriberState = (EPSSubscriberState) invokeMethod(base, "getEPSSubscriberState");
        this.locationInformationEPS = (LocationInformationEPS) invokeMethod(base, "getLocationInformationEPS");
        this.locationInformation5GS = (LocationInformation5GS) invokeMethod(base, "getLocationInformation5GS");
        this.nrLocationInformation = (NRLocationInformation) invokeMethod(base, "getNRLocationInformation");
        this.nrCellGlobalId = (NRCellGlobalId) invokeMethod(base, "getNRCellGlobalId");
        this.nrTAId = (NRTAId) invokeMethod(base, "getNRTAId");
        this.imsi = (IMSI) invokeMethod(base, "getIMSI");
        // Try getMsisdn() first, fallback to getMSISDN() for compatibility
        ISDNAddressString msisdnFromBase = (ISDNAddressString) invokeMethod(base, "getMsisdn");
        if (msisdnFromBase == null) {
            msisdnFromBase = (ISDNAddressString) invokeMethod(base, "getMSISDN");
        }
        this.msisdn = msisdnFromBase;
        
        // These will be delegated to base, so set to null
        this.locationInformation = null;
        this.subscriberState = null;
        this.extensionContainer = null;
        this.locationInformationGPRS = null;
        this.psSubscriberState = null;
        this.imei = null;
        this.msClassmark2 = null;
        this.gprsMSClass = null;
        this.mnpInfoRes = null;
    }
    
    /**
     * Constructs an ExtendedSubscriberInfoImpl with all fields directly set.
     * This constructor is used by the Builder and Factory create methods.
     */
    public ExtendedSubscriberInfoImpl(
            LocationInformation locationInformation,
            SubscriberState subscriberState,
            MAPExtensionContainer extensionContainer,
            LocationInformationGPRS locationInformationGPRS,
            PSSubscriberState psSubscriberState,
            IMEI imei,
            MSClassmark2 msClassmark2,
            GPRSMSClass gprsMSClass,
            MNPInfoRes mnpInfoRes,
            TimeZone timeZone,
            DaylightSavingTime daylightSavingTime,
            IMSVoiceOverPsSessionsIndication imsVoiceOverPsSessionsIndication,
            UEActivityTime lastUEActivityTime,
            RATType lastRATType,
            EPSSubscriberState epsSubscriberState,
            LocationInformationEPS locationInformationEPS,
            LocationInformation5GS locationInformation5GS,
            NRLocationInformation nrLocationInformation,
            NRCellGlobalId nrCellGlobalId,
            NRTAId nrTAId,
            IMSI imsi,
            ISDNAddressString msisdn) {
        
        this.baseSubscriberInfo = null;
        
        // Standard fields
        this.locationInformation = locationInformation;
        this.subscriberState = subscriberState;
        this.extensionContainer = extensionContainer;
        this.locationInformationGPRS = locationInformationGPRS;
        this.psSubscriberState = psSubscriberState;
        this.imei = imei;
        this.msClassmark2 = msClassmark2;
        this.gprsMSClass = gprsMSClass;
        this.mnpInfoRes = mnpInfoRes;
        
        // Extended fields
        this.timeZone = timeZone;
        this.daylightSavingTime = daylightSavingTime;
        this.imsVoiceOverPsSessionsIndication = imsVoiceOverPsSessionsIndication;
        this.lastUEActivityTime = lastUEActivityTime;
        this.lastRATType = lastRATType;
        this.epsSubscriberState = epsSubscriberState;
        this.locationInformationEPS = locationInformationEPS;
        this.locationInformation5GS = locationInformation5GS;
        this.nrLocationInformation = nrLocationInformation;
        this.nrCellGlobalId = nrCellGlobalId;
        this.nrTAId = nrTAId;
        this.imsi = imsi;
        this.msisdn = msisdn;
    }
    
    // ==================== Standard SubscriberInfo Methods ====================
    
    @Override
    public LocationInformation getLocationInformation() {
        if (baseSubscriberInfo != null) {
            return baseSubscriberInfo.getLocationInformation();
        }
        return locationInformation;
    }
    
    @Override
    public SubscriberState getSubscriberState() {
        if (baseSubscriberInfo != null) {
            return baseSubscriberInfo.getSubscriberState();
        }
        return subscriberState;
    }
    
    @Override
    public MAPExtensionContainer getExtensionContainer() {
        if (baseSubscriberInfo != null) {
            MAPExtensionContainer container = (MAPExtensionContainer) baseSubscriberInfo.getExtensionContainer();
            return container;
        }
        return extensionContainer;
    }
    
    @Override
    public LocationInformationGPRS getLocationInformationGPRS() {
        if (baseSubscriberInfo != null) {
            LocationInformationGPRS gprsInfo = (LocationInformationGPRS) baseSubscriberInfo.getLocationInformationGPRS();
            return gprsInfo;
        }
        return locationInformationGPRS;
    }
    
    @Override
    public PSSubscriberState getPSSubscriberState() {
        if (baseSubscriberInfo != null) {
            return baseSubscriberInfo.getPSSubscriberState();
        }
        return psSubscriberState;
    }
    
    @Override
    public IMEI getIMEI() {
        if (baseSubscriberInfo != null) {
            return baseSubscriberInfo.getIMEI();
        }
        return imei;
    }
    
    @Override
    public MSClassmark2 getMSClassmark2() {
        if (baseSubscriberInfo != null) {
            // Try to get from base via reflection first
            MSClassmark2 fromBase = (MSClassmark2) invokeMethod(baseSubscriberInfo, "getMSClassmark2");
            if (fromBase != null) {
                return fromBase;
            }
        }
        return msClassmark2;
    }
    
    @Override
    public GPRSMSClass getGPRSMSClass() {
        if (baseSubscriberInfo != null) {
            // Try to get from base via reflection first
            GPRSMSClass fromBase = (GPRSMSClass) invokeMethod(baseSubscriberInfo, "getGPRSMSClass");
            if (fromBase != null) {
                return fromBase;
            }
        }
        return gprsMSClass;
    }
    
    @Override
    public MNPInfoRes getMNPInfoRes() {
        if (baseSubscriberInfo != null) {
            // Try to get from base via reflection first
            MNPInfoRes fromBase = (MNPInfoRes) invokeMethod(baseSubscriberInfo, "getMNPInfoRes");
            if (fromBase != null) {
                return fromBase;
            }
        }
        return mnpInfoRes;
    }
    
    // ==================== Extended Methods ====================
    
    @Override
    public TimeZone getTimeZone() {
        return timeZone;
    }
    
    @Override
    public DaylightSavingTime getDaylightSavingTime() {
        return daylightSavingTime;
    }
    
    @Override
    public IMSVoiceOverPsSessionsIndication getIMSVoiceOverPsSessionsIndication() {
        return imsVoiceOverPsSessionsIndication;
    }
    
    @Override
    public UEActivityTime getLastUEActivityTime() {
        return lastUEActivityTime;
    }
    
    @Override
    public RATType getLastRATType() {
        return lastRATType;
    }
    
    @Override
    public EPSSubscriberState getEPSSubscriberState() {
        return epsSubscriberState;
    }
    
    @Override
    public LocationInformationEPS getLocationInformationEPS() {
        return locationInformationEPS;
    }
    
    @Override
    public LocationInformation5GS getLocationInformation5GS() {
        return locationInformation5GS;
    }
    
    @Override
    public NRLocationInformation getNRLocationInformation() {
        return nrLocationInformation;
    }
    
    @Override
    public NRCellGlobalId getNRCellGlobalId() {
        return nrCellGlobalId;
    }
    
    @Override
    public NRTAId getNRTAId() {
        return nrTAId;
    }
    
    @Override
    public IMSI getIMSI() {
        return imsi;
    }
    
    @Override
    public IMSVoiceOverPsSessionsIndication getImsVoiceOverPsSessionsIndication() {
        return imsVoiceOverPsSessionsIndication;
    }
    
    @Override
    public ISDNAddressString getMsisdn() {
        return msisdn;
    }
    
    @Override
    public MNPInfoRes getMNPInfoResult() {
        // Alias method for getMNPInfoRes() - returns the same value
        return getMNPInfoRes();
    }
    
    @Override
    public SubscriberInfo getBaseSubscriberInfo() {
        return baseSubscriberInfo;
    }
    
    // ==================== Helper Methods ====================
    
    /**
     * Invokes a method on the target object using reflection.
     * Returns null if the method doesn't exist or invocation fails.
     * 
     * @param target the target object
     * @param methodName the method name to invoke
     * @return the result of the method invocation, or null
     */
    private static Object invokeMethod(Object target, String methodName) {
        if (target == null) {
            return null;
        }
        try {
            java.lang.reflect.Method method = target.getClass().getMethod(methodName);
            return method.invoke(target);
        } catch (NoSuchMethodException e) {
            // Method doesn't exist in this version - that's OK
            return null;
        } catch (Exception e) {
            // Other errors - log and return null
            return null;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExtendedSubscriberInfoImpl [");
        
        if (getLocationInformation() != null) {
            sb.append("locationInformation=").append(getLocationInformation()).append(", ");
        }
        if (getSubscriberState() != null) {
            sb.append("subscriberState=").append(getSubscriberState()).append(", ");
        }
        if (getIMEI() != null) {
            sb.append("imei=").append(getIMEI()).append(", ");
        }
        if (getIMSI() != null) {
            sb.append("imsi=").append(getIMSI()).append(", ");
        }
        if (getMsisdn() != null) {
            sb.append("msisdn=").append(getMsisdn()).append(", ");
        }
        if (getLocationInformationGPRS() != null) {
            sb.append("locationInformationGPRS=").append(getLocationInformationGPRS()).append(", ");
        }
        if (getPSSubscriberState() != null) {
            sb.append("psSubscriberState=").append(getPSSubscriberState()).append(", ");
        }
        if (getMSClassmark2() != null) {
            sb.append("msClassmark2=").append(getMSClassmark2()).append(", ");
        }
        if (getGPRSMSClass() != null) {
            sb.append("gprsMSClass=").append(getGPRSMSClass()).append(", ");
        }
        if (getMNPInfoRes() != null) {
            sb.append("mnpInfoRes=").append(getMNPInfoRes()).append(", ");
        }
        if (getTimeZone() != null) {
            sb.append("timeZone=").append(getTimeZone()).append(", ");
        }
        if (getDaylightSavingTime() != null) {
            sb.append("daylightSavingTime=").append(getDaylightSavingTime()).append(", ");
        }
        if (getIMSVoiceOverPsSessionsIndication() != null) {
            sb.append("imsVoiceOverPsSessionsIndication=").append(getIMSVoiceOverPsSessionsIndication()).append(", ");
        }
        if (getLastUEActivityTime() != null) {
            sb.append("lastUEActivityTime=").append(getLastUEActivityTime()).append(", ");
        }
        if (getLastRATType() != null) {
            sb.append("lastRATType=").append(getLastRATType()).append(", ");
        }
        if (getEPSSubscriberState() != null) {
            sb.append("epsSubscriberState=").append(getEPSSubscriberState()).append(", ");
        }
        if (getLocationInformationEPS() != null) {
            sb.append("locationInformationEPS=").append(getLocationInformationEPS()).append(", ");
        }
        if (getLocationInformation5GS() != null) {
            sb.append("locationInformation5GS=").append(getLocationInformation5GS()).append(", ");
        }
        if (getNRLocationInformation() != null) {
            sb.append("nrLocationInformation=").append(getNRLocationInformation()).append(", ");
        }
        if (getNRCellGlobalId() != null) {
            sb.append("nrCellGlobalId=").append(getNRCellGlobalId()).append(", ");
        }
        if (getNRTAId() != null) {
            sb.append("nrTAId=").append(getNRTAId()).append(", ");
        }
        
        // Remove trailing ", " if present
        int len = sb.length();
        if (len > 2 && sb.substring(len - 2).equals(", ")) {
            sb.setLength(len - 2);
        }
        
        sb.append("]");
        return sb.toString();
    }
}
