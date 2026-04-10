package org.mobicents.gmlc.slee.concurrent;

public class SuplTransaction {
    private static final SuplTransaction INSTANCE = new SuplTransaction();
    
    private SuplTransaction() {}
    
    public static SuplTransaction Instance() {
        return INSTANCE;
    }
}
