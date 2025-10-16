package org.mobicents.gmlc.slee.diameter.sh;

import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import org.mobicents.gmlc.slee.diameter.sh.elements.Sh5GSTrackingAreaId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShCellGlobalId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShEUTRANCellGlobalId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShGeodeticInformation;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShGeographicalInformation;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShLocationAreaId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShLocationNumber;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShNRCellGlobalId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShRoutingAreaId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShServiceAreaId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShTrackingAreaId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShUserCSGInformation;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShVisitedPLMNId;

import java.io.Serializable;
import java.util.Arrays;

public class ShUdaAvpValues implements Serializable {

  private static final long serialVersionUID =1L;

  private static final int DIAMETER_Sh_VENDOR_ID = 10415;

  /*
  3GPP TS 29.329 v15.2.0 reference
    The User-Data-Answer (UDA) command, indicated by the Command-Code field set to 306 and the 'R' bit cleared in the Command Flags field,
    is sent by a server in response to the User-Data-Request command.
    The Experimental-Result AVP may contain one of the values defined in clause 6.2 or in 3GPP TS 29.229 [6].

      Message Format

      < User-Data-Answer > ::=	< Diameter Header: 306, PXY, 16777217 >

								< Session-Id >
								  [ DRMP ]
								  { Vendor-Specific-Application-Id }
                                  [ Result-Code ]
								  [ Experimental-Result ]
								  { Auth-Session-State }
								  { Origin-Host }
								  { Origin-Realm }
								  *[ Supported-Features ]
								  [ Wildcarded-Public-Identity ]
								  [ Wildcarded-IMPU ]
								  [ User-Data ]
								  [ OC-Supported-Features ]
								  [ OC-OLR ]
								  *[ Load ]
								  *[ AVP ]
								  [ Failed-AVP ]
								  *[ Proxy-Info ]
								  *[ Route-Record ]

   */

  private Long resultCode;
  private ExperimentalResultAvp experimentalResultAvp;
  private String wildcardedPublicIdentity;
  private String wildcardedIMPU;
  private byte[] userData;
  private String msisdn;
  private String imsPublicIdentity;
  private CSLocationInformation csLocationInformation;
  private PSLocationInformation psLocationInformation;
  private EPSLocationInformation epsLocationInformation;
  private Sh5GSLocationInformation sh5GSLocationInformation;
  private ShCellGlobalId csCellGlobalId;
  private ShCellGlobalId psCellGlobalId;
  private ShServiceAreaId csServiceAreaId;
  private ShServiceAreaId psServiceAreaId;
  private ShLocationAreaId csLocationAreaId;
  private ShLocationAreaId psLocationAreaId;
  private ShLocationNumber locationNumber;
  private ShGeographicalInformation csGeographicalInformation;
  private ShGeographicalInformation psGeographicalInformation;
  private ShGeographicalInformation epsGeographicalInformation;
  private ShGeographicalInformation sh5GSGeographicalInformation;
  private ShGeodeticInformation csGeodeticInformation;
  private ShGeodeticInformation psGeodeticInformation;
  private ShGeodeticInformation epsGeodeticInformation;
  private MSCNumber mscNumber;
  private VLRNumber vlrNumber;
  private SGSNNumber sgsnNumber;
  private String mmeName;
  private String csCurrentLocationInfoRetrieved;
  private String psCurrentLocationInfoRetrieved;
  private String epsCurrentLocationInfoRetrieved;
  private String sh5GSCurrentLocationInfoRetrieved;
  private Integer csAgeOfLocationInfo;
  private Integer psAgeOfLocationInfo;
  private Integer epsAgeOfLocationInfo;
  private Integer sh5GSAgeOfLocationInfo;
  private ShEUTRANCellGlobalId eutrancgi;
  private ShRoutingAreaId routingAreaId;
  private ShTrackingAreaId trackingAreaId;
  private ShUserCSGInformation userCSGInformation;
  private ShNRCellGlobalId shNRCellGlobalId;
  private Sh5GSTrackingAreaId sh5GSTrackingAreaId;
  private String amfAddress;
  private String smsfAddress;
  private ShVisitedPLMNId psVisitedPLMNId, epsVisitedPLMNId, sh5gsVisitedPLMNId;
  private LocalTimeZone csLocalTimeZone, psLocalTimeZone, epsLocalTimeZone, sh5gsLocalTimeZone;
  private Integer psRatType, epsRatType, sh5gsRatType;
  //private OCSupportedFeatures ocSupportedFeatures //not implemented yet in jain-slee.diameter
  //private OCOlr ocOlr //not implemented yet in jain-slee.diameter

