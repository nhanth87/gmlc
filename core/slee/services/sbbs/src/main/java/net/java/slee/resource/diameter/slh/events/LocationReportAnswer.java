package net.java.slee.resource.diameter.slh.events;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;

/**
 * Location-Report-Answer (LRA) message as defined in 3GPP TS 29.172.
 * 
 * The Location-Report-Answer is sent by the HSS to the GMLC in response to a Location-Report-Request.
 * 
 * @author GMLC Team
 */
public interface LocationReportAnswer extends DiameterMessage {

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
     * Gets the Result-Code AVP.
     * @return long representing the Result-Code
     */
    long getResultCode();

    /**
     * Sets the Result-Code AVP.
     * @param resultCode the result code to set
     */
    void setResultCode(long resultCode);

    /**
     * Checks if the Result-Code AVP is present.
     * @return true if present, false otherwise
     */
    boolean hasResultCode();
}
