package org.mobicents.gmlc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test stub for GmlcPropertiesManagement class
 */
class GmlcPropertiesManagementTest {

    private GmlcPropertiesManagement propertiesManagement;
    private static final String TEST_NAME = "TestGmlcProperties";

    @BeforeEach
    void setUp() {
        propertiesManagement = GmlcPropertiesManagement.getInstance(TEST_NAME);
    }

    @Test
    @DisplayName("Test GmlcPropertiesManagement instance creation")
    void testInstanceCreation() {
        assertNotNull(propertiesManagement);
    }

    @Test
    @DisplayName("Test GMLC GT getters and setters")
    void testGmlcGt() {
        String testGt = "123456789012345";
        propertiesManagement.setGmlcGt(testGt);
        
        assertEquals(testGt, propertiesManagement.getGmlcGt());
    }

    @Test
    @DisplayName("Test GMLC SSN getters and setters")
    void testGmlcSsn() {
        int testSsn = 145;
        propertiesManagement.setGmlcSsn(testSsn);
        
        assertEquals(testSsn, propertiesManagement.getGmlcSsn());
    }

    @Test
    @DisplayName("Test HLR SSN getters and setters")
    void testHlrSsn() {
        int testSsn = 6;
        propertiesManagement.setHlrSsn(testSsn);
        
        assertEquals(testSsn, propertiesManagement.getHlrSsn());
    }

    @Test
    @DisplayName("Test MSC SSN getters and setters")
    void testMscSsn() {
        int testSsn = 8;
        propertiesManagement.setMscSsn(testSsn);
        
        assertEquals(testSsn, propertiesManagement.getMscSsn());
    }

    @Test
    @DisplayName("Test VLR SSN getters and setters")
    void testVlrSsn() {
        int testSsn = 7;
        propertiesManagement.setVlrSsn(testSsn);
        
        assertEquals(testSsn, propertiesManagement.getVlrSsn());
    }

    @Test
    @DisplayName("Test SGSN SSN getters and setters")
    void testSgsnSsn() {
        int testSsn = 149;
        propertiesManagement.setSgsnSsn(testSsn);
        
        assertEquals(testSsn, propertiesManagement.getSgsnSsn());
    }

    @Test
    @DisplayName("Test Max MAP Version getters and setters")
    void testMaxMapVersion() {
        int testVersion = 3;
        propertiesManagement.setMaxMapVersion(testVersion);
        
        assertEquals(testVersion, propertiesManagement.getMaxMapVersion());
    }

    @Test
    @DisplayName("Test Max Activity Count getters and setters")
    void testMaxActivityCount() {
        int testCount = 5000;
        propertiesManagement.setMaxActivityCount(testCount);
        
        assertEquals(testCount, propertiesManagement.getMaxActivityCount());
    }

    @Test
    @DisplayName("Test Dialog Timeout getters and setters")
    void testDialogTimeout() {
        long testTimeout = 60000L;
        propertiesManagement.setDialogTimeout(testTimeout);
        
        assertEquals(testTimeout, propertiesManagement.getDialogTimeout());
    }

    @Test
    @DisplayName("Test Event Context Suspend Delivery Timeout getters and setters")
    void testEventContextSuspendDeliveryTimeout() {
        int testTimeout = 60000;
        propertiesManagement.setEventContextSuspendDeliveryTimeout(testTimeout);
        
        assertEquals(testTimeout, propertiesManagement.getEventContextSuspendDeliveryTimeout());
    }

    @Test
    @DisplayName("Test Diameter Origin Realm getters and setters")
    void testDiameterOriginRealm() {
        String testRealm = "test.restcomm.org";
        propertiesManagement.setDiameterOriginRealm(testRealm);
        
        assertEquals(testRealm, propertiesManagement.getDiameterOriginRealm());
    }

    @Test
    @DisplayName("Test Diameter Origin Host getters and setters")
    void testDiameterOriginHost() {
        String testHost = "test-gmlc";
        propertiesManagement.setDiameterOriginHost(testHost);
        
        assertEquals(testHost, propertiesManagement.getDiameterOriginHost());
    }

    @Test
    @DisplayName("Test MongoDB properties getters and setters")
    void testMongoDbProperties() {
        String testHost = "test-mongo-host";
        int testPort = 27017;
        String testDatabase = "test-gmlc-db";
        
        propertiesManagement.setMongoHost(testHost);
        propertiesManagement.setMongoPort(testPort);
        propertiesManagement.setMongoDatabase(testDatabase);
        
        assertEquals(testHost, propertiesManagement.getMongoHost());
        assertEquals(testPort, propertiesManagement.getMongoPort());
        assertEquals(testDatabase, propertiesManagement.getMongoDatabase());
    }

    @Test
    @DisplayName("Test SUPL properties getters and setters")
    void testSuplProperties() {
        boolean testSslEnabled = true;
        int testSslPort = 7275;
        int testNoSslPort = 7276;
        
        propertiesManagement.setSuplSslEnabled(testSslEnabled);
        propertiesManagement.setSuplSslPort(testSslPort);
        propertiesManagement.setSuplNoSslPort(testNoSslPort);
        
        assertEquals(testSslEnabled, propertiesManagement.getSuplSslEnabled());
        assertEquals(testSslPort, propertiesManagement.getSuplSslPort());
        assertEquals(testNoSslPort, propertiesManagement.getSuplNoSslPort());
    }

    @Test
    @DisplayName("Test CdrLoggedType enum values")
    void testCdrLoggedTypeEnum() {
        assertNotNull(GmlcPropertiesManagement.CdrLoggedType.Database);
        assertNotNull(GmlcPropertiesManagement.CdrLoggedType.Textfile);
    }
}
