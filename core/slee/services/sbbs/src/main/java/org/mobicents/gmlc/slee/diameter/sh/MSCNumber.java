package org.mobicents.gmlc.slee.diameter.sh;

import org.apache.commons.codec.binary.Base64;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "MSCNumber")
public class MSCNumber implements Serializable {

  private static final long serialVersionUID = 1L;
  private String address;

  public MSCNumber() {
    super();
  }

  public String getAddress() {
    return address;
  }
  @XmlElement (name = "Address")
  public void setAddress(String address) {
    if (address != null) {
      this.address = new String(getMSCNumberBytes(address));
    }
  }

  public byte[] getMSCNumberBytes(String address) {
    return Base64.decodeBase64(address.getBytes());
  }

  @Override
  public String toString() {
    return "MSCNumber [address=" + address + "]";
  }

}

