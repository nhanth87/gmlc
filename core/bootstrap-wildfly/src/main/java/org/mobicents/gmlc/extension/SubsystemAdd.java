package org.mobicents.gmlc.extension;

import org.jboss.as.controller.AbstractBoottimeAddStepHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.registry.Resource;
import org.jboss.as.controller.services.path.PathManager;
import org.jboss.as.controller.services.path.PathManagerService;
import org.jboss.as.jmx.MBeanServerService;
import org.jboss.dmr.ModelNode;
import org.jboss.logging.Logger;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;
import org.mobicents.gmlc.service.GmlcService;
import org.restcomm.ss7.service.SS7ExtensionService;
import org.restcomm.ss7.service.SS7ServiceInterface;

import javax.management.MBeanServer;

/**
 * Handler responsible for adding the subsystem resource to the model
 *
 * @author <a href="kabir.khan@jboss.com">Kabir Khan</a>
 */
class SubsystemAdd extends AbstractBoottimeAddStepHandler {

  static final SubsystemAdd INSTANCE = new SubsystemAdd();

  private final Logger logger = Logger.getLogger(SubsystemAdd.class);

  private SubsystemAdd() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void populateModel(ModelNode operation, ModelNode model) throws OperationFailedException {
    logger.info("Populating the model");
    model.setEmptyObject();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void performBoottime(OperationContext context, ModelNode operation, Resource resource) {
    ModelNode fullModel = Resource.Tools.readModel(context.readResource(PathAddress.EMPTY_ADDRESS));
    GmlcService service = GmlcService.INSTANCE;
    service.setModel(fullModel);

    ServiceName serviceName = GmlcService.getServiceName();

    logger.info(">>> Adding GMLC Service at boot time");
    context.getServiceTarget()
            .addService(serviceName)
            .addDependency(PathManagerService.SERVICE_NAME, PathManager.class, service.getPathManagerInjector())
            .addDependency(MBeanServerService.SERVICE_NAME, MBeanServer.class, service.getMbeanServer())
            .addDependency(SS7ExtensionService.getServiceName(), SS7ServiceInterface.class, service.getSS7Service())
            .setInstance(service)
            .setInitialMode(ServiceController.Mode.ACTIVE)
            .install();
    logger.info(">>> Finished adding GMLC Service at boot time");
  }
}
