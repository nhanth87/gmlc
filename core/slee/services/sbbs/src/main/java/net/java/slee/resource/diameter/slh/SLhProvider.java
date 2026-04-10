package net.java.slee.resource.diameter.slh;

import java.io.IOException;

import net.java.slee.resource.diameter.Validator;
import net.java.slee.resource.diameter.base.CreateActivityException;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slh.events.LocationReportAnswer;
import net.java.slee.resource.diameter.slh.events.LocationReportRequest;
import net.java.slee.resource.diameter.slh.events.RoutingInfoAnswer;
import net.java.slee.resource.diameter.slh.events.RoutingInfoRequest;

/**
 * The SLhProvider is used by a Diameter SLh Client (e.g., a GMLC) in an EPS network to create and send
 * requests to a Diameter SLh Server (e.g., an HSS).
 *
 * @author GMLC Team
 */
public interface SLhProvider {

    /**
     * Get access to the SLh Diameter message factory.
     *
     * @return message factory for SLh Diameter
     */
    SLhMessageFactory getSLhMessageFactory();

    /**
     * Get access to the SLh Diameter AVP factory.
     *
     * @return AVP factory for SLh Diameter
     */
    SLhAVPFactory getSLhAVPFactory();

    /**
     * Alias for getSLhAVPFactory() for backward compatibility.
     *
     * @return AVP factory for SLh Diameter
     */
    default SLhAVPFactory getSLhAvpFactory() {
        return getSLhAVPFactory();
    }

    /**
     * Create a new SLh activity to send and receive Diameter SLh messages.
     * @throws CreateActivityException if the RA could not create the activity for any reason
     * @return an SLhActivity
     */
    SLhActivity createSLhActivity() throws CreateActivityException;

    /**
     * Sends a synchronous RoutingInfoRequest which will block until an answer is received from the peer.
     *
     * @param message created using the MessageFactory
     * @return answer received from HSS
     * @throws IOException if there was a problem sending the request
     */
    RoutingInfoAnswer routingInfoRequest(RoutingInfoRequest message) throws IOException;

    /**
     * Sends a synchronous LocationReportRequest which will block until an answer is received from the peer.
     *
     * @param message created using the MessageFactory
     * @return answer received from HSS
     * @throws IOException if there was a problem sending the request
     */
    LocationReportAnswer locationReportRequest(LocationReportRequest message) throws IOException;

    /**
     * Return the number of peers this Diameter resource adaptor is connected to.
     * 
     * @return connected peer count
     */
    int getPeerCount();

    /**
     * Returns array containing identities of connected peers
     * 
     * @return array of DiameterIdentity
     */
    DiameterIdentity[] getConnectedPeers();

    /**
     * Returns the validator used to validate AVPs
     * 
     * @return Validator instance
     */
    Validator getValidator();
}
