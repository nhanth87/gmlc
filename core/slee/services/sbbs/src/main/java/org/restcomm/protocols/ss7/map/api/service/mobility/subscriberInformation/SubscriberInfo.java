package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
 * SubscriberInfo interface - Stub for jSS7 9.2.5 compatibility
 * Contains subscriber information retrieved via Any Time Interrogation
 */
public interface SubscriberInfo {
    
    /**
     * Gets the location information
     * @return the location information
     */
    LocationInformation getLocationInformation();
    
    /**
     * Gets the subscriber state
     * @return the subscriber state
     */
    SubscriberState getSubscriberState();
    
    /**
     * Gets the IMEI
     * @return the IMEI
     */
    IMEI getIMEI();
    
    /**
     * Gets the IMSI
     * @return the IMSI
     */
    IMSI getIMSI();
    
    /**
     * Gets the MSISDN
     * @return the MSISDN
     */
    ISDNAddressString getMsisdn();
    
    /**
     * Gets the extension container
     * @return the extension container
     */
    Object getExtensionContainer();
    
    /**
     * Gets the location information GPRS
     * @return the location information GPRS
     */
    Object getLocationInformationGPRS();
    
    /**
     * Gets the PS subscriber state
     * @return the PS subscriber state
     */
    PSSubscriberState getPSSubscriberState();
    
    /**
     * Gets the GPRS MS class
     * @return the GPRS MS class
     */
    Object getGPRSMSClass();
    
    /**
     * Gets the MNP info result
     * @return the MNP info result
     */
    Object getMNPInfoResult();
    
    /**
     * Gets the IMS voice over PS sessions indication
     * @return the IMS voice over PS sessions indication
     */
    IMSVoiceOverPsSessionsIndication getImsVoiceOverPsSessionsIndication();
    
    /**
     * Gets the last UE activity time
     * @return the last UE activity time
     */
    UEActivityTime getLastUEActivityTime();
    
    /**
     * Gets the last RAT type
     * @return the last RAT type
     */
    RATType getLastRATType();
    
    /**
     * Gets the EPS subscriber state
     * @return the EPS subscriber state
     */
    EPSSubscriberState getEPSSubscriberState();
    
    /**
     * Gets the location information for EPS
     * @return the location information EPS
     */
    LocationInformationEPS getLocationInformationEPS();
    
    /**
     * Gets the location information for 5GS
     * @return the location information 5GS
     */
    LocationInformation5GS getLocationInformation5GS();
    
    /**
     * Gets the NR location information
     * @return the NR location information
     */
    Object getNRLocationInformation();
    
    /**
     * Gets the NRCellGlobalId
     * @return the NR cell global ID
     */
    NRCellGlobalId getNRCellGlobalId();
    
    /**
     * Gets the NR TAI
     * @return the NR TAI
     */
    NRTAId getNRTAId();
}
