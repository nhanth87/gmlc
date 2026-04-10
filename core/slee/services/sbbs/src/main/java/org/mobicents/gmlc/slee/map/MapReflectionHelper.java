/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2024, TeleStax Inc. and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 */

package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation5GS;

import java.lang.reflect.Method;

/**
 * Helper class to access MAP API methods that may not exist in jSS7 9.2.5.
 * Uses reflection to safely call methods if they exist, returning null otherwise.
 */
public final class MapReflectionHelper {

    private MapReflectionHelper() {
        // Utility class
    }

    @SuppressWarnings("unchecked")
    private static <T> T safeInvoke(Object target, String methodName, Class<T> returnType) {
        if (target == null) {
            return null;
        }
        try {
            Method method = target.getClass().getMethod(methodName);
            Object result = method.invoke(target);
            return returnType.isInstance(result) ? (T) result : null;
        } catch (NoSuchMethodException e) {
            // Method doesn't exist in this jSS7 version
            return null;
        } catch (Exception e) {
            // Other errors - return null
            return null;
        }
    }

    // ==================== SubscriberInfo methods ====================

    public static Object getMSClassmark2(SubscriberInfo subscriberInfo) {
        return safeInvoke(subscriberInfo, "getMSClassmark2", Object.class);
    }

    public static Object getMNPInfoRes(SubscriberInfo subscriberInfo) {
        return safeInvoke(subscriberInfo, "getMNPInfoRes", Object.class);
    }

    public static Object getIMSVoiceOverPsSessionsIndication(SubscriberInfo subscriberInfo) {
        return safeInvoke(subscriberInfo, "getIMSVoiceOverPsSessionsIndication", Object.class);
    }

    public static Object getLastUEActivityTime(SubscriberInfo subscriberInfo) {
        return safeInvoke(subscriberInfo, "getLastUEActivityTime", Object.class);
    }

    public static Object getLastRATType(SubscriberInfo subscriberInfo) {
        return safeInvoke(subscriberInfo, "getLastRATType", Object.class);
    }

    public static Object getEPSSubscriberState(SubscriberInfo subscriberInfo) {
        return safeInvoke(subscriberInfo, "getEPSSubscriberState", Object.class);
    }

    public static Object getLocationInformationEPS(SubscriberInfo subscriberInfo) {
        return safeInvoke(subscriberInfo, "getLocationInformationEPS", Object.class);
    }

    public static Object getTimeZone(SubscriberInfo subscriberInfo) {
        return safeInvoke(subscriberInfo, "getTimeZone", Object.class);
    }

    public static Object getDaylightSavingTime(SubscriberInfo subscriberInfo) {
        return safeInvoke(subscriberInfo, "getDaylightSavingTime", Object.class);
    }

    public static LocationInformation5GS getLocationInformation5GS(SubscriberInfo subscriberInfo) {
        Object result = safeInvoke(subscriberInfo, "getLocationInformation5GS", Object.class);
        return result instanceof LocationInformation5GS ? (LocationInformation5GS) result : null;
    }

    // ==================== LocationInformation methods ====================

    public static Object getLocationInformationEPS(LocationInformation locationInfo) {
        return safeInvoke(locationInfo, "getLocationInformationEPS", Object.class);
    }

    public static Object getGeographicalInformation(LocationInformation locationInfo) {
        return safeInvoke(locationInfo, "getGeographicalInformation", Object.class);
    }

    public static Object getGeodeticInformation(LocationInformation locationInfo) {
        return safeInvoke(locationInfo, "getGeodeticInformation", Object.class);
    }

    public static Boolean isCurrentLocationRetrieved(LocationInformation locationInfo) {
        return safeInvoke(locationInfo, "isCurrentLocationRetrieved", Boolean.class);
    }

    public static Boolean isSaiPresent(LocationInformation locationInfo) {
        return safeInvoke(locationInfo, "isSaiPresent", Boolean.class);
    }

    // ==================== LocationInformation5GS methods ====================

    public static Boolean isCurrentLocationRetrieved(LocationInformation5GS locationInfo5GS) {
        if (locationInfo5GS == null) {
            return null;
        }
        // First try direct method
        Boolean result = safeInvoke(locationInfo5GS, "isCurrentLocationRetrieved", Boolean.class);
        if (result != null) {
            return result;
        }
        // Try alternative method names
        try {
            Method method = locationInfo5GS.getClass().getMethod("getCurrentLocationRetrieved");
            Object value = method.invoke(locationInfo5GS);
            return value instanceof Boolean ? (Boolean) value : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Object getEUtranCellGlobalIdentity(LocationInformation5GS locationInfo5GS) {
        return safeInvoke(locationInfo5GS, "getEUtranCellGlobalIdentity", Object.class);
    }

    public static Object getNrCellGlobalIdentity(LocationInformation5GS locationInfo5GS) {
        return safeInvoke(locationInfo5GS, "getNrCellGlobalIdentity", Object.class);
    }

    // ==================== ISDNAddressString methods ====================

    /**
     * Gets the NumberingPlan from an ISDNAddressString.
     * This method may not exist in jSS7 9.2.5, so we use reflection.
     * @param isdnAddressString the ISDN address string
     * @return the NumberingPlan object, or null if the method doesn't exist
     */
    public static Object getNumberingPlan(ISDNAddressString isdnAddressString) {
        return safeInvoke(isdnAddressString, "getNumberingPlan", Object.class);
    }
}
