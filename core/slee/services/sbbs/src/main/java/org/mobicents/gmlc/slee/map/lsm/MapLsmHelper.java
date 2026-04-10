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

package org.mobicents.gmlc.slee.map.lsm;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSLocationInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberLocationReportRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranAdditionalPositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranBaroPressureMeas;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranCivicAddress;

/**
 * Helper class to access MAP LSM API methods that may not exist in jSS7 9.2.5.
 * Uses reflection to safely call methods if they exist, returning null otherwise.
 */
public final class MapLsmHelper {

    private MapLsmHelper() {
        // Utility class
    }

    @SuppressWarnings("unchecked")
    private static <T> T safeInvoke(Object target, String methodName, Class<T> returnType) {
        if (target == null) {
            return null;
        }
        try {
            java.lang.reflect.Method method = target.getClass().getMethod(methodName);
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

    // ==================== ProvideSubscriberLocationResponse methods ====================

    public static UtranAdditionalPositioningData getUtranAdditionalPositioningData(ProvideSubscriberLocationResponse response) {
        return safeInvoke(response, "getUtranAdditionalPositioningData", UtranAdditionalPositioningData.class);
    }

    public static UtranBaroPressureMeas getUtranBaroPressureMeas(ProvideSubscriberLocationResponse response) {
        return safeInvoke(response, "getUtranBaroPressureMeas", UtranBaroPressureMeas.class);
    }

    public static UtranCivicAddress getUtranCivicAddress(ProvideSubscriberLocationResponse response) {
        return safeInvoke(response, "getUtranCivicAddress", UtranCivicAddress.class);
    }

    // ==================== SubscriberLocationReportRequest methods ====================

    public static UtranAdditionalPositioningData getUtranAdditionalPositioningData(SubscriberLocationReportRequest request) {
        return safeInvoke(request, "getUtranAdditionalPositioningData", UtranAdditionalPositioningData.class);
    }

    public static UtranBaroPressureMeas getUtranBaroPressureMeas(SubscriberLocationReportRequest request) {
        return safeInvoke(request, "getUtranBaroPressureMeas", UtranBaroPressureMeas.class);
    }

    public static UtranCivicAddress getUtranCivicAddress(SubscriberLocationReportRequest request) {
        return safeInvoke(request, "getUtranCivicAddress", UtranCivicAddress.class);
    }

    // ==================== LCSLocationInfo methods ====================

    public static ISDNAddressString getSgsnName(LCSLocationInfo lcsLocationInfo) {
        return safeInvoke(lcsLocationInfo, "getSgsnName", ISDNAddressString.class);
    }

    public static ISDNAddressString getSgsnRealm(LCSLocationInfo lcsLocationInfo) {
        return safeInvoke(lcsLocationInfo, "getSgsnRealm", ISDNAddressString.class);
    }
}
