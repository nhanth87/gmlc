package org.mobicents.gmlc.slee.supl.ULP_Components;

/**
 * QoP (Quality of Position) stub class for SUPL
 */
public class QoP {
    private Integer horizontalAccuracy;
    private Integer verticalAccuracy;
    private Integer maximumLocationAge;
    private Integer delay;
    private Integer responseTime;
    
    public QoP(Integer horizontalAccuracy) {
        this.horizontalAccuracy = horizontalAccuracy;
    }
    
    public QoP(Integer horizontalAccuracy, Integer verticalAccuracy, 
               Integer maximumLocationAge, Integer delay, Integer responseTime) {
        this.horizontalAccuracy = horizontalAccuracy;
        this.verticalAccuracy = verticalAccuracy;
        this.maximumLocationAge = maximumLocationAge;
        this.delay = delay;
        this.responseTime = responseTime;
    }
    
    public Integer getHorizontalAccuracy() {
        return horizontalAccuracy;
    }
    
    public Integer getVerticalAccuracy() {
        return verticalAccuracy;
    }
    
    public Integer getMaximumLocationAge() {
        return maximumLocationAge;
    }
    
    public Integer getDelay() {
        return delay;
    }
    
    public Integer getResponseTime() {
        return responseTime;
    }
}
