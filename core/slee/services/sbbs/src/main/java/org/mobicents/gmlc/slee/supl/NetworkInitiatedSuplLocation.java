package org.mobicents.gmlc.slee.supl;

import org.mobicents.gmlc.slee.supl.SUPL_INIT.SLPMode;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.TriggerParams;
import org.mobicents.gmlc.slee.supl.ULP_Components.QoP;
import org.mobicents.gmlc.slee.supl.ULP_Components.PosMethod;

public class NetworkInitiatedSuplLocation {
    public NetworkInitiatedSuplLocation() {}
    
    public SuplResponseHelperForMLP processNetworkInitiatedSuplBySmppAreaEventTriggeredService(
            QoP qoP, int triggerType, PosMethod posMethod, SLPMode slpMode, 
            TriggerParams triggerParams, Integer transactionId, String msisdn) {
        return null;
    }
    
    public SuplResponseHelperForMLP processNetworkInitiatedSuplBySmppStandardService(
            QoP qoP, SLPMode slpMode, Integer transactionId, String msisdn) {
        return null;
    }
}
