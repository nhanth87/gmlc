package net.java.slee.resource.diameter.slh.events;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;

/**
 * Routing-Info-Request (RIR) message as defined in 3GPP TS 29.172.
 * 
 * The Routing-Info-Request is sent by the GMLC to the HSS to request routing information.
 * 
 * @author GMLC Team
 */
public interface RoutingInfoRequest extends DiameterMessage {

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
    void setDestinationRealm(byte[] destinationRealm);

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
     * Gets the MSISDN AVP.
     * @return byte[] representing the MSISDN
     */
    byte[] getMsisdn();

    /**
     * Sets the MSISDN AVP.
     * @param msisdn the MSISDN to set
     */
    void setMsisdn(byte[] msisdn);

    /**
     * Checks if the MSISDN AVP is present.
     * @return true if present, false otherwise
     */
    boolean hasMsisdn();

    /**
     * Gets the GMLC-Number AVP.
     * @return byte[] representing the GMLC-Number
     */
    byte[] getGMLCNumber();

    /**
     * Sets the GMLC-Number AVP.
     * @param gmlcNumber the GMLC number to set
     */
    void setGMLCNumber(byte[] gmlcNumber);

    /**
     * Checks if the GMLC-Number AVP is present.
     * @return true if present, false otherwise
     */
    boolean hasGMLCNumber();
}
