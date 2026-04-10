package com.objsys.asn1j.runtime;

public class Asn1BitString {
    public byte[] value;
    public int nbrBits;
    public Asn1BitString() {}
    public Asn1BitString(byte[] data, int bits) {
        this.value = data;
        this.nbrBits = bits;
    }
}
