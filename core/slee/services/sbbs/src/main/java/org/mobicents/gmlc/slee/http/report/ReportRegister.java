package org.mobicents.gmlc.slee.http.report;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:aferreiraguido@gmail.com"> Alejandro Ferreira Guido </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ReportRegister {

    ConcurrentHashMap<Integer, ReportParameters> reportRegisteredElements;

    public ReportRegister(){
        reportRegisteredElements = new ConcurrentHashMap<>();
    }

    public Integer add(ReportParameters reportParameters) {
        Integer referenceNumber = 0;
        synchronized (this) {
            ++referenceNumber;
        }
        reportRegisteredElements.put(referenceNumber, reportParameters);
        return referenceNumber;
    }

    public ReportParameters get(Integer referenceNumber){
        if (reportRegisteredElements.containsKey(referenceNumber)) {
            return reportRegisteredElements.get(referenceNumber);
        }
        return null;
    }

    public void remove(Integer referenceNumber){
        reportRegisteredElements.remove(referenceNumber);
    }
}
