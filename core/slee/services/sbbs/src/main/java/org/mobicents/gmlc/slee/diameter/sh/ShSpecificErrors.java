package org.mobicents.gmlc.slee.diameter.sh;

import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ShSpecificErrors {

  static int DIAMETER_USER_DATA_NOT_AVAILABLE = 4100;
  static int DIAMETER_PRIOR_UPDATE_IN_PROGRESS = 4101;
  static int DIAMETER_ERROR_IDENTITIES_DONT_MATCH = 5002;
  static int DIAMETER_ERROR_TOO_MUCH_DATA = 5008;
  static int DIAMETER_ERROR_FEATURE_UNSUPPORTED = 5011;
  static int DIAMETER_ERROR_USER_DATA_NOT_RECOGNIZED = 5100;
  static int DIAMETER_ERROR_OPERATION_NOT_ALLOWED = 5101;
  static int DIAMETER_ERROR_USER_DATA_CANNOT_BE_READ = 5102;
  static int DIAMETER_ERROR_USER_DATA_CANNOT_BE_MODIFIED = 5103;
  static int DIAMETER_ERROR_USER_DATA_CANNOT_BE_NOTIFIED_ON_CHANGES = 5104;
  static int DIAMETER_ERROR_TRANSPARENT_DATA_OUT_OF_SYNC = 5105;
  static int DIAMETER_ERROR_SUBS_DATA_ABSENT = 5106;
  static int DIAMETER_ERROR_NO_SUBSCRIPTION_TO_DATA = 5107;
  static int DIAMETER_ERROR_DSAI_NOT_AVAILABLE = 5108;

  private static String errorMessage;

  public ShSpecificErrors() {
  }

  public static String diameterErrorMessage(long resultCode) {
    if (resultCode == DIAMETER_USER_DATA_NOT_AVAILABLE)
      errorMessage = "DIAMETER_USER_DATA_NOT_AVAILABLE, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_PRIOR_UPDATE_IN_PROGRESS)
      errorMessage = "DIAMETER_PRIOR_UPDATE_IN_PROGRESS, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_IDENTITIES_DONT_MATCH)
      errorMessage = "DIAMETER_ERROR_IDENTITIES_DONT_MATCH, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_TOO_MUCH_DATA)
      errorMessage = "DIAMETER_ERROR_TOO_MUCH_DATA, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_FEATURE_UNSUPPORTED)
      errorMessage = "DIAMETER_ERROR_FEATURE_UNSUPPORTED, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_USER_DATA_NOT_RECOGNIZED)
      errorMessage = "DIAMETER_ERROR_USER_DATA_NOT_RECOGNIZED, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_OPERATION_NOT_ALLOWED)
      errorMessage = "DIAMETER_ERROR_OPERATION_NOT_ALLOWED, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_USER_DATA_CANNOT_BE_READ)
      errorMessage = "DIAMETER_ERROR_USER_DATA_CANNOT_BE_READ, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_USER_DATA_CANNOT_BE_MODIFIED)
      errorMessage = "DIAMETER_ERROR_USER_DATA_CANNOT_BE_MODIFIED, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_USER_DATA_CANNOT_BE_NOTIFIED_ON_CHANGES)
      errorMessage = "DIAMETER_ERROR_USER_DATA_CANNOT_BE_NOTIFIED_ON_CHANGES, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_TRANSPARENT_DATA_OUT_OF_SYNC)
      errorMessage = "DIAMETER_ERROR_TRANSPARENT_DATA_OUT_OF_SYNC, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_SUBS_DATA_ABSENT)
      errorMessage = "DIAMETER_ERROR_SUBS_DATA_ABSENT, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_NO_SUBSCRIPTION_TO_DATA)
      errorMessage = "DIAMETER_ERROR_NO_SUBSCRIPTION_TO_DATA, Diameter error code: " + resultCode;
    if (resultCode == DIAMETER_ERROR_DSAI_NOT_AVAILABLE)
      errorMessage = "DIAMETER_ERROR_DSAI_NOT_AVAILABLE, Diameter error code: " + resultCode;

    return errorMessage;
  }

  public static Integer getHttpServletResponseEquivalentCode(long resultCode) {
    int statusCode = HttpServletResponse.SC_OK;
    if (resultCode == DIAMETER_USER_DATA_NOT_AVAILABLE)
      statusCode = HttpServletResponse.SC_NOT_FOUND;
    if (resultCode == DIAMETER_PRIOR_UPDATE_IN_PROGRESS)
      statusCode = HttpServletResponse.SC_SERVICE_UNAVAILABLE;
    if (resultCode == DIAMETER_ERROR_IDENTITIES_DONT_MATCH)
      statusCode = HttpServletResponse.SC_CONFLICT;
    if (resultCode == DIAMETER_ERROR_TOO_MUCH_DATA)
      statusCode = HttpServletResponse.SC_BAD_REQUEST;
    if (resultCode == DIAMETER_ERROR_FEATURE_UNSUPPORTED)
      statusCode = HttpServletResponse.SC_SERVICE_UNAVAILABLE;
    if (resultCode == DIAMETER_ERROR_USER_DATA_NOT_RECOGNIZED)
      statusCode = HttpServletResponse.SC_NOT_FOUND;
    if (resultCode == DIAMETER_ERROR_OPERATION_NOT_ALLOWED)
      statusCode = HttpServletResponse.SC_UNAUTHORIZED;
    if (resultCode == DIAMETER_ERROR_USER_DATA_CANNOT_BE_READ)
      statusCode = HttpServletResponse.SC_BAD_REQUEST;
    if (resultCode == DIAMETER_ERROR_USER_DATA_CANNOT_BE_MODIFIED)
      statusCode = HttpServletResponse.SC_FORBIDDEN;
    if (resultCode == DIAMETER_ERROR_USER_DATA_CANNOT_BE_NOTIFIED_ON_CHANGES)
      statusCode = HttpServletResponse.SC_FORBIDDEN;
    if (resultCode == DIAMETER_ERROR_TRANSPARENT_DATA_OUT_OF_SYNC)
      statusCode = HttpServletResponse.SC_PRECONDITION_FAILED;
    if (resultCode == DIAMETER_ERROR_SUBS_DATA_ABSENT)
      statusCode = HttpServletResponse.SC_NOT_FOUND;
    if (resultCode == DIAMETER_ERROR_NO_SUBSCRIPTION_TO_DATA)
      statusCode = HttpServletResponse.SC_PRECONDITION_FAILED;
    if (resultCode == DIAMETER_ERROR_DSAI_NOT_AVAILABLE)
      statusCode = HttpServletResponse.SC_PRECONDITION_FAILED;

    return statusCode;
  }
}
