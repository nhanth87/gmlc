package org.mobicents.gmlc.slee.diameter.slg.avp;

/**
 * Interface representing Deferred Mobile Terminated Location Request (MT-LR) Data.
 * This interface provides access to data related to deferred location requests where
 * the location is reported when the target mobile becomes available or when a specific
 * event occurs.
 * 
 * @author <a href="mailto:abhayani@tenilab.org"> Amit Bhayani </a>
 */
public interface DeferredMTLRData {

    /**
     * Gets the deferred location type.
     * This indicates the type of trigger event for the deferred location request.
     * 
     * @return the deferred location type as a long value
     */
    long getDeferredLocationType();

    /**
     * Gets the termination cause.
     * This indicates the reason for termination of the deferred location request.
     * 
     * @return the termination cause as a long value
     */
    long getTerminationCause();
}
