package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 * LSAIdentityImpl - Localised Service Area Identity implementation
 * Stub for jSS7 9.2.5 compatibility
 */
public class LSAIdentityImpl extends OctetStringBase implements LSAIdentity {

    private static final String LSA_ID = "lsaId";

    public LSAIdentityImpl() {
        super(3, 3, "LSAIdentity");
    }

    public LSAIdentityImpl(byte[] data) {
        super(3, 3, "LSAIdentity", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public int getLSAId() {
        if (data == null || data.length < 3)
            return 0;
        // LSA ID is 24 bits (3 bytes)
        return ((data[0] & 0xFF) << 16) | ((data[1] & 0xFF) << 8) | (data[2] & 0xFF);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");
        sb.append(LSA_ID + "=");
        sb.append(getLSAId());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean isPlmnSignificantLSA() {
        // Stub implementation - returns false
        // In jSS7, the universal/PLMN significant bit is usually bit 0 of first byte
        if (data == null || data.length < 1)
            return false;
        // PLMN significant LSA means bit 0 is 0 (universal when bit 0 is 1)
        return (data[0] & 0x01) == 0;
    }
}
