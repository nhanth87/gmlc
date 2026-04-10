package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

/**
 * Coordinate latitude sign enum for SUPL
 */
public enum Coordinate_latitudeSign {
    NORTH,
    SOUTH;

    public static Coordinate_latitudeSign north() {
        return NORTH;
    }

    public static Coordinate_latitudeSign south() {
        return SOUTH;
    }
}
