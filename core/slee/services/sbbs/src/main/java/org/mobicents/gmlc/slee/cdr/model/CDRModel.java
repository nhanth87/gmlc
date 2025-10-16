package org.mobicents.gmlc.slee.cdr.model;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class CDRModel {

    private String timeStamp  = "";
    private String cdrDate;
    private String cdrTime;
    private String gmlcId = "";
    private String curlUser  = "";
    private String recordStatus  = "";
    private String statusCode  = "";
    private String localDialogId  = "";
    private String remoteDialogId  = "";
    private String dialogDuration  = "";
    /*
     * LOCAL Address
     */
    private String localSPC  = "";
    private String localSSN  = "";
    private String localRoutingIndicator  = "";

    /*
     * LOCAL GLOBAL TITLE
     */
    private String localGlobalTitleIndicator  = "";
    private String localGlobalTitleDigits  = "";

    /*
     * REMOTE Address
     */
    private String remoteSPC  = "";
    private String remoteSSN  = "";
    private String remoteRoutingIndicator  = "";

    /*
     * REMOTE GLOBAL TITLE
     */
    private String remoteGlobalTitleIndicator  = "";
    private String remoteGlobalTitleDigits  = "";

    /*
     * ISDN Address
     */
    private String isdnAddressNature = "";
    private String isdnNumberingPlanIndicator = "";
    private String isdnAddressDigits = "";

    /*
     * Diameter Session Id
     */
    private String diameterSessionId  = "";

    /*
     * Diameter Command Origin Host and Realm
     */
    private String diameterOriginHost  = "";
    private String diameterOriginRealm  = "";

    /*
     * Diameter Command Destination Host and Realm
     */
    private String diameterDestinationHost  = "";
    private String diameterDestinationRealm  = "";

    /*
     * SUPL TLS SLP Socket (GMLC)
     */
    private String slpSocketAddress  = "";
    private String slpSocketPort  = "";

    /*
     * SUPL TLS SET Socket (GMLC)
     */
    private String setSocketAddress  = "";
    private String setSocketPort  = "";

    /**
     * CS Location Information
     */

    /*
     * LOCATION NUMBER
     */

    private String locationNumberOddFlag  = "";
    private String locationNumberNAI  = "";
    private String locationNumberINNI  = "";
    private String locationNumberNPI  = "";
    private String locationNumberAPRI  = "";
    private String locationNumberSI  = "";
    private String locationNumberAddress  = "";

    /*
     * CGI
     */
    private String cellGlobalIdServiceAreaIdMCC  = "";
    private String cellGlobalIdServiceAreaIdMNC  = "";
    private String cellGlobalIdServiceAreaIdLac  = "";
    private String cellGlobalIdServiceAreaIdCI  = "";

    /*
     * VLR NUMBER
     */
    private String vlrNumber  = "";

    /*
     * MSC NUMBER
     */
    private String mscNumber  = "";

    /*
     * AGE OF LOCATION INFORMATION
     */
    private String ageOfLocationInformation  = "";

    /*
     * GEOGRAPHICAL INFO
     */
    private String geographicalInfoTypeOfShape  = "";
    private String geographicalInfoLatitude  = "";
    private String geographicalInfoLongitude  = "";
    private String geographicalInfoUncertainty  = "";

    /*
     * GEODETIC INFO
     */
    private String geodeticInfoTypeOfShape  = "";
    private String geodeticInfoLatitude  = "";
    private String geodeticInfoLongitude  = "";
    private String geodeticInfoUncertainty  = "";
    private String geodeticInfoConfidence  = "";
    private String geodeticInfoScreeningAndPresentationIndicators  = "";

    /*
     * E-UTRAN CGI from EPS location information from ATI, PSI or Sh
     */
    private String eutranCellGlobalIdMCC = "";
    private String eutranCellGlobalIdMNC = "";
    private String eutranCellGlobalIdECI = "";
    private String eutranCellGlobalIdENBID = "";
    private String eutranCellGlobalIdCI = "";
    private String cellPortionId  = "";

    /*
     * TRACKING AREA IDENTITY from EPS location information from ATI, PSI or Sh
     */
    private String taiMCC  = "";
    private String taiMNC  = "";
    private String taiTAC  = "";

    /*
     * EPS GEOGRAPHICAL INFORMATION from EPS location information from ATI, PSI or Sh
     */
    private String epsLocationInfoGeographicalTypeOfShape = "";
    private String epsLocationInfoGeographicalLatitude = "";
    private String epsLocationInfoGeographicalLongitude = "";
    private String epsLocationInfoGeographicalUncertainty = "";

    /*
     * E-UTRAN CGI from 5GS location information from Sh
     */
    private String eutranCellGlobalId5GsMCC = "";
    private String eutranCellGlobalId5GsMNC = "";
    private String eutranCellGlobalId5GsECI = "";
    private String eutranCellGlobalId5GsENBID = "";
    private String eutranCellGlobalId5GsCI = "";
    private String cellPortionId5Gs  = "";

    /*
     * TRACKING AREA IDENTITY from 5GS location information from Sh
     */
    private String tai5GsMCC  = "";
    private String tai5GsMNC  = "";
    private String tai5GsTAC  = "";

    /*
     * GEOGRAPHICAL INFORMATION from 5GS location information
     */
    private String geographicalInfo5GsTypeOfShape  = "";
    private String geographicalInfo5GsLatitude  = "";
    private String geographicalInfo5GsLongitude  = "";
    private String geographicalInfo5GsUncertainty  = "";

    /*
     * EPS GEODETIC INFORMATION
     */
    private String epsLocationInfoGeodeticTypeOfShape = "";
    private String epsLocationInfoGeodeticLatitude = "";
    private String epsLocationInfoGeodeticLongitude = "";
    private String epsLocationInfoGeodeticUncertainty = "";
    private String epsLocationInfoGeodeticConfidence = "";
    private String epsLocationInfoGeodeticScreeningAndPresentationInd = "";

    /*
     * 5GS GEODETIC INFORMATION
     */
    private String nrLocationInfoGeodeticTypeOfShape = "";
    private String nrLocationInfoGeodeticLatitude = "";
    private String nrLocationInfoGeodeticLongitude = "";
    private String nrLocationInfoGeodeticUncertainty = "";
    private String nrLocationInfoGeodeticConfidence = "";
    private String nrLocationInfoGeodeticScreeningAndPresentationInd = "";

    /*
     * MME NAME
     */
    private String epsLocationInfoGeodeticMMEName = "";

    /*
     *-------------------------------
     * Location Information GPRS
     * ------------------------------
     */

    /*
     * PS CGI
     */
    private String psCellGlobalIdServiceAreaIdOrLAIMCC  = "";
    private String psCellGlobalIdServiceAreaIdOrLAIMNC  = "";
    private String psCellGlobalIdServiceAreaIdOrLAILac  = "";
    private String psCellGlobalIdServiceAreaIdOrLAICI  = "";

    /*
     * ROUTEING AREA IDENTITY
     */
    private String raiMCC  = "";
    private String raiMNC  = "";
    private String raiLAC  = "";
    private String raiRAC  = "";

    /*
     * PS AGE OF LOCATION INFORMATION
     */
    private String psAgeOfLocationInformation  = "";

    /*
     * GPRS GEOGRAPHICAL INFORMATION
     */

    private String psGeographicalInfoTypeOfShape  = "";
    private String psGeographicalInfoLatitude  = "";
    private String psGeographicalInfoLongitude  = "";
    private String psGeographicalInfoUncertainty  = "";

    /*
     * GEODETIC INFO
     */
    private String psGeodeticInfoTypeOfShape  = "";
    private String psGeodeticInfoLatitude  = "";
    private String psGeodeticInfoLongitude  = "";
    private String psGeodeticInfoUncertainty  = "";
    private String psGeodeticInfoConfidence  = "";
    private String psGeodeticInfoScreeningAndPresentationIndicators  = "";

    /*
     * LSA ID
     */
    private String lsaId  = "";
    private String lsaIdPLMNSignificant  = "";

    /*
     * SGSN NUMBER
     */
    private String sgsnNumber  = "";

    /*
     * SAI Present
     */
    private String saiPresent  = "";

    /*
     * CS and PS Subscriber STATE
     */
    private String subscriberState  = "";
    private String notReachableReasonState  = "";

    // MS CLASSMARK 2
    private String msClassmark  = "";
    // GPRS MS CLASS MS RADIO ACCESS CAPABILITY
    private String gprsMSRadioAccessCapability  = "";
    // GPRS MS CLASS MS NETWORK CAPABILITY
    private String gprsMSNetworkCapability  = "";

    /*
     * MNP INFO RESULT (from ATI or PSI)
     */

    private String mnpNumberPortabilityStatus  = "";
    private String mnpIMSI  = "";
    private String mnpMSISDN  = "";
    private String mnpRouteingNumber  = "";


    /*
     * Sh 5GS Location Information only parameters
     */
    /*
     * NR Cell Global Id
     */
    private String nrCellGlobalId5GsMCC  = "";
    private String nrCellGlobalId5GsMNC  = "";
    private String nrCellGlobalId5GsNCI  = "";

    /*
     * 5GS AMF Address
     */
    private String amfAddress5Gs = "";

    /*
     * 5GS SMSF Address
     */
    private String smsfAddress5gs = "";

    /*
     * Visited PLMN ID from Sh UDR/UDA
     */
    private String visitedPlmnIdMCC  = "";
    private String visitedPlmnIdMNC  = "";

    /*
     * Local Time Zone from Sh UDR/UDA
     */
    private String localTimeZone  = "";
    private String daylightSavingTime  = "";

    /*
     * RAT type from Sh UDR/UDA
     */
    private String ratType  = "";

    /*
     * Current Location Retrieved
     */
    private String currentLocationRetrieved  = "";

    /*
     * IMSI
     * LMSI
     */
    private String imsi = "";
    private String lmsi = "";

    /*
     * MAP LSM (SRILCS, PSL & SLR) and LTE LCS (RIR, PLR, LRR) gathered parameters
     */
    /*
     * NETWORK NODE NUMBER
     */
    private String networkNodeNumber  = "";

    /*
     * GPRS NODE INDICATOR
     */
    private String gprsNodeIndicator  = "";

    /*
     * ADDITIONAL NUMBER
     */
    // MAP LSM ADDITIONAL NUMBER (MSC)
    private String mapMSCNumber  = "";
    // MAP LSM ADDITIONAL NUMBER (SGSN)
    private String mapSGSNNumber  = "";

    /*
     * MME NAME
     */
    private String mmeName  = "";

    /*
     * MME REALM
     */
    private String mmeRealm  = "";

    /*
     * SGSN NAME
     */
    private String sgsnName  = "";

    /*
     * SGSN REALM
     */
    private String sgsnRealm  = "";

    /*
     * H-GMLC ADDRESS
     */
    private String hgmlcAddressType = "";
    private String hgmlcAddressData = "";

    /*
     * V-GMLC ADDRESS
     */
    private String vgmlcAddressType = "";
    private String vgmlcAddressData = "";

    /*
     * PPR ADDRESS
     */
    private String pprAddressType  = "";
    private String pprAddressData  = "";

    /*
     * LCS Capability Sets
     */
    private String lcsCapabilitySets = "";

    /*
     + 3GPP AAA Server Name
     */
    private String tgppAAAServerName = "";


    /*
     * LOCATION ESTIMATE
     */
    private String locationEstimateTypeOfShape  = "";
    private String locationEstimateLatitude  = "";
    private String locationEstimateLongitude  = "";
    private String locationEstimateUncertainty  = "";
    private String locationEstimateUncertaintySemiMajorAxis  = "";
    private String locationEstimateUncertaintySemiMinorAxis  = "";
    private String locationEstimateConfidence  = "";
    private String locationEstimateAngleOfMajorAxis  = "";
    private String locationEstimateAltitude  = "";
    private String locationEstimateUncertaintyAltitude  = "";
    private String locationEstimateInnerRadius  = "";
    private String locationEstimateUncertaintyRadius  = "";
    private String locationEstimateOffSetAngle  = "";
    private String locationEstimateIncludedAngle  = "";

    /*
     * ADDITIONAL LOCATION ESTIMATE
     */
    private String additionalLocationEstimateTypeOfShape  = "";
    private String additionalLocationEstimatePolygonNumberOfPoint  = "";
    private String additionalLocationEstPolyListLatLongPoints = "";
    private String polygonCentroidLatitude  = "";
    private String polygonCentroidLongitude  = "";

    // ADDITIONAL LOCATION ESTIMATE LATITUDE
    private String additionalLocationEstimateLatitude  = "";
    private String additionalLocationEstimateLongitude  = "";
    private String additionalLocationEstimateUncertainty  = "";
    private String additionalLocationEstimateUncertaintySemiMajorAxis  = "";
    private String additionalLocationEstimateUncertaintySemiMinorAxis  = "";
    private String additionalLocationEstimateAngleOfMajorAxis  = "";
    private String additionalLocationEstimateConfidence  = "";
    private String additionalLocationEstimateAltitude  = "";
    private String additionalLocationEstimateUncertaintyAltitude  = "";
    private String additionalLocationEstimateInnerRadius  = "";
    private String additionalLocationEstimateUncertaintyRadius  = "";
    private String additionalLocationEstimateOffSetAngle  = "";
    private String additionalLocationEstimateIncludedAngle  = "";

    /*
     * AGE OF LOCATION ESTIMATE
     */
    private String ageOfLocationEstimate  = "";

    /*
     * GERAN POSITIONING DATA
     */
    private String geranPositioningDataInformation  = "";

    /*
     * GERAN GANSS POSITIONING DATA
     */
    private String geranGANSSPositioningDataInformation  = "";

    /*
     * UTRAN POSITIONING DATA
     */
    private String utranPositioningDataInfo  = "";

    /*
     * UTRAN GANSS POSITIONING DATA
     */
    private String utranGANSSPositioningData  = "";

    /*
     * UTRAN ADDITIONAL POSITIONING DATA
     */
    private String utranAdditionalPositioningDataInfo  = "";


    /*
     * E-UTRAN POSITIONING DATA
     */
    private String eutranPositioningDataInfo = "";

    /*
     * DEFERRED MT LR RESPONSE INDICATOR
     */
    private String deferredMTLRResponseIndicator  = "";

    /*
     * CGI or SAI or LAI
     */
    private String lcsCGIorSAIorLAIMCC  = "";
    private String lcsCGIorSAIorLAIMNC  = "";
    private String lcsCGIorSAIorLAILAC  = "";
    private String lcsCGIorSAIorLAICI  = "";

    /*
     * LTE ECGI (obtained from SLg)
     */
    private String lcsEutranCgiMCC  = "";
    private String lcsEutranCgiMNC  = "";
    private String lcsEutranCgiECI  = "";
    private String lcsEutranCgiENBID  = "";
    private String lcsEutranCgiCI  = "";
    private String lcsCellPortionId  = "";

    /*
     * PSEUDONYM INDICATOR
     */
    private String pseudonymIndicator  = "";

    /*
     * ACCURACY FULFILLMENT INDICATOR
     */
    private String accuracyFulfilmentIndicator  = "";

    /*
     * SEQUENCE NUMBER
     */
    private String sequenceNumber  = "";

    /*
     * VELOCITY ESTIMATE
     */
    // HORIZONTAL VELOCITY ESTIMATE
    private String horizontalVelocityEstimate  = "";

    // VELOCITY ESTIMATE BEARING
    private String velocityEstimateBearing  = "";

    // VERTICAL VELOCITY ESTIMATE
    private String verticalVelocityEstimate  = "";

    // VELOCITY ESTIMATE HORIZONTAL UNCERTAINTY
    private String velocityHorizontalUncertainty  = "";

    // VELOCITY ESTIMATE VERTICAL UNCERTAINTY
    private String velocityVerticalUncertainty  = "";

    // VELOCITY ESTIMATE TYPE
    private String velocityType  = "";

    /*
     * SERVING NODE ADDRESS
     */
    private String servingNodeAddressMSCNumber  = "";
    private String servingNodeAddressSGSNNumber  = "";
    private String servingNodeAddressMMENumber  = "";

    /*
     * LCSClientID
     */
    private String lcsClientType  = "";
    private String lcsClientName  = "";
    private String lcsClientDCS  = "";
    private String lcsClientFI  = "";
    private String lcsClientAPN  = "";
    private String lcsClientDialedByMS  = "";
    private String lcsClientExternalID  = "";
    private String lcsClientInternalID  = "";
    private String lcsClientRequestorDCS  = "";
    private String lcsClientRequestorFI  = "";
    private String lcsClientRequestorString  = "";

    /*
     * LCS QoS
     */
    // LCS QoS HORIZONTAL ACCURACY
    private String lcsQoSHorizontalAccuracy  = "";

    // LCS QoS VERTICAL ACCURACY
    private String lcsQoSverticalAccuracy  = "";

    // LCS QoS VERTICAL COORDINATE REQUESTED
    private String lcsQoSVerticalCoordinateRequest  = "";

    // LCS QoS RESPONSE TIME
    private String lcsQoSResponseTime  = "";

    // LCS QoS CLASS
    private String lcsQoSClass  = "";

    /*
     * Client LCS REFERENCE NUMBER
     */
    private String clientReferenceNumber  = "";

    /*
     * LCS REFERENCE NUMBER
     */
    private String lcsReferenceNumber  = "";

    /*
     * LCS SERVICE TYPE ID
     */
    private String lcsServiceTypeID  = "";

    /*
     * BAROMETRIC PRESSURE
     */
    private String barometricPressureMeasurement  = "";

    /*
     * CIVIC ADDRESS
     */
    private String civicAddress  = "";

    /*
     * LCS EVENT (MAP/LTE)
     */
    private String lcsEvent  = "";

    /*
     * MSISDN
     */
    private String msisdnAddress  = "";

    /*
     * IMEI
     */
    private String imei  = "";

    /*
     * DEFERRED MT LR DATA
     */
    // LOCATION EVENT TYPE
    private String deferredLocationEventType  = "";

    /*
     * DEFERRED MT-LR DATA
     */
    // DEFERRED MT-LR DATA NETWORK NODE NUMBER
    private String deferredLcsLocationInfoNetworkNodeNumber  = "";

    // DEFERRED MT-LR DATA GPRS NODE IND
    private String deferredLcsLocationInfoGprsNodeIndicator  = "";

    // DEFERRED MT-LR DATA ADDITIONAL NUMBER
    private String deferredLcsLocationInfoAdditionalNumber  = "";

    // DEFERRED MT-LR DATA LMSI
    private String deferredLcsLocationInfoLMSI  = "";

    // DEFERRED MT-LR DATA MME NAME
    private String deferredLcsLocationInfoMmeName  = "";

    // DEFERRED MT-LR DATA AAA SERVER NAME
    private String deferredLcsLocationInfoAaaServerName  = "";

    // DEFERRED MT-LR DATA LCS CAPS R98_99
    private String deferredLcsCsR98_99  = "";

    // DEFERRED MT-LR DATA LCS CAPS R4
    private String deferredLcsCsR4  = "";

    // DEFERRED MT-LR DATA LCS CAPS R5
    private String deferredLcsCsR5  = "";

    // DEFERRED MT-LR DATA LCS CAPS R6
    private String deferredLcsCsR6  = "";

    // DEFERRED MT-LR DATA LCS CAPS R7
    private String deferredLcsCsR7  = "";


    // DEFERRED MT-LR DATA ADD LCS CAPS R98_99
    private String deferredALcsCsR98_99  = "";

    // DEFERRED MT-LR DATA ADD LCS CAPS R4
    private String deferredALcsCsR4  = "";

    // DEFERRED MT-LR DATA ADD LCS CAPS R5
    private String deferredALcsCsR5  = "";

    // DEFERRED MT-LR DATA ADD LCS CAPS R6
    private String deferredALcsCsR6  = "";

    // DEFERRED MT-LR DATA ADD LCS CAPS R7
    private String deferredALcsCsR7  = "";

    private String deferredTerminationCause  = "";

    /*
     * PERIODIC LDR INFO
     */
    // REPORTING AMOUNT
    private String periodicReportingAmount  = "";
    private String periodicReportingInterval  = "";

    /*
     * MO-LR SHORT-CIRCUIT INDICATOR
     */
    private String moLrShortCircuitIndicator  = "";

    private String reportingPLMNList  = "";

    /*
     * 1xRTT-RCID
     */
    private String oneXRTTRCID  = "";

    /*
     * na-ESRK Request
     */
    private String naEsrkRequest = "";

    /*
     * na-ESRD
     */
    private String naESRD = "";

    /*
     * na-ESRK
     */
    private String naESRK = "";

    /*
     * AMF-Instance-Id
     */
    private String amfInstanceId = "";

    /*
     * SLh & SLg Flags
     */
    protected Long riaFlags;
    protected Long plrFlags;
    protected Long plaFlags;
    protected Long lrrFlags;
    protected Long lraFlags;


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCdrDate() {
        return cdrDate;
    }

    public void setCdrDate(String cdrDate) {
        this.cdrDate = cdrDate;
    }

    public String getCdrTime() {
        return cdrTime;
    }

    public void setCdrTime(String cdrTime) {
        this.cdrTime = cdrTime;
    }

    public String getGmlcId() {
        return gmlcId;
    }

    public void setGmlcId(String gmlcId) {
        this.gmlcId = gmlcId;
    }

    public String getCurlUser() {
        return curlUser;
    }

    public void setCurlUser(String curlUser) {
        this.curlUser = curlUser;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getLocalDialogId() {
        return localDialogId;
    }

    public void setLocalDialogId(String localDialogId) {
        this.localDialogId = localDialogId;
    }

    public String getRemoteDialogId() {
        return remoteDialogId;
    }

    public void setRemoteDialogId(String remoteDialogId) {
        this.remoteDialogId = remoteDialogId;
    }

    public String getDialogDuration() {
        return dialogDuration;
    }

    public void setDialogDuration(String dialogDuration) {
        this.dialogDuration = dialogDuration;
    }

    public String getLocalSPC() {
        return localSPC;
    }

    public void setLocalSPC(String localSPC) {
        this.localSPC = localSPC;
    }

    public String getLocalSSN() {
        return localSSN;
    }

    public void setLocalSSN(String localSSN) {
        this.localSSN = localSSN;
    }

    public String getLocalRoutingIndicator() {
        return localRoutingIndicator;
    }

    public void setLocalRoutingIndicator(String localRoutingIndicator) {
        this.localRoutingIndicator = localRoutingIndicator;
    }

    public String getLocalGlobalTitleIndicator() {
        return localGlobalTitleIndicator;
    }

    public void setLocalGlobalTitleIndicator(String localGlobalTitleIndicator) {
        this.localGlobalTitleIndicator = localGlobalTitleIndicator;
    }

    public String getLocalGlobalTitleDigits() {
        return localGlobalTitleDigits;
    }

    public void setLocalGlobalTitleDigits(String localGlobalTitleDigits) {
        this.localGlobalTitleDigits = localGlobalTitleDigits;
    }

    public String getRemoteSPC() {
        return remoteSPC;
    }

    public void setRemoteSPC(String remoteSPC) {
        this.remoteSPC = remoteSPC;
    }

    public String getRemoteSSN() {
        return remoteSSN;
    }

    public void setRemoteSSN(String remoteSSN) {
        this.remoteSSN = remoteSSN;
    }

    public String getRemoteRoutingIndicator() {
        return remoteRoutingIndicator;
    }

    public void setRemoteRoutingIndicator(String remoteRoutingIndicator) {
        this.remoteRoutingIndicator = remoteRoutingIndicator;
    }

    public String getRemoteGlobalTitleIndicator() {
        return remoteGlobalTitleIndicator;
    }

    public void setRemoteGlobalTitleIndicator(String remoteGlobalTitleIndicator) {
        this.remoteGlobalTitleIndicator = remoteGlobalTitleIndicator;
    }

    public String getRemoteGlobalTitleDigits() {
        return remoteGlobalTitleDigits;
    }

    public void setRemoteGlobalTitleDigits(String remoteGlobalTitleDigits) {
        this.remoteGlobalTitleDigits = remoteGlobalTitleDigits;
    }

    public String getIsdnAddressNature() {
        return isdnAddressNature;
    }

    public void setIsdnAddressNature(String isdnAddressNature) {
        this.isdnAddressNature = isdnAddressNature;
    }

    public String getIsdnNumberingPlanIndicator() {
        return isdnNumberingPlanIndicator;
    }

    public void setIsdnNumberingPlanIndicator(String isdnNumberingPlanIndicator) {
        this.isdnNumberingPlanIndicator = isdnNumberingPlanIndicator;
    }

    public String getIsdnAddressDigits() {
        return isdnAddressDigits;
    }

    public void setIsdnAddressDigits(String isdnAddressDigits) {
        this.isdnAddressDigits = isdnAddressDigits;
    }

    public String getDiameterSessionId() {
        return diameterSessionId;
    }

    public void setDiameterSessionId(String diameterSessionId) {
        this.diameterSessionId = diameterSessionId;
    }

    public String getDiameterOriginHost() {
        return diameterOriginHost;
    }

    public void setDiameterOriginHost(String diameterOriginHost) {
        this.diameterOriginHost = diameterOriginHost;
    }

    public String getDiameterOriginRealm() {
        return diameterOriginRealm;
    }

    public void setDiameterOriginRealm(String diameterOriginRealm) {
        this.diameterOriginRealm = diameterOriginRealm;
    }

    public String getDiameterDestinationHost() {
        return diameterDestinationHost;
    }

    public void setDiameterDestinationHost(String diameterDestinationHost) {
        this.diameterDestinationHost = diameterDestinationHost;
    }

    public String getDiameterDestinationRealm() {
        return diameterDestinationRealm;
    }

    public void setDiameterDestinationRealm(String diameterDestinationRealm) {
        this.diameterDestinationRealm = diameterDestinationRealm;
    }

    public String getSlpSocketAddress() {
        return slpSocketAddress;
    }

    public void setSlpSocketAddress(String slpSocketAddress) {
        this.slpSocketAddress = slpSocketAddress;
    }

    public String getSlpSocketPort() {
        return slpSocketPort;
    }

    public void setSlpSocketPort(String slpSocketPort) {
        this.slpSocketPort = slpSocketPort;
    }

    public String getSetSocketAddress() {
        return setSocketAddress;
    }

    public void setSetSocketAddress(String setSocketAddress) {
        this.setSocketAddress = setSocketAddress;
    }

    public String getSetSocketPort() {
        return setSocketPort;
    }

    public void setSetSocketPort(String setSocketPort) {
        this.setSocketPort = setSocketPort;
    }

    public String getLocationNumberOddFlag() {
        return locationNumberOddFlag;
    }

    public void setLocationNumberOddFlag(String locationNumberOddFlag) {
        this.locationNumberOddFlag = locationNumberOddFlag;
    }

    public String getLocationNumberNAI() {
        return locationNumberNAI;
    }

    public void setLocationNumberNAI(String locationNumberNAI) {
        this.locationNumberNAI = locationNumberNAI;
    }

    public String getLocationNumberINNI() {
        return locationNumberINNI;
    }

    public void setLocationNumberINNI(String locationNumberINNI) {
        this.locationNumberINNI = locationNumberINNI;
    }

    public String getLocationNumberNPI() {
        return locationNumberNPI;
    }

    public void setLocationNumberNPI(String locationNumberNPI) {
        this.locationNumberNPI = locationNumberNPI;
    }

    public String getLocationNumberAPRI() {
        return locationNumberAPRI;
    }

    public void setLocationNumberAPRI(String locationNumberAPRI) {
        this.locationNumberAPRI = locationNumberAPRI;
    }

    public String getLocationNumberSI() {
        return locationNumberSI;
    }

    public void setLocationNumberSI(String locationNumberSI) {
        this.locationNumberSI = locationNumberSI;
    }

    public String getLocationNumberAddress() {
        return locationNumberAddress;
    }

    public void setLocationNumberAddress(String locationNumberAddress) {
        this.locationNumberAddress = locationNumberAddress;
    }

    public String getCellGlobalIdServiceAreaIdMCC() {
        return cellGlobalIdServiceAreaIdMCC;
    }

    public void setCellGlobalIdServiceAreaIdMCC(String cellGlobalIdServiceAreaIdMCC) {
        this.cellGlobalIdServiceAreaIdMCC = cellGlobalIdServiceAreaIdMCC;
    }

    public String getCellGlobalIdServiceAreaIdMNC() {
        return cellGlobalIdServiceAreaIdMNC;
    }

    public void setCellGlobalIdServiceAreaIdMNC(String cellGlobalIdServiceAreaIdMNC) {
        this.cellGlobalIdServiceAreaIdMNC = cellGlobalIdServiceAreaIdMNC;
    }

    public String getCellGlobalIdServiceAreaIdLac() {
        return cellGlobalIdServiceAreaIdLac;
    }

    public void setCellGlobalIdServiceAreaIdLac(String cellGlobalIdServiceAreaIdLac) {
        this.cellGlobalIdServiceAreaIdLac = cellGlobalIdServiceAreaIdLac;
    }

    public String getCellGlobalIdServiceAreaIdCI() {
        return cellGlobalIdServiceAreaIdCI;
    }

    public void setCellGlobalIdServiceAreaIdCI(String cellGlobalIdServiceAreaIdCI) {
        this.cellGlobalIdServiceAreaIdCI = cellGlobalIdServiceAreaIdCI;
    }

    public String getVlrNumber() {
        return vlrNumber;
    }

    public void setVlrNumber(String vlrNumber) {
        this.vlrNumber = vlrNumber;
    }

    public String getMscNumber() {
        return mscNumber;
    }

    public void setMscNumber(String mscNumber) {
        this.mscNumber = mscNumber;
    }

    public String getAgeOfLocationInformation() {
        return ageOfLocationInformation;
    }

    public void setAgeOfLocationInformation(String ageOfLocationInformation) {
        this.ageOfLocationInformation = ageOfLocationInformation;
    }

    public String getGeographicalInfoTypeOfShape() {
        return geographicalInfoTypeOfShape;
    }

    public void setGeographicalInfoTypeOfShape(String geographicalInfoTypeOfShape) {
        this.geographicalInfoTypeOfShape = geographicalInfoTypeOfShape;
    }

    public String getGeographicalInfoLatitude() {
        return geographicalInfoLatitude;
    }

    public void setGeographicalInfoLatitude(String geographicalInfoLatitude) {
        this.geographicalInfoLatitude = geographicalInfoLatitude;
    }

    public String getGeographicalInfoLongitude() {
        return geographicalInfoLongitude;
    }

    public void setGeographicalInfoLongitude(String geographicalInfoLongitude) {
        this.geographicalInfoLongitude = geographicalInfoLongitude;
    }

    public String getGeographicalInfoUncertainty() {
        return geographicalInfoUncertainty;
    }

    public void setGeographicalInfoUncertainty(String geographicalInfoUncertainty) {
        this.geographicalInfoUncertainty = geographicalInfoUncertainty;
    }

    public String getGeodeticInfoTypeOfShape() {
        return geodeticInfoTypeOfShape;
    }

    public void setGeodeticInfoTypeOfShape(String geodeticInfoTypeOfShape) {
        this.geodeticInfoTypeOfShape = geodeticInfoTypeOfShape;
    }

    public String getGeodeticInfoLatitude() {
        return geodeticInfoLatitude;
    }

    public void setGeodeticInfoLatitude(String geodeticInfoLatitude) {
        this.geodeticInfoLatitude = geodeticInfoLatitude;
    }

    public String getGeodeticInfoLongitude() {
        return geodeticInfoLongitude;
    }

    public void setGeodeticInfoLongitude(String geodeticInfoLongitude) {
        this.geodeticInfoLongitude = geodeticInfoLongitude;
    }

    public String getGeodeticInfoUncertainty() {
        return geodeticInfoUncertainty;
    }

    public void setGeodeticInfoUncertainty(String geodeticInfoUncertainty) {
        this.geodeticInfoUncertainty = geodeticInfoUncertainty;
    }

    public String getGeodeticInfoConfidence() {
        return geodeticInfoConfidence;
    }

    public void setGeodeticInfoConfidence(String geodeticInfoConfidence) {
        this.geodeticInfoConfidence = geodeticInfoConfidence;
    }

    public String getGeodeticInfoScreeningAndPresentationIndicators() {
        return geodeticInfoScreeningAndPresentationIndicators;
    }

    public void setGeodeticInfoScreeningAndPresentationIndicators(String geodeticInfoScreeningAndPresentationIndicators) {
        this.geodeticInfoScreeningAndPresentationIndicators = geodeticInfoScreeningAndPresentationIndicators;
    }

    public String getEutranCellGlobalIdMCC() {
        return eutranCellGlobalIdMCC;
    }

    public void setEutranCellGlobalIdMCC(String eutranCellGlobalIdMCC) {
        this.eutranCellGlobalIdMCC = eutranCellGlobalIdMCC;
    }

    public String getEutranCellGlobalIdMNC() {
        return eutranCellGlobalIdMNC;
    }

    public void setEutranCellGlobalIdMNC(String eutranCellGlobalIdMNC) {
        this.eutranCellGlobalIdMNC = eutranCellGlobalIdMNC;
    }

    public String getEutranCellGlobalIdECI() {
        return eutranCellGlobalIdECI;
    }

    public void setEutranCellGlobalIdECI(String eutranCellGlobalIdECI) {
        this.eutranCellGlobalIdECI = eutranCellGlobalIdECI;
    }

    public String getEutranCellGlobalIdENBID() {
        return eutranCellGlobalIdENBID;
    }

    public void setEutranCellGlobalIdENBID(String eutranCellGlobalIdENBID) {
        this.eutranCellGlobalIdENBID = eutranCellGlobalIdENBID;
    }

    public String getEutranCellGlobalIdCI() {
        return eutranCellGlobalIdCI;
    }

    public void setEutranCellGlobalIdCI(String eutranCellGlobalIdCI) {
        this.eutranCellGlobalIdCI = eutranCellGlobalIdCI;
    }

    public String getCellPortionId() {
        return cellPortionId;
    }

    public void setCellPortionId(String cellPortionId) {
        this.cellPortionId = cellPortionId;
    }

    public String getTaiMCC() {
        return taiMCC;
    }

    public void setTaiMCC(String taiMCC) {
        this.taiMCC = taiMCC;
    }

    public String getTaiMNC() {
        return taiMNC;
    }

    public void setTaiMNC(String taiMNC) {
        this.taiMNC = taiMNC;
    }

    public String getTaiTAC() {
        return taiTAC;
    }

    public void setTaiTAC(String taiTAC) {
        this.taiTAC = taiTAC;
    }

    public String getEpsLocationInfoGeographicalTypeOfShape() {
        return epsLocationInfoGeographicalTypeOfShape;
    }

    public void setEpsLocationInfoGeographicalTypeOfShape(String epsLocationInfoGeographicalTypeOfShape) {
        this.epsLocationInfoGeographicalTypeOfShape = epsLocationInfoGeographicalTypeOfShape;
    }

    public String getEpsLocationInfoGeographicalLatitude() {
        return epsLocationInfoGeographicalLatitude;
    }

    public void setEpsLocationInfoGeographicalLatitude(String epsLocationInfoGeographicalLatitude) {
        this.epsLocationInfoGeographicalLatitude = epsLocationInfoGeographicalLatitude;
    }

    public String getEpsLocationInfoGeographicalLongitude() {
        return epsLocationInfoGeographicalLongitude;
    }

    public void setEpsLocationInfoGeographicalLongitude(String epsLocationInfoGeographicalLongitude) {
        this.epsLocationInfoGeographicalLongitude = epsLocationInfoGeographicalLongitude;
    }

    public String getEpsLocationInfoGeographicalUncertainty() {
        return epsLocationInfoGeographicalUncertainty;
    }

    public void setEpsLocationInfoGeographicalUncertainty(String epsLocationInfoGeographicalUncertainty) {
        this.epsLocationInfoGeographicalUncertainty = epsLocationInfoGeographicalUncertainty;
    }

    public String getNrLocationInfoGeodeticTypeOfShape() {
        return nrLocationInfoGeodeticTypeOfShape;
    }

    public void setNrLocationInfoGeodeticTypeOfShape(String nrLocationInfoGeodeticTypeOfShape) {
        this.nrLocationInfoGeodeticTypeOfShape = nrLocationInfoGeodeticTypeOfShape;
    }

    public String getNrLocationInfoGeodeticLatitude() {
        return nrLocationInfoGeodeticLatitude;
    }

    public void setNrLocationInfoGeodeticLatitude(String nrLocationInfoGeodeticLatitude) {
        this.nrLocationInfoGeodeticLatitude = nrLocationInfoGeodeticLatitude;
    }

    public String getNrLocationInfoGeodeticLongitude() {
        return nrLocationInfoGeodeticLongitude;
    }

    public void setNrLocationInfoGeodeticLongitude(String nrLocationInfoGeodeticLongitude) {
        this.nrLocationInfoGeodeticLongitude = nrLocationInfoGeodeticLongitude;
    }

    public String getNrLocationInfoGeodeticUncertainty() {
        return nrLocationInfoGeodeticUncertainty;
    }

    public void setNrLocationInfoGeodeticUncertainty(String nrLocationInfoGeodeticUncertainty) {
        this.nrLocationInfoGeodeticUncertainty = nrLocationInfoGeodeticUncertainty;
    }

    public String getNrLocationInfoGeodeticConfidence() {
        return nrLocationInfoGeodeticConfidence;
    }

    public void setNrLocationInfoGeodeticConfidence(String nrLocationInfoGeodeticConfidence) {
        this.nrLocationInfoGeodeticConfidence = nrLocationInfoGeodeticConfidence;
    }

    public String getNrLocationInfoGeodeticScreeningAndPresentationInd() {
        return nrLocationInfoGeodeticScreeningAndPresentationInd;
    }

    public void setNrLocationInfoGeodeticScreeningAndPresentationInd(String nrLocationInfoGeodeticScreeningAndPresentationInd) {
        this.nrLocationInfoGeodeticScreeningAndPresentationInd = nrLocationInfoGeodeticScreeningAndPresentationInd;
    }

    public String getEutranCellGlobalId5GsMCC() {
        return eutranCellGlobalId5GsMCC;
    }

    public void setEutranCellGlobalId5GsMCC(String eutranCellGlobalId5GsMCC) {
        this.eutranCellGlobalId5GsMCC = eutranCellGlobalId5GsMCC;
    }

    public String getEutranCellGlobalId5GsMNC() {
        return eutranCellGlobalId5GsMNC;
    }

    public void setEutranCellGlobalId5GsMNC(String eutranCellGlobalId5GsMNC) {
        this.eutranCellGlobalId5GsMNC = eutranCellGlobalId5GsMNC;
    }

    public String getEutranCellGlobalId5GsECI() {
        return eutranCellGlobalId5GsECI;
    }

    public void setEutranCellGlobalId5GsECI(String eutranCellGlobalId5GsECI) {
        this.eutranCellGlobalId5GsECI = eutranCellGlobalId5GsECI;
    }

    public String getEutranCellGlobalId5GsENBID() {
        return eutranCellGlobalId5GsENBID;
    }

    public void setEutranCellGlobalId5GsENBID(String eutranCellGlobalId5GsENBID) {
        this.eutranCellGlobalId5GsENBID = eutranCellGlobalId5GsENBID;
    }

    public String getEutranCellGlobalId5GsCI() {
        return eutranCellGlobalId5GsCI;
    }

    public void setEutranCellGlobalId5GsCI(String eutranCellGlobalId5GsCI) {
        this.eutranCellGlobalId5GsCI = eutranCellGlobalId5GsCI;
    }

    public String getCellPortionId5Gs() {
        return cellPortionId5Gs;
    }

    public void setCellPortionId5Gs(String cellPortionId5Gs) {
        this.cellPortionId5Gs = cellPortionId5Gs;
    }

    public String getTai5GsMCC() {
        return tai5GsMCC;
    }

    public void setTai5GsMCC(String tai5GsMCC) {
        this.tai5GsMCC = tai5GsMCC;
    }

    public String getTai5GsMNC() {
        return tai5GsMNC;
    }

    public void setTai5GsMNC(String tai5GsMNC) {
        this.tai5GsMNC = tai5GsMNC;
    }

    public String getTai5GsTAC() {
        return tai5GsTAC;
    }

    public void setTai5GsTAC(String tai5GsTAC) {
        this.tai5GsTAC = tai5GsTAC;
    }

    public String getGeographicalInfo5GsTypeOfShape() {
        return geographicalInfo5GsTypeOfShape;
    }

    public void setGeographicalInfo5GsTypeOfShape(String geographicalInfo5GsTypeOfShape) {
        this.geographicalInfo5GsTypeOfShape = geographicalInfo5GsTypeOfShape;
    }

    public String getGeographicalInfo5GsLatitude() {
        return geographicalInfo5GsLatitude;
    }

    public void setGeographicalInfo5GsLatitude(String geographicalInfo5GsLatitude) {
        this.geographicalInfo5GsLatitude = geographicalInfo5GsLatitude;
    }

    public String getGeographicalInfo5GsLongitude() {
        return geographicalInfo5GsLongitude;
    }

    public void setGeographicalInfo5GsLongitude(String geographicalInfo5GsLongitude) {
        this.geographicalInfo5GsLongitude = geographicalInfo5GsLongitude;
    }

    public String getGeographicalInfo5GsUncertainty() {
        return geographicalInfo5GsUncertainty;
    }

    public void setGeographicalInfo5GsUncertainty(String geographicalInfo5GsUncertainty) {
        this.geographicalInfo5GsUncertainty = geographicalInfo5GsUncertainty;
    }

    public String getEpsLocationInfoGeodeticTypeOfShape() {
        return epsLocationInfoGeodeticTypeOfShape;
    }

    public void setEpsLocationInfoGeodeticTypeOfShape(String epsLocationInfoGeodeticTypeOfShape) {
        this.epsLocationInfoGeodeticTypeOfShape = epsLocationInfoGeodeticTypeOfShape;
    }

    public String getEpsLocationInfoGeodeticLatitude() {
        return epsLocationInfoGeodeticLatitude;
    }

    public void setEpsLocationInfoGeodeticLatitude(String epsLocationInfoGeodeticLatitude) {
        this.epsLocationInfoGeodeticLatitude = epsLocationInfoGeodeticLatitude;
    }

    public String getEpsLocationInfoGeodeticLongitude() {
        return epsLocationInfoGeodeticLongitude;
    }

    public void setEpsLocationInfoGeodeticLongitude(String epsLocationInfoGeodeticLongitude) {
        this.epsLocationInfoGeodeticLongitude = epsLocationInfoGeodeticLongitude;
    }

    public String getEpsLocationInfoGeodeticUncertainty() {
        return epsLocationInfoGeodeticUncertainty;
    }

    public void setEpsLocationInfoGeodeticUncertainty(String epsLocationInfoGeodeticUncertainty) {
        this.epsLocationInfoGeodeticUncertainty = epsLocationInfoGeodeticUncertainty;
    }

    public String getEpsLocationInfoGeodeticConfidence() {
        return epsLocationInfoGeodeticConfidence;
    }

    public void setEpsLocationInfoGeodeticConfidence(String epsLocationInfoGeodeticConfidence) {
        this.epsLocationInfoGeodeticConfidence = epsLocationInfoGeodeticConfidence;
    }

    public String getEpsLocationInfoGeodeticScreeningAndPresentationInd() {
        return epsLocationInfoGeodeticScreeningAndPresentationInd;
    }

    public void setEpsLocationInfoGeodeticScreeningAndPresentationInd(String epsLocationInfoGeodeticScreeningAndPresentationInd) {
        this.epsLocationInfoGeodeticScreeningAndPresentationInd = epsLocationInfoGeodeticScreeningAndPresentationInd;
    }

    public String getEpsLocationInfoGeodeticMMEName() {
        return epsLocationInfoGeodeticMMEName;
    }

    public void setEpsLocationInfoGeodeticMMEName(String epsLocationInfoGeodeticMMEName) {
        this.epsLocationInfoGeodeticMMEName = epsLocationInfoGeodeticMMEName;
    }

    public String getPsCellGlobalIdServiceAreaIdOrLAIMCC() {
        return psCellGlobalIdServiceAreaIdOrLAIMCC;
    }

    public void setPsCellGlobalIdServiceAreaIdOrLAIMCC(String psCellGlobalIdServiceAreaIdOrLAIMCC) {
        this.psCellGlobalIdServiceAreaIdOrLAIMCC = psCellGlobalIdServiceAreaIdOrLAIMCC;
    }

    public String getPsCellGlobalIdServiceAreaIdOrLAIMNC() {
        return psCellGlobalIdServiceAreaIdOrLAIMNC;
    }

    public void setPsCellGlobalIdServiceAreaIdOrLAIMNC(String psCellGlobalIdServiceAreaIdOrLAIMNC) {
        this.psCellGlobalIdServiceAreaIdOrLAIMNC = psCellGlobalIdServiceAreaIdOrLAIMNC;
    }

    public String getPsCellGlobalIdServiceAreaIdOrLAILac() {
        return psCellGlobalIdServiceAreaIdOrLAILac;
    }

    public void setPsCellGlobalIdServiceAreaIdOrLAILac(String psCellGlobalIdServiceAreaIdOrLAILac) {
        this.psCellGlobalIdServiceAreaIdOrLAILac = psCellGlobalIdServiceAreaIdOrLAILac;
    }

    public String getPsCellGlobalIdServiceAreaIdOrLAICI() {
        return psCellGlobalIdServiceAreaIdOrLAICI;
    }

    public void setPsCellGlobalIdServiceAreaIdOrLAICI(String psCellGlobalIdServiceAreaIdOrLAICI) {
        this.psCellGlobalIdServiceAreaIdOrLAICI = psCellGlobalIdServiceAreaIdOrLAICI;
    }

    public String getRaiMCC() {
        return raiMCC;
    }

    public void setRaiMCC(String raiMCC) {
        this.raiMCC = raiMCC;
    }

    public String getRaiMNC() {
        return raiMNC;
    }

    public void setRaiMNC(String raiMNC) {
        this.raiMNC = raiMNC;
    }

    public String getRaiLAC() {
        return raiLAC;
    }

    public void setRaiLAC(String raiLAC) {
        this.raiLAC = raiLAC;
    }

    public String getRaiRAC() {
        return raiRAC;
    }

    public void setRaiRAC(String raiRAC) {
        this.raiRAC = raiRAC;
    }

    public String getPsAgeOfLocationInformation() {
        return psAgeOfLocationInformation;
    }

    public void setPsAgeOfLocationInformation(String psAgeOfLocationInformation) {
        this.psAgeOfLocationInformation = psAgeOfLocationInformation;
    }

    public String getPsGeographicalInfoTypeOfShape() {
        return psGeographicalInfoTypeOfShape;
    }

    public void setPsGeographicalInfoTypeOfShape(String psGeographicalInfoTypeOfShape) {
        this.psGeographicalInfoTypeOfShape = psGeographicalInfoTypeOfShape;
    }

    public String getPsGeographicalInfoLatitude() {
        return psGeographicalInfoLatitude;
    }

    public void setPsGeographicalInfoLatitude(String psGeographicalInfoLatitude) {
        this.psGeographicalInfoLatitude = psGeographicalInfoLatitude;
    }

    public String getPsGeographicalInfoLongitude() {
        return psGeographicalInfoLongitude;
    }

    public void setPsGeographicalInfoLongitude(String psGeographicalInfoLongitude) {
        this.psGeographicalInfoLongitude = psGeographicalInfoLongitude;
    }

    public String getPsGeographicalInfoUncertainty() {
        return psGeographicalInfoUncertainty;
    }

    public void setPsGeographicalInfoUncertainty(String psGeographicalInfoUncertainty) {
        this.psGeographicalInfoUncertainty = psGeographicalInfoUncertainty;
    }

    public String getPsGeodeticInfoTypeOfShape() {
        return psGeodeticInfoTypeOfShape;
    }

    public void setPsGeodeticInfoTypeOfShape(String psGeodeticInfoTypeOfShape) {
        this.psGeodeticInfoTypeOfShape = psGeodeticInfoTypeOfShape;
    }

    public String getPsGeodeticInfoLatitude() {
        return psGeodeticInfoLatitude;
    }

    public void setPsGeodeticInfoLatitude(String psGeodeticInfoLatitude) {
        this.psGeodeticInfoLatitude = psGeodeticInfoLatitude;
    }

    public String getPsGeodeticInfoLongitude() {
        return psGeodeticInfoLongitude;
    }

    public void setPsGeodeticInfoLongitude(String psGeodeticInfoLongitude) {
        this.psGeodeticInfoLongitude = psGeodeticInfoLongitude;
    }

    public String getPsGeodeticInfoUncertainty() {
        return psGeodeticInfoUncertainty;
    }

    public void setPsGeodeticInfoUncertainty(String psGeodeticInfoUncertainty) {
        this.psGeodeticInfoUncertainty = psGeodeticInfoUncertainty;
    }

    public String getPsGeodeticInfoConfidence() {
        return psGeodeticInfoConfidence;
    }

    public void setPsGeodeticInfoConfidence(String psGeodeticInfoConfidence) {
        this.psGeodeticInfoConfidence = psGeodeticInfoConfidence;
    }

    public String getPsGeodeticInfoScreeningAndPresentationIndicators() {
        return psGeodeticInfoScreeningAndPresentationIndicators;
    }

    public void setPsGeodeticInfoScreeningAndPresentationIndicators(String psGeodeticInfoScreeningAndPresentationIndicators) {
        this.psGeodeticInfoScreeningAndPresentationIndicators = psGeodeticInfoScreeningAndPresentationIndicators;
    }

    public String getLsaId() {
        return lsaId;
    }

    public void setLsaId(String lsaId) {
        this.lsaId = lsaId;
    }

    public String getLsaIdPLMNSignificant() {
        return lsaIdPLMNSignificant;
    }

    public void setLsaIdPLMNSignificant(String lsaIdPLMNSignificant) {
        this.lsaIdPLMNSignificant = lsaIdPLMNSignificant;
    }

    public String getSgsnNumber() {
        return sgsnNumber;
    }

    public void setSgsnNumber(String sgsnNumber) {
        this.sgsnNumber = sgsnNumber;
    }

    public String getSaiPresent() {
        return saiPresent;
    }

    public void setSaiPresent(String saiPresent) {
        this.saiPresent = saiPresent;
    }

    public String getSubscriberState() {
        return subscriberState;
    }

    public void setSubscriberState(String subscriberState) {
        this.subscriberState = subscriberState;
    }

    public String getNotReachableReasonState() {
        return notReachableReasonState;
    }

    public void setNotReachableReasonState(String notReachableReasonState) {
        this.notReachableReasonState = notReachableReasonState;
    }

    public String getMsClassmark() {
        return msClassmark;
    }

    public void setMsClassmark(String msClassmark) {
        this.msClassmark = msClassmark;
    }

    public String getGprsMSRadioAccessCapability() {
        return gprsMSRadioAccessCapability;
    }

    public void setGprsMSRadioAccessCapability(String gprsMSRadioAccessCapability) {
        this.gprsMSRadioAccessCapability = gprsMSRadioAccessCapability;
    }

    public String getGprsMSNetworkCapability() {
        return gprsMSNetworkCapability;
    }

    public void setGprsMSNetworkCapability(String gprsMSNetworkCapability) {
        this.gprsMSNetworkCapability = gprsMSNetworkCapability;
    }

    public String getMnpNumberPortabilityStatus() {
        return mnpNumberPortabilityStatus;
    }

    public void setMnpNumberPortabilityStatus(String mnpNumberPortabilityStatus) {
        this.mnpNumberPortabilityStatus = mnpNumberPortabilityStatus;
    }

    public String getMnpIMSI() {
        return mnpIMSI;
    }

    public void setMnpIMSI(String mnpIMSI) {
        this.mnpIMSI = mnpIMSI;
    }

    public String getMnpMSISDN() {
        return mnpMSISDN;
    }

    public void setMnpMSISDN(String mnpMSISDN) {
        this.mnpMSISDN = mnpMSISDN;
    }

    public String getMnpRouteingNumber() {
        return mnpRouteingNumber;
    }

    public void setMnpRouteingNumber(String mnpRouteingNumber) {
        this.mnpRouteingNumber = mnpRouteingNumber;
    }

    public String getNrCellGlobalId5GsMCC() {
        return nrCellGlobalId5GsMCC;
    }

    public void setNrCellGlobalId5GsMCC(String nrCellGlobalId5GsMCC) {
        this.nrCellGlobalId5GsMCC = nrCellGlobalId5GsMCC;
    }

    public String getNrCellGlobalId5GsMNC() {
        return nrCellGlobalId5GsMNC;
    }

    public void setNrCellGlobalId5GsMNC(String nrCellGlobalId5GsMNC) {
        this.nrCellGlobalId5GsMNC = nrCellGlobalId5GsMNC;
    }

    public String getNrCellGlobalId5GsNCI() {
        return nrCellGlobalId5GsNCI;
    }

    public void setNrCellGlobalId5GsNCI(String nrCellGlobalId5GsNCI) {
        this.nrCellGlobalId5GsNCI = nrCellGlobalId5GsNCI;
    }

    public String getAmfAddress5Gs() {
        return amfAddress5Gs;
    }

    public void setAmfAddress5Gs(String amfAddress5Gs) {
        this.amfAddress5Gs = amfAddress5Gs;
    }

    public String getSmsfAddress5gs() {
        return smsfAddress5gs;
    }

    public void setSmsfAddress5gs(String smsfAddress5gs) {
        this.smsfAddress5gs = smsfAddress5gs;
    }

    public String getVisitedPlmnIdMCC() {
        return visitedPlmnIdMCC;
    }

    public void setVisitedPlmnIdMCC(String visitedPlmnIdMCC) {
        this.visitedPlmnIdMCC = visitedPlmnIdMCC;
    }

    public String getVisitedPlmnIdMNC() {
        return visitedPlmnIdMNC;
    }

    public void setVisitedPlmnIdMNC(String visitedPlmnIdMNC) {
        this.visitedPlmnIdMNC = visitedPlmnIdMNC;
    }

    public String getLocalTimeZone() {
        return localTimeZone;
    }

    public void setLocalTimeZone(String localTimeZone) {
        this.localTimeZone = localTimeZone;
    }

    public String getDaylightSavingTime() {
        return daylightSavingTime;
    }

    public void setDaylightSavingTime(String daylightSavingTime) {
        this.daylightSavingTime = daylightSavingTime;
    }

    public String getRatType() {
        return ratType;
    }

    public void setRatType(String ratType) {
        this.ratType = ratType;
    }

    public String getCurrentLocationRetrieved() {
        return currentLocationRetrieved;
    }

    public void setCurrentLocationRetrieved(String currentLocationRetrieved) {
        this.currentLocationRetrieved = currentLocationRetrieved;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getLmsi() {
        return lmsi;
    }

    public void setLmsi(String lmsi) {
        this.lmsi = lmsi;
    }

    public String getNetworkNodeNumber() {
        return networkNodeNumber;
    }

    public void setNetworkNodeNumber(String networkNodeNumber) {
        this.networkNodeNumber = networkNodeNumber;
    }

    public String getGprsNodeIndicator() {
        return gprsNodeIndicator;
    }

    public void setGprsNodeIndicator(String gprsNodeIndicator) {
        this.gprsNodeIndicator = gprsNodeIndicator;
    }

    public String getMapMSCNumber() {
        return mapMSCNumber;
    }

    public void setMapMSCNumber(String mapMSCNumber) {
        this.mapMSCNumber = mapMSCNumber;
    }

    public String getMapSGSNNumber() {
        return mapSGSNNumber;
    }

    public void setMapSGSNNumber(String mapSGSNNumber) {
        this.mapSGSNNumber = mapSGSNNumber;
    }

    public String getMmeName() {
        return mmeName;
    }

    public void setMmeName(String mmeName) {
        this.mmeName = mmeName;
    }

    public String getMmeRealm() {
        return mmeRealm;
    }

    public void setMmeRealm(String mmeRealm) {
        this.mmeRealm = mmeRealm;
    }

    public String getSgsnName() {
        return sgsnName;
    }

    public void setSgsnName(String sgsnName) {
        this.sgsnName = sgsnName;
    }

    public String getSgsnRealm() {
        return sgsnRealm;
    }

    public void setSgsnRealm(String sgsnRealm) {
        this.sgsnRealm = sgsnRealm;
    }

    public String getHgmlcAddressType() {
        return hgmlcAddressType;
    }

    public void setHgmlcAddressType(String hgmlcAddressType) {
        this.hgmlcAddressType = hgmlcAddressType;
    }

    public String getHgmlcAddressData() {
        return hgmlcAddressData;
    }

    public void setHgmlcAddressData(String hgmlcAddressData) {
        this.hgmlcAddressData = hgmlcAddressData;
    }

    public String getVgmlcAddressType() {
        return vgmlcAddressType;
    }

    public void setVgmlcAddressType(String vgmlcAddressType) {
        this.vgmlcAddressType = vgmlcAddressType;
    }

    public String getVgmlcAddressData() {
        return vgmlcAddressData;
    }

    public void setVgmlcAddressData(String vgmlcAddressData) {
        this.vgmlcAddressData = vgmlcAddressData;
    }

    public String getPprAddressType() {
        return pprAddressType;
    }

    public void setPprAddressType(String pprAddressType) {
        this.pprAddressType = pprAddressType;
    }

    public String getPprAddressData() {
        return pprAddressData;
    }

    public String getLcsCapabilitySets() {
        return lcsCapabilitySets;
    }

    public void setLcsCapabilitySets(String lcsCapabilitySets) {
        this.lcsCapabilitySets = lcsCapabilitySets;
    }

    public String getTgppAAAServerName() {
        return tgppAAAServerName;
    }

    public void setTgppAAAServerName(String tgppAAAServerName) {
        this.tgppAAAServerName = tgppAAAServerName;
    }

    public void setPprAddressData(String pprAddressData) {
        this.pprAddressData = pprAddressData;
    }

    public String getLocationEstimateTypeOfShape() {
        return locationEstimateTypeOfShape;
    }

    public void setLocationEstimateTypeOfShape(String locationEstimateTypeOfShape) {
        this.locationEstimateTypeOfShape = locationEstimateTypeOfShape;
    }

    public String getLocationEstimateLatitude() {
        return locationEstimateLatitude;
    }

    public void setLocationEstimateLatitude(String locationEstimateLatitude) {
        this.locationEstimateLatitude = locationEstimateLatitude;
    }

    public String getLocationEstimateLongitude() {
        return locationEstimateLongitude;
    }

    public void setLocationEstimateLongitude(String locationEstimateLongitude) {
        this.locationEstimateLongitude = locationEstimateLongitude;
    }

    public String getLocationEstimateUncertainty() {
        return locationEstimateUncertainty;
    }

    public void setLocationEstimateUncertainty(String locationEstimateUncertainty) {
        this.locationEstimateUncertainty = locationEstimateUncertainty;
    }

    public String getLocationEstimateUncertaintySemiMajorAxis() {
        return locationEstimateUncertaintySemiMajorAxis;
    }

    public void setLocationEstimateUncertaintySemiMajorAxis(String locationEstimateUncertaintySemiMajorAxis) {
        this.locationEstimateUncertaintySemiMajorAxis = locationEstimateUncertaintySemiMajorAxis;
    }

    public String getLocationEstimateUncertaintySemiMinorAxis() {
        return locationEstimateUncertaintySemiMinorAxis;
    }

    public void setLocationEstimateUncertaintySemiMinorAxis(String locationEstimateUncertaintySemiMinorAxis) {
        this.locationEstimateUncertaintySemiMinorAxis = locationEstimateUncertaintySemiMinorAxis;
    }

    public String getLocationEstimateConfidence() {
        return locationEstimateConfidence;
    }

    public void setLocationEstimateConfidence(String locationEstimateConfidence) {
        this.locationEstimateConfidence = locationEstimateConfidence;
    }

    public String getLocationEstimateAngleOfMajorAxis() {
        return locationEstimateAngleOfMajorAxis;
    }

    public void setLocationEstimateAngleOfMajorAxis(String locationEstimateAngleOfMajorAxis) {
        this.locationEstimateAngleOfMajorAxis = locationEstimateAngleOfMajorAxis;
    }

    public String getLocationEstimateAltitude() {
        return locationEstimateAltitude;
    }

    public void setLocationEstimateAltitude(String locationEstimateAltitude) {
        this.locationEstimateAltitude = locationEstimateAltitude;
    }

    public String getLocationEstimateUncertaintyAltitude() {
        return locationEstimateUncertaintyAltitude;
    }

    public void setLocationEstimateUncertaintyAltitude(String locationEstimateUncertaintyAltitude) {
        this.locationEstimateUncertaintyAltitude = locationEstimateUncertaintyAltitude;
    }

    public String getLocationEstimateInnerRadius() {
        return locationEstimateInnerRadius;
    }

    public void setLocationEstimateInnerRadius(String locationEstimateInnerRadius) {
        this.locationEstimateInnerRadius = locationEstimateInnerRadius;
    }

    public String getLocationEstimateUncertaintyRadius() {
        return locationEstimateUncertaintyRadius;
    }

    public void setLocationEstimateUncertaintyRadius(String locationEstimateUncertaintyRadius) {
        this.locationEstimateUncertaintyRadius = locationEstimateUncertaintyRadius;
    }

    public String getLocationEstimateOffSetAngle() {
        return locationEstimateOffSetAngle;
    }

    public void setLocationEstimateOffSetAngle(String locationEstimateOffSetAngle) {
        this.locationEstimateOffSetAngle = locationEstimateOffSetAngle;
    }

    public String getLocationEstimateIncludedAngle() {
        return locationEstimateIncludedAngle;
    }

    public void setLocationEstimateIncludedAngle(String locationEstimateIncludedAngle) {
        this.locationEstimateIncludedAngle = locationEstimateIncludedAngle;
    }

    public String getAdditionalLocationEstimateTypeOfShape() {
        return additionalLocationEstimateTypeOfShape;
    }

    public void setAdditionalLocationEstimateTypeOfShape(String additionalLocationEstimateTypeOfShape) {
        this.additionalLocationEstimateTypeOfShape = additionalLocationEstimateTypeOfShape;
    }

    public String getAdditionalLocationEstimatePolygonNumberOfPoint() {
        return additionalLocationEstimatePolygonNumberOfPoint;
    }

    public void setAdditionalLocationEstimatePolygonNumberOfPoint(String additionalLocationEstimatePolygonNumberOfPoint) {
        this.additionalLocationEstimatePolygonNumberOfPoint = additionalLocationEstimatePolygonNumberOfPoint;
    }

    public String getAdditionalLocationEstPolyListLatLongPoints() {
        return additionalLocationEstPolyListLatLongPoints;
    }

    public void setAdditionalLocationEstPolyListLatLongPoints(String additionalLocationEstPolyListLatLongPoints) {
        this.additionalLocationEstPolyListLatLongPoints = additionalLocationEstPolyListLatLongPoints;
    }

    public String getPolygonCentroidLatitude() {
        return polygonCentroidLatitude;
    }

    public void setPolygonCentroidLatitude(String polygonCentroidLatitude) {
        this.polygonCentroidLatitude = polygonCentroidLatitude;
    }

    public String getPolygonCentroidLongitude() {
        return polygonCentroidLongitude;
    }

    public void setPolygonCentroidLongitude(String polygonCentroidLongitude) {
        this.polygonCentroidLongitude = polygonCentroidLongitude;
    }

    public String getAdditionalLocationEstimateLatitude() {
        return additionalLocationEstimateLatitude;
    }

    public void setAdditionalLocationEstimateLatitude(String additionalLocationEstimateLatitude) {
        this.additionalLocationEstimateLatitude = additionalLocationEstimateLatitude;
    }

    public String getAdditionalLocationEstimateLongitude() {
        return additionalLocationEstimateLongitude;
    }

    public void setAdditionalLocationEstimateLongitude(String additionalLocationEstimateLongitude) {
        this.additionalLocationEstimateLongitude = additionalLocationEstimateLongitude;
    }

    public String getAdditionalLocationEstimateUncertainty() {
        return additionalLocationEstimateUncertainty;
    }

    public void setAdditionalLocationEstimateUncertainty(String additionalLocationEstimateUncertainty) {
        this.additionalLocationEstimateUncertainty = additionalLocationEstimateUncertainty;
    }

    public String getAdditionalLocationEstimateUncertaintySemiMajorAxis() {
        return additionalLocationEstimateUncertaintySemiMajorAxis;
    }

    public void setAdditionalLocationEstimateUncertaintySemiMajorAxis(String additionalLocationEstimateUncertaintySemiMajorAxis) {
        this.additionalLocationEstimateUncertaintySemiMajorAxis = additionalLocationEstimateUncertaintySemiMajorAxis;
    }

    public String getAdditionalLocationEstimateUncertaintySemiMinorAxis() {
        return additionalLocationEstimateUncertaintySemiMinorAxis;
    }

    public void setAdditionalLocationEstimateUncertaintySemiMinorAxis(String additionalLocationEstimateUncertaintySemiMinorAxis) {
        this.additionalLocationEstimateUncertaintySemiMinorAxis = additionalLocationEstimateUncertaintySemiMinorAxis;
    }

    public String getAdditionalLocationEstimateAngleOfMajorAxis() {
        return additionalLocationEstimateAngleOfMajorAxis;
    }

    public void setAdditionalLocationEstimateAngleOfMajorAxis(String additionalLocationEstimateAngleOfMajorAxis) {
        this.additionalLocationEstimateAngleOfMajorAxis = additionalLocationEstimateAngleOfMajorAxis;
    }

    public String getAdditionalLocationEstimateConfidence() {
        return additionalLocationEstimateConfidence;
    }

    public void setAdditionalLocationEstimateConfidence(String additionalLocationEstimateConfidence) {
        this.additionalLocationEstimateConfidence = additionalLocationEstimateConfidence;
    }

    public String getAdditionalLocationEstimateAltitude() {
        return additionalLocationEstimateAltitude;
    }

    public void setAdditionalLocationEstimateAltitude(String additionalLocationEstimateAltitude) {
        this.additionalLocationEstimateAltitude = additionalLocationEstimateAltitude;
    }

    public String getAdditionalLocationEstimateUncertaintyAltitude() {
        return additionalLocationEstimateUncertaintyAltitude;
    }

    public void setAdditionalLocationEstimateUncertaintyAltitude(String additionalLocationEstimateUncertaintyAltitude) {
        this.additionalLocationEstimateUncertaintyAltitude = additionalLocationEstimateUncertaintyAltitude;
    }

    public String getAdditionalLocationEstimateInnerRadius() {
        return additionalLocationEstimateInnerRadius;
    }

    public void setAdditionalLocationEstimateInnerRadius(String additionalLocationEstimateInnerRadius) {
        this.additionalLocationEstimateInnerRadius = additionalLocationEstimateInnerRadius;
    }

    public String getAdditionalLocationEstimateUncertaintyRadius() {
        return additionalLocationEstimateUncertaintyRadius;
    }

    public void setAdditionalLocationEstimateUncertaintyRadius(String additionalLocationEstimateUncertaintyRadius) {
        this.additionalLocationEstimateUncertaintyRadius = additionalLocationEstimateUncertaintyRadius;
    }

    public String getAdditionalLocationEstimateOffSetAngle() {
        return additionalLocationEstimateOffSetAngle;
    }

    public void setAdditionalLocationEstimateOffSetAngle(String additionalLocationEstimateOffSetAngle) {
        this.additionalLocationEstimateOffSetAngle = additionalLocationEstimateOffSetAngle;
    }

    public String getAdditionalLocationEstimateIncludedAngle() {
        return additionalLocationEstimateIncludedAngle;
    }

    public void setAdditionalLocationEstimateIncludedAngle(String additionalLocationEstimateIncludedAngle) {
        this.additionalLocationEstimateIncludedAngle = additionalLocationEstimateIncludedAngle;
    }

    public String getAgeOfLocationEstimate() {
        return ageOfLocationEstimate;
    }

    public void setAgeOfLocationEstimate(String ageOfLocationEstimate) {
        this.ageOfLocationEstimate = ageOfLocationEstimate;
    }

    public String getGeranPositioningDataInformation() {
        return geranPositioningDataInformation;
    }

    public void setGeranPositioningDataInformation(String geranPositioningDataInformation) {
        this.geranPositioningDataInformation = geranPositioningDataInformation;
    }

    public String getGeranGANSSPositioningDataInformation() {
        return geranGANSSPositioningDataInformation;
    }

    public void setGeranGANSSPositioningDataInformation(String geranGANSSPositioningDataInformation) {
        this.geranGANSSPositioningDataInformation = geranGANSSPositioningDataInformation;
    }

    public String getUtranPositioningDataInfo() {
        return utranPositioningDataInfo;
    }

    public void setUtranPositioningDataInfo(String utranPositioningDataInfo) {
        this.utranPositioningDataInfo = utranPositioningDataInfo;
    }

    public String getUtranGANSSPositioningData() {
        return utranGANSSPositioningData;
    }

    public void setUtranGANSSPositioningData(String utranGANSSPositioningData) {
        this.utranGANSSPositioningData = utranGANSSPositioningData;
    }

    public String getUtranAdditionalPositioningDataInfo() {
        return utranAdditionalPositioningDataInfo;
    }

    public void setUtranAdditionalPositioningDataInfo(String utranAdditionalPositioningDataInfo) {
        this.utranAdditionalPositioningDataInfo = utranAdditionalPositioningDataInfo;
    }

    public String getEutranPositioningDataInfo() {
        return eutranPositioningDataInfo;
    }

    public void setEutranPositioningDataInfo(String eutranPositioningDataInfo) {
        this.eutranPositioningDataInfo = eutranPositioningDataInfo;
    }

    public String getDeferredMTLRResponseIndicator() {
        return deferredMTLRResponseIndicator;
    }

    public void setDeferredMTLRResponseIndicator(String deferredMTLRResponseIndicator) {
        this.deferredMTLRResponseIndicator = deferredMTLRResponseIndicator;
    }

    public String getLcsCGIorSAIorLAIMCC() {
        return lcsCGIorSAIorLAIMCC;
    }

    public void setLcsCGIorSAIorLAIMCC(String lcsCGIorSAIorLAIMCC) {
        this.lcsCGIorSAIorLAIMCC = lcsCGIorSAIorLAIMCC;
    }

    public String getLcsCGIorSAIorLAIMNC() {
        return lcsCGIorSAIorLAIMNC;
    }

    public void setLcsCGIorSAIorLAIMNC(String lcsCGIorSAIorLAIMNC) {
        this.lcsCGIorSAIorLAIMNC = lcsCGIorSAIorLAIMNC;
    }

    public String getLcsCGIorSAIorLAILAC() {
        return lcsCGIorSAIorLAILAC;
    }

    public void setLcsCGIorSAIorLAILAC(String lcsCGIorSAIorLAILAC) {
        this.lcsCGIorSAIorLAILAC = lcsCGIorSAIorLAILAC;
    }

    public String getLcsCGIorSAIorLAICI() {
        return lcsCGIorSAIorLAICI;
    }

    public void setLcsCGIorSAIorLAICI(String lcsCGIorSAIorLAICI) {
        this.lcsCGIorSAIorLAICI = lcsCGIorSAIorLAICI;
    }

    public String getLcsEutranCgiMCC() {
        return lcsEutranCgiMCC;
    }

    public void setLcsEutranCgiMCC(String lcsEutranCgiMCC) {
        this.lcsEutranCgiMCC = lcsEutranCgiMCC;
    }

    public String getLcsEutranCgiMNC() {
        return lcsEutranCgiMNC;
    }

    public void setLcsEutranCgiMNC(String lcsEutranCgiMNC) {
        this.lcsEutranCgiMNC = lcsEutranCgiMNC;
    }

    public String getLcsEutranCgiECI() {
        return lcsEutranCgiECI;
    }

    public void setLcsEutranCgiECI(String lcsEutranCgiECI) {
        this.lcsEutranCgiECI = lcsEutranCgiECI;
    }

    public String getLcsEutranCgiENBID() {
        return lcsEutranCgiENBID;
    }

    public void setLcsEutranCgiENBID(String lcsEutranCgiENBID) {
        this.lcsEutranCgiENBID = lcsEutranCgiENBID;
    }

    public String getLcsEutranCgiCI() {
        return lcsEutranCgiCI;
    }

    public void setLcsEutranCgiCI(String lcsEutranCgiCI) {
        this.lcsEutranCgiCI = lcsEutranCgiCI;
    }

    public String getLcsCellPortionId() {
        return lcsCellPortionId;
    }

    public void setLcsCellPortionId(String lcsCellPortionId) {
        this.lcsCellPortionId = lcsCellPortionId;
    }

    public String getPseudonymIndicator() {
        return pseudonymIndicator;
    }

    public void setPseudonymIndicator(String pseudonymIndicator) {
        this.pseudonymIndicator = pseudonymIndicator;
    }

    public String getAccuracyFulfilmentIndicator() {
        return accuracyFulfilmentIndicator;
    }

    public void setAccuracyFulfilmentIndicator(String accuracyFulfilmentIndicator) {
        this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getHorizontalVelocityEstimate() {
        return horizontalVelocityEstimate;
    }

    public void setHorizontalVelocityEstimate(String horizontalVelocityEstimate) {
        this.horizontalVelocityEstimate = horizontalVelocityEstimate;
    }

    public String getVelocityEstimateBearing() {
        return velocityEstimateBearing;
    }

    public void setVelocityEstimateBearing(String velocityEstimateBearing) {
        this.velocityEstimateBearing = velocityEstimateBearing;
    }

    public String getVerticalVelocityEstimate() {
        return verticalVelocityEstimate;
    }

    public void setVerticalVelocityEstimate(String verticalVelocityEstimate) {
        this.verticalVelocityEstimate = verticalVelocityEstimate;
    }

    public String getVelocityHorizontalUncertainty() {
        return velocityHorizontalUncertainty;
    }

    public void setVelocityHorizontalUncertainty(String velocityHorizontalUncertainty) {
        this.velocityHorizontalUncertainty = velocityHorizontalUncertainty;
    }

    public String getVelocityVerticalUncertainty() {
        return velocityVerticalUncertainty;
    }

    public void setVelocityVerticalUncertainty(String velocityVerticalUncertainty) {
        this.velocityVerticalUncertainty = velocityVerticalUncertainty;
    }

    public String getVelocityType() {
        return velocityType;
    }

    public void setVelocityType(String velocityType) {
        this.velocityType = velocityType;
    }

    public String getServingNodeAddressMSCNumber() {
        return servingNodeAddressMSCNumber;
    }

    public void setServingNodeAddressMSCNumber(String servingNodeAddressMSCNumber) {
        this.servingNodeAddressMSCNumber = servingNodeAddressMSCNumber;
    }

    public String getServingNodeAddressSGSNNumber() {
        return servingNodeAddressSGSNNumber;
    }

    public void setServingNodeAddressSGSNNumber(String servingNodeAddressSGSNNumber) {
        this.servingNodeAddressSGSNNumber = servingNodeAddressSGSNNumber;
    }

    public String getServingNodeAddressMMENumber() {
        return servingNodeAddressMMENumber;
    }

    public void setServingNodeAddressMMENumber(String servingNodeAddressMMENumber) {
        this.servingNodeAddressMMENumber = servingNodeAddressMMENumber;
    }

    public String getLcsClientType() {
        return lcsClientType;
    }

    public void setLcsClientType(String lcsClientType) {
        this.lcsClientType = lcsClientType;
    }

    public String getLcsClientName() {
        return lcsClientName;
    }

    public void setLcsClientName(String lcsClientName) {
        this.lcsClientName = lcsClientName;
    }

    public String getLcsClientDCS() {
        return lcsClientDCS;
    }

    public void setLcsClientDCS(String lcsClientDCS) {
        this.lcsClientDCS = lcsClientDCS;
    }

    public String getLcsClientFI() {
        return lcsClientFI;
    }

    public void setLcsClientFI(String lcsClientFI) {
        this.lcsClientFI = lcsClientFI;
    }

    public String getLcsClientAPN() {
        return lcsClientAPN;
    }

    public void setLcsClientAPN(String lcsClientAPN) {
        this.lcsClientAPN = lcsClientAPN;
    }

    public String getLcsClientDialedByMS() {
        return lcsClientDialedByMS;
    }

    public void setLcsClientDialedByMS(String lcsClientDialedByMS) {
        this.lcsClientDialedByMS = lcsClientDialedByMS;
    }

    public String getLcsClientExternalID() {
        return lcsClientExternalID;
    }

    public void setLcsClientExternalID(String lcsClientExternalID) {
        this.lcsClientExternalID = lcsClientExternalID;
    }

    public String getLcsClientInternalID() {
        return lcsClientInternalID;
    }

    public void setLcsClientInternalID(String lcsClientInternalID) {
        this.lcsClientInternalID = lcsClientInternalID;
    }

    public String getLcsClientRequestorDCS() {
        return lcsClientRequestorDCS;
    }

    public void setLcsClientRequestorDCS(String lcsClientRequestorDCS) {
        this.lcsClientRequestorDCS = lcsClientRequestorDCS;
    }

    public String getLcsClientRequestorFI() {
        return lcsClientRequestorFI;
    }

    public void setLcsClientRequestorFI(String lcsClientRequestorFI) {
        this.lcsClientRequestorFI = lcsClientRequestorFI;
    }

    public String getLcsClientRequestorString() {
        return lcsClientRequestorString;
    }

    public void setLcsClientRequestorString(String lcsClientRequestorString) {
        this.lcsClientRequestorString = lcsClientRequestorString;
    }

    public String getLcsQoSHorizontalAccuracy() {
        return lcsQoSHorizontalAccuracy;
    }

    public void setLcsQoSHorizontalAccuracy(String lcsQoSHorizontalAccuracy) {
        this.lcsQoSHorizontalAccuracy = lcsQoSHorizontalAccuracy;
    }

    public String getLcsQoSverticalAccuracy() {
        return lcsQoSverticalAccuracy;
    }

    public void setLcsQoSverticalAccuracy(String lcsQoSverticalAccuracy) {
        this.lcsQoSverticalAccuracy = lcsQoSverticalAccuracy;
    }

    public String getLcsQoSVerticalCoordinateRequest() {
        return lcsQoSVerticalCoordinateRequest;
    }

    public void setLcsQoSVerticalCoordinateRequest(String lcsQoSVerticalCoordinateRequest) {
        this.lcsQoSVerticalCoordinateRequest = lcsQoSVerticalCoordinateRequest;
    }

    public String getLcsQoSResponseTime() {
        return lcsQoSResponseTime;
    }

    public void setLcsQoSResponseTime(String lcsQoSResponseTime) {
        this.lcsQoSResponseTime = lcsQoSResponseTime;
    }

    public String getLcsQoSClass() {
        return lcsQoSClass;
    }

    public void setLcsQoSClass(String lcsQoSClass) {
        this.lcsQoSClass = lcsQoSClass;
    }

    public String getClientReferenceNumber() {
        return clientReferenceNumber;
    }

    public void setClientReferenceNumber(String clientReferenceNumber) {
        this.clientReferenceNumber = clientReferenceNumber;
    }

    public String getLcsReferenceNumber() {
        return lcsReferenceNumber;
    }

    public void setLcsReferenceNumber(String lcsReferenceNumber) {
        this.lcsReferenceNumber = lcsReferenceNumber;
    }

    public String getLcsServiceTypeID() {
        return lcsServiceTypeID;
    }

    public void setLcsServiceTypeID(String lcsServiceTypeID) {
        this.lcsServiceTypeID = lcsServiceTypeID;
    }

    public String getBarometricPressureMeasurement() {
        return barometricPressureMeasurement;
    }

    public void setBarometricPressureMeasurement(String barometricPressureMeasurement) {
        this.barometricPressureMeasurement = barometricPressureMeasurement;
    }

    public String getCivicAddress() {
        return civicAddress;
    }

    public void setCivicAddress(String civicAddress) {
        this.civicAddress = civicAddress;
    }

    public String getLcsEvent() {
        return lcsEvent;
    }

    public void setLcsEvent(String lcsEvent) {
        this.lcsEvent = lcsEvent;
    }

    public String getMsisdnAddress() {
        return msisdnAddress;
    }

    public void setMsisdnAddress(String msisdnAddress) {
        this.msisdnAddress = msisdnAddress;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDeferredLocationEventType() {
        return deferredLocationEventType;
    }

    public void setDeferredLocationEventType(String deferredLocationEventType) {
        this.deferredLocationEventType = deferredLocationEventType;
    }

    public String getDeferredLcsLocationInfoNetworkNodeNumber() {
        return deferredLcsLocationInfoNetworkNodeNumber;
    }

    public void setDeferredLcsLocationInfoNetworkNodeNumber(String deferredLcsLocationInfoNetworkNodeNumber) {
        this.deferredLcsLocationInfoNetworkNodeNumber = deferredLcsLocationInfoNetworkNodeNumber;
    }

    public String getDeferredLcsLocationInfoGprsNodeIndicator() {
        return deferredLcsLocationInfoGprsNodeIndicator;
    }

    public void setDeferredLcsLocationInfoGprsNodeIndicator(String deferredLcsLocationInfoGprsNodeIndicator) {
        this.deferredLcsLocationInfoGprsNodeIndicator = deferredLcsLocationInfoGprsNodeIndicator;
    }

    public String getDeferredLcsLocationInfoAdditionalNumber() {
        return deferredLcsLocationInfoAdditionalNumber;
    }

    public void setDeferredLcsLocationInfoAdditionalNumber(String deferredLcsLocationInfoAdditionalNumber) {
        this.deferredLcsLocationInfoAdditionalNumber = deferredLcsLocationInfoAdditionalNumber;
    }

    public String getDeferredLcsLocationInfoLMSI() {
        return deferredLcsLocationInfoLMSI;
    }

    public void setDeferredLcsLocationInfoLMSI(String deferredLcsLocationInfoLMSI) {
        this.deferredLcsLocationInfoLMSI = deferredLcsLocationInfoLMSI;
    }

    public String getDeferredLcsLocationInfoMmeName() {
        return deferredLcsLocationInfoMmeName;
    }

    public void setDeferredLcsLocationInfoMmeName(String deferredLcsLocationInfoMmeName) {
        this.deferredLcsLocationInfoMmeName = deferredLcsLocationInfoMmeName;
    }

    public String getDeferredLcsLocationInfoAaaServerName() {
        return deferredLcsLocationInfoAaaServerName;
    }

    public void setDeferredLcsLocationInfoAaaServerName(String deferredLcsLocationInfoAaaServerName) {
        this.deferredLcsLocationInfoAaaServerName = deferredLcsLocationInfoAaaServerName;
    }

    public String getDeferredLcsCsR98_99() {
        return deferredLcsCsR98_99;
    }

    public void setDeferredLcsCsR98_99(String deferredLcsCsR98_99) {
        this.deferredLcsCsR98_99 = deferredLcsCsR98_99;
    }

    public String getDeferredLcsCsR4() {
        return deferredLcsCsR4;
    }

    public void setDeferredLcsCsR4(String deferredLcsCsR4) {
        this.deferredLcsCsR4 = deferredLcsCsR4;
    }

    public String getDeferredLcsCsR5() {
        return deferredLcsCsR5;
    }

    public void setDeferredLcsCsR5(String deferredLcsCsR5) {
        this.deferredLcsCsR5 = deferredLcsCsR5;
    }

    public String getDeferredLcsCsR6() {
        return deferredLcsCsR6;
    }

    public void setDeferredLcsCsR6(String deferredLcsCsR6) {
        this.deferredLcsCsR6 = deferredLcsCsR6;
    }

    public String getDeferredLcsCsR7() {
        return deferredLcsCsR7;
    }

    public void setDeferredLcsCsR7(String deferredLcsCsR7) {
        this.deferredLcsCsR7 = deferredLcsCsR7;
    }

    public String getDeferredALcsCsR98_99() {
        return deferredALcsCsR98_99;
    }

    public void setDeferredALcsCsR98_99(String deferredALcsCsR98_99) {
        this.deferredALcsCsR98_99 = deferredALcsCsR98_99;
    }

    public String getDeferredALcsCsR4() {
        return deferredALcsCsR4;
    }

    public void setDeferredALcsCsR4(String deferredALcsCsR4) {
        this.deferredALcsCsR4 = deferredALcsCsR4;
    }

    public String getDeferredALcsCsR5() {
        return deferredALcsCsR5;
    }

    public void setDeferredALcsCsR5(String deferredALcsCsR5) {
        this.deferredALcsCsR5 = deferredALcsCsR5;
    }

    public String getDeferredALcsCsR6() {
        return deferredALcsCsR6;
    }

    public void setDeferredALcsCsR6(String deferredALcsCsR6) {
        this.deferredALcsCsR6 = deferredALcsCsR6;
    }

    public String getDeferredALcsCsR7() {
        return deferredALcsCsR7;
    }

    public void setDeferredALcsCsR7(String deferredALcsCsR7) {
        this.deferredALcsCsR7 = deferredALcsCsR7;
    }

    public String getDeferredTerminationCause() {
        return deferredTerminationCause;
    }

    public void setDeferredTerminationCause(String deferredTerminationCause) {
        this.deferredTerminationCause = deferredTerminationCause;
    }

    public String getPeriodicReportingAmount() {
        return periodicReportingAmount;
    }

    public void setPeriodicReportingAmount(String periodicReportingAmount) {
        this.periodicReportingAmount = periodicReportingAmount;
    }

    public String getPeriodicReportingInterval() {
        return periodicReportingInterval;
    }

    public void setPeriodicReportingInterval(String periodicReportingInterval) {
        this.periodicReportingInterval = periodicReportingInterval;
    }

    public String getMoLrShortCircuitIndicator() {
        return moLrShortCircuitIndicator;
    }

    public void setMoLrShortCircuitIndicator(String moLrShortCircuitIndicator) {
        this.moLrShortCircuitIndicator = moLrShortCircuitIndicator;
    }

    public String getReportingPLMNList() {
        return reportingPLMNList;
    }

    public void setReportingPLMNList(String reportingPLMNList) {
        this.reportingPLMNList = reportingPLMNList;
    }

    public String getOneXRTTRCID() {
        return oneXRTTRCID;
    }

    public void setOneXRTTRCID(String oneXRTTRCID) {
        this.oneXRTTRCID = oneXRTTRCID;
    }

    public String getNaEsrkRequest() {
        return naEsrkRequest;
    }

    public void setNaEsrkRequest(String naEsrkRequest) {
        this.naEsrkRequest = naEsrkRequest;
    }

    public String getNaESRD() {
        return naESRD;
    }

    public void setNaESRD(String naESRD) {
        this.naESRD = naESRD;
    }

    public String getNaESRK() {
        return naESRK;
    }

    public void setNaESRK(String naESRK) {
        this.naESRK = naESRK;
    }

    public String getAmfInstanceId() {
        return amfInstanceId;
    }

    public void setAmfInstanceId(String amfInstanceId) {
        this.amfInstanceId = amfInstanceId;
    }

    public Long getRiaFlags() {
        return riaFlags;
    }

    public void setRiaFlags(Long riaFlags) {
        this.riaFlags = riaFlags;
    }

    public Long getPlrFlags() {
        return plrFlags;
    }

    public void setPlrFlags(Long plrFlags) {
        this.plrFlags = plrFlags;
    }

    public Long getPlaFlags() {
        return plaFlags;
    }

    public void setPlaFlags(Long plaFlags) {
        this.plaFlags = plaFlags;
    }

    public Long getLrrFlags() {
        return lrrFlags;
    }

    public void setLrrFlags(Long lrrFlags) {
        this.lrrFlags = lrrFlags;
    }

    public Long getLraFlags() {
        return lraFlags;
    }

    public void setLraFlags(Long lraFlags) {
        this.lraFlags = lraFlags;
    }
}
