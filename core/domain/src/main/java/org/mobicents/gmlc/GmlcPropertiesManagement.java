package org.mobicents.gmlc;

import javolution.text.TextBuilder;
import javolution.xml.XMLBinding;
import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;
import javolution.xml.stream.XMLStreamException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 * @author <a href="mailto:abhayani@gmail.com"> Amit Bhayani </a>
 * @author <a href="mailto:kennymendieta89@gmail.com"> Kenny Mendieta </a>
 *
 */
public class GmlcPropertiesManagement implements GmlcPropertiesManagementMBean {

  private static final Logger logger = Logger.getLogger(GmlcPropertiesManagement.class);

  protected static final String GMLC_GT = "gmlcgt";
  protected static final String GMLC_SSN = "gmlcssn";
  protected static final String HLR_SSN = "hlrssn";
  protected static final String MSC_SSN = "mscssn";
  protected static final String VLR_SSN = "vlrssn";
  protected static final String SGSN_SSN = "sgsnssn";
  protected static final String MAX_MAP_VERSION = "maxmapv";
  protected static final String MAX_ACTIVITY_COUNT = "maxactivitycount";
  protected static final String DIALOG_TIMEOUT = "dialogtimeout";
  protected static final String EVENT_CONTEXT_SUSPEND_DELIVERY_TIMEOUT = "eventcntxtsusdeltimeout";

  protected static final String DIAMETER_ORIGIN_REALM = "diameteroriginrealm";
  protected static final String DIAMETER_ORIGIN_HOST = "diameteroriginhost";
  protected static final String DIAMETER_DEST_REALM = "diameterdestrealm";
  protected static final String DIAMETER_DEST_HOST = "diameterdesthost";
  protected static final String DIAMETER_GMLC_NUMBER = "diametergmlcnumber";
  protected static final String DIAMETER_GMLC_ADDRESS = "diametergmlcaddress";
  protected static final String DIAMETER_GMLC_ADDRESS_TYPE = "diametergmlcaddresstype";
  protected static final String RIA_GMLC_ADDRESS = "riagmlcaddress";

  protected static final String LCS_NON_TRIGGERED_REPORT_OPTION = "lcsnontriggeredreportoption";
  protected static final String LCS_URL_CALLBACK = "lcsurlcallback";

  protected static final String MONGO_DB_HOST = "mongohost";
  protected static final String MONGO_DB_PORT = "mongoport";
  protected static final String MONGO_DB_DATABASE = "mongodatabase";

  protected static final String CURL_USER = "curluser";
  protected static final String CURL_TOKEN = "curltoken";

  protected static final String SERVER_OVERLOADED_MESSAGE = "serveroverloadedmsg";
  protected static final String SERVER_ERROR_MESSAGE = "servererrmssg";
  protected static final String DIALOG_TIMEOUT_ERROR_MESSAGE = "dialogtimeouterrmssg";

  private static final String TAB_INDENT = "\t";
  private static final String CLASS_ATTRIBUTE = "type";
  private static final XMLBinding binding = new XMLBinding();
  protected static final String CDR_LOGGING_TO = "cdrloggingto";
  private static final String PERSIST_FILE_NAME = "gmlcproperties.xml";

  private String serverOverloadedMessage = "Server is overloaded. Please try later";
  private String serverErrorMessage = "Server error, please try again after sometime";
  private String dialogTimeoutErrorMessage = "Request timeout please try again after sometime.";

