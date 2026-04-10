package org.mobicents.gmlc.slee.map;

/**
 * Wrapper for RATType to make it compatible with UsedRATType interface.
 * 
 * GMLC code uses:
 * - org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RATType
 * - org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UsedRATType
 * 
 * AtiResponseParams.setLastRATType() expects UsedRATType, but
 * SubscriberInfo.getLastRATType() returns RATType.
 * This wrapper bridges the two types.
 */
public final class RATTypeWrapper {

    private RATTypeWrapper() {
        // Utility class, prevent instantiation
    }

    /**
     * Converts RATType to UsedRATType
     *
     * @param ratType the RAT type from subscriberInformation package
     * @return the UsedRATType wrapper, or null if input is null
     */
    public static org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UsedRATType wrap(
            org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RATType ratType) {
        if (ratType == null) {
            return null;
        }
        return new RATTypeWrapperImpl(ratType);
    }

    /**
     * Converts RATType to UsedRATType with a specific integer value
     *
     * @param ratType the RAT type from subscriberInformation package
     * @param usedRatTypeValue the integer value for UsedRATType
     * @return the UsedRATType wrapper, or null if input is null
     */
    public static org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UsedRATType wrap(
            org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RATType ratType,
            int usedRatTypeValue) {
        if (ratType == null) {
            return null;
        }
        return new RATTypeWrapperImpl(ratType, usedRatTypeValue);
    }

    /**
     * Implementation of UsedRATType that wraps RATType
     */
    private static class RATTypeWrapperImpl implements org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UsedRATType {
        private final org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RATType ratType;
        private final int usedRatTypeValue;

        RATTypeWrapperImpl(org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RATType ratType) {
            this.ratType = ratType;
            this.usedRatTypeValue = 0; // Default value
        }

        RATTypeWrapperImpl(org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RATType ratType, 
                          int usedRatTypeValue) {
            this.ratType = ratType;
            this.usedRatTypeValue = usedRatTypeValue;
        }

        @Override
        public int getUsedRATType() {
            // Return the configured value
            // The RATType interface is empty in the stub, so we return the default/configured value
            return usedRatTypeValue;
        }

        @Override
        public String toString() {
            return "RATTypeWrapper{usedRATType=" + usedRatTypeValue + "}";
        }
    }
}
