// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface LCSFormatIndicator {

    int _LOGICAL_NAME = 0;
    int _EMAIL_ADDRESS = 1;
    int _MSISDN = 2;
    int _URL = 3;
    int _SIP_URL = 4;

    int getValue();

    static LCSFormatIndicator fromInt(int value) {
        return null;
    }
}
