package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;

/**
 * EUtranCgi interface - Stub for jSS7 9.2.5 compatibility
 * Represents E-UTRAN Cell Global Identifier
 */
public interface EUtranCgi extends CellGlobalIdOrServiceAreaIdOrLAI {
    
    /**
     * Gets the E-UTRAN Cell Identifier
     * @return the E-UTRAN cell ID
     */
    int getEUtranCellId();
    
    /**
     * Gets the MCC
     * @return the MCC
     */
    int getMCC();
    
    /**
     * Gets the MNC
     * @return the MNC
     */
    int getMNC();
    
    /**
     * Gets the ECI (E-UTRAN Cell Identifier)
     * @return the ECI
     */
    long getEci() throws MAPException;
    
    /**
     * Gets the ENodeB ID
     * @return the ENodeB ID
     */
    long getENodeBId() throws MAPException;
    
    /**
     * Gets the CI (Cell Identity)
     * @return the CI
     */
    int getCi() throws MAPException;
}
