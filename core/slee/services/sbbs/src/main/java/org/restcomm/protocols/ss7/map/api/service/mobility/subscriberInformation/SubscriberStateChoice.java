package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 * SubscriberStateChoice enum - Stub for jSS7 9.2.5 compatibility
 * Defines the possible subscriber state choices
 */
public enum SubscriberStateChoice {
    assumedIdle(0),
    camelBusy(1),
    netDetNotReachable(2),
    notProvidedFromVLR(3);
    
    private final int code;
    
    SubscriberStateChoice(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    public static SubscriberStateChoice getInstance(int code) {
        switch (code) {
            case 0:
                return assumedIdle;
            case 1:
                return camelBusy;
            case 2:
                return netDetNotReachable;
            case 3:
                return notProvidedFromVLR;
            default:
                return null;
        }
    }
}
