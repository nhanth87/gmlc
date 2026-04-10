package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 * LSAIdentity interface - Localised Service Area Identity
 * Stub for jSS7 9.2.5 compatibility
 */
public interface LSAIdentity extends Serializable {

    byte[] getData();

    int getLSAId();

    boolean isPlmnSignificantLSA();
}
