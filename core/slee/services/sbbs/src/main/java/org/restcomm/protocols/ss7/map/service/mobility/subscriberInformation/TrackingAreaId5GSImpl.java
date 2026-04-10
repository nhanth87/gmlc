package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TrackingAreaId5GS;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 * TrackingAreaId5GSImpl - 5G Tracking Area Identity implementation
 * Stub for jSS7 9.2.5 compatibility
 */
public class TrackingAreaId5GSImpl extends OctetStringBase implements TrackingAreaId5GS {

    private static final String MCC = "mcc";
    private static final String MNC = "mnc";
    private static final String TAC = "tac";

    public TrackingAreaId5GSImpl() {
        super(5, 5, "TrackingAreaId5GS");
    }

    public TrackingAreaId5GSImpl(byte[] data) {
        super(5, 5, "TrackingAreaId5GS", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public int getMCC() {
        if (data == null || data.length < 3)
            return 0;
        // Simple BCD decoding for MCC (first 2 bytes)
        return ((data[0] & 0x0F) * 100) + ((data[0] >> 4) * 10) + (data[1] & 0x0F);
    }

    @Override
    public int getMNC() {
        if (data == null || data.length < 3)
            return 0;
        // Simple BCD decoding for MNC
        int mnc = ((data[2] & 0x0F) * 10) + ((data[2] >> 4));
        if ((data[1] >> 4) != 0x0F) {
            mnc = mnc * 10 + (data[1] >> 4);
        }
        return mnc;
    }

    @Override
    public int getTAC() {
        if (data == null || data.length < 5)
            return 0;
        // TAC is in bytes 3 and 4
        return ((data[3] & 0xFF) << 8) | (data[4] & 0xFF);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");
        sb.append(MCC + "=");
        sb.append(getMCC());
        sb.append(", " + MNC + "=");
        sb.append(getMNC());
        sb.append(", " + TAC + "=");
        sb.append(getTAC());
        sb.append("]");
        return sb.toString();
    }
}
