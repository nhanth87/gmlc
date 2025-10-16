package org.mobicents.gmlc.slee;

import org.mobicents.gmlc.slee.primitives.Polygon;

import java.io.Serializable;

/**
 * Inner class for MLP response parameters
 */
class MLPResponseParams implements Serializable {

   /*********************/
   /*** MLP Response ***/
   /*******************/
   String mlpMsisdn;
   Integer mlpMcc, mlpMnc, mlpLac, mlpCi, mlpSac, mlpTac, mlpRac, mlpNrTac;
   Long mlpEci, mlpNci;
   String mlpVlrNo, mlpMscNo, mlpMmeName, mlpSgsnName;
   String mlpState;
   Integer mlpAge;
   Double x;
   Double y;
   String mlpTypeOfShape;
   Double radius;
   Polygon mlpPolygon;
   Integer mlpNumberOfPoints;
   Double mlpUncertainty, mlpUncertaintySemiMajorAxis, mlpUncertaintySemiMinorAxis, mlpAngleOfMajorAxis,
           mlpUncertaintyAltitude, mlpUncertaintyInnerRadius, mlpOffsetAngle, mlpIncludedAngle;
   Integer mlpConfidence, mlpAltitude, mlpInnerRadius, numberOfPoints;
   Integer mlpAgeOfLocationEstimate, mlpAccuracyFulfilmentIndicator;
   Integer mlpTargetHorizontalSpeed, mlpTargetVerticalSpeed;
   String mlpVelocityType;
   String mlpImei, mlpImsi, mlpLmsi;
   String mlpCivicAddress;
   Long mlpBarometricPressure;
   Integer mlpTransId;
   Integer mlpLcsRefNumber;
   Integer mlpRatType;
}
