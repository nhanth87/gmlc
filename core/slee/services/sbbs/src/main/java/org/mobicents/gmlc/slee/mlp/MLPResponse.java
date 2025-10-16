package org.mobicents.gmlc.slee.mlp;

import org.mobicents.gmlc.slee.mlp.v3_4.Cgi;
import org.mobicents.gmlc.slee.mlp.v3_4.CircularArcArea;
import org.mobicents.gmlc.slee.mlp.v3_4.CircularArea;
import org.mobicents.gmlc.slee.mlp.v3_4.Coord;
import org.mobicents.gmlc.slee.mlp.v3_4.EllipticalArea;
import org.mobicents.gmlc.slee.mlp.v3_4.GsmNetParam;
import org.mobicents.gmlc.slee.mlp.v3_4.LinearRing;
import org.mobicents.gmlc.slee.mlp.v3_4.Msid;
import org.mobicents.gmlc.slee.mlp.v3_4.Neid;
import org.mobicents.gmlc.slee.mlp.v3_4.OuterBoundaryIs;
import org.mobicents.gmlc.slee.mlp.v3_4.Pd;
import org.mobicents.gmlc.slee.mlp.v3_4.Point;
import org.mobicents.gmlc.slee.mlp.v3_4.Polygon;
import org.mobicents.gmlc.slee.mlp.v3_4.Pos;
import org.mobicents.gmlc.slee.mlp.v3_4.Poserr;
import org.mobicents.gmlc.slee.mlp.v3_4.Result;
import org.mobicents.gmlc.slee.mlp.v3_4.Sai;
import org.mobicents.gmlc.slee.mlp.v3_4.ServingCell;
import org.mobicents.gmlc.slee.mlp.v3_4.Shape;
import org.mobicents.gmlc.slee.mlp.v3_4.Slia;
import org.mobicents.gmlc.slee.mlp.v3_4.Slrep;
import org.mobicents.gmlc.slee.mlp.v3_4.SvcResult;
import org.mobicents.gmlc.slee.mlp.v3_4.Time;
import org.mobicents.gmlc.slee.mlp.v3_4.Tlra;
import org.mobicents.gmlc.slee.mlp.v3_4.Tlrep;
import org.mobicents.gmlc.slee.mlp.v3_4.TrlPos;
import org.mobicents.gmlc.slee.mlp.v3_4.Vlrid;
import org.mobicents.gmlc.slee.mlp.v3_4.Vmscid;

import javax.slee.facilities.Tracer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is a helper for generating MLP XML responses to send to an MLP client.
 * It uses the JAXB generated XML bound classes in org.oma.protocols.mlp
 * It exists to generate consistent XML output using the JAXB marshaller
 * It supports generating a result only for a single MSISDN that has a single Point (X/Y - lat/lon) result
 *
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 * @author <a href="mailto:eross@locatrix.com"> Andrew Eross </a>
 */
public class MLPResponse {

  public enum MLPResultType {
    OK,
    SYSTEM_FAILURE,
    UNSPECIFIED_ERROR,
    UNAUTHORIZED_APPLICATION,
    UNKNOWN_SUBSCRIBER,
    ABSENT_SUBSCRIBER,
    POSITION_METHOD_FAILURE,
    TIMEOUT,
    CONGESTION_IN_LOCATION_SERVER,
    UNSUPPORTED_VERSION,
    TOO_MANY_POSITION_ITEMS,
    FORMAT_ERROR,
    SYNTAX_ERROR,
    PROTOCOL_ELEMENT_NOT_SUPPORTED,
    SERVICE_NOT_SUPPORTED,
    PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED,
    INVALID_PROTOCOL_ELEMENT_VALUE,
    INVALID_PROTOCOL_ELEMENT_ATTRIBUTE_VALUE,
    PROTOCOL_ELEMENT_VALUE_NOT_SUPPORTED,
    PROTOCOL_ELEMENT_ATTRIBUTE_VALUE_NOT_SUPPORTED,
    CANCELLATION_OF_TRIGGERED_LOCATION_REQUEST,
    INVALID_MSID_IN_TLRSR,
    TLRSR_FOR_INDIVIDUAL_TARGET_NOT_SUPPORTED,
    QOP_NOT_ATTAINABLE,
    POSITIONING_NOT_ALLOWED,
    CONGESTION_IN_MOBILE_NETWORK,
    DISALLOWED_BY_LOCAL_REGULATIONS,
    MISCONFIGURATION_OF_LOCATION_SERVER,
    TARGET_MOVED_TO_NEW_MSC_SGSN,
    STANDARD_LOCATION_REPORT_SERVICE_NOT_SUPPORTED,
    MLS_CLIENT_ERROR,
    STANDARD_LOCATION_REPORT_SERVICE_NOT_ACCEPTED,
    SUBSCRIBER_IN_STANDARD_LOCATION_REPORT_SERVICE_NOT_VALID,
    INVALID_SERVICE_ID_IN_STANDARD_LOCATION_REPORT_SERVICE
  }

  /**
   * Logger from the calling SBB
   */
  private final Tracer logger;

  protected static final DecimalFormat coordinatesFormat = new DecimalFormat("#0.000000");
  private String exceptionError = "";

  public MLPResponse(Tracer logger) {
    this.logger = logger;
  }

  // If there's an internal exception or other error, we have to fall back to some "worst case scenario"
  // static XML return data
  private final String genericStandardLocationRequestErrorXML =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
          "  <!DOCTYPE svc_result SYSTEM \"MLP_v3_4.dtd\">" +
          "  <svc_result xmlns=\"MLP_v3_4.dtd\" ver=\"3.4.0\">\n" +
          "  <slia ver=\"3.4.0\">\n" +
          "    <result resid=\"1\">SYSTEM FAILURE</result>\n" +
          "    <add_info>" + exceptionError + "</add_info>\n" +
          "  </slia>\n" +
          "</svc_result>";

  private final String genericTriggeredLocationRequestErrorXML =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
          "  <!DOCTYPE svc_result SYSTEM \"MLP_v3_4.dtd\">" +
          "  <svc_result xmlns=\"MLP_v3_4.dtd\" ver=\"3.4.0\">\n" +
          "  <tlra ver=\"3.4.0\">\n" +
          "    <result resid=\"1\">SYSTEM FAILURE</result>\n" +
          "    <add_info>" + exceptionError + "</add_info>\n" +
          "  </tlra>\n" +
          "</svc_result>";

