package org.mobicents.gmlc.slee.cdr;

/**
 *
 * @author <a href="mailto:bbaranow@redhat.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface CDRInterface {

  String TRACER_NAME = "LocationRecord";
  String CRD_TRACER_NAME = "cdr";

  /**
   * Initializes CDR storage.
   *
   * @param reset - if <b>true</b> it resets storage.
   */
  void init(boolean reset);

  void createRecord(RecordStatus outcome);

  void setState(GMLCCDRState state);

  GMLCCDRState getState();

}
