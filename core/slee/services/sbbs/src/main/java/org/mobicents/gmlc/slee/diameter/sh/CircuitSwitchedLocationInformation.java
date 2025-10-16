package org.mobicents.gmlc.slee.diameter.sh;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Sh-Data")
public class CircuitSwitchedLocationInformation implements Serializable {

  private static final long serialVersionUID = 1L;
  private CSLocationInformation csLocationInformation;

  public CircuitSwitchedLocationInformation(CSLocationInformation csLocationInformation) {
    super();
    this.csLocationInformation = csLocationInformation;
  }

  public CircuitSwitchedLocationInformation() {
    super();
  }

  public CSLocationInformation getCsLocationInformation() {
    return csLocationInformation;
  }
  @XmlElement (name = "CSLocationInformation")
  public void setCsLocationInformation(CSLocationInformation csLocationInformation) {
    this.csLocationInformation = csLocationInformation;
  }

  @Override
  public String toString() {
    return "ShData [csLocationInformation=" + csLocationInformation + "]";
  }

}

