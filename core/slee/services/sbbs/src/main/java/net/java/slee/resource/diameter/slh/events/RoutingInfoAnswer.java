package net.java.slee.resource.diameter.slh.events;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;

/**
 * Routing-Info-Answer (RIA) message as defined in 3GPP TS 29.172.
 * 
 * The Routing-Info-Answer is sent by the HSS to the GMLC in response to a Routing-Info-Request.
 * 
 * @author GMLC Team
 */
public interface RoutingInfoAnswer extends DiameterMessage {

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
     * Checks if the User-Name AVP is present.
     * @return true if present, false otherwise
     */
    boolean hasUserName();

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
     * Gets the MME-Name AVP.
     * @return String representing the MME-Name
     */
    String getMMEName();

    /**
     * Sets the MME-Name AVP.
     * @param mmeName the MME name to set
     */
    void setMMEName(String mmeName);

    /**
     * Checks if the MME-Name AVP is present.
     * @return true if present, false otherwise
     */
    boolean hasMMEName();

    /**
     * Gets the SGSN-Number AVP.
     * @return byte[] representing the SGSN-Number
     */
    byte[] getSGSNNumber();

    /**
     * Sets the SGSN-Number AVP.
     * @param sgsnNumber the SGSN number to set
     */
    void setSGSNNumber(byte[] sgsnNumber);

    /**
     * Checks if the SGSN-Number AVP is present.
     * @return true if present, false otherwise
     */
    boolean hasSGSNNumber();

    /**
     * Gets the 3GPP-AAA-Server-Name AVP.
     * @return String representing the 3GPP-AAA-Server-Name
     */
    String get3GPPAAAServerName();

    /**
     * Sets the 3GPP-AAA-Server-Name AVP.
     * @param serverName the 3GPP AAA server name to set
     */
    void set3GPPAAAServerName(String serverName);

    /**
     * Checks if the 3GPP-AAA-Server-Name AVP is present.
     * @return true if present, false otherwise
     */
    boolean has3GPPAAAServerName();
}
