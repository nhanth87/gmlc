package org.mobicents.gmlc.slee;

// import com.paicbd.slee.resource.ulp.ULPMessageFactory;
// import com.paicbd.slee.resource.ulp.UlpActivityContextInterfaceFactory;
// import com.paicbd.slee.resource.ulp.UlpResourceAdaptorSbbInterface;
import net.java.slee.resource.diameter.base.DiameterActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.base.DiameterAvpFactory;
import net.java.slee.resource.diameter.base.DiameterMessageFactory;
import net.java.slee.resource.diameter.base.DiameterProvider;
import net.java.slee.resource.diameter.sh.DiameterShAvpFactory;
import net.java.slee.resource.diameter.sh.client.ShClientActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.sh.client.ShClientMessageFactory;
import net.java.slee.resource.diameter.sh.client.ShClientProvider;
import net.java.slee.resource.diameter.sh.server.ShServerActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.sh.server.ShServerMessageFactory;
import net.java.slee.resource.diameter.sh.server.ShServerProvider;
import net.java.slee.resource.diameter.slg.SLgAVPFactory;
import net.java.slee.resource.diameter.slg.SLgActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.slg.SLgMessageFactory;
import net.java.slee.resource.diameter.slg.SLgProvider;
import net.java.slee.resource.diameter.slh.SLhAVPFactory;
import net.java.slee.resource.diameter.slh.SLhActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.slh.SLhMessageFactory;
import net.java.slee.resource.diameter.slh.SLhProvider;
import net.java.slee.resource.http.HttpServletRaActivityContextInterfaceFactory;
import net.java.slee.resource.http.HttpServletRaSbbInterface;
import org.mobicents.slee.SbbContextExt;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.slee.resource.map.MAPContextInterfaceFactory;

import javax.slee.ActivityContextInterface;
import javax.slee.CreateException;
import javax.slee.RolledBackContext;
import javax.slee.Sbb;
import javax.slee.SbbContext;
import javax.slee.facilities.Tracer;
import javax.slee.resource.ResourceAdaptorTypeID;

/**
 *
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 * @author <a href="mailto:abhayani@gmail.com"> Amit Bhayani </a>
 * @author <a href="mailto:serg.vetyutnev@gmail.com"> Sergey Vetyutnev </a>
 * @author <a href="mailto:bbaranow@redhat.com"> Bartosz Baranowski </a>
 */
public class GMLCBaseSbb implements Sbb {

  private final String loggerName;

  // -------------------------------------------------------------
  // SLEE stuff
  // -------------------------------------------------------------
  protected SbbContextExt sbbContext;

  protected Tracer logger;

  // -------------------------------------------------------------
  // MAP RA stuff
  // -------------------------------------------------------------
  protected static final ResourceAdaptorTypeID mapRATypeID = new ResourceAdaptorTypeID("MAPResourceAdaptorType","org.restcomm", "2.0");
  protected static final String mapRaLink = "MAPRA";
  protected MAPContextInterfaceFactory mapAcif;
  protected MAPProvider mapProvider;
  protected MAPParameterFactory mapParameterFactory;

  // -------------------------------------------------------------
  // Diameter SLh RA stuff
  // -------------------------------------------------------------
  protected static final ResourceAdaptorTypeID diameterSLhRATypeID = new ResourceAdaptorTypeID("Diameter SLh", "java.net", "0.8.1");
  protected static final String slhRaLink = "DiameterSLh";
  protected SLhActivityContextInterfaceFactory slhActivityContextInterfaceFactory;
  protected SLhProvider slhProvider;
  protected SLhMessageFactory slhMessageFactory;
  protected SLhAVPFactory slhAVPFactory;

  // -------------------------------------------------------------
  // Diameter SLg RA stuff
  // -------------------------------------------------------------
  protected static final ResourceAdaptorTypeID diameterSLgRATypeID = new ResourceAdaptorTypeID("Diameter SLg", "java.net", "0.8.1");
  protected static final String slgRaLink = "DiameterSLg";
  protected SLgActivityContextInterfaceFactory slgActivityContextInterfaceFactory;
  protected SLgProvider slgProvider;
  protected SLgMessageFactory slgMessageFactory;
  protected SLgAVPFactory slgAVPFactory;

  // -------------------------------------------------------------
  // Diameter Sh RA stuff
  // -------------------------------------------------------------
  protected static final ResourceAdaptorTypeID diameterShClientRATypeID = new ResourceAdaptorTypeID("Diameter Sh-Client", "java.net", "0.8.1");
  protected static final String shClientRaLink = "DiameterShClient";
  protected ShClientActivityContextInterfaceFactory shClientActivityContextInterfaceFactory;
  protected ShClientProvider shClientProvider;
  protected ShClientMessageFactory shClientMessageFactory;

  protected static final ResourceAdaptorTypeID diameterShServerRATypeID = new ResourceAdaptorTypeID("Diameter Sh-Server", "java.net", "0.8.1");
  protected static final String shServerRaLink = "DiameterShServer";
  protected ShServerActivityContextInterfaceFactory shServerActivityContextInterfaceFactory;
  protected ShServerProvider shServerProvider;
  protected ShServerMessageFactory shServerMessageFactory;

