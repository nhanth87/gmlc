package org.mobicents.gmlc.slee.diameter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class DiameterBaseError {

    public static final int DIAMETER_COMMAND_UNSUPPORTED = 3001;
    public static final int DIAMETER_UNABLE_TO_DELIVER = 3002;
    public static final int DIAMETER_REALM_NOT_SERVED = 3003;
    public static final int DIAMETER_TOO_BUSY = 3004;
    public static final int DIAMETER_LOOP_DETECTED = 3005;
    public static final int DIAMETER_REDIRECT_INDICATION = 3006;
    public static final int DIAMETER_APPLICATION_UNSUPPORTED = 3007;
    public static final int DIAMETER_INVALID_HDR_BITS = 3008;
    public static final int DIAMETER_INVALID_AVP_BITS = 3009;
    public static final int DIAMETER_UNKNOWN_PEER = 3010;
    public static final int DIAMETER_AUTHENTICATION_REJECTED = 4001;
    public static final int DIAMETER_OUT_OF_SPACE = 4002;
    public static final int DIAMETER_ELECTION_LOST = 4003;
    public static final int DIAMETER_AVP_UNSUPPORTED = 5001;
    public static final int DIAMETER_UNKNOWN_SESSION_ID = 5002;
    public static final int DIAMETER_AUTHORIZATION_REJECTED = 5003;
    public static final int DIAMETER_INVALID_AVP_VALUE = 5004;
    public static final int DIAMETER_MISSING_AVP = 5005;
    public static final int DIAMETER_RESOURCES_EXCEEDED = 5006;
    public static final int DIAMETER_CONTRADICTING_AVPS = 5007;
    public static final int DIAMETER_AVP_NOT_ALLOWED = 5008;
    public static final int DIAMETER_AVP_OCCURS_TOO_MANY_TIMES = 5009;
    public static final int DIAMETER_NO_COMMON_APPLICATION = 5010;
    public static final int DIAMETER_UNSUPPORTED_VERSION = 5011;
    public static final int DIAMETER_UNABLE_TO_COMPLY = 5012;
    public static final int DIAMETER_INVALID_BIT_IN_HEADER = 5013;
    public static final int DIAMETER_INVALID_AVP_LENGTH = 5014;
    public static final int DIAMETER_INVALID_MESSAGE_LENGTH = 5015;
    public static final int DIAMETER_INVALID_AVP_BIT_COMBO = 5016;
    public static final int DIAMETER_NO_COMMON_SECURITY = 5017;


    private static String errorMessage;

    public DiameterBaseError() {
    }

    public static String diameterErrorMessage(long resultCode) {
        if (resultCode == 3001)
            errorMessage = "DIAMETER_COMMAND_UNSUPPORTED, Diameter error code: " + resultCode;
        else if (resultCode == 3002)
            errorMessage = "DIAMETER_UNABLE_TO_DELIVER, Diameter error code: " + resultCode;
        else if (resultCode == 3003)
            errorMessage = "DIAMETER_REALM_NOT_SERVED, Diameter error code: " + resultCode;
        else if (resultCode == 3004)
            errorMessage = "DIAMETER_TOO_BUSY, Diameter error code: " + resultCode;
        else if (resultCode == 3005)
            errorMessage = "DIAMETER_LOOP_DETECTED, Diameter error code: " + resultCode;
        else if (resultCode == 3006)
            errorMessage = "DIAMETER_REDIRECT_INDICATION, Diameter error code: " + resultCode;
        else if (resultCode == 3007)
            errorMessage = "DIAMETER_APPLICATION_UNSUPPORTED, Diameter error code: " + resultCode;
        else if (resultCode == 3008)
            errorMessage = "DIAMETER_INVALID_HDR_BITS, Diameter error code: " + resultCode;
        else if (resultCode == 3009)
            errorMessage = "DIAMETER_INVALID_AVP_BITS, Diameter error code: " + resultCode;
        else if (resultCode == 3010)
            errorMessage = "DIAMETER_UNKNOWN_PEER, Diameter error code: " + resultCode;
        else if (resultCode == 4001)
            errorMessage = "DIAMETER_AUTHENTICATION_REJECTED, Diameter error code: " + resultCode;
        else if (resultCode == 4002)
            errorMessage = "DIAMETER_OUT_OF_SPACE, Diameter error code: " + resultCode;
        else if (resultCode == 4003)
            errorMessage = "DIAMETER_ELECTION_LOST, Diameter error code: " + resultCode;
        else if (resultCode == 5001)
            errorMessage = "DIAMETER_AVP_UNSUPPORTED, Diameter error code: " + resultCode;
        else if (resultCode == 5002)
            errorMessage = "DIAMETER_UNKNOWN_SESSION_ID, Diameter error code: " + resultCode;
        else if (resultCode == 5003)
            errorMessage = "DIAMETER_AUTHORIZATION_REJECTED, Diameter error code: " + resultCode;
        else if (resultCode == 5004)
            errorMessage = "DIAMETER_INVALID_AVP_VALUE, Diameter error code: " + resultCode;
        else if (resultCode == 5005)
            errorMessage = "DIAMETER_MISSING_AVP, Diameter error code: " + resultCode;
        else if (resultCode == 5006)
            errorMessage = "DIAMETER_RESOURCES_EXCEEDED, Diameter error code: " + resultCode;
        else if (resultCode == 5007)
            errorMessage = "DIAMETER_CONTRADICTING_AVPS, Diameter error code: " + resultCode;
        else if (resultCode == 5008)
            errorMessage = "DIAMETER_AVP_NOT_ALLOWED, Diameter error code: " + resultCode;
        else if (resultCode == 5009)
            errorMessage = "DIAMETER_AVP_OCCURS_TOO_MANY_TIMES, Diameter error code: " + resultCode;
        else if (resultCode == 5010)
            errorMessage = "DIAMETER_NO_COMMON_APPLICATION, Diameter error code: " + resultCode;
        else if (resultCode == 5011)
            errorMessage = "DIAMETER_UNSUPPORTED_VERSION, Diameter error code: " + resultCode;
        else if (resultCode == 5012)
            errorMessage = "DIAMETER_UNABLE_TO_COMPLY, Diameter error code: " + resultCode;
        else if (resultCode == 5013)
            errorMessage = "DIAMETER_INVALID_BIT_IN_HEADER, Diameter error code: " + resultCode;
        else if (resultCode == 5014)
            errorMessage = "DIAMETER_INVALID_AVP_LENGTH, Diameter error code: " + resultCode;
        else if (resultCode == 5015)
            errorMessage = "DIAMETER_INVALID_MESSAGE_LENGTH, Diameter error code: " + resultCode;
        else if (resultCode == 5016)
            errorMessage = "DIAMETER_INVALID_AVP_BIT_COMBO, Diameter error code: " + resultCode;
        else if (resultCode == 5017)
            errorMessage = "DIAMETER_NO_COMMON_SECURITY, Diameter error code: " + resultCode;

        return errorMessage;
    }

    public static Integer getHttpServletResponseEquivalentCode(long resultCode) {
        int statusCode = HttpServletResponse.SC_OK;
        if (resultCode == 3001) // DIAMETER_COMMAND_UNSUPPORTED
            statusCode = HttpServletResponse.SC_NOT_IMPLEMENTED;
        else if (resultCode == 3002) // DIAMETER_UNABLE_TO_DELIVER
            statusCode = HttpServletResponse.SC_BAD_GATEWAY;
        else if (resultCode == 3003) // DIAMETER_REALM_NOT_SERVED
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        else if (resultCode == 3004) // DIAMETER_TOO_BUSY
            statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        else if (resultCode == 3005) // DIAMETER_LOOP_DETECTED
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 3006) // DIAMETER_REDIRECT_INDICATION
            statusCode = HttpServletResponse.SC_TEMPORARY_REDIRECT;
        else if (resultCode == 3007) // DIAMETER_APPLICATION_UNSUPPORTED
            statusCode = HttpServletResponse.SC_NOT_IMPLEMENTED;
        else if (resultCode == 3008) // DIAMETER_INVALID_HDR_BITS
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 3009) // DIAMETER_INVALID_AVP_BITS
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 3010) // DIAMETER_UNKNOWN_PEER
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        else if (resultCode == 4001) // DIAMETER_AUTHENTICATION_REJECTED
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        else if (resultCode == 4002) // DIAMETER_OUT_OF_SPACE
            statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        else if (resultCode == 4003) // DIAMETER_ELECTION_LOST
            statusCode = HttpServletResponse.SC_EXPECTATION_FAILED;
        else if (resultCode == 5001) // DIAMETER_AVP_UNSUPPORTED
            statusCode = HttpServletResponse.SC_NOT_IMPLEMENTED;
        else if (resultCode == 5002) // DIAMETER_UNKNOWN_SESSION_ID
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        else if (resultCode == 5003) // DIAMETER_AUTHORIZATION_REJECTED
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        else if (resultCode == 5004) // DIAMETER_INVALID_AVP_VALUE
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5005) // DIAMETER_MISSING_AVP
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5006) // DIAMETER_RESOURCES_EXCEEDED
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5007) // DIAMETER_CONTRADICTING_AVP
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5008) // DIAMETER_AVP_NOT_ALLOWED
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5009) // DIAMETER_AVP_OCCURS_TOO_MANY_TIMES
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5010) // DIAMETER_NO_COMMON_APPLICATION
            statusCode = HttpServletResponse.SC_NOT_IMPLEMENTED;
        else if (resultCode == 5011) // DIAMETER_UNSUPPORTED_VERSION
            statusCode = HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED;
        else if (resultCode == 5012) // DIAMETER_UNABLE_TO_COMPLY
            statusCode = HttpServletResponse.SC_NOT_IMPLEMENTED;
        else if (resultCode == 5013) // DIAMETER_INVALID_BIT_IN_HEADER
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5014) // DIAMETER_INVALID_AVP_LENGTH
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5015) // DIAMETER_INVALID_MESSAGE_LENGTH
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5016) // DIAMETER_INVALID_AVP_BIT_COMBO
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        else if (resultCode == 5017) // DIAMETER_NO_COMMON_SECURITY
            statusCode = HttpServletResponse.SC_BAD_REQUEST;

        return statusCode;
    }
}
