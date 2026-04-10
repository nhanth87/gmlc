package com.objsys.asn1j.runtime;

public class Asn1EncodeBuffer {
    public byte[] buffer = new byte[1024];
    public int len;
    public void encode(Asn1BitString val) {}
    public void encode(Asn1OctetString val) {}
    public byte[] getMsgCopy() {
        return java.util.Arrays.copyOf(buffer, len);
    }
}
