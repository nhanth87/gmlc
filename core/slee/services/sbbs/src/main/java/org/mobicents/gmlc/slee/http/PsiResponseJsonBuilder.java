package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.map.PsiResponseParams;
import org.mobicents.gmlc.slee.primitives.EUTRANCGI;
import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.mobicents.gmlc.slee.primitives.RoutingAreaId;
import org.mobicents.gmlc.slee.primitives.RoutingAreaIdImpl;
import org.mobicents.gmlc.slee.primitives.TrackingAreaId;
import org.mobicents.gmlc.slee.primitives.TrackingAreaIdImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UsedRATType;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EUtranCgi;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NRCellGlobalId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NRTAId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TAId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.FQDN;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.EUtranCgiImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.NRTAIdImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.TAIdImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.LSAIdentityImpl;

import java.nio.charset.StandardCharsets;

import static org.mobicents.gmlc.slee.http.JsonWriter.bytesToHexString;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAddressPresentationRestrictedIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAmfAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeConfidence;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCurrentLocationRetrieved;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeENBId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranEci;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImei;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeInternalNetworkNumberIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLac;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLastRatType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLatitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLmsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLocationNumberAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLongitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLsaId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLsaLSB;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMSClassmark;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMSNetworkCapability;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMSRadioAccessCapability;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnpImsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnpMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnpRouteingNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnpStatus;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMscNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNatureOfAddressIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNotReachableReason;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNrCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNumberingPlanIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOddFlag;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeRoutingAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSaiPresent;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeScreeningAndPresentationIndicators;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeScreeningIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeServiceAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSubscriberState;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTime;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTrackingAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTypeOfShape;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertainty;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVlrNumber;
import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class PsiResponseJsonBuilder {

    protected static final Logger logger = Logger.getLogger(PsiResponseJsonBuilder.class);

    public PsiResponseJsonBuilder() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param psiResponseParams         Subscriber Information values gathered from PSI response event
     * @param imsi                      IMSI value used on PSI attempt (gathered from SRI or SRISM response or HTTP request)
     * @param msisdn                    MSISDN value of the target subscriber
     * @param lmsi                      LMSI value gathered from SRISM response event
     */
    public static String buildJsonResponseForPsi(PsiResponseParams psiResponseParams, String imsi, String msisdn, LMSI lmsi) throws MAPException {

        int csMcc, csMnc, csLac, csCiOrSac, psMcc, psMnc, psLac, psCiOrSac, ecgiMcc, ecgiMnc, ecgiCi, raiMcc, raiMnc, raiLac, rac, taiMcc, taiMnc, tac,
                nrCgiMcc, nrCgiMnc, nrTaiMcc, nrTaiMnc, nrTaiTac, vPlmnIdMcc, vPlmnIdMnc,
                natureOfAddressIndicator, numberingPlanIndicator, internalNetworkNumberIndicator, addressPresentationRestrictedIndicator, screeningIndicator,
                ageOfLocationInfo, geodeticConfidence, gprsGeodeticConfidence, epsGeodeticConfidence, geodeticScreeningAndPresentationIndicators,
                gprsGeodeticScreeningAndPresentationIndicators, epsGeodeticScreeningAndPresentationIndicators,
                nrGeodeticConfidence, nrGeodeticScreeningAndPresentationIndicators, year, month, day, hour, minute, second;
        csMcc = csMnc = csLac = csCiOrSac = psMcc = psMnc = psLac = psCiOrSac = ecgiMcc = ecgiMnc = ecgiCi = raiMcc = raiMnc = raiLac = rac = taiMcc = taiMnc = tac =
                nrCgiMcc = nrCgiMnc = nrTaiMcc = nrTaiMnc = nrTaiTac = vPlmnIdMcc = vPlmnIdMnc = -1;
        Integer mnpInfoResultNumberPortabilityStatus = null;
        String locationNumberAddressDigits, csSubscriberState, psSubscriberState, epsSubscriberState, notReachableReason, vlrNumber, mscNumber, mmeName, sgsnNumber, amfAddressString, lsaId,
                imei, lastUeActivityTime, lastRatType, mnpInfoResultMSISDN, mnpInfoResultIMSI, mnpInfoResultRouteingNumber, geographicalTypeOfShape, geodeticTypeOfShape, gprsGeographicalTypeOfShape,
                gprsGeodeticTypeOfShape, epsGeographicalTypeOfShape, epsGeodeticTypeOfShape, nrGeographicalTypeOfShape, nrGeodeticTypeOfShape, lmsiHexValue, msClassmark, msNetCap, msRASCap;
         csSubscriberState = psSubscriberState = epsSubscriberState = notReachableReason = imei = lastUeActivityTime = lastRatType =
                mnpInfoResultMSISDN = mnpInfoResultIMSI = mnpInfoResultRouteingNumber = lmsiHexValue = msClassmark = msNetCap = msRASCap = null;
        double geographicalLatitude, geographicalLongitude, geographicalUncertainty, geodeticLatitude, geodeticLongitude, geodeticUncertainty;
        double epsGeographicalLatitude, epsGeographicalLongitude, epsGeographicalUncertainty, epsGeodeticLatitude, epsGeodeticLongitude, epsGeodeticUncertainty;
        double gprsGeographicalLatitude, gprsGeographicalLongitude, gprsGeographicalUncertainty, gprsGeodeticLatitude, gprsGeodeticLongitude, gprsGeodeticUncertainty;
        double nrGeographicalLatitude, nrGeographicalLongitude, nrGeographicalUncertainty, nrGeodeticLatitude, nrGeodeticLongitude, nrGeodeticUncertainty;
        Long ecgiEci = null, ecgiENBId = null, nrCgiCi = null;
        boolean oddFlag, saiPresent, lsaUniversal;
        saiPresent = false;

        JsonObject psiSubscriberInformationJsonObject = new JsonObject();
        writeNetwork("GSM/UMTS", psiSubscriberInformationJsonObject);
        writeProtocol("MAP", psiSubscriberInformationJsonObject);
        writeOperation("PSI", psiSubscriberInformationJsonObject);
        writeOperationResult("SUCCESS", psiSubscriberInformationJsonObject);
        JsonObject psiCSLocationInformationJsonObject = null;
        JsonObject psiCsEPSLocationInformationJsonObject;
        JsonObject psiPSLocationInformationJsonObject = null;
        JsonObject psiEPSLocationInformationJsonObject = null;
        JsonObject psi5GSLocationInformationJsonObject = null;

        if (psiResponseParams != null) {

            if (psiResponseParams.getLocationInformation() != null) {
                psiCSLocationInformationJsonObject = new JsonObject();

                if (psiResponseParams.getLocationInformation().getSaiPresent()) {
                    saiPresent = true;
                }

                if (psiResponseParams.getLocationInformation().getLocationNumber() != null) {
                    if (psiResponseParams.getLocationInformation().getLocationNumber().getLocationNumber() != null) {
                        JsonObject locationNumberJsonObject = new JsonObject();
                        LocationNumber locationNumber = psiResponseParams.getLocationInformation().getLocationNumber().getLocationNumber();
                        oddFlag = locationNumber.isOddFlag();
                        natureOfAddressIndicator = locationNumber.getNatureOfAddressIndicator();
                        internalNetworkNumberIndicator = locationNumber.getInternalNetworkNumberIndicator();
                        numberingPlanIndicator = locationNumber.getNumberingPlanIndicator();
                        addressPresentationRestrictedIndicator = locationNumber.getAddressRepresentationRestrictedIndicator();
                        screeningIndicator = locationNumber.getScreeningIndicator();
                        locationNumberAddressDigits = locationNumber.getAddress();
                        // Write CS Location Information values
                        writeOddFlag(oddFlag, locationNumberJsonObject);
                        writeNatureOfAddressIndicator(natureOfAddressIndicator, locationNumberJsonObject);
                        writeInternalNetworkNumberIndicator(internalNetworkNumberIndicator, locationNumberJsonObject);
                        writeNumberingPlanIndicator(numberingPlanIndicator, locationNumberJsonObject);
                        writeAddressPresentationRestrictedIndicator(addressPresentationRestrictedIndicator, locationNumberJsonObject);
                        writeScreeningIndicator(screeningIndicator, locationNumberJsonObject);
                        writeLocationNumberAddress(locationNumberAddressDigits, locationNumberJsonObject);
                        psiCSLocationInformationJsonObject.add("LocationNumber", locationNumberJsonObject);
                    }
                }

                if (psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                    JsonObject csCgiOrLaiOrSaiJsonObject = new JsonObject();
                    if (psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                        csMcc = psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                        csMnc = psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                        csLac = psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                    } else if (psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                        csMcc = psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                        csMnc = psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                        csLac = psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                        csCiOrSac = psiResponseParams.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                    }
                    writeMcc(csMcc, csCgiOrLaiOrSaiJsonObject);
                    writeMnc(csMnc, csCgiOrLaiOrSaiJsonObject);
                    writeLac(csLac, csCgiOrLaiOrSaiJsonObject);
                    if (!saiPresent) {
                        if (csCiOrSac != -1) {
                            writeCellId(csCiOrSac, csCgiOrLaiOrSaiJsonObject);
                            psiCSLocationInformationJsonObject.add("CGI", csCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (csLac != -1)
                                psiCSLocationInformationJsonObject.add("LAI", csCgiOrLaiOrSaiJsonObject);
                        }
                    } else {
                        if (csCiOrSac != -1) {
                            writeServiceAreaCode(csCiOrSac, csCgiOrLaiOrSaiJsonObject);
                            psiCSLocationInformationJsonObject.add("SAI", csCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (csLac != -1)
                                psiCSLocationInformationJsonObject.add("LAI", csCgiOrLaiOrSaiJsonObject);
                        }
                    }
                }
                if (csCiOrSac != -1)
                    writeSaiPresent(saiPresent, psiCSLocationInformationJsonObject);

                if (psiResponseParams.getLocationInformation().getGeographicalInformation() != null) {
                    JsonObject psiGeographicalInformationJsonObject = new JsonObject();
                    geographicalLatitude = psiResponseParams.getLocationInformation().getGeographicalInformation().getLatitude();
                    geographicalLongitude = psiResponseParams.getLocationInformation().getGeographicalInformation().getLongitude();
                    geographicalTypeOfShape = psiResponseParams.getLocationInformation().getGeographicalInformation().getTypeOfShape().name();
                    geographicalUncertainty = psiResponseParams.getLocationInformation().getGeographicalInformation().getUncertainty();
                    writeTypeOfShape(geographicalTypeOfShape, psiGeographicalInformationJsonObject);
                    writeLatitude(geographicalLatitude, psiGeographicalInformationJsonObject);
                    writeLongitude(geographicalLongitude, psiGeographicalInformationJsonObject);
                    writeUncertainty(geographicalUncertainty, geographicalLatitude, geographicalLongitude, psiGeographicalInformationJsonObject);
                    psiCSLocationInformationJsonObject.add("GeographicalInformation", psiGeographicalInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation().getGeodeticInformation() != null) {
                    JsonObject psiGeodeticInformationJsonObject = new JsonObject();
                    geodeticLatitude = psiResponseParams.getLocationInformation().getGeodeticInformation().getLatitude();
                    geodeticLongitude = psiResponseParams.getLocationInformation().getGeodeticInformation().getLongitude();
                    geodeticTypeOfShape = psiResponseParams.getLocationInformation().getGeodeticInformation().getTypeOfShape().name();
                    geodeticUncertainty = psiResponseParams.getLocationInformation().getGeodeticInformation().getUncertainty();
                    geodeticConfidence = psiResponseParams.getLocationInformation().getGeodeticInformation().getConfidence();
                    geodeticScreeningAndPresentationIndicators = psiResponseParams.getLocationInformation().getGeodeticInformation().getScreeningAndPresentationIndicators();
                    writeTypeOfShape(geodeticTypeOfShape, psiGeodeticInformationJsonObject);
                    writeLatitude(geodeticLatitude, psiGeodeticInformationJsonObject);
                    writeLongitude(geodeticLongitude, psiGeodeticInformationJsonObject);
                    writeUncertainty(geodeticUncertainty, geodeticLatitude, geodeticLongitude, psiGeodeticInformationJsonObject);
                    writeConfidence(geodeticConfidence, geodeticLatitude, geodeticLongitude, psiGeodeticInformationJsonObject);
                    writeScreeningAndPresentationIndicators(geodeticScreeningAndPresentationIndicators, geodeticLatitude, geodeticLongitude, psiGeodeticInformationJsonObject);
                    psiCSLocationInformationJsonObject.add("GeodeticInformation", psiGeodeticInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation().getAgeOfLocationInformation() != null) {
                    ageOfLocationInfo = psiResponseParams.getLocationInformation().getAgeOfLocationInformation();
                    writeAol(ageOfLocationInfo, psiCSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation().getCurrentLocationRetrieved()) {
                    writeCurrentLocationRetrieved(true, psiCSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation().getVlrNumber() != null) {
                    vlrNumber = psiResponseParams.getLocationInformation().getVlrNumber().getAddress();
                    writeVlrNumber(vlrNumber, psiCSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation().getMscNumber() != null) {
                    mscNumber = psiResponseParams.getLocationInformation().getMscNumber().getAddress();
                    writeMscNumber(mscNumber, psiCSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation().getLocationInformationEPS() != null) {
                    psiCsEPSLocationInformationJsonObject = new JsonObject();

                    if (psiResponseParams.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                        JsonObject csLocInfoEutranCgiJsonObject = new JsonObject();
                        EUTRANCGI eutrancgi = new EUTRANCGIImpl(psiResponseParams.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity().getData());
                        try {
                            ecgiMcc = eutrancgi.getMCC();
                            ecgiMnc = eutrancgi.getMNC();
                            ecgiEci = eutrancgi.getEci();
                            ecgiENBId = eutrancgi.getENodeBId();
                            ecgiCi = eutrancgi.getCi();
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                        writeMcc(ecgiMcc, csLocInfoEutranCgiJsonObject);
                        writeMnc(ecgiMnc, csLocInfoEutranCgiJsonObject);
                        writeEUtranEci(ecgiEci, csLocInfoEutranCgiJsonObject);
                        writeENBId(ecgiENBId, csLocInfoEutranCgiJsonObject);
                        writeEUtranCellId(ecgiCi, csLocInfoEutranCgiJsonObject);
                        psiCsEPSLocationInformationJsonObject.add("ECGI", csLocInfoEutranCgiJsonObject);
                    }

                    if (psiResponseParams.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                        JsonObject csLocInfoTaiJsonObject = new JsonObject();
                        TrackingAreaId tai = new TrackingAreaIdImpl(psiResponseParams.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getData());
                        try {
                            taiMcc = tai.getMCC();
                            taiMnc = tai.getMNC();
                            tac = tai.getTAC();
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                        writeMcc(taiMcc, csLocInfoTaiJsonObject);
                        writeMnc(taiMnc, csLocInfoTaiJsonObject);
                        writeTrackingAreaCode(tac, csLocInfoTaiJsonObject);
                        psiCsEPSLocationInformationJsonObject.add("TAI", csLocInfoTaiJsonObject);
                    }

                    if (psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null) {
                        JsonObject csLocInfoEPSGeographicalInformationJsonObject = new JsonObject();
                        epsGeographicalLatitude = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLatitude();
                        epsGeographicalLongitude = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLongitude();
                        epsGeographicalTypeOfShape = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getTypeOfShape().name();
                        epsGeographicalUncertainty = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getUncertainty();
                        writeTypeOfShape(epsGeographicalTypeOfShape, csLocInfoEPSGeographicalInformationJsonObject);
                        writeLatitude(epsGeographicalLatitude, csLocInfoEPSGeographicalInformationJsonObject);
                        writeLongitude(epsGeographicalLongitude, csLocInfoEPSGeographicalInformationJsonObject);
                        writeUncertainty(epsGeographicalUncertainty, epsGeographicalLatitude, epsGeographicalLongitude, csLocInfoEPSGeographicalInformationJsonObject);
                        psiCsEPSLocationInformationJsonObject.add("GeographicalInformation", csLocInfoEPSGeographicalInformationJsonObject);
                    }

                    if (psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
                        JsonObject csLocInfoEPSGeodeticInformationJsonObject = new JsonObject();
                        epsGeodeticLatitude = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLatitude();
                        epsGeodeticLongitude = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLongitude();
                        epsGeodeticTypeOfShape = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape().name();
                        epsGeodeticUncertainty = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getUncertainty();
                        epsGeodeticConfidence = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getConfidence();
                        epsGeodeticScreeningAndPresentationIndicators = psiResponseParams.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getScreeningAndPresentationIndicators();
                        writeTypeOfShape(epsGeodeticTypeOfShape, csLocInfoEPSGeodeticInformationJsonObject);
                        writeLatitude(epsGeodeticLatitude, csLocInfoEPSGeodeticInformationJsonObject);
                        writeLongitude(epsGeodeticLongitude, csLocInfoEPSGeodeticInformationJsonObject);
                        writeUncertainty(epsGeodeticUncertainty, epsGeodeticLatitude, epsGeodeticLongitude, csLocInfoEPSGeodeticInformationJsonObject);
                        writeConfidence(epsGeodeticConfidence, epsGeodeticLatitude, epsGeodeticLongitude, csLocInfoEPSGeodeticInformationJsonObject);
                        writeScreeningAndPresentationIndicators(epsGeodeticScreeningAndPresentationIndicators, epsGeodeticLatitude, epsGeodeticLongitude, csLocInfoEPSGeodeticInformationJsonObject);
                        psiCsEPSLocationInformationJsonObject.add("GeodeticInformation", csLocInfoEPSGeodeticInformationJsonObject);
                    }

                    if (psiResponseParams.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation() != null) {
                        ageOfLocationInfo = psiResponseParams.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation();
                        writeAol(ageOfLocationInfo, psiCsEPSLocationInformationJsonObject);
                    }

                    if (psiResponseParams.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved()) {
                        writeCurrentLocationRetrieved(true, psiCsEPSLocationInformationJsonObject);
                    }

                    if (psiResponseParams.getLocationInformation().getLocationInformationEPS().getMmeName() != null) {
                        mmeName = new String(psiResponseParams.getLocationInformation().getLocationInformationEPS().getMmeName().getData());
                        writeMmeName(mmeName, psiCsEPSLocationInformationJsonObject);
                    }

                    // Write EPS Location Information values
                    psiCSLocationInformationJsonObject.add("EPSLocationInformation", psiCsEPSLocationInformationJsonObject);
                }
            }

            if (psiResponseParams.getLocationInformationGPRS() != null) {
                psiPSLocationInformationJsonObject = new JsonObject();

                if (psiResponseParams.getLocationInformationGPRS().isSaiPresent())
                    saiPresent = true;

                if (psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                    JsonObject psCgiOrLaiOrSaiJsonObject = new JsonObject();
                    if (psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                        psMcc = psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                        psMnc = psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                        psLac = psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                    } else if (psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                        psMcc = psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                        psMnc = psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                        psLac = psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                        psCiOrSac = psiResponseParams.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                    }
                    writeMcc(psMcc, psCgiOrLaiOrSaiJsonObject);
                    writeMnc(psMnc, psCgiOrLaiOrSaiJsonObject);
                    writeLac(psLac, psCgiOrLaiOrSaiJsonObject);
                    if (!saiPresent) {
                        if (psCiOrSac != -1) {
                            writeCellId(psCiOrSac, psCgiOrLaiOrSaiJsonObject);
                            psiPSLocationInformationJsonObject.add("CGI", psCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (psLac != -1)
                                psiPSLocationInformationJsonObject.add("LAI", psCgiOrLaiOrSaiJsonObject);
                        }
                    } else {
                        if (psCiOrSac != -1) {
                            writeServiceAreaCode(psCiOrSac, psCgiOrLaiOrSaiJsonObject);
                            psiPSLocationInformationJsonObject.add("SAI", psCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (psLac != -1)
                                psiPSLocationInformationJsonObject.add("LAI", psCgiOrLaiOrSaiJsonObject);
                        }
                    }
                }

                if (psCiOrSac != -1)
                    writeSaiPresent(saiPresent, psiPSLocationInformationJsonObject);

                if (psiResponseParams.getLocationInformationGPRS().getRouteingAreaIdentity() != null) {
                    JsonObject raiJsonObject = new JsonObject();
                    RoutingAreaId rai = new RoutingAreaIdImpl(psiResponseParams.getLocationInformationGPRS().getRouteingAreaIdentity().getData());
                    try {
                        raiMcc = rai.getMCC();
                        raiMnc = rai.getMNC();
                        raiLac = rai.getLAC();
                        rac = rai.getRAC();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    writeMcc(raiMcc, raiJsonObject);
                    writeMnc(raiMnc, raiJsonObject);
                    writeLac(raiLac, raiJsonObject);
                    writeRoutingAreaCode(rac, raiJsonObject);
                    psiPSLocationInformationJsonObject.add("RAI", raiJsonObject);
                }

                if (psiResponseParams.getLocationInformationGPRS().getLSAIdentity() != null) {
                    JsonObject lsaJsonObject = new JsonObject();
                    LSAIdentity lsaIdentity = new LSAIdentityImpl(psiResponseParams.getLocationInformationGPRS().getLSAIdentity().getData());
                    lsaUniversal = lsaIdentity.isPlmnSignificantLSA(); // isPlmnSignificantLSA means the opposite in jSS7 implementation
                    lsaId = new String(psiResponseParams.getLocationInformationGPRS().getLSAIdentity().getData());
                    writeLsaLSB(lsaUniversal, lsaJsonObject);
                    writeLsaId(lsaId, lsaJsonObject);
                    psiPSLocationInformationJsonObject.add("LSA", lsaJsonObject);
                }

                if (psiResponseParams.getLocationInformationGPRS().getGeographicalInformation() != null) {
                    JsonObject psiGPRSGeographicalInformationJsonObject = new JsonObject();
                    gprsGeographicalLatitude = psiResponseParams.getLocationInformationGPRS().getGeographicalInformation().getLatitude();
                    gprsGeographicalLongitude = psiResponseParams.getLocationInformationGPRS().getGeographicalInformation().getLongitude();
                    gprsGeographicalUncertainty = psiResponseParams.getLocationInformationGPRS().getGeographicalInformation().getUncertainty();
                    gprsGeographicalTypeOfShape = psiResponseParams.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape().name();
                    writeTypeOfShape(gprsGeographicalTypeOfShape, psiGPRSGeographicalInformationJsonObject);
                    writeLatitude(gprsGeographicalLatitude, psiGPRSGeographicalInformationJsonObject);
                    writeLongitude(gprsGeographicalLongitude, psiGPRSGeographicalInformationJsonObject);
                    writeUncertainty(gprsGeographicalUncertainty, gprsGeographicalLatitude, gprsGeographicalLongitude, psiGPRSGeographicalInformationJsonObject);
                    psiPSLocationInformationJsonObject.add("GeographicalInformation", psiGPRSGeographicalInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformationGPRS().getGeodeticInformation() != null) {
                    JsonObject psiGPRSGeodeticInformationJsonObject = new JsonObject();
                    gprsGeodeticTypeOfShape = psiResponseParams.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape().name();
                    gprsGeodeticLatitude = psiResponseParams.getLocationInformationGPRS().getGeodeticInformation().getLatitude();
                    gprsGeodeticLongitude = psiResponseParams.getLocationInformationGPRS().getGeodeticInformation().getLongitude();
                    gprsGeodeticUncertainty = psiResponseParams.getLocationInformationGPRS().getGeodeticInformation().getUncertainty();
                    gprsGeodeticConfidence = psiResponseParams.getLocationInformationGPRS().getGeodeticInformation().getConfidence();
                    gprsGeodeticScreeningAndPresentationIndicators = psiResponseParams.getLocationInformationGPRS().getGeodeticInformation().getScreeningAndPresentationIndicators();
                    writeTypeOfShape(gprsGeodeticTypeOfShape, psiGPRSGeodeticInformationJsonObject);
                    writeLatitude(gprsGeodeticLatitude, psiGPRSGeodeticInformationJsonObject);
                    writeLongitude(gprsGeodeticLongitude, psiGPRSGeodeticInformationJsonObject);
                    writeUncertainty(gprsGeodeticUncertainty, gprsGeodeticLatitude, gprsGeodeticLongitude, psiGPRSGeodeticInformationJsonObject);
                    writeConfidence(gprsGeodeticConfidence, gprsGeodeticLatitude, gprsGeodeticLongitude, psiGPRSGeodeticInformationJsonObject);
                    writeScreeningAndPresentationIndicators(gprsGeodeticScreeningAndPresentationIndicators, gprsGeodeticLatitude, gprsGeodeticLongitude, psiGPRSGeodeticInformationJsonObject);
                    psiPSLocationInformationJsonObject.add("GeodeticInformation", psiGPRSGeodeticInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformationGPRS().getAgeOfLocationInformation() != null) {
                    ageOfLocationInfo = psiResponseParams.getLocationInformationGPRS().getAgeOfLocationInformation();
                    writeAol(ageOfLocationInfo, psiPSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformationGPRS().isCurrentLocationRetrieved()) {
                    writeCurrentLocationRetrieved(true, psiPSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformationGPRS().getSGSNNumber() != null) {
                    sgsnNumber = psiResponseParams.getLocationInformationGPRS().getSGSNNumber().getAddress();
                    writeSgsnNumber(sgsnNumber, psiPSLocationInformationJsonObject);
                }
            }

            if (psiResponseParams.getMnpInfoRes() != null) {
                if (psiResponseParams.getMnpInfoRes().getNumberPortabilityStatus() != null) {
                    mnpInfoResultNumberPortabilityStatus = psiResponseParams.getMnpInfoRes().getNumberPortabilityStatus().getType();
                }
                if (psiResponseParams.getMnpInfoRes().getMSISDN() != null) {
                    mnpInfoResultMSISDN = psiResponseParams.getMnpInfoRes().getMSISDN().getAddress();
                }
                if (psiResponseParams.getMnpInfoRes().getIMSI() != null) {
                    mnpInfoResultIMSI = new String(psiResponseParams.getMnpInfoRes().getIMSI().getData().getBytes());
                }
                if (psiResponseParams.getMnpInfoRes().getRouteingNumber() != null) {
                    mnpInfoResultRouteingNumber = psiResponseParams.getMnpInfoRes().getRouteingNumber().getRouteingNumber();
                }
            }

            if (psiResponseParams.getImei() != null) {
                imei = psiResponseParams.getImei().getIMEI();
            }

            if (psiResponseParams.getSubscriberState() != null) {
                csSubscriberState = psiResponseParams.getSubscriberState().getSubscriberStateChoice().toString();
                if (psiResponseParams.getSubscriberState().getNotReachableReason() != null)
                    notReachableReason = psiResponseParams.getSubscriberState().getNotReachableReason().name();
            }
            if (psiResponseParams.getPsSubscriberState() != null) {
                psSubscriberState = psiResponseParams.getPsSubscriberState().getChoice().toString();
                if (psiResponseParams.getPsSubscriberState().getNetDetNotReachable() != null)
                    notReachableReason = psiResponseParams.getPsSubscriberState().getNetDetNotReachable().name();
            }

            if (psiResponseParams.getMsClassmark2() != null) {
                msClassmark = bytesToHexString(psiResponseParams.getMsClassmark2().getData());
            }

            if (psiResponseParams.getGprsMSClass() != null) {
                msNetCap = bytesToHexString(psiResponseParams.getGprsMSClass().getMSNetworkCapability().getData());
                msRASCap = bytesToHexString(psiResponseParams.getGprsMSClass().getMSRadioAccessCapability().getData());
            }

            if (psiResponseParams.getLastUEActivityTime() != null) {
                year = psiResponseParams.getLastUEActivityTime().getYear();
                month = psiResponseParams.getLastUEActivityTime().getMonth();
                day = psiResponseParams.getLastUEActivityTime().getDay();
                hour = psiResponseParams.getLastUEActivityTime().getHour();
                minute = psiResponseParams.getLastUEActivityTime().getMinute();
                second = psiResponseParams.getLastUEActivityTime().getSecond();
                lastUeActivityTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
            }

            if (psiResponseParams.getLastRATType() != null) {
                UsedRATType usedRATType = UsedRATType.getInstance(psiResponseParams.getLastRATType().getCode());
                lastRatType = usedRATType.name();
            }

            // TODO psiResponseParams.getTimeZone() & psiResponseParams.getDaylightSavingTime()

            if (psiResponseParams.getEpsSubscriberState() != null) {
                epsSubscriberState = psiResponseParams.getEpsSubscriberState().getChoice().toString();
                if (psiResponseParams.getEpsSubscriberState().getNetDetNotReachable() != null)
                    notReachableReason = psiResponseParams.getEpsSubscriberState().getNetDetNotReachable().name();
            }

            if (psiResponseParams.getLocationInformationEPS() != null) {
                JsonObject epsLocInfoEutranCgiJsonObject = new JsonObject();
                psiEPSLocationInformationJsonObject = new JsonObject();
                if (psiResponseParams.getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                    EUtranCgi eUtranCgi = new EUtranCgiImpl(psiResponseParams.getLocationInformationEPS().getEUtranCellGlobalIdentity().getData());
                    try {
                        ecgiMcc = eUtranCgi.getMCC();
                        ecgiMnc = eUtranCgi.getMNC();
                        ecgiEci = eUtranCgi.getEci();
                        ecgiENBId = eUtranCgi.getENodeBId();
                        ecgiCi = eUtranCgi.getCi();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    writeMcc(ecgiMcc, epsLocInfoEutranCgiJsonObject);
                    writeMnc(ecgiMnc, epsLocInfoEutranCgiJsonObject);
                    writeEUtranEci(ecgiEci, epsLocInfoEutranCgiJsonObject);
                    writeENBId(ecgiENBId, epsLocInfoEutranCgiJsonObject);
                    writeEUtranCellId(ecgiCi, epsLocInfoEutranCgiJsonObject);
                    psiEPSLocationInformationJsonObject.add("ECGI", epsLocInfoEutranCgiJsonObject);
                }

                if (psiResponseParams.getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                    JsonObject epsLocInfoTaiJsonObject = new JsonObject();
                    TAId tai = new TAIdImpl(psiResponseParams.getLocationInformationEPS().getTrackingAreaIdentity().getData());
                    try {
                        taiMcc = tai.getMCC();
                        taiMnc = tai.getMNC();
                        tac = tai.getTAC();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    writeMcc(taiMcc, epsLocInfoTaiJsonObject);
                    writeMnc(taiMnc, epsLocInfoTaiJsonObject);
                    writeTrackingAreaCode(tac, epsLocInfoTaiJsonObject);
                    psiEPSLocationInformationJsonObject.add("TAI", epsLocInfoTaiJsonObject);
                }

                if (psiResponseParams.getLocationInformationEPS().getGeographicalInformation() != null) {
                    JsonObject psiNonCsEpsGeographicalInformationJsonObject = new JsonObject();
                    epsGeographicalLatitude = psiResponseParams.getLocationInformationEPS().getGeographicalInformation().getLatitude();
                    epsGeographicalLongitude = psiResponseParams.getLocationInformationEPS().getGeographicalInformation().getLongitude();
                    epsGeographicalTypeOfShape = psiResponseParams.getLocationInformationEPS().getGeographicalInformation().getTypeOfShape().name();
                    epsGeographicalUncertainty = psiResponseParams.getLocationInformationEPS().getGeographicalInformation().getUncertainty();
                    writeTypeOfShape(epsGeographicalTypeOfShape, psiNonCsEpsGeographicalInformationJsonObject);
                    writeLatitude(epsGeographicalLatitude, psiNonCsEpsGeographicalInformationJsonObject);
                    writeLongitude(epsGeographicalLongitude, psiNonCsEpsGeographicalInformationJsonObject);
                    writeUncertainty(epsGeographicalUncertainty, epsGeographicalLatitude, epsGeographicalLongitude, psiNonCsEpsGeographicalInformationJsonObject);
                    psiEPSLocationInformationJsonObject.add("GeographicalInformation", psiNonCsEpsGeographicalInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformationEPS().getGeodeticInformation() != null) {
                    JsonObject psiNonCsEpsGeodeticInformationJsonObject = new JsonObject();
                    epsGeodeticLatitude = psiResponseParams.getLocationInformationEPS().getGeodeticInformation().getLatitude();
                    epsGeodeticLongitude = psiResponseParams.getLocationInformationEPS().getGeodeticInformation().getLongitude();
                    epsGeodeticTypeOfShape = psiResponseParams.getLocationInformationEPS().getGeodeticInformation().getTypeOfShape().name();
                    epsGeodeticUncertainty = psiResponseParams.getLocationInformationEPS().getGeodeticInformation().getUncertainty();
                    epsGeodeticConfidence = psiResponseParams.getLocationInformationEPS().getGeodeticInformation().getConfidence();
                    epsGeodeticScreeningAndPresentationIndicators = psiResponseParams.getLocationInformationEPS().getGeodeticInformation().getScreeningAndPresentationIndicators();
                    writeTypeOfShape(epsGeodeticTypeOfShape, psiNonCsEpsGeodeticInformationJsonObject);
                    writeLatitude(epsGeodeticLatitude, psiNonCsEpsGeodeticInformationJsonObject);
                    writeLongitude(epsGeodeticLongitude, psiNonCsEpsGeodeticInformationJsonObject);
                    writeUncertainty(epsGeodeticUncertainty, epsGeodeticLatitude, epsGeodeticLongitude, psiNonCsEpsGeodeticInformationJsonObject);
                    writeConfidence(epsGeodeticConfidence, epsGeodeticLatitude, epsGeodeticLongitude, psiNonCsEpsGeodeticInformationJsonObject);
                    writeScreeningAndPresentationIndicators(epsGeodeticScreeningAndPresentationIndicators, epsGeodeticLatitude, epsGeodeticLongitude, psiNonCsEpsGeodeticInformationJsonObject);
                    psiEPSLocationInformationJsonObject.add("GeodeticInformation", psiNonCsEpsGeodeticInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformationEPS().getAgeOfLocationInformation() != null) {
                    ageOfLocationInfo = psiResponseParams.getLocationInformationEPS().getAgeOfLocationInformation();
                    writeAol(ageOfLocationInfo, psiEPSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformationEPS().getCurrentLocationRetrieved()) {
                    writeCurrentLocationRetrieved(true, psiEPSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformationEPS().getMmeName() != null) {
                    mmeName = new String(psiResponseParams.getLocationInformationEPS().getMmeName().getData());
                    writeMmeName(mmeName, psiEPSLocationInformationJsonObject);
                }
            }

            if (psiResponseParams.getLocationInformation5GS() != null) {
                psi5GSLocationInformationJsonObject = new JsonObject();

                if (psiResponseParams.getLocationInformation5GS().getNRCellGlobalId() != null) {
                    JsonObject nrCgiJsonObject = new JsonObject();
                    NRCellGlobalId nrCellGlobalId = psiResponseParams.getLocationInformation5GS().getNRCellGlobalId();
                    try {
                        nrCgiMcc = nrCellGlobalId.getMCC();
                        nrCgiMnc = nrCellGlobalId.getMNC();
                        nrCgiCi = nrCellGlobalId.getNCI();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    writeMcc(nrCgiMcc, nrCgiJsonObject);
                    writeMnc(nrCgiMnc, nrCgiJsonObject);
                    writeNrCellId(nrCgiCi, nrCgiJsonObject);
                    psi5GSLocationInformationJsonObject.add("NCGI", nrCgiJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS().getNRTAId() != null) {
                    JsonObject nrTaiJsonObject = new JsonObject();
                    NRTAId nrtaId = new NRTAIdImpl(psiResponseParams.getLocationInformation5GS().getNRTAId().getData());
                    try {
                        nrTaiMcc = nrtaId.getMCC();
                        nrTaiMnc = nrtaId.getMNC();
                        nrTaiTac = nrtaId.getNrTAC();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    writeMcc(nrTaiMcc, nrTaiJsonObject);
                    writeMnc(nrTaiMnc, nrTaiJsonObject);
                    writeTrackingAreaCode(nrTaiTac, nrTaiJsonObject);
                    psi5GSLocationInformationJsonObject.add("NR-TAI", nrTaiJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS().getEUtranCgi() != null) {
                    JsonObject locInfo5gsEutranCgiJsonObject = new JsonObject();
                    EUtranCgi eUtranCgi = new EUtranCgiImpl(psiResponseParams.getLocationInformation5GS().getEUtranCgi().getData());
                    try {
                        ecgiMcc = eUtranCgi.getMCC();
                        ecgiMnc = eUtranCgi.getMNC();
                        ecgiEci = eUtranCgi.getEci();
                        ecgiENBId = eUtranCgi.getENodeBId();
                        ecgiCi = eUtranCgi.getCi();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    writeMcc(ecgiMcc, locInfo5gsEutranCgiJsonObject);
                    writeMnc(ecgiMnc, locInfo5gsEutranCgiJsonObject);
                    writeEUtranEci(ecgiEci, locInfo5gsEutranCgiJsonObject);
                    writeENBId(ecgiENBId, locInfo5gsEutranCgiJsonObject);
                    writeEUtranCellId(ecgiCi, locInfo5gsEutranCgiJsonObject);
                    psi5GSLocationInformationJsonObject.add("ECGI", locInfo5gsEutranCgiJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS().getTAId() != null) {
                    JsonObject locInfo5gsTaiJsonObject = new JsonObject();
                    TAId tai = new TAIdImpl(psiResponseParams.getLocationInformation5GS().getTAId().getData());
                    try {
                        taiMcc = tai.getMCC();
                        taiMnc = tai.getMNC();
                        tac = tai.getTAC();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    writeMcc(taiMcc, locInfo5gsTaiJsonObject);
                    writeMnc(taiMnc, locInfo5gsTaiJsonObject);
                    writeTrackingAreaCode(tac, locInfo5gsTaiJsonObject);
                    psi5GSLocationInformationJsonObject.add("TAI", locInfo5gsTaiJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS().getGeographicalInformation() != null) {
                    JsonObject psi5gsGeographicalInformationJsonObject = new JsonObject();
                    nrGeographicalTypeOfShape = psiResponseParams.getLocationInformation5GS().getGeographicalInformation().getTypeOfShape().name();
                    nrGeographicalLatitude = psiResponseParams.getLocationInformation5GS().getGeographicalInformation().getLatitude();
                    nrGeographicalLongitude = psiResponseParams.getLocationInformation5GS().getGeographicalInformation().getLongitude();
                    nrGeographicalUncertainty = psiResponseParams.getLocationInformation5GS().getGeographicalInformation().getUncertainty();
                    writeTypeOfShape(nrGeographicalTypeOfShape, psi5gsGeographicalInformationJsonObject);
                    writeLatitude(nrGeographicalLatitude, psi5gsGeographicalInformationJsonObject);
                    writeLongitude(nrGeographicalLongitude, psi5gsGeographicalInformationJsonObject);
                    writeUncertainty(nrGeographicalUncertainty, nrGeographicalLatitude, nrGeographicalLongitude, psi5gsGeographicalInformationJsonObject);
                    psi5GSLocationInformationJsonObject.add("GeographicalInformation", psi5gsGeographicalInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS().getGeodeticInformation() != null) {
                    JsonObject psi5gsGeodeticInformationJsonObject = new JsonObject();
                    nrGeodeticTypeOfShape = psiResponseParams.getLocationInformation5GS().getGeodeticInformation().getTypeOfShape().name();
                    nrGeodeticLatitude = psiResponseParams.getLocationInformation5GS().getGeodeticInformation().getLatitude();
                    nrGeodeticLongitude = psiResponseParams.getLocationInformation5GS().getGeodeticInformation().getLongitude();
                    nrGeodeticUncertainty = psiResponseParams.getLocationInformation5GS().getGeodeticInformation().getUncertainty();
                    nrGeodeticConfidence = psiResponseParams.getLocationInformation5GS().getGeodeticInformation().getConfidence();
                    nrGeodeticScreeningAndPresentationIndicators = psiResponseParams.getLocationInformation5GS().getGeodeticInformation().getScreeningAndPresentationIndicators();
                    writeTypeOfShape(nrGeodeticTypeOfShape, psi5gsGeodeticInformationJsonObject);
                    writeLatitude(nrGeodeticLatitude, psi5gsGeodeticInformationJsonObject);
                    writeLongitude(nrGeodeticLongitude, psi5gsGeodeticInformationJsonObject);
                    writeUncertainty(nrGeodeticUncertainty, nrGeodeticLatitude, nrGeodeticLongitude, psi5gsGeodeticInformationJsonObject);
                    writeConfidence(nrGeodeticConfidence, nrGeodeticLatitude, nrGeodeticLongitude, psi5gsGeodeticInformationJsonObject);
                    writeScreeningAndPresentationIndicators(nrGeodeticScreeningAndPresentationIndicators, nrGeodeticLatitude, nrGeodeticLongitude, psi5gsGeodeticInformationJsonObject);
                    psi5GSLocationInformationJsonObject.add("GeodeticInformation", psi5gsGeodeticInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS().getAMFAddress() != null) {
                    FQDN amfAddress = psiResponseParams.getLocationInformation5GS().getAMFAddress();
                    amfAddressString = new String(amfAddress.getData(), StandardCharsets.UTF_8);
                    writeAmfAddress(amfAddressString, psi5GSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS().getAgeOfLocationInformation() != null) {
                    ageOfLocationInfo = psiResponseParams.getLocationInformation5GS().getAgeOfLocationInformation();
                    writeAol(ageOfLocationInfo, psi5GSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS().isCurrentLocationRetrieved()) {
                    writeCurrentLocationRetrieved(true, psi5GSLocationInformationJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS() != null) {
                    JsonObject vPlmnIdJsonObject = new JsonObject();
                    if (psiResponseParams.getLocationInformation5GS().getVPlmnId() != null) {
                        PlmnId vPlmnId = psiResponseParams.getLocationInformation5GS().getVPlmnId();
                        try {
                            vPlmnIdMcc = vPlmnId.getMcc();
                            vPlmnIdMnc = vPlmnId.getMnc();
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                        writeMcc(vPlmnIdMcc, vPlmnIdJsonObject);
                        writeMnc(vPlmnIdMnc, vPlmnIdJsonObject);
                    }
                    psi5GSLocationInformationJsonObject.add("VisitedPLMNId", vPlmnIdJsonObject);
                }

                if (psiResponseParams.getLocationInformation5GS().getUsedRATType() != null) {
                    JsonObject nrUsedRatTypeJsonObject = new JsonObject();
                    UsedRATType usedRATType = UsedRATType.getInstance(psiResponseParams.getLocationInformation5GS().getUsedRATType().getCode());
                    String ratType = usedRATType.name();
                    writeLastRatType(ratType, nrUsedRatTypeJsonObject);
                    psi5GSLocationInformationJsonObject.add("Used-RAT-Type", nrUsedRatTypeJsonObject);
                }
            }
        }

        // Write Subscriber Information values gathered via SRI/SRISM or PSI
        if (msisdn != null)
            writeMsisdn(msisdn, psiSubscriberInformationJsonObject);
        if (imsi != null)
            writeImsi(imsi, psiSubscriberInformationJsonObject);
        if (imei != null)
            writeImei(imei, psiSubscriberInformationJsonObject);
        if (lmsi != null) {
            if (lmsi.getData() != null)
                lmsiHexValue = bytesToHex(lmsi.getData());
            writeLmsi(lmsiHexValue, psiSubscriberInformationJsonObject);
        }

        // Write CS Location Information values which might include EPS Location Information values
        if (psiCSLocationInformationJsonObject != null)
            psiSubscriberInformationJsonObject.add("CSLocationInformation", psiCSLocationInformationJsonObject);

        // Write GPRS Location Information values
        if (psiPSLocationInformationJsonObject != null)
            psiSubscriberInformationJsonObject.add("PSLocationInformation", psiPSLocationInformationJsonObject);

        // Write EPS Location Information values (not included in CS Location Information)
        if (psiEPSLocationInformationJsonObject != null)
            psiSubscriberInformationJsonObject.add("EPSLocationInformation", psiEPSLocationInformationJsonObject);

        // Write 5GS Location Information values
        if (psi5GSLocationInformationJsonObject != null)
            psiSubscriberInformationJsonObject.add("5GSLocationInformation", psi5GSLocationInformationJsonObject);

        // Write Subscriber State
        if (csSubscriberState != null)
            writeSubscriberState(csSubscriberState, psiSubscriberInformationJsonObject);
        else if (psSubscriberState != null)
            writeSubscriberState(psSubscriberState, psiSubscriberInformationJsonObject);
        else if (epsSubscriberState != null)
            writeSubscriberState(epsSubscriberState, psiSubscriberInformationJsonObject);
        if (notReachableReason != null)
            writeNotReachableReason(notReachableReason, psiSubscriberInformationJsonObject);

        // Write MNP Information Result values
        if (mnpInfoResultNumberPortabilityStatus != null || mnpInfoResultMSISDN != null || mnpInfoResultIMSI != null || mnpInfoResultRouteingNumber != null) {
            JsonObject psiMnpInfoResultJsonObject = new JsonObject();
            writeMnpStatus(mnpInfoResultNumberPortabilityStatus, psiMnpInfoResultJsonObject);
            writeMnpMsisdn(mnpInfoResultMSISDN, psiMnpInfoResultJsonObject);
            writeMnpImsi(mnpInfoResultIMSI, psiMnpInfoResultJsonObject);
            writeMnpRouteingNumber(mnpInfoResultRouteingNumber, psiMnpInfoResultJsonObject);
            psiSubscriberInformationJsonObject.add("MNPInfoResult", psiMnpInfoResultJsonObject);
        }

        // Write MS Classmark
        if (msClassmark != null)
            writeMSClassmark(msClassmark, psiSubscriberInformationJsonObject);

        // Write GPRS MS Class values
        if (msNetCap != null) {
            JsonObject psiGprsMsClassJsonObject = new JsonObject();
            writeMSNetworkCapability(msNetCap, psiGprsMsClassJsonObject); // TODO: fix decoding of this value
            writeMSRadioAccessCapability(msRASCap, psiGprsMsClassJsonObject); // TODO: fix decoding of this value
            psiSubscriberInformationJsonObject.add("GPRSMSClass", psiGprsMsClassJsonObject);
        }

        // Write last UE activity time
        if (lastUeActivityTime != null) {
            JsonObject psiLastUeActivityTime = new JsonObject();
            writeTime(lastUeActivityTime, psiLastUeActivityTime);
            psiSubscriberInformationJsonObject.add("LastUEActivityTime", psiLastUeActivityTime);
        }

        // Write Last RAT type
        if (lastRatType != null) {
            JsonObject psiLastRatType = new JsonObject();
            writeLastRatType(lastRatType, psiLastRatType);
            psiSubscriberInformationJsonObject.add("LastRATType", psiLastRatType);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(psiSubscriberInformationJsonObject);
    }
}
