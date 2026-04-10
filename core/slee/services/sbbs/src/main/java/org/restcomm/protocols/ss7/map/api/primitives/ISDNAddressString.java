package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * ISDNAddressString interface - Stub for jSS7 9.2.5 compatibility
 * Represents an ISDN address (phone number)
 */
public interface ISDNAddressString {
    
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
