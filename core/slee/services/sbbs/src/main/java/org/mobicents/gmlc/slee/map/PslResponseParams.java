package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranAdditionalPositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranCivicAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class PslResponseParams implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * ProvideSubscriberLocation-Res ::= SEQUENCE {
   *   locationEstimate                  Ext-GeographicalInformation,
   *   ageOfLocationEstimate             [0] AgeOfLocationInformation          OPTIONAL,
   *   extensionContainer                [1] ExtensionContainer                OPTIONAL,
   *   ...,
   *   add-LocationEstimate              [2] Add-GeographicalInformation       OPTIONAL,
   *   deferredmt-lrResponseIndicator    [3] NULL                              OPTIONAL,
   *   geranPositioningData              [4] PositioningDataInformation        OPTIONAL,
   *   utranPositioningData              [5] UtranPositioningDataInfo          OPTIONAL,
   *   cellIdOrSai                       [6] CellGlobalIdOrServiceAreaIdOrLAI  OPTIONAL,
   *   sai-Present                       [7] NULL                              OPTIONAL,
   *   accuracyFulfilmentIndicator       [8] AccuracyFulfilmentIndicator       OPTIONAL,
   *   velocityEstimate                  [9] VelocityEstimate                  OPTIONAL,
   *   mo-lrShortCircuitIndicator       [10] NULL                              OPTIONAL,
   *   geranGANSSpositioningData        [11] GeranGANSSpositioningData         OPTIONAL,
   *   utranGANSSpositioningData        [12] UtranGANSSpositioningData         OPTIONAL,
   *   targetServingNodeForHandover     [13] ServingNodeAddress                OPTIONAL,
   *   utranAdditionalPositioningData   [14] UtranAdditionalPositioningData    OPTIONAL,
   *   utranBaroPressureMeas            [15] UtranBaroPressureMeas             OPTIONAL,
   *   utranCivicAddress                [16] UtranCivicAddress                 OPTIONAL
   *  }
   */
  private ExtGeographicalInformation locationEstimate;
  private Integer ageOfLocationEstimate;
  private AddGeographicalInformation additionalLocationEstimate;
  private boolean deferredMTLRResponseIndicator;
  private PositioningDataInformation geranPositioningDataInformation;
  private UtranPositioningDataInfo utranPositioningDataInfo;
  private CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI;
  private boolean saiPresent;
  private AccuracyFulfilmentIndicator accuracyFulfilmentIndicator;
  private VelocityEstimate velocityEstimate;
  private boolean moLrShortCircuitIndicator;
  private GeranGANSSpositioningData geranGANSSpositioningData;
  private UtranGANSSpositioningData utranGANSSpositioningData;
  private ServingNodeAddress targetServingNodeForHandover;
  private UtranAdditionalPositioningData utranAdditionalPositioningData;
  private Integer utranBaroPressureMeas;
  private UtranCivicAddress utranCivicAddress;

  public PslResponseParams() {
    super();
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public ExtGeographicalInformation getLocationEstimate() {
    return locationEstimate;
  }

  public void setLocationEstimate(ExtGeographicalInformation locationEstimate) {
    this.locationEstimate = locationEstimate;
  }

  public Integer getAgeOfLocationEstimate() {
    return ageOfLocationEstimate;
  }

  public void setAgeOfLocationEstimate(Integer ageOfLocationEstimate) {
    this.ageOfLocationEstimate = ageOfLocationEstimate;
  }

  public AddGeographicalInformation getAdditionalLocationEstimate() {
    return additionalLocationEstimate;
  }

  public void setAdditionalLocationEstimate(AddGeographicalInformation additionalLocationEstimate) {
    this.additionalLocationEstimate = additionalLocationEstimate;
  }

  public boolean isDeferredMTLRResponseIndicator() {
    return deferredMTLRResponseIndicator;
  }

  public void setDeferredMTLRResponseIndicator(boolean deferredMTLRResponseIndicator) {
    this.deferredMTLRResponseIndicator = deferredMTLRResponseIndicator;
  }

  public PositioningDataInformation getGeranPositioningDataInformation() {
    return geranPositioningDataInformation;
  }

  public void setGeranPositioningDataInformation(PositioningDataInformation geranPositioningDataInformation) {
    this.geranPositioningDataInformation = geranPositioningDataInformation;
  }

  public UtranPositioningDataInfo getUtranPositioningDataInfo() {
    return utranPositioningDataInfo;
  }

  public void setUtranPositioningDataInfo(UtranPositioningDataInfo utranPositioningDataInfo) {
    this.utranPositioningDataInfo = utranPositioningDataInfo;
  }

  public CellGlobalIdOrServiceAreaIdOrLAI getCellGlobalIdOrServiceAreaIdOrLAI() {
    return cellGlobalIdOrServiceAreaIdOrLAI;
  }

  public void setCellGlobalIdOrServiceAreaIdOrLAI(CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI) {
    this.cellGlobalIdOrServiceAreaIdOrLAI = cellGlobalIdOrServiceAreaIdOrLAI;
  }

  public boolean isSaiPresent() {
    return saiPresent;
  }

  public void setSaiPresent(boolean saiPresent) {
    this.saiPresent = saiPresent;
  }

  public AccuracyFulfilmentIndicator getAccuracyFulfilmentIndicator() {
    return accuracyFulfilmentIndicator;
  }

  public void setAccuracyFulfilmentIndicator(AccuracyFulfilmentIndicator accuracyFulfilmentIndicator) {
    this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
  }

  public VelocityEstimate getVelocityEstimate() {
    return velocityEstimate;
  }

  public void setVelocityEstimate(VelocityEstimate velocityEstimate) {
    this.velocityEstimate = velocityEstimate;
  }

  public boolean isMoLrShortCircuitIndicator() {
    return moLrShortCircuitIndicator;
  }

  public void setMoLrShortCircuitIndicator(boolean moLrShortCircuitIndicator) {
    this.moLrShortCircuitIndicator = moLrShortCircuitIndicator;
  }

  public GeranGANSSpositioningData getGeranGANSSpositioningData() {
    return geranGANSSpositioningData;
  }

  public void setGeranGANSSpositioningData(GeranGANSSpositioningData geranGANSSpositioningData) {
    this.geranGANSSpositioningData = geranGANSSpositioningData;
  }

  public UtranGANSSpositioningData getUtranGANSSpositioningData() {
    return utranGANSSpositioningData;
  }

  public void setUtranGANSSpositioningData(UtranGANSSpositioningData utranGANSSpositioningData) {
    this.utranGANSSpositioningData = utranGANSSpositioningData;
  }

  public ServingNodeAddress getTargetServingNodeForHandover() {
    return targetServingNodeForHandover;
  }

  public void setTargetServingNodeForHandover(ServingNodeAddress targetServingNodeForHandover) {
    this.targetServingNodeForHandover = targetServingNodeForHandover;
  }

  /* public UtranAdditionalPositioningData getUtranAdditionalPositioningData() {
    return utranAdditionalPositioningData;
  }

  // public void setUtranAdditionalPositioningData(UtranAdditionalPositioningData REMOVED) {
    this.utranAdditionalPositioningData = utranAdditionalPositioningData;
  } */

  public Integer getUtranBaroPressureMeas() {
    return utranBaroPressureMeas;
  }

  public void setUtranBaroPressureMeas(Integer utranBaroPressureMeas) {
    this.utranBaroPressureMeas = utranBaroPressureMeas;
  }

  public UtranAdditionalPositioningData getUtranAdditionalPositioningData() {
    return utranAdditionalPositioningData;
  }

  public void setUtranAdditionalPositioningData(UtranAdditionalPositioningData utranAdditionalPositioningData) {
    this.utranAdditionalPositioningData = utranAdditionalPositioningData;
  }

  public UtranCivicAddress getUtranCivicAddress() {
    return utranCivicAddress;
  }

  public void setUtranCivicAddress(UtranCivicAddress utranCivicAddress) {
    this.utranCivicAddress = utranCivicAddress;
  }

  @Override
  public String toString() {
    return "PslResponseValues{" +
            "moLrShortCircuitIndicator=" + moLrShortCircuitIndicator +
            ", locationEstimate=" + locationEstimate +
            ", ageOfLocationEstimate=" + ageOfLocationEstimate +
            ", additionalLocationEstimate=" + additionalLocationEstimate +
            ", deferredMTLRResponseIndicator=" + deferredMTLRResponseIndicator +
            ", geranPositioningDataInformation=" + geranPositioningDataInformation +
            ", utranPositioningDataInfo=" + utranPositioningDataInfo +
            ", cellGlobalIdOrServiceAreaIdOrLAI=" + cellGlobalIdOrServiceAreaIdOrLAI +
            ", saiPresent=" + saiPresent +
            // ", accuracyFulfilmentIndicator=" + accuracyFulfilmentIndicator +
            ", velocityEstimate=" + velocityEstimate +
            ", moLrShortCircuitIndicator=" + moLrShortCircuitIndicator +
            ", geranGANSSpositioningData=" + geranGANSSpositioningData +
            ", utranGANSSpositioningData=" + utranGANSSpositioningData +
            ", targetServingNodeForHandover=" + targetServingNodeForHandover +
            // ", utranAdditionalPositioningData=" + utranAdditionalPositioningData +
            ", utranBaroPressureMeas=" + utranBaroPressureMeas +
            // ", utranCivicAddress=" + utranCivicAddress +
            '}';
  }
}