  /**
   * Get the MLP result code as per OMA MLP section 5.4.1
   *
   * @param mlpResultType MLPResultType internal enum value for use in the MLP classes
   * @return MLP result code to return to the client
   */
  public static String getResultCodeForType(MLPResultType mlpResultType) {

    /*
    0 - 99 Location server specific errors
    0	OK	                        No error occurred while processing the request.
    1	SYSTEM FAILURE	            The request can not be handled because of a general problem in the location server.
    2	UNSPECIFIED ERROR	        An unspecified error used in case none of the other errors apply. This can also be used in case privacy issues prevent certain errors from being presented.
    3	UNAUTHORIZED APPLICATION	The requesting location-based application is not allowed to access the location server or a wrong password has been supplied.
    4	UNKNOWN SUBSCRIBER	        The user is unknown, i.e. no such subscription exists.
    5	ABSENT SUBSCRIBER	        The user is currently not reachable.
    6	POSITION METHOD FAILURE	    The location service fails to obtain the user's position via the positioning method used.
                                    The exact cause may be indicated in ADD_INFO by the inclusion of an event code (A, B, C etc.)
                                    from the list below:
                                    A: Target does not support SUPL.
                                    B: SUPL Positioning Failure - Target does not support requested service. For example a SUPL 1.0 device doesn't support periodic trigger service.
                                    C: SUPL Positioning Failure - Target fails to deliver Cell Info.
                                    D: SUPL Positioning Failure - both Cell ID location and GNSS positioning fail.
    7	TIMEOUT	                    Timer expiry for the requested event trigger

    100 - 199 Request specific errors
    101	CONGESTION IN LOCATION SERVER	The request can not be handled due to congestion in the location server.
    103	UNSUPPORTED VERSION	            The Location server does not support the indicated protocol version.
    104	TOO MANY POSITION ITEMS	        Too many position items have been specified in the request.
    105	FORMAT ERROR	                A protocol element in the request has invalid format. The invalid element is indicated in ADD_INFO.
    106	SYNTAX ERROR	                The position request has invalid syntax. Details may be indicated in ADD_INFO.
    107	PROTOCOL ELEMENT NOT SUPPORTED	A protocol element specified in the position request is not supported by the Location Server, or the position result is not supported by the LCS Client. The element is indicated in ADD_INFO.
    108	SERVICE NOT SUPPORTED	        The requested service is not supported in the Location Server. The service is indicated in ADD_INFO.
    109	PROTOCOL ELEMENT ATTRIBUTE NOT SUPPORTED	A protocol element attribute is not supported in the Location Server. The attribute is indicated in ADD_INFO.
    110	INVALID PROTOCOL ELEMENT VALUE	            A protocol element in the request has an invalid value. The element is indicated in ADD_INFO.
    111	INVALID PROTOCOL ELEMENT ATTRIBUTE VALUE	A protocol element attribute in the request has a wrong value. The element is indicated in ADD_INFO.
    112	PROTOCOL ELEMENT VALUE NOT SUPPORTED	    A specific value of a protocol element is not supported in the Location Server. The element and value are indicated in ADD_INFO.
    113	PROTOCOL ELEMENT ATTRIBUTE VALUE NOT SUPPORTED	A specific value of a protocol element attribute is not supported in the Location Server. The attribute and value are indicated in ADD_INFO.
    114	CANCELLATION OF TRIGGERED LOCATION REQUEST	The requested triggered location report is cancelled.
    115	INVALID MSID IN TLRSR	                    One or more msid elements in the Triggered Location Reporting Stop Request are not valid to the Location Server.
    116	TLRSR FOR INDIVIDUAL TARGET NOT SUPPORTED	The function of stopping triggered location reporting for individual target(s) is not supported in Location Server.

    200 - 299 Network specific errors
    201	QOP NOT ATTAINABLE	            The requested QoP cannot be provided. The exact QoP parameter which cannot be provided, i.e. accuracy, response time or max_loc_age, and value are indicated in ADD_INFO.
    202	POSITIONING NOT ALLOWED	        The subscriber does not allow the application to position him/her for whatever reason (privacy settings in location server, LCS privacy class).
    203	CONGESTION IN MOBILE NETWORK	The request can not be handled due to congestion in the mobile network.
    204	DISALLOWED BY LOCAL REGULATIONS	The location request is disallowed by local regulatory requirements.
    207	MISCONFIGURATION OF LOCATION SERVER	The location server is not completely configured to be able to calculate a position.
    208	TARGET MOVED TO NEW MSC/SGSN	The triggered Location Request has been aborted due to that target has moved to another MSC/SGSN. This result code shall only be used towards The Home Location Server. Restrictions:
                                        - This code SHALL only be used in RLP.
                                        - This result code shall only be used towards The Home Location Server.

    300 - 499 Reserved for future use

    500 - 599 Vendor specific errors

    600 - 699 MLS Client specific errors
    601	STANDARD LOCATION REPORT SERVICE NOT SUPPORTED	The MLS Client does not support the standard location report service.
    602	MLS CLIENT ERROR	                            An error occurred in the MLS Client.
    603	STANDARD LOCATION REPORT SERVICE NOT ACCEPTED	The standard location report was not accepted by the MLS Client.
    604	SUBSCRIBER IN STANDARD LOCATION REPORT SERVICE NOT VALID  The subscriber in the Standard Location Report is not valid to the MLS Client.
    605	INVALID SERVICE ID IN STANDARD LOCATION REPORT SERVICE	  The service identity in the Standard Location Report is not valid to the MLS Client.
     */

    switch (mlpResultType) {
      case OK:
        return "0";
      case UNSPECIFIED_ERROR:
        return "2";
      case UNAUTHORIZED_APPLICATION:
        return "3";
      case UNKNOWN_SUBSCRIBER:
        return "4";
      case ABSENT_SUBSCRIBER:
        return "5";
      case POSITION_METHOD_FAILURE:
        return "6";
      case TIMEOUT:
        return "7";
      case CONGESTION_IN_LOCATION_SERVER:
        return "101";
      case UNSUPPORTED_VERSION:
        return "103";
      case TOO_MANY_POSITION_ITEMS:
        return "104";
      case FORMAT_ERROR:
        return "105";
      case SYNTAX_ERROR:
        return "106";
      case PROTOCOL_ELEMENT_NOT_SUPPORTED:
        return "107";
      case SERVICE_NOT_SUPPORTED:
        return "108";
      case PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED:
        return "109";
      case INVALID_PROTOCOL_ELEMENT_VALUE:
        return "110";
      case INVALID_PROTOCOL_ELEMENT_ATTRIBUTE_VALUE:
        return "111";
      case PROTOCOL_ELEMENT_VALUE_NOT_SUPPORTED:
        return "112";
      case PROTOCOL_ELEMENT_ATTRIBUTE_VALUE_NOT_SUPPORTED:
        return "113";
      case CANCELLATION_OF_TRIGGERED_LOCATION_REQUEST:
        return "114";
      case INVALID_MSID_IN_TLRSR:
        return "115";
      case TLRSR_FOR_INDIVIDUAL_TARGET_NOT_SUPPORTED:
        return "116";
      case QOP_NOT_ATTAINABLE:
        return "201";
      case POSITIONING_NOT_ALLOWED:
        return "202";
      case CONGESTION_IN_MOBILE_NETWORK:
        return "203";
      case DISALLOWED_BY_LOCAL_REGULATIONS:
        return "204";
      case MISCONFIGURATION_OF_LOCATION_SERVER:
        return "207";
      case TARGET_MOVED_TO_NEW_MSC_SGSN:
        return "208";
      case STANDARD_LOCATION_REPORT_SERVICE_NOT_SUPPORTED:
        return "601";
      case MLS_CLIENT_ERROR:
        return "602";
      case STANDARD_LOCATION_REPORT_SERVICE_NOT_ACCEPTED:
        return "603";
      case SUBSCRIBER_IN_STANDARD_LOCATION_REPORT_SERVICE_NOT_VALID:
        return "604";
      case INVALID_SERVICE_ID_IN_STANDARD_LOCATION_REPORT_SERVICE:
        return "605";
      default:
        return "1"; // contains SYSTEM_FAILURE case
    }
  }

