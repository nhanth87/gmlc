package org.mobicents.gmlc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test stub for GmlcManagement class
 */
class GmlcManagementTest {

    private GmlcManagement gmlcManagement;
    private static final String TEST_NAME = "TestGmlc";

    @BeforeEach
    void setUp() {
        gmlcManagement = new GmlcManagement(TEST_NAME);
    }

    @Test
    @DisplayName("Test GmlcManagement instance creation")
    void testInstanceCreation() {
        assertNotNull(gmlcManagement);
        assertEquals(TEST_NAME, gmlcManagement.getName());
    }

    @Test
    @DisplayName("Test getInstance with name creates singleton")
    void testGetInstanceWithName() {
        GmlcManagement instance1 = GmlcManagement.getInstance(TEST_NAME);
        GmlcManagement instance2 = GmlcManagement.getInstance(TEST_NAME);
        
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Test persist directory getters and setters")
    void testPersistDir() {
        String testDir = "/test/persist/dir";
        gmlcManagement.setPersistDir(testDir);
        
        assertEquals(testDir, gmlcManagement.getPersistDir());
    }

    @Test
    @DisplayName("Test isStarted returns false before start")
    void testIsStartedBeforeStart() {
        assertFalse(gmlcManagement.isStarted());
    }

    @Test
    @DisplayName("Test JMX domain constant")
    void testJmxDomainConstant() {
        assertEquals("org.mobicents.gmlc", GmlcManagement.JMX_DOMAIN);
    }

    @Test
    @DisplayName("Test getInstance without parameter returns same instance")
    void testGetInstanceWithoutParameter() {
        GmlcManagement instance1 = GmlcManagement.getInstance();
        GmlcManagement instance2 = GmlcManagement.getInstance();
        
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }
}
