package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 * Wrapper for PSSubscriberStateChoice - Alternative enum for jSS7 9.2.5 compatibility
 */
public class PSSubscriberStateChoiceWrapper {
    
    public static final int PS_ATTACHED_REACHABLE_FOR_PAGING = 0;
    public static final int PS_ATTACHED_NOT_REACHABLE_FOR_PAGING = 1;
    public static final int PS_PDP_ACTIVE_REACHABLE_FOR_PAGING = 2;
    public static final int PS_PDP_ACTIVE_NOT_REACHABLE_FOR_PAGING = 3;
    public static final int NET_DET_NOT_REACHABLE = 4;
    public static final int NOT_PROVIDED_FROM_SGSN_OR_MME = 5;
}
