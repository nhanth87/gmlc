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

/**
 * Factory class for creating ExtendedSubscriberInfo instances that wrap
 * the base jSS7 SubscriberInfo and provide additional methods not available
 * in jSS7 9.2.5.
 * 
 * This allows MobileCoreNetworkInterfaceSbb to access extended subscriber
 * information fields without directly modifying the jSS7 library.
 * 
 * Usage:
 * <pre>
 * SubscriberInfo baseInfo = event.getSubscriberInfo();
 * SubscriberInfoFactory.ExtendedSubscriberInfo extended = 
 *     SubscriberInfoFactory.extend(baseInfo);
 * 
 * // Now you can access all methods:
 * TimeZone tz = extended.getTimeZone();
 * DaylightSavingTime dst = extended.getDaylightSavingTime();
 * LocationInformation5GS loc5gs = extended.getLocationInformation5GS();
 * </pre>
 */
public class SubscriberInfoFactory {
    
    /**
     * Wraps a base SubscriberInfo with an ExtendedSubscriberInfo implementation.
     * 
     * @param base the base SubscriberInfo from jSS7
     * @return an ExtendedSubscriberInfo that wraps the base
     */
    public static ExtendedSubscriberInfo extend(SubscriberInfo base) {
        if (base == null) {
            return null;
        }
        if (base instanceof ExtendedSubscriberInfo) {
            return (ExtendedSubscriberInfo) base;
        }
        return new ExtendedSubscriberInfoImpl(base);
    }
    
    /**
     * Extended SubscriberInfo interface that includes all methods required by
     * MobileCoreNetworkInterfaceSbb but not available in jSS7 9.2.5.
     */
    public interface ExtendedSubscriberInfo extends SubscriberInfo {
        
        /**
         * Gets the timezone information.
         * @return TimeZone or null if not available
         */
        TimeZone getTimeZone();
        
        /**
         * Gets the daylight saving time information.
         * @return DaylightSavingTime or null if not available
         */
        DaylightSavingTime getDaylightSavingTime();
        
        /**
         * Gets the IMS voice over PS sessions indication.
         * @return IMSVoiceOverPsSessionsIndication or null if not available
         */
        IMSVoiceOverPsSessionsIndication getIMSVoiceOverPsSessionsIndication();
        
        /**
         * Gets the MNP info result.
         * @return MNPInfoRes or null if not available
         */
        MNPInfoRes getMNPInfoRes();
        
        /**
         * Gets the MS classmark 2.
         * @return MSClassmark2 or null if not available
         */
        MSClassmark2 getMSClassmark2();
        
        /**
         * Gets the GPRS MS class.
         * @return GPRSMSClass or null if not available
         */
        GPRSMSClass getGPRSMSClass();
        
        /**
         * Gets the last UE activity time.
         * @return UEActivityTime or null if not available
         */
        UEActivityTime getLastUEActivityTime();
        
        /**
         * Gets the last RAT type.
         * @return RATType or null if not available
         */
        RATType getLastRATType();
        
        /**
         * Gets the EPS subscriber state.
         * @return EPSSubscriberState or null if not available
         */
        EPSSubscriberState getEPSSubscriberState();
        
        /**
         * Gets the location information for EPS.
         * @return LocationInformationEPS or null if not available
         */
        LocationInformationEPS getLocationInformationEPS();
        
        /**
         * Gets the location information for 5GS.
         * @return LocationInformation5GS or null if not available
         */
        LocationInformation5GS getLocationInformation5GS();
        
        /**
         * Gets the NR location information.
         * @return NRLocationInformation or null if not available
         */
        NRLocationInformation getNRLocationInformation();
        
        /**
         * Gets the NR cell global ID.
         * @return NRCellGlobalId or null if not available
         */
        NRCellGlobalId getNRCellGlobalId();
        
        /**
         * Gets the NR TAI.
         * @return NRTAId or null if not available
         */
        NRTAId getNRTAId();
        
        /**
         * Gets the IMSI.
         * @return IMSI or null if not available
         */
        IMSI getIMSI();
        
        /**
         * Gets the MSISDN.
         * @return ISDNAddressString or null if not available
         */
        ISDNAddressString getMsisdn();
        
        /**
         * Gets the base SubscriberInfo that this object wraps.
         * @return the base SubscriberInfo
         */
        SubscriberInfo getBaseSubscriberInfo();
    }
    
    /**
     * Creates a new ExtendedSubscriberInfoImpl with all fields directly set.
     * This is useful for testing or when building a SubscriberInfo from scratch.
     * 
     * @param locationInformation LocationInformation
     * @param subscriberState SubscriberState
     * @param extensionContainer MAPExtensionContainer
     * @param locationInformationGPRS LocationInformationGPRS
     * @param psSubscriberState PSSubscriberState
     * @param imei IMEI
     * @param msClassmark2 MSClassmark2
     * @param gprsMSClass GPRSMSClass
     * @param mnpInfoRes MNPInfoRes
     * @param timeZone TimeZone
     * @param daylightSavingTime DaylightSavingTime
     * @param imsVoiceOverPs IMSVoiceOverPsSessionsIndication
     * @param lastUEActivityTime UEActivityTime
     * @param lastRATType RATType
     * @param epsSubscriberState EPSSubscriberState
     * @param locationInformationEPS LocationInformationEPS
     * @param locationInformation5GS LocationInformation5GS
     * @param nrLocationInformation NRLocationInformation
     * @param nrCellGlobalId NRCellGlobalId
     * @param nrTAId NRTAId
     * @param imsi IMSI
     * @param msisdn ISDNAddressString
     * @return a new ExtendedSubscriberInfo instance
     */
    public static ExtendedSubscriberInfo create(
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
            IMSVoiceOverPsSessionsIndication imsVoiceOverPs,
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
        
        return new ExtendedSubscriberInfoImpl(
                locationInformation,
                subscriberState,
                extensionContainer,
                locationInformationGPRS,
                psSubscriberState,
                imei,
                msClassmark2,
                gprsMSClass,
                mnpInfoRes,
                timeZone,
                daylightSavingTime,
                imsVoiceOverPs,
                lastUEActivityTime,
                lastRATType,
                epsSubscriberState,
                locationInformationEPS,
                locationInformation5GS,
                nrLocationInformation,
                nrCellGlobalId,
                nrTAId,
                imsi,
                msisdn);
    }
    