  protected static final String SUPL_SSL_ENABLED = "suplsslenabled";
  protected static final String SUPL_SSL_PORT = "suplsslport";
  protected static final String SUPL_NO_SSL_PORT = "suplnosslport";
  protected static final String SUPL_TLS_CERT_PATH = "supltlscertpath";
  protected static final String SUPL_TLS_CERT_PWD = "supltlscertpwd";
  protected static final String SUPL_ENABLED = "suplenabled";
  protected static final String SMPP_HOST = "smpphost";
  protected static final String SMPP_PORT = "smppport";
  protected static final String SMPP_SID = "smppsid";
  protected static final String SMPP_PWD = "smpppwd";
  protected static final String FQDN = "fqdn";
  protected static final String SUPL_SESSION_EXPIRED = "supl_session_expired";
  protected static final String SUPL_PERIODIC_SESSION_EXPIRED = "supl_periodic_session_expired";
  protected static final String REDIS_DB_HOST = "redis_host";
  protected static final String REDIS_DB_PORT = "redis_port";
  public static final String GLAAS_ENABLED = "glaasenabled";
  public static final String GLAAS_CDR_ENDPOINT = "glaascdrendpoint";
  public static final String GLAAS_APP_TOKEN = "glaasapptoken";
  public static final String GLAAS_THREAD_POLL = "glassthreadpoll";

  private static GmlcPropertiesManagement instance;

  private final String name;

  private String persistDir = null;

  private final TextBuilder persistFile = TextBuilder.newInstance();

  /**
   * SS7 properties for GSM/UMTS location services
   **/
  private String gmlcGt = "598974500145";
  private int gmlcSsn = 145;
  private int hlrSsn = 6;
  private int mscSsn = 8;
  private int vlrSsn = 7;
  private int sgsnSsn = 149;
  private int maxMapVersion = 3;
  // max count of TCAP Dialogs that are possible at the same time
  private int maxActivityCount = 5000;

  /**
   * Dialog timeout in milliseconds.
   * Once HTTP request is sent, it expects response back in dialogTimeout milliseconds.
   */
  private long dialogTimeout = 60000;

  /**
   * From javax.slee EventContext public void suspendDelivery(int timeout)
   * Suspend further delivery of the event associated with this event context.  No further
   * SBBs will receive the event until resumeDelivery() is invoked on
   * an event context for the same event, or the specified timeout is reached, whichever
   * occurs first.
   * Timeout period, measured in milliseconds, before event delivery
   * will be implicitly resumed if <code>resumeDelivery()</code> has not been invoked.
   */
  private int eventContextSuspendDeliveryTimeout = 60000;

  /**
   * Diameter properties for LTE location services
   **/
  private String diameterOriginRealm = "restcomm.org";
  private String diameterOriginHost = "gmlc";
  private String diameterDestRealm = "restcomm.org";
  private String diameterDestHost = "simulator";
  private String diameterGmlcNumber = "598970755909";
  private String diameterGmlcAddress = "1.1.1.1";
  private String diameterGmlcAddressType = "4";
  private Boolean useRiaGmlcAddress = false;

  private String lcsNonTriggeredReportOption = "json";

  private String lcsUrlCallback = "http://localhost:8081/api/report";

  private String curlUser = "fer";
  private String curlToken = "n4ndO";

  /**
   *  Mongo Database properties
   */
  private String mongoHost = "localhost";
  private Integer mongoPort = 27017;
  private String mongoDatabase = "gmlc";

  private Boolean suplSslEnabled = true;
  private Integer suplSslPort = 7275;
  private Integer suplNoSslPort = 7276;
  private String suplTlsCertPath = "serverkeystore.jks";
  private String suplTlsCertPwd = "password";
  private Integer suplSessionExpired = 2880;
  private Integer suplPeriodicSessionExpired = 7200;

  /**
   *  Redis Database properties
   */
  private String redisHost = "localhost";
  private Integer redisPort = 6379;

  private Boolean suplEnabled = true;
  private String smppHost = "localhost";
  private Integer smppPort = 2776;
  private String smppSid = "gmlc_ulp_smpp";
  private String smppPwd = "gmlc_ulp_smpp";

  private String fqdn = "127.0.0.1";


  /**
   *  GLaaS properties
   */
  private Boolean glaasEnabled = false;
  private String glaasCdrEndpoint = "";
  private String glaasAppToken = "";
  private Integer glassThreadPoll = 5;

  private CdrLoggedType cdrLoggingTo = CdrLoggedType.Textfile;

  private GmlcPropertiesManagement(String name) {
    this.name = name;
    binding.setClassAttribute(CLASS_ATTRIBUTE);
  }

