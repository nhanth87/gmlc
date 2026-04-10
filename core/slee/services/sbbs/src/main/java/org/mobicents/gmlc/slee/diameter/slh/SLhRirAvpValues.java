package org.mobicents.gmlc.slee.diameter.slh;

import net.java.slee.resource.diameter.base.events.avp.DiameterAvp;
import net.java.slee.resource.diameter.slg.events.avp.ReportingPLMNListAvp;
import net.java.slee.resource.diameter.slh.SLhAVPFactory;
// import net.java.slee.resource.diameter.slh.events.avp.LCSRoutingInfoAVPCodes;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLhRirAvpValues implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final int DIAMETER_SLh_VENDOR_ID = 10415;

  /*
    3GPP TS 29.173 v15.0.0 reference
      6.2.3	LCS-Routing-Info-Request (RIR) Command
      The LCS-Routing-Info-Request (RIR) command, indicated by the Command-Code field set to 8388622
      and the "R" bit set in the Command Flags field, is sent from GMLC to HSS.

      Message Format:

      < LCS-Routing-Info-Request> ::=	< Diameter Header: 8388622, REQ, PXY, 16777291 >

	    < Session-Id >
	    [ Vendor-Specific-Application-Id ]
	    { Auth-Session-State }
	    { Origin-Host }
	    { Origin-Realm }
	    [ Destination-Host ]
	    { Destination-Realm }
	    [ User-Name ]
	    [ MSISDN ]
	    [ GMLC-Number ]
	    *[ Supported-Features ]
	    *[ Proxy-Info ]
	    *[ Route-Record ]
	    *[ AVP ]

  */

  private String userName; // IMSI
  private byte[] msisdn;
  private byte[] gmlcNumber;

  public SLhRirAvpValues() {
    super();
  }

  public SLhRirAvpValues(String userName, byte[] msisdn, byte[] gmlcNumber) {
    super();
    this.userName = userName;
    this.msisdn = msisdn;
    this.gmlcNumber = gmlcNumber;
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

  public byte[] getGmlcNumber() {
    return gmlcNumber;
  }

  public void setGmlcNumber(byte[] gmlcNumber) {
    this.gmlcNumber = gmlcNumber;
  }

  /** RIR AVPs building method **/
  public void createRIRAvps(SLhAVPFactory slhAVPFactory) {

    try {

      // AVP name="User-Name" code="1" mandatory="must" protected="may" may-encrypt="yes" vendor-bit="mustnot" type-name="UTF8String"
      DiameterAvp userNameAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, 1, this.userName);
      setUserName(userNameAvp.stringValue());

      // AVP name="MSISDN" code="701" vendor-id="TGPP" mandatory="must" protected="may" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp msisdnAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, 701, this.msisdn);
      setMsisdn(msisdnAvp.byteArrayValue());

      // AVP name="GMLC-Number" code="1474" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp gmlcNumberAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, 1474, this.gmlcNumber);
      setGmlcNumber(gmlcNumberAvp.byteArrayValue());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * PUBLIC SECTION REQUEST VARIABLES */
  public String plrUserName, plrMsisdn, plrImei, plrLcsNameString, plrLcsRequestorIdString, plrLcsCodeword, plrServiceSelection,
      plrAreaIdentification, plrAdditionalAreaIdentification, plrVisitedPLMNIdList, lrrCallbackUrl;

  public Long plrLcsPriority, plrHorizontalAccuracy, plrVerticalAccuracy, plrLcsSupportedGadShapes, plrLcsServiceTypeId, plrDeferredLocationType,
      plrFlags, plrAreaType, plrAdditionalAreaType, plrAreaEventIntervalTime, plrAreaEventSamplingInterval, plrAreaEventMaxInterval,
      plrAreaEventReportingDuration, plrAreaEventRepLocRequirements, plrPeriodicLDRReportingAmount, plrPeriodicLDRReportingInterval,
      plrMotionEventLinearDistance, plrMotionEventIntervalTime, plrMotionEventMaximumInterval, plrMotionEventSamplingInterval,
      plrMotionEvenReportingDuration, plrMotionEvenReportingLocationRequirements;

  public Integer plrSlgLocationType, plrLcsFormatInd, plrLcsClientType, plrLcsRequestorFormatIndicator, plrLcsReferenceNumber, plrQoSClass,
      plrVerticalRequested, plrResponseTime, plrVelocityRequested, plrPrivacyCheckNonSession, plrPrivacyCheckSession, plrAreaEventOccurrenceInfo,
      plrPeriodicLocationSupportIndicator, plrPrioritizedListIndicator, plrMotionEventOccurrenceInfo;

  public ReportingPLMNListAvp reportingPLMNListAvp;

}
