package org.mobicents.gmlc.slee.diameter.sh.elements;

import org.mobicents.gmlc.slee.primitives.NRCellGlobalId;
import org.mobicents.gmlc.slee.primitives.NRCellGlobalIdImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ShNRCellGlobalId {

    private static final String MCC = "mcc";
    private static final String MNC = "mnc";
    private static final String NCI = "nci";

    private String nrCellGlobalIdStr;
    private NRCellGlobalId nrCellGlobalId;

    public ShNRCellGlobalId(String nrCellGlobalIdStr, NRCellGlobalId nrCellGlobalId) {
        this.nrCellGlobalIdStr = nrCellGlobalIdStr;
        this.nrCellGlobalId = nrCellGlobalId;
    }

    public ShNRCellGlobalId(String nrCellGlobalIdStr) {
        this.nrCellGlobalIdStr = nrCellGlobalIdStr;
    }

    public ShNRCellGlobalId(NRCellGlobalId nrCellGlobalId) {
        this.nrCellGlobalId = nrCellGlobalId;
    }

    public ShNRCellGlobalId() {
    }

    public NRCellGlobalId getNRCellGlobalId() {
        return nrCellGlobalId;
    }

    public void setNRCellGlobalId(NRCellGlobalId nrCellGlobalId) {
        this.nrCellGlobalId = nrCellGlobalId;
    }

    public String getNRCellGlobalIdStr() {
        return nrCellGlobalIdStr;
    }

    public void setNRCellGlobalIdStr(String nrCellGlobalIdStr) {
        try {
            int mcc = Integer.parseInt(nrCellGlobalIdStr.substring(0,3));
            int mnc = 0;
            long nci = 0;
            if (nrCellGlobalIdStr.length() == 15) {
                mnc = Integer.parseInt(nrCellGlobalIdStr.substring(3,6));
                nci = Long.parseLong(nrCellGlobalIdStr.substring(6), 16);
            } else if (nrCellGlobalIdStr.length() == 14) {
                mnc = Integer.parseInt(nrCellGlobalIdStr.substring(3,5));
                nci = Long.parseLong(nrCellGlobalIdStr.substring(5), 16);
            }
            NRCellGlobalIdImpl nrCgi = new NRCellGlobalIdImpl();
            nrCgi.setData(mcc, mnc, nci);
            byte[] nrCgiBytes = nrCgi.getData();
            this.nrCellGlobalId = decodeNRCGIBytes(nrCgiBytes);
            this.nrCellGlobalIdStr = getNRCellGlobalIdStr();
        } catch (MAPException e) {
            e.printStackTrace();
        }
    }

    public NRCellGlobalId decodeNRCGIBytes(byte[] nrCGIBytes) {
        return new NRCellGlobalIdImpl(nrCGIBytes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NCGI");
        sb.append(" [");

        if (nrCellGlobalId != null) {

            try {
                try {
                    sb.append(MCC+"=");
                    sb.append(this.nrCellGlobalId.getMCC());
                    sb.append(", "+MNC+"=");
                    sb.append(this.nrCellGlobalId.getMNC());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                sb.append(", "+NCI+"=");
                sb.append(this.nrCellGlobalId.getNCI());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sb.append("]");

        return sb.toString();
    }
}
