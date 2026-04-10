package org.mobicents.gmlc.slee.supl.ULP_Components;

/**
 * PosMethod (Positioning Method) stub class for SUPL
 */
public class PosMethod {
    
    private String method;
    
    private PosMethod(String method) {
        this.method = method;
    }
    
    public static PosMethod agpsSETassisted() {
        return new PosMethod("agpsSETassisted");
    }
    
    public static PosMethod agpsSETbased() {
        return new PosMethod("agpsSETbased");
    }
    
    public static PosMethod autonomousGPS() {
        return new PosMethod("autonomousGPS");
    }
    
    public static PosMethod aflt() {
        return new PosMethod("aflt");
    }
    
    public static PosMethod eCID() {
        return new PosMethod("eCID");
    }
    
    public static PosMethod otdoa() {
        return new PosMethod("otdoa");
    }
    
    public String getMethod() {
        return method;
    }
}
