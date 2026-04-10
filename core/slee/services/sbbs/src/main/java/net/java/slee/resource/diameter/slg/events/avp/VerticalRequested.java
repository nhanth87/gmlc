// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface VerticalRequested {

    int VERTICAL_COORDINATE_IS_NOT_REQUESTED = 0;
    int VERTICAL_COORDINATE_IS_REQUESTED = 1;
    int _VERTICAL_COORDINATE_IS_NOT_REQUESTED = 0;
    int _VERTICAL_COORDINATE_IS_REQUESTED = 1;

    int getValue();

    static VerticalRequested fromInt(int value) {
        return null;
    }
}
