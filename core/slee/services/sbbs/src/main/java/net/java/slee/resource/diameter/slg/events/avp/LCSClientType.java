// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface LCSClientType {

    int _EMERGENCY_SERVICES = 0;
    int _VALUE_ADDED_SERVICES = 1;
    int _PLMN_OPERATOR_SERVICES = 2;
    int _LAWFUL_INTERCEPT_SERVICES = 3;

    int getValue();

    static LCSClientType fromInt(int value) {
        return null;
    }
}
