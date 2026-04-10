package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.GeographicalInformation;
import org.restcomm.protocols.ss7.map.api.primitives.GeodeticInformation;
import org.restcomm.protocols.ss7.map.api.primitives.RoutingAreaIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.TrackingAreaIdentity;

public interface LocationInformationEPS {
    // Method with lowercase 'u' (original naming)
    CellGlobalIdOrServiceAreaIdOrLAI getEutranCellGlobalIdentity();

    // Method with capital 'EU' (what SBB code calls)
    CellGlobalIdOrServiceAreaIdOrLAI getEUtranCellGlobalIdentity();

    // Add these missing methods:
    GeographicalInformation getGeographicalInformation();
    GeodeticInformation getGeodeticInformation();
    TrackingAreaIdentity getTrackingAreaIdentity();
    RoutingAreaIdentity getRouteingAreaIdentity();
    LSAIdentity getLSAIdentity();
    ISDNAddressString getSGSNNumber();

    boolean getCurrentLocationRetrieved();
    
    Integer getAgeOfLocationInformation();
    
    DiameterIdentity getMmeName();
}
