package org.mobicents.gmlc.slee.cdr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test stub for CDRModel class
 */
class CDRModelTest {

    private CDRModel cdrModel;

    @BeforeEach
    void setUp() {
        cdrModel = new CDRModel();
    }

    @Test
    @DisplayName("Test CDRModel instance creation")
    void testInstanceCreation() {
        assertNotNull(cdrModel);
    }

    @Test
    @DisplayName("Test timestamp getters and setters")
    void testTimestamp() {
        String timestamp = "2024-01-01 12:00:00";
        cdrModel.setTimeStamp(timestamp);
        
        assertEquals(timestamp, cdrModel.getTimeStamp());
    }

    @Test
    @DisplayName("Test CDR date getters and setters")
    void testCdrDate() {
        String cdrDate = "2024-01-01";
        cdrModel.setCdrDate(cdrDate);
        
        assertEquals(cdrDate, cdrModel.getCdrDate());
    }

    @Test
    @DisplayName("Test CDR time getters and setters")
    void testCdrTime() {
        String cdrTime = "12:00:00";
        cdrModel.setCdrTime(cdrTime);
        
        assertEquals(cdrTime, cdrModel.getCdrTime());
    }

    @Test
    @DisplayName("Test GMLC ID getters and setters")
    void testGmlcId() {
        String gmlcId = "GMLC001";
        cdrModel.setGmlcId(gmlcId);
        
        assertEquals(gmlcId, cdrModel.getGmlcId());
    }

    @Test
    @DisplayName("Test curl user getters and setters")
    void testCurlUser() {
        String curlUser = "testUser";
        cdrModel.setCurlUser(curlUser);
        
        assertEquals(curlUser, cdrModel.getCurlUser());
    }

    @Test
    @DisplayName("Test record status getters and setters")
    void testRecordStatus() {
        String recordStatus = "SUCCESS";
        cdrModel.setRecordStatus(recordStatus);
        
        assertEquals(recordStatus, cdrModel.getRecordStatus());
    }

    @Test
    @DisplayName("Test status code getters and setters")
    void testStatusCode() {
        String statusCode = "200";
        cdrModel.setStatusCode(statusCode);
        
        assertEquals(statusCode, cdrModel.getStatusCode());
    }

    @Test
    @DisplayName("Test local dialog ID getters and setters")
    void testLocalDialogId() {
        String localDialogId = "12345";
        cdrModel.setLocalDialogId(localDialogId);
        
        assertEquals(localDialogId, cdrModel.getLocalDialogId());
    }

    @Test
    @DisplayName("Test remote dialog ID getters and setters")
    void testRemoteDialogId() {
        String remoteDialogId = "67890";
        cdrModel.setRemoteDialogId(remoteDialogId);
        
        assertEquals(remoteDialogId, cdrModel.getRemoteDialogId());
    }

    @Test
    @DisplayName("Test dialog duration getters and setters")
    void testDialogDuration() {
        String duration = "5000";
        cdrModel.setDialogDuration(duration);
        
        assertEquals(duration, cdrModel.getDialogDuration());
    }

    @Test
    @DisplayName("Test ISDN address getters and setters")
    void testIsdnAddress() {
        String isdnAddressNature = "international";
        String isdnNumberingPlan = "ISDN";
        String isdnDigits = "1234567890";
        
        cdrModel.setIsdnAddressNature(isdnAddressNature);
        cdrModel.setIsdnNumberingPlanIndicator(isdnNumberingPlan);
        cdrModel.setIsdnAddressDigits(isdnDigits);
        
        assertEquals(isdnAddressNature, cdrModel.getIsdnAddressNature());
        assertEquals(isdnNumberingPlan, cdrModel.getIsdnNumberingPlanIndicator());
        assertEquals(isdnDigits, cdrModel.getIsdnAddressDigits());
    }

    @Test
    @DisplayName("Test Diameter session getters and setters")
    void testDiameterSession() {
        String sessionId = "session-123";
        String originHost = "origin-host";
        String originRealm = "origin-realm";
        String destHost = "dest-host";
        String destRealm = "dest-realm";
        
        cdrModel.setDiameterSessionId(sessionId);
        cdrModel.setDiameterOriginHost(originHost);
        cdrModel.setDiameterOriginRealm(originRealm);
        cdrModel.setDiameterDestinationHost(destHost);
        cdrModel.setDiameterDestinationRealm(destRealm);
        
        assertEquals(sessionId, cdrModel.getDiameterSessionId());
        assertEquals(originHost, cdrModel.getDiameterOriginHost());
        assertEquals(originRealm, cdrModel.getDiameterOriginRealm());
        assertEquals(destHost, cdrModel.getDiameterDestinationHost());
        assertEquals(destRealm, cdrModel.getDiameterDestinationRealm());
    }

