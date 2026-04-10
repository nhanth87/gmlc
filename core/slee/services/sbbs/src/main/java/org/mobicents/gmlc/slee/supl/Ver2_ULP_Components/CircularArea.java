package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.Asn1Integer;

/**
 * CircularArea class for SUPL representing a circular geographic area
 */
public class CircularArea {

    private Coordinate coordinate;
    private Asn1Integer radius;

    public CircularArea() {
    }

    public CircularArea(Coordinate coordinate, Asn1Integer radius) {
        this.coordinate = coordinate;
        this.radius = radius;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Asn1Integer getRadius() {
        return radius;
    }

    public void setRadius(Asn1Integer radius) {
        this.radius = radius;
    }
}
