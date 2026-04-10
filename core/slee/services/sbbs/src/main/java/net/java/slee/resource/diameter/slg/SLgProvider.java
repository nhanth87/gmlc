// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg;

import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;

public interface SLgProvider {

    SLgClientSessionActivity createSLgClientSessionActivity(DiameterIdentity destinationHost, DiameterIdentity destinationRealm);
}
