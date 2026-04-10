package org.mobicents.gmlc.slee.supl;

/**
 * SUPL_INIT message stub class for SUPL
 */
public class SUPL_INIT {
    
    /**
     * SLP Mode enumeration
     */
    public enum SLPMode {
        PROXY,
        NON_PROXY;
        
        public static SLPMode proxy() {
            return PROXY;
        }
        
        public static SLPMode nonProxy() {
            return NON_PROXY;
        }
    }
}
