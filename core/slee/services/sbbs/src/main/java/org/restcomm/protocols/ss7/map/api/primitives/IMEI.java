package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * IMEI interface - Stub for jSS7 9.2.5 compatibility
 * Represents International Mobile Equipment Identity
 */
public interface IMEI {
    
    /**
     * Gets the IMEI value as string
     * @return the IMEI value
     */
    String getIMEI();
    
    /**
     * Gets the IMEI with SVN (Software Version Number)
     * @return the IMEI with SVN
     */
    String getIMEISV();
}