    /**
     * Creates a builder for constructing ExtendedSubscriberInfo instances.
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Builder pattern for creating ExtendedSubscriberInfo instances.
     */
    public static class Builder {
        private LocationInformation locationInformation;
        private SubscriberState subscriberState;
        private MAPExtensionContainer extensionContainer;
        private LocationInformationGPRS locationInformationGPRS;
        private PSSubscriberState psSubscriberState;
        private IMEI imei;
        private MSClassmark2 msClassmark2;
        private GPRSMSClass gprsMSClass;
        private MNPInfoRes mnpInfoRes;
        private TimeZone timeZone;
        private DaylightSavingTime daylightSavingTime;
        private IMSVoiceOverPsSessionsIndication imsVoiceOverPs;
        private UEActivityTime lastUEActivityTime;
        private RATType lastRATType;
        private EPSSubscriberState epsSubscriberState;
        private LocationInformationEPS locationInformationEPS;
        private LocationInformation5GS locationInformation5GS;
        private NRLocationInformation nrLocationInformation;
        private NRCellGlobalId nrCellGlobalId;
        private NRTAId nrTAId;
        private IMSI imsi;
        private ISDNAddressString msisdn;
        
        public Builder locationInformation(LocationInformation val) {
            this.locationInformation = val;
            return this;
        }
        
        public Builder subscriberState(SubscriberState val) {
            this.subscriberState = val;
            return this;
        }
        
        public Builder extensionContainer(MAPExtensionContainer val) {
            this.extensionContainer = val;
            return this;
        }
        
        public Builder locationInformationGPRS(LocationInformationGPRS val) {
            this.locationInformationGPRS = val;
            return this;
        }
        
        public Builder psSubscriberState(PSSubscriberState val) {
            this.psSubscriberState = val;
            return this;
        }
        
        public Builder imei(IMEI val) {
            this.imei = val;
            return this;
        }
        
        public Builder msClassmark2(MSClassmark2 val) {
            this.msClassmark2 = val;
            return this;
        }
        
        public Builder gprsMSClass(GPRSMSClass val) {
            this.gprsMSClass = val;
            return this;
        }
        
        public Builder mnpInfoRes(MNPInfoRes val) {
            this.mnpInfoRes = val;
            return this;
        }
        
        public Builder timeZone(TimeZone val) {
            this.timeZone = val;
            return this;
        }
        
        public Builder daylightSavingTime(DaylightSavingTime val) {
            this.daylightSavingTime = val;
            return this;
        }
        
        public Builder imsVoiceOverPs(IMSVoiceOverPsSessionsIndication val) {
            this.imsVoiceOverPs = val;
            return this;
        }
        
        public Builder lastUEActivityTime(UEActivityTime val) {
            this.lastUEActivityTime = val;
            return this;
        }
        
        public Builder lastRATType(RATType val) {
            this.lastRATType = val;
            return this;
        }
        
        public Builder epsSubscriberState(EPSSubscriberState val) {
            this.epsSubscriberState = val;
            return this;
        }
        
        public Builder locationInformationEPS(LocationInformationEPS val) {
            this.locationInformationEPS = val;
            return this;
        }
        
        public Builder locationInformation5GS(LocationInformation5GS val) {
            this.locationInformation5GS = val;
            return this;
        }
        
        public Builder nrLocationInformation(NRLocationInformation val) {
            this.nrLocationInformation = val;
            return this;
        }
        
        public Builder nrCellGlobalId(NRCellGlobalId val) {
            this.nrCellGlobalId = val;
            return this;
        }
        
        public Builder nrTAId(NRTAId val) {
            this.nrTAId = val;
            return this;
        }
        
        public Builder imsi(IMSI val) {
            this.imsi = val;
            return this;
        }
        
        public Builder msisdn(ISDNAddressString val) {
            this.msisdn = val;
            return this;
        }
        
        public ExtendedSubscriberInfo build() {
            return new ExtendedSubscriberInfoImpl(
                    locationInformation,
                    subscriberState,
                    extensionContainer,
                    locationInformationGPRS,
                    psSubscriberState,
                    imei,
                    msClassmark2,
                    gprsMSClass,
                    mnpInfoRes,
                    timeZone,
                    daylightSavingTime,
                    imsVoiceOverPs,
                    lastUEActivityTime,
                    lastRATType,
                    epsSubscriberState,
                    locationInformationEPS,
                    locationInformation5GS,
                    nrLocationInformation,
                    nrCellGlobalId,
                    nrTAId,
                    imsi,
                    msisdn);
        }
    }
}
