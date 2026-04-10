package net.java.slee.resource.diameter.slg.events.avp;

/**
 * Accuracy Fulfilment Indicator enum as defined in 3GPP TS 29.172.
 * 
 * This enum indicates whether the requested accuracy was fulfilled or not.
 * 
 * @author GMLC Team
 */
public enum AccuracyFulfilmentIndicator {

    /**
     * The requested accuracy was fulfilled.
     */
    REQUESTED_ACCURACY_FULFILLED(0),

    /**
     * The requested accuracy was not fulfilled.
     */
    REQUESTED_ACCURACY_NOT_FULFILLED(1);

    private final int value;

    private AccuracyFulfilmentIndicator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AccuracyFulfilmentIndicator fromInt(int value) {
        switch (value) {
            case 0:
                return REQUESTED_ACCURACY_FULFILLED;
            case 1:
                return REQUESTED_ACCURACY_NOT_FULFILLED;
            default:
                return null;
        }
    }
}
