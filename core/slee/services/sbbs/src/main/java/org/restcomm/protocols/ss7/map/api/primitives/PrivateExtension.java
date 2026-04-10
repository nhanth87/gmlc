package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * PrivateExtension interface - Stub for jSS7 9.2.5 compatibility
 * Represents a private extension for MAP protocol
 */
public interface PrivateExtension {
    
    /**
     * Gets the extension identifier (OID)
     * @return the extension ID
     */
    long[] getExtensionId();
    
    /**
     * Gets the extension data
     * @return the extension data
     */
    byte[] getExtensionData();
}