  protected static GmlcPropertiesManagement getInstance(String name) {
    if (instance == null) {
      instance = new GmlcPropertiesManagement(name);
    }
    return instance;
  }

  public static GmlcPropertiesManagement getInstance() {
    return instance;
  }

  public String getName() {
    return name;
  }

  public String getPersistDir() {
    return persistDir;
  }

  public void setPersistDir(String persistDir) {
    this.persistDir = persistDir;
  }

  @Override
  public String getGmlcGt() {
    return gmlcGt;
  }

  @Override
  public void setGmlcGt(String gmlcGt) {
    this.gmlcGt = gmlcGt;
    this.store();
  }

  @Override
  public int getGmlcSsn() {
    return gmlcSsn;
  }

  @Override
  public void setGmlcSsn(int gmlcSsn) {
    this.gmlcSsn = gmlcSsn;
    this.store();
  }

  @Override
  public int getHlrSsn() {
    return hlrSsn;
  }

  @Override
  public void setHlrSsn(int hlrSsn) {
    this.hlrSsn = hlrSsn;
    this.store();
  }

  @Override
  public int getMscSsn() {
    return mscSsn;
  }

  @Override
  public void setMscSsn(int mscSsn) {
    this.mscSsn = mscSsn;
    this.store();
  }

  @Override
  public int getVlrSsn() {
    return vlrSsn;
  }

  @Override
  public void setVlrSsn(int vlrSsn) {
    this.vlrSsn = vlrSsn;
  }

  @Override
  public int getSgsnSsn() {
    return sgsnSsn;
  }

  @Override
  public void setSgsnSsn(int sgsnSsn) {
    this.sgsnSsn = sgsnSsn;
  }

  @Override
  public int getMaxMapVersion() {
    return maxMapVersion;
  }

  @Override
  public void setMaxMapVersion(int maxMapVersion) {
    this.maxMapVersion = maxMapVersion;
    this.store();
  }

  @Override
  public long getDialogTimeout() {
    return dialogTimeout;
  }

  @Override
  public void setDialogTimeout(long dialogTimeout) {
    this.dialogTimeout = dialogTimeout;
    this.store();
  }

  @Override
  public String getDiameterOriginRealm() {
    return diameterOriginRealm;
  }

  @Override
  public void setDiameterOriginRealm(String diameterOriginRealm) {
    this.diameterOriginRealm = diameterOriginRealm;
    this.store();
  }

  @Override
  public String getDiameterOriginHost() {
    return diameterOriginHost;
  }

  @Override
  public void setDiameterOriginHost(String diameterOriginHost) {
    this.diameterOriginHost = diameterOriginHost;
    this.store();
  }

  @Override
  public String getDiameterDestRealm() {
    return diameterDestRealm;
  }

  @Override
  public void setDiameterDestRealm(String diameterDestRealm) {
    this.diameterDestRealm = diameterDestRealm;
    this.store();
  }

  @Override
  public String getDiameterDestHost() {
    return diameterDestHost;
  }

  @Override
  public void setDiameterDestHost(String diameterDestHost) {
    this.diameterDestHost = diameterDestHost;
    this.store();
  }

  @Override
  public String getDiameterGmlcNumber() {
    return diameterGmlcNumber;
  }

  @Override
  public void setDiameterGmlcNumber(String diameterGmlcNumber) {
    this.diameterGmlcNumber = diameterGmlcNumber;
    this.store();
  }

  @Override
  public String getDiameterGmlcAddress() {
    return diameterGmlcAddress;
  }

  @Override
  public void setDiameterGmlcAddress(String diameterGmlcAddress) {
    this.diameterGmlcAddress = diameterGmlcAddress;
    this.store();
  }

  @Override
  public String getDiameterGmlcAddressType() {
    return diameterGmlcAddressType;
  }

  @Override
  public void setDiameterGmlcAddressType(String diameterGmlcAddressType) {
    this.diameterGmlcAddressType = diameterGmlcAddressType;
    this.store();
  }

  @Override
  public Boolean getUseRiaGmlcAddress() {
    return useRiaGmlcAddress;
  }

