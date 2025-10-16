package org.mobicents.gmlc.slee.primitives;

import com.google.common.collect.Multimap;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface EUTRANPositioningData extends Serializable {

    public byte[] getData();

    public byte[] getPositioningDataSet();

    public byte[] getGNSSPositioningDataSet();

    public byte[] getAdditionalPositioningDataSet();

    public HashMap<String, Integer> getPositioningDataMethodsAndUsage(byte[] positioningDataSet) throws Exception;

    public Multimap<String, String> getGNSSPositioningMethodsAndGNSSIds(byte[] gnssPositioningDataSet) throws Exception;

    public Multimap<String, String> getEUtranAdditionalPositioningMethodsAndIds(byte[] additionalPositioningDataSet) throws Exception;

    public String getPositioningDataSetUsage(int u);

    public String getUsage(byte[] data, int index) throws Exception;

    public int getUsageCode(byte[] data, int index);
}
