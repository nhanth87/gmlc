// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface LCSPrivacyCheck {

    int _OVERRIDE_DISABLED = 0;
    int _OVERRIDE_ENABLED = 1;
    int _CALLING_DISABLED = 2;
    int _CALLING_ENABLED = 3;

    int getValue();

    static LCSPrivacyCheck fromInt(int value) {
        return null;
    }

    static LCSPrivacyCheck fromInt(Integer value) {
        return null;
    }
}
