// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface ResponseTime {

    int _LOW_DELAY = 0;
    int _DELAY_TOLERANT = 1;

    int getValue();

    static ResponseTime fromInt(int value) {
        return null;
    }
}