  @Override
  public void setUseRiaGmlcAddress(Boolean useRiaGmlcAddress) {
    this.useRiaGmlcAddress = useRiaGmlcAddress;
    this.store();
  }

  @Override
  public int getMaxActivityCount() {
    return maxActivityCount;
  }

  @Override
  public void setMaxActivityCount(int maxActivityCount) {
    this.maxActivityCount = maxActivityCount;
    this.store();
  }

  @Override
  public int getEventContextSuspendDeliveryTimeout() {
    return eventContextSuspendDeliveryTimeout;
  }

  @Override
  public void setEventContextSuspendDeliveryTimeout(int eventContextSuspendDeliveryTimeout) {
    this.eventContextSuspendDeliveryTimeout = eventContextSuspendDeliveryTimeout;
    this.store();
  }

  @Override
  public String getServerOverloadedMessage() {
    return this.serverOverloadedMessage;
  }

  @Override
  public void setServerOverloadedMessage(String serverOverloadedMessage) {
    this.serverOverloadedMessage = serverOverloadedMessage;
    this.store();
  }

  @Override
  public String getServerErrorMessage() {
    return this.serverErrorMessage;
  }

  @Override
  public void setServerErrorMessage(String serverErrorMessage) {
    this.serverErrorMessage = serverErrorMessage;
    this.store();
  }

  @Override
  public String getDialogTimeoutErrorMessage() {
    return this.dialogTimeoutErrorMessage;
  }

  @Override
  public void setDialogTimeoutErrorMessage(String dialogTimeoutErrorMessage) {
    this.dialogTimeoutErrorMessage = dialogTimeoutErrorMessage;
    this.store();
  }

  @Override
  public String getLcsNonTriggeredReportOption() {
    return lcsNonTriggeredReportOption;
  }

  @Override
  public void setLcsNonTriggeredReportOption(String lcsNonTriggeredReportOption) {
    this.lcsNonTriggeredReportOption = lcsNonTriggeredReportOption;
  }

  @Override
  public String getLcsUrlCallback() {
    return this.lcsUrlCallback;
  }

  @Override
  public void setLcsUrlCallback(String lcsUrlCallback) {
    this.lcsUrlCallback = lcsUrlCallback;
    this.store();
  }

  @Override
  public String getMongoHost() {
    return mongoHost;
  }

  @Override
  public void setMongoHost(String mongoHost) {
    this.mongoHost = mongoHost;
    this.store();
  }

  @Override
  public Integer getMongoPort() {
    return mongoPort;
  }

  @Override
  public void setMongoPort(Integer mongoPort) {
    this.mongoPort = mongoPort;
    this.store();
  }

  @Override
  public String getMongoDatabase() {
    return mongoDatabase;
  }

  @Override
  public void setMongoDatabase(String mongoDatabase) {
    this.mongoDatabase = mongoDatabase;
    this.store();
  }

  @Override
  public String getCurlUser() {
    return curlUser;
  }

  @Override
  public void setCurlUser(String curlUser) {
    this.curlUser = curlUser;
  }

  @Override
  public String getCurlToken() {
    return curlToken;
  }

  @Override
  public void setCurlToken(String curlToken) {
    this.curlToken = curlToken;
  }

  @Override
  public Boolean getSuplSslEnabled() {
    return suplSslEnabled;
  }

  @Override
  public void setSuplSslEnabled(Boolean suplSslEnabled) {
    this.suplSslEnabled = suplSslEnabled;
  }

  @Override
  public Integer getSuplSslPort() {
    return suplSslPort;
  }

  @Override
  public void setSuplSslPort(Integer suplSslPort) {
    this.suplSslPort = suplSslPort;
  }

  @Override
  public Integer getSuplNoSslPort() {
    return suplNoSslPort;
  }

  @Override
  public void setSuplNoSslPort(Integer suplNoSslPort) {
    this.suplNoSslPort = suplNoSslPort;
  }

  @Override
  public String getSuplTlsCertPath() {
    return suplTlsCertPath;
  }

