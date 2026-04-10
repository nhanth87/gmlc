package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * AddressNature enum - Stub for jSS7 9.2.5 compatibility
 * Defines the nature of address indicators
 */
public enum AddressNature {
    unknown(0),
    international_number(1),
    national_number(2),
    network_specific_number(3),
    subscriber_number(4),
    reserved(5),
    abbreviated_number(6),
    extension(7);
    
    private final int code;
    
    AddressNature(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    public static AddressNature getInstance(int code) {
        switch (code) {
            case 0:
                return unknown;
            case 1:
                return international_number;
            case 2:
                return national_number;
            case 3:
                return network_specific_number;
            case 4:
                return subscriber_number;
            case 5:
                return reserved;
            case 6:
                return abbreviated_number;
            case 7:
                return extension;
            default:
                return null;
        }
    }
}
