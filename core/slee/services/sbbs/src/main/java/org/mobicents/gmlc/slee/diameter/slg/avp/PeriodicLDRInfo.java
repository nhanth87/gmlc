package org.mobicents.gmlc.slee.diameter.slg.avp;

/**
 * Interface representing Periodic Location Deferred Request (LDR) Information.
 * This interface provides access to parameters for periodic location reporting,
 * where location is reported at regular intervals.
 * 
 * @author <a href="mailto:abhayani@tenilab.org"> Amit Bhayani </a>
 */
public interface PeriodicLDRInfo {

    /**
     * Gets the reporting amount.
     * This specifies the number of location reports to be generated.
     * 
     * @return the reporting amount as a long value
     */
    long getReportingAmount();

    /**
     * Gets the reporting interval.
     * This specifies the time interval between consecutive location reports in seconds.
     * 
     * @return the reporting interval as a long value
     */
    long getReportingInterval();
}
