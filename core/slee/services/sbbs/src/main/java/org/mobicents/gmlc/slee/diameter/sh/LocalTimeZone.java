package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "LocalTimeZone")
public class LocalTimeZone implements Serializable {

    private static final long serialVersionUID = 1L;
    private String timeZone;
    private Integer daylightSavingTime;

    public LocalTimeZone() {
        super();
    }


    public String getTimeZone() {
        return timeZone;
    }

    @XmlElement(name = "TimeZone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getDaylightSavingTime() {
        return daylightSavingTime;
    }

    @XmlElement(name = "DaylightSavingTime")
    public void setDaylightSavingTime(Integer daylightSavingTime) {
        this.daylightSavingTime = daylightSavingTime;
    }

    @Override
    public String toString() {
        return "LocalTimeZone{" +
            "timeZone='" + timeZone + '\'' +
            ", daylightSavingTime=" + daylightSavingTime +
            '}';
    }
}
