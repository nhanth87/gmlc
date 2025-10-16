package org.mobicents.gmlc.slee.diameter.sh.elements;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeodeticInformation;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeodeticInformationImpl;

import java.util.Base64;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ShGeodeticInformation {

  private static final String TYPE_OF_SHAPE = "typeOfShape";
  private static final String LATITUDE = "latitude";
  private static final String LONGITUDE = "longitude";
  private static final String UNCERTAINTY = "uncertainty";
  private static final String CONFIDENCE = "confidence";
  private static final String SCREENING_AND_PRESENTATION_INDICATORS = "screeningAndPresentationIndicators";

  private String geodeticInfoStr;
  private GeodeticInformation geodeticInformation;

  public ShGeodeticInformation(GeodeticInformation geodeticInformation, String xmlString) {
    setGeodeticInfoStr(xmlString);
    this.geodeticInformation = geodeticInformation;
  }

  public ShGeodeticInformation(String xmlString) {
    setGeodeticInfoStr(xmlString);
  }

  public ShGeodeticInformation(GeodeticInformation geodeticInformation) {
    this.geodeticInformation = geodeticInformation;
  }

  public ShGeodeticInformation() {
  }

  public GeodeticInformation getGeodeticInformation() {
    return geodeticInformation;
  }

  public void setGeodeticInformation(GeodeticInformation geodeticInformation) {
    this.geodeticInformation = geodeticInformation;
  }

  public String getGeodeticInfoStr() {
    return geodeticInfoStr;
  }

  public void setGeodeticInfoStr(String geodeticInfoStr) {
    if (geodeticInfoStr != null) {
      byte[] geoInfoBytes = getGeodeticInfoBytes(geodeticInfoStr);
      this.geodeticInformation = decodeGeodeticInfoBytes(geoInfoBytes);
      this.geodeticInfoStr = this.geodeticInformation.toString();
    }
  }

  public byte[] getGeodeticInfoBytes(String geoInfo) {
    return Base64.getDecoder().decode(geoInfo);
  }

  public GeodeticInformation decodeGeodeticInfoBytes(byte[] geoInfoBytes) {
    return new GeodeticInformationImpl(geoInfoBytes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("GeodeticInformation");
    sb.append(" [");

    if (geodeticInformation != null) {

      sb.append(TYPE_OF_SHAPE+"=");
      sb.append(this.geodeticInformation.getTypeOfShape());

      sb.append(", "+LATITUDE+"=");
      sb.append(this.geodeticInformation.getLatitude());

      sb.append(", "+LONGITUDE+"=");
      sb.append(this.geodeticInformation.getLongitude());

      sb.append(", "+UNCERTAINTY+"=");
      sb.append(this.geodeticInformation.getUncertainty());

      sb.append(", "+CONFIDENCE+"=");
      sb.append(this.geodeticInformation.getConfidence());

      sb.append(", "+SCREENING_AND_PRESENTATION_INDICATORS+"=");
      sb.append(this.geodeticInformation.getScreeningAndPresentationIndicators());

    }

    sb.append("]");

    return sb.toString();
  }

}
