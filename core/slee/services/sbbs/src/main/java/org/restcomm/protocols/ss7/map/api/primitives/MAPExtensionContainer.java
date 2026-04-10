package org.restcomm.protocols.ss7.map.api.primitives;

import java.util.List;

/**
 * MAPExtensionContainer interface - Stub for jSS7 9.2.5 compatibility
 * Represents MAP protocol extension container for private network extensions
 */
public interface MAPExtensionContainer {
    
    /**
     * Gets the private extension list
     * @return list of private extensions
     */
    List<PrivateExtension> getPrivateExtensionList();
    
    /**
     * Gets the PCS extension
     * @return the PCS extension
     */
    byte[] getPcsExtension();
    
    /**
     * Checks if extension container is empty
     * @return true if empty
     */
    boolean isEmpty();
}
