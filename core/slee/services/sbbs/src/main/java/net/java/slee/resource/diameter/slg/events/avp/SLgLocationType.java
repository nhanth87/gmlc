// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface SLgLocationType {

    int _CURRENT_LOCATION = 0;
    int _CURRENT_OR_LAST_LOCATION = 1;
    int _INITIAL_LOCATION = 2;
    int _ACTIVATION_LOCATION = 3;
    int _DEFERRED_LOCATION = 4;
    int _CURRENT_OR_LAST_KNOWN_LOCATION = 1;
    int _ACTIVATE_DEFERRED_LOCATION = 3;
    int _CANCEL_DEFERRED_LOCATION = 4;
    int _NOTIFICATION_VERIFICATION_ONLY = 5;

    int getValue();

    static SLgLocationType fromInt(int value) {
        return null;
    }
}
