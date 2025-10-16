package org.mobicents.gmlc.slee.http;

import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.diameter.AVPHandler;
import org.mobicents.gmlc.slee.diameter.slg.SLgLrrAvpValues;
import org.mobicents.gmlc.slee.primitives.CivicAddressElements;
import org.mobicents.gmlc.slee.primitives.CivicAddressXmlReader;
import org.mobicents.gmlc.slee.primitives.EUTRANPositioningData;
import org.mobicents.gmlc.slee.primitives.EUTRANPositioningDataImpl;
import org.mobicents.gmlc.slee.primitives.EllipsoidPoint;
import org.mobicents.gmlc.slee.primitives.PolygonImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranAdditionalPositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EUtranCgi;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.primitives.CellGlobalIdOrServiceAreaIdFixedLengthImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.EUtranCgiImpl;

import javax.xml.bind.DatatypeConverter;
import java.awt.geom.Point2D;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mobicents.gmlc.slee.gis.GeographicHelper.polygonCentroid;
import static org.mobicents.gmlc.slee.http.JsonWriter.bytesToHexString;
import static org.mobicents.gmlc.slee.http.JsonWriter.write3gppAaaServerName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAccuracyFulfilmentIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAgeOfLocationEstimate;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAltitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAmfInstanceId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAngleOfMajorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeBarometricPressure;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeBearing;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellPortionId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCivicAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeClientReferenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeConfidence;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeDeferredLocationType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeENBId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranAddPositioningMethod;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranEci;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranGnssPositioningGnssId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranGnssPositioningMethod;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranGnssPositioningUsage;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranPositioningMethod;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranPositioningUsage;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranGanssPositioningGanssId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranGanssPositioningMethod;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranGanssPositioningUsage;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranPositioningMethod;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranPositioningUsage;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGmlcAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeHorizontalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImei;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeIncludedAngle;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeInnerRadius;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSCapabilitySets;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSEPSClientFormatIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSEPSClientNameString;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSServiceTypeID;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLRRTerminationCause;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLac;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLatitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLcsPseudonymIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLcsQoSClass;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLcsReferenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLocationEvent;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLongitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLrrFlags;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeRealm;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMscNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNumberOfPoints;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOffsetAngle;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeReportingAmount;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeReportingInterval;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSequenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeServiceAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnRealm;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTypeOfShape;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertainty;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyAltitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyHorizontalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyInnerRadius;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintySemiMajorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintySemiMinorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyVerticalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranAddPositioningMethod;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranAddPositioningPosId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranAddPositioningUsage;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranGanssPositioningGanssId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranGanssPositioningMethod;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranGanssPositioningUsage;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranPositioningMethod;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranPositioningUsage;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVelocityType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVerticalSpeed;
import static org.mobicents.gmlc.slee.utils.TBCDUtil.toTBCDString;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class LrrRequestJsonBuilder {

    protected static final Logger logger = Logger.getLogger(LrrRequestJsonBuilder.class);

    public LrrRequestJsonBuilder() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param lrr                   LRR Request values gathered from LRR request event
     * @param clientReferenceNumber Reference Number gathered from the originating HTTP request sent by the GMLC Client
     */
    public static String buildJsonReportForLRR(SLgLrrAvpValues lrr, Integer clientReferenceNumber) {

        int lcsReferenceNumber, clientRefNum, locationEvent, lcsFormatIndicator, altitude, innerRadius, confidence, numberOfPoints,
                accuracyFulfilmentIndicator, lcsQoSClassValue, cgiMcc, cgiMnc, cgiLac, ci, saiMcc, saiMnc, saiLac, sac,
                ecgiMcc, ecgiMnc, ecgiCi, horizontalSpeed, bearing, verticalSpeed, uncertaintyHorizontalSpeed, uncertaintyVerticalSpeed, lcsPseudonymIndicator;
        altitude = innerRadius = confidence = numberOfPoints = cgiMcc = cgiMnc = cgiLac = ci = saiMcc = saiMnc = saiLac = sac =
                ecgiMcc = ecgiMnc = ecgiCi = -1;
        long lcsServiceTypeId, ageOfLocationEstimate, eci, eNBId, cellPortionId, reportingAmount, reportingInterval, deferredLocationType, mtLrTerminationCause,
            lcsCapabilitySets, dlrTerminationCause, mtLrLcsCapabilitySets, dlrLcsCapabilitySets, barometricPressure;
        eci = eNBId = cellPortionId = lcsCapabilitySets = dlrLcsCapabilitySets = mtLrLcsCapabilitySets = -1;
        String msisdn, imsi, imei, lcsEPSClientName, typeOfShape, velocityType,
            mmeName, mmeRealm, sgsnName, sgsnRealm, sgsnNumber, aaaServerName, mscNumber, gmlcAddress,
            mtLrMmeName, mtLrMmeRealm, mtLrSgsnName, mtLrSgsnRealm, mLlrSgsnNumber, mtLr3gppAAAServerName, mtLrMscNumber, mtLrGmlcAddress,
            dlrMmeName, dlrMmeRealm, dlrSgsnName, dlrSgsnRealm, dlrSgsnNumber, dlr3gppAAAServerName, dlrMscNumber, dlrGmlcAddress, civicAddress, amfInstanceId;
        mmeName = mmeRealm = sgsnName = sgsnRealm = sgsnNumber = aaaServerName = mscNumber = gmlcAddress =
                mtLrMmeName = mtLrMmeRealm = mtLrSgsnName = mtLrSgsnRealm = mLlrSgsnNumber = mtLr3gppAAAServerName = mtLrMscNumber = mtLrGmlcAddress =
                    dlrMmeName = dlrMmeRealm = dlrSgsnName = dlrSgsnRealm = dlrSgsnNumber = dlr3gppAAAServerName = dlrMscNumber = dlrGmlcAddress = null;
        Double latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, uncertaintyAltitude, uncertaintyInnerRadius,
            angleOfMajorAxis, offsetAngle, includedAngle;
        latitude = longitude = uncertainty = uncertaintySemiMajorAxis = uncertaintySemiMinorAxis = uncertaintyAltitude = uncertaintyInnerRadius =
            angleOfMajorAxis = offsetAngle = includedAngle = null;
        PolygonImpl polygon = null;
        EllipsoidPoint[] polygonEllipsoidPoints;
        Double[][] polygonArray;

        JsonObject lrrJsonObject = new JsonObject();
        writeNetwork("LTE", lrrJsonObject);
        writeProtocol("Diameter SLg (ELP)", lrrJsonObject);
        writeOperation("LRR", lrrJsonObject);
        writeOperationResult("SUCCESS", lrrJsonObject);

        if (lrr != null) {

            if (clientReferenceNumber != null) {
                clientRefNum = clientReferenceNumber;
                writeClientReferenceNumber(clientRefNum, lrrJsonObject);
            }

            /*** Location-Event AVP ***/
            if (lrr.getLocationEvent() != null) {
                locationEvent = lrr.getLocationEvent().getValue();
                // Write Location-Event from SLg LRR
                writeLocationEvent(locationEvent, lrrJsonObject);
            }

            /*** LCS-EPS-Client-Name AVP ***/
            if (lrr.getLcsEPSClientName() != null) {
                JsonObject lcsEPSClientNameJsonObject = new JsonObject();
                if (lrr.getLcsEPSClientName().getLCSNameString() != null) {
                    lcsEPSClientName = lrr.getLcsEPSClientName().getLCSNameString();
                    writeLCSEPSClientNameString(lcsEPSClientName, lcsEPSClientNameJsonObject);
                }
                if (lrr.getLcsEPSClientName().getLCSFormatIndicator() != null) {
                    lcsFormatIndicator = lrr.getLcsEPSClientName().getLCSFormatIndicator().getValue();
                    writeLCSEPSClientFormatIndicator(lcsFormatIndicator, lcsEPSClientNameJsonObject);
                }
                // Write LCS-EPS-Client-Name from SLg LRR
                lrrJsonObject.add("LcsEPSClientName", lcsEPSClientNameJsonObject);
            }

            /*** User-Name AVP ***/
            if (lrr.getUserName() != null) {
                imsi = new String(AVPHandler.userName2Imsi(lrr.getUserName()).getData().getBytes());
                // Write IMSI from SLg LRR
                writeImsi(imsi, lrrJsonObject);
            }

            /*** MSISDN AVP ***/
            if (lrr.getMsisdn() != null) {
                msisdn = AVPHandler.tbcd2IsdnAddressString(lrr.getMsisdn()).getAddress();
                // Write MSISDN from SLg LRR
                writeMsisdn(msisdn, lrrJsonObject);
            }

            /*** IMEI AVP ***/
            if (lrr.getImei() != null) {
                imei = AVPHandler.string2MapImei(lrr.getImei()).getIMEI();
                // Write IMEI from SLg LRR
                writeImei(imei, lrrJsonObject);
            }

            /*** Location-Estimate AVP ***/
            if (lrr.getLocationEstimate() != null) {
                JsonObject locationEstimateJsonObject = new JsonObject();
                ExtGeographicalInformation lteLocationEstimate = AVPHandler.lteLocationEstimate2ExtGeographicalInformation(lrr.getLocationEstimate());
                typeOfShape = lteLocationEstimate.getTypeOfShape().name();
                if (lteLocationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    latitude = lteLocationEstimate.getLatitude();
                    longitude = lteLocationEstimate.getLongitude();
                    uncertainty = lteLocationEstimate.getUncertainty();
                    uncertaintySemiMajorAxis = lteLocationEstimate.getUncertaintySemiMajorAxis();
                    uncertaintySemiMinorAxis = lteLocationEstimate.getUncertaintySemiMinorAxis();
                    angleOfMajorAxis = lteLocationEstimate.getAngleOfMajorAxis();
                    altitude = lteLocationEstimate.getAltitude();
                    uncertaintyAltitude = lteLocationEstimate.getUncertaintyAltitude();
                    innerRadius = lteLocationEstimate.getInnerRadius();
                    uncertaintyInnerRadius = lteLocationEstimate.getUncertaintyRadius();
                    offsetAngle = lteLocationEstimate.getOffsetAngle();
                    includedAngle = lteLocationEstimate.getIncludedAngle();
                    confidence = lteLocationEstimate.getConfidence();
                } else {
                    polygon = new PolygonImpl(lteLocationEstimate.getData());
                    numberOfPoints = polygon.getNumberOfPoints();
                    polygonEllipsoidPoints = new EllipsoidPoint[numberOfPoints];
                    for (int point=0; point<numberOfPoints; point++) {
                        polygonEllipsoidPoints[point] = polygon.getEllipsoidPoint(point);
                    }
                    try {
                        polygon.setData(polygonEllipsoidPoints);
                    } catch (MAPException e) {
                        logger.error(e.getMessage());
                    }
                }
                // Write Location-Estimate AVP values from LRR
                writeTypeOfShape(typeOfShape, locationEstimateJsonObject);
                if (typeOfShape.equalsIgnoreCase("EllipsoidPoint")) {
                    writeLatitude(latitude, locationEstimateJsonObject);
                    writeLongitude(longitude, locationEstimateJsonObject);
                } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyCircle")) {
                    writeLatitude(latitude, locationEstimateJsonObject);
                    writeLongitude(longitude, locationEstimateJsonObject);
                    writeUncertainty(uncertainty, locationEstimateJsonObject);
                } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyEllipse")) {
                    writeLatitude(latitude, locationEstimateJsonObject);
                    writeLongitude(longitude, locationEstimateJsonObject);
                    writeUncertaintySemiMajorAxis(uncertaintySemiMajorAxis, locationEstimateJsonObject);
                    writeUncertaintySemiMinorAxis(uncertaintySemiMinorAxis, locationEstimateJsonObject);
                    writeAngleOfMajorAxis(angleOfMajorAxis, locationEstimateJsonObject);
                    writeConfidence(confidence, locationEstimateJsonObject);
                } else if (typeOfShape.equalsIgnoreCase("Polygon")) {
                    JsonObject lrrLocationEstimatePolygonPointsJsonObject = new JsonObject();
                    polygonArray = new Double[numberOfPoints][numberOfPoints];
                    Double lat, lon;
                    if (numberOfPoints > 2 && numberOfPoints <= 15) {
                        writeNumberOfPoints(numberOfPoints, locationEstimateJsonObject);
                        for (int index=0; index<numberOfPoints; index++) {
                            lat = polygon.getEllipsoidPoint(index).getLatitude();
                            lon = polygon.getEllipsoidPoint(index).getLongitude();
                            polygonArray[index][0] = lat;
                            polygonArray[index][1] = lon;
                            String polygonPoint = "polygonPoint"+(index+1);
                            writeLatitude(lat, lrrLocationEstimatePolygonPointsJsonObject);
                            writeLongitude(lon, lrrLocationEstimatePolygonPointsJsonObject);
                            locationEstimateJsonObject.add(polygonPoint, lrrLocationEstimatePolygonPointsJsonObject);
                            lrrLocationEstimatePolygonPointsJsonObject = new JsonObject();
                        }
                        List<Point2D> listOfPoints = new ArrayList<>();
                        Point2D[] point2D = new Point2D.Double[polygonArray.length];
                        Point2D polygonPoint;
                        for (int point = 0; point < polygonArray.length; point++) {
                            lat = polygonArray[point][0];
                            lon = polygonArray[point][1];
                            polygonPoint = new Point2D.Double(lat,lon);
                            listOfPoints.add(polygonPoint);
                            point2D[point] = listOfPoints.get(point);
                        }
                        polygonCentroid(point2D);
                        JsonObject lrrLocationEstimatePolygonCentroidObject = new JsonObject();
                        writeLatitude(polygonCentroid(point2D).getX(), lrrLocationEstimatePolygonCentroidObject);
                        writeLongitude(polygonCentroid(point2D).getY(), lrrLocationEstimatePolygonCentroidObject);
                        locationEstimateJsonObject.add("polygonCentroid", lrrLocationEstimatePolygonCentroidObject);
                    }
                } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitude")) {
                    writeLatitude(latitude, locationEstimateJsonObject);
                    writeLongitude(longitude, locationEstimateJsonObject);
                    writeAltitude(altitude, locationEstimateJsonObject);
                } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitudeAndUncertaintyEllipsoid")) {
                    writeLatitude(latitude, locationEstimateJsonObject);
                    writeLongitude(longitude, locationEstimateJsonObject);
                    writeAltitude(altitude, locationEstimateJsonObject);
                    writeUncertaintySemiMajorAxis(uncertaintySemiMajorAxis, locationEstimateJsonObject);
                    writeUncertaintySemiMinorAxis(uncertaintySemiMinorAxis, locationEstimateJsonObject);
                    writeAngleOfMajorAxis(angleOfMajorAxis, locationEstimateJsonObject);
                    writeUncertaintyAltitude(uncertaintyAltitude, locationEstimateJsonObject);
                    writeConfidence(confidence, locationEstimateJsonObject);
                } else if (typeOfShape.equalsIgnoreCase("EllipsoidArc")) {
                    writeLatitude(latitude, locationEstimateJsonObject);
                    writeLongitude(longitude, locationEstimateJsonObject);
                    writeInnerRadius(innerRadius, locationEstimateJsonObject);
                    writeUncertaintyInnerRadius(uncertaintyInnerRadius, locationEstimateJsonObject);
                    writeOffsetAngle(offsetAngle, locationEstimateJsonObject);
                    writeIncludedAngle(includedAngle, locationEstimateJsonObject);
                    writeConfidence(confidence, locationEstimateJsonObject);
                }
                // Write Location-Estimate from SLg LRR
                lrrJsonObject.add("LocationEstimate", locationEstimateJsonObject);
            }

            /*** Accuracy-Fulfilment-Indicator AVP ***/
            if (lrr.getAccuracyFulfilmentIndicator() != null) {
                accuracyFulfilmentIndicator = AVPHandler.diamAccFulInd2MapAccFulInd(lrr.getAccuracyFulfilmentIndicator()).getIndicator();
                // Write Accuracy-Fulfilment-Indicator from SLg LRR
                writeAccuracyFulfilmentIndicator(accuracyFulfilmentIndicator, lrrJsonObject);
            }

            /*** Age-Of-Location-Estimate AVP ***/
            if (lrr.getAgeOfLocationEstimate() != null) {
                ageOfLocationEstimate = lrr.getAgeOfLocationEstimate();
                // Write Age-Of-Location-Estimate from SLg LRR
                writeAgeOfLocationEstimate(ageOfLocationEstimate, lrrJsonObject);
            }

            /*** Velocity Estimate AVP ***/
            if (lrr.getVelocityEstimate() != null) {
                VelocityEstimate lteVelocityEstimate = AVPHandler.lteVelocityEstimate2MapVelocityEstimate(lrr.getVelocityEstimate());
                horizontalSpeed = lteVelocityEstimate.getHorizontalSpeed();
                bearing = lteVelocityEstimate.getBearing();
                verticalSpeed = lteVelocityEstimate.getVerticalSpeed();
                uncertaintyHorizontalSpeed = lteVelocityEstimate.getUncertaintyHorizontalSpeed();
                uncertaintyVerticalSpeed = lteVelocityEstimate.getUncertaintyVerticalSpeed();
                velocityType = lteVelocityEstimate.getVelocityType().name();
                // Write Velocity Estimate values from SLg LRR
                JsonObject velocityEstimateJsonObject = new JsonObject();
                writeHorizontalSpeed(horizontalSpeed, velocityEstimateJsonObject);
                writeBearing(bearing, velocityEstimateJsonObject);
                writeVerticalSpeed(verticalSpeed, velocityEstimateJsonObject);
                writeUncertaintyHorizontalSpeed(uncertaintyHorizontalSpeed, velocityEstimateJsonObject);
                writeUncertaintyVerticalSpeed(uncertaintyVerticalSpeed, velocityEstimateJsonObject);
                writeVelocityType(velocityType, velocityEstimateJsonObject);
                lrrJsonObject.add("VelocityEstimate", velocityEstimateJsonObject);
            }

            /*** EUTRAN-Positioning-Data AVP ***/
            if (lrr.getEUtranPositioningData() != null) {
                try {
                    EUTRANPositioningData eutranPositioningData = new EUTRANPositioningDataImpl(lrr.getEUtranPositioningData());
                    if (eutranPositioningData.getPositioningDataSet() != null) {
                        HashMap<String, Integer> methodsAndUsage = eutranPositioningData.getPositioningDataMethodsAndUsage(eutranPositioningData.getPositioningDataSet());
                        JsonObject lrrEUtranPosInfoDataSetJsonObject = new JsonObject();
                        JsonObject lrrEUtranPosInfoJsonObject = new JsonObject();
                        JsonObject[] lrrEUtranPosInfoMethodAndUsage = new JsonObject[methodsAndUsage.size()];
                        int itemIndex = 0;
                        for (HashMap.Entry<String, Integer> item : methodsAndUsage.entrySet()) {
                            String property = "Item-" + itemIndex;
                            String method = item.getKey();
                            Integer usage = item.getValue();
                            lrrEUtranPosInfoMethodAndUsage[itemIndex] = new JsonObject();
                            lrrEUtranPosInfoDataSetJsonObject.add(property, lrrEUtranPosInfoMethodAndUsage[itemIndex]);
                            writeEUtranPositioningMethod(method, lrrEUtranPosInfoMethodAndUsage[itemIndex]);
                            writeEUtranPositioningUsage(usage, lrrEUtranPosInfoMethodAndUsage[itemIndex]);
                            itemIndex++;
                        }
                        lrrEUtranPosInfoJsonObject.add("PositioningDataSet", lrrEUtranPosInfoDataSetJsonObject);
                        // Write EUTRAN-Positioning-Data AVP Positioning Data Set values from SLg PLA
                        lrrJsonObject.add("EUtranPositioningData", lrrEUtranPosInfoJsonObject);
                    }
                    if (eutranPositioningData.getGNSSPositioningDataSet() != null) {
                        Multimap<String, String> methodsAndGanssIds = eutranPositioningData.getGNSSPositioningMethodsAndGNSSIds(eutranPositioningData.getGNSSPositioningDataSet());
                        JsonObject lrrEUtranGnssPosInfoDataSetJsonObject = new JsonObject();
                        JsonObject lrrEUtranGnssInfoJsonObject = new JsonObject();
                        JsonObject[] lrrEUtranGnssPosInfoMethodIdUsage  = new JsonObject[methodsAndGanssIds.size()];
                        String method, id;
                        int itemIndex = 0, usage;
                        for (Map.Entry<String, String> entry : methodsAndGanssIds.entries()) {
                            method = entry.getKey();
                            id = entry.getValue();
                            usage = eutranPositioningData.getUsageCode(eutranPositioningData.getGNSSPositioningDataSet(), itemIndex);
                            String property = "Item-" + itemIndex;
                            lrrEUtranGnssPosInfoMethodIdUsage[itemIndex] = new JsonObject();
                            lrrEUtranGnssPosInfoDataSetJsonObject.add(property, lrrEUtranGnssPosInfoMethodIdUsage[itemIndex]);
                            writeEUtranGnssPositioningMethod(method, lrrEUtranGnssPosInfoMethodIdUsage[itemIndex]);
                            writeEUtranGnssPositioningGnssId(id, lrrEUtranGnssPosInfoMethodIdUsage[itemIndex]);
                            writeEUtranGnssPositioningUsage(usage, lrrEUtranGnssPosInfoMethodIdUsage[itemIndex]);
                            itemIndex++;
                        }
                        lrrEUtranGnssInfoJsonObject.add("GnssPositioningDataSet", lrrEUtranGnssPosInfoDataSetJsonObject);
                        // Write EUTRAN-Positioning-Data AVP GNSS Positioning Data Set values from SLg PLA
                        lrrJsonObject.add("EUtranPositioningData", lrrEUtranGnssInfoJsonObject);
                    }
                    if (eutranPositioningData.getAdditionalPositioningDataSet() != null) {
                        Multimap<String, String> methodsAndAddPosIds = eutranPositioningData.getEUtranAdditionalPositioningMethodsAndIds(eutranPositioningData.getAdditionalPositioningDataSet());
                        JsonObject lrrEUtranAddPosInfoDataSetJsonObject = new JsonObject();
                        JsonObject lrrEUtranAddInfoJsonObject = new JsonObject();
                        JsonObject[] lrrEUtranAddPosInfoMethodAndId = new JsonObject[methodsAndAddPosIds.size()];
                        String method, id;
                        int itemIndex = 0, usage;
                        for (Map.Entry<String, String> entry : methodsAndAddPosIds.entries()) {
                            method = entry.getKey();
                            id = entry.getValue();
                            usage = eutranPositioningData.getUsageCode(eutranPositioningData.getAdditionalPositioningDataSet(), itemIndex);
                            String property = "Item-" + itemIndex;
                            lrrEUtranAddPosInfoMethodAndId[itemIndex] = new JsonObject();
                            lrrEUtranAddPosInfoDataSetJsonObject.add(property, lrrEUtranAddPosInfoMethodAndId[itemIndex]);
                            writeEUtranAddPositioningMethod(method, lrrEUtranAddPosInfoMethodAndId[itemIndex]);
                            writeUtranAddPositioningPosId(id, lrrEUtranAddPosInfoMethodAndId[itemIndex]);
                            writeUtranAddPositioningUsage(usage, lrrEUtranAddPosInfoMethodAndId[itemIndex]);
                            itemIndex++;
                        }
                        lrrEUtranAddInfoJsonObject.add("AdditionalPositioningDataSet", lrrEUtranAddPosInfoDataSetJsonObject);
                        // Write EUTRAN-Positioning-Data AVP Additional Positioning Data Set values from SLg PLA
                        lrrJsonObject.add("EUtranPositioningData", lrrEUtranAddInfoJsonObject);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            /*** ECGI AVP ***/
            if (lrr.getEcgi() != null) {
                try {
                    EUtranCgi eutranCgi = new EUtranCgiImpl(lrr.getEcgi());
                    ecgiMcc = eutranCgi.getMCC();
                    ecgiMnc = eutranCgi.getMNC();
                    eci = eutranCgi.getEci();
                    eNBId = eutranCgi.getENodeBId();
                    ecgiCi = eutranCgi.getCi();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                // Write ECGI or ESMLC Cell Info values from SLg LRR
                JsonObject eCGIorESMLCCellInfoJsonObject = new JsonObject();
                writeMcc(ecgiMcc, eCGIorESMLCCellInfoJsonObject);
                writeMnc(ecgiMnc, eCGIorESMLCCellInfoJsonObject);
                writeEUtranEci(eci, eCGIorESMLCCellInfoJsonObject);
                writeENBId(eNBId, eCGIorESMLCCellInfoJsonObject);
                writeCellId(ecgiCi, eCGIorESMLCCellInfoJsonObject);
                lrrJsonObject.add("ECGI", eCGIorESMLCCellInfoJsonObject);
            }

            /*** GERAN-Positioning-Info AVP ***/
            if (lrr.getGeranPositioningInfoAvp() != null) {
                if (lrr.getGeranPositioningInfoAvp().getGERANPositioningData() != null) {
                    try {
                        PositioningDataInformation geranPositioningDataInformation = AVPHandler.lteGeranPosDataInfo2MapGeranPosDataInfo(lrr.getGeranPositioningInfoAvp().getGERANPositioningData());
                        HashMap<String, Integer> methodsAndUsage = geranPositioningDataInformation.getPositioningDataSet();
                        JsonObject lrrGeranPosInfoDataSetJsonObject = new JsonObject();
                        JsonObject lrrGeranPosInfoJsonObject = new JsonObject();
                        JsonObject[] lrrGeranPosInfoMethodAndUsage = new JsonObject[methodsAndUsage.size()];
                        int itemIndex = 0;
                        for (HashMap.Entry<String, Integer> item : methodsAndUsage.entrySet()) {
                            String property = "Item-" + itemIndex;
                            String method = item.getKey();
                            Integer usage = item.getValue();
                            lrrGeranPosInfoMethodAndUsage[itemIndex] = new JsonObject();
                            lrrGeranPosInfoDataSetJsonObject.add(property, lrrGeranPosInfoMethodAndUsage[itemIndex]);
                            writeGeranPositioningMethod(method, lrrGeranPosInfoMethodAndUsage[itemIndex]);
                            writeGeranPositioningUsage(usage, lrrGeranPosInfoMethodAndUsage[itemIndex]);
                            itemIndex++;
                        }
                        lrrGeranPosInfoJsonObject.add("PositioningDataSet", lrrGeranPosInfoDataSetJsonObject);
                        // Write GERAN Positioning Info values from SLg LRR
                        lrrJsonObject.add("GeranPositioningData", lrrGeranPosInfoJsonObject);
                    } catch (MAPException e) {
                        logger.error(e.getMessage());
                    }
                }
                if (lrr.getGeranPositioningInfoAvp().getGERANGANSSPositioningData() != null) {
                    try {
                        GeranGANSSpositioningData geranGANSSpositioningData = AVPHandler.lteGeranGanssPosDataInfo2MapGeranGanssPosDataInfo(lrr.getGeranPositioningInfoAvp().getGERANGANSSPositioningData());
                        Multimap<String, String> methodsAndGanssIds = geranGANSSpositioningData.getGeranGANSSPositioningMethodsAndGANSSIds();
                        JsonObject lrrGeranGanssPosInfoDataSetJsonObject = new JsonObject();
                        JsonObject lrrGeranGanssInfoJsonObject = new JsonObject();
                        JsonObject[] lrrGeranGanssPosInfoMethodIdUsage = new JsonObject[methodsAndGanssIds.size()];
                        String method, id;
                        int itemIndex = 0, usage;
                        for (Map.Entry<String, String> item : methodsAndGanssIds.entries()) {
                            method = item.getKey();
                            id = item.getValue();
                            usage = geranGANSSpositioningData.getUsageCode(geranGANSSpositioningData.getData(), itemIndex+1);
                            String property = "Item-" + itemIndex;
                            lrrGeranGanssPosInfoMethodIdUsage[itemIndex] = new JsonObject();
                            lrrGeranGanssPosInfoDataSetJsonObject.add(property, lrrGeranGanssPosInfoMethodIdUsage[itemIndex]);
                            writeGeranGanssPositioningMethod(method, lrrGeranGanssPosInfoMethodIdUsage[itemIndex]);
                            writeGeranGanssPositioningGanssId(id, lrrGeranGanssPosInfoMethodIdUsage[itemIndex]);
                            writeGeranGanssPositioningUsage(usage, lrrGeranGanssPosInfoMethodIdUsage[itemIndex]);
                            itemIndex++;
                        }
                        lrrGeranGanssInfoJsonObject.add("GanssPositioningDataSet", lrrGeranGanssPosInfoDataSetJsonObject);
                        // Write GERAN GANSS Positioning Info values from SLg LRR
                        lrrJsonObject.add("GeranGANSSPositioningData", lrrGeranGanssInfoJsonObject);
                    } catch (MAPException e) {
                        logger.error(e.getMessage());
                    }
                }
            }

            /*** Cell-Global-Identity AVP ***/
            if (lrr.getCellGlobalIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength cellGlobalId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(lrr.getCellGlobalIdentity());
                try {
                    cgiMcc = cellGlobalId.getMCC();
                    cgiMnc = cellGlobalId.getMNC();
                    cgiLac = cellGlobalId.getLac();
                    ci = cellGlobalId.getCellIdOrServiceAreaCode();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                // Write CGI values from SLg LRR
                JsonObject cellGlobalIdentityJsonObject = new JsonObject();
                writeMcc(cgiMcc, cellGlobalIdentityJsonObject);
                writeMnc(cgiMnc, cellGlobalIdentityJsonObject);
                writeLac(cgiLac, cellGlobalIdentityJsonObject);
                writeCellId(ci, cellGlobalIdentityJsonObject);
                // Write Cell-Global-Identity from SLg LRR
                lrrJsonObject.add("CGI", cellGlobalIdentityJsonObject);
            }

            /*** UTRAN-Positioning-Info AVP ***/
            if (lrr.getUtranPositioningInfoAvp() != null) {
                if (lrr.getUtranPositioningInfoAvp().getUTRANPositioningData() != null) {
                    try {
                        UtranPositioningDataInfo utranPositioningDataInfo = AVPHandler.lteUtranPosData2MapUtranPosDataInfo(lrr.getUtranPositioningInfoAvp().getUTRANPositioningData());
                        HashMap<String, Integer> methodsAndUsage = utranPositioningDataInfo.getUtranPositioningDataSet();
                        JsonObject lrrUtranPosInfoDataSetJsonObject = new JsonObject();
                        JsonObject lrrUtranPosInfoJsonObject = new JsonObject();
                        JsonObject[] lrrUtranPosInfoMethodAndUsage = new JsonObject[methodsAndUsage.size()];
                        int itemIndex = 0;
                        for (HashMap.Entry<String, Integer> item : methodsAndUsage.entrySet()) {
                            String property = "Item-" + itemIndex;
                            String method = item.getKey();
                            Integer usage = item.getValue();
                            lrrUtranPosInfoMethodAndUsage[itemIndex] = new JsonObject();
                            lrrUtranPosInfoDataSetJsonObject.add(property, lrrUtranPosInfoMethodAndUsage[itemIndex]);
                            writeUtranPositioningMethod(method, lrrUtranPosInfoMethodAndUsage[itemIndex]);
                            writeUtranPositioningUsage(usage, lrrUtranPosInfoMethodAndUsage[itemIndex]);
                            itemIndex++;
                        }
                        lrrUtranPosInfoJsonObject.add("PositioningDataSet", lrrUtranPosInfoDataSetJsonObject);
                        // Write GERAN Positioning Info values from SLg LRR
                        lrrJsonObject.add("UtranPositioningData", lrrUtranPosInfoJsonObject);
                    } catch (MAPException e) {
                        logger.error(e.getMessage());
                    }
                }
                if (lrr.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData() != null) {
                    try {
                        UtranGANSSpositioningData utranGANSSpositioningData = AVPHandler.lteUtranGanssPosData2MapUtranGanssPosDataInfo(lrr.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData());
                        Multimap<String, String> methodsAndGanssIds = utranGANSSpositioningData.getUtranGANSSPositioningMethodsAndGANSSIds();
                        JsonObject lrrUtranGanssPosInfoDataSetJsonObject = new JsonObject();
                        JsonObject lrrUtranGanssInfoJsonObject = new JsonObject();
                        JsonObject[] lrrUtranGanssPosInfoMethodIdUsage  = new JsonObject[methodsAndGanssIds.size()];
                        String method, id;
                        int itemIndex = 0, usage;
                        for (Map.Entry<String, String> item : methodsAndGanssIds.entries()) {
                            method = item.getKey();
                            id = item.getValue();
                            usage = utranGANSSpositioningData.getUsageCode(utranGANSSpositioningData.getData(), itemIndex);
                            String property = "Item-" + itemIndex;
                            lrrUtranGanssPosInfoMethodIdUsage[itemIndex] = new JsonObject();
                            lrrUtranGanssPosInfoDataSetJsonObject.add(property, lrrUtranGanssPosInfoMethodIdUsage[itemIndex]);
                            writeUtranGanssPositioningMethod(method, lrrUtranGanssPosInfoMethodIdUsage[itemIndex]);
                            writeUtranGanssPositioningGanssId(id, lrrUtranGanssPosInfoMethodIdUsage[itemIndex]);
                            writeUtranGanssPositioningUsage(usage, lrrUtranGanssPosInfoMethodIdUsage[itemIndex]);
                            itemIndex++;
                        }
                        lrrUtranGanssInfoJsonObject.add("GanssPositioningDataSet", lrrUtranGanssPosInfoDataSetJsonObject);
                        // Write UTRAN GANSS Positioning Info values from SLg LRR
                        lrrJsonObject.add("UtranGANSSPositioningData", lrrUtranGanssInfoJsonObject);
                    } catch (MAPException e) {
                        logger.error(e.getMessage());
                    }
                }
                if (lrr.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData() != null) {
                    try {
                        UtranAdditionalPositioningData utranAdditionalPositioningData = AVPHandler.lteUtranAddPosData2MapUtranAdditionalPositioningdata(lrr.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData());
                        Multimap<String, String> methodsAndAddPosIds = utranAdditionalPositioningData.getUtranAdditionalPositioningMethodsAndIds();
                        JsonObject lrrUtranAddPosInfoDataSetJsonObject = new JsonObject();
                        JsonObject lrrUtranAddInfoJsonObject = new JsonObject();
                        JsonObject[] lrrUtranAddPosInfoMethodAndId = new JsonObject[methodsAndAddPosIds.size()];
                        String method, id;
                        int itemIndex = 0, usage;
                        for (Map.Entry<String, String> item : methodsAndAddPosIds.entries()) {
                            method = item.getKey();
                            id = item.getValue();
                            usage = utranAdditionalPositioningData.getUsageCode(utranAdditionalPositioningData.getData(), itemIndex);
                            String property = "Item-" + itemIndex;
                            lrrUtranAddPosInfoMethodAndId[itemIndex] = new JsonObject();
                            lrrUtranAddPosInfoDataSetJsonObject.add(property, lrrUtranAddPosInfoMethodAndId[itemIndex]);
                            writeUtranAddPositioningMethod(method, lrrUtranAddPosInfoMethodAndId[itemIndex]);
                            writeUtranAddPositioningPosId(id, lrrUtranAddPosInfoMethodAndId[itemIndex]);
                            writeUtranAddPositioningUsage(usage, lrrUtranAddPosInfoMethodAndId[itemIndex]);
                            itemIndex++;
                        }
                        lrrUtranAddInfoJsonObject.add("AdditionalPositioningDataSet", lrrUtranAddPosInfoDataSetJsonObject);
                        // Write UTRAN Positioning Info values from SLg LRR
                        lrrJsonObject.add("UtranAdditionalPositioningData", lrrUtranAddInfoJsonObject);
                    } catch (MAPException e) {
                        logger.error(e.getMessage());
                    }
                }
            }

            /*** Service-Area-Identity AVP ***/
            if (lrr.getServiceAreaIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength serviceAreaId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(lrr.getServiceAreaIdentity());
                try {
                    saiMcc = serviceAreaId.getMCC();
                    saiMnc = serviceAreaId.getMNC();
                    saiLac = serviceAreaId.getLac();
                    sac = serviceAreaId.getCellIdOrServiceAreaCode();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                // Write SAI values from SLg LRR
                JsonObject serviceAreaIdentityJsonObject = new JsonObject();
                writeMcc(saiMcc, serviceAreaIdentityJsonObject);
                writeMnc(saiMnc, serviceAreaIdentityJsonObject);
                writeLac(saiLac, serviceAreaIdentityJsonObject);
                writeServiceAreaCode(sac, serviceAreaIdentityJsonObject);
                // Write Service-Area-Identity from SLg LRR
                lrrJsonObject.add("SAI", serviceAreaIdentityJsonObject);
            }

            /*** LCS-Service-Type-ID  AVP ***/
            if (lrr.getLcsServiceTypeId() != null) {
                lcsServiceTypeId = lrr.getLcsServiceTypeId();
                // Write LCS-Service-Type-ID from SLg LRR
                writeLCSServiceTypeID(lcsServiceTypeId, lrrJsonObject);
            }

            /*** Pseudonym-Indicator AVP ***/
            if (lrr.getPseudonymIndicator() != null) {
                lcsPseudonymIndicator = lrr.getPseudonymIndicator().getValue();
                // Write Pseudonym-Indicator from SLg LRR
                writeLcsPseudonymIndicator(lcsPseudonymIndicator, lrrJsonObject);
            }

            /*** LCS-QoS-Class AVP ***/
            if (lrr.getLcsQoSClass() != null) {
                lcsQoSClassValue = lrr.getLcsQoSClass().getValue();
                // Write LCS-QoS-Class from SLg LRR
                writeLcsQoSClass(lcsQoSClassValue, lrrJsonObject);
            }

            /*** Serving-Node AVP ***/
            if (lrr.getServingNodeAvp() != null) {
                if (lrr.getServingNodeAvp().getMMEName() != null)
                    mmeName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getMMEName()).getData());
                if (lrr.getServingNodeAvp().getMMERealm() != null)
                    mmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getMMERealm()).getData());
                if (lrr.getServingNodeAvp().getSGSNName() != null)
                    sgsnName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getSGSNName()).getData());
                if (lrr.getServingNodeAvp().getSGSNRealm() != null)
                    sgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getSGSNRealm()).getData());
                if (lrr.getServingNodeAvp().getSGSNNumber() != null)
                    sgsnNumber = toTBCDString(lrr.getServingNodeAvp().getSGSNNumber());
                if (lrr.getServingNodeAvp().get3GPPAAAServerName() != null)
                    aaaServerName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().get3GPPAAAServerName()).getData());
                if (lrr.getServingNodeAvp().getMSCNumber() != null)
                    mscNumber = toTBCDString(lrr.getServingNodeAvp().getMSCNumber());
                if (lrr.getServingNodeAvp().hasLcsCapabilitiesSets())
                    lcsCapabilitySets = lrr.getServingNodeAvp().getLcsCapabilitiesSets();
                if (lrr.getServingNodeAvp().hasGMLCAddress()) {
                    gmlcAddress = bytesToHexString(lrr.getServingNodeAvp().getGMLCAddress().getAddress());
                    try {
                        InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(gmlcAddress));
                        gmlcAddress = address.getHostAddress();
                    } catch (UnknownHostException e) {
                        logger.error(e.getMessage());
                    }
                }
                // Write Serving Node values from SLg LRR
                JsonObject targetservingNodeJsonObject = new JsonObject();
                writeMmeName(mmeName, targetservingNodeJsonObject);
                writeMmeRealm(mmeRealm, targetservingNodeJsonObject);
                writeSgsnName(sgsnName, targetservingNodeJsonObject);
                writeSgsnRealm(sgsnRealm, targetservingNodeJsonObject);
                writeSgsnNumber(sgsnNumber, targetservingNodeJsonObject);
                write3gppAaaServerName(aaaServerName, targetservingNodeJsonObject);
                writeMscNumber(mscNumber, targetservingNodeJsonObject);
                writeLCSCapabilitySets(lcsCapabilitySets, targetservingNodeJsonObject);
                writeGmlcAddress(gmlcAddress, targetservingNodeJsonObject);
                // Write Target Serving-Node Identity from SLg LRR
                lrrJsonObject.add("TargetServingNodeForHandover", targetservingNodeJsonObject);
            }

            /*** LRR-Flags ***/
            if (lrr.getLrrFlags() != null) {
                writeLrrFlags(lrr.getLrrFlags(), lrrJsonObject);
            }

            /*** LCS-Reference-Number  AVP ***/
            if (lrr.getLcsReferenceNumber() != null) {
                lcsReferenceNumber = AVPHandler.byte2Int(lrr.getLcsReferenceNumber());
                // Write LCS-Reference-Number from SLg LRR
                writeLcsReferenceNumber(lcsReferenceNumber, lrrJsonObject);
            }

            /*** Deferred-MT-LR-Data AVP ***/
            if (lrr.getDeferredMTLRDataAvp() != null) {
                deferredLocationType = lrr.getDeferredMTLRDataAvp().getDeferredLocationType();
                mtLrTerminationCause = lrr.getDeferredMTLRDataAvp().getTerminationCause();
                JsonObject deferredMTLRDataJsonObject = new JsonObject();
                writeDeferredLocationType(deferredLocationType, deferredMTLRDataJsonObject);
                writeLRRTerminationCause(mtLrTerminationCause, deferredMTLRDataJsonObject);

                if (mtLrTerminationCause == 4) {
                    if (lrr.getDeferredMTLRDataAvp().getServingNode() != null) {
                        JsonObject deferredMTLRDataServingNodeJsonObject = new JsonObject();
                        if (lrr.getDeferredMTLRDataAvp().getServingNode().getMMEName() != null)
                            mtLrMmeName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getMMEName()).getData());
                        if (lrr.getDeferredMTLRDataAvp().getServingNode().getMMERealm() != null)
                            mtLrMmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getMMERealm()).getData());
                        if (lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNName() != null)
                            mtLrSgsnName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNName()).getData());
                        if (lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNRealm() != null)
                            mtLrSgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNRealm()).getData());
                        if (lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNNumber() != null)
                            mLlrSgsnNumber = toTBCDString(lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNNumber());
                        if (lrr.getDeferredMTLRDataAvp().getServingNode().get3GPPAAAServerName() != null)
                            mtLr3gppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().get3GPPAAAServerName()).getData());
                        if (lrr.getDeferredMTLRDataAvp().getServingNode().getMSCNumber() != null)
                            mtLrMscNumber = toTBCDString(lrr.getDeferredMTLRDataAvp().getServingNode().getMSCNumber());
                        if (lrr.getDeferredMTLRDataAvp().getServingNode().hasLcsCapabilitiesSets())
                            mtLrLcsCapabilitySets = lrr.getDeferredMTLRDataAvp().getServingNode().getLcsCapabilitiesSets();
                        if (lrr.getDeferredMTLRDataAvp().getServingNode().hasGMLCAddress()) {
                            mtLrGmlcAddress = bytesToHexString(lrr.getDeferredMTLRDataAvp().getServingNode().getGMLCAddress().getAddress());
                            try {
                                InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(mtLrGmlcAddress));
                                mtLrGmlcAddress = address.getHostAddress();
                            } catch (UnknownHostException e) {
                                logger.error(e.getMessage());
                            }
                        }
                        writeMmeName(mtLrMmeName, deferredMTLRDataServingNodeJsonObject);
                        writeMmeRealm(mtLrMmeRealm, deferredMTLRDataServingNodeJsonObject);
                        writeSgsnName(mtLrSgsnName, deferredMTLRDataServingNodeJsonObject);
                        writeSgsnRealm(mtLrSgsnRealm, deferredMTLRDataServingNodeJsonObject);
                        writeSgsnNumber(mLlrSgsnNumber, deferredMTLRDataServingNodeJsonObject);
                        write3gppAaaServerName(mtLr3gppAAAServerName, deferredMTLRDataServingNodeJsonObject);
                        writeMscNumber(mtLrMscNumber, deferredMTLRDataServingNodeJsonObject);
                        writeLCSCapabilitySets(mtLrLcsCapabilitySets, deferredMTLRDataServingNodeJsonObject);
                        writeGmlcAddress(mtLrGmlcAddress, deferredMTLRDataServingNodeJsonObject);
                        deferredMTLRDataJsonObject.add("ServingNode", deferredMTLRDataServingNodeJsonObject);
                    }
                }
                // Write Deferred-MT-LR-Data from SLg LRR
                lrrJsonObject.add("DeferredMTLRData", deferredMTLRDataJsonObject);
            }

            /*** GMLC-Address AVP ***/
            if (lrr.getGmlcAddress() != null) {
                gmlcAddress = bytesToHexString(lrr.getGmlcAddress().getAddress());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(gmlcAddress));
                    gmlcAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    logger.error(e.getMessage());
                }
                // Write GMLC-Address from SLg LRR
                writeGmlcAddress(gmlcAddress, lrrJsonObject);
            }

            /*** Sequence Number (Reporting-Amount AVP) ***/
            if (lrr.getReportingAmount() != null) {
                reportingAmount = lrr.getReportingAmount();
                // Write Reporting-Amount from SLg LRR
                writeSequenceNumber((int) reportingAmount, lrrJsonObject);
            }

            /*** Periodic-LDR-Info AVP ***/
            if (lrr.getPeriodicLDRInformation() != null) {
                reportingAmount = lrr.getPeriodicLDRInformation().getReportingAmount();
                reportingInterval = lrr.getPeriodicLDRInformation().getReportingInterval();
                // Write Periodic-LDR-Info from SLg LRR
                JsonObject periodicLDRInfoJsonObject = new JsonObject();
                writeReportingAmount(reportingAmount, periodicLDRInfoJsonObject);
                writeReportingInterval(reportingInterval, periodicLDRInfoJsonObject);
                lrrJsonObject.add("PeriodicLDRInfo", periodicLDRInfoJsonObject);
            }

            /*** ESMLC-Cell-Info AVP ***/
            if (lrr.getEsmlcCellInfoAvp() != null) {
                if (lrr.getEsmlcCellInfoAvp().getECGI() != null) {
                    EUtranCgi eutranCgi = new EUtranCgiImpl(lrr.getEsmlcCellInfoAvp().getECGI());
                    try {
                        ecgiMcc = eutranCgi.getMCC();
                        ecgiMnc = eutranCgi.getMNC();
                        eci = eutranCgi.getEci();
                        eNBId = eutranCgi.getENodeBId();
                        ecgiCi = eutranCgi.getCi();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
                if (lrr.getEsmlcCellInfoAvp().getCellPortionID() > -1)
                    cellPortionId = lrr.getEsmlcCellInfoAvp().getCellPortionID();
                // Write ECGI or ESMLC Cell Info values from SLg LRR
                JsonObject eCGIorESMLCCellInfoJsonObject = new JsonObject();
                writeMcc(ecgiMcc, eCGIorESMLCCellInfoJsonObject);
                writeMnc(ecgiMnc, eCGIorESMLCCellInfoJsonObject);
                writeEUtranEci(eci, eCGIorESMLCCellInfoJsonObject);
                writeENBId(eNBId, eCGIorESMLCCellInfoJsonObject);
                writeCellId(ecgiCi, eCGIorESMLCCellInfoJsonObject);
                writeCellPortionId(cellPortionId, eCGIorESMLCCellInfoJsonObject);
                lrrJsonObject.add("ESMLCCellInfo", eCGIorESMLCCellInfoJsonObject);
            }

            /*** 1xRTT-RCID AVP ***/
            // TODO

            /*** Delayed-Location-Reporting-Data AVP ***/
            if (lrr.getDelayedLocationReportingDataAvp() != null) {
                lrr.getDelayedLocationReportingDataAvp().getTerminationCause();
                dlrTerminationCause = lrr.getDelayedLocationReportingDataAvp().getTerminationCause();
                JsonObject delayedLocationReportingDataJsonObject = new JsonObject();
                writeLRRTerminationCause(dlrTerminationCause, delayedLocationReportingDataJsonObject);

                if (dlrTerminationCause == 4) {
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode() != null) {
                        JsonObject dlrDataServingNodeJsonObject = new JsonObject();
                        if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMEName() != null)
                            dlrMmeName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMEName()).getData());
                        if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMERealm() != null)
                            dlrMmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMERealm()).getData());
                        if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNName() != null)
                            dlrSgsnName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNName()).getData());
                        if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNRealm() != null)
                            dlrSgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNRealm()).getData());
                        if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNNumber() != null)
                            dlrSgsnNumber = toTBCDString(lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNNumber());
                        if (lrr.getDelayedLocationReportingDataAvp().getServingNode().get3GPPAAAServerName() != null)
                            dlr3gppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().get3GPPAAAServerName()).getData());
                        if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getMSCNumber() != null)
                            dlrMscNumber = toTBCDString(lrr.getDelayedLocationReportingDataAvp().getServingNode().getMSCNumber());
                        if (lrr.getDelayedLocationReportingDataAvp().getServingNode().hasLcsCapabilitiesSets())
                            dlrLcsCapabilitySets = lrr.getDelayedLocationReportingDataAvp().getServingNode().getLcsCapabilitiesSets();
                        if (lrr.getDelayedLocationReportingDataAvp().getServingNode().hasGMLCAddress()) {
                            dlrGmlcAddress = bytesToHexString(lrr.getDelayedLocationReportingDataAvp().getServingNode().getGMLCAddress().getAddress());
                            try {
                                InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(dlrGmlcAddress));
                                dlrGmlcAddress = address.getHostAddress();
                            } catch (UnknownHostException e) {
                                logger.error(e.getMessage());
                            }
                        }
                        writeMmeName(dlrMmeName, dlrDataServingNodeJsonObject);
                        writeMmeRealm(dlrMmeRealm, dlrDataServingNodeJsonObject);
                        writeSgsnName(dlrSgsnName, dlrDataServingNodeJsonObject);
                        writeSgsnRealm(dlrSgsnRealm, dlrDataServingNodeJsonObject);
                        writeSgsnNumber(dlrSgsnNumber, dlrDataServingNodeJsonObject);
                        write3gppAaaServerName(dlr3gppAAAServerName, dlrDataServingNodeJsonObject);
                        writeMscNumber(dlrMscNumber, dlrDataServingNodeJsonObject);
                        writeLCSCapabilitySets(dlrLcsCapabilitySets, dlrDataServingNodeJsonObject);
                        writeGmlcAddress(dlrGmlcAddress, dlrDataServingNodeJsonObject);
                        delayedLocationReportingDataJsonObject.add("ServingNode", dlrDataServingNodeJsonObject);
                    }
                }
                // Write Delayed Location Reporting Data from SLg LRR
                lrrJsonObject.add("DelayedLocationReportingData", delayedLocationReportingDataJsonObject);
            }

            /*** Civic-Address AVP ***/
            if (lrr.getCivicAddress() != null) {
                civicAddress = lrr.getCivicAddress();
                CivicAddressXmlReader reader = new CivicAddressXmlReader();
                reader.civicAddressXMLReader(civicAddress);
                CivicAddressElements civicAddressElements = reader.getCivicAddressElements();
                JsonObject lrrCivicAddressJsonObject = new JsonObject();
                // Write Civic Address from SLg LRR
                writeCivicAddress(civicAddressElements, lrrCivicAddressJsonObject);
                lrrJsonObject.add("CivicAddress", lrrCivicAddressJsonObject);
            }

            /*** Barometric-Pressure AVP ***/
            if (lrr.getBarometricPressure() != null) {
                JsonObject lrrBarometricPressureJsonObject = new JsonObject();
                barometricPressure = lrr.getBarometricPressure();
                writeBarometricPressure(barometricPressure, lrrBarometricPressureJsonObject);
                lrrJsonObject.add("BarometricPressure", lrrBarometricPressureJsonObject);
            }

            /*** AMF-Instance-Id AVP ***/
            if (lrr.getAmfInstanceId() != null) {
                amfInstanceId = lrr.getAmfInstanceId();
                // Write AMF instance id from SLg LRR
                writeAmfInstanceId(amfInstanceId, lrrJsonObject);
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(lrrJsonObject);
    }

}
