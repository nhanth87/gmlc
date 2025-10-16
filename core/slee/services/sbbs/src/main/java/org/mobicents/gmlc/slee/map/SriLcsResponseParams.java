package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SupportedLCSCapabilitySets;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SriLcsResponseParams implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * RoutingInfoForLCS-Res ::= SEQUENCE {
   *   targetMS                  [0] SubscriberIdentity,
   *   lcsLocationInfo           [1] LCSLocationInfo,
   *   extensionContainer        [2] ExtensionContainer OPTIONAL,
   *   ...,
   *   v-gmlc-Address            [3] GSN-Address OPTIONAL,
   *   h-gmlc-Address            [4] GSN-Address OPTIONAL,
   *   ppr-Address               [5] GSN-Address OPTIONAL,
   *   additional-v-gmlc-Address [6] GSN-Address OPTIONAL }

   * LCSLocationInfo ::= SEQUENCE {
   *   networkNode-Number              ISDN-AddressString,
   *    -- NetworkNode-number can be msc-number, sgsn-number or a dummy value of "0"
   *   lmsi                           [0] LMSI OPTIONAL,
   *   extensionContainer             [1] ExtensionContainer OPTIONAL,
   *   ... ,
   *   gprsNodeIndicator              [2] NULL OPTIONAL,
   *    -- gprsNodeIndicator is set only if the SGSN number is sent as the Network Node Number
   *   additional-Number              [3] Additional-Number OPTIONAL,
   *   supportedLCS-CapabilitySets    [4] SupportedLCS-CapabilitySets OPTIONAL,
   *   additional-LCS-CapabilitySets  [5] SupportedLCS-CapabilitySets OPTIONAL,
   *   mme-Name                       [6] DiameterIdentity OPTIONAL,
   *   aaa-Server-Name                [8] DiameterIdentity OPTIONAL,
   *   sgsn-Name                      [9] DiameterIdentity OPTIONAL,
   *   sgsn-Realm                    [10] DiameterIdentity OPTIONAL
   *  }
   */
  private ISDNAddressString msisdn; // The MSISDN is provided to identify the target MS.
  private IMSI imsi; // International Mobile Subscriber Identity defined in 3GPP TS 23.003.
  private LMSI lmsi; // Local MS identity allocated by the VLR to a given subscriber for internal management of data in the VLR
  private ISDNAddressString networkNodeNumber; // ISDN number of LCS target node (MSC or MME, SGSN, or IP-SM-GW) or of an LCS Router.
  private Boolean gprsNodeIndicator; // Indication that the Network Node Number received from HLR, etc. is to be considered as the SGSN number.
  private AdditionalNumber additionalNumber; // This parameter refers to the ISDN number of an additional LCS target node (MSC or MME or SGSN) or of an LCS Router.
  private SupportedLCSCapabilitySets supportedLCSCapabilitySets, addSupportedLCSCapabilitySets; // Capability sets of LCS supported in the VLR or SGSN. Provided only if LCS capability sets are available in HLR and Network Node Number is present in this message.
  private DiameterIdentity mmeName; // Diameter Identity of an MME as defined in 3GPP TS 23.003.
  private DiameterIdentity sgsnName, sgsnRealm; // Diameter Identity of an SGSN as defined in 3GPP TS 23.003. These parameters are provided in a successful response when the serving node is an SGSN and the SGSN has indicated its support for Lgd interface.
  private DiameterIdentity aaaServerName; // Diameter Identity of a 3GPP AAA server as defined in 3GPP TS 29.273.
  private GSNAddress hGmlcAddress, vGmlcAddress, pprAddress, addVGmlcAddress; // IP address of a H-GMLC, V-GMLC and PPR.

  private String pslMsisdn, pslImsi;
  private Integer pslLcsReferenceNumber, pslReferenceNumber;

  public SriLcsResponseParams() {
    super();
  }

  public ISDNAddressString getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(ISDNAddressString msisdn) {
    this.msisdn = msisdn;
  }

  public IMSI getImsi() {
    return imsi;
  }

  public void setImsi(IMSI imsi) {
    this.imsi = imsi;
  }

  public LMSI getLmsi() {
    return lmsi;
  }

  public void setLmsi(LMSI lmsi) {
    this.lmsi = lmsi;
  }

  public ISDNAddressString getNetworkNodeNumber() {
    return networkNodeNumber;
  }

  public void setNetworkNodeNumber(ISDNAddressString networkNodeNumber) {
    this.networkNodeNumber = networkNodeNumber;
  }

  public Boolean isGprsNodeIndicator() {
    return gprsNodeIndicator;
  }

  public void setGprsNodeIndicator(Boolean gprsNodeIndicator) {
    this.gprsNodeIndicator = gprsNodeIndicator;
  }

  public AdditionalNumber getAdditionalNumber() {
    return additionalNumber;
  }

  public void setAdditionalNumber(AdditionalNumber additionalNumber) {
    this.additionalNumber = additionalNumber;
  }

  public SupportedLCSCapabilitySets getSupportedLCSCapabilitySets() {
    return supportedLCSCapabilitySets;
  }

  public void setSupportedLCSCapabilitySets(SupportedLCSCapabilitySets supportedLCSCapabilitySets) {
    this.supportedLCSCapabilitySets = supportedLCSCapabilitySets;
  }

  public SupportedLCSCapabilitySets getAddSupportedLCSCapabilitySets() {
    return addSupportedLCSCapabilitySets;
  }

  public void setAddSupportedLCSCapabilitySets(SupportedLCSCapabilitySets addSupportedLCSCapabilitySets) {
    this.addSupportedLCSCapabilitySets = addSupportedLCSCapabilitySets;
  }

  public DiameterIdentity getMmeName() {
    return mmeName;
  }

  public void setMmeName(DiameterIdentity mmeName) {
    this.mmeName = mmeName;
  }

  public DiameterIdentity getSgsnName() {
    return sgsnName;
  }

  public void setSgsnName(DiameterIdentity sgsnName) {
    this.sgsnName = sgsnName;
  }

  public DiameterIdentity getSgsnRealm() {
    return sgsnRealm;
  }

  public void setSgsnRealm(DiameterIdentity sgsnRealm) {
    this.sgsnRealm = sgsnRealm;
  }

  public DiameterIdentity getAaaServerName() {
    return aaaServerName;
  }

  public void setAaaServerName(DiameterIdentity aaaServerName) {
    this.aaaServerName = aaaServerName;
  }

  public GSNAddress getHGmlcAddress() {
    return hGmlcAddress;
  }

  public void setHGmlcAddress(GSNAddress hGmlcAddress) {
    this.hGmlcAddress = hGmlcAddress;
  }

  public GSNAddress getVGmlcAddress() {
    return vGmlcAddress;
  }

  public void setVGmlcAddress(GSNAddress vGmlcAddress) {
    this.vGmlcAddress = vGmlcAddress;
  }

  public GSNAddress getPprAddress() {
    return pprAddress;
  }

  public void setPprAddress(GSNAddress pprAddress) {
    this.pprAddress = pprAddress;
  }

  public GSNAddress getAddVGmlcAddress() {
    return addVGmlcAddress;
  }

  public void setAddVGmlcAddress(GSNAddress addVGmlcAddress) {
    this.addVGmlcAddress = addVGmlcAddress;
  }

  public String getPslMsisdn() { return this.pslMsisdn; }

  public void setPslMsisdn(String pslMsisdn) { this.pslMsisdn = pslMsisdn; }

  public String getPslImsi() { return this.pslImsi; }

  public void setPslImsi(String pslImsi) { this.pslImsi = pslImsi; }

  public Integer getPslLcsReferenceNumber() { return this.pslLcsReferenceNumber; }

  public void setPslLcsReferenceNumber(Integer pslLcsReferenceNumber) { this.pslLcsReferenceNumber = pslLcsReferenceNumber; }

  public Integer getPslReferenceNumber() { return this.pslReferenceNumber; }

  public void setPslReferenceNumber(Integer pslReferenceNumber) { this.pslReferenceNumber = pslReferenceNumber; }

  @Override
  public String toString() {
    return "SriLcsResponseParams{" +
        "msisdn=" + msisdn +
        ", imsi=" + imsi +
        ", lmsi=" + lmsi +
        ", networkNodeNumber=" + networkNodeNumber +
        ", gprsNodeIndicator=" + gprsNodeIndicator +
        ", additionalNumber=" + additionalNumber +
        ", supportedLCSCapabilitySets=" + supportedLCSCapabilitySets +
        ", addSupportedLCSCapabilitySets=" + addSupportedLCSCapabilitySets +
        ", mmeName=" + mmeName +
        ", sgsnName=" + sgsnName +
        ", sgsnRealm=" + sgsnRealm +
        ", aaaServerName=" + aaaServerName +
        ", hGmlcAddress=" + hGmlcAddress +
        ", vGmlcAddress=" + vGmlcAddress +
        ", pprAddress=" + pprAddress +
        ", addVGmlcAddress=" + addVGmlcAddress +
        ", pslMsisdn='" + pslMsisdn + '\'' +
        ", pslImsi='" + pslImsi + '\'' +
        ", pslLcsReferenceNumber=" + pslLcsReferenceNumber +
        ", pslReferenceNumber=" + pslReferenceNumber +
        '}';
  }
}

