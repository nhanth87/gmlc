package org.mobicents.gmlc.slee.diameter.slg;

import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.mobicents.gmlc.slee.primitives.RoutingAreaIdImpl;
import org.mobicents.gmlc.slee.primitives.TrackingAreaIdImpl;
import org.mobicents.gmlc.slee.primitives.UTRANCellIdImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.primitives.CellGlobalIdOrServiceAreaIdFixedLengthImpl;
import org.restcomm.protocols.ss7.map.primitives.LAIFixedLengthImpl;
import org.restcomm.protocols.ss7.map.primitives.PlmnIdImpl;

import static org.mobicents.gmlc.slee.utils.TBCDUtil.parseTBCD;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLgAreaEventInfoHelper {

    public SLgAreaEventInfoHelper() {
    }

    public static String convertAreaTypeToString(long areaTypeValue) {
        String areaTypeString;
        switch((int) areaTypeValue) {
            case 0:
                areaTypeString = "countryCode";
                break;
            case 1:
                areaTypeString = "plmnId";
                break;
            case 2:
                areaTypeString = "locationAreaId";
                break;
            case 3:
                areaTypeString = "routingAreaId";
                break;
            case 4:
                areaTypeString = "cellGlobalId";
                break;
            case 5:
                areaTypeString = "utranCellId";
                break;
            case 6:
                areaTypeString = "trackingAreaId";
                break;
            case 7:
                areaTypeString = "eUtranCellId";
                break;
            default:
                areaTypeString = "Invalid";
                break;
        }
        return areaTypeString;
    }

    public static byte[] setAreaIdToTbcd(String[] areaId, String areaType) {
        int mcc = -1, mnc = -1, lac = -1, ci = -1, uci = -1, rac = -1, tac = -1, enbid = -1;
        long eci = -1;
        byte[] areaIdTbcd;
        for (int i=0; i < areaId.length; i++) {
            if (i==0)
                mcc = Integer.parseInt(areaId[i]);
            if (i==1)
                mnc = Integer.parseInt(areaId[i]);
            if (i==2) {
                if (areaType.equalsIgnoreCase("locationAreaId") || areaType.equalsIgnoreCase("cellGlobalId") ||
                    areaType.equalsIgnoreCase("routingAreaId"))
                    lac = Integer.parseInt(areaId[i]);
                else if (areaType.equalsIgnoreCase("trackingAreaId"))
                    tac = Integer.parseInt(areaId[i]);
                else if (areaType.equalsIgnoreCase("utranCellId"))
                    uci = Integer.parseInt(areaId[i]);
                else if (areaType.equalsIgnoreCase("eUtranCellId")) {
                    enbid = Integer.parseInt(areaId[i]);
                    eci = Long.parseLong(areaId[i]);
                }
            }
            if (i==3) {
                if (areaType.equalsIgnoreCase("cellGlobalId") || areaType.equalsIgnoreCase("routingAreaId"))
                    ci = rac = Integer.parseInt(areaId[i]);
                else if (areaType.equalsIgnoreCase("eUtranCellId"))
                    ci = Integer.parseInt(areaId[i]);
            }
        }
        if (areaType.equalsIgnoreCase("countryCode")) {
            if (mcc < 1 || mcc > 999)
                return null;
            else
                return parseTBCD(String.valueOf(mcc));
        } else if (areaType.equalsIgnoreCase("plmnId")) {
            try {
                PlmnId plmnId = new PlmnIdImpl(mcc, mnc);
                return plmnId.getData();
            } catch (Exception e) {
                return null;
            }
        } else if (areaType.equalsIgnoreCase("locationAreaId")) {
            LAIFixedLengthImpl lai = new LAIFixedLengthImpl();
            try {
                lai.setData(mcc, mnc, lac);
                return lai.getData();
            } catch (MAPException e) {
                return null;
            }
        } else if (areaType.equalsIgnoreCase("cellGlobalId")) {
            CellGlobalIdOrServiceAreaIdFixedLengthImpl cgi = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
            try {
                cgi.setData(mcc, mnc, lac, ci);
                areaIdTbcd = cgi.getData();
                return areaIdTbcd;
            } catch (MAPException e) {
                return null;
            }
        } else if (areaType.equalsIgnoreCase("utranCellId")) {
            UTRANCellIdImpl utranCellId = new UTRANCellIdImpl();
            try {
                utranCellId.setData(mcc, mnc, uci);
                areaIdTbcd = utranCellId.getData();
                return areaIdTbcd;
            } catch (MAPException e) {
                return null;
            }
        } else if (areaType.equalsIgnoreCase("routingAreaId")) {
            RoutingAreaIdImpl rai = new RoutingAreaIdImpl();
            try {
                rai.setData(mcc, mnc, lac, rac);
                areaIdTbcd = rai.getData();
                return areaIdTbcd;
            } catch (MAPException e) {
                return null;
            }
        } else if (areaType.equalsIgnoreCase("trackingAreaId")) {
            TrackingAreaIdImpl tai = new TrackingAreaIdImpl();
            try {
                tai.setData(mcc, mnc, tac);
                areaIdTbcd = tai.getData();
                return areaIdTbcd;
            } catch (MAPException e) {
                return null;
            }
        } else if (areaType.equalsIgnoreCase("eUtranCellId")) {
            EUTRANCGIImpl ecgi = new EUTRANCGIImpl();
            try {
                if (ci > -1)
                    ecgi.setData(mcc, mnc, enbid, ci);
                else
                    ecgi.setData(mcc, mnc, eci);
                areaIdTbcd = ecgi.getData();
                return areaIdTbcd;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
