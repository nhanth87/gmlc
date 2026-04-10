package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Extension")
public class PSLocationInformationExtension implements Serializable {

    private static final long serialVersionUID = 1L;
    // private UserCSGInformation userCSGInformation;
    private PSLocationInformationExtension2 psLocationInformationExtension2;

    public PSLocationInformationExtension() {
        super();
    }

    /*public UserCSGInformation getUserCSGInformation() {
        return userCSGInformation;
    }
    @XmlElement(name = "UserCSGInformation")
    public void setUserCSGInformation(UserCSGInformation userCSGInformation) {
        this.userCSGInformation = userCSGInformation;
    }*/

    public PSLocationInformationExtension2 getPsLocationInformationExtension2() {
        return psLocationInformationExtension2;
    }
    @XmlElement (name = "Extension")
    public void setPsLocationInformationExtension2(PSLocationInformationExtension2 psLocationInformationExtension2) {
        this.psLocationInformationExtension2 = psLocationInformationExtension2;
    }

    @Override
    public String toString() {
        return "PSLocationInformationExtension{" +
            // "userCSGInformation=" + userCSGInformation +
            "psLocationInformationExtension2=" + psLocationInformationExtension2 +
            '}';
    }
}
