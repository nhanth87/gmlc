// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface PrioritizedListIndicator {

    int _NOT_PRIORITIZED = 0;
    int NOT_PRIORITIZED = 0;
    int _PRIORITIZED = 1;

    int getValue();

    static PrioritizedListIndicator fromInt(int value) {
        return null;
    }

    static PrioritizedListIndicator fromInt(Integer value) {
        return null;
    }
}
