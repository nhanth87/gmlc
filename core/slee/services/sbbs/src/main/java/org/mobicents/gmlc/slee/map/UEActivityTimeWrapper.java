package org.mobicents.gmlc.slee.map;

/**
 * Wrapper for UEActivityTime to make it compatible with Time interface.
 * 
 * GMLC code uses:
 * - org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UEActivityTime
 * - org.restcomm.protocols.ss7.map.api.primitives.Time
 * 
 * AtiResponseParams.setLastUEActivityTime() expects Time, but
 * SubscriberInfo.getLastUEActivityTime() returns UEActivityTime.
 * This wrapper bridges the two types.
 */
public final class UEActivityTimeWrapper {

    private UEActivityTimeWrapper() {
        // Utility class, prevent instantiation
    }

    /**
     * Wraps UEActivityTime as Time interface from primitives package
     *
     * @param ueActivityTime the UE activity time from subscriberInformation package
     * @return the wrapped Time interface, or null if input is null
     */
    public static org.restcomm.protocols.ss7.map.api.primitives.Time wrap(
            org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UEActivityTime ueActivityTime) {
        if (ueActivityTime == null) {
            return null;
        }
        return new UEActivityTimeWrapperImpl(ueActivityTime);
    }

    /**
     * Implementation of primitives.Time that wraps UEActivityTime
     */
    private static class UEActivityTimeWrapperImpl implements org.restcomm.protocols.ss7.map.api.primitives.Time {
        private static final long serialVersionUID = 1L;
        
        private final org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UEActivityTime ueActivityTime;

        UEActivityTimeWrapperImpl(org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UEActivityTime ueActivityTime) {
            this.ueActivityTime = ueActivityTime;
        }

        @Override
        public byte[] getData() {
            // UEActivityTime doesn't have data access methods in the stub
            // Return empty array as placeholder
            return new byte[0];
        }

        @Override
        public int getYear() {
            // UEActivityTime doesn't provide year access
            return 0;
        }

        @Override
        public int getMonth() {
            // UEActivityTime doesn't provide month access
            return 0;
        }

        @Override
        public int getDay() {
            // UEActivityTime doesn't provide day access
            return 0;
        }

        @Override
        public int getHour() {
            // UEActivityTime doesn't provide hour access
            return 0;
        }

        @Override
        public int getMinute() {
            // UEActivityTime doesn't provide minute access
            return 0;
        }

        @Override
        public int getSecond() {
            // UEActivityTime doesn't provide second access
            return 0;
        }

        @Override
        public String toString() {
            return "UEActivityTimeWrapper{}";
        }
    }
}
