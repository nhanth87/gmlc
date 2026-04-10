package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * CellGlobalIdOrServiceAreaIdOrLAI interface - Stub for jSS7 9.2.5 compatibility
 * Represents Cell Global Identity or Service Area Identity or Location Area Identity
 */
public interface CellGlobalIdOrServiceAreaIdOrLAI {
    
    /**
     * Gets the MCC (Mobile Country Code)
     * @return the MCC
     */
    int getMCC();
    
    /**
     * Gets the MNC (Mobile Network Code)
     * @return the MNC
     */
    int getMNC();
    
    /**
     * Gets the LAC (Location Area Code)
     * @return the LAC
     */
    int getLac();
    
    /**
     * Gets the cell ID or service area code
     * @return the cell ID or SAC
     */
    int getCellIdOrServiceAreaCode();
    
    /**
     * Gets the cell ID (for CGI)
     * @return the cell ID
     */
    int getCellId();
    
    /**
     * Gets the service area code (for SAI)
     * @return the service area code
     */
    int getServiceAreaCode();
    
    /**
     * Checks if this is a Cell Global Identity
     * @return true if CGI
     */
    boolean isCGI();
    
    /**
     * Checks if this is a Service Area Identity
     * @return true if SAI
     */
    boolean isSAI();
    
    /**
     * Checks if this is a Location Area Identity
     * @return true if LAI
     */
    boolean isLAI();
    
    /**
     * Gets the data as byte array
     * @return the encoded data
     */
    byte[] getData();
    
    /**
     * Gets the PLMN ID
     * @return the PLMN ID string
     */
    String getPlmnId();
}