  @Override
  public void setSuplTlsCertPath(String tlsCertPath) {
    this.suplTlsCertPath = tlsCertPath;
  }

  @Override
  public String getSuplTlsCertPwd() {
    return suplTlsCertPwd;
  }

  @Override
  public void setSuplTlsCertPwd(String suplTlsCertPwd) {
    this.suplTlsCertPwd = suplTlsCertPwd;
  }

  @Override
  public Integer getSuplSessionExpired() {
    return suplSessionExpired;
  }

  @Override
  public void setSuplSessionExpired(Integer suplSessionExpired) {
    this.suplSessionExpired = suplSessionExpired;
  }

  @Override
  public Integer getSuplPeriodicSessionExpired() {
    return suplPeriodicSessionExpired;
  }

  @Override
  public void setSuplPeriodicSessionExpired(Integer suplPeriodicSessionExpired) {
    this.suplPeriodicSessionExpired = suplPeriodicSessionExpired;
  }

  @Override
  public String getRedisHost() {
    return redisHost;
  }

  @Override
  public void setRedisHost(String redisHost) {
    this.redisHost = redisHost;
  }

  @Override
  public Integer getRedisPort() {
    return redisPort;
  }

  @Override
  public void setRedisPort(Integer redisPort) {
    this.redisPort = redisPort;
  }

  @Override
  public Boolean getGlaasEnabled() {
    return glaasEnabled;
  }

  @Override
  public void setGlaasEnabled(Boolean glaasEnabled) {
    this.glaasEnabled = glaasEnabled;
  }

  @Override
  public String getGlaasCdrEndpoint() {
    return glaasCdrEndpoint;
  }

  @Override
  public void setGlaasCdrEndpoint(String glaasCdrEndpoint) {
    this.glaasCdrEndpoint = glaasCdrEndpoint;
  }

  @Override
  public String getGlaasAppToken() {
    return glaasAppToken;
  }

  @Override
  public void setGlaasAppToken(String glaasAppToken) {
    this.glaasAppToken = glaasAppToken;
  }

  @Override
  public Integer getGlassThreadPoll() {
    return glassThreadPoll;
  }

  @Override
  public void setGlassThreadPoll(Integer glassThreadPoll) {
    this.glassThreadPoll = glassThreadPoll;
  }

  @Override
  public Boolean getSuplEnabled() {
    return suplEnabled;
  }

  @Override
  public void setSuplEnabled(Boolean suplEnabled) {
    this.suplEnabled = suplEnabled;
  }

  @Override
  public String getSmppHost() {
    return smppHost;
  }

  @Override
  public void setSmppHost(String smppHost) {
    this.smppHost = smppHost;
  }

  @Override
  public Integer getSmppPort() {
    return smppPort;
  }

  @Override
  public void setSmppPort(Integer smppPort) {
    this.smppPort = smppPort;
  }

  @Override
  public String getSmppSid() {
    return smppSid;
  }

  @Override
  public void setSmppSid(String smppSid) {
    this.smppSid = smppSid;
  }

  @Override
  public String getSmppPwd() {
    return smppPwd;
  }

  @Override
  public void setSmppPwd(String smppPwd) {
    this.smppPwd = smppPwd;
  }

  @Override
  public String getFqdn() {
    return fqdn;
  }

  @Override
  public void setFqdn(String fqdn) {
    this.fqdn = fqdn;
  }

  public CdrLoggedType getCdrLoggingTo() {
    return cdrLoggingTo;
  }

  public void setCdrLoggingTo(CdrLoggedType cdrLoggingTo) {
    this.cdrLoggingTo = cdrLoggingTo;
    this.store();
  }

  public void start() throws Exception {

    this.persistFile.clear();

    if (persistDir != null) {
      this.persistFile.append(persistDir).append(File.separator).append(this.name).append("_")
          .append(PERSIST_FILE_NAME);
    } else {
      persistFile
          .append(System.getProperty(GmlcManagement.GMLC_PERSIST_DIR_KEY,
              System.getProperty(GmlcManagement.USER_DIR_KEY))).append(File.separator).append(this.name)
          .append("_").append(PERSIST_FILE_NAME);
    }

    logger.info(String.format("Loading GMLC Properties from %s", persistFile));

    try {
      this.load();
    } catch (FileNotFoundException e) {
      logger.warn(String.format("Failed to load the GMLC configuration file. \n%s", e.getMessage()));
    }

  }

