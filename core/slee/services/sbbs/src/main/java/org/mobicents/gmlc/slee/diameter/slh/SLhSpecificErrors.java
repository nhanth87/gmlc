package org.mobicents.gmlc.slee.diameter.slh;

import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLhSpecificErrors {

  public static final int DIAMETER_ERROR_ABSENT_USER = 4201;
  public static final int DIAMETER_ERROR_USER_UNKNOWN = 5001;
  public static final int DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK = 5490;

  private static String errorMessage;

  public SLhSpecificErrors() {
  }

  public static String diameterErrorMessage(long resultCode) {
    if (resultCode == DIAMETER_ERROR_ABSENT_USER)
      errorMessage = "DIAMETER_ERROR_ABSENT_USER, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_USER_UNKNOWN)
      errorMessage = "DIAMETER_ERROR_USER_UNKNOWN, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK)
      errorMessage = "DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK, Diameter error code: " + resultCode;

    return errorMessage;
  }

  public static Integer getHttpServletResponseEquivalentCode(long resultCode) {
    int statusCode = HttpServletResponse.SC_OK;
    if (resultCode == DIAMETER_ERROR_ABSENT_USER)
      statusCode = HttpServletResponse.SC_GONE;
    else if (resultCode == DIAMETER_ERROR_USER_UNKNOWN)
      statusCode = HttpServletResponse.SC_NOT_FOUND;
    else if (resultCode == DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK)
      statusCode = HttpServletResponse.SC_UNAUTHORIZED;

    return statusCode;
  }
}
