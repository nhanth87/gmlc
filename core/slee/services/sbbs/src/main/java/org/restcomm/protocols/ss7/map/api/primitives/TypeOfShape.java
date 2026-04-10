package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * TypeOfShape enum - Stub for jSS7 9.2.5 compatibility
 * Defines the possible shape types for geographical information
 */
public enum TypeOfShape {
    EllipsoidPoint(0),
    EllipsoidPointWithUncertaintyCircle(1),
    EllipsoidPointWithUncertaintyEllipse(2),
    Polygon(3),
    EllipsoidPointWithAltitude(4),
    EllipsoidPointWithAltitudeAndUncertaintyEllipsoid(5),
    EllipsoidArc(6);
    
    private final int code;
    
    TypeOfShape(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    public static TypeOfShape getInstance(int code) {
        switch (code) {
            case 0:
                return EllipsoidPoint;
            case 1:
                return EllipsoidPointWithUncertaintyCircle;
            case 2:
                return EllipsoidPointWithUncertaintyEllipse;
            case 3:
                return Polygon;
            case 4:
                return EllipsoidPointWithAltitude;
            case 5:
                return EllipsoidPointWithAltitudeAndUncertaintyEllipsoid;
            case 6:
                return EllipsoidArc;
            default:
                return null;
        }
    }
}
