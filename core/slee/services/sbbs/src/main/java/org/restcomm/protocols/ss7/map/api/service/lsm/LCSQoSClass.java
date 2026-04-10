package org.restcomm.protocols.ss7.map.api.service.lsm;

public enum LCSQoSClass {
    BestEffort(0), Assured(1);
    private final int code;
    LCSQoSClass(int code) { this.code = code; }
    public int getCode() { return code; }
}
