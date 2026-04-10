package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * GeographicalInformation interface - Stub for jSS7 9.2.5 compatibility
 * Represents geographical coordinates (latitude, longitude, uncertainty)
 */
public interface GeographicalInformation {
    
    /**
     * Gets the type of shape for the geographical information
     * @return the type of shape
     */
    TypeOfShape getTypeOfShape();
    
    /**
     * Gets the latitude value
     * @return the latitude
     */
    double getLatitude();
    
    /**
     * Gets the longitude value
     * @return the longitude
     */
    double getLongitude();
    
    /**
     * Gets the uncertainty value
     * @return the uncertainty
     */
    double getUncertainty();
    
    /**
     * Gets the confidence value
     * @return the confidence
     */
    int getConfidence();
    
    /**
     * Gets the altitude
     * @return the altitude
     */
    int getAltitude();
    
    /**
     * Gets the uncertainty for semi-major axis
     * @return the uncertainty semi-major
     */
    double getUncertaintySemiMajorAxis();
    
    /**
     * Gets the uncertainty for semi-minor axis
     * @return the uncertainty semi-minor
     */
    double getUncertaintySemiMinorAxis();
    
    /**
     * Gets the angle of the major axis
     * @return the angle of major axis
     */
    double getAngleOfMajorAxis();
    
    /**
     * Gets the inner radius
     * @return the inner radius
     */
    int getInnerRadius();
    
    /**
     * Gets the uncertainty radius
     * @return the uncertainty radius
     */
    double getUncertaintyRadius();
    
    /**
     * Gets the offset angle
     * @return the offset angle
     */
    double getOffsetAngle();
    
    /**
     * Gets the included angle
     * @return the included angle
     */
    double getIncludedAngle();
    
    /**
     * Gets the data as byte array
     * @return the encoded data
     */
    byte[] getData();
}
