// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg;

import net.java.slee.resource.diameter.slg.events.ProvideLocationRequest;

public interface SLgClientSessionActivity extends SLgActivity {

    ProvideLocationRequest createProvideLocationRequest();

    void sendProvideLocationRequest(ProvideLocationRequest plr);
}
