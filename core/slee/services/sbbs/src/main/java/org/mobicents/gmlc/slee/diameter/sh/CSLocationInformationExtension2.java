package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Extension")
public class CSLocationInformationExtension2 implements Serializable {

    private static final long serialVersionUID = 1L;
    private String eUTRANCellGlobalId;
    private String trackingAreaId;
    private CSLocationInformationExtension3 csLocationInformationExtension3;

    public CSLocationInformationExtension2() {
        super();
    }

    public String geteUTRANCellGlobalId() {
        return eUTRANCellGlobalId;
    }
    @XmlElement (name = "E-UTRANCellGlobalId")
    public void seteUTRANCellGlobalId(String eUTRANCellGlobalId) {
        this.eUTRANCellGlobalId = eUTRANCellGlobalId;
    }

    public String getTrackingAreaId() {
        return trackingAreaId;
    }
    @XmlElement (name = "TrackingAreaId")
    public void setTrackingAreaId(String trackingAreaId) {
        this.trackingAreaId = trackingAreaId;
    }

    public CSLocationInformationExtension3 getCsLocationInformationExtension3() {
        return csLocationInformationExtension3;
    }
    @XmlElement (name = "Extension")
    public void setCsLocationInformationExtension3(CSLocationInformationExtension3 csLocationInformationExtension3) {
        this.csLocationInformationExtension3 = csLocationInformationExtension3;
    }

    @Override
    public String toString() {
        return "CSLocationInformationExtension2{" +
            "eUTRANCellGlobalId='" + eUTRANCellGlobalId + '\'' +
            ", trackingAreaId='" + trackingAreaId + '\'' +
            ", csLocationInformationExtension3=" + csLocationInformationExtension3 +
            '}';
    }
}
