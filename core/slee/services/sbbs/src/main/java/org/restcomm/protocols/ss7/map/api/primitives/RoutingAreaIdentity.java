package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 * RoutingAreaIdentity interface - Routing Area Identity (RAI)
 * Stub for jSS7 9.2.5 compatibility
 */
public interface RoutingAreaIdentity extends Serializable {

    byte[] getData();

    int getMCC();

    int getMNC();

    int getLAC();

    int getRAC();
}