  protected DiameterShAvpFactory shAvpFactory;

  // -------------------------------------------------------------
  // Diameter Base RA stuff
  // -------------------------------------------------------------
  protected static final ResourceAdaptorTypeID diameterBaseRATypeID = new ResourceAdaptorTypeID("Diameter Base", "java.net", "0.8.1");
  protected static final String diameterBaseRaLink = "DiameterBaseResourceAdaptor";
  protected DiameterActivityContextInterfaceFactory diameterActivityContextInterfaceFactory;
  protected DiameterProvider diameterBaseProvider;
  protected DiameterMessageFactory diameterBaseMessageFactory;
  protected DiameterAvpFactory diameterBaseAvpFactory;

  // -------------------------------------------------------------
  // HTTP Servlet RA stuff
  // -------------------------------------------------------------
  protected static ResourceAdaptorTypeID httpServletRATypeID;
  protected static final String httpServletRaLink = "HttpServletRA";
  protected HttpServletRaSbbInterface httpServletProvider;
  protected HttpServletRaActivityContextInterfaceFactory httpServletRaActivityContextInterfaceFactory;

  // -------------------------------------------------------------
  // SUPL ULP RA stuff
  // -------------------------------------------------------------
  protected static final ResourceAdaptorTypeID suplRATypeID = new ResourceAdaptorTypeID("ULPResourceAdaptorType", "com.paicbd", "1.0");
  protected static final String suplRaLink = "ULPRA";
  /*
  protected UlpResourceAdaptorSbbInterface ulpResourceAdaptorSbbInterface; // ulp provider
  protected UlpActivityContextInterfaceFactory ulpActivityContextInterfaceFactory;
  protected ULPMessageFactory ulpMessageFactory;
  */


  public GMLCBaseSbb(String loggerName) {
    super();
    this.loggerName = loggerName;
  }

  // -------------------------------------------------------------
  // SLEE minimal stuff
  // -------------------------------------------------------------
  public void setSbbContext(SbbContext sbbContext) {
    this.sbbContext = (SbbContextExt) sbbContext;
    this.logger = sbbContext.getTracer(this.loggerName);
  }

  public void unsetSbbContext() {
    // clean RAs
    this.mapAcif = null;
    this.mapProvider = null;
    this.mapParameterFactory = null;

    this.slhActivityContextInterfaceFactory = null;
    this.slhProvider = null;
    this.slhMessageFactory = null;
    this.slhAVPFactory = null;

    this.slgActivityContextInterfaceFactory = null;
    this.slgProvider = null;
    this.slgMessageFactory = null;
    this.slgAVPFactory = null;

    this.shClientActivityContextInterfaceFactory = null;
    this.shClientProvider = null;
    this.shClientMessageFactory = null;
    this.shServerActivityContextInterfaceFactory = null;
    this.shServerProvider = null;
    this.shServerMessageFactory = null;
    this.shAvpFactory = null;

    this.diameterActivityContextInterfaceFactory = null;
    this.diameterBaseProvider = null;
    this.diameterBaseMessageFactory = null;
    this.diameterBaseAvpFactory = null;

    this.httpServletProvider = null;
    this.httpServletRaActivityContextInterfaceFactory = null;

    // this.ulpResourceAdaptorSbbInterface = null;
    // this.ulpActivityContextInterfaceFactory= null;
    // this.ulpMessageFactory = null;

    //this.gmlcStatAggregator = null;

    //this.jdbcRA = null;
    //this.jdbcACIF = null;

    // clean SLEE
    this.sbbContext = null;
    this.logger = null;
  }

  public void sbbCreate() throws CreateException {
  }

  public void sbbPostCreate() throws CreateException {
  }

  public void sbbActivate() {
  }

  public void sbbPassivate() {
  }

  public void sbbLoad() {
  }

  public void sbbStore() {
  }

  public void sbbRemove() {
  }

  public void sbbExceptionThrown(Exception exception, Object object, ActivityContextInterface activityContextInterface) {
  }

  public void sbbRolledBack(RolledBackContext rolledBackContext) {
  }

  /*protected boolean checkMaxActivityCount(int maxActivityCount) {
    if (maxActivityCount <= 0)
      return true;
    return mapProvider.getCurrentDialogsCount() < maxActivityCount;
  }*/

  // -------------------------------------------------------------
  // JDBC RA stuff
  // -------------------------------------------------------------
  //*** Will be implemented on a later stage ***//
  //protected static final ResourceAdaptorTypeID JDBC_RESOURCE_ADAPTOR_ID = JdbcResourceAdaptorSbbInterface.RATYPE_ID;
  //protected static final String JDBC_RA_LINK = "JDBCRA";
  //protected JdbcResourceAdaptorSbbInterface jdbcRA;
  //protected JdbcActivityContextInterfaceFactory jdbcACIF;

  // -------------------------------------------------------------
  // Statistics stuff
  // -------------------------------------------------------------
  //*** Will be implemented on a later stage ***//
  //protected GmlcStatAggregator gmlcStatAggregator;

}
