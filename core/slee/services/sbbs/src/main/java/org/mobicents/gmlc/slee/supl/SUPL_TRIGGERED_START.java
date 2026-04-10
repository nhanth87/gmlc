package org.mobicents.gmlc.slee.supl;

import com.objsys.asn1j.runtime.Asn1BitString;
import com.objsys.asn1j.runtime.Asn1Boolean;
import com.objsys.asn1j.runtime.Asn1Integer;
import org.mobicents.gmlc.slee.supl.Ver2_ULP_Components.CircularArea;
import org.mobicents.gmlc.slee.supl.Ver2_ULP_Components.EllipticalArea;

/**
 * SUPL_TRIGGERED_START message stub class for SUPL
 * Contains nested classes for area event handling
 */
public class SUPL_TRIGGERED_START {

    /**
     * AreaEventType enum representing different types of area events
     */
    public enum AreaEventType {
        ENTERING_AREA,
        LEAVING_AREA,
        INSIDE_AREA,
        OUTSIDE_AREA;

        public static AreaEventType enteringArea() {
            return ENTERING_AREA;
        }

        public static AreaEventType leavingArea() {
            return LEAVING_AREA;
        }

        public static AreaEventType insideArea() {
            return INSIDE_AREA;
        }

        public static AreaEventType outsideArea() {
            return OUTSIDE_AREA;
        }

        public static AreaEventType valueOf(int value) {
            // Return a default value for unknown codes
            return ENTERING_AREA;
        }
    }

    /**
     * RepeatedReportingParams class for SUPL repeated reporting parameters
     */
    public static class RepeatedReportingParams {
        private Asn1Integer minimumIntervalTime;
        private Asn1Integer maxNumberOfReports;

        public RepeatedReportingParams() {
        }

        public RepeatedReportingParams(Asn1Integer minimumIntervalTime, Asn1Integer maxNumberOfReports) {
            this.minimumIntervalTime = minimumIntervalTime;
            this.maxNumberOfReports = maxNumberOfReports;
        }

        public Asn1Integer getMinimumIntervalTime() {
            return minimumIntervalTime;
        }

        public void setMinimumIntervalTime(Asn1Integer minimumIntervalTime) {
            this.minimumIntervalTime = minimumIntervalTime;
        }

        public Asn1Integer getMaxNumberOfReports() {
            return maxNumberOfReports;
        }

        public void setMaxNumberOfReports(Asn1Integer maxNumberOfReports) {
            this.maxNumberOfReports = maxNumberOfReports;
        }
    }

    /**
     * GeographicTargetArea class representing a geographic target area
     */
    public static class GeographicTargetArea {
        private CircularArea circularArea;
        private EllipticalArea ellipticalArea;

        public GeographicTargetArea() {
        }

        public boolean isCircularArea() {
            return circularArea != null;
        }

        public boolean isEllipticalArea() {
            return ellipticalArea != null;
        }

        public CircularArea getCircularArea() {
            return circularArea;
        }

        public void setCircularArea(CircularArea circularArea) {
            this.circularArea = circularArea;
        }

        public EllipticalArea getEllipticalArea() {
            return ellipticalArea;
        }

        public void setEllipticalArea(EllipticalArea ellipticalArea) {
            this.ellipticalArea = ellipticalArea;
        }
    }

    /**
     * GeographicTargetAreaList class representing a list of geographic target areas
     */
    public static class GeographicTargetAreaList {
        private GeographicTargetArea[] geographicTargetAreas;

        public GeographicTargetAreaList() {
        }

        public GeographicTargetAreaList(GeographicTargetArea[] geographicTargetAreas) {
            this.geographicTargetAreas = geographicTargetAreas;
        }

        public GeographicTargetArea[] getGeographicTargetAreas() {
            return geographicTargetAreas;
        }

        public void setGeographicTargetAreas(GeographicTargetArea[] geographicTargetAreas) {
            this.geographicTargetAreas = geographicTargetAreas;
        }
    }

    /**
     * AreaId class representing an area identifier
     */
    public static class AreaId {
        private LTEAreaId lteAreaId;

        public AreaId() {
        }

        public LTEAreaId getLTEAreaId() {
            return lteAreaId;
        }

        public void setLTEAreaId(LTEAreaId lteAreaId) {
            this.lteAreaId = lteAreaId;
        }
    }

    /**
     * AreaIdSet class representing a set of area identifiers
     */
    public static class AreaIdSet {
        private AreaId[] areaIds;

        public AreaIdSet() {
        }

        public AreaIdSet(AreaId[] areaIds) {
            this.areaIds = areaIds;
        }

        public AreaId[] getAreaIds() {
            return areaIds;
        }

        public void setAreaIds(AreaId[] areaIds) {
            this.areaIds = areaIds;
        }
    }

    /**
     * AreaIdList class representing a list of area identifier sets
     */
    public static class AreaIdList {
        private AreaIdSet areaIdSet;

        public AreaIdList() {
        }

        public AreaIdList(AreaIdSet areaIdSet) {
            this.areaIdSet = areaIdSet;
        }

        public AreaIdSet getAreaIdSet() {
            return areaIdSet;
        }

        public void setAreaIdSet(AreaIdSet areaIdSet) {
            this.areaIdSet = areaIdSet;
        }
    }

    /**
     * AreaEventParams_areaIdLists class representing lists of area IDs for area event params
     */
    public static class AreaEventParams_areaIdLists {
        private AreaIdList[] areaIdLists;

