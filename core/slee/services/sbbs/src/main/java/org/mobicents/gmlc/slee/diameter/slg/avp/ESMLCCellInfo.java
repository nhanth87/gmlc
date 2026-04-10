package org.mobicents.gmlc.slee.diameter.slg.avp;

/**
 * Interface representing Evolved Serving Mobile Location Centre (E-SMLC) Cell Information.
 * This interface provides access to cell-related information from the E-SMLC,
 * including E-UTRAN Cell Global Identifier (ECGI) and cell portion identification.
 * 
 * @author <a href="mailto:abhayani@tenilab.org"> Amit Bhayani </a>
 */
public interface ESMLCCellInfo {

    /**
     * Gets the E-UTRAN Cell Global Identifier (ECGI).
     * This uniquely identifies a cell in the E-UTRAN network.
     * 
     * @return the ECGI as a byte array
     */
    byte[] getECGI();

    /**
     * Gets the cell portion identifier.
     * This identifies a specific portion within a cell, used for more granular
     * location reporting within the cell coverage area.
     * 
     * @return the cell portion ID as a long value
     */
    long getCellPortionID();
}
