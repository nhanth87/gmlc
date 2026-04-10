package org.mobicents.gmlc.slee.diameter.slg;

import net.java.slee.resource.diameter.base.events.avp.Address;

/**
 * Extended Location Report Request interface for SLg-specific methods.
 * This interface extends the basic LocationReportRequest from diameter-slh
 * with additional SLg-specific methods required by the GMLC implementation.
 *
 * @author GMLC Team
 * @version 1.0
 */
public interface ExtendedLocationReportRequest extends net.java.slee.resource.diameter.slh.events.LocationReportRequest {

    boolean hasUTRANPositioningInfo();
    Object getUTRANPositioningInfo();

    boolean hasGERANPositioningInfo();
    Object getGERANPositioningInfo();

    boolean hasServingNode();
    Object getServingNode();

    boolean hasLCSServiceTypeID();
    int getLCSServiceTypeID();

    boolean hasPseudonymIndicator();
    long getPseudonymIndicator();

    boolean hasLCSQoSClass();
    long getLCSQoSClass();

    boolean hasLCSReferenceNumber();
    byte[] getLCSReferenceNumber();

    boolean hasDeferredMTLRData();
    Object getDeferredMTLRData();

    boolean hasPeriodicLDRInformation();
    Object getPeriodicLDRInformation();

    boolean hasESMLCCellInfo();
    Object getESMLCCellInfo();

    boolean hasLRRFlags();
    long getLRRFlags();

    boolean hasCellGlobalIdentity();
    byte[] getCellGlobalIdentity();

    boolean hasServiceAreaIdentity();
    byte[] getServiceAreaIdentity();

    boolean hasReportingAmount();
    long getReportingAmount();

    boolean has1xRTTRCID();
    byte[] get1xRTTRCID();

    boolean hasDelayedLocationReportingData();
    Object getDelayedLocationReportingData();

    boolean hasGMLCAddress();
    Address getGMLCAddress();

    boolean hasVelocityEstimate();
    byte[] getVelocityEstimate();

    boolean hasEUTRANPositioningData();
    byte[] getEUTRANPositioningData();
}
