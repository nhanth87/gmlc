package net.java.slee.resource.diameter.slg.events.avp;

/**
 * Location Event enum as defined in 3GPP TS 29.172.
 * 
 * This enum indicates the type of location event that triggered the LRR.
 * 
 * @author GMLC Team
 */
public enum LocationEvent {

    /**
     * Normal location event.
     */
    NORMAL_LOCATION_EVENT(0),

    /**
     * Triggered location event for change of service area.
     */
    CHANGE_OF_SERVICE_AREA(1),

    /**
     * Triggered location event for entering service area.
     */
    ENTERING_SERVICE_AREA(2),

    /**
     * Triggered location event for leaving service area.
     */
    LEAVING_SERVICE_AREA(3),

    /**
     * Triggered location event for change of MBSFN area.
     */
    CHANGE_OF_MBSFN_AREA(4),

    /**
     * Triggered location event for entering MBSFN area.
     */
    ENTERING_MBSFN_AREA(5),

    /**
     * Triggered location event for leaving MBSFN area.
     */
    LEAVING_MBSFN_AREA(6),

    /**
     * Triggered location event for change of geographical area.
     */
    CHANGE_OF_GEOGRAPHICAL_AREA(7),

    /**
     * Triggered location event for entering geographical area.
     */
    ENTERING_GEOGRAPHICAL_AREA(8),

    /**
     * Triggered location event for leaving geographical area.
     */
    LEAVING_GEOGRAPHICAL_AREA(9),

    /**
     * Triggered location event for periodic location.
     */
    PERIODIC_LOCATION(10),

    /**
     * Triggered location event for velocity change.
     */
    VELOCITY_CHANGED(11);

    private final int value;

    private LocationEvent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LocationEvent fromInt(int value) {
        switch (value) {
            case 0:
                return NORMAL_LOCATION_EVENT;
            case 1:
                return CHANGE_OF_SERVICE_AREA;
            case 2:
                return ENTERING_SERVICE_AREA;
            case 3:
                return LEAVING_SERVICE_AREA;
            case 4:
                return CHANGE_OF_MBSFN_AREA;
            case 5:
                return ENTERING_MBSFN_AREA;
            case 6:
                return LEAVING_MBSFN_AREA;
            case 7:
                return CHANGE_OF_GEOGRAPHICAL_AREA;
            case 8:
                return ENTERING_GEOGRAPHICAL_AREA;
            case 9:
                return LEAVING_GEOGRAPHICAL_AREA;
            case 10:
                return PERIODIC_LOCATION;
            case 11:
                return VELOCITY_CHANGED;
            default:
                return null;
        }
    }
}
