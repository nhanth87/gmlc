package org.mobicents.gmlc.slee.diameter.slh;

import net.java.slee.resource.diameter.base.events.avp.Address;
import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import net.java.slee.resource.diameter.slh.events.avp.AdditionalServingNodeAvp;
import net.java.slee.resource.diameter.slh.events.avp.ServingNodeAvp;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLhRiaAvpValues implements Serializable {

  private static final long serialVersionUID = 1L;

  /*
  3GPP TS 29.173 v18.0.0 § 6.2.4

  The LCS-Routing-Info-Answer (RIA) command, indicated by the Command-Code field set to 8388622
  and the 'R' bit cleared in the Command Flags field, is sent from HSS to GMLC.

  Message Format:
  < LCS-Routing-Info-Answer > ::= < Diameter Header: 8388622, PXY, 16777291 >
		                   < Session-Id >
		                   [ Vendor-Specific-Application-Id ]
		                   [ Result-Code ]
		                   [ Experimental-Result ]
		                   { Auth-Session-State }
		                   { Origin-Host }
		                   { Origin-Realm }
		                  *[ Supported-Features ]
		                   [ User-Name ]
		                   [ MSISDN ]
		                   [ LMSI ]
		                   [ Serving-Node ]
		                  *[ Additional-Serving-Node ]
		                   [ GMLC-Address ]
		                   [ PPR-Address ]
		                   [ RIA-Flags ]
		                  *[ AVP ]
		                   [ Failed-AVP ]
		                  *[ Proxy-Info ]
		                  *[ Route-Record ]
 */

  private Long resultCode;
  private ExperimentalResultAvp experimentalResultAvp;
  private String userName;
  private byte[] msisdn;
  private byte[] lmsi;
  private ServingNodeAvp servingNodeAvp;
  private AdditionalServingNodeAvp additionalServingNodeAvp;
  private Address gmlcAddress;
  private Address pprAddress;
  private Long riaFLags;

  public Integer lteLcsReferenceNumber;

  public SLhRiaAvpValues() {
    super();
  }

  public Long getResultCode() {
    return resultCode;
  }

  public void setResultCode(Long resultCode) {
    this.resultCode = resultCode;
  }

  public ExperimentalResultAvp getExperimentalResultAvp() {
    return experimentalResultAvp;
  }

  public void setExperimentalResultAvp(ExperimentalResultAvp experimentalResultAvp) {
    this.experimentalResultAvp = experimentalResultAvp;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public byte[] getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(byte[] msisdn) {
    this.msisdn = msisdn;
  }

  public byte[] getLmsi() {
    return lmsi;
  }

  public void setLmsi(byte[] lmsi) {
    this.lmsi = lmsi;
  }

  public ServingNodeAvp getServingNodeAvp() {
    return servingNodeAvp;
  }

  public void setServingNodeAvp(ServingNodeAvp servingNodeAvp) {
    this.servingNodeAvp = servingNodeAvp;
  }

  public AdditionalServingNodeAvp getAdditionalServingNodeAvp() {
    return additionalServingNodeAvp;
  }

  public void setAdditionalServingNodeAvp(AdditionalServingNodeAvp additionalServingNodeAvp) {
    this.additionalServingNodeAvp = additionalServingNodeAvp;
  }

  public Address getGmlcAddress() {
    return gmlcAddress;
  }

  public void setGmlcAddress(Address gmlcAddress) {
    this.gmlcAddress = gmlcAddress;
  }

  public Address getPprAddress() {
    return pprAddress;
  }

  public void setPprAddress(Address pprAddress) {
    this.pprAddress = pprAddress;
  }

  public Long  getRiaFLags() {
    return riaFLags;
  }

  public void setRiaFLags(Long  riaFLags) {
    this.riaFLags = riaFLags;
  }

}