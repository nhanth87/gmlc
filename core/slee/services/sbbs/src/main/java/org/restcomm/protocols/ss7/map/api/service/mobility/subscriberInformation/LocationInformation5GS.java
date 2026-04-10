package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.GeodeticInformation;
import org.restcomm.protocols.ss7.map.api.primitives.GeographicalInformation;
import org.restcomm.protocols.ss7.map.api.primitives.TAId;

public interface LocationInformation5GS {
    CellGlobalIdOrServiceAreaIdOrLAI getCGIOrSAIOrLAI();
    boolean isCurrentLocationRetrieved();
    
    // Additional methods for 5G location information
    GeographicalInformation getGeographicalInformation();
    GeodeticInformation getGeodeticInformation();
    NRCellGlobalId getNRCellGlobalId();
    NRTAId getNRTAId();
    EUtranCgi getEUtranCgi();
    TAId getTAId();
}