  /**
   * Get the MLP result string as per OMA MLP section 5.4.1
   *
   * @param mlpResultType MLPResultType internal enum value for use in the MLP classes
   * @return MLP result string to return to the client
   */
  public static String getResultStringForType(MLPResultType mlpResultType) {
    switch (mlpResultType) {
      case OK:
        return "OK";
      case UNSPECIFIED_ERROR:
        return "UNSPECIFIED ERROR";
      case UNAUTHORIZED_APPLICATION:
        return "UNAUTHORIZED APPLICATION";
      case UNKNOWN_SUBSCRIBER:
        return "UNKNOWN SUBSCRIBER";
      case ABSENT_SUBSCRIBER:
        return "ABSENT SUBSCRIBER";
      case POSITION_METHOD_FAILURE:
        return "POSITION METHOD FAILURE";
      case TIMEOUT:
        return "TIMEOUT";
      case CONGESTION_IN_LOCATION_SERVER:
        return "CONGESTION IN LOCATION SERVER";
      case UNSUPPORTED_VERSION:
        return "UNSUPPORTED VERSION";
      case TOO_MANY_POSITION_ITEMS:
        return "TOO MANY POSITION ITEMS";
      case FORMAT_ERROR:
        return "FORMAT ERROR";
      case SYNTAX_ERROR:
        return "SYNTAX ERROR";
      case PROTOCOL_ELEMENT_NOT_SUPPORTED:
        return "PROTOCOL ELEMENT NOT SUPPORTED";
      case SERVICE_NOT_SUPPORTED:
        return "SERVICE NOT SUPPORTED";
      case PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED:
        return "PROTOCOL ELEMENT ATTRIBUTE NOT SUPPORTED";
      case INVALID_PROTOCOL_ELEMENT_VALUE:
        return "INVALID PROTOCOL ELEMENT VALUE";
      case INVALID_PROTOCOL_ELEMENT_ATTRIBUTE_VALUE:
        return "INVALID PROTOCOL ELEMENT ATTRIBUTE VALUE";
      case PROTOCOL_ELEMENT_VALUE_NOT_SUPPORTED:
        return "PROTOCOL ELEMENT VALUE NOT SUPPORTED";
      case PROTOCOL_ELEMENT_ATTRIBUTE_VALUE_NOT_SUPPORTED:
        return "PROTOCOL ELEMENT ATTRIBUTE VALUE NOT SUPPORTED";
      case CANCELLATION_OF_TRIGGERED_LOCATION_REQUEST:
        return "CANCELLATION OF TRIGGERED LOCATION REQUEST";
      case INVALID_MSID_IN_TLRSR:
        return "INVALID MSID IN TLRSR";
      case TLRSR_FOR_INDIVIDUAL_TARGET_NOT_SUPPORTED:
        return "TLRSR FOR INDIVIDUAL TARGET NOT SUPPORTED";
      case QOP_NOT_ATTAINABLE:
        return "QOP NOT ATTAINABLE";
      case POSITIONING_NOT_ALLOWED:
        return "POSITIONING NOT ALLOWED";
      case CONGESTION_IN_MOBILE_NETWORK:
        return "CONGESTION IN MOBILE NETWORK";
      case DISALLOWED_BY_LOCAL_REGULATIONS:
        return "DISALLOWED BY LOCAL REGULATIONS";
      case MISCONFIGURATION_OF_LOCATION_SERVER:
        return "MISCONFIGURATION OF LOCATION SERVER";
      case TARGET_MOVED_TO_NEW_MSC_SGSN:
        return "TARGET MOVED TO NEW MSC/SGSN";
      case STANDARD_LOCATION_REPORT_SERVICE_NOT_SUPPORTED:
        return "STANDARD LOCATION REPORT SERVICE NOT SUPPORTED";
      case MLS_CLIENT_ERROR:
        return "MLS CLIENT ERROR";
      case STANDARD_LOCATION_REPORT_SERVICE_NOT_ACCEPTED:
        return "STANDARD LOCATION REPORT SERVICE NOT ACCEPTED";
      case SUBSCRIBER_IN_STANDARD_LOCATION_REPORT_SERVICE_NOT_VALID:
        return "SUBSCRIBER IN STANDARD LOCATION REPORT SERVICE NOT VALID";
      case INVALID_SERVICE_ID_IN_STANDARD_LOCATION_REPORT_SERVICE:
        return "INVALID SERVICE ID IN STANDARD LOCATION REPORT SERVICE";
      default:
        return "SYSTEM FAILURE"; // contains SYSTEM_FAILURE case
    }
  }

  /**
   * Is this error type a system error?
   *
   * @param mlpResultType MLPResultType internal enum value for use in the MLP classes
   * @return boolean true if it is a system error
   */
  public static boolean isSystemError(MLPResultType mlpResultType) {
    switch (mlpResultType) {
      case SYSTEM_FAILURE:
      case UNSPECIFIED_ERROR:
        return true;
      default:
        return false;
    }
  }

