// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface MotionEventInfoAvp {

    void setOccurrenceInfo(OccurrenceInfo occurrenceInfo);

    void setOccurrenceInfo(int occurrenceInfo);

    void setIntervalTime(int intervalTime);

    void setMaxIntervalTime(int maxIntervalTime);

    void setMaxIntervalTime(Long maxInterval);

    void setMinimumIntervalTime(int minIntervalTime);

    void setMinimumIntervalTime(Long minInterval);

    void setSamplingInterval(int samplingInterval);

    void setReportInterval(int reportInterval);

    void setReportDuration(int reportDuration);

    void setReportDuration(Long reportDuration);

    void setReportingLocationRequirements(int requirements);

    void setReportingLocationRequirements(Long requirements);

    OccurrenceInfo getOccurrenceInfo();

    void setMinIntervalTime(int minInterval);

    void setMinIntervalTime(Long minInterval);
}
