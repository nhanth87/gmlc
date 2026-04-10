// STUB interface - Missing from diameter-slg resource adaptor
package net.java.slee.resource.diameter.slg.events.avp;

public interface AreaEventInfoAvp {

    void setOccurrenceInfo(OccurrenceInfo occurrenceInfo);

    void setOccurrenceInfo(int occurrenceInfo);

    void setAreaDefinition(AreaDefinitionAvp areaDefinition);

    void setReportingLocationRequirements(int requirements);

    OccurrenceInfo getOccurrenceInfo();
}
