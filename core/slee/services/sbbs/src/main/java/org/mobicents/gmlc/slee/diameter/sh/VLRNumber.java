package org.mobicents.gmlc.slee.diameter.sh;

import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "VLRNumber")
public class VLRNumber implements Serializable {

  private static final long serialVersionUID = 1L;
  private String address;

  public VLRNumber() {
    super();
  }

  public String getAddress() {
    return address;
  }

  @XmlElement (name = "Address")
  public void setAddress(String address) {
    if (address != null) {
      this.address = new String(getVLRNumberBytes(address));
    }
  }

  public byte[] getVLRNumberBytes(String address) {
    return Base64.decodeBase64(address.getBytes());
  }

  @Override
  public String toString() {
    return "VLRNumber [address=" + address + "]";
  }

}

