package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * IMSI interface - Stub for jSS7 9.2.5 compatibility
 * Represents International Mobile Subscriber Identity
 */
public interface IMSI {
    
    /**
     * Gets the IMSI value as string
     * @return the IMSI value
     */
    String getData();
    
    /**
     * Gets the IMSI digits
     * @return the IMSI digits
     */
    String getImsi();
}
