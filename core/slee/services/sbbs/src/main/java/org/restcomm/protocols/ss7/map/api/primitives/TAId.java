package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 * TAId interface - Tracking Area Identity for 4G LTE
 * Stub for jSS7 9.2.5 compatibility
 */
public interface TAId extends Serializable {

    byte[] getData();

    int getMCC() throws MAPException;

    int getMNC() throws MAPException;

    int getTAC() throws MAPException;
}
