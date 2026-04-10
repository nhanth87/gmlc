package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 * SubscriberState interface - Stub for jSS7 9.2.5 compatibility
 * Represents the state of a subscriber (assumedIdle, camelBusy, netDetNotReachable, notProvidedFromVLR)
 */
public interface SubscriberState {
    
    /**
     * Gets the subscriber state choice
     * @return the subscriber state choice enum value
     */
    SubscriberStateChoice getSubscriberStateChoice();
    
    /**
     * Gets the not reachable reason
     * @return the not reachable reason
     */
    Integer getNotReachableReason();
}
