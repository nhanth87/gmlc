package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * NumberingPlan enum - Stub for jSS7 9.2.5 compatibility
 * Defines the numbering plan indicators
 */
public enum NumberingPlan {
    unknown(0),
    ISDN(1),
    spare_2(2),
    data(3),
    telex(4),
    spare_5(5),
    land_mobile(6),
    spare_7(7),
    national(8),
    private_plan(9),
    reserved(15);
    
    private final int code;
    
    NumberingPlan(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    public static NumberingPlan getInstance(int code) {
        switch (code) {
            case 0:
                return unknown;
            case 1:
                return ISDN;
            case 2:
                return spare_2;
            case 3:
                return data;
            case 4:
                return telex;
            case 5:
                return spare_5;
            case 6:
                return land_mobile;
            case 7:
                return spare_7;
            case 8:
                return national;
            case 9:
                return private_plan;
            case 15:
                return reserved;
            default:
                return null;
        }
    }
}
