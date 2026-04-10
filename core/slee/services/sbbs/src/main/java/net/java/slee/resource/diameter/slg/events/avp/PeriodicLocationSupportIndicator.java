// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface PeriodicLocationSupportIndicator {

    int _NOT_SUPPORTED = 0;
    int NOT_SUPPORTED = 0;
    int _SUPPORTED = 1;

    int getValue();

    static PeriodicLocationSupportIndicator fromInt(int value) {
        return null;
    }

    static PeriodicLocationSupportIndicator fromInt(Integer value) {
        return null;
    }
}
