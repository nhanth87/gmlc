package org.mobicents.gmlc.slee.map;

/**
 * Wrapper for converting between different IMEI interface types.
 * 
 * GMLC code uses both:
 * - org.restcomm.protocols.ss7.map.api.primitives.IMEI
 * - org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI
 * 
 * They are incompatible but represent the same thing. This wrapper provides
 * conversion between them.
 */
public final class IMEIWrapper {

    private IMEIWrapper() {
        // Utility class, prevent instantiation
    }

    /**
     * Wraps a primitives.IMEI as subscriberInformation.IMEI
     *
     * @param imei the IMEI from primitives package
     * @return the wrapped IMEI for subscriberInformation package, or null if input is null
     */
    public static org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI wrap(
            org.restcomm.protocols.ss7.map.api.primitives.IMEI imei) {
        if (imei == null) {
            return null;
        }
        return new IMEIWrapperImpl(imei);
    }

    /**
     * Unwraps a subscriberInformation.IMEI to primitives.IMEI
     *
     * @param imei the IMEI from subscriberInformation package
     * @return the unwrapped IMEI for primitives package, or null if input is null
     */
    public static org.restcomm.protocols.ss7.map.api.primitives.IMEI unwrap(
            org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI imei) {
        if (imei == null) {
            return null;
        }
        return new IMEIUnwrapperImpl(imei);
    }

    /**
     * Implementation of subscriberInformation.IMEI that wraps primitives.IMEI
     */
    private static class IMEIWrapperImpl implements org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI {
        private final org.restcomm.protocols.ss7.map.api.primitives.IMEI imei;

        IMEIWrapperImpl(org.restcomm.protocols.ss7.map.api.primitives.IMEI imei) {
            this.imei = imei;
        }

        @Override
        public String getIMEI() {
            return imei.getIMEI();
        }

        @Override
        public String toString() {
            return "IMEIWrapper{imei=" + getIMEI() + "}";
        }
    }

    /**
     * Implementation of primitives.IMEI that wraps subscriberInformation.IMEI
     */
    private static class IMEIUnwrapperImpl implements org.restcomm.protocols.ss7.map.api.primitives.IMEI {
        private final org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI imei;

        IMEIUnwrapperImpl(org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI imei) {
            this.imei = imei;
        }

        @Override
        public String getIMEI() {
            return imei.getIMEI();
        }

        @Override
        public String getIMEISV() {
            // subscriberInformation.IMEI doesn't have getIMEISV(), return getIMEI() instead
            return imei.getIMEI();
        }

        @Override
        public String toString() {
            return "IMEIUnwrapper{imei=" + getIMEI() + "}";
        }
    }
}
