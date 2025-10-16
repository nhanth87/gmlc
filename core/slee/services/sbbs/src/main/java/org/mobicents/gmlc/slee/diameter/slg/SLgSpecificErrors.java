package org.mobicents.gmlc.slee.diameter.slg;

import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLgSpecificErrors {

  public static final int DIAMETER_AVP_DELAYED_LOCATION_REPORTING_DATA = 2555;
  public static final int DIAMETER_ERROR_UNREACHABLE_USER = 4221;
  public static final int DIAMETER_ERROR_SUSPENDED_USER = 4222;
  public static final int DIAMETER_ERROR_DETACHED_USER = 4223;
  public static final int DIAMETER_ERROR_POSITIONING_DENIED = 4224;
  public static final int DIAMETER_ERROR_POSITIONING_FAILED = 4225;
  public static final int DIAMETER_ERROR_UNKNOWN_UNREACHABLE_LCS_CLIENT = 4226;
  public static final int DIAMETER_ERROR_USER_UNKNOWN = 5001;
  public static final int DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK = 5490;

  private static String errorMessage;

  public SLgSpecificErrors() {
  }

  public static String diameterErrorMessage(long resultCode) {
    if (resultCode == DIAMETER_AVP_DELAYED_LOCATION_REPORTING_DATA)
      errorMessage = "DIAMETER_AVP_DELAYED_LOCATION_REPORTING_DATA, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_UNREACHABLE_USER)
      errorMessage = "DIAMETER_ERROR_UNREACHABLE_USER, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_SUSPENDED_USER)
      errorMessage = "DIAMETER_ERROR_SUSPENDED_USER, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_DETACHED_USER)
      errorMessage = "DIAMETER_ERROR_DETACHED_USER, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_POSITIONING_DENIED)
      errorMessage = "DIAMETER_ERROR_POSITIONING_DENIED, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_POSITIONING_FAILED)
      errorMessage = "DIAMETER_ERROR_POSITIONING_FAILED, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_UNKNOWN_UNREACHABLE_LCS_CLIENT)
      errorMessage = "DIAMETER_ERROR_UNKNOWN_UNREACHABLE_LCS_CLIENT, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_USER_UNKNOWN)
      errorMessage = "DIAMETER_ERROR_UNKNOWN_UNREACHABLE, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK)
      errorMessage = "DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK, Diameter error code: " + resultCode;

    return errorMessage;
  }

  public static Integer getHttpServletResponseEquivalentCode(long resultCode) {
    int statusCode = HttpServletResponse.SC_OK;
    if (resultCode == DIAMETER_AVP_DELAYED_LOCATION_REPORTING_DATA)
      statusCode = HttpServletResponse.SC_PARTIAL_CONTENT;
    if (resultCode == DIAMETER_ERROR_UNREACHABLE_USER)
      statusCode = HttpServletResponse.SC_GONE;
    if (resultCode == DIAMETER_ERROR_SUSPENDED_USER)
      statusCode = HttpServletResponse.SC_GONE;
    if (resultCode == DIAMETER_ERROR_DETACHED_USER)
      statusCode = HttpServletResponse.SC_GONE;
    if (resultCode == DIAMETER_ERROR_POSITIONING_DENIED)
      statusCode = HttpServletResponse.SC_UNAUTHORIZED;
    if (resultCode == DIAMETER_ERROR_POSITIONING_FAILED)
      statusCode = HttpServletResponse.SC_EXPECTATION_FAILED;
    if (resultCode == DIAMETER_ERROR_UNKNOWN_UNREACHABLE_LCS_CLIENT)
      statusCode = HttpServletResponse.SC_GONE;
    if (resultCode == DIAMETER_ERROR_USER_UNKNOWN)
      statusCode = HttpServletResponse.SC_NOT_FOUND;
    if (resultCode == DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK)
      statusCode = HttpServletResponse.SC_UNAUTHORIZED;

    return statusCode;
  }
}
