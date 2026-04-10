package org.restcomm.protocols.ss7.map.api.service.lsm;

public enum TypeOfShape {
    EllipsoidPoint(0),
    EllipsoidPointWithUncertaintyCircle(1),
    EllipsoidPointWithUncertaintyEllipse(2),
    Polygon(3),
    EllipsoidPointWithAltitude(4),
    EllipsoidPointWithAltitudeAndUncertaintyEllipsoid(5),
    EllipsoidArc(6);
    private final int type;
    TypeOfShape(int type) { this.type = type; }
    public int getType() { return type; }
}
