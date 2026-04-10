package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 * PSSubscriberStateChoice enum - Stub for jSS7 9.2.5 compatibility
 */
public enum PSSubscriberStateChoice {
    psAttachedReachableForPaging(0),
    psAttachedNotReachableForPaging(1),
    psPDPActiveReachableForPaging(2),
    psPDPActiveNotReachableForPaging(3),
    netDetNotReachable(4),
    notProvidedFromSGSNorMME(5);
    
    private final int code;
    
    PSSubscriberStateChoice(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
}
