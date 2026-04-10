package org.mobicents.gmlc.slee.diameter.slh;

import net.java.slee.resource.diameter.base.events.avp.Address;
import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import net.java.slee.resource.diameter.slh.events.ServingNode;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLhRiaAvpValues implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long resultCode;
  private ExperimentalResultAvp experimentalResultAvp;
  private String userName;
  private byte[] msisdn;
  private byte[] lmsi;
  private ServingNode servingNodeAvp;
  private ServingNode additionalServingNodeAvp;
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

  public ServingNode getServingNodeAvp() {
    return servingNodeAvp;
  }

  public void setServingNodeAvp(ServingNode servingNodeAvp) {
    this.servingNodeAvp = servingNodeAvp;
  }

  public ServingNode getAdditionalServingNodeAvp() {
    return additionalServingNodeAvp;
  }

  public void setAdditionalServingNodeAvp(ServingNode additionalServingNodeAvp) {
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

  public Long getRiaFLags() {
    return riaFLags;
  }

  public void setRiaFLags(Long riaFLags) {
    this.riaFLags = riaFLags;
  }

}
