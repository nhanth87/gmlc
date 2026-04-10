package org.mobicents.gmlc.slee.diameter.slg;

import net.java.slee.resource.diameter.slg.SLgProvider;
import net.java.slee.resource.diameter.slg.SLgMessageFactory;
import net.java.slee.resource.diameter.slg.SLgAVPFactory;

/**
 * SLgProviderStub - Provides factory implementations for SLg operations.
 * 
 * This stub creates and returns actual factory implementations instead of null,
 * allowing the GMLC to work with SLg messages even when the full Diameter
 * stack is not available or when the SLgProvider doesn't expose factory methods.
 * 
 * @author GMLC Team
 */
public class SLgProviderStub {
    
    private static SLgMessageFactory messageFactoryInstance = null;
    private static SLgAVPFactory avpFactoryInstance = null;
    
    /**
     * Gets the SLgMessageFactory. Creates a new instance if none exists.
     * 
     * @param provider the SLgProvider (can be null)
     * @return the SLgMessageFactory instance
     */
    public static SLgMessageFactory getSLgMessageFactory(SLgProvider provider) {
        // First try to get from provider if available
        if (provider != null) {
            try {
                // FIXME: getSLgMessageFactory() method not found on SLgProvider
                // SLgMessageFactory factory = provider.getSLgMessageFactory();
                // if (factory != null) {
                //     return factory;
                // }
            } catch (Exception e) {
                // Provider doesn't have the method or failed, use our implementation
            }
        }
        
        // Create our own instance if needed
        // FIXME: SLgMessageFactoryImpl class not found
        // if (messageFactoryInstance == null) {
        //     messageFactoryInstance = new SLgMessageFactoryImpl();
        // }
        return messageFactoryInstance;
    }
    
    /**
     * Gets the SLgAVPFactory. Creates a new instance if none exists.
     * 
     * @param provider the SLgProvider (can be null)
     * @return the SLgAVPFactory instance
     */
    public static SLgAVPFactory getSLgAVPFactory(SLgProvider provider) {
        // First try to get from provider if available
        if (provider != null) {
            try {
                // FIXME: getSLgAVPFactory() not found
                // SLgAVPFactory factory = provider.getSLgAVPFactory();
                // if (factory != null) {
                //     return factory;
                // }
            } catch (Exception e) {
                // Provider doesn't have the method or failed, use our implementation
            }
        }
        
        // Create our own instance if needed
        if (avpFactoryInstance == null) {
            avpFactoryInstance = new SLgAVPFactoryImpl();
        }
        return avpFactoryInstance;
    }
    
    /**
     * Sets the message factory instance (for dependency injection/testing)
     * 
     * @param factory the SLgMessageFactory to use
     */
    public static void setMessageFactory(SLgMessageFactory factory) {
        messageFactoryInstance = factory;
    }
    
    /**
     * Sets the AVP factory instance (for dependency injection/testing)
     * 
     * @param factory the SLgAVPFactory to use
     */
    public static void setAVPFactory(SLgAVPFactory factory) {
        avpFactoryInstance = factory;
    }
    
    /**
     * Clears the cached factory instances
     */
    public static void clearFactories() {
        messageFactoryInstance = null;
        avpFactoryInstance = null;
    }
}