  /*
   * Use JAXB marshalling to generate the MLP XML result data for a single successful position look-up
   *
   * @param x         X coordinate in WGS84 DMS format
   * @param y         Y coordinate in WGS84 DMS format
   * @param radius    Position radius in meters (e.g. 5000 for 5km of accuracy)
   * @param msid      Location for MSISDN
   * @param mcc       Mobile Country Code
   * @param mnc       Mobile Network Code
   * @param lac       Location Area Code
   * @param ci        Cell Id
   * @param eci       LTE Cell Id
   * @param tac       Tracking Area Code
   * @param mmeName   Mobility Management Entity name
   * @param sgsnName  Serving GPRS Support Node name
   * @param mscNumber Mobile Switching Center number (E.164 digits, Global Title)
   * @param vlrNumber Visitor Location Register number (E.164 digits, Global Title)
   * @param imei      International Mobile Equipment Identity
   * @param imsi      International Mobile Subscriber Identity
   * @param age       Age of Location
   *
   * @return String XML result to return to client
   * Example usage:
   * svcResultXml = MLPResponse.getSinglePositionSuccessXML("27 28 25.00S", "153 01 43.00E", "+1000", "20140507082957", "61307341370");
   * Example result based on above usage:
   * <?xml version="1.0" encoding="UTF-8"?>
   * <!DOCTYPE svc_result SYSTEM "MLP_SVC_RESULT_320.DTD">
   * <svc_result xmlns="MLP_SVC_RESULT_320.DTD" ver="3.2.0">
   * <slia ver="3.4.0">
   *         <pos>
   *             <msid>59899077937</msid>
   *             <imei>011714004661057</imei>
   *             <pd>
   *                 <time utc_off="-0300">20160828181421</time>
   *                 <plmn>
   *                     <mcc>748</mcc>
   *                     <mnc>21</mnc>
   *                 </plmn>
   *                 <gsm_net_param>
   *                     <cgi>
   *                         <mcc>748</mcc>
   *                         <mnc>21</mnc>
   *                         <lac>32000</lac>
   *                         <cellid>38221</cellid>
   *                     </cgi>
   *                     <neid>
   *                         <vlrid>
   *                             <vlrno>59899000231</vlrno>
   *                         </vlrid>
   *                     </neid>
   *                 </gsm_net_param>
   *                 <geo_info>
   *                     <CoordinateReferenceSystem>
   *                 	    <Identifier>
   *                 		    <code>4326</code>
   *                 			<codeSpace>EPSG</codeSpace>
   *                 			<edition>6.1</edition>
   *                 		</Identifier>
   *                 	</CoordinateReferenceSystem>
   *                 	<shape>
   *                 		<CircularArea>
   *                 		    <coord>
   *                 			    <X>44 43 15.66S</X>
   *                 			    <Y>105 59 36.28E</Y>
   *                 			</coord>
   *                 		    <radius>76</radius>
   *                 		</CircularArea>
   *                 	</shape>
   *                 </geo_info>
   *             </pd>
   *         </pos>
   *     </slia>
   * </svc_result>
   */
  public String getCoreNetworkSinglePositionXML(String operation, String typeOfShape, Double x, Double y, Double radius, Double semiMajor,
                                                Double semMinor, Double angle, Double arcStartAngle, Double arcStopAngle, Integer altitude,
                                                org.mobicents.gmlc.slee.primitives.Polygon polygon, Integer polygonPointsAmount,
                                                Integer mcc, Integer mnc, Integer lac, Integer ci, Integer sac,
                                                Long eci, Integer rac, Integer tac, Long nci, Integer nrTac, String mmeName, String sgsnName, String mscNumber,
                                                String vlrNumber, String msid, String imei, String imsi, Integer age, String lmsi,
                                                Integer clientTransId, Integer lcsRefNumber, Integer ratType, MLPResultType mlpResultType,
                                                Boolean mlpTriggeredReportingService, Boolean isReport) {

    // Generate XML response
    String svcResultXml;

    try {
      // Eventually this timestamp should be replaced by the actual network position time
      Date requestTime = new Date();
      String date = new SimpleDateFormat("yyyyMMddHHmmss").format(requestTime);
      String utcOffset = new SimpleDateFormat("Z").format(requestTime);

      // Generate the response XML
      svcResultXml = this.generateSinglePositionSuccessXML(operation, typeOfShape, x, y, radius, semiMajor, semMinor, angle, arcStartAngle, arcStopAngle,
          altitude, polygon, polygonPointsAmount, utcOffset, date, msid, mcc, mnc, lac, ci, sac, eci, rac, tac, nci, nrTac, mmeName, sgsnName, mscNumber, vlrNumber,
          imei, imsi, age, lmsi, clientTransId, lcsRefNumber, ratType, mlpResultType, mlpTriggeredReportingService, isReport);

    } catch (IllegalArgumentException e) {
      // Generate the error XML
      svcResultXml = this.getSystemErrorResponseXML(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Failed to create request timestamp", mlpTriggeredReportingService);
    } catch (IOException e) {
      // Generate the error XML
      svcResultXml = this.getSystemErrorResponseXML(MLPResponse.MLPResultType.SYSTEM_FAILURE, "IO failure generating XML", mlpTriggeredReportingService);
    } catch (JAXBException e) {
      // Generate the error XML
      svcResultXml = this.getSystemErrorResponseXML(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Failed to generate XML response from internal objects", mlpTriggeredReportingService);

    }

    return svcResultXml;
  }

  /**
   * Internal XML generation support function for above getSs7SinglePositionSuccess7XML() when operation is ATI or PSI
   *
   * @param x                   X coordinate in WGS84 DMS format
   * @param y                   Y coordinate in WGS84 DMS format
   * @param radius              Position radius in meters (e.g. 5000 for 5km of accuracy)
   * @param semiMajor           Length of semi-major axis, oriented at angle A (0 to 180º) measured clockwise from North
   * @param semiMinor           Length of semi-minor axis, oriented at angle A (0 to 180º) measured clockwise from North
   * @param angle               Angle A (0 to 180°) measured clockwise from north and a semi-minor axis
   * @param arcStartAngle       Angle (in angularUnit) between North and the first defined radius
   * @param arcStopAngle        Angle (in angularUnit) between the first and second defined radius
   * @param altitude            Altitude of the location estimate
   * @param polygon             Array containing location estimate coordinates for each point of the polygon
   * @param polygonPointsAmount Amount of points of the polygon
   * @param utcOffSet           UTC offset for location timestamp in "[+/-]HHmm" format
   * @param date                Location timestamp at above UTC offset in "yyyyMMddHHmmss" format
   * @param msid                MSISDN
   * @param mcc                 Mobile Country Code
   * @param mnc                 Mobile Network Code
   * @param lac                 Location Area Code
   * @param ci                  Cell Id
   * @param sac                 Service Area Code
   * @param eci                 LTE Cell Id
   * @param tac                 LTE Tracking Area Code
   * @param nci                 5GS NR Cell Id
   * @param nrTac               5GS NR Tracking Area Code
   * @param mmeName             Mobility Management Entity name
   * @param sgsnName            Serving GPRS Support Node name
   * @param mscNumber           Mobile Switching Center number (E.164 digits, Global Title)
   * @param vlrNumber           Visitor Location Register number (E.164 digits, Global Title)
   * @param imei                International Mobile Equipment Identity
   * @param imsi                International Mobile Subscriber Identity
   * @param age                 Age of Location
   * @param lmsi                Location Mobile Subscriber Identity
   * @param clientTransId       Transaction Identity of the GMLC client
   * @param lcsReferenceNumber  LCS Reference Number parameter of the PSL invoke
   * @param mlpResultType       MLPResultType internal enum value for use in the MLP classes
   * @return                    String XML result to return to client
   * @throws IOException        IO error occurred while generating the XML result
   * @throws JAXBException      JAXB error occurred while generating the XML result
   */
  private String generateSinglePositionSuccessXML(String operation, String typeOfShape , Double x, Double y, Double radius, Double semiMajor,
                                                  Double semiMinor, Double angle, Double arcStartAngle, Double arcStopAngle, Integer altitude,
                                                  org.mobicents.gmlc.slee.primitives.Polygon polygon, Integer polygonPointsAmount,
                                                  String utcOffSet, String date, String msid, Integer mcc, Integer mnc, Integer lac,
                                                  Integer ci, Integer sac, Long eci, Integer rac, Integer tac, Long nci, Integer nrTac, String mmeName, String sgsnName,
                                                  String mscNumber, String vlrNumber, String imei, String imsi, Integer age, String lmsi,
                                                  Integer clientTransId, Integer lcsReferenceNumber, Integer ratType,
                                                  MLPResultType mlpResultType, Boolean mlpTriggeredReportingService, Boolean isReport) throws  IOException, JAXBException {

    String lXml;
    String ver = "3.4.0";

    // Create all the objects we'll use to generate our svc_result XML
    SvcResult mlpSvcResult = new SvcResult();
    Slia mlpSlia = new Slia();
    Tlra mlpTlra = new Tlra();
    Tlrep mlpTlrep = new Tlrep();
    Slrep mlpSlrep = new Slrep();
    Pos mlpPos = new Pos();
    TrlPos mlpTrlPos = new TrlPos();
    Msid mlpMsid = new Msid();
    Pd mlpPd = new Pd();
    Time mlpTime = new Time();
    Shape mlpShape = new Shape();
    Point mlpPoint = new Point();
    CircularArea mlpCircularArea = new CircularArea();
    EllipticalArea mlpEllipticalArea = new EllipticalArea();
    CircularArcArea mlpCircularArcArea = new CircularArcArea();
    Polygon mlpPolygon = new Polygon();
    Coord mlpCoord = new Coord();
    List<Pos> posList = new ArrayList<>();
    List<TrlPos> trlPosList = new ArrayList<>();
    GsmNetParam mlpGsmNetParam = new GsmNetParam();
    Cgi mlpCgi = new Cgi();
    Sai mlpSai = new Sai();
    ServingCell mlpServingCell = new ServingCell();
    Neid mlpNeid = new Neid();
    Vmscid mlpVmscId = new Vmscid();
    Vlrid mlpVlrId = new Vlrid();
    Result mlpResult = new Result();

    try {
      // Setup all the objects for this single position (Pos object)
      // Msid object for Pos
      // MSISDN
      mlpMsid.setContent(msid);
      mlpPos.setMsid(mlpMsid);
      // Pd object for Pos
      // Time object for Pd
      mlpTime.setUtcOff(utcOffSet);
      mlpTime.setContent(date);
      mlpPd.setTime(mlpTime);
      // Shape object for Pd
      if (typeOfShape != null) {
        if (typeOfShape.equalsIgnoreCase("EllipsoidPoint")) {
          mlpCoord.setX(String.valueOf(x));
          mlpCoord.setY(String.valueOf(y));
          mlpPoint.setCoord(mlpCoord);
          mlpShape.setPoint(mlpPoint);
        } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyCircle")) {
          mlpCoord.setX(String.valueOf(x));
          mlpCoord.setY(String.valueOf(y));
          mlpCircularArea.setCoord(mlpCoord);
          if (radius != null) {
            mlpCircularArea.setRadius(String.valueOf(radius));
          }
          mlpShape.setCircularArea(mlpCircularArea);
          if (operation.equalsIgnoreCase("ATI") || operation.equalsIgnoreCase("PSI")) {
            // This is a strange condition that sometimes happens in live networks for MAP ATI and MAP PSI,
            // where both latitude and longitude are given as 0.0 which is obviously a fake coordinate
            // For such case, we set the shape to NULL
            if (x != null && y != null) {
              if (x == 0.0 && y == 0.0) {
                logger.warning("Received x == 0.0 && y == 0.0 for EllipsoidPointWithUncertaintyCircle type from SS7 MAP " + operation +
                    " while setting the shape for MLP SLIA");
                mlpShape = null;
              }
            }
          }
        } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyEllipse")) {
          mlpCoord.setX(String.valueOf(x));
          mlpCoord.setY(String.valueOf(y));
          mlpEllipticalArea.setCoord(mlpCoord);
          mlpEllipticalArea.setSemiMajor(String.valueOf(semiMajor));
          mlpEllipticalArea.setSemiMinor(String.valueOf(semiMinor));
          mlpEllipticalArea.setAngle(String.valueOf(angle));
          mlpShape.setEllipticalArea(mlpEllipticalArea);
          // confidence not contemplated in MLP 3.4 EllipticalArea
        } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitudeAndUncertaintyEllipsoid")) {
          mlpCoord.setX(String.valueOf(x));
          mlpCoord.setY(String.valueOf(y));
          mlpEllipticalArea.setCoord(mlpCoord);
          mlpEllipticalArea.setSemiMajor(String.valueOf(semiMajor));
          mlpEllipticalArea.setSemiMinor(String.valueOf(semiMinor));
          mlpEllipticalArea.setAngle(String.valueOf(angle));
          mlpShape.setEllipticalArea(mlpEllipticalArea);
          // altitude and confidence not contemplated in MLP 3.4 EllipticalArea
        } else if (typeOfShape.equalsIgnoreCase("EllipsoidArc")) {
          mlpCoord.setX(String.valueOf(x));
          mlpCoord.setY(String.valueOf(y));
          mlpCircularArcArea.setCoord(mlpCoord);
          mlpCircularArcArea.setInRadius(String.valueOf(radius));
          mlpCircularArcArea.setStartAngle(String.valueOf(arcStartAngle));
          mlpCircularArcArea.setStopAngle(String.valueOf(arcStopAngle));
          mlpShape.setCircularArcArea(mlpCircularArcArea);
          // confidence not contemplated in MLP 3.4 EllipticalArea
        } else if (typeOfShape.equalsIgnoreCase("Polygon")) {
          LinearRing linearRing = new LinearRing();
          OuterBoundaryIs outerBoundaryIs = new OuterBoundaryIs();
          double lat, pointLatitude, lon, pointLongitude;
          String formattedLatitude, formattedLongitude;
          Double[][] polygonArray = new Double[polygonPointsAmount][polygonPointsAmount];
          if (polygonPointsAmount > 2 && polygonPointsAmount <= 15) {
            for (int index = 0; index < polygonPointsAmount; index++) {
              lat = polygon.getEllipsoidPoint(index).getLatitude();
              lon = polygon.getEllipsoidPoint(index).getLongitude();
              polygonArray[index][0] = lat;
              polygonArray[index][1] = lon;
            }
            for (int point = 0; point < polygonArray.length; point++) {
              Coord polygonCoord = new Coord();
              pointLatitude = polygonArray[point][0];
              pointLongitude = polygonArray[point][1];
              formattedLatitude = coordinatesFormat.format(pointLatitude);
              formattedLongitude = coordinatesFormat.format(pointLongitude);
              polygonCoord.setX(formattedLatitude);
              polygonCoord.setY(formattedLongitude);
              linearRing.getContent().add(polygonCoord);
            }
            outerBoundaryIs.setLinearRing(linearRing);
            mlpPolygon.setOuterBoundaryIs(outerBoundaryIs);
          }
          mlpShape.setPolygon(mlpPolygon);
        }
        mlpPd.setShape(mlpShape);
      }
      // GsmNetParam and ServingCell setting for object Pos
      if (mcc != null || mnc != null) {
        if (mcc != null) {
          mlpCgi.setMcc(String.valueOf(mcc));
        }
        if (mnc != null) {
          mlpCgi.setMnc(String.valueOf(mnc));
        }
        if (lac != null && sac == null) {
          mlpCgi.setLac(String.valueOf(lac));
          if (ci != null) {
            mlpCgi.setCellid(String.valueOf(ci));
          }
          mlpGsmNetParam.setCgi(mlpCgi);
        }
        if (sac != null) {
          mlpSai.setMcc(String.valueOf(mcc));
          mlpSai.setMnc(String.valueOf(mnc));
          mlpSai.setLac(String.valueOf(lac));
          mlpSai.setSac(String.valueOf(sac));
          mlpServingCell.setSai(mlpSai);
        }
        if (eci != null) {
          mlpServingCell.setMcc(String.valueOf(mcc));
          mlpServingCell.setMnc(String.valueOf(mnc));
          mlpServingCell.setLteCi(String.valueOf(eci));
        }
        if (nci != null) {
          mlpServingCell.setMcc(String.valueOf(mcc));
          mlpServingCell.setMnc(String.valueOf(mnc));
          mlpServingCell.setNrCi(String.valueOf(nci));
        }
        if (rac != null) {
          mlpPos.setRoutingAreaCode(String.valueOf(rac));
        }
        if (tac != null) {
          mlpPos.setTrackingAreaCode(String.valueOf(tac));
        }
        if (nrTac != null) {
          mlpPos.setNrTrackingAreaCode(String.valueOf(nrTac));
        }
      }
      if (mscNumber != null || vlrNumber != null) {
        if (mscNumber != null) {
          mlpVmscId.setVmscno(mscNumber);
          mlpNeid.getContent().add(mlpVmscId);
          mlpGsmNetParam.setNeid(mlpNeid);
        }
        if (vlrNumber != null) {
          mlpVlrId.setVlrno(vlrNumber);
          mlpNeid.getContent().add(mlpVlrId);
          mlpGsmNetParam.setNeid(mlpNeid);
        }
      }
      if (imsi != null) {
        if (!imsi.equals("")) {
          mlpGsmNetParam.setImsi(imsi);
        }
      }
      if (lmsi != null) {
        if (!lmsi.equals("")) {
          mlpGsmNetParam.setLmsi(lmsi);
        }
      }
      if (!isReport) {
        if (mlpServingCell.getSai() != null || mlpServingCell.getLteCi() != null || mlpServingCell.getNrCi() != null) {
          // Add the ServingCell to Pos for SLIA
          mlpPos.setServingCell(mlpServingCell);
        }
        if (mlpCgi.getLac() != null || mscNumber != null || vlrNumber != null || imsi != null || lmsi != null) {
          // Add the gsmNetParams to Pos for SLIA
          mlpPos.setGsmNetParam(mlpGsmNetParam);
        }
      } else {
        if (mlpTriggeredReportingService) {
            if (mlpServingCell.getSai() != null || mlpServingCell.getLteCi() != null || mlpServingCell.getNrCi() != null) {
              // Add the ServingCell to TrlPos for TLRA
              mlpTrlPos.setServingCell(mlpServingCell);
            }
            if (mlpCgi.getLac() != null || mscNumber != null || vlrNumber != null || imsi != null || lmsi != null) {
              // Add the gsmNetParams to TrlPos for TLRA
              mlpTrlPos.setGsmNetParam(mlpGsmNetParam);
            }
        } else {
          if (mlpServingCell.getSai() != null || mlpServingCell.getLteCi() != null || mlpServingCell.getNrCi() != null) {
            // Add the ServingCell to Pos for SLREP
            mlpPos.setServingCell(mlpServingCell);
          }
          if (mlpCgi.getLac() != null || mscNumber != null || vlrNumber != null || imsi != null || lmsi != null) {
            // Add the gsmNetParams to Pos for SLREP
            mlpPos.setGsmNetParam(mlpGsmNetParam);
          }
        }
      }
      // Set transId in Pos (LCS Reference Number)
      if (operation.equalsIgnoreCase("PSL") || operation.equalsIgnoreCase("PLR")) {
        mlpTlra.setLcsRef(String.valueOf(lcsReferenceNumber));
        mlpPos.setResultType("FINAL");
      } else if (isReport) {
        mlpTlrep.setLcsRef(String.valueOf(lcsReferenceNumber));
      }
      // addInfo ?
      // posMethod ?
      // resultType
      mlpPos.setResultType("FINAL");
      if (!isReport) {
        // Set Pd object in Pos
        mlpPos.setPd(mlpPd);
        // Add the position list to the SLIA or TLRA
        posList.add(mlpPos);
        for (Pos pos : posList) {
          if (mlpTriggeredReportingService) {
              mlpTlra.getPos().add(pos);
          } else {
            mlpSlia.getPos().add(pos);
          }
        }
      } else {
        if (mlpTriggeredReportingService) {
            // Set the Msid in TrlPos for TLREP
            mlpTrlPos.setMsid(mlpMsid);
            // Set Pd object in TrlPos for TLREP
            mlpTrlPos.setPd(mlpPd);
            // Add the position list to the TLREP
            trlPosList.add(mlpTrlPos);
            for (TrlPos tlrPos : trlPosList) {
              mlpTlrep.getTrlPos().add(tlrPos);
          }
        } else {
          // Set Pd object in Pos
          mlpPos.setPd(mlpPd);
          // Add the position list to the SLREP
          posList.add(mlpPos);
          for (Pos pos : posList) {
            mlpSlrep.getPos().add(pos);
          }
        }
      }
      // Set req_id to SLIA or TLRA/TLREP (Client Reference Number)
      if (clientTransId != null) {
        mlpSlia.setReqId(String.valueOf(clientTransId));
        mlpTlra.setReqId(String.valueOf(clientTransId));
        if (isReport)
          mlpTlrep.setReqId(String.valueOf(clientTransId));
      }
      // Result
      mlpResult.setContent(MLPResponse.getResultStringForType(mlpResultType));
      mlpResult.setResid(MLPResponse.getResultCodeForType(mlpResultType));
      if (mlpTriggeredReportingService) {
        if (!isReport) {
          if (operation.equalsIgnoreCase("PSL") || operation.equalsIgnoreCase("PLR")
              || operation.equalsIgnoreCase("SUPL")) {
            // Result for TLRA
            mlpTlra.setResult(mlpResult);
            // Version for TLRA
            mlpTlra.setVer(ver);
            // Set SvcResult with MLP TLRA
            mlpSvcResult.setTlra(mlpTlra);
          }
        } else {
          // Version for TLREP
          mlpTlrep.setVer(ver);
          // Set SvcResult with MLP TLREP
          mlpSvcResult.setTlrep(mlpTlrep);
        }
      } else {
        if (operation.equalsIgnoreCase("SLR") || operation.equalsIgnoreCase("LRR")) {
          // Version for SLREP
          mlpSlrep.setVer(ver);
          // Set SvcResult with MLP SLREP
          mlpSvcResult.setSlrep(mlpSlrep);
        } else {
          // Result for SLIA
          mlpSlia.setResult(mlpResult);
          // Version for SLIA
          mlpSlia.setVer(ver);
          // Set SvcResult with MLP SLIA
          mlpSvcResult.setSlia(mlpSlia);
        }
      }

      mlpSvcResult.setVer(ver);

      lXml = marshalMlpResult(mlpSvcResult, mlpTriggeredReportingService);
      // Return our XML string result
      return lXml;

    } catch (IllegalArgumentException illegalArgumentException) {
      // Return generic XML error response because we couldn't generate the correct response
      illegalArgumentException.printStackTrace();
      this.logger.warning("Exception while creating timestamp: " + illegalArgumentException.getMessage());
      this.exceptionError = "IllegalArgumentException while marshalling XML response data";
      if (mlpTriggeredReportingService)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (IOException ioException) {
      // Return generic XML error response because we couldn't generate the correct response
      ioException.printStackTrace();
      this.logger.warning("Exception while creating XML response data: " + ioException.getMessage());
      this.exceptionError = "IOException while marshalling XML response data";
      if (mlpTriggeredReportingService)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    }   catch (JAXBException jaxbException) {
      // Return generic XML error response because we couldn't generate the correct response
      jaxbException.printStackTrace();
      this.logger.warning("Exception while marshalling XML response data: " + jaxbException.getMessage());
      this.exceptionError = "JAXBException while marshalling XML response data";
      if (mlpTriggeredReportingService)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (Exception e) {
      // Return generic XML error response because we couldn't generate the correct response
      e.printStackTrace();
      this.logger.warning("Exception while marshalling XML response data: " + e.getMessage());
      this.exceptionError = "Exception while marshalling XML response data";
      if (mlpTriggeredReportingService)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    }
  }

  /**
   * Generate a MLP system error response
   *
   * @param mlpClientErrorType    Error type to return to client
   * @param mlpClientErrorMessage Error message to send to client
   * @return String XML result to return to client
   */
  public String getSystemErrorResponseXML(MLPResultType mlpClientErrorType, String mlpClientErrorMessage, boolean isTriggered) {
    // Generate XML response
    String svcResultXml;
    try {
      // Generate the error XML
      svcResultXml = this.generateSystemErrorXML(mlpClientErrorType, mlpClientErrorMessage, false);
      return svcResultXml;
    } catch (IOException e) {
      // Return generic XML error response because we couldn't generate the correct response
      e.printStackTrace();
      this.logger.info("Exception while creating XML response data: " + e.getMessage());
      this.exceptionError = "IOException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (JAXBException e) {
      // Return generic XML error response because we couldn't generate the correct response
      e.printStackTrace();
      this.logger.info("Exception while marshalling XML response data: " + e.getMessage());
      this.exceptionError = "Exception while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    }
  }

  /**
   * Internal XML generation support function for above getSystemErrorResponseXML()
   *
   * @param mlpClientErrorType                Error type to return to client
   * @param mlpClientErrorMessage             Error message to send to client
   * @return                                  String XML result to return to client
   * @throws IOException                      IO error occurred while generating the XML result
   * @throws JAXBException                    JAXB error occurred while generating the XML result
   */
  private String generateSystemErrorXML(MLPResultType mlpClientErrorType, String mlpClientErrorMessage, boolean isTriggered) throws  IOException, JAXBException {

    String lXml;
    String ver = "3.4.0";

    try {
      // Create all the objects we'll use to generate our svc_result XML
      SvcResult mlpSvcResult = new SvcResult();
      Slia mlpSlia = new Slia();
      Tlra mlpTlra = new Tlra();
      Result mlpResult = new Result();

      // Set the additional data error message if one is available
      if (mlpClientErrorMessage != null) {
        if (isTriggered)
          mlpTlra.setAddInfo(mlpClientErrorMessage);
        else
          mlpSlia.setAddInfo(mlpClientErrorMessage);
      }

      mlpResult.setContent(MLPResponse.getResultStringForType(mlpClientErrorType));
      mlpResult.setResid(MLPResponse.getResultCodeForType(mlpClientErrorType));

      if (isTriggered) {
        mlpTlra.setResult(mlpResult);
        mlpTlra.setVer(ver);
        mlpSvcResult.setTlra(mlpTlra);
      } else {
        mlpSlia.setResult(mlpResult);
        mlpSlia.setVer(ver);
        mlpSvcResult.setSlia(mlpSlia);
      }
      mlpSvcResult.setVer(ver);

      JAXBContext jc = JAXBContext.newInstance(SvcResult.class);
      Marshaller marshaller  = jc.createMarshaller();

      ByteArrayOutputStream lOutputStream = new ByteArrayOutputStream();

      // Generate the XML
      marshaller.marshal(mlpSvcResult,lOutputStream);

      // Convert the stream to a string
      lXml = lOutputStream.toString(StandardCharsets.UTF_8);

      // Return our XML string result
      return lXml;

    } catch (IllegalArgumentException illegalArgumentException) {
      // Return generic XML error response because we couldn't generate the correct response
      illegalArgumentException.printStackTrace();
      this.logger.info("Exception while creating timestamp: " + illegalArgumentException.getMessage());
      this.exceptionError = "IllegalArgumentException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (JAXBException jaxbException) {
      // Return generic XML error response because we couldn't generate the correct response
      jaxbException.printStackTrace();
      this.logger.info("Exception while marshalling XML response data: " + jaxbException.getMessage());
      this.exceptionError = "JAXBException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (Exception e) {
      // Return generic XML error response because we couldn't generate the correct response
      e.printStackTrace();
      this.logger.info("Exception while marshalling XML response data: " + e.getMessage());
      this.exceptionError = "Exception while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    }
  }

  /**
   * Generate a MLP error response for a position
   *
   * @param msid                  Device MSISDN
   * @param mlpClientErrorType    Error type to return to client
   * @param mlpClientErrorMessage Error message to send to client
   * @return String XML result to return to client
   */
  public String getPositionErrorResponseXML(String msid, String imsi, String mscNumber, String vlrNumber, MLPResultType mlpClientErrorType,
                                            String mlpClientErrorMessage, boolean isTriggered) {
    // Generate XML response
    String svcResultXml;

    try {
      // Eventually this timestamp should be replaced by the actual network position time
      Date requestTime = new Date();
      String date = new SimpleDateFormat("yyyyMMddHHmmss").format(requestTime);
      String utcOffset = new SimpleDateFormat("Z").format(requestTime);

      // Generate the error XML
      this.logger.info("Creating error XML response for type: " + MLPResponse.getResultCodeForType(mlpClientErrorType) + " message: " + mlpClientErrorMessage);
      svcResultXml = this.generatePositionErrorXML(utcOffset, date, msid, imsi, mscNumber, vlrNumber, mlpClientErrorType, mlpClientErrorMessage, isTriggered);
      return svcResultXml;
    } catch (IllegalArgumentException illegalArgumentException) {
      // Return generic XML error response because we couldn't generate the correct response
      illegalArgumentException.printStackTrace();
      this.logger.info("Exception while creating timestamp: " + illegalArgumentException.getMessage());
      this.exceptionError = "IllegalArgumentException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (IOException ioException) {
      // Return generic XML error response because we couldn't generate the correct response
      ioException.printStackTrace();
      this.logger.info("Exception while creating XML response data: " + ioException.getMessage());
      this.exceptionError = "IOException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    }   catch (JAXBException jaxbException) {
      // Return generic XML error response because we couldn't generate the correct response
      jaxbException.printStackTrace();
      this.logger.info("Exception while marshalling XML response data: " + jaxbException.getMessage());
      this.exceptionError = "JAXBException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (Exception e) {
      // Return generic XML error response because we couldn't generate the correct response
      e.printStackTrace();
      this.logger.info("Exception while marshalling XML response data: " + e.getMessage());
      this.exceptionError = "Exception while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    }
  }

  /**
   * Internal XML generation support function for above getSystemErrorResponseXML()
   *
   * @param utcOffSet             Utc offset for location timestamp in "[+/-]HHmm" format
   * @param time                  Location timestamp at above UTC offset in "yyyyMMddHHmmss" format
   * @param msid                  Device MSISDN
   * @param mlpClientErrorType    Error type to return to client
   * @param mlpClientErrorMessage Error message to send to client
   * @return                      String XML result to return to client
   * @throws IOException          IO error occurred while generating the XML result
   * @throws JAXBException        JAXB error occurred while generating the XML result
   */
  private String generatePositionErrorXML(String utcOffSet, String time, String msid, String imsi, String mscNumber, String vlrNumber,
                                          MLPResultType mlpClientErrorType, String mlpClientErrorMessage, boolean isTriggered)
      throws  IOException, JAXBException {

    String lXml;
    String ver = "3.4.0";

    try {
      // Create all the objects we'll use to generate our svc_result XML
      SvcResult mlpSvcResult = new SvcResult();
      Slia mlpSlia = new Slia();
      Tlra mlpTlra = new Tlra();
      Poserr mlpPosErr = new Poserr();
      Pos mlpPos = new Pos();
      Msid mlpMsid = new Msid();
      Time mlpTime = new Time();
      List<Pos> posList = new ArrayList<>();
      GsmNetParam mlpGsmNetParam = new GsmNetParam();
      Neid mlpNeid = new Neid();
      Vmscid mlpVmscId = new Vmscid();
      Vlrid mlpVlrId = new Vlrid();
      Result mlpResult = new Result();

      // Set the data
      mlpTime.setUtcOff(utcOffSet);
      mlpTime.setContent(time);
      mlpMsid.setContent(msid);

      // Set the additional data error message if one is available
      if (mlpClientErrorMessage != null) {
        mlpPosErr.setAddInfo(mlpClientErrorMessage);
      }

      mlpResult.setContent(MLPResponse.getResultStringForType(mlpClientErrorType));
      mlpResult.setResid(MLPResponse.getResultCodeForType(mlpClientErrorType));
      mlpPosErr.setResult(mlpResult);
      mlpPosErr.setTime(mlpTime);
      mlpPos.setMsid(mlpMsid);
      mlpPos.setPoserr(mlpPosErr);
      // MSISDN
      mlpPos.setMsid(mlpMsid);
      // IMSI
      if (imsi != null) {
        if (!imsi.equals("")) {
          mlpGsmNetParam.setImsi(imsi);
        }
        mlpPos.setGsmNetParam(mlpGsmNetParam);
      }
      if (mscNumber != null || vlrNumber != null) {
        if (mscNumber != null) {
          mlpVmscId.setVmscno(mscNumber);
          mlpNeid.getContent().add(mlpVmscId);
          mlpGsmNetParam.setNeid(mlpNeid);
        }
        if (vlrNumber != null) {
          mlpVlrId.setVlrno(vlrNumber);
          mlpNeid.getContent().add(mlpVlrId);
        }
        mlpGsmNetParam.setNeid(mlpNeid);
      }
      posList.add(mlpPos);
      for (Pos pos : posList) {
        if (isTriggered)
          mlpTlra.getPos().add(pos);
        else
          mlpSlia.getPos().add(pos);
      }

      if (isTriggered) {
        mlpTlra.setVer(ver);
        mlpTlra.setResult(mlpResult);
        mlpSvcResult.setTlra(mlpTlra);
        mlpSvcResult.setVer(ver);
      } else {
        mlpSlia.setVer(ver);
        mlpSlia.setResult(mlpResult);
        mlpSvcResult.setSlia(mlpSlia);
        mlpSvcResult.setVer(ver);
      }

      lXml = marshalMlpResult(mlpSvcResult, isTriggered);

      // Return our XML string result
      return lXml;
    } catch (IllegalArgumentException illegalArgumentException) {
      // Return generic XML error response because we couldn't generate the correct response
      illegalArgumentException.printStackTrace();
      this.logger.info("Exception while creating timestamp: " + illegalArgumentException.getMessage());
      this.exceptionError = "IllegalArgumentException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (IOException ioException) {
      // Return generic XML error response because we couldn't generate the correct response
      ioException.printStackTrace();
      this.logger.info("Exception while creating XML response data: " + ioException.getMessage());
      this.exceptionError = "IOException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (JAXBException jaxbException) {
      // Return generic XML error response because we couldn't generate the correct response
      jaxbException.printStackTrace();
      this.logger.info("Exception while marshalling XML response data: " + jaxbException.getMessage());
      this.exceptionError = "JAXBException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (Exception e) {
      // Return generic XML error response because we couldn't generate the correct response
      e.printStackTrace();
      this.logger.info("Exception while marshalling XML response data: " + e.getMessage());
      this.exceptionError = "Exception while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    }
  }

  /**
   * Create the svc_result XML result for any type of result (error or success)
   *
   * @param mlpSvcResult    Fully filled in SvcResult object to marshal (convert to XML)
   * @return                String of XML result to send to client
   * @throws IOException    IO error occurred while generating the XML result
   * @throws JAXBException  JAXB error occurred while generating the XML result
   */
  private String marshalMlpResult(SvcResult mlpSvcResult, boolean isTriggered) throws IOException, JAXBException {
    String lXml;

    JAXBContext jc = JAXBContext.newInstance(SvcResult.class);
    Marshaller marshaller = jc.createMarshaller();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DOMResult domResult = new DOMResult();

    // Generate the XML
    marshaller.marshal(mlpSvcResult, domResult);

    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      transformerFactory.setAttribute("indent-number", 4);
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      transformer.transform(new DOMSource(domResult.getNode()), new StreamResult(outputStream));
    } catch (TransformerConfigurationException transformerConfigurationException) {
      transformerConfigurationException.printStackTrace();
      this.logger.info("Exception while creating XML response data: " + transformerConfigurationException.getMessage());
      this.exceptionError = "TransformerConfigurationException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (TransformerException transformerException) {
      transformerException.printStackTrace();
      this.logger.info("TransformerException while creating XML response data: " + transformerException.getMessage());
      this.exceptionError = "TransformerConfigurationException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    } catch (Exception e) {
      e.printStackTrace();
      this.logger.info("Exception while creating XML response data: " + e.getMessage());
      this.exceptionError = "TransformerConfigurationException while marshalling XML response data";
      if (isTriggered)
        return genericTriggeredLocationRequestErrorXML;
      else
        return genericStandardLocationRequestErrorXML;
    }

    // Convert the stream to a string
    lXml = outputStream.toString(StandardCharsets.UTF_8);
    // Return our XML string result
    return lXml;
  }
}