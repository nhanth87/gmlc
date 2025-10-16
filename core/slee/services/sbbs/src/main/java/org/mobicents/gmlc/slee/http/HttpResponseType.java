package org.mobicents.gmlc.slee.http;

import org.mobicents.gmlc.slee.mlp.MLPResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class HttpResponseType {

    public static int setHttpServletResponseStatusCode(MLPResponse.MLPResultType mlpResultType) {
        switch (mlpResultType) {
            case SYSTEM_FAILURE:
                return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            case UNSPECIFIED_ERROR:
            case INVALID_SERVICE_ID_IN_STANDARD_LOCATION_REPORT_SERVICE:
            case CONGESTION_IN_MOBILE_NETWORK:
            case SERVICE_NOT_SUPPORTED:
            case CONGESTION_IN_LOCATION_SERVER:
                return HttpServletResponse.SC_SERVICE_UNAVAILABLE;
            case UNAUTHORIZED_APPLICATION:
                return HttpServletResponse.SC_UNAUTHORIZED;
            case UNKNOWN_SUBSCRIBER:
            case SUBSCRIBER_IN_STANDARD_LOCATION_REPORT_SERVICE_NOT_VALID:
                return HttpServletResponse.SC_NOT_FOUND;
            case ABSENT_SUBSCRIBER:
            case TARGET_MOVED_TO_NEW_MSC_SGSN:
                return HttpServletResponse.SC_GONE;
            case POSITION_METHOD_FAILURE:
            case QOP_NOT_ATTAINABLE:
                return HttpServletResponse.SC_EXPECTATION_FAILED;
            case TIMEOUT:
                return HttpServletResponse.SC_REQUEST_TIMEOUT;
            case UNSUPPORTED_VERSION:
            case STANDARD_LOCATION_REPORT_SERVICE_NOT_ACCEPTED:
            case STANDARD_LOCATION_REPORT_SERVICE_NOT_SUPPORTED:
            case TLRSR_FOR_INDIVIDUAL_TARGET_NOT_SUPPORTED:
            case PROTOCOL_ELEMENT_ATTRIBUTE_VALUE_NOT_SUPPORTED:
            case PROTOCOL_ELEMENT_VALUE_NOT_SUPPORTED:
            case INVALID_PROTOCOL_ELEMENT_ATTRIBUTE_VALUE:
            case INVALID_PROTOCOL_ELEMENT_VALUE:
            case PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED:
            case PROTOCOL_ELEMENT_NOT_SUPPORTED:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case TOO_MANY_POSITION_ITEMS:
            case INVALID_MSID_IN_TLRSR:
            case SYNTAX_ERROR:
            case FORMAT_ERROR:
                return HttpServletResponse.SC_BAD_REQUEST;
            case CANCELLATION_OF_TRIGGERED_LOCATION_REQUEST:
                return HttpServletResponse.SC_RESET_CONTENT;
            case POSITIONING_NOT_ALLOWED:
            case DISALLOWED_BY_LOCAL_REGULATIONS:
                return HttpServletResponse.SC_FORBIDDEN;
            case MISCONFIGURATION_OF_LOCATION_SERVER:
            case MLS_CLIENT_ERROR:
                return HttpServletResponse.SC_BAD_GATEWAY;
            default:
                return HttpServletResponse.SC_OK;
        }
    }

}
