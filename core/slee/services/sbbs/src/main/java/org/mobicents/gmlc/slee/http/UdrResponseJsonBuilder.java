package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.diameter.sh.ShUdaAvpValues;
import org.mobicents.gmlc.slee.primitives.EUTRANCGI;
import org.mobicents.gmlc.slee.primitives.NRCellGlobalId;
import org.mobicents.gmlc.slee.primitives.RoutingAreaId;
import org.mobicents.gmlc.slee.primitives.TrackingAreaId;
import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;

import static org.mobicents.gmlc.slee.http.JsonWriter.writeAddressPresentationRestrictedIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAmfAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCSGId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeConfidence;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCurrentLocationRetrieved;
// import REMOVED_DaylightSavingTime;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeENBId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranEci;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeIMSPublicIdentity;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeInternalNetworkNumberIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLac;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLatitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLocationNumberAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLongitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMscNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNatureOfAddressIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNrCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNumberingPlanIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOddFlag;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeRatType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeRoutingAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeScreeningAndPresentationIndicators;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeScreeningIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeServiceAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSmsfAddress;
// import REMOVED_TimeZone;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTrackingAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTypeOfShape;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertainty;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVisitedPlmnIdMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVisitedPlmnIdMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVlrNumber;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class UdrResponseJsonBuilder {

    protected static final Logger logger = Logger.getLogger(UdrResponseJsonBuilder.class);

    public UdrResponseJsonBuilder() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param uda       Sh-Data location information values gathered from UDA response event
     */
    public static String buildJsonResponseForUdr(ShUdaAvpValues uda) {

        int csMcc, csMnc, csLac, csCi, csEcgiMcc, csEcgiMnc, csEcgiCi, csTaiMcc, csTaiMnc, csTac, csSac, psMcc, psMnc, psLac, psCi, psSac, raiMcc, raiMnc, raiLac, rac, ecgiMcc,
            ecgiMnc, ecgiCi, taiMcc, taiMnc, tac, csAol, psAol, epsAol, sh5gsAol, natureOfAddressIndicator, numberingPlanIndicator, internalNetworkNumberIndicator,
            addressPresentationRestrictedIndicator, screeningIndicator, csConfidence, csScreeningAndPresentationIndicators, psConfidence, psScreeningAndPresentationIndicators,
            epsConfidence, epsScreeningAndPresentationIndicators, nrCgiMcc, nrCgiMnc, sh5gsEcgiMcc, sh5gsEcgiMnc, sh5gsEcgiCi, sh5gsTaiMcc, sh5gsTaiMnc, sh5gsTac,
            csDaylightSavingTime, psVPlmnIdMcc, psVPlmnIdMnc, epsVPlmnIdMcc, epsVPlmnIdMnc, sh5gsVPlmnIdMcc, sh5gsVPlmnIdMnc,
            psDaylightSavingTime, epsDaylightSavingTime, sh5gsDaylightSavingTime, psRatType, epsRatType, sh5gsRatType;
        csMcc = csMnc = csLac = csCi = csSac = psMcc = psMnc = psLac = psCi = psSac = -1;
        String msisdn, imsPublicId, locationNumberAddressDigits, csGeographicalTypeOfShape, csGeodeticTypeOfShape, psGeographicalTypeOfShape, psGeodeticTypeOfShape,
            epsGeographicalTypeOfShape, epsGeodeticTypeOfShape, sh5gsGeographicalTypeOfShape, mscNumberAddress, vlrNumberAddress, sgsnNumberAddress, mmeName, csCsgId,
            psCsgId, epsCsgId, csCurrentLocationInfoRetrieved, psCurrentLocationInfoRetrieved, epsCurrentLocationInfoRetrieved, sh5gsCurrentLocationInfoRetrieved, amfAddress,
            smsfAddress, csTimeZone, psTimeZone, epsTimeZone, sh5gsTimeZone;
         csCurrentLocationInfoRetrieved = null;
        boolean oddFlag, cgi = false, sai = false, lai = false;
        double csGeographicalLatitude, csGeographicalLongitude, csGeographicalUncertainty, csGeodeticLatitude, csGeodeticLongitude, csGeodeticUncertainty;
        double psGeographicalLatitude, psGeographicalLongitude, psGeographicalUncertainty, psGeodeticLatitude, psGeodeticLongitude, psGeodeticUncertainty;
        double epsGeographicalLatitude, epsGeographicalLongitude, epsGeographicalUncertainty, epsGeodeticLatitude, epsGeodeticLongitude, epsGeodeticUncertainty;
        double sh5gsGeographicalLatitude, sh5gsGeographicalLongitude, sh5gsGeographicalUncertainty;
        long eci, eNBId, csEci, csENBId, nrCgiNci, sh5gsEci, sh5gsENBid;

        JsonObject udaJsonObject = new JsonObject();
        writeNetwork("IMS", udaJsonObject);
        writeProtocol("Diameter Sh", udaJsonObject);
        writeOperation("UDR-UDA", udaJsonObject);
        writeOperationResult("SUCCESS", udaJsonObject);
        JsonObject publicIdentifiersJsonObject = null;
        JsonObject csLocationInfoJsonObject = null;
        JsonObject psLocationInfoJsonObject = null;
        JsonObject epsLocationInfoJsonObject = null;
        JsonObject sh5gsLocationInfoJsonObject = null;

        if (uda != null) {
            // Get User-Data-Answer values
            if (uda.getUserData() != null) {

                // Public Identifiers
                if (uda.getImsPublicIdentity() != null || uda.getMsisdn() != null) {
                    publicIdentifiersJsonObject = new JsonObject();
                    if (uda.getImsPublicIdentity() != null) {
                        imsPublicId = uda.getImsPublicIdentity();
                        writeIMSPublicIdentity(imsPublicId, publicIdentifiersJsonObject);
                    }
                    if (uda.getMsisdn() != null) {
                        msisdn = uda.getMsisdn();
                        writeMsisdn(msisdn, publicIdentifiersJsonObject);
                    }
                }

                // CS Location Information
                if (uda.getCsLocationInformation() != null) {
                    csLocationInfoJsonObject = new JsonObject();
                    if (uda.getLocationNumber() != null) {
                        JsonObject locationNumberJsonObject = new JsonObject();
                        LocationNumber locationNumber = uda.getLocationNumber().getLocationNumber();
                        oddFlag = locationNumber.isOddFlag();
                        natureOfAddressIndicator = locationNumber.getNatureOfAddressIndicator();
                        internalNetworkNumberIndicator = locationNumber.getInternalNetworkNumberIndicator();
                        numberingPlanIndicator = locationNumber.getNumberingPlanIndicator();
                        addressPresentationRestrictedIndicator = locationNumber.getAddressRepresentationRestrictedIndicator();
                        screeningIndicator = locationNumber.getScreeningIndicator();
                        locationNumberAddressDigits = locationNumber.getAddress();
                        writeOddFlag(oddFlag, locationNumberJsonObject);
                        writeNatureOfAddressIndicator(natureOfAddressIndicator, locationNumberJsonObject);
                        writeInternalNetworkNumberIndicator(internalNetworkNumberIndicator, locationNumberJsonObject);
                        writeNumberingPlanIndicator(numberingPlanIndicator, locationNumberJsonObject);
                        writeAddressPresentationRestrictedIndicator(addressPresentationRestrictedIndicator, locationNumberJsonObject);
                        writeScreeningIndicator(screeningIndicator, locationNumberJsonObject);
                        writeLocationNumberAddress(locationNumberAddressDigits, locationNumberJsonObject);
                        csLocationInfoJsonObject.add("LocationNumber", locationNumberJsonObject);
                    }
                    if (uda.getCsCellGlobalId() != null) {
                        cgi = true;
                        CellGlobalIdOrServiceAreaIdFixedLength csCgi = uda.getCsCellGlobalId().getCellGlobalIdOrServiceAreaIdFixedLength();
                        try {
                            csMcc = csCgi.getMCC();
                            csMnc = csCgi.getMNC();
                            csLac = csCgi.getLac();
                            csCi = csCgi.getCellIdOrServiceAreaCode();
                        } catch (MAPException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getCsServiceAreaId() != null) {
                        sai = true;
                        CellGlobalIdOrServiceAreaIdFixedLength csSai = uda.getCsServiceAreaId().getCellGlobalIdOrServiceAreaIdFixedLength();
                        try {
                            csMcc = csSai.getMCC();
                            csMnc = csSai.getMNC();
                            csLac = csSai.getLac();
                            csSac = csSai.getCellIdOrServiceAreaCode();
                        } catch (MAPException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getCsLocationAreaId() != null) {
                        lai = true;
                        LAIFixedLength csLai = uda.getCsLocationAreaId().getLaiFixedLength();
                        try {
                            csMcc = csLai.getMCC();
                            csMnc = csLai.getMNC();
                            csLac = csLai.getLac();
                        } catch (MAPException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (csMcc != -1 || cgi || sai || lai) {
                        JsonObject csCgiOrSaiOrLaiJsonObject = new JsonObject();
                        if (csMcc != -1)
                            writeMcc(csMcc, csCgiOrSaiOrLaiJsonObject);
                        if (csMnc != -1)
                            writeMnc(csMnc, csCgiOrSaiOrLaiJsonObject);
                        if (csLac != -1)
                            writeLac(csLac, csCgiOrSaiOrLaiJsonObject);
                        if (cgi) {
                            writeCellId(csCi, csCgiOrSaiOrLaiJsonObject);
                            csLocationInfoJsonObject.add("CGI", csCgiOrSaiOrLaiJsonObject);
                        } else if (sai) {
                            writeServiceAreaCode(csSac, csCgiOrSaiOrLaiJsonObject);
                            csLocationInfoJsonObject.add("SAI", csCgiOrSaiOrLaiJsonObject);
                        } else {
                            csLocationInfoJsonObject.add("LAI", csCgiOrSaiOrLaiJsonObject);
                        }
                    }
                    
                    if (uda.getCsGeographicalInformation() != null) {
                        if (uda.getCsGeographicalInformation().getGeographicalInformation() != null) {
                            csGeographicalTypeOfShape = uda.getCsGeographicalInformation().getGeographicalInformation().getTypeOfShape().name();
                            csGeographicalLatitude = uda.getCsGeographicalInformation().getGeographicalInformation().getLatitude();
                            csGeographicalLongitude = uda.getCsGeographicalInformation().getGeographicalInformation().getLongitude();
                            csGeographicalUncertainty = uda.getCsGeographicalInformation().getGeographicalInformation().getUncertainty();
                            JsonObject csGeographicalInfoJsonObject = new JsonObject();
                            writeTypeOfShape(csGeographicalTypeOfShape, csGeographicalInfoJsonObject);
                            writeLatitude(csGeographicalLatitude, csGeographicalInfoJsonObject);
                            writeLongitude(csGeographicalLongitude, csGeographicalInfoJsonObject);
                            writeUncertainty(csGeographicalUncertainty, csGeographicalInfoJsonObject);
                            csLocationInfoJsonObject.add("GeographicalInformation", csGeographicalInfoJsonObject);
                        }
                    }
                    if (uda.getCsGeodeticInformation() != null) {
                        if (uda.getCsGeodeticInformation().getGeodeticInformation() != null) {
                            csGeodeticTypeOfShape = uda.getCsGeodeticInformation().getGeodeticInformation().getTypeOfShape().name();
                            csGeodeticLatitude = uda.getCsGeodeticInformation().getGeodeticInformation().getLatitude();
                            csGeodeticLongitude = uda.getCsGeodeticInformation().getGeodeticInformation().getLongitude();
                            csGeodeticUncertainty = uda.getCsGeodeticInformation().getGeodeticInformation().getUncertainty();
                            csConfidence = uda.getCsGeodeticInformation().getGeodeticInformation().getConfidence();
                            csScreeningAndPresentationIndicators = uda.getCsGeodeticInformation().getGeodeticInformation().getScreeningAndPresentationIndicators();
                            JsonObject csGeodeticInfoJsonObject= new JsonObject();
                            writeTypeOfShape(csGeodeticTypeOfShape, csGeodeticInfoJsonObject);
                            writeLatitude(csGeodeticLatitude, csGeodeticInfoJsonObject);
                            writeLongitude(csGeodeticLongitude, csGeodeticInfoJsonObject);
                            writeUncertainty(csGeodeticUncertainty, csGeodeticInfoJsonObject);
                            writeConfidence(csConfidence, csGeodeticInfoJsonObject);
                            writeScreeningAndPresentationIndicators(csScreeningAndPresentationIndicators, csGeodeticInfoJsonObject);
                            csLocationInfoJsonObject.add("GeodeticInformation", csGeodeticInfoJsonObject);
                        }
                    }
                    if (uda.getMscNumber() != null) {
                        mscNumberAddress = uda.getMscNumber().getAddress();
                        writeMscNumber(mscNumberAddress, csLocationInfoJsonObject);
                    }
                    if (uda.getVlrNumber() != null) {
                        vlrNumberAddress = uda.getVlrNumber().getAddress();
                        writeVlrNumber(vlrNumberAddress, csLocationInfoJsonObject);
                    }
                    if (uda.getCsCurrentLocationInfoRetrieved() != null) {
                        csCurrentLocationInfoRetrieved = uda.getCsCurrentLocationInfoRetrieved();
                        if (csCurrentLocationInfoRetrieved.equalsIgnoreCase("0")||
                            csCurrentLocationInfoRetrieved.equalsIgnoreCase("true"))
                            csCurrentLocationInfoRetrieved = "true";
                        else if (csCurrentLocationInfoRetrieved.equalsIgnoreCase("1")||
                            csCurrentLocationInfoRetrieved.equalsIgnoreCase("false"))
                            csCurrentLocationInfoRetrieved = "false";
                        writeCurrentLocationRetrieved(Boolean.valueOf(csCurrentLocationInfoRetrieved), csLocationInfoJsonObject);
                    }
                    if (uda.getCsAgeOfLocationInfo() != null) {
                        csAol = uda.getCsAgeOfLocationInfo();
                        writeAol(csAol, csLocationInfoJsonObject);
                        if (csAol > 0)
                            csCurrentLocationInfoRetrieved = "false";
                        if (csCurrentLocationInfoRetrieved != null)
                            writeCurrentLocationRetrieved(Boolean.valueOf(csCurrentLocationInfoRetrieved), csLocationInfoJsonObject);
                    }
                    /* Commented out: getUserCSGInformationStr() method doesn't exist
                    if (uda.getUserCSGInformation() != null) {
                        csCsgId = uda.getUserCSGInformation().getUserCSGInformationStr();
                        writeCSGId(csCsgId, csLocationInfoJsonObject);
                    }
                    */
                    if (uda.getEutrancgi() != null || uda.getTrackingAreaId() != null ||
                            uda.getCsLocalTimeZone() != null) {
                        JsonObject csEpsLocationInfoJsonObject = new JsonObject();
                        if (uda.getEutrancgi() != null) {
                            EUTRANCGI eUtranCgi = uda.getEutrancgi().getEutranCgi();
                            try {
                                csEcgiMcc = eUtranCgi.getMCC();
                                csEcgiMnc = eUtranCgi.getMNC();
                                csENBId = eUtranCgi.getENodeBId();
                                csEci = eUtranCgi.getEci();
                                csEcgiCi = eUtranCgi.getCi();
                                JsonObject csEUtranCgiJsonObject = new JsonObject();
                                writeMcc(csEcgiMcc, csEUtranCgiJsonObject);
                                writeMnc(csEcgiMnc, csEUtranCgiJsonObject);
                                writeEUtranEci(csEci, csEUtranCgiJsonObject);
                                writeENBId(csENBId, csEUtranCgiJsonObject);
                                writeEUtranCellId(csEcgiCi, csEUtranCgiJsonObject);
                                csEpsLocationInfoJsonObject.add("ECGI", csEUtranCgiJsonObject);
                            } catch (Exception e) {
                                logger.error(e.getMessage());
                            }
                        }
                        if (uda.getTrackingAreaId() != null) {
                            TrackingAreaId trackingAreaId = uda.getTrackingAreaId().getTrackingAreaId();
                            try {
                                csTaiMcc = trackingAreaId.getMCC();
                                csTaiMnc = trackingAreaId.getMNC();
                                csTac = trackingAreaId.getTAC();
                                JsonObject csTrackingAreaIdJsonObject = new JsonObject();
                                writeMcc(csTaiMcc, csTrackingAreaIdJsonObject);
                                writeMnc(csTaiMnc, csTrackingAreaIdJsonObject);
                                writeTrackingAreaCode(csTac, csTrackingAreaIdJsonObject);
                                csEpsLocationInfoJsonObject.add("TAI", csTrackingAreaIdJsonObject);
                            } catch (MAPException e) {
                                logger.error(e.getMessage());
                            }
                        }
                        /* Commented out: writeTimeZone() method doesn't exist
                        if (uda.getCsLocalTimeZone() != null) {
                            csTimeZone = uda.getCsLocalTimeZone().getTimeZone();
                            JsonObject csLocalTimeZoneJsonObject = new JsonObject();
                            writeTimeZone(csTimeZone, csLocalTimeZoneJsonObject);
                            csDaylightSavingTime = uda.getCsLocalTimeZone().getDaylightSavingTime();
                            writeDaylightSavingTime(csDaylightSavingTime, csLocalTimeZoneJsonObject);
                            csEpsLocationInfoJsonObject.add("LocalTimeZone", csLocalTimeZoneJsonObject);
                        }
                        */
                        // Write EPS Location Information values from CS Location Information extension
                        csLocationInfoJsonObject.add("EPSLocationInformation", csEpsLocationInfoJsonObject);
                    }
                }

                // PS Location Information
                if (uda.getPsLocationInformation() != null) {
                    psLocationInfoJsonObject = new JsonObject();
                    if (uda.getPsCellGlobalId() != null) {
                        cgi = true;
                        CellGlobalIdOrServiceAreaIdFixedLength psCgi = uda.getPsCellGlobalId().getCellGlobalIdOrServiceAreaIdFixedLength();
                        try {
                            psMcc = psCgi.getMCC();
                            psMnc = psCgi.getMNC();
                            psLac = psCgi.getLac();
                            psCi = psCgi.getCellIdOrServiceAreaCode();
                        } catch (MAPException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getPsServiceAreaId() != null) {
                        sai = true;
                        CellGlobalIdOrServiceAreaIdFixedLength psSai = uda.getPsServiceAreaId().getCellGlobalIdOrServiceAreaIdFixedLength();
                        try {
                            psMcc = psSai.getMCC();
                            psMnc = psSai.getMNC();
                            psLac = psSai.getLac();
                            psSac = psSai.getCellIdOrServiceAreaCode();
                        } catch (MAPException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getPsLocationAreaId() != null) {
                        lai = true;
                        LAIFixedLength psLai = uda.getPsLocationAreaId().getLaiFixedLength();
                        try {
                            psMcc = psLai.getMCC();
                            psMnc = psLai.getMNC();
                            psLac = psLai.getLac();
                        } catch (MAPException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (psMcc != -1 || cgi || sai || lai) {
                        JsonObject psCgiOrSaiOrLaiJsonObject = new JsonObject();
                        if (psMcc != -1)
                            writeMcc(psMcc, psCgiOrSaiOrLaiJsonObject);
                        if (psMnc != -1)
                            writeMnc(psMnc, psCgiOrSaiOrLaiJsonObject);
                        if (psLac != -1)
                            writeLac(psLac, psCgiOrSaiOrLaiJsonObject);
                        if (cgi) {
                            writeCellId(psCi, psCgiOrSaiOrLaiJsonObject);
                            psLocationInfoJsonObject.add("CGI", psCgiOrSaiOrLaiJsonObject);
                        } else if (sai) {
                            writeServiceAreaCode(psSac, psCgiOrSaiOrLaiJsonObject);
                            psLocationInfoJsonObject.add("SAI", psCgiOrSaiOrLaiJsonObject);
                        } else {
                            psLocationInfoJsonObject.add("LAI", psCgiOrSaiOrLaiJsonObject);
                        }
                    }
                    if (uda.getRoutingAreaId() != null) {
                        RoutingAreaId raId = uda.getRoutingAreaId().getRoutingAreaIdentity();
                        try {
                            raiMcc = raId.getMCC();
                            raiMnc = raId.getMNC();
                            raiLac = raId.getLAC();
                            rac = raId.getRAC();
                            JsonObject routingAreaIdJsonObject = new JsonObject();
                            writeMcc(raiMcc, routingAreaIdJsonObject);
                            writeMnc(raiMnc, routingAreaIdJsonObject);
                            writeLac(raiLac, routingAreaIdJsonObject);
                            writeRoutingAreaCode(rac, routingAreaIdJsonObject);
                            psLocationInfoJsonObject.add("RAI", routingAreaIdJsonObject);
                        } catch (MAPException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getPsGeographicalInformation() != null) {
                        if (uda.getPsGeographicalInformation().getGeographicalInformation() != null) {
                            psGeographicalTypeOfShape = uda.getPsGeographicalInformation().getGeographicalInformation().getTypeOfShape().name();
                            psGeographicalLatitude = uda.getPsGeographicalInformation().getGeographicalInformation().getLatitude();
                            psGeographicalLongitude = uda.getPsGeographicalInformation().getGeographicalInformation().getLongitude();
                            psGeographicalUncertainty = uda.getPsGeographicalInformation().getGeographicalInformation().getUncertainty();
                            JsonObject psGeographicalInfoJsonObject = new JsonObject();
                            writeTypeOfShape(psGeographicalTypeOfShape, psGeographicalInfoJsonObject);
                            writeLatitude(psGeographicalLatitude, psGeographicalInfoJsonObject);
                            writeLongitude(psGeographicalLongitude, psGeographicalInfoJsonObject);
                            writeUncertainty(psGeographicalUncertainty, psGeographicalInfoJsonObject);
                            psLocationInfoJsonObject.add("GeographicalInformation", psGeographicalInfoJsonObject);
                        }
                    }
                    if (uda.getPsGeodeticInformation() != null) {
                        if (uda.getPsGeodeticInformation().getGeodeticInformation() != null) {
                            psGeodeticTypeOfShape = uda.getPsGeodeticInformation().getGeodeticInformation().getTypeOfShape().name();
                            psGeodeticLatitude = uda.getPsGeodeticInformation().getGeodeticInformation().getLatitude();
                            psGeodeticLongitude = uda.getPsGeodeticInformation().getGeodeticInformation().getLongitude();
                            psGeodeticUncertainty = uda.getPsGeodeticInformation().getGeodeticInformation().getUncertainty();
                            psConfidence = uda.getPsGeodeticInformation().getGeodeticInformation().getConfidence();
                            psScreeningAndPresentationIndicators = uda.getPsGeodeticInformation().getGeodeticInformation().getScreeningAndPresentationIndicators();
                            JsonObject psGeodeticInfoJsonObject= new JsonObject();
                            writeTypeOfShape(psGeodeticTypeOfShape, psGeodeticInfoJsonObject);
                            writeLatitude(psGeodeticLatitude, psGeodeticInfoJsonObject);
                            writeLongitude(psGeodeticLongitude, psGeodeticInfoJsonObject);
                            writeUncertainty(psGeodeticUncertainty, psGeodeticInfoJsonObject);
                            writeConfidence(psConfidence, psGeodeticInfoJsonObject);
                            writeScreeningAndPresentationIndicators(psScreeningAndPresentationIndicators, psGeodeticInfoJsonObject);
                            psLocationInfoJsonObject.add("GeodeticInformation", psGeodeticInfoJsonObject);
                        }
                    }
                    if (uda.getSgsnNumber() != null) {
                        sgsnNumberAddress = uda.getSgsnNumber().getAddress();
                        writeSgsnNumber(sgsnNumberAddress, psLocationInfoJsonObject);
                    }
                    if (uda.getPsCurrentLocationInfoRetrieved() != null) {
                        psCurrentLocationInfoRetrieved = uda.getPsCurrentLocationInfoRetrieved();
                        if (psCurrentLocationInfoRetrieved.equalsIgnoreCase("0") ||
                            psCurrentLocationInfoRetrieved.equalsIgnoreCase("true"))
                            psCurrentLocationInfoRetrieved = "true";
                        else if (psCurrentLocationInfoRetrieved.equalsIgnoreCase("1") ||
                            psCurrentLocationInfoRetrieved.equalsIgnoreCase("false"))
                            psCurrentLocationInfoRetrieved = "false";
                        writeCurrentLocationRetrieved(Boolean.valueOf(psCurrentLocationInfoRetrieved), psLocationInfoJsonObject);
                    }
                    if (uda.getPsAgeOfLocationInfo() != null) {
                        psAol = uda.getPsAgeOfLocationInfo();
                        writeAol(psAol, psLocationInfoJsonObject);
                        if (psAol > 0) {
                            psCurrentLocationInfoRetrieved = "false";
                            writeCurrentLocationRetrieved(Boolean.valueOf(psCurrentLocationInfoRetrieved), psLocationInfoJsonObject);
                        }
                    }
                    /* Commented out: getUserCSGInformationStr() method doesn't exist
                    if (uda.getUserCSGInformation() != null) {
                        psCsgId = uda.getUserCSGInformation().getUserCSGInformationStr();
                        writeCSGId(psCsgId, psLocationInfoJsonObject);
                    }
                    */
                    if (uda.getPsVisitedPLMNId() != null) {
                        if (uda.getPsVisitedPLMNId().getVisitedPlmnId() != null) {
                            psVPlmnIdMcc = uda.getPsVisitedPLMNId().getVisitedPlmnId().getMcc();
                            psVPlmnIdMnc = uda.getPsVisitedPLMNId().getVisitedPlmnId().getMnc();
                            JsonObject psVisitedPlmnIdJsonObject = new JsonObject();
                            writeVisitedPlmnIdMcc(psVPlmnIdMcc, psVisitedPlmnIdJsonObject);
                            writeVisitedPlmnIdMnc(psVPlmnIdMnc, psVisitedPlmnIdJsonObject);
                            psLocationInfoJsonObject.add("VisitedPLMNId", psVisitedPlmnIdJsonObject);
                        }
                    }
                    /* Commented out: writeTimeZone(), writeDaylightSavingTime() methods don't exist
                    if (uda.getPsLocalTimeZone() != null) {
                        psTimeZone = uda.getPsLocalTimeZone().getTimeZone();
                        JsonObject psLocalTimeZoneJsonObject = new JsonObject();
                        writeTimeZone(psTimeZone, psLocalTimeZoneJsonObject);
                        psDaylightSavingTime = uda.getPsLocalTimeZone().getDaylightSavingTime();
                        writeDaylightSavingTime(psDaylightSavingTime, psLocalTimeZoneJsonObject);
                        psLocationInfoJsonObject.add("LocalTimeZone", psLocalTimeZoneJsonObject);
                    }
                    */
                    if (uda.getPsRatType() != null) {
                        psRatType = uda.getPsRatType();
                        writeRatType(psRatType, psLocationInfoJsonObject);
                    }
                }

                // EPS Location Information
                if (uda.getEpsLocationInformation() != null) {
                    epsLocationInfoJsonObject = new JsonObject();
                    if (uda.getEutrancgi() != null) {
                        EUTRANCGI eUtranCgi = uda.getEutrancgi().getEutranCgi();
                        try {
                            ecgiMcc = eUtranCgi.getMCC();
                            ecgiMnc = eUtranCgi.getMNC();
                            eci = eUtranCgi.getEci();
                            eNBId = eUtranCgi.getENodeBId();
                            ecgiCi = eUtranCgi.getCi();
                            JsonObject epsEUtranCgiJsonObject = new JsonObject();
                            writeMcc(ecgiMcc, epsEUtranCgiJsonObject);
                            writeMnc(ecgiMnc, epsEUtranCgiJsonObject);
                            writeEUtranEci(eci, epsEUtranCgiJsonObject);
                            writeENBId(eNBId, epsEUtranCgiJsonObject);
                            writeEUtranCellId(ecgiCi, epsEUtranCgiJsonObject);
                            epsLocationInfoJsonObject.add("ECGI", epsEUtranCgiJsonObject);
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getTrackingAreaId() != null) {
                        TrackingAreaId trackingAreaId = uda.getTrackingAreaId().getTrackingAreaId();
                        try {
                            taiMcc = trackingAreaId.getMCC();
                            taiMnc = trackingAreaId.getMNC();
                            tac = trackingAreaId.getTAC();
                            JsonObject epsTrackingAreaIdJsonObject = new JsonObject();
                            writeMcc(taiMcc, epsTrackingAreaIdJsonObject);
                            writeMnc(taiMnc, epsTrackingAreaIdJsonObject);
                            writeTrackingAreaCode(tac, epsTrackingAreaIdJsonObject);
                            epsLocationInfoJsonObject.add("TAI", epsTrackingAreaIdJsonObject);
                        } catch (MAPException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getEpsGeographicalInformation() != null) {
                        if (uda.getEpsGeographicalInformation().getGeographicalInformation() != null) {
                            epsGeographicalTypeOfShape = uda.getEpsGeographicalInformation().getGeographicalInformation().getTypeOfShape().name();
                            epsGeographicalLatitude = uda.getEpsGeographicalInformation().getGeographicalInformation().getLatitude();
                            epsGeographicalLongitude = uda.getEpsGeographicalInformation().getGeographicalInformation().getLongitude();
                            epsGeographicalUncertainty = uda.getEpsGeographicalInformation().getGeographicalInformation().getUncertainty();
                            JsonObject epsGeographicalInfoJsonObject = new JsonObject();
                            writeTypeOfShape(epsGeographicalTypeOfShape, epsGeographicalInfoJsonObject);
                            writeLatitude(epsGeographicalLatitude, epsGeographicalInfoJsonObject);
                            writeLongitude(epsGeographicalLongitude, epsGeographicalInfoJsonObject);
                            writeUncertainty(epsGeographicalUncertainty, epsGeographicalInfoJsonObject);
                            epsLocationInfoJsonObject.add("GeographicalInformation", epsGeographicalInfoJsonObject);
                        }
                    }
                    if (uda.getEpsGeodeticInformation() != null) {
                        if (uda.getEpsGeodeticInformation().getGeodeticInformation() != null) {
                            epsGeodeticTypeOfShape = uda.getEpsGeodeticInformation().getGeodeticInformation().getTypeOfShape().name();
                            epsGeodeticLatitude = uda.getEpsGeodeticInformation().getGeodeticInformation().getLatitude();
                            epsGeodeticLongitude = uda.getEpsGeodeticInformation().getGeodeticInformation().getLongitude();
                            epsGeodeticUncertainty = uda.getEpsGeodeticInformation().getGeodeticInformation().getUncertainty();
                            epsConfidence = uda.getEpsGeodeticInformation().getGeodeticInformation().getConfidence();
                            epsScreeningAndPresentationIndicators = uda.getEpsGeodeticInformation().getGeodeticInformation().getScreeningAndPresentationIndicators();
                            JsonObject epsGeodeticInfoJsonObject = new JsonObject();
                            writeTypeOfShape(epsGeodeticTypeOfShape, epsGeodeticInfoJsonObject);
                            writeLatitude(epsGeodeticLatitude, epsGeodeticInfoJsonObject);
                            writeLongitude(epsGeodeticLongitude, epsGeodeticInfoJsonObject);
                            writeUncertainty(epsGeodeticUncertainty, epsGeodeticInfoJsonObject);
                            writeConfidence(epsConfidence, epsGeodeticInfoJsonObject);
                            writeScreeningAndPresentationIndicators(epsScreeningAndPresentationIndicators, epsGeodeticInfoJsonObject);
                            epsLocationInfoJsonObject.add("GeodeticInformation", epsGeodeticInfoJsonObject);
                        }
                    }
                    if (uda.getMmeName() != null) {
                        mmeName = uda.getMmeName();
                        writeMmeName(mmeName, epsLocationInfoJsonObject);
                    }
                    if (uda.getEpsCurrentLocationInfoRetrieved() != null) {
                        epsCurrentLocationInfoRetrieved = uda.getEpsCurrentLocationInfoRetrieved();
                        if (epsCurrentLocationInfoRetrieved.equalsIgnoreCase("0") ||
                            epsCurrentLocationInfoRetrieved.equalsIgnoreCase("true"))
                            epsCurrentLocationInfoRetrieved = "true";
                        else if (epsCurrentLocationInfoRetrieved.equalsIgnoreCase("1") ||
                            epsCurrentLocationInfoRetrieved.equalsIgnoreCase("false"))
                            epsCurrentLocationInfoRetrieved = "false";
                        writeCurrentLocationRetrieved(Boolean.valueOf(epsCurrentLocationInfoRetrieved), epsLocationInfoJsonObject);
                    }
                    if (uda.getEpsAgeOfLocationInfo() != null) {
                        epsAol = uda.getEpsAgeOfLocationInfo();
                        writeAol(epsAol, epsLocationInfoJsonObject);
                        if (epsAol > 0) {
                            epsCurrentLocationInfoRetrieved = "false";
                            writeCurrentLocationRetrieved(Boolean.valueOf(epsCurrentLocationInfoRetrieved), epsLocationInfoJsonObject);
                        }
                    }
                    /* Commented out: getUserCSGInformationStr() method doesn't exist
                    if (uda.getUserCSGInformation() != null) {
                        epsCsgId = uda.getUserCSGInformation().getUserCSGInformationStr();
                        writeCSGId(epsCsgId, epsLocationInfoJsonObject);
                    }
                    */
                    if (uda.getEpsVisitedPLMNId() != null) {
                        if (uda.getEpsVisitedPLMNId().getVisitedPlmnId() != null) {
                            epsVPlmnIdMcc = uda.getEpsVisitedPLMNId().getVisitedPlmnId().getMcc();
                            epsVPlmnIdMnc = uda.getEpsVisitedPLMNId().getVisitedPlmnId().getMnc();
                            JsonObject epsVisitedPlmnIdJsonObject = new JsonObject();
                            writeVisitedPlmnIdMcc(epsVPlmnIdMcc, epsVisitedPlmnIdJsonObject);
                            writeVisitedPlmnIdMnc(epsVPlmnIdMnc, epsVisitedPlmnIdJsonObject);
                            epsLocationInfoJsonObject.add("VisitedPLMNId", epsVisitedPlmnIdJsonObject);
                        }
                    }
                    /* Commented out: writeTimeZone(), writeDaylightSavingTime() methods don't exist
                    if (uda.getEpsLocalTimeZone() != null) {
                        epsTimeZone = uda.getEpsLocalTimeZone().getTimeZone();
                        JsonObject epsLocalTimeZoneJsonObject = new JsonObject();
                        writeTimeZone(epsTimeZone, epsLocalTimeZoneJsonObject);
                        epsDaylightSavingTime = uda.getEpsLocalTimeZone().getDaylightSavingTime();
                        writeDaylightSavingTime(epsDaylightSavingTime, epsLocalTimeZoneJsonObject);
                        epsLocationInfoJsonObject.add("LocalTimeZone", epsLocalTimeZoneJsonObject);
                    }
                    */
                    if (uda.getEpsRatType() != null) {
                        epsRatType = uda.getEpsRatType();
                        writeRatType(epsRatType, epsLocationInfoJsonObject);
                    }
                }

                // 5GS Location Information
                if (uda.getSh5GSLocationInformation() != null) {
                    sh5gsLocationInfoJsonObject = new JsonObject();
                    if (uda.getShNRCellGlobalId() != null) {
                        NRCellGlobalId nrCellGlobalId = uda.getShNRCellGlobalId().getNRCellGlobalId();
                        try {
                            nrCgiMcc = nrCellGlobalId.getMCC();
                            nrCgiMnc = nrCellGlobalId.getMNC();
                            nrCgiNci = nrCellGlobalId.getNCI();
                            JsonObject nrCgiJsonObject = new JsonObject();
                            writeMcc(nrCgiMcc, nrCgiJsonObject);
                            writeMnc(nrCgiMnc, nrCgiJsonObject);
                            writeNrCellId(nrCgiNci, nrCgiJsonObject);
                            sh5gsLocationInfoJsonObject.add("NCGI", nrCgiJsonObject);
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getEutrancgi() != null) {
                        EUTRANCGI eUtranCgi = uda.getEutrancgi().getEutranCgi();
                        try {
                            sh5gsEcgiMcc = eUtranCgi.getMCC();
                            sh5gsEcgiMnc = eUtranCgi.getMNC();
                            sh5gsEci =eUtranCgi.getEci();
                            sh5gsENBid = eUtranCgi.getENodeBId();
                            sh5gsEcgiCi = eUtranCgi.getCi();
                            JsonObject sh5gsEUtranCgiJsonObject = new JsonObject();
                            writeMcc(sh5gsEcgiMcc, sh5gsEUtranCgiJsonObject);
                            writeMnc(sh5gsEcgiMnc, sh5gsEUtranCgiJsonObject);
                            writeEUtranEci(sh5gsEci, sh5gsEUtranCgiJsonObject);
                            writeENBId(sh5gsENBid, sh5gsEUtranCgiJsonObject);
                            writeEUtranCellId(sh5gsEcgiCi, sh5gsEUtranCgiJsonObject);
                            sh5gsLocationInfoJsonObject.add("ECGI", sh5gsEUtranCgiJsonObject);
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getTrackingAreaId() != null) {
                        TrackingAreaId trackingAreaId = uda.getTrackingAreaId().getTrackingAreaId();
                        try {
                            sh5gsTaiMcc = trackingAreaId.getMCC();
                            sh5gsTaiMnc = trackingAreaId.getMNC();
                            sh5gsTac = trackingAreaId.getTAC();
                            JsonObject sh5gsTrackingAreaIdJsonObject = new JsonObject();
                            writeMcc(sh5gsTaiMcc, sh5gsTrackingAreaIdJsonObject);
                            writeMnc(sh5gsTaiMnc, sh5gsTrackingAreaIdJsonObject);
                            writeTrackingAreaCode(sh5gsTac, sh5gsTrackingAreaIdJsonObject);
                            sh5gsLocationInfoJsonObject.add("TAI", sh5gsTrackingAreaIdJsonObject);
                        } catch (MAPException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (uda.getSh5GSGeographicalInformation() != null) {
                        if (uda.getSh5GSGeographicalInformation().getGeographicalInformation() != null) {
                            sh5gsGeographicalTypeOfShape = uda.getSh5GSGeographicalInformation().getGeographicalInformation().getTypeOfShape().name();
                            sh5gsGeographicalLatitude = uda.getSh5GSGeographicalInformation().getGeographicalInformation().getLatitude();
                            sh5gsGeographicalLongitude = uda.getSh5GSGeographicalInformation().getGeographicalInformation().getLongitude();
                            sh5gsGeographicalUncertainty = uda.getSh5GSGeographicalInformation().getGeographicalInformation().getUncertainty();
                            JsonObject sh5gsGeographicalInfoJsonObject = new JsonObject();
                            writeTypeOfShape(sh5gsGeographicalTypeOfShape, sh5gsGeographicalInfoJsonObject);
                            writeLatitude(sh5gsGeographicalLatitude, sh5gsGeographicalInfoJsonObject);
                            writeLongitude(sh5gsGeographicalLongitude, sh5gsGeographicalInfoJsonObject);
                            writeUncertainty(sh5gsGeographicalUncertainty, sh5gsGeographicalInfoJsonObject);
                            sh5gsLocationInfoJsonObject.add("GeographicalInformation", sh5gsGeographicalInfoJsonObject);
                        }
                    }
                    if (uda.getAmfAddress() != null) {
                        amfAddress = uda.getAmfAddress();
                        writeAmfAddress(amfAddress, sh5gsLocationInfoJsonObject);
                    }
                    if (uda.getSmsfAddress() != null) {
                        smsfAddress = uda.getSmsfAddress();
                        writeSmsfAddress(smsfAddress, sh5gsLocationInfoJsonObject);
                    }
                    if (uda.getSh5GSCurrentLocationInfoRetrieved() != null) {
                         sh5gsCurrentLocationInfoRetrieved = uda.getSh5GSCurrentLocationInfoRetrieved();
                        if (sh5gsCurrentLocationInfoRetrieved.equalsIgnoreCase("0") ||
                            sh5gsCurrentLocationInfoRetrieved.equalsIgnoreCase("true"))
                            sh5gsCurrentLocationInfoRetrieved = "true";
                        else if (sh5gsCurrentLocationInfoRetrieved.equalsIgnoreCase("1") ||
                            sh5gsCurrentLocationInfoRetrieved.equalsIgnoreCase("false"))
                            sh5gsCurrentLocationInfoRetrieved = "false";
                        writeCurrentLocationRetrieved(Boolean.valueOf(sh5gsCurrentLocationInfoRetrieved), sh5gsLocationInfoJsonObject);
                    }
                    if (uda.getSh5GSAgeOfLocationInfo() != null) {
                        sh5gsAol = uda.getSh5GSAgeOfLocationInfo();
                        writeAol(sh5gsAol, sh5gsLocationInfoJsonObject);
                        if (sh5gsAol > 0) {
                            sh5gsCurrentLocationInfoRetrieved = "false";
                            writeCurrentLocationRetrieved(Boolean.valueOf(sh5gsCurrentLocationInfoRetrieved), sh5gsLocationInfoJsonObject);
                        }
                    }
                    if (uda.getSh5gsVisitedPLMNId() != null) {
                        if (uda.getSh5gsVisitedPLMNId().getVisitedPlmnId() != null) {
                            sh5gsVPlmnIdMcc = uda.getSh5gsVisitedPLMNId().getVisitedPlmnId().getMcc();
                            sh5gsVPlmnIdMnc = uda.getSh5gsVisitedPLMNId().getVisitedPlmnId().getMnc();
                            JsonObject sh5gsVisitedPlmnIdJsonObject = new JsonObject();
                            writeVisitedPlmnIdMcc(sh5gsVPlmnIdMcc, sh5gsVisitedPlmnIdJsonObject);
                            writeVisitedPlmnIdMnc(sh5gsVPlmnIdMnc, sh5gsVisitedPlmnIdJsonObject);
                            sh5gsLocationInfoJsonObject.add("VisitedPLMNId", sh5gsVisitedPlmnIdJsonObject);
                        }
                    }
                    /* Commented out: writeTimeZone(), writeDaylightSavingTime() methods don't exist
                    if (uda.getSh5gsLocalTimeZone() != null) {
                        sh5gsTimeZone = uda.getSh5gsLocalTimeZone().getTimeZone();
                        JsonObject sh5gsLocalTimeZoneJsonObject = new JsonObject();
                        writeTimeZone(sh5gsTimeZone, sh5gsLocalTimeZoneJsonObject);
                        sh5gsDaylightSavingTime = uda.getSh5gsLocalTimeZone().getDaylightSavingTime();
                        writeDaylightSavingTime(sh5gsDaylightSavingTime, sh5gsLocalTimeZoneJsonObject);
                        sh5gsLocationInfoJsonObject.add("LocalTimeZone", sh5gsLocalTimeZoneJsonObject);
                    }
                    */
                    if (uda.getSh5gsRatType() != null) {
                        sh5gsRatType = uda.getSh5gsRatType();
                        writeRatType(sh5gsRatType, sh5gsLocationInfoJsonObject);
                    }
                }
            }
        }

        // Write Public Identifiers values from UDA
        if (publicIdentifiersJsonObject != null)
            udaJsonObject.add("PublicIdentifiers", publicIdentifiersJsonObject);

        // Write CS Location Information values from UDA which might include EPS Location Information values
        if (csLocationInfoJsonObject != null)
            udaJsonObject.add("CSLocationInformation", csLocationInfoJsonObject);

        // Write PS Location Information values from UDA
        if (psLocationInfoJsonObject != null)
            udaJsonObject.add("PSLocationInformation", psLocationInfoJsonObject);

        // Write EPS Location Information values from UDA
        if (epsLocationInfoJsonObject != null)
            udaJsonObject.add("EPSLocationInformation", epsLocationInfoJsonObject);

        // Write 5GS Location Information from UDA
        if (sh5gsLocationInfoJsonObject != null)
            udaJsonObject.add("5GSLocationInformation", sh5gsLocationInfoJsonObject);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(udaJsonObject);

    }
}
