package net.java.slee.resource.diameter.slh;

import net.java.slee.resource.diameter.base.DiameterMessageFactory;
import net.java.slee.resource.diameter.slh.events.LocationReportAnswer;
import net.java.slee.resource.diameter.slh.events.LocationReportRequest;
import net.java.slee.resource.diameter.slh.events.RoutingInfoAnswer;
import net.java.slee.resource.diameter.slh.events.RoutingInfoRequest;

/**
 * The SLh message factory interface used to create Diameter SLh messages.
 * 
 * @author GMLC Team
 */
public interface SLhMessageFactory {

    /**
     * variable equal to SLh application ID
     */
    public static final int _SLH_APP_ID = 16777291;

    /**
     * ID of SLh application vendor - 3GPP
     */
    public static final int _SLH_VENDOR_ID = 10415;

    /**
     * Get a factory to create AVPs and messages defined by Diameter Base.
     * 
     * @return base Diameter message factory
     */
    DiameterMessageFactory getBaseMessageFactory();

    /**
     * Create an empty RoutingInfoRequest that will need to have AVPs set on it before being sent.
     * 
     * @return a RoutingInfoRequest object
     */
    RoutingInfoRequest createRoutingInfoRequest();

    /**
     * Create a RoutingInfoAnswer containing a Result-Code or Experimental-Result AVP populated with the given value.
     * If <code>isExperimentalResultCode</code> is <code>true</code>, the <code>resultCode</code> parameter will be set
     * in a {@link org.mobicents.slee.resource.diameter.base.types.ExperimentalResultAvp} AVP, if it is <code>false</code> it 
     * will be sent as a standard Result-Code AVP.
     * 
     * @param request the corresponding request
     * @param resultCode the result code value
     * @param isExperimentalResultCode whether the result code is experimental
     * @return a RoutingInfoAnswer object
     */
    RoutingInfoAnswer createRoutingInfoAnswer(RoutingInfoRequest request, long resultCode, boolean isExperimentalResultCode);

    /**
     * Create an empty RoutingInfoAnswer that will need to have AVPs set on it before being sent.
     * 
     * @param request the corresponding request
     * @return a RoutingInfoAnswer object
     */
    RoutingInfoAnswer createRoutingInfoAnswer(RoutingInfoRequest request);

    /**
     * Create an empty LocationReportRequest that will need to have AVPs set on it before being sent.
     * 
     * @return a LocationReportRequest object
     */
    LocationReportRequest createLocationReportRequest();

    /**
     * Create a LocationReportAnswer containing a Result-Code or Experimental-Result AVP populated with the given value.
     * If <code>isExperimentalResultCode</code> is <code>true</code>, the <code>resultCode</code> parameter will be set
     * in a {@link org.mobicents.slee.resource.diameter.base.types.ExperimentalResultAvp} AVP, if it is <code>false</code> it 
     * will be sent as a standard Result-Code AVP.
     * 
     * @param request the corresponding request
     * @param resultCode the result code value
     * @param isExperimentalResultCode whether the result code is experimental
     * @return a LocationReportAnswer object
     */
    LocationReportAnswer createLocationReportAnswer(LocationReportRequest request, long resultCode, boolean isExperimentalResultCode);

    /**
     * Create an empty LocationReportAnswer that will need to have AVPs set on it before being sent.
     * 
     * @param request the corresponding request
     * @return a LocationReportAnswer object
     */
    LocationReportAnswer createLocationReportAnswer(LocationReportRequest request);
}
