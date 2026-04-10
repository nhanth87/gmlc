package net.java.slee.resource.diameter.slh;

import javax.slee.ActivityContextInterface;
import javax.slee.UnrecognizedActivityException;

/**
 * Obtains an ActivityContextInterface for a given SLh activity
 *
 * @author GMLC Team
 */
public interface SLhActivityContextInterfaceFactory {

    /**
     * Returns the ActivityContextInterface for the given SLh activity
     * 
     * @param activity the SLh activity
     * @return ActivityContextInterface
     * @throws UnrecognizedActivityException if the activity is not recognized
     */
    ActivityContextInterface getActivityContextInterface(SLhActivity activity) throws UnrecognizedActivityException;
}
