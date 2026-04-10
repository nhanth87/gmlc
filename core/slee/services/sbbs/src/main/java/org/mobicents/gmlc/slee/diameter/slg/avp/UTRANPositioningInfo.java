package org.mobicents.gmlc.slee.diameter.slg.avp;

/**
 * Interface representing UTRAN (UMTS Terrestrial Radio Access Network) Positioning Information.
 * This interface provides access to positioning data specific to UTRAN network elements.
 * 
 * @author <a href="mailto:abhayani@tenilab.org"> Amit Bhayani </a>
 */
public interface UTRANPositioningInfo {

    /**
     * Gets the UTRAN positioning data.
     * 
     * @return the UTRAN positioning data as a byte array, or null if not available
     */
    byte[] getUTRANPositioningData();

    /**
     * Gets the UTRAN GANSS (Galileo and Additional Navigation Satellite Systems) positioning data.
     * 
     * @return the UTRAN GANSS positioning data as a byte array, or null if not available
     */
    byte[] getUTRANGANSSPositioningData();

    /**
     * Gets the UTRAN additional positioning data.
     * 
     * @return the UTRAN additional positioning data as a byte array, or null if not available
     */
    byte[] getUTRANAdditionalPositioningData();
}
