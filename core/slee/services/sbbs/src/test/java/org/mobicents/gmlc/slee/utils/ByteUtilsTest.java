package org.mobicents.gmlc.slee.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test stub for ByteUtils class
 */
class ByteUtilsTest {

    private byte[] testBytes;
    private static final String TEST_HEX_STRING = "48656c6c6f"; // "Hello" in hex

    @BeforeEach
    void setUp() {
        testBytes = new byte[] { 0x48, 0x65, 0x6c, 0x6c, 0x6f }; // "Hello"
    }

    @Test
    @DisplayName("Test byteToHex conversion")
    void testByteToHex() {
        byte testByte = 0x48;
        String result = ByteUtils.byteToHex(testByte);
        
        assertNotNull(result);
        assertEquals("48", result);
    }

    @Test
    @DisplayName("Test hexToByte conversion")
    void testHexToByte() {
        String hexString = "48";
        byte result = ByteUtils.hexToByte(hexString);
        
        assertEquals((byte) 0x48, result);
    }

    @Test
    @DisplayName("Test bytesToHex conversion")
    void testBytesToHex() {
        String result = ByteUtils.bytesToHex(testBytes);
        
        assertNotNull(result);
        assertEquals(TEST_HEX_STRING, result);
    }

    @Test
    @DisplayName("Test bytesToHex with null input")
    void testBytesToHexWithNull() {
        String result = ByteUtils.bytesToHex(null);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test dumpBytes conversion")
    void testDumpBytes() {
        String result = ByteUtils.dumpBytes(testBytes);
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.contains("0x"));
    }

    @Test
    @DisplayName("Test dumpBytes with null input")
    void testDumpBytesWithNull() {
        String result = ByteUtils.dumpBytes(null);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test dumpBytesToHexString conversion")
    void testDumpBytesToHexString() {
        String result = ByteUtils.dumpBytesToHexString(testBytes);
        
        assertNotNull(result);
        assertEquals(TEST_HEX_STRING, result);
    }

    @Test
    @DisplayName("Test hexStringToByteArray conversion")
    void testHexStringToByteArray() {
        byte[] result = ByteUtils.hexStringToByteArray(TEST_HEX_STRING);
        
        assertNotNull(result);
        assertArrayEquals(testBytes, result);
    }

    @Test
    @DisplayName("Test hexStringToByteArray with null input")
    void testHexStringToByteArrayWithNull() {
        byte[] result = ByteUtils.hexStringToByteArray(null);
        
        assertNull(result);
    }

    @Test
    @DisplayName("Test subBytes with start index only")
    void testSubBytesWithStartIndex() {
        byte[] result = ByteUtils.subBytes(testBytes, 1);
        
        assertNotNull(result);
        assertEquals(4, result.length);
        assertEquals((byte) 0x65, result[0]);
    }

    @Test
    @DisplayName("Test subBytes with start and end index")
    void testSubBytesWithStartAndEndIndex() {
        byte[] result = ByteUtils.subBytes(testBytes, 1, 3);
        
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals((byte) 0x65, result[0]);
        assertEquals((byte) 0x6c, result[1]);
    }

    @Test
    @DisplayName("Test getBytes method")
    void testGetBytes() {
        byte[] destination = new byte[2];
        ByteUtils.getBytes(testBytes, 0, 2, destination, 0);
        
        assertEquals((byte) 0x48, destination[0]);
        assertEquals((byte) 0x65, destination[1]);
    }

    @Test
    @DisplayName("Test encodeHexString conversion")
    void testEncodeHexString() {
        String result = ByteUtils.encodeHexString(testBytes);
        
        assertNotNull(result);
        assertEquals(TEST_HEX_STRING, result);
    }

    @Test
    @DisplayName("Test decodeHexString conversion")
    void testDecodeHexString() {
        byte[] result = ByteUtils.decodeHexString(TEST_HEX_STRING);
        
        assertNotNull(result);
        assertArrayEquals(testBytes, result);
    }

    @Test
    @DisplayName("Test decodeHexString with invalid input throws exception")
    void testDecodeHexStringWithInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            ByteUtils.decodeHexString("486"); // Odd length
        });
    }

    @Test
    @DisplayName("Test getMNC with valid PLMN ID")
    void testGetMNC() {
        byte[] plmnId = new byte[] { 0x47, (byte) 0xf8, 0x10 }; // MCC=748, MNC=01
        String result = ByteUtils.getMNC(plmnId);
        
        assertNotNull(result);
        assertTrue(result.length() >= 1);
    }

    @Test
    @DisplayName("Test getMNC with invalid PLMN ID throws exception")
    void testGetMNCWithInvalidPlmnId() {
        byte[] invalidPlmnId = new byte[] { 0x47, 0xf8 }; // Wrong length
        
        assertThrows(IllegalArgumentException.class, () -> {
            ByteUtils.getMNC(invalidPlmnId);
        });
    }

    @Test
    @DisplayName("Test generateRandomStringByLength")
    void testGenerateRandomStringByLength() {
        int length = 10;
        String result = ByteUtils.generateRandomStringByLength(length, true);
        
        assertNotNull(result);
        assertEquals(length, result.length());
    }

    @Test
    @DisplayName("Test hex2Double conversion")
    void testHex2Double() {
        double result = ByteUtils.hex2Double("00000001");
        
        assertTrue(result >= 0);
    }
}