  public void stop() throws Exception {
  }

  /**
   * Persist
   */
  public void store() {

    // TODO : Should we keep reference to Objects rather than recreating
    // everytime?
    try {
      XMLObjectWriter writer = XMLObjectWriter.newInstance(new FileOutputStream(persistFile.toString()));
      writer.setBinding(binding);
      // Enables cross-references.
      // writer.setReferenceResolver(new XMLReferenceResolver());
      writer.setIndentation(TAB_INDENT);

      writer.write(this.gmlcGt, GMLC_GT, String.class);
      writer.write(this.gmlcSsn, GMLC_SSN, Integer.class);
      writer.write(this.hlrSsn, HLR_SSN, Integer.class);
      writer.write(this.mscSsn, MSC_SSN, Integer.class);
      writer.write(this.vlrSsn, VLR_SSN, Integer.class);
      writer.write(this.sgsnSsn, SGSN_SSN, Integer.class);
      writer.write(this.maxMapVersion, MAX_MAP_VERSION, Integer.class);
      writer.write(this.maxActivityCount, MAX_ACTIVITY_COUNT, Integer.class);
      writer.write(this.dialogTimeout, DIALOG_TIMEOUT, Long.class);
      writer.write(this.eventContextSuspendDeliveryTimeout, EVENT_CONTEXT_SUSPEND_DELIVERY_TIMEOUT, Integer.class);

      writer.write(this.diameterOriginRealm, DIAMETER_ORIGIN_REALM, String.class);
      writer.write(this.diameterOriginHost, DIAMETER_ORIGIN_HOST, String.class);
      writer.write(this.diameterDestRealm, DIAMETER_DEST_REALM, String.class);
      writer.write(this.diameterDestHost, DIAMETER_DEST_HOST, String.class);
      writer.write(this.diameterGmlcNumber, DIAMETER_GMLC_NUMBER, String.class);
      writer.write(this.diameterGmlcAddress, DIAMETER_GMLC_ADDRESS, String.class);
      writer.write(this.diameterGmlcAddressType, DIAMETER_GMLC_ADDRESS_TYPE, String.class);
      writer.write(this.useRiaGmlcAddress, RIA_GMLC_ADDRESS, Boolean.class);

      writer.write(this.lcsNonTriggeredReportOption, LCS_NON_TRIGGERED_REPORT_OPTION, String.class);
      writer.write(this.lcsUrlCallback, LCS_URL_CALLBACK, String.class);

      writer.write(this.mongoHost, MONGO_DB_HOST, String.class);
      writer.write(this.mongoPort, MONGO_DB_PORT, Integer.class);
      writer.write(this.mongoDatabase, MONGO_DB_DATABASE, String.class);

      writer.write(this.curlUser, CURL_USER, String.class);
      writer.write(this.curlToken, CURL_TOKEN, String.class);

      writer.write(this.suplSslEnabled, SUPL_SSL_ENABLED, Boolean.class);
      writer.write(this.suplSslPort, SUPL_SSL_PORT, Integer.class);
      writer.write(this.suplNoSslPort, SUPL_NO_SSL_PORT, Integer.class);
      writer.write(this.suplTlsCertPath, SUPL_TLS_CERT_PATH, String.class);
      writer.write(this.suplTlsCertPwd, SUPL_TLS_CERT_PWD, String.class);
      writer.write(this.suplSessionExpired, SUPL_SESSION_EXPIRED, Integer.class);
      writer.write(this.suplPeriodicSessionExpired, SUPL_PERIODIC_SESSION_EXPIRED, Integer.class);
      writer.write(this.redisHost, REDIS_DB_HOST, String.class);
      writer.write(this.redisPort, REDIS_DB_PORT, Integer.class);

      writer.write(this.glaasEnabled, GLAAS_ENABLED, Boolean.class);
      writer.write(this.glaasCdrEndpoint, GLAAS_CDR_ENDPOINT, String.class);
      writer.write(this.glaasAppToken, GLAAS_APP_TOKEN, String.class);
      writer.write(this.glassThreadPoll, GLAAS_THREAD_POLL, Integer.class);

      writer.write(this.suplEnabled, SUPL_ENABLED, Boolean.class);
      writer.write(this.smppHost, SMPP_HOST, String.class);
      writer.write(this.smppPort, SMPP_PORT, Integer.class);
      writer.write(this.smppSid, SMPP_SID, String.class);
      writer.write(this.smppPwd, SMPP_PWD, String.class);
      writer.write(this.fqdn, FQDN, String.class);

      writer.write(this.cdrLoggingTo.toString(), CDR_LOGGING_TO, String.class);

      writer.close();
    } catch (Exception e) {
      logger.error("Error while persisting the Rule state in file", e);
    }
  }

