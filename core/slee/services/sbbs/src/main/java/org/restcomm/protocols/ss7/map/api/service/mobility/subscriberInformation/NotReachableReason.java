package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 * NotReachableReason enum - Stub for jSS7 9.2.5 compatibility
 * Defines reasons why a subscriber is not reachable
 */
public enum NotReachableReason {
    msPurged(0),
    imsiDetached(1),
    restrictedArea(2),
    notRegistered(3);
    
    private final int code;
    
    NotReachableReason(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    /**
     * Get the not reachable reason code
     * @return the reason code
     */
    public int getNotReachableReason() {
        return code;
    }
    
    public static NotReachableReason getInstance(int code) {
        switch (code) {
            case 0:
                return msPurged;
            case 1:
                return imsiDetached;
            case 2:
                return restrictedArea;
            case 3:
                return notRegistered;
            default:
                return null;
        }
    }
}
