package org.mobicents.gmlc;

/**
 * @author <a href="mailto:abhayani@gmail.com"> Amit Bhayani </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface GmlcPropertiesManagementMBean {

  String getGmlcGt();

  void setGmlcGt(String serviceCenterGt);

  int getGmlcSsn();

  void setGmlcSsn(int serviceCenterSsn);

  int getHlrSsn();

  void setHlrSsn(int hlrSsn);

  int getMscSsn();

  void setMscSsn(int mscSsn);

  int getVlrSsn();

  void setVlrSsn(int vlrSsn);

  int getSgsnSsn();

  void setSgsnSsn(int sgsnSsn);

  int getMaxMapVersion();

  void setMaxMapVersion(int maxMapVersion);

  long getDialogTimeout();

  void setDialogTimeout(long dialogTimeout);

  int getMaxActivityCount();

  void setMaxActivityCount(int maxActivityCount);

  int getEventContextSuspendDeliveryTimeout();

  void setEventContextSuspendDeliveryTimeout(int eventContextSuspendDeliveryTimeout);

  String getServerOverloadedMessage();

  void setServerOverloadedMessage(String serverOverloadedMessage);

  String getServerErrorMessage();

  void setServerErrorMessage(String serverErrorMessage);

  String getDialogTimeoutErrorMessage();

  void setDialogTimeoutErrorMessage(String dialogTimeoutErrorMessage);

  String getDiameterOriginRealm();

  void setDiameterOriginRealm(String diameterOriginRealm);

  String getDiameterOriginHost();

  void setDiameterOriginHost(String diameterOriginHost);

  String getDiameterDestRealm();

  void setDiameterDestRealm(String diameterDestRealm);

  String getDiameterDestHost();

  void setDiameterDestHost(String diameterDestUri);

  String getDiameterGmlcNumber();

  void setDiameterGmlcNumber(String diameterUserName);

  String getDiameterGmlcAddress();

  void setDiameterGmlcAddress(String diameterGmlcAddress);

  String getDiameterGmlcAddressType();

  void setDiameterGmlcAddressType(String diameterGmlcAddressType);

  Boolean getUseRiaGmlcAddress();

  void setUseRiaGmlcAddress(Boolean useRiaGmlcAddress);

  String getLcsNonTriggeredReportOption();

  void setLcsNonTriggeredReportOption(String lcsNonTriggeredReportOption);

  String getLcsUrlCallback();

  void setLcsUrlCallback(String lcsUrlCallback);

  String getMongoHost();

  void setMongoHost(String mongoHost);

  Integer getMongoPort();

  void setMongoPort(Integer mongoPort);

  String getMongoDatabase();

  void setMongoDatabase(String mongoDatabase);

  String getCurlUser();

  void setCurlUser(String curlUser);

  String getCurlToken();

  void setCurlToken(String curlToken);

  Boolean getSuplSslEnabled();

  void setSuplSslEnabled(Boolean suplSslEnabled);

  Integer getSuplSslPort();

  void setSuplSslPort(Integer suplSslPort);

  Integer getSuplNoSslPort();

  void setSuplNoSslPort(Integer suplNoSslPort);

  String getSuplTlsCertPath();

  void setSuplTlsCertPath(String tlsCertPath);

  String getSuplTlsCertPwd();

  void setSuplTlsCertPwd(String suplTlsCertPassword);

  Integer getSuplSessionExpired();

  void setSuplSessionExpired(Integer suplSessionExpired);

  Integer getSuplPeriodicSessionExpired();

  void setSuplPeriodicSessionExpired(Integer suplPeriodicSessionExpired);

  String getRedisHost();

  void setRedisHost(String redisHost);

  Integer getRedisPort();

  void setRedisPort(Integer redisPort);

  Boolean getGlaasEnabled();

  void setGlaasEnabled(Boolean glaasEnabled);

  String getGlaasCdrEndpoint();

  void setGlaasCdrEndpoint(String glaasCdrEndpoint);

  String getGlaasAppToken();

  void setGlaasAppToken(String glaasAppToken);

  Integer getGlassThreadPoll();

  void setGlassThreadPoll(Integer glassThreadPoll);
  Boolean getSuplEnabled();
  void setSuplEnabled(Boolean suplEnabled);
  String getSmppHost();
  void setSmppHost(String smppHost);
  Integer getSmppPort();
  void setSmppPort(Integer smppPort);
  String getSmppSid();
  void setSmppSid(String smppSid);
  String getSmppPwd();
  void setSmppPwd(String smppPwd);
  String getFqdn();
  void setFqdn(String fqdn);
}