  /**
   * Load and create LinkSets and Link from persisted file
   *
   * @throws FileNotFoundException when file is not found
   */
  public void load() throws FileNotFoundException {

    try {
      XMLObjectReader reader = XMLObjectReader.newInstance(new FileInputStream(persistFile.toString()));
      reader.setBinding(binding);

      gmlcGt = reader.read(GMLC_GT, String.class);
      gmlcSsn = reader.read(GMLC_SSN, Integer.class);
      hlrSsn = reader.read(HLR_SSN, Integer.class);
      mscSsn = reader.read(MSC_SSN, Integer.class);
      vlrSsn = reader.read(VLR_SSN, Integer.class);
      sgsnSsn = reader.read(SGSN_SSN, Integer.class);
      maxMapVersion = reader.read(MAX_MAP_VERSION, Integer.class);
      maxActivityCount = reader.read(MAX_ACTIVITY_COUNT, Integer.class);
      dialogTimeout = reader.read(DIALOG_TIMEOUT, Long.class);
      eventContextSuspendDeliveryTimeout = reader.read(EVENT_CONTEXT_SUSPEND_DELIVERY_TIMEOUT, Integer.class);

      diameterOriginRealm = reader.read(DIAMETER_ORIGIN_REALM, String.class);
      diameterOriginHost = reader.read(DIAMETER_ORIGIN_HOST, String.class);
      diameterDestRealm = reader.read(DIAMETER_DEST_REALM, String.class);
      diameterDestHost = reader.read(DIAMETER_DEST_HOST, String.class);
      diameterGmlcNumber = reader.read(DIAMETER_GMLC_NUMBER, String.class);
      diameterGmlcAddress = reader.read(DIAMETER_GMLC_ADDRESS, String.class);
      diameterGmlcAddressType = reader.read(DIAMETER_GMLC_ADDRESS_TYPE, String.class);
      useRiaGmlcAddress = reader.read(RIA_GMLC_ADDRESS, Boolean.class);

      lcsNonTriggeredReportOption = reader.read(LCS_NON_TRIGGERED_REPORT_OPTION, String.class);
      lcsUrlCallback = reader.read(LCS_URL_CALLBACK, String.class);

      mongoHost = reader.read(MONGO_DB_HOST, String.class);
      mongoPort = reader.read(MONGO_DB_PORT, Integer.class);
      mongoDatabase = reader.read(MONGO_DB_DATABASE, String.class);

      curlUser = reader.read(CURL_USER, String.class);
      curlToken = reader.read(CURL_TOKEN, String.class);

      suplSslEnabled = reader.read(SUPL_SSL_ENABLED, Boolean.class);
      suplSslPort = reader.read(SUPL_SSL_PORT, Integer.class);
      suplNoSslPort = reader.read(SUPL_NO_SSL_PORT, Integer.class);
      suplTlsCertPath = reader.read(SUPL_TLS_CERT_PATH, String.class);
      suplTlsCertPwd = reader.read(SUPL_TLS_CERT_PWD, String.class);
      suplSessionExpired = reader.read(SUPL_SESSION_EXPIRED, Integer.class);
      suplPeriodicSessionExpired = reader.read(SUPL_PERIODIC_SESSION_EXPIRED, Integer.class);
      redisHost = reader.read(REDIS_DB_HOST, String.class);
      redisPort = reader.read(REDIS_DB_PORT, Integer.class);

      glaasEnabled = reader.read(GLAAS_ENABLED, Boolean.class);
      glaasCdrEndpoint = reader.read(GLAAS_CDR_ENDPOINT, String.class);
      glaasAppToken = reader.read(GLAAS_APP_TOKEN, String.class);
      glassThreadPoll = reader.read(GLAAS_THREAD_POLL, Integer.class);

      suplEnabled = reader.read(SUPL_ENABLED, Boolean.class);
      logger.info("GMLC suplEnabled -> " + suplEnabled);
      smppHost = reader.read(SMPP_HOST, String.class);
      smppPort = reader.read(SMPP_PORT, Integer.class);
      smppSid = reader.read(SMPP_SID, String.class);
      smppPwd = reader.read(SMPP_PWD, String.class);
      logger.info("GMLC smppPwd -> " + smppPwd);
      fqdn = reader.read(FQDN, String.class);

      if (logger.isInfoEnabled()) {
        logger.info(String.format("GmlcProperties GMLC {GT: '%s', SSN: %d}, HLR { SSN: %d }, MSC { SSN: %d }, VLR { SSN: %d }, SGSN { SSN: %d }," +
                "MaxMapVersion: %d, MaxActCntOrigin: %d " + "Dialog Timeout: %d, Event Context Suspend Delivery Timeout: %d, " +
                "Diameter { OrigRealm: '%s', OrigHost: '%s', DestRealm: '%s', DestHost: '%s', GmlcNumber: '%s', GmlcAddress: '%s', GmlcAddressType: '%s', useRiaGmlcAddress: '%s' }, " +
                "LCS NON TRIGGERED REPORT OPTION { API: '%s' }, LCS URL CALLBACK { URL: '%s' }, MONGODB {Host: '%s', Port: %d, Database: '%s'}," +
                "SUPL { SUPL_ENABLED: '%s', SSL_ENABLED: '%s', SSL_PORT: %d, NO_SSL_PORT: %d, TLS_CERT_PATH: '%s', TLS_CERT_PWD: '%s', SUPL_SESSION_EXPIRED: %d, SUPL_PERIODIC_SESSION_EXPIRED: %d, " +
                        "SMPP_HOST: '%s', SMPP_PORT: '%d', SMPP_SID: '%s', SMPP_PWD: '%s', FQDN: '%s' }," +
                "REDIS { REDIS_DB_HOST: '%S', REDIS_DB_PORT: %d }, GLaaS {ENABLED: '%s', ENDPOINT: '%s', APP TOKEN: '%s', THREAD POLL: '%s' }",
            gmlcGt, gmlcSsn, hlrSsn, mscSsn, vlrSsn, sgsnSsn, maxMapVersion, maxActivityCount, dialogTimeout, eventContextSuspendDeliveryTimeout,
            diameterOriginRealm, diameterOriginHost, diameterDestRealm, diameterDestHost, diameterGmlcNumber, diameterGmlcAddress, diameterGmlcAddressType, useRiaGmlcAddress, lcsNonTriggeredReportOption,
            lcsUrlCallback, mongoHost, mongoPort, mongoDatabase, suplEnabled, suplSslEnabled, suplSslPort, suplNoSslPort, suplTlsCertPath, suplTlsCertPwd, suplSessionExpired, suplPeriodicSessionExpired,
            smppHost, smppPort, smppSid, smppPwd, fqdn, redisHost, redisPort, glaasEnabled, glaasCdrEndpoint, glaasAppToken, glassThreadPoll));
      }

      reader.close();
    } catch (XMLStreamException ex) {
      logger.error("Caught XML exception while loading GMLC properties!", ex);
    }
  }

  public enum CdrLoggedType {
    Database, Textfile,
  }

}