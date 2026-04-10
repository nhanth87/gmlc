// STUB interface - Missing from diameter-slh resource adaptor
package net.java.slee.resource.diameter.slh.events;

import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.base.events.avp.Address;

public interface ServingNode {

    byte[] getSGSNNumber();

    DiameterIdentity getSGSNName();

    DiameterIdentity getSGSNRealm();

    DiameterIdentity getMMEName();

    DiameterIdentity getMMERealm();

    byte[] getMSCNumber();

    DiameterIdentity get3GPPAAAServerName();

    long getLcsCapabilitiesSets();

    Address getGMLCAddress();

    boolean hasLcsCapabilitiesSets();

    boolean hasGMLCAddress();
}
