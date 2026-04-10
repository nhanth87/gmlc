package org.mobicents.gmlc.slee.utils;

import org.joda.time.DateTime;
import org.mobicents.gmlc.slee.diameter.AVPHandler;
import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.mobicents.gmlc.slee.primitives.NRCellGlobalId;
import org.mobicents.gmlc.slee.primitives.NRCellGlobalIdImpl;
import org.mobicents.gmlc.slee.primitives.RoutingAreaIdImpl;
import org.mobicents.gmlc.slee.primitives.TrackingAreaId5GSImpl;
import org.mobicents.gmlc.slee.primitives.TrackingAreaIdImpl;
import org.mobicents.gmlc.slee.primitives.UTRANCellIdImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.api.service.lsm.AreaType;
import org.restcomm.protocols.ss7.map.primitives.CellGlobalIdOrServiceAreaIdFixedLengthImpl;
import org.restcomm.protocols.ss7.map.primitives.LAIFixedLengthImpl;
import org.restcomm.protocols.ss7.map.primitives.PlmnIdImpl;
import org.restcomm.protocols.ss7.map.service.lsm.AreaIdentificationImpl;

import java.util.Arrays;
import java.util.Random;

import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;
import static org.mobicents.gmlc.slee.utils.ByteUtils.dumpBytes;
import static org.mobicents.gmlc.slee.utils.ByteUtils.dumpBytesToHexString;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class TBCDUtil {

    private static final String cTBCDSymbolString = "0123456789*#abc";
    private static final char[] cTBCDSymbols = cTBCDSymbolString.toCharArray();
    private static Integer mcc = null, mnc = null, lac = null, ci = null, sac = null, uci = null, rac = null, tac = null, enbid = null, nrtac = null;
    private static Long eci = null, nci = null;

    public TBCDUtil() {
    }

    public static void main(String[] args) throws Exception {

        //if (args.length == 0)
        //    return;

        String msisdn = "60193303030";
        String gmlcNumber = "989350081360";
        String imsi = "502153207655206";

        byte[] msisdnTbcd = parseTBCD(msisdn);
        byte[] gmlcNumberTbcd = parseTBCD(gmlcNumber);
        byte[] imsiTbcd = parseTBCD(imsi);

        //MCC+MNC+MSIN
        ImsiTbcdImpl myImsi = new ImsiTbcdImpl(imsi);
        int imsiMcc = myImsi.getMcc();
        int imsiMnc = myImsi.getMnc();
        String imsiMsin = myImsi.getMsin();

        System.out.println("IMSI="+imsi+" parsed as TBCD as octets: " + dumpBytes(imsiTbcd));
        System.out.println("IMSI="+imsi+" TBCD octets decoded to TBCD String: " + toTBCDString(imsiTbcd));
        System.out.println("IMSI="+imsi+" TBCD octets decoded to Hex: " + bytesToHex(imsiTbcd));//05123502675502f6
        System.out.println("IMSI="+imsi+" MCC: " + imsiMcc);
        System.out.println("IMSI="+imsi+" MNC: " + imsiMnc);
        System.out.println("IMSI="+imsi+" MSIN: " + imsiMsin);

        System.out.println("MSISDN="+msisdn+" parsed as TBCD as octets: " + dumpBytes(msisdnTbcd));
        System.out.println("MSISDN="+msisdn+" TBCD octets decoded to TBCD String: " + toTBCDString(msisdnTbcd));
        System.out.println("MSISDN="+msisdn+" TBCD dump bytes to Hex: " + dumpBytesToHexString(msisdnTbcd)); // TBCD = ?0691333030f0
        ISDNAddressString isdnAddressString = AVPHandler.tbcd2IsdnAddressString(msisdnTbcd);
        System.out.println("MSISDN="+msisdn+" : tbcd2IsdnAddressString.getAddress : " + isdnAddressString.getAddress());

        System.out.println("GMLC-Number="+gmlcNumber+" parsed as TBCD as octets: " + dumpBytes(gmlcNumberTbcd));
        System.out.println("GMLC-Number="+gmlcNumber+" TBCD octets decoded to TBCD String: " + toTBCDString(gmlcNumberTbcd));
        System.out.println("GMLC-Number="+gmlcNumber+" TBCD dump bytes to Hex: " + dumpBytesToHexString(gmlcNumberTbcd));
        isdnAddressString = AVPHandler.tbcd2IsdnAddressString(msisdnTbcd);
        System.out.println("GMLC-Number="+gmlcNumber+" : tbcd2IsdnAddressString.getAddress : " + isdnAddressString.getAddress());

        msisdn = "59899077937";
        msisdnTbcd = parseTBCD(msisdn);
        String ms = bytesToHex(msisdnTbcd);
        System.out.println("MSISDN="+msisdn+" TBCD octets to hex String: " + ms);
        msisdnTbcd = parseTBCD(msisdn);
        System.out.println("MSISDN="+msisdn+" TBCD octets to TBCD string: " + toTBCDString(msisdnTbcd));

        String[] ccArray = new String[1];
        Integer[] areaIdParams;
        String cc = "748";
        ccArray[0] = cc;
        System.out.println("Country Code="+cc+" TBCD octets decoded: " + bytesToHex(parseTBCD(cc)));
        System.out.println("*******************************************************************");
        String countryCodeTbcd = setAreaIdTbcd(ccArray, "countryCode");
        System.out.println("Country Code = "+cc+", TBCD encoded: " + countryCodeTbcd); // 47f8
        areaIdParams = setAreaIdParams(ccArray, "countryCode");
        try {
            AreaIdentificationImpl areaIdentification = new AreaIdentificationImpl(parseTBCD(cc));
            System.out.println(areaIdentification);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String plmnStr = "748-1";
        String[] areaIdArray = plmnStr.split("-");
        setAreaIdParameters(areaIdArray, "plmnId");
        String plmnIdTbcd = setAreaIdTbcd(areaIdArray, "plmnId");
        System.out.println("PLMN ID = "+plmnStr+"; TBCD encoded: " + plmnIdTbcd);
        //PlmnIdImpl plmnId = new PlmnIdImpl(mcc, mnc); // 47f810
        areaIdParams = setAreaIdParams(areaIdArray, "plmnId");
        PlmnIdImpl plmnId = new PlmnIdImpl(areaIdParams[0], areaIdParams[1]);
        try {
            AreaIdentificationImpl areaIdentification = new AreaIdentificationImpl(plmnId.getData());
            System.out.println(areaIdentification);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LAIFixedLengthImpl lai = new LAIFixedLengthImpl();
        String laiStr = "736-2-13100";
        areaIdArray = laiStr.split("-");
        setAreaIdParameters(areaIdArray, "locationAreaId");
        try {
            lai.setData(mcc, mnc, lac); // 37f620332c mcc=736 mnc=2 lac=13100 ?// 05f2910c09 mcc=502 mnc=19 lac=3081
        } catch (MAPException e) {
            e.printStackTrace();
        }
        String laiTbcd = setAreaIdTbcd(areaIdArray, "locationAreaId");
        System.out.println("LAI = "+laiStr+"; TBCD encoded: " + laiTbcd);
        areaIdParams = setAreaIdParams(areaIdArray, "locationAreaId");
        try {
            AreaIdentificationImpl areaIdentification = new AreaIdentificationImpl(AreaType.locationAreaId, areaIdParams[0], areaIdParams[1], areaIdParams[2], 0);
            System.out.println(areaIdentification);
        } catch (MAPException e) {
            e.printStackTrace();
        }

        CellGlobalIdOrServiceAreaIdFixedLengthImpl cgi = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
        String cgiStr = "502-16-33562-788";
        areaIdArray = cgiStr.split("-");
        setAreaIdParameters(areaIdArray, "cellGlobalId");
        try {
            cgi.setData(mcc, mnc, lac, ci); // 05f261831a0314 mcc=502 mnc=16 lac=33562 ci= 788
        } catch (MAPException e) {
            e.printStackTrace();
        }
        String cgiTbcd = setAreaIdTbcd(areaIdArray, "cellGlobalId");
        System.out.println("CGI = "+cgiStr+"; TBCD encoded: " + cgiTbcd);
        areaIdParams = setAreaIdParams(areaIdArray, "cellGlobalId");
        try {
            AreaIdentificationImpl areaIdentification = new AreaIdentificationImpl(AreaType.cellGlobalId, areaIdParams[0], areaIdParams[1], areaIdParams[2], areaIdParams[3]);
            System.out.println(areaIdentification);
            System.out.println("CGI(TBCD) = " + bytesToHex(areaIdentification.getData()));
        } catch (MAPException e) {
            e.printStackTrace();
        }

        CellGlobalIdOrServiceAreaIdFixedLengthImpl sai = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
        String saiStr = "502-19-3081-33045";
        areaIdArray = saiStr.split("-");
        setAreaIdParameters(areaIdArray, "cellGlobalId");
        try {
            sai.setData(mcc, mnc, lac, sac); // 05f2910c098115 mcc=502 mnc=19 lac=3081 ci= 33045
        } catch (MAPException e) {
            e.printStackTrace();
        }
        String saiTbcd = setAreaIdTbcd(areaIdArray, "cellGlobalId");
        System.out.println("SAI = "+saiStr+"; TBCD encoded: " + saiTbcd);
        areaIdParams = setAreaIdParams(areaIdArray, "cellGlobalId");
        try {
            AreaIdentificationImpl areaIdentification = new AreaIdentificationImpl(AreaType.cellGlobalId, areaIdParams[0], areaIdParams[1], areaIdParams[2], areaIdParams[3]);
            System.out.println(areaIdentification);
            System.out.println("SAI(TBCD) = " + bytesToHex(areaIdentification.getData()));
        } catch (MAPException e) {
            e.printStackTrace();
        }

        UTRANCellIdImpl utranCid = new UTRANCellIdImpl();
        String utranCidStr = "502-17-134283263";
        areaIdArray = utranCidStr.split("-");
        setAreaIdParameters(areaIdArray, "utranCellId");
        try {
            utranCid.setData(mcc, mnc, uci); // 05f2710800ffff mcc=502 mnc=17 uci=134283263
        } catch (MAPException e) {
            e.printStackTrace();
        }
        String uciTbcd = setAreaIdTbcd(areaIdArray, "utranCellId");
        System.out.println("UCI = "+utranCidStr+"; TBCD encoded: " + uciTbcd);
        areaIdParams = setAreaIdParams(areaIdArray, "utranCellId");
        try {
            AreaIdentificationImpl areaIdentification = new AreaIdentificationImpl(AreaType.utranCellId, areaIdParams[0], areaIdParams[1], -1, areaIdParams[3]);
            System.out.println(areaIdentification);
        } catch (MAPException e) {
            e.printStackTrace();
        }

        RoutingAreaIdImpl rai = new RoutingAreaIdImpl();
        String raiStr = "748-1-101-10263";
        areaIdArray = raiStr.split("-");
        setAreaIdParameters(areaIdArray, "routingAreaId");
        try {
            rai.setData(mcc, mnc, lac, rac); // 47f8207d05ff mcc=748 mnc=2 lac=32005 rac=24561
        } catch (MAPException e) {
            e.printStackTrace();
        }
        String raiTbcd = setAreaIdTbcd(areaIdArray, "routingAreaId");
        System.out.println("RAI = "+raiStr+"; TBCD encoded: " + raiTbcd);
        areaIdParams = setAreaIdParams(areaIdArray, "routingAreaId");
        try {
            AreaIdentificationImpl areaIdentification = new AreaIdentificationImpl(AreaType.routingAreaId, areaIdParams[0], areaIdParams[1], areaIdParams[2], areaIdParams[3]);
            System.out.println(areaIdentification);
        } catch (MAPException e) {
            e.printStackTrace();
        }

        TrackingAreaIdImpl tai = new TrackingAreaIdImpl();
        String taiStr = "502-18-1029";
        areaIdArray = taiStr.split("-");
        setAreaIdParameters(areaIdArray, "trackingAreaId");
        try {
            tai.setData(mcc, mnc, tac); // 05f2810405 mcc=502 mnc=18 tac=1029
        } catch (MAPException e) {
            e.printStackTrace();
        }
        String taiTbcd = setAreaIdTbcd(areaIdArray, "trackingAreaId");
        System.out.println("TAI = "+taiStr+"; TBCD encoded: " + taiTbcd);

        //nrTrackingAreaId
        TrackingAreaId5GSImpl tai5g = new TrackingAreaId5GSImpl();
        String tai5gStr = "208-93-595578";
        areaIdArray = tai5gStr.split("-");
        setAreaIdParameters(areaIdArray, "nrTrackingAreaId");
        try {
            tai5g.setData(mcc, mnc, nrtac);
        } catch (MAPException e) {
            e.printStackTrace();
        }
        String tai5gTbcd = setAreaIdTbcd(areaIdArray, "nrTrackingAreaId");
        System.out.println("NR-TAI = "+tai5gStr+"; TBCD encoded: " + tai5gTbcd);

        EUTRANCGIImpl ecgi = new EUTRANCGIImpl();
        String ecgiStr = "502-18-811059-103";
        areaIdArray = ecgiStr.split("-");
        setAreaIdParameters(areaIdArray, "eUtranCellId");
        try {
            ecgi.setData(mcc, mnc, enbid, ci);
            // ?05f2810c603367
            // ?0x05, 0xf2, 0x81, 0x0c, 0x60, 0x33, 0x67
            // mcc=502 mnc=18 enbid=811059 ci= 103 (eci=207631207)

        } catch (MAPException e) {
            e.printStackTrace();
        }
        String ecgiTbcd = setAreaIdTbcd(areaIdArray, "eUtranCellId");
        System.out.println("ECGI = "+ecgiStr+"; TBCD encoded: " + ecgiTbcd);

        EUTRANCGIImpl ecgi2 = new EUTRANCGIImpl();
        ecgiStr = "502-19-207631107";
        areaIdArray = ecgiStr.split("-");
        setAreaIdParameters(areaIdArray, "eUtranCellId");
        try {
            ecgi2.setData(mcc, mnc, eci);
            // ?05f2910c603303
            // ?0x05, 0xf2, 0x11, 0x0c, 0x60, 0x33, 0x03
            // mcc=502 mnc=19 eci=207631107 (enbid=811059 ci= 3)
        } catch (MAPException e) {
            e.printStackTrace();
        }
        String ecgiTbcd2 = setAreaIdTbcd(areaIdArray, "eUtranCellId");
        System.out.println("ECGI = "+ecgiStr+"; TBCD encoded: " + ecgiTbcd2);

        NRCellGlobalIdImpl nrCGI = new NRCellGlobalIdImpl();
        // 748-1-7638827009L
        //String nrCgiStr = "748-1-7638827009";
        //String nrCgiStr = "208-93-34359738376";
        String nrCgiStr = "748-2-68719476735";
        areaIdArray = nrCgiStr.split("-");
        setAreaIdParameters(areaIdArray, "NRCellId");
        //
        try {
            nrCGI.setData(mcc, mnc, nci);
        } catch (MAPException e) {
            e.printStackTrace();
        }
        String nrCgiTbcd = setAreaIdTbcd(areaIdArray, "NRCellId");
        System.out.println("NRCGI = "+nrCgiStr+"; TBCD encoded: " + nrCgiTbcd);


        System.out.println("*******************************************************************");

        System.out.println("PLMNID toString: " + plmnId);
        System.out.println("PLMNID packet bytes: " + dumpBytes(plmnId.getData()));
        System.out.println("PLMNID dumpBytesToHexString: " + dumpBytesToHexString(plmnId.getData()));
        System.out.println("PLMNID bytesToHex: " + bytesToHex(plmnId.getData()));
        System.out.println("PLMNID bytes size: " + plmnId.getData().length);

        System.out.println("LAI toString: " + lai);
        System.out.println("LAI packet bytes: " + dumpBytes(lai.getData()));
        System.out.println("LAI dumpBytesToHexString: " + dumpBytesToHexString(lai.getData()));
        System.out.println("LAI bytesToHex: " + bytesToHex(lai.getData()));
        System.out.println("LAI bytes size: " + lai.getData().length);

        System.out.println("CGI toString: " + cgi);
        System.out.println("CGI packet bytes: " + dumpBytes(cgi.getData()));
        System.out.println("CGI dumpBytesToHexString: " + dumpBytesToHexString(cgi.getData()));
        System.out.println("CGI bytesToHex: " + bytesToHex(cgi.getData()));
        System.out.println("CGI bytes size: " + cgi.getData().length);

        System.out.println("SAI toString: " + sai);
        System.out.println("SAI packet bytes: " + dumpBytes(sai.getData()));
        System.out.println("SAI dumpBytesToHexString: " + dumpBytesToHexString(sai.getData()));
        System.out.println("SAI bytesToHex: " + bytesToHex(sai.getData()));
        System.out.println("SAI bytes size: " + sai.getData().length);

        System.out.println("RAI toString: " + rai);
        System.out.println("RAI packet bytes: " + dumpBytes(rai.getData()));
        System.out.println("RAI dumpBytesToHexString: " + dumpBytesToHexString(rai.getData()));
        System.out.println("RAI bytesToHex: " + bytesToHex(rai.getData()));
        System.out.println("RAI bytes size: " + rai.getData().length);

        System.out.println("TAI toString: " + tai);
        System.out.println("TAI packet bytes: " + dumpBytes(tai.getData()));
        System.out.println("TAI dumpBytesToHexString: " + dumpBytesToHexString(tai.getData()));
        System.out.println("TAI bytesToHex: " + bytesToHex(tai.getData()));
        System.out.println("TAI bytes size: " + tai.getData().length);

        System.out.println("ECGI (mcc+mnc+eNBId+ci) toString: " + ecgi);
        System.out.println("ECGI (mcc+mnc+eNBId+ci) packet bytes: " + dumpBytes(ecgi.getData()));
        System.out.println("ECGI (mcc+mnc+eNBId+ci) dumpBytesToHexString: " + dumpBytesToHexString(ecgi.getData()));
        System.out.println("ECGI (mcc+mnc+eNBId+ci) bytesToHex: " + bytesToHex(ecgi.getData()));
        System.out.println("ECGI (mcc+mnc+eNBId+ci) bytes size: " + ecgi.getData().length);

        System.out.println("ECGI (mcc+mnc+eci) toString: " + ecgi2);
        System.out.println("ECGI (mcc+mnc+eci) packet bytes: " + dumpBytes(ecgi2.getData()));
        System.out.println("ECGI (mcc+mnc+eci) dumpBytesToHexString: " + dumpBytesToHexString(ecgi2.getData()));
        System.out.println("ECGI (mcc+mnc+eci) bytesToHex: " + bytesToHex(ecgi2.getData()));
        System.out.println("ECGI (mcc+mnc+eci) bytes size: " + ecgi2.getData().length);

        System.out.println("NR-TAI (mcc+mnc+tac) toString: " + tai5g);
        System.out.println("NR-TAI MCC=: "+tai5g.getMCC()+", MNC=" +tai5g.getMNC()+", TAC="+tai5g.get5GSTAC());
        System.out.println("NR-TAI (mcc+mnc+tac) packet bytes: " + dumpBytes(tai5g.getData()));
        System.out.println("NR-TAI (mcc+mnc+tac) dumpBytesToHexString: " + dumpBytesToHexString(tai5g.getData()));
        System.out.println("NR-TAI (mcc+mnc+tac) bytesToHex: " + bytesToHex(tai5g.getData()));
        System.out.println("NR-TAI (mcc+mnc+tac) bytes size: " + tai5g.getData().length);

        System.out.println("NR CGI (mcc+mnc+nci) toString: " + nrCGI);
        System.out.println("NR CGI MCC=: "+nrCGI.getMCC()+", MNC=" +nrCGI.getMNC()+", NCI="+nrCGI.getNCI());
        System.out.println("NR CGI (mcc+mnc+nci) packet bytes: " + dumpBytes(nrCGI.getData()));
        System.out.println("NR CGI (mcc+mnc+nci) dumpBytesToHexString: " + dumpBytesToHexString(nrCGI.getData()));
        System.out.println("NR CGI (mcc+mnc+nci) bytesToHex: " + bytesToHex(nrCGI.getData()));
        System.out.println("NR CGI (mcc+mnc+nci) bytes size: " + nrCGI.getData().length);

        nrCgiStr = generateNrCgiValue();
        try {
            int mcc = Integer.parseInt(nrCgiStr.substring(0,3));
            int mnc = 0;
            long nci = 0;
            String nciStr = null;
            if (nrCgiStr.length() == 15) {
                mnc = Integer.parseInt(nrCgiStr.substring(3,6));
                nciStr = nrCgiStr.substring(6);
                nci = Long.parseLong(nrCgiStr.substring(6), 16);
            } else if (nrCgiStr.length() == 14) {
                mnc = Integer.parseInt(nrCgiStr.substring(3,5));
                nciStr = nrCgiStr.substring(5);
                nci = Long.parseLong(nrCgiStr.substring(5), 16);
            }
            System.out.println("NR CGI (2nd value) from String MCC="+mcc+", MNC="+mnc+", NCI string="+nciStr+", value="+nci);
            NRCellGlobalIdImpl nrCgi = new NRCellGlobalIdImpl();
            nrCgi.setData(mcc, mnc, nci);
            byte[] nrCgiBytes = nrCgi.getData();
            NRCellGlobalId nrCellGlobalId = decodeNRCGIBytes(nrCgiBytes);
            System.out.println("NR CGI (2nd value) from NRCellGlobalIdImpl: "+nrCellGlobalId);
        } catch (MAPException e) {
            e.printStackTrace();
        }


        DateTime start = DateTime.now();
        System.out.println("START: " + start);
        try {
            Thread.sleep(500);
            DateTime stop = DateTime.now();
            System.out.println("STOP: " + stop);
            long duration = stop.getMillis() - start.getMillis();
            System.out.println("Duration: " + duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method converts a TBCD byte array to a character string.
     */
    public static String toTBCDString(byte[] tbcd) {

        int size = (tbcd == null ? 0 : tbcd.length);
        StringBuffer buffer = new StringBuffer(2*size);
        for (int i=0; i<size; ++i) {
            int octet = tbcd[i];
            int n2 = (octet >> 4) & 0xF;
            int n1 = octet & 0xF;

            if (n1 == 15) {
                throw new NumberFormatException("Illegal filler in octet n=" + i);
            }
            buffer.append(cTBCDSymbols[n1]);

            if (n2 == 15) {
                if (i != size-1)
                    throw new NumberFormatException("Illegal filler in octet n=" + i);
            } else
                buffer.append(cTBCDSymbols[n2]);
        }

        return buffer.toString();
    }

    private static NRCellGlobalId decodeNRCGIBytes(byte[] nrCGIBytes) {
        return new NRCellGlobalIdImpl(nrCGIBytes);
    }

    private static String generateNrCgiValue() {
        Random rand = new Random();
        String value;
        switch(rand.nextInt(10) + 1) {
            case 1:
                value = "5021919F8F2136";
                break;
            case 2:
                value = "5020219F8F2136";
                break;
            default:
                value = "50215219F8F2136";
                break;
        }
        return value;
    }

    /*
     * This method converts a character string to a TBCD string.
     */
    public static byte[] parseTBCD(String tbcd) {
        int length = (tbcd == null ? 0:tbcd.length());
        int size = (length + 1)/2;
        byte[] buffer = new byte[size];

        for (int i=0, i1=0, i2=1; i<size; ++i, i1+=2, i2+=2) {

            char c = tbcd.charAt(i1);
            int n2 = getTBCDNibble(c, i1);
            int octet;
            int n1 = 15;
            if (i2 < length) {
                c = tbcd.charAt(i2);
                n1 = getTBCDNibble(c, i2);
            }
            octet = (n1 << 4) + n2;
            buffer[i] = (byte)(octet & 0xFF);
        }

        return buffer;
    }

    private static int getTBCDNibble(char c, int i1) {

        int n = Character.digit(c, 10);

        if (n < 0 || n > 9) {
            switch (c) {
                case '*':
                    n = 10;
                    break;
                case '#':
                    n = 11;
                    break;
                case 'a':
                    n = 12;
                    break;
                case 'b':
                    n = 13;
                    break;
                case 'c':
                    n = 14;
                    break;
                default:
                    throw new NumberFormatException("Bad character '" + c
                            + "' at position " + i1);
            }
        }
        return n;
    }

    public static class ImsiTbcdImpl {

        byte[] tbcd;

        public ImsiTbcdImpl(String imsi) {
            this.tbcd = parseTBCD(imsi);
        }

        public String getImsi() {
            return toTBCDString(this.tbcd);
        }

        public int getMcc() {
            int mcc = (tbcd[0] & 0x0F) * 100;
            mcc += (tbcd[0] >> 4) * 10;
            mcc += tbcd[1] & 0x0F;
            return mcc;
        }

        public int getMnc() {
            // not working :(
            int mnc = (tbcd[1] >> 4) * 100;
            mnc += (tbcd[2] & 0x0F) * 10;
            mnc += tbcd[2] >> 4;
            return mnc;
        }

        public String getMsin() {
            return toTBCDString(Arrays.copyOfRange(tbcd, 3, 8));
        }
    }

    public static void setAreaIdParameters(String[] areaId, String areaType) {
        for (int i=0; i < areaId.length; i++) {
            if (i==0)
                mcc = Integer.valueOf(areaId[i]);
            if (i==1)
                mnc = Integer.valueOf(areaId[i]);
            if (i==2) {
                if (areaType.equalsIgnoreCase("locationAreaId") || areaType.equalsIgnoreCase("cellGlobalId") ||
                        areaType.equalsIgnoreCase("routingAreaId"))
                    lac = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("trackingAreaId"))
                    tac = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("nrTrackingAreaId"))
                    nrtac = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("utranCellId"))
                    uci = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("eUtranCellId")) {
                    enbid = Integer.valueOf(areaId[i]);
                    eci = Long.valueOf(areaId[i]);
                } else if (areaType.equalsIgnoreCase("NRCellId")) {
                    nci = Long.valueOf(areaId[i]);
                }
            }
            if (i==3) {
                if (areaType.equalsIgnoreCase("cellGlobalId") || areaType.equalsIgnoreCase("routingAreaId"))
                    ci = sac = rac = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("eUtranCellId"))
                    ci = Integer.valueOf(areaId[i]);
            }
        }
    }

    public static Integer[] setAreaIdParams(String[] areaId, String areaType) {
        Integer[] areaIdParams = new Integer[4];
        for (int i=0; i < areaId.length; i++) {
            if (i==0) {
                mcc = areaIdParams[0] = Integer.valueOf(areaId[i]);
                if (areaType.equalsIgnoreCase("countryCode"))
                    areaIdParams[1] = areaIdParams[2] = areaIdParams[3] = -1;
            }
            if (i==1) {
                mnc = areaIdParams[1] = Integer.valueOf(areaId[i]);
                if (areaType.equalsIgnoreCase("plmnId"))
                    areaIdParams[2] = areaIdParams[3] = -1;
            }
            if (i==2) {
                if (areaType.equalsIgnoreCase("locationAreaId") || areaType.equalsIgnoreCase("cellGlobalId") ||
                        areaType.equalsIgnoreCase("routingAreaId"))
                    lac = areaIdParams[2] = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("utranCellId"))
                    uci = areaIdParams[3] = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("eUtranCellId")) {
                    enbid = areaIdParams[2] = Integer.valueOf(areaId[i]);
                    eci = (long) enbid;
                } else if (areaType.equalsIgnoreCase("NRCellId")) {
                    int n = areaIdParams[2] = Integer.valueOf(areaId[i]);
                    nci = (long) n;
                }
                else if (areaType.equalsIgnoreCase("trackingAreaId")) {
                    tac = areaIdParams[2] = Integer.valueOf(areaId[i]);
                    areaIdParams[3] = -1;
                }
                else if (areaType.equalsIgnoreCase("nrTrackingAreaId")) {
                    nrtac = areaIdParams[2] = Integer.valueOf(areaId[i]);
                    areaIdParams[3] = -1;
                }
            }
            if (i==3) {
                if (areaType.equalsIgnoreCase("cellGlobalId") || areaType.equalsIgnoreCase("routingAreaId"))
                    ci = sac = rac = areaIdParams[3] = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("eUtranCellId"))
                    ci = areaIdParams[3] = Integer.valueOf(areaId[i]);
            }
        }
        return areaIdParams;
    }

    public static String setAreaIdTbcd(String[] areaId, String areaType) {
        Integer mcc = null, mnc = null, lac = null, ci = null, sac = null, uci = null, rac = null, tac = null, enbid = null, nrtac = null;
        Long eci = null, nrci = null;
        String areaIdTbcd = "Invalid";
        for (int i=0; i < areaId.length; i++) {
            if (i==0)
                mcc = Integer.valueOf(areaId[i]);
            if (i==1)
                mnc = Integer.valueOf(areaId[i]);
            if (i==2) {
                if (areaType.equalsIgnoreCase("locationAreaId") || areaType.equalsIgnoreCase("cellGlobalId") ||
                        areaType.equalsIgnoreCase("routingAreaId"))
                    lac = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("trackingAreaId"))
                    tac = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("nrTrackingAreaId"))
                    nrtac = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("utranCellId"))
                    uci = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("eUtranCellId")) {
                    enbid = Integer.valueOf(areaId[i]);
                    eci = Long.valueOf(areaId[i]);
                } else if (areaType.equalsIgnoreCase("NRCellId")) {
                    nrci = Long.valueOf(areaId[i]);
                }
            }
            if (i==3) {
                if (areaType.equalsIgnoreCase("cellGlobalId") || areaType.equalsIgnoreCase("routingAreaId"))
                    ci = sac = rac = Integer.valueOf(areaId[i]);
                else if (areaType.equalsIgnoreCase("eUtranCellId"))
                    ci = Integer.valueOf(areaId[i]);
            }
        }
        if (areaType.equalsIgnoreCase("countryCode")) {
            if (mnc != null)
                return areaIdTbcd;
            try {
                if (mcc < 1 || mcc > 999)
                    return areaIdTbcd;
                else
                    areaIdTbcd = bytesToHex(parseTBCD(String.valueOf(mcc)));
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        } else if (areaType.equalsIgnoreCase("plmnId")) {
            try {
                PlmnIdImpl plmnIdImpl = new PlmnIdImpl(mcc, mnc);
                areaIdTbcd = bytesToHex(plmnIdImpl.getData());
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        } else if (areaType.equalsIgnoreCase("locationAreaId")) {
            LAIFixedLengthImpl lai = new LAIFixedLengthImpl();
            try {
                lai.setData(mcc, mnc, lac);
                areaIdTbcd = bytesToHex(lai.getData());
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        } else if (areaType.equalsIgnoreCase("cellGlobalId")) {
            CellGlobalIdOrServiceAreaIdFixedLengthImpl cgi = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
            try {
                cgi.setData(mcc, mnc, lac, ci);
                areaIdTbcd = bytesToHex(cgi.getData());
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        } else if (areaType.equalsIgnoreCase("utranCellId")) {
            UTRANCellIdImpl utranCellId = new UTRANCellIdImpl();
            try {
                utranCellId.setData(mcc, mnc, uci);
                areaIdTbcd = bytesToHex(utranCellId.getData());
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        } else if (areaType.equalsIgnoreCase("routingAreaId")) {
            RoutingAreaIdImpl rai = new RoutingAreaIdImpl();
            try {
                rai.setData(mcc, mnc, lac, rac);
                areaIdTbcd = bytesToHex(rai.getData());
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        } else if (areaType.equalsIgnoreCase("trackingAreaId")) {
            TrackingAreaIdImpl tai = new TrackingAreaIdImpl();
            try {
                tai.setData(mcc, mnc, tac);
                areaIdTbcd = bytesToHex(tai.getData());
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        } else if (areaType.equalsIgnoreCase("nrTrackingAreaId")) {
            TrackingAreaId5GSImpl tai5g = new TrackingAreaId5GSImpl();
            try {
                tai5g.setData(mcc, mnc, nrtac);
                areaIdTbcd = bytesToHex(tai5g.getData());
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        } else if (areaType.equalsIgnoreCase("eUtranCellId")) {
            EUTRANCGIImpl ecgi = new EUTRANCGIImpl();
            try {
                if (ci != null)
                    ecgi.setData(mcc, mnc, enbid, ci);
                else
                    ecgi.setData(mcc, mnc, eci);
                areaIdTbcd = bytesToHex(ecgi.getData());
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        } else if (areaType.equalsIgnoreCase("NRCellId")) {
            NRCellGlobalIdImpl nrcgi = new NRCellGlobalIdImpl();
            try {
                nrcgi.setData(mcc, mnc, nrci);
                areaIdTbcd = bytesToHex(nrcgi.getData());
                return areaIdTbcd;
            } catch (Exception e) {
                return areaIdTbcd;
            }
        }
        return areaIdTbcd;
    }
}