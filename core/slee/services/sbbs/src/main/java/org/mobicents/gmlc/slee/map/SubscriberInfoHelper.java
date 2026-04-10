package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.primitives.Time;
import java.lang.reflect.Method;

/**
 * Helper class for accessing SubscriberInfo methods via reflection.
 * Used when SubscriberInfo interface methods may not be available 
 * in the runtime classpath (jSS7 9.2.5 compatibility).
 */
public class SubscriberInfoHelper {
    
    /**
     * Gets the timezone from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the Time object or null if not available
     */
    public static Object getTimeZone(SubscriberInfo info) {
        return invokeMethod(info, "getTimeZone");
    }
    
    /**
     * Gets the daylight saving time from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the DaylightSavingTime object or null if not available
     */
    public static Object getDaylightSavingTime(SubscriberInfo info) {
        return invokeMethod(info, "getDaylightSavingTime");
    }
    
    /**
     * Gets the IMS voice over PS sessions indication from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the IMSVoiceOverPsSessionsIndication object or null if not available
     */
    public static Object getIMSVoiceOverPsSessionsIndication(SubscriberInfo info) {
        return invokeMethod(info, "getIMSVoiceOverPsSessionsIndication");
    }
    
    /**
     * Gets the MNP info result from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the MNPInfoRes object or null if not available
     */
    public static Object getMNPInfoRes(SubscriberInfo info) {
        return invokeMethod(info, "getMNPInfoRes");
    }
    
    /**
     * Gets the MS Classmark2 from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the MSClassmark2 object or null if not available
     */
    public static Object getMSClassmark2(SubscriberInfo info) {
        return invokeMethod(info, "getMSClassmark2");
    }
    
    /**
     * Gets the GPRS MS Class from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the GPRSMSClass object or null if not available
     */
    public static Object getGPRSMSClass(SubscriberInfo info) {
        return invokeMethod(info, "getGPRSMSClass");
    }
    
    /**
     * Gets the last UE activity time from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the UEActivityTime object or null if not available
     */
    public static Object getLastUEActivityTime(SubscriberInfo info) {
        return invokeMethod(info, "getLastUEActivityTime");
    }
    
    /**
     * Gets the last RAT type from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the RATType object or null if not available
     */
    public static Object getLastRATType(SubscriberInfo info) {
        return invokeMethod(info, "getLastRATType");
    }
    
    /**
     * Gets the EPS subscriber state from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the EPSSubscriberState object or null if not available
     */
    public static Object getEPSSubscriberState(SubscriberInfo info) {
        return invokeMethod(info, "getEPSSubscriberState");
    }
    
    /**
     * Gets the location information EPS from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the PSLocationInformation object or null if not available
     */
    public static Object getLocationInformationEPS(SubscriberInfo info) {
        return invokeMethod(info, "getLocationInformationEPS");
    }
    
    /**
     * Gets the location information 5GS from SubscriberInfo using reflection.
     * @param info the SubscriberInfo object
     * @return the LocationInformation5GS object or null if not available
     */
    public static Object getLocationInformation5GS(SubscriberInfo info) {
        return invokeMethod(info, "getLocationInformation5GS");
    }
    
    /**
     * Generic method invoker using reflection.
     * @param target the target object to invoke method on
     * @param methodName the method name to invoke
     * @return the result of method invocation or null if failed
     */
    private static Object invokeMethod(Object target, String methodName) {
        if (target == null) return null;
        try {
            Method method = target.getClass().getMethod(methodName);
            return method.invoke(target);
        } catch (Exception e) {
            return null;
        }
    }
}