    @Test
    @DisplayName("Test CGI getters and setters")
    void testCgi() {
        String mcc = "502";
        String mnc = "16";
        String lac = "33562";
        String ci = "788";
        
        cdrModel.setCellGlobalIdServiceAreaIdMCC(mcc);
        cdrModel.setCellGlobalIdServiceAreaIdMNC(mnc);
        cdrModel.setCellGlobalIdServiceAreaIdLac(lac);
        cdrModel.setCellGlobalIdServiceAreaIdCI(ci);
        
        assertEquals(mcc, cdrModel.getCellGlobalIdServiceAreaIdMCC());
        assertEquals(mnc, cdrModel.getCellGlobalIdServiceAreaIdMNC());
        assertEquals(lac, cdrModel.getCellGlobalIdServiceAreaIdLac());
        assertEquals(ci, cdrModel.getCellGlobalIdServiceAreaIdCI());
    }

    @Test
    @DisplayName("Test MSISDN getters and setters")
    void testMsisdn() {
        String msisdn = "1234567890";
        cdrModel.setMsisdnAddress(msisdn);
        
        assertEquals(msisdn, cdrModel.getMsisdnAddress());
    }

    @Test
    @DisplayName("Test IMSI getters and setters")
    void testImsi() {
        String imsi = "502153207655206";
        cdrModel.setImsi(imsi);
        
        assertEquals(imsi, cdrModel.getImsi());
    }

    @Test
    @DisplayName("Test IMEI getters and setters")
    void testImei() {
        String imei = "123456789012345";
        cdrModel.setImei(imei);
        
        assertEquals(imei, cdrModel.getImei());
    }

    @Test
    @DisplayName("Test local address getters and setters")
    void testLocalAddress() {
        String localSpc = "1";
        String localSsn = "145";
        String localRi = "GT";
        
        cdrModel.setLocalSPC(localSpc);
        cdrModel.setLocalSSN(localSsn);
        cdrModel.setLocalRoutingIndicator(localRi);
        
        assertEquals(localSpc, cdrModel.getLocalSPC());
        assertEquals(localSsn, cdrModel.getLocalSSN());
        assertEquals(localRi, cdrModel.getLocalRoutingIndicator());
    }

    @Test
    @DisplayName("Test remote address getters and setters")
    void testRemoteAddress() {
        String remoteSpc = "2";
        String remoteSsn = "6";
        String remoteRi = "GT";
        
        cdrModel.setRemoteSPC(remoteSpc);
        cdrModel.setRemoteSSN(remoteSsn);
        cdrModel.setRemoteRoutingIndicator(remoteRi);
        
        assertEquals(remoteSpc, cdrModel.getRemoteSPC());
        assertEquals(remoteSsn, cdrModel.getRemoteSSN());
        assertEquals(remoteRi, cdrModel.getRemoteRoutingIndicator());
    }

    @Test
    @DisplayName("Test VLR number getters and setters")
    void testVlrNumber() {
        String vlrNumber = "1234567890";
        cdrModel.setVlrNumber(vlrNumber);
        
        assertEquals(vlrNumber, cdrModel.getVlrNumber());
    }

    @Test
    @DisplayName("Test MSC number getters and setters")
    void testMscNumber() {
        String mscNumber = "1234567890";
        cdrModel.setMscNumber(mscNumber);
        
        assertEquals(mscNumber, cdrModel.getMscNumber());
    }

    @Test
    @DisplayName("Test SGSN number getters and setters")
    void testSgsnNumber() {
        String sgsnNumber = "1234567890";
        cdrModel.setSgsnNumber(sgsnNumber);
        
        assertEquals(sgsnNumber, cdrModel.getSgsnNumber());
    }

    @Test
    @DisplayName("Test age of location information getters and setters")
    void testAgeOfLocationInformation() {
        String age = "100";
        cdrModel.setAgeOfLocationInformation(age);
        
        assertEquals(age, cdrModel.getAgeOfLocationInformation());
    }

    @Test
    @DisplayName("Test geographical info getters and setters")
    void testGeographicalInfo() {
        String typeOfShape = "EllipsoidPoint";
        String latitude = "37.422002";
        String longitude = "-122.084177";
        String uncertainty = "100";
        
        cdrModel.setGeographicalInfoTypeOfShape(typeOfShape);
        cdrModel.setGeographicalInfoLatitude(latitude);
        cdrModel.setGeographicalInfoLongitude(longitude);
        cdrModel.setGeographicalInfoUncertainty(uncertainty);
        
        assertEquals(typeOfShape, cdrModel.getGeographicalInfoTypeOfShape());
        assertEquals(latitude, cdrModel.getGeographicalInfoLatitude());
        assertEquals(longitude, cdrModel.getGeographicalInfoLongitude());
        assertEquals(uncertainty, cdrModel.getGeographicalInfoUncertainty());
    }
}
