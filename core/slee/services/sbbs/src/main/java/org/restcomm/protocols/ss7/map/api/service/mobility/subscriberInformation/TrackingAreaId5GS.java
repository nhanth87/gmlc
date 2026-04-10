package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 * TrackingAreaId5GS interface - 5G Tracking Area Identity
 * Stub for jSS7 9.2.5 compatibility
 */
public interface TrackingAreaId5GS extends Serializable {

    byte[] getData();

    int getMCC();

    int getMNC();

    int getTAC();
}
