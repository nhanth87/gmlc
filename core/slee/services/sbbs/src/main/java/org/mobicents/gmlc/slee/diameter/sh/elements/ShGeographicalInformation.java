package org.mobicents.gmlc.slee.diameter.sh.elements;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeographicalInformation;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeographicalInformationImpl;

import java.util.Base64;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ShGeographicalInformation {

  private static final String TYPE_OF_SHAPE = "typeOfShape";
  private static final String LATITUDE = "latitude";
  private static final String LONGITUDE = "longitude";
  private static final String UNCERTAINTY = "uncertainty";

  private String geographicalInfoStr;
  private GeographicalInformation geographicalInformation;

  public ShGeographicalInformation(GeographicalInformation geographicalInformation, String xmlString) {
    setGeographicalInfoStr(xmlString);
    this.geographicalInformation = geographicalInformation;
  }

  public ShGeographicalInformation(String xmlString) {
    setGeographicalInfoStr(xmlString);
  }

  public ShGeographicalInformation(GeographicalInformation geographicalInformation) {
    this.geographicalInformation = geographicalInformation;
  }

  public ShGeographicalInformation() {
  }

  public GeographicalInformation getGeographicalInformation() {
    return geographicalInformation;
  }

  public void setGeographicalInformation(GeographicalInformation geographicalInformation) {
    this.geographicalInformation = geographicalInformation;
  }

  public String getGeographicalInfoStr() {
    return geographicalInfoStr;
  }

  public void setGeographicalInfoStr(String geographicalInfoStr) {
    if (geographicalInfoStr != null) {
      byte[] geoInfoBytes = getGeographicalInfoBytes(geographicalInfoStr);
      this.geographicalInformation = decodeGeographicalInfoBytes(geoInfoBytes);
      this.geographicalInfoStr = this.geographicalInformation.toString();
    }
  }

  public byte[] getGeographicalInfoBytes(String geoInfo) {
    return Base64.getDecoder().decode(geoInfo);
  }

  public GeographicalInformation decodeGeographicalInfoBytes(byte[] geoInfoBytes) {
    return new GeographicalInformationImpl(geoInfoBytes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("GeographicalInformation");
    sb.append(" [");

    if (geographicalInformation != null) {

      sb.append(TYPE_OF_SHAPE+"=");
      sb.append(this.geographicalInformation.getTypeOfShape());

      sb.append(", "+LATITUDE+"=");
      sb.append(this.geographicalInformation.getLatitude());

      sb.append(", "+LONGITUDE+"=");
      sb.append(this.geographicalInformation.getLongitude());

      sb.append(", "+UNCERTAINTY+"=");
      sb.append(this.geographicalInformation.getUncertainty());

    }

    sb.append("]");

    return sb.toString();
  }

}
