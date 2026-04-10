package net.java.slee.resource.diameter.slh.events;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;

/**
 * Location-Report-Request (LRR) message as defined in 3GPP TS 29.172.
 * 
 * The Location-Report-Request is sent by the GMLC to the HSS to report a location event.
 * 
 * @author GMLC Team
 */
public interface LocationReportRequest extends DiameterMessage {

    /**
     * Gets the Session-Id AVP.
     * @return String representing the Session-Id
     */
    String getSessionId();

    /**
     * Sets the Session-Id AVP.
     * @param sessionId the session ID to set
     */
    void setSessionId(String sessionId);

    /**
     * Gets the Auth-Session-State AVP.
     * @return long representing the Auth-Session-State
     */
    long getAuthSessionState();

    /**
     * Sets the Auth-Session-State AVP.
     * @param authSessionState the auth session state to set
     */
    void setAuthSessionState(long authSessionState);

    /**
     * Gets the Origin-Host AVP.
     * @return DiameterIdentity representing the Origin-Host
     */
    DiameterIdentity getOriginHost();

    /**
     * Sets the Origin-Host AVP.
     * @param originHost the origin host to set
     */
    void setOriginHost(DiameterIdentity originHost);

    /**
     * Gets the Origin-Realm AVP.
     * @return DiameterIdentity representing the Origin-Realm
     */
    DiameterIdentity getOriginRealm();

    /**
     * Sets the Origin-Realm AVP.
     * @param originRealm the origin realm to set
     */
    void setOriginRealm(DiameterIdentity originRealm);

    /**
     * Gets the Destination-Host AVP.
     * @return DiameterIdentity representing the Destination-Host
     */
    DiameterIdentity getDestinationHost();

    /**
     * Sets the Destination-Host AVP.
     * @param destinationHost the destination host to set
     */
    void setDestinationHost(DiameterIdentity destinationHost);

    /**
     * Gets the Destination-Realm AVP.
     * @return DiameterIdentity representing the Destination-Realm
     */
    DiameterIdentity getDestinationRealm();

    /**
     * Sets the Destination-Realm AVP.
     * @param destinationRealm the destination realm to set
     */
    void setDestinationRealm(DiameterIdentity destinationRealm);

    /**
     * Gets the User-Name AVP (IMSI).
     * @return String representing the User-Name (IMSI)
     */
    String getUserName();

    /**
     * Sets the User-Name AVP (IMSI).
     * @param userName the user name (IMSI) to set
     */
    void setUserName(String userName);

    /**
     * Gets the Location-Event AVP.
     * @return int representing the Location-Event
     */
    int getLocationEvent();

    /**
     * Sets the Location-Event AVP.
     * @param locationEvent the location event to set
     */
    void setLocationEvent(int locationEvent);

    /**
     * Checks if the Location-Event AVP is present.
     * @return true if present, false otherwise
     */
    boolean hasLocationEvent();

    /**
     * Gets the LCS-Service-Type-ID AVP.
     * @return int representing the LCS-Service-Type-ID
     */
    int getLCSServiceTypeID();

    /**
     * Sets the LCS-Service-Type-ID AVP.
     * @param lcsServiceTypeID the LCS service type ID to set
     */
    void setLCSServiceTypeID(int lcsServiceTypeID);

    /**
     * Checks if the LCS-Service-Type-ID AVP is present.
     * @return true if present, false otherwise
     */
    boolean hasLCSServiceTypeID();
}
