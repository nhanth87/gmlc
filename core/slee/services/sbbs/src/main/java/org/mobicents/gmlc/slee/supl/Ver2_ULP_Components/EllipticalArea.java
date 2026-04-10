package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.Asn1Integer;

/**
 * EllipticalArea class for SUPL representing an elliptical geographic area
 */
public class EllipticalArea {

    private Coordinate coordinate;
    private Asn1Integer semiMajor;
    private Asn1Integer semiMinor;
    private Asn1Integer angle;

    public EllipticalArea() {
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Asn1Integer getSemiMajor() {
        return semiMajor;
    }

    public void setSemiMajor(Asn1Integer semiMajor) {
        this.semiMajor = semiMajor;
    }

    public Asn1Integer getSemiMinor() {
        return semiMinor;
    }

    public void setSemiMinor(Asn1Integer semiMinor) {
        this.semiMinor = semiMinor;
    }

    public Asn1Integer getAngle() {
        return angle;
    }

    public void setAngle(Asn1Integer angle) {
        this.angle = angle;
    }
}