  public ShUdaAvpValues() {
    super();
  }

  public ShUdaAvpValues(byte[] userData) {
    super();
    this.userData = userData;
  }

  public ShUdaAvpValues(CSLocationInformation csLocationInformation, PSLocationInformation psLocationInformation, EPSLocationInformation epsLocationInformation,
                        Sh5GSLocationInformation sh5GSLocationInformation) {
    super();
    this.csLocationInformation = csLocationInformation;
    this.psLocationInformation = psLocationInformation;
    this.epsLocationInformation = epsLocationInformation;
    this.sh5GSLocationInformation = sh5GSLocationInformation;
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

  public String getWildcardedPublicIdentity() {
    return wildcardedPublicIdentity;
  }

  public void setWildcardedPublicIdentity(String wildcardedPublicIdentity) {
    this.wildcardedPublicIdentity = wildcardedPublicIdentity;
  }

  public String getWildcardedIMPU() {
    return wildcardedIMPU;
  }

  public void setWildcardedIMPU(String wildcardedIMPU) {
    this.wildcardedIMPU = wildcardedIMPU;
  }

  public byte[] getUserData() {
    return userData;
  }

  public void setUserData(byte[] userData) {
    this.userData = userData;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getImsPublicIdentity() {
    return imsPublicIdentity;
  }

  public void setImsPublicIdentity(String imsPublicIdentity) {
    this.imsPublicIdentity = imsPublicIdentity;
  }

  public CSLocationInformation getCsLocationInformation() {
    return csLocationInformation;
  }

  public void setCsLocationInformation(CSLocationInformation csLocationInformation) {
    this.csLocationInformation = csLocationInformation;
  }

  public PSLocationInformation getPsLocationInformation() {
    return psLocationInformation;
  }

  public void setPsLocationInformation(PSLocationInformation psLocationInformation) {
    this.psLocationInformation = psLocationInformation;
  }

  public EPSLocationInformation getEpsLocationInformation() {
    return epsLocationInformation;
  }

  public void setEpsLocationInformation(EPSLocationInformation epsLocationInformation) {
    this.epsLocationInformation = epsLocationInformation;
  }

  public Sh5GSLocationInformation getSh5GSLocationInformation() {
    return sh5GSLocationInformation;
  }

  public void setSh5GSLocationInformation(Sh5GSLocationInformation sh5GSLocationInformation) {
    this.sh5GSLocationInformation = sh5GSLocationInformation;
  }

  public ShCellGlobalId getCsCellGlobalId() {
    return csCellGlobalId;
  }

  public void setCsCellGlobalId(ShCellGlobalId csCellGlobalId) {
    this.csCellGlobalId = csCellGlobalId;
  }

  public ShCellGlobalId getPsCellGlobalId() {
    return psCellGlobalId;
  }

  public void setPsCellGlobalId(ShCellGlobalId psCellGlobalId) {
    this.psCellGlobalId = psCellGlobalId;
  }

  public ShServiceAreaId getCsServiceAreaId() {
    return csServiceAreaId;
  }

  public void setCsServiceAreaId(ShServiceAreaId csServiceAreaId) {
    this.csServiceAreaId = csServiceAreaId;
  }

  public ShServiceAreaId getPsServiceAreaId() {
    return psServiceAreaId;
  }

  public void setPsServiceAreaId(ShServiceAreaId psServiceAreaId) {
    this.psServiceAreaId = psServiceAreaId;
  }

  public ShLocationAreaId getCsLocationAreaId() {
    return csLocationAreaId;
  }

  public void setCsLocationAreaId(ShLocationAreaId csLocationAreaId) {
    this.csLocationAreaId = csLocationAreaId;
  }

  public ShLocationAreaId getPsLocationAreaId() {
    return psLocationAreaId;
  }

  public void setPsLocationAreaId(ShLocationAreaId psLocationAreaId) {
    this.psLocationAreaId = psLocationAreaId;
  }

  public ShLocationNumber getLocationNumber() {
    return locationNumber;
  }

  public void setLocationNumber(ShLocationNumber locationNumber) {
    this.locationNumber = locationNumber;
  }

  public ShGeographicalInformation getCsGeographicalInformation() {
    return csGeographicalInformation;
  }

  public void setCsGeographicalInformation(ShGeographicalInformation csGeographicalInformation) {
    this.csGeographicalInformation = csGeographicalInformation;
  }

  public ShGeographicalInformation getPsGeographicalInformation() {
    return psGeographicalInformation;
  }

  public void setPsGeographicalInformation(ShGeographicalInformation psGeographicalInformation) {
    this.psGeographicalInformation = psGeographicalInformation;
  }

  public ShGeographicalInformation getEpsGeographicalInformation() {
    return epsGeographicalInformation;
  }

  public void setEpsGeographicalInformation(ShGeographicalInformation epsGeographicalInformation) {
    this.epsGeographicalInformation = epsGeographicalInformation;
  }

  public ShGeographicalInformation getSh5GSGeographicalInformation() {
    return sh5GSGeographicalInformation;
  }

  public void setSh5GSGeographicalInformation(ShGeographicalInformation sh5GSGeographicalInformation) {
    this.sh5GSGeographicalInformation = sh5GSGeographicalInformation;
  }

  public ShGeodeticInformation getCsGeodeticInformation() {
    return csGeodeticInformation;
  }

  public void setCsGeodeticInformation(ShGeodeticInformation csGeodeticInformation) {
    this.csGeodeticInformation = csGeodeticInformation;
  }

  public ShGeodeticInformation getPsGeodeticInformation() {
    return psGeodeticInformation;
  }

  public void setPsGeodeticInformation(ShGeodeticInformation psGeodeticInformation) {
    this.psGeodeticInformation = psGeodeticInformation;
  }

  public ShGeodeticInformation getEpsGeodeticInformation() {
    return epsGeodeticInformation;
  }

  public void setEpsGeodeticInformation(ShGeodeticInformation epsGeodeticInformation) {
    this.epsGeodeticInformation = epsGeodeticInformation;
  }

  public MSCNumber getMscNumber() {
    return mscNumber;
  }

  public void setMscNumber(MSCNumber mscNumber) {
    this.mscNumber = mscNumber;
  }

  public VLRNumber getVlrNumber() {
    return vlrNumber;
  }

  public void setVlrNumber(VLRNumber vlrNumber) {
    this.vlrNumber = vlrNumber;
  }

  public SGSNNumber getSgsnNumber() {
    return sgsnNumber;
  }

  public void setSgsnNumber(SGSNNumber sgsnNumber) {
    this.sgsnNumber = sgsnNumber;
  }

  public String getMmeName() {
    return mmeName;
  }

  public void setMmeName(String mmeName) {
    this.mmeName = mmeName;
  }

  public String getCsCurrentLocationInfoRetrieved() {
    return csCurrentLocationInfoRetrieved;
  }

  public void setCsCurrentLocationInfoRetrieved(String csCurrentLocationInfoRetrieved) {
    this.csCurrentLocationInfoRetrieved = csCurrentLocationInfoRetrieved;
  }

  public String getPsCurrentLocationInfoRetrieved() {
    return psCurrentLocationInfoRetrieved;
  }

  public void setPsCurrentLocationInfoRetrieved(String psCurrentLocationInfoRetrieved) {
    this.psCurrentLocationInfoRetrieved = psCurrentLocationInfoRetrieved;
  }

  public String getEpsCurrentLocationInfoRetrieved() {
    return epsCurrentLocationInfoRetrieved;
  }

  public void setEpsCurrentLocationInfoRetrieved(String epsCurrentLocationInfoRetrieved) {
    this.epsCurrentLocationInfoRetrieved = epsCurrentLocationInfoRetrieved;
  }

  public String getSh5GSCurrentLocationInfoRetrieved() {
    return sh5GSCurrentLocationInfoRetrieved;
  }

  public void setSh5GSCurrentLocationInfoRetrieved(String sh5GSCurrentLocationInfoRetrieved) {
    this.sh5GSCurrentLocationInfoRetrieved = sh5GSCurrentLocationInfoRetrieved;
  }

  public Integer getCsAgeOfLocationInfo() {
    return csAgeOfLocationInfo;
  }

  public void setCsAgeOfLocationInfo(Integer csAgeOfLocationInfo) {
    this.csAgeOfLocationInfo = csAgeOfLocationInfo;
  }

  public Integer getPsAgeOfLocationInfo() {
    return psAgeOfLocationInfo;
  }

  public void setPsAgeOfLocationInfo(Integer psAgeOfLocationInfo) {
    this.psAgeOfLocationInfo = psAgeOfLocationInfo;
  }

  public Integer getEpsAgeOfLocationInfo() {
    return epsAgeOfLocationInfo;
  }

  public void setEpsAgeOfLocationInfo(Integer epsAgeOfLocationInfo) {
    this.epsAgeOfLocationInfo = epsAgeOfLocationInfo;
  }

  public Integer getSh5GSAgeOfLocationInfo() {
    return sh5GSAgeOfLocationInfo;
  }

  public void setSh5GSAgeOfLocationInfo(Integer sh5GSAgeOfLocationInfo) {
    this.sh5GSAgeOfLocationInfo = sh5GSAgeOfLocationInfo;
  }

  public ShEUTRANCellGlobalId getEutrancgi() {
    return eutrancgi;
  }

  public void setEutrancgi(ShEUTRANCellGlobalId eutrancgi) {
    this.eutrancgi = eutrancgi;
  }

  public ShRoutingAreaId getRoutingAreaId() {
    return routingAreaId;
  }

  public void setRoutingAreaId(ShRoutingAreaId routingAreaId) {
    this.routingAreaId = routingAreaId;
  }

  public ShTrackingAreaId getTrackingAreaId() {
    return trackingAreaId;
  }

  public void setTrackingAreaId(ShTrackingAreaId trackingAreaId) {
    this.trackingAreaId = trackingAreaId;
  }

  public ShUserCSGInformation getUserCSGInformation() {
    return userCSGInformation;
  }

  public void setUserCSGInformation(ShUserCSGInformation userCSGInformation) {
    this.userCSGInformation = userCSGInformation;
  }

  public ShNRCellGlobalId getShNRCellGlobalId() {
    return shNRCellGlobalId;
  }

  public void setShNRCellGlobalId(ShNRCellGlobalId shNRCellGlobalId) {
    this.shNRCellGlobalId = shNRCellGlobalId;
  }

  public Sh5GSTrackingAreaId getSh5GSTrackingAreaId() {
    return sh5GSTrackingAreaId;
  }

  public void setSh5GSTrackingAreaId(Sh5GSTrackingAreaId sh5GSTrackingAreaId) {
    this.sh5GSTrackingAreaId = sh5GSTrackingAreaId;
  }

  public String getAmfAddress() {
    return amfAddress;
  }

  public void setAmfAddress(String amfAddress) {
    this.amfAddress = amfAddress;
  }

  public String getSmsfAddress() {
    return smsfAddress;
  }

  public void setSmsfAddress(String smfAddress) {
    this.smsfAddress = smfAddress;
  }

  public ShVisitedPLMNId getPsVisitedPLMNId() {
    return psVisitedPLMNId;
  }

  public void setPsVisitedPLMNId(ShVisitedPLMNId psVisitedPLMNId) {
    this.psVisitedPLMNId = psVisitedPLMNId;
  }

  public ShVisitedPLMNId getEpsVisitedPLMNId() {
    return epsVisitedPLMNId;
  }

  public void setEpsVisitedPLMNId(ShVisitedPLMNId epsVisitedPLMNId) {
    this.epsVisitedPLMNId = epsVisitedPLMNId;
  }

  public ShVisitedPLMNId getSh5gsVisitedPLMNId() {
    return sh5gsVisitedPLMNId;
  }

  public void setSh5gsVisitedPLMNId(ShVisitedPLMNId sh5gsVisitedPLMNId) {
    this.sh5gsVisitedPLMNId = sh5gsVisitedPLMNId;
  }

  public LocalTimeZone getCsLocalTimeZone() {
    return csLocalTimeZone;
  }

  public void setCsLocalTimeZone(LocalTimeZone csLocalTimeZone) {
    this.csLocalTimeZone = csLocalTimeZone;
  }

  public LocalTimeZone getPsLocalTimeZone() {
    return psLocalTimeZone;
  }

  public void setPsLocalTimeZone(LocalTimeZone psLocalTimeZone) {
    this.psLocalTimeZone = psLocalTimeZone;
  }

  public LocalTimeZone getEpsLocalTimeZone() {
    return epsLocalTimeZone;
  }

  public void setEpsLocalTimeZone(LocalTimeZone epsLocalTimeZone) {
    this.epsLocalTimeZone = epsLocalTimeZone;
  }

  public LocalTimeZone getSh5gsLocalTimeZone() {
    return sh5gsLocalTimeZone;
  }

  public void setSh5gsLocalTimeZone(LocalTimeZone sh5gsLocalTimeZone) {
    this.sh5gsLocalTimeZone = sh5gsLocalTimeZone;
  }

  public Integer getPsRatType() {
    return psRatType;
  }

  public void setPsRatType(Integer psRatType) {
    this.psRatType = psRatType;
  }

  public Integer getEpsRatType() {
    return epsRatType;
  }

  public void setEpsRatType(Integer epsRatType) {
    this.epsRatType = epsRatType;
  }

  public Integer getSh5gsRatType() {
    return sh5gsRatType;
  }

  public void setSh5gsRatType(Integer sh5gsRatType) {
    this.sh5gsRatType = sh5gsRatType;
  }

  @Override
  public String toString() {
    return "ShUdaAvpValues{" +
        "resultCode=" + resultCode +
        ", experimentalResultAvp=" + experimentalResultAvp +
        ", wildcardedPublicIdentity='" + wildcardedPublicIdentity + '\'' +
        ", wildcardedIMPU='" + wildcardedIMPU + '\'' +
        ", userData=" + Arrays.toString(userData) +
        ", msisdn='" + msisdn + '\'' +
        ", imsPublicIdentity='" + imsPublicIdentity + '\'' +
        ", csLocationInformation=" + csLocationInformation +
        ", psLocationInformation=" + psLocationInformation +
        ", epsLocationInformation=" + epsLocationInformation +
        ", sh5GSLocationInformation=" + sh5GSLocationInformation +
        ", csCellGlobalId=" + csCellGlobalId +
        ", psCellGlobalId=" + psCellGlobalId +
        ", csServiceAreaId=" + csServiceAreaId +
        ", psServiceAreaId=" + psServiceAreaId +
        ", csLocationAreaId=" + csLocationAreaId +
        ", psLocationAreaId=" + psLocationAreaId +
        ", locationNumber=" + locationNumber +
        ", csGeographicalInformation=" + csGeographicalInformation +
        ", psGeographicalInformation=" + psGeographicalInformation +
        ", epsGeographicalInformation=" + epsGeographicalInformation +
        ", sh5GSGeographicalInformation=" + sh5GSGeographicalInformation +
        ", csGeodeticInformation=" + csGeodeticInformation +
        ", psGeodeticInformation=" + psGeodeticInformation +
        ", epsGeodeticInformation=" + epsGeodeticInformation +
        ", mscNumber=" + mscNumber +
        ", vlrNumber=" + vlrNumber +
        ", sgsnNumber=" + sgsnNumber +
        ", mmeName='" + mmeName + '\'' +
        ", csCurrentLocationInfoRetrieved='" + csCurrentLocationInfoRetrieved + '\'' +
        ", psCurrentLocationInfoRetrieved='" + psCurrentLocationInfoRetrieved + '\'' +
        ", epsCurrentLocationInfoRetrieved='" + epsCurrentLocationInfoRetrieved + '\'' +
        ", sh5GSCurrentLocationInfoRetrieved='" + sh5GSCurrentLocationInfoRetrieved + '\'' +
        ", csAgeOfLocationInfo=" + csAgeOfLocationInfo +
        ", psAgeOfLocationInfo=" + psAgeOfLocationInfo +
        ", epsAgeOfLocationInfo=" + epsAgeOfLocationInfo +
        ", sh5GSAgeOfLocationInfo=" + sh5GSAgeOfLocationInfo +
        ", eutrancgi=" + eutrancgi +
        ", routingAreaId=" + routingAreaId +
        ", trackingAreaId=" + trackingAreaId +
        ", userCSGInformation=" + userCSGInformation +
        ", shNRCellGlobalId=" + shNRCellGlobalId +
        ", sh5GSTrackingAreaId=" + sh5GSTrackingAreaId +
        ", amfAddress='" + amfAddress + '\'' +
        ", smsfAddress='" + smsfAddress + '\'' +
        ", psVisitedPLMNId=" + psVisitedPLMNId +
        ", epsVisitedPLMNId=" + epsVisitedPLMNId +
        ", sh5gsVisitedPLMNId=" + sh5gsVisitedPLMNId +
        ", csLocalTimeZone=" + csLocalTimeZone +
        ", psLocalTimeZone=" + psLocalTimeZone +
        ", epsLocalTimeZone=" + epsLocalTimeZone +
        ", sh5gsLocalTimeZone=" + sh5gsLocalTimeZone +
        ", psRatType=" + psRatType +
        ", epsRatType=" + epsRatType +
        ", sh5gsRatType=" + sh5gsRatType +
        '}';
  }
}
