package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 * TrackingAreaIdentity interface - Tracking Area Identity
 * Stub for jSS7 9.2.5 compatibility
 */
public interface TrackingAreaIdentity extends Serializable {

    byte[] getData();

    int getMCC();

    int getMNC();

    int getTAC();
}
