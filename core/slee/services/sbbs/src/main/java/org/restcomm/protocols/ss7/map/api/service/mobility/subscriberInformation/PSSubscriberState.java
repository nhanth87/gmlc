package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

public interface PSSubscriberState {
    
    /**
     * Get the network detected not reachable reason
     * @return NotReachableReason or null
     */
    NotReachableReason getNetDetNotReachable();
}
