package org.mobicents.gmlc.slee.primitives;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class EUTRANPositioningDataImpl implements EUTRANPositioningData {

    private final byte[] data;
    private byte[] positioningDataSet;
    private byte[] gNSSPositioningDataSet;
    private byte[] additionalPositioningDataSet;

    public EUTRANPositioningDataImpl(byte[] eutranPositioningData) throws Exception {
        if (eutranPositioningData == null)
            throw new Exception("EUTRAN-Positioning-Data AVP Positioning Data Set must not be empty");
        this.data = eutranPositioningData;
        int dataType = (data[0] & 0xF0) >> 4;
        int dataLength = data[0] & 0x0F;
        switch (dataType) {
            case 2:
                if (dataLength > 9)
                    throw new Exception("EUTRAN-Positioning-Data AVP GNSS Positioning Data Set must not be higher than 9");
                gNSSPositioningDataSet = Arrays.copyOfRange(data, 1, data.length);
                break;
            case 4:
                if (dataLength > 9)
                    throw new Exception("EUTRAN-Positioning-Data AVP Positioning Data Set must not be higher than 9");
                positioningDataSet = Arrays.copyOfRange(data, 1, data.length);
                break;
            default:
                if (dataLength > 8)
                    throw new Exception("EUTRAN-Positioning-Data AVP Additional Positioning Data Set must not be higher than 8");
                additionalPositioningDataSet = Arrays.copyOfRange(data, 1, data.length);
                break;
        }
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public byte[] getPositioningDataSet() {
        return positioningDataSet;
    }

    @Override
    public byte[] getGNSSPositioningDataSet() {
        return gNSSPositioningDataSet;
    }

    @Override
    public byte[] getAdditionalPositioningDataSet() {
        return additionalPositioningDataSet;
    }

    @Override
    public HashMap<String, Integer> getPositioningDataMethodsAndUsage(byte[] positioningDataSet) throws Exception {
        if (positioningDataSet == null)
            throw new Exception("EUTRAN-Positioning-Data AVP Positioning Data Set must not be empty");
        if (positioningDataSet.length > 9)
            throw new Exception("EUTRAN-Positioning-Data AVP Positioning Data Set must not be higher than 9");

        LinkedHashMap<String, Integer> posMethodsAndUsage = new LinkedHashMap<>();
        String positioningMethod;
        int usage;

        for (int i=0; i<=positioningDataSet.length-1; i++) {
            positioningMethod = getPositioningMethod((positioningDataSet[i] & 0xF8) >> 3);
            usage = positioningDataSet[i] & 0x07;
            posMethodsAndUsage.put(positioningMethod, usage);
        }
        return posMethodsAndUsage;
    }

    public String getPositioningMethod(int code) throws Exception {
        /*
         * Coding of positioning method (bits 8-4):
         *  00000 Cell ID
         *  00001 Reserved
         *  00010 E-CID
         *  00011 Reserved
         *  00100 OTDOA
         *  00101 Reserved
         *  00110 Reserved
         *  00111 Reserved
         *  01000 U-TDOA
         *  01001 Reserved
         *  01010 Reserved
         *  01011 Reserved
         *  01100 to 01111 reserved for other location technologies
         *  10000 to 11111 reserved for network specific positioning methods
         */
        if (code > 31)
            throw new Exception("EUTRAN Positioning Method code must be an integer value between 0 and 3");

        String posMethod;
        switch (code) {
            case 0:
                posMethod = "Cell ID";
                break;
            case 2:
                posMethod = "E-CID";
                break;
            case 4:
                posMethod = "OTDOA";
                break;
            case 8:
                posMethod = "U-TDOA";
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
            case 10:
            case 11:
                posMethod = "Reserved";
                break;
            case 12:
            case 13:
            case 14:
            case 15:
                posMethod = "reserved for other location technologies";
                break;
            default:
                posMethod = "reserved for network specific positioning methods";
                break;
        }
        return posMethod;
    }

    @Override
    public String getPositioningDataSetUsage(int u) {
        String usage = null;
        /*
         * Coding of usage (bits 3-1)
         *  000 Attempted unsuccessfully due to failure or interruption - not used.
         *  001 Attempted successfully: results not used to generate location - not used.
         *  010 Attempted successfully: results used to verify but not generate location - not used.
         *  011 Attempted successfully: results used to generate location.
         *  100 Attempted successfully: case where UE supports multiple mobile based positioning methods
         *      and the actual method or methods used by the UE cannot be determined.
         */
        switch (u) {
            case 0:
                usage = "Attempted unsuccessfully due to failure or interruption - not used";
                break;
            case 1:
                usage = "Attempted successfully: results not used to generate location - not used";
                break;
            case 2:
                usage = "Attempted successfully: results used to verify but not generate location - not used";
                break;
            case 3:
                usage = "Attempted successfully: results used to generate location";
                break;
            case 4:
                usage = "Attempted successfully: case where UE supports multiple mobile based positioning methods and the actual method or methods " +
                        "used by the UE cannot be determined";
                break;
        }
        return usage;
    }

    @Override
    public Multimap<String, String> getGNSSPositioningMethodsAndGNSSIds(byte[] gnssPositioningDataSet) throws Exception {
        if (gnssPositioningDataSet == null)
            throw new Exception("EUTRAN-Positioning-Data AVP GNSS Positioning Data Set must not be empty");
        if (gnssPositioningDataSet.length > 9)
            throw new Exception("EUTRAN-Positioning-Data AVP GNSS Positioning Data Set must not be higher than 9");

        Multimap<String, String> methodsAndGNSSId = LinkedHashMultimap.create();
        String method;
        String ganssId;

        for (int i=0; i<=gnssPositioningDataSet.length-1; i++) {
            method = getGnssPositioningMethod((gnssPositioningDataSet[i] & 0xC0) >> 6);
            ganssId = getGNSSId((gnssPositioningDataSet[i] & 0x38) >> 3);
            methodsAndGNSSId.put(method, ganssId);
        }
        return methodsAndGNSSId;
    }

    public String getGnssPositioningMethod(int code) throws Exception {
        /*
         * Coding of Method (bits 8-7):
         * 00   UE-Based
         * 01   UE-Assisted
         * 10   Conventional
         * 11   Reserved
         */
        if (code > 3)
            throw new Exception("EUTRAN GNSS Positioning Method code must be an integer value between 0 and 3");

        String method = null;
        switch (code) {
            case 0:
                method = "UE-Based";
                break;
            case 1:
                method = "UE-Assisted";
                break;
            case 2:
                method = "Conventional";
                break;
            case 3:
                method = "Reserved";
                break;
        }
        return method;
    }

    public String getGNSSId(int code) throws Exception {
        /*
         * Coding of the GNSS Id (bits 6-4) :
         *  000 : GPS
         *  001 : Galileo
         *  010 : SBAS
         *  011 : Modernized GPS
         *  100 : QZSS
         *  101 : GLONASS
         *  110 : BDS
         *  111 : NavIC
         */
        if (code > 7)
            throw new Exception("GNSS Id must be an integer value between 0 and 7");

        String gnssId = null;
        switch (code) {
            case 0:
                gnssId = "GPS";
                break;
            case 1:
                gnssId = "Galileo";
                break;
            case 2:
                gnssId = "SBAS";
                break;
            case 3:
                gnssId = "Modernized GPS";
                break;
            case 4:
                gnssId = "QZSS";
                break;
            case 5:
                gnssId = "GLONASS";
                break;
            case 6:
                gnssId = "BDS";
                break;
            case 7:
                gnssId = "NavIC";
                break;
        }
        return gnssId;
    }


    @Override
    public String getUsage(byte[] data, int index) {
        String usage = null;
        /*
         * Coding of usage (bits 3-1):
         * 000  Attempted unsuccessfully due to failure or interruption
         * 001  Attempted successfully: results not used to generate location
         * 010  Attempted successfully: results used to verify but not generate location
         * 011  Attempted successfully: results used to generate location
         * 100  Attempted successfully: case where UE supports multiple mobile based positioning methods and the actual method or methods used by the UE cannot be determined.
         *
         */
        switch (getUsageCode(data, index)) {
            case 0:
                usage = "Attempted unsuccessfully due to failure or interruption";
                break;
            case 1:
                usage = "Attempted successfully: results not used to generate location";
                break;
            case 2:
                usage = "Attempted successfully: results used to verify but not generate location";
                break;
            case 3:
                usage = "Attempted successfully: results used to generate location";
                break;
            case 4:
                usage = "Attempted successfully: case where UE supports multiple mobile based positioning methods and the actual method or methods " +
                        "used by the UE cannot be determined";
                break;
        }
        return usage;
    }

    @Override
    public int getUsageCode(byte[] data, int index) {
        return data[index] & 0x07;
    }

    @Override
    public Multimap<String, String> getEUtranAdditionalPositioningMethodsAndIds(byte[] additionalPositioningDataSet) throws Exception {
        if (additionalPositioningDataSet == null)
            throw new Exception("EUTRAN Additional Positioning Data set must not be null");
        if (additionalPositioningDataSet.length > 8)
            throw new Exception("EUTRAN Additional Positioning Data set length must not be higher than 8");

        Multimap<String, String> methodsAndPosId = LinkedHashMultimap.create();
        String method;
        String posId;

        for (int i=0; i<=additionalPositioningDataSet.length-1; i++) {
            method = getAdditionalPositioningMethod((additionalPositioningDataSet[i] & 0xC0) >> 6);
            posId = getAdditionalPositioningId((additionalPositioningDataSet[i] & 0x38) >> 3);
            methodsAndPosId.put(method, posId);
        }
        return methodsAndPosId;
    }

    public String getAdditionalPositioningMethod(int code) throws Exception {
        /*
         * Coding of positioning method (bits 8-7):
         * 00 UE-Based
         * 01 UE-Assisted
         * 10 Standalone
         * 11 Reserved
         */
        if (code > 3)
            throw new Exception("EUTRAN Additional Positioning Method code must be an integer value between 0 and 3");

        String posMethod = null;
        switch (code) {
            case 0:
                posMethod = "UE-Based";
                break;
            case 1:
                posMethod = "UE-Assisted";
                break;
            case 2:
                posMethod = "Standalone";
                break;
            case 3:
                posMethod = "Reserved";
                break;
        }
        return posMethod;
    }

    public String getAdditionalPositioningId(int id) throws Exception {
        /*
         * Coding of Additional Positioning ID (bits 6-4):
         * 000 Barometric Pressure
         * 001 WLAN
         * 010 Bluetooth
         * 011 MBS
         * 100 Motion-Sensor(s)
         * other values reserved.
         */
        if (id > 7)
            throw new Exception("EUTRAN Additional Positioning Id must be an integer value between 0 and 7");

        String additionalPositioningId;
        switch (id) {
            case 0:
                additionalPositioningId = "Barometric Pressure";
                break;
            case 1:
                additionalPositioningId = "WLAN";
                break;
            case 2:
                additionalPositioningId = "Bluetooth";
                break;
            case 3:
                additionalPositioningId = "MBS";
                break;
            case 4:
                additionalPositioningId = "Motion-Sensor(s)";
                break;
            default:
                additionalPositioningId = "reserved";
                break;
        }
        return additionalPositioningId;
    }
}
