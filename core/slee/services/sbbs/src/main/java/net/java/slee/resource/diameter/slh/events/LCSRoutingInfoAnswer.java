// STUB interface - Missing from diameter-slh resource adaptor
package net.java.slee.resource.diameter.slh.events;

import net.java.slee.resource.diameter.base.events.avp.DiameterAvp;
import net.java.slee.resource.diameter.base.events.avp.Address;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;

public interface LCSRoutingInfoAnswer extends DiameterAvp {

    boolean hasLMSI();

    byte[] getLMSI();

    boolean hasServingNode();

    ServingNode getServingNode();

    boolean hasAdditionalServingNode();

    ServingNode getAdditionalServingNode();

    boolean hasGMLCAddress();

    Address getGMLCAddress();

    boolean hasPPRAddress();

    Address getPPRAddress();

    boolean hasResultCode();

    long getResultCode();

    boolean hasExperimentalResult();

    Object getExperimentalResult();

    String getSessionId();

    boolean hasUserName();

    String getUserName();

    boolean hasMSISDN();

    byte[] getMSISDN();

    boolean hasRIAFlags();

    long getRIAFlags();

    DiameterIdentity getOriginHost();

    DiameterIdentity getOriginRealm();
}
