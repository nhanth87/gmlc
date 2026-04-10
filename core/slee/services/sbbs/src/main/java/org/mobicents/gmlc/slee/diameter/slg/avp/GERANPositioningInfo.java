package org.mobicents.gmlc.slee.diameter.slg.avp;

/**
 * Interface representing GERAN (GSM/EDGE Radio Access Network) Positioning Information.
 * This interface provides access to positioning data specific to GERAN network elements.
 * 
 * @author <a href="mailto:abhayani@tenilab.org"> Amit Bhayani </a>
 */
public interface GERANPositioningInfo {

    /**
     * Gets the GERAN positioning data.
     * 
     * @return the GERAN positioning data as a byte array, or null if not available
     */
    byte[] getGERANPositioningData();

    /**
     * Gets the GERAN GANSS (Galileo and Additional Navigation Satellite Systems) positioning data.
     * 
     * @return the GERAN GANSS positioning data as a byte array, or null if not available
     */
    byte[] getGERANGANSSPositioningData();
}
