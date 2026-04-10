package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * AddressString interface - Stub for jSS7 9.2.5 compatibility
 * Represents a generic address string
 */
public interface AddressString {
    
    /**
     * Gets the address as string
     * @return the address
     */
    String getAddress();
    
    /**
     * Gets the nature of address indicator
     * @return the nature of address
     */
    int getNatureOfAddressIndicator();
    
    /**
     * Gets the numbering plan indicator
     * @return the numbering plan
     */
    int getNumberingPlanIndicator();
    
    /**
     * Gets the data as byte array
     * @return the encoded data
     */
    byte[] getData();
}
