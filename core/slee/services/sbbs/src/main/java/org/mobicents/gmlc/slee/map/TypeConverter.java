package org.mobicents.gmlc.slee.map;

/**
 * Type converter utility class for converting between incompatible MAP API types.
 * This class provides static methods to wrap/convert types from one package to another
 * where they represent the same logical entity but have incompatible interfaces.
 * 
 * Main conversions provided:
 * - IMEI (primitives <=> subscriberInformation)
 * - UEActivityTime => Time (primitives)
 * - RATType => UsedRATType (subscriberInformation)
 * 
 * This class delegates to specific wrapper classes:
 * - IMEIWrapper for IMEI conversions
 * - UEActivityTimeWrapper for UEActivityTime to Time conversion
 * - RATTypeWrapper for RATType to UsedRATType conversion
 */
public final class TypeConverter {

    private TypeConverter() {
        // Utility class, prevent instantiation
    }

    /**
     * Converts org.restcomm.protocols.ss7.map.api.primitives.IMEI to
     * org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI
     *
     * @param imei the IMEI from primitives package
     * @return the wrapped IMEI for subscriberInformation package
     */
    public static org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI wrapIMEI(
            org.restcomm.protocols.ss7.map.api.primitives.IMEI imei) {
        return IMEIWrapper.wrap(imei);
    }

    /**
     * Converts org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI to
     * org.restcomm.protocols.ss7.map.api.primitives.IMEI
     *
     * @param imei the IMEI from subscriberInformation package
     * @return the wrapped IMEI for primitives package
     */
    public static org.restcomm.protocols.ss7.map.api.primitives.IMEI unwrapIMEI(
            org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.IMEI imei) {
        return IMEIWrapper.unwrap(imei);
    }

    /**
     * Wraps UEActivityTime as Time interface from primitives package
     *
     * @param ueActivityTime the UE activity time
     * @return the wrapped Time interface
     */
    public static org.restcomm.protocols.ss7.map.api.primitives.Time wrapUEActivityTime(
            org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UEActivityTime ueActivityTime) {
        return UEActivityTimeWrapper.wrap(ueActivityTime);
    }

    /**
     * Converts RATType to UsedRATType (subscriberInformation package)
     *
     * @param ratType the RAT type
     * @return the UsedRATType wrapper
     */
    public static org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UsedRATType convertRATType(
            org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RATType ratType) {
        return RATTypeWrapper.wrap(ratType);
    }

    /**
     * Converts RATType to UsedRATType with a specific integer value
     *
     * @param ratType the RAT type
     * @param usedRatTypeValue the integer value for UsedRATType
     * @return the UsedRATType wrapper
     */
    public static org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UsedRATType convertRATType(
            org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RATType ratType,
            int usedRatTypeValue) {
        return RATTypeWrapper.wrap(ratType, usedRatTypeValue);
    }
}
