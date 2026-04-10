package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 * PeriodicLDRInfo interface - Stub for jSS7 9.2.5 compatibility
 * Contains periodic location deferred request information
 */
public interface PeriodicLDRInfo {
    /**
     * Gets the Reporting Amount
     * @return the number of periodic location reports
     */
    int getReportingAmount();

    /**
     * Gets the Reporting Interval
     * @return the interval between periodic reports in seconds
     */
    int getReportingInterval();
}