        public AreaEventParams_areaIdLists() {
        }

        public AreaEventParams_areaIdLists(AreaIdList[] areaIdLists) {
            this.areaIdLists = areaIdLists;
        }

        public AreaIdList[] getAreaIdLists() {
            return areaIdLists;
        }

        public void setAreaIdLists(AreaIdList[] areaIdLists) {
            this.areaIdLists = areaIdLists;
        }
    }

    /**
     * LTEAreaId class representing an LTE area identifier
     */
    public static class LTEAreaId {
        private Long mcc;
        private Long mnc;
        private Asn1BitString eci;

        public LTEAreaId() {
        }

        public LTEAreaId(Long mcc, Long mnc, Asn1BitString eci) {
            this.mcc = mcc;
            this.mnc = mnc;
            this.eci = eci;
        }

        public Long getMcc() {
            return mcc;
        }

        public void setMcc(Long mcc) {
            this.mcc = mcc;
        }

        public Long getMnc() {
            return mnc;
        }

        public void setMnc(Long mnc) {
            this.mnc = mnc;
        }

        public Asn1BitString getEci() {
            return eci;
        }

        public void setEci(Asn1BitString eci) {
            this.eci = eci;
        }
    }

    /**
     * PeriodicParams class representing periodic reporting parameters
     */
    public static class PeriodicParams {
        private Asn1Integer reportingInterval;
        private Asn1Integer numberOfReports;

        public PeriodicParams() {
        }

        public PeriodicParams(Asn1Integer reportingInterval, Asn1Integer numberOfReports) {
            this.reportingInterval = reportingInterval;
            this.numberOfReports = numberOfReports;
        }

        public Asn1Integer getReportingInterval() {
            return reportingInterval;
        }

        public void setReportingInterval(Asn1Integer reportingInterval) {
            this.reportingInterval = reportingInterval;
        }

        public Asn1Integer getNumberOfReports() {
            return numberOfReports;
        }

        public void setNumberOfReports(Asn1Integer numberOfReports) {
            this.numberOfReports = numberOfReports;
        }
    }

    /**
     * AreaEventParams class representing area event parameters
     */
    public static class AreaEventParams {
        private AreaEventType areaEventType;
        private Asn1Boolean locationEstimateRequired;
        private RepeatedReportingParams repeatedReportingParams;
        private Asn1Integer startTime;
        private Asn1Integer stopTime;
        private GeographicTargetAreaList geographicTargetAreaList;
        private AreaEventParams_areaIdLists areaIdLists;

        public AreaEventParams() {
        }

        public AreaEventParams(AreaEventType areaEventType, Asn1Boolean locationEstimateRequired,
                               RepeatedReportingParams repeatedReportingParams, Asn1Integer startTime,
                               Asn1Integer stopTime, GeographicTargetAreaList geographicTargetAreaList,
                               AreaEventParams_areaIdLists areaIdLists) {
            this.areaEventType = areaEventType;
            this.locationEstimateRequired = locationEstimateRequired;
            this.repeatedReportingParams = repeatedReportingParams;
            this.startTime = startTime;
            this.stopTime = stopTime;
            this.geographicTargetAreaList = geographicTargetAreaList;
            this.areaIdLists = areaIdLists;
        }

        public AreaEventType getAreaEventType() {
            return areaEventType;
        }

        public void setAreaEventType(AreaEventType areaEventType) {
            this.areaEventType = areaEventType;
        }

        public Asn1Boolean getLocationEstimateRequired() {
            return locationEstimateRequired;
        }

        public void setLocationEstimateRequired(Asn1Boolean locationEstimateRequired) {
            this.locationEstimateRequired = locationEstimateRequired;
        }

        public RepeatedReportingParams getRepeatedReportingParams() {
            return repeatedReportingParams;
        }

        public void setRepeatedReportingParams(RepeatedReportingParams repeatedReportingParams) {
            this.repeatedReportingParams = repeatedReportingParams;
        }

        public Asn1Integer getStartTime() {
            return startTime;
        }

        public void setStartTime(Asn1Integer startTime) {
            this.startTime = startTime;
        }

        public Asn1Integer getStopTime() {
            return stopTime;
        }

        public void setStopTime(Asn1Integer stopTime) {
            this.stopTime = stopTime;
        }

        public GeographicTargetAreaList getGeographicTargetAreaList() {
            return geographicTargetAreaList;
        }

        public void setGeographicTargetAreaList(GeographicTargetAreaList geographicTargetAreaList) {
            this.geographicTargetAreaList = geographicTargetAreaList;
        }

        public AreaEventParams_areaIdLists getAreaIdLists() {
            return areaIdLists;
        }

        public void setAreaIdLists(AreaEventParams_areaIdLists areaIdLists) {
            this.areaIdLists = areaIdLists;
        }
    }

    /**
     * TriggerParams class for triggered location requests
     */
    public static class TriggerParams {
        private AreaEventParams areaEventParams;
        private PeriodicParams periodicParams;

        public TriggerParams() {
        }

        public AreaEventParams getAreaEventParams() {
            return areaEventParams;
        }

        public void setAreaEventParams(AreaEventParams areaEventParams) {
            this.areaEventParams = areaEventParams;
        }

        public PeriodicParams getPeriodicParams() {
            return periodicParams;
        }

        public void setPeriodicParams(PeriodicParams periodicParams) {
            this.periodicParams = periodicParams;
        }
    }
}
