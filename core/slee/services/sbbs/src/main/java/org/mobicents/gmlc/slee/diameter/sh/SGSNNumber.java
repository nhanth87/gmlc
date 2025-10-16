package org.mobicents.gmlc.slee.diameter.sh;

import org.apache.commons.codec.binary.Base64;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "SGSNNumber")
public class SGSNNumber implements Serializable {

  private static final long serialVersionUID = 1L;
  private String address;

  public SGSNNumber() {
  }

  public String getAddress() {
    return address;
  }
  @XmlElement (name = "Address")
  public void setAddress(String address) {
    if (address != null) {
      this.address = new String(getSGSNNumberBytes(address));
    }
  }

  public byte[] getSGSNNumberBytes(String address) {
    return Base64.decodeBase64(address.getBytes());
  }

  @Override
  public String toString() {
    return "SGSN Number [address = " + address + "]";
  }
}

