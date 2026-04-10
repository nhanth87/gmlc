// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface LCSQoSClass {

    int _BEST_EFFORT = 0;
    int BEST_EFFORT = 0;
    int _ASSURED = 1;
    int _RESERVED = 2;
    int _HIGH_PRIORITY = 3;
    int _LOW_PRIORITY = 4;

    int getValue();

    static LCSQoSClass fromInt(int value) {
        return null;
    }
}
