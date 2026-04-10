package org.mobicents.gmlc.slee.primitives;

import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TAId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeographicalInformation;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface LocationInformation5GS extends Serializable {

    NRCellGlobalId getNRCellGlobalIdentity();

    EUTRANCGI getEUtranCellGlobalIdentity();

    TAId getTrackingAreaIdentity();

    TrackingAreaId5GS getTrackingAreaIdentity5GS();

    GeographicalInformation getGeographicalInformation();

    org.restcomm.protocols.ss7.map.api.primitives.GeodeticInformation getGeodeticInformation();

    String getAMFAddress();

    String getSMSFAddress();

    boolean getCurrentLocationRetrieved();

    Integer getAgeOfLocationInformation();

    PlmnId getVisitedPlmnId();

    String getTimeZone();

    Integer getDaylightSavingTime();

    Integer getRatType();
}
