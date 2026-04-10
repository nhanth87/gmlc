package net.java.slee.resource.diameter.slh;

import net.java.slee.resource.diameter.base.DiameterAvpFactory;
import net.java.slee.resource.diameter.base.events.avp.DiameterAvp;

/**
 * 
 * Diameter SLh AVP factory interface defining methods to create SLh specific AVPs.
 * 
 * @author GMLC Team
 */
public interface SLhAVPFactory {

    /**
     * Returns the base AVP factory
     * 
     * @return DiameterAvpFactory instance
     */
    DiameterAvpFactory getBaseFactory();

    /**
     * Creates a Diameter AVP with the given code and value.
     * 
     * @param code the AVP code
     * @param value the AVP value
     * @return DiameterAvp instance
     */
    DiameterAvp createAvp(int code, int value);

    /**
     * Creates a Diameter AVP with the given code, vendor ID, and value.
     * 
     * @param code the AVP code
     * @param vendorId the vendor ID
     * @param value the AVP value
     * @return DiameterAvp instance
     */
    DiameterAvp createAvp(int code, long vendorId, int value);

    /**
     * Creates a Diameter AVP with the given code and value.
     * 
     * @param code the AVP code
     * @param value the AVP value as long
     * @return DiameterAvp instance
     */
    DiameterAvp createAvp(int code, long value);

    /**
     * Creates a Diameter AVP with the given code, vendor ID, and value.
     * 
     * @param code the AVP code
     * @param vendorId the vendor ID
     * @param value the AVP value as long
     * @return DiameterAvp instance
     */
    DiameterAvp createAvp(int code, long vendorId, long value);

    /**
     * Creates a Diameter AVP with the given code and value.
     * 
     * @param code the AVP code
     * @param value the AVP value as String
     * @return DiameterAvp instance
     */
    DiameterAvp createAvp(int code, String value);

    /**
     * Creates a Diameter AVP with the given code, vendor ID, and value.
     * 
     * @param code the AVP code
     * @param vendorId the vendor ID
     * @param value the AVP value as String
     * @return DiameterAvp instance
     */
    DiameterAvp createAvp(int code, long vendorId, String value);

    /**
     * Creates a Diameter AVP with the given code and value.
     * 
     * @param code the AVP code
     * @param value the AVP value as byte[]
     * @return DiameterAvp instance
     */
    DiameterAvp createAvp(int code, byte[] value);

    /**
     * Creates a Diameter AVP with the given code, vendor ID, and value.
     * 
     * @param code the AVP code
     * @param vendorId the vendor ID
     * @param value the AVP value as byte[]
     * @return DiameterAvp instance
     */
    DiameterAvp createAvp(int code, long vendorId, byte[] value);
}
