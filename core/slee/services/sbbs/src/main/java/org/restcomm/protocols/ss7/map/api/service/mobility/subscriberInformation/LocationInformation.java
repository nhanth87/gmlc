package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.GeographicalInformation;
import org.restcomm.protocols.ss7.map.api.primitives.GeodeticInformation;

/**
 * LocationInformation interface - Stub for jSS7 9.2.5 compatibility
 * Contains location information for a subscriber
 */
public interface LocationInformation {
    /**
     * Gets the Age of Location Information
     * @return the age of location information in minutes
     */
    Integer getAgeOfLocationInformation();

    /**
     * Gets the Cell Global Identity or Service Area Identity or LAI
     * @return the cell global ID or service area ID or LAI
     */
    CellGlobalIdOrServiceAreaIdOrLAI getCellGlobalIdOrServiceAreaIdOrLAI();

    /**
     * Gets the VLR Number
     * @return the ISDN address string representing VLR number
     */
    ISDNAddressString getVlrNumber();

    /**
     * Gets the Location Information EPS
     * @return the EPS location information
     */
    LocationInformationEPS getLocationInformationEPS();

    /**
     * Gets the Geographical Information
     * @return the geographical information
     */
    GeographicalInformation getGeographicalInformation();

    /**
     * Gets the Geodetic Information
     * @return the geodetic information
     */
    GeodeticInformation getGeodeticInformation();

    /**
     * Gets the Location Number
     * @return the location number
     */
    Object getLocationNumber();

    /**
     * Gets the LSA Identity
     * @return the LSA identity
     */
    Object getLsaIdentity();

    /**
     * Gets the MSC Number
     * @return the MSC number
     */
    ISDNAddressString getMscNumber();

    /**
     * Gets the SGSN Number
     * @return the SGSN number
     */
    ISDNAddressString getSgsnNumber();

    /**
     * Gets the current location retrieved flag
     * @return true if current location retrieved
     */
    boolean getCurrentLocationRetrieved();

    /**
     * Gets the SAI (Service Area Identity) present flag
     * @return true if SAI is present
     */
    boolean getSaiPresent();

    /**
     * Gets the age of location information for GPRS
     * @return the age of location information for GPRS
     */
    Integer getAgeOfLocationInformationGPRS();

    /**
     * Gets the SGSN name
     * @return the SGSN name
     */
    String getSgsnName();

    /**
     * Gets the SGSN realm
     * @return the SGSN realm
     */
    String getSgsnRealm();

    /**
     * Gets the MME name
     * @return the MME name
     */
    String getMmeName();

    /**
     * Gets the MME realm
     * @return the MME realm
     */
    String getMmeRealm();

    /**
     * Gets the MSC number for enhanced circuit switched fallback
     * @return the MSC number for eCSFB
     */
    ISDNAddressString getMscNumberForEcsfb();
}
