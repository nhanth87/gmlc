package com.objsys.asn1j.runtime;

public class Asn1OctetString {
    public byte[] value;
    public Asn1OctetString() {}
    public Asn1OctetString(byte[] data) {
        this.value = data;
    }
}
