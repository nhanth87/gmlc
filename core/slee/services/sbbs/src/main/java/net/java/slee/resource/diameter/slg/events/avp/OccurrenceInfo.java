// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface OccurrenceInfo {

    int ONE_TIME_EVENT = 0;
    int _ONE_TIME_EVENT = 0;
    int _AREA_DEFINED_EVENT = 1;
    int _MULTIPLE_TIME_EVENT = 1;
    int _MULTI_AREA_EVENT = 2;
    int _ACTIVE_LOCATION_VERIFICATION = 3;

    int getValue();

    static OccurrenceInfo fromInt(int value) {
        return null;
    }
}
