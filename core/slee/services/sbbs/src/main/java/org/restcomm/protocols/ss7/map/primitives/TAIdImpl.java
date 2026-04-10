package org.restcomm.protocols.ss7.map.primitives;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.primitives.TAId;

import java.io.IOException;

/**
 * TAIdImpl - Tracking Area Identity implementation for 4G LTE
 * Stub for jSS7 9.2.5 compatibility
 */
public class TAIdImpl extends OctetStringBase implements TAId {

    private static final String MCC = "mcc";
    private static final String MNC = "mnc";
    private static final String TAC = "tac";

    private static final String DATA = "data";
    private static final String DEFAULT_VALUE = null;
    private static final int DEFAULT_INT_VALUE = 0;

    public TAIdImpl() {
        super(5, 5, "TAId");
    }

    public TAIdImpl(byte[] data) {
        super(5, 5, "TAId", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

    public void setData(int mcc, int mnc, int tac) throws MAPException {
        if (mcc < 1 || mcc > 999)
            throw new MAPException("Bad MCC value");
        if (mnc < 0 || mnc > 999)
            throw new MAPException("Bad MNC value");

        this.data = new byte[5];

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if (mcc < 100)
            sb.append("0");
        if (mcc < 10)
            sb.append("0");
        sb.append(mcc);

        if (mnc < 100) {
            if (mnc < 10)
                sb2.append("0");
            sb2.append(mnc);
        } else {
            sb.append(mnc % 10);
            sb2.append(mnc / 10);
        }

        AsnOutputStream asnOs = new AsnOutputStream();
        TbcdString.encodeString(asnOs, sb.toString());
        System.arraycopy(asnOs.toByteArray(), 0, this.data, 0, 2);

        asnOs = new AsnOutputStream();
        TbcdString.encodeString(asnOs, sb2.toString());
        System.arraycopy(asnOs.toByteArray(), 0, this.data, 2, 1);

        data[3] = (byte) (tac / 256);
        data[4] = (byte) (tac % 256);
    }

    @Override
    public int getMCC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 5)
            throw new MAPException("Data length must equal 5");

        AsnInputStream ansIS = new AsnInputStream(data);
        String res;
        try {
            res = TbcdString.decodeString(ansIS, 3);
        } catch (IOException e) {
            throw new MAPException("IOException when decoding TAId: " + e.getMessage(), e);
        } catch (MAPParsingComponentException e) {
            throw new MAPException("MAPParsingComponentException when decoding TAId: " + e.getMessage(), e);
        }

        if (res.length() < 5 || res.length() > 6)
            throw new MAPException("Decoded TbcdString must equal 5 or 6");

        String sMcc = res.substring(0, 3);

        return Integer.parseInt(sMcc);
    }

    @Override
    public int getMNC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 5)
            throw new MAPException("Data length must equal 5");

        AsnInputStream ansIS = new AsnInputStream(data);
        String res;
        try {
            res = TbcdString.decodeString(ansIS, 3);
        } catch (IOException e) {
            throw new MAPException("IOException when decoding TAId: " + e.getMessage(), e);
        } catch (MAPParsingComponentException e) {
            throw new MAPException("MAPParsingComponentException when decoding TAId: " + e.getMessage(), e);
        }

        if (res.length() < 5 || res.length() > 6)
            throw new MAPException("Decoded TbcdString must equal 5 or 6");

        String sMnc;
        if (res.length() == 5) {
            sMnc = res.substring(3);
        } else {
            sMnc = res.substring(4) + res.charAt(3);
        }

        return Integer.parseInt(sMnc);
    }

    @Override
    public int getTAC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 5)
            throw new MAPException("Data length must equal 5");

        return (data[3] & 0xFF) * 256 + (data[4] & 0xFF);
    }

    @Override
    public String toString() {
        int mcc = 0;
        int mnc = 0;
        int tac = 0;
        boolean correctData = false;

        try {
            mcc = this.getMCC();
            mnc = this.getMNC();
            tac = this.getTAC();
            correctData = true;
        } catch (MAPException e) {
            // Ignore
        }

        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");
        if (correctData) {
            sb.append(MCC + "=");
            sb.append(mcc);
            sb.append(", " + MNC + "=");
            sb.append(mnc);
            sb.append(", " + TAC + "=");
            sb.append(tac);
        } else {
            sb.append("Data=");
            sb.append(this.printDataArr());
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<TAIdImpl> TA_ID_XML = new XMLFormat<>(TAIdImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, TAIdImpl taId) throws XMLStreamException {
            int mcc = xml.getAttribute(MCC, DEFAULT_INT_VALUE);
            int mnc = xml.getAttribute(MNC, DEFAULT_INT_VALUE);
            int tac = xml.getAttribute(TAC, DEFAULT_INT_VALUE);

            try {
                taId.setData(mcc, mnc, tac);
            } catch (MAPException e) {
                throw new XMLStreamException("MAPException when deserializing TAIdImpl", e);
            }
        }

        @Override
        public void write(TAIdImpl taId, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            try {
                xml.setAttribute(MCC, taId.getMCC());
                xml.setAttribute(MNC, taId.getMNC());
                xml.setAttribute(TAC, taId.getTAC());
            } catch (MAPException e) {
                throw new XMLStreamException("MAPException when serializing TAIdImpl", e);
            }
        }
    };
}
