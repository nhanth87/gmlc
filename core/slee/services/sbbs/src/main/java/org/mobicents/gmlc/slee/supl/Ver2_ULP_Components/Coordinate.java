package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.Asn1Integer;

/**
 * Coordinate class for SUPL representing latitude and longitude
 */
public class Coordinate {

    private Coordinate_latitudeSign latitudeSign;
    private Asn1Integer latitude;
    private Asn1Integer longitude;

    public Coordinate() {
    }

    public Coordinate(Coordinate_latitudeSign latitudeSign, Asn1Integer latitude, Asn1Integer longitude) {
        this.latitudeSign = latitudeSign;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinate_latitudeSign getLatitudeSign() {
        return latitudeSign;
    }

    public void setLatitudeSign(Coordinate_latitudeSign latitudeSign) {
        this.latitudeSign = latitudeSign;
    }

    public Asn1Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Asn1Integer latitude) {
        this.latitude = latitude;
    }

    public Asn1Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Asn1Integer longitude) {
        this.longitude = longitude;
    }
}
