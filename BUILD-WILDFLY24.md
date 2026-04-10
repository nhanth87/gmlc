# GMLC Build Guide for WildFly 24

## Version Information

| Component | Version |
|-----------|---------|
| jSS7 | 9.0.0-318 |
| MAP RA | 9.0.0-215 |
| SCTP | 2.0.2-17 |
| GMLC | 6.0.1-SNAPSHOT |
| WildFly | 24.0.1.Final |
| Java | 11 |

## Prerequisites

1. **Java 11** installed
2. **Maven 3.8+** installed
3. **Ant 1.9+** installed (optional, for build.xml)
4. **WildFly 24.0.1.Final** downloaded

## Build Steps

### Step 1: Build SCTP (if not already built)

```bash
cd ../sctp
mvn clean install -DskipTests
```

### Step 2: Build jSS7

```bash
cd ../jSS7
mvn clean install -DskipTests
```

Expected output:
- jSS7 modules in `service/wildfly/modules/target/module/main/`
- jSS7 extension in `service/wildfly/extension/target/module/main/`
- jSS7 commons in `service/wildfly/restcomm-ss7-wildfly-commons/target/module/main/`

### Step 3: Build jain-slee.ss7 (MAP RA)

```bash
cd ../jain-slee.ss7
mvn clean install -DskipTests
```

Expected output:
- MAP RA DU in `resources/map/du/target/restcomm-slee-ra-map-du-9.0.0-215.jar`

### Step 4: Build GMLC

```bash
cd ../gmlc
mvn clean install -DskipTests
```

Or using the local build script:

```bash
cd release-wildfly
ant -f build-local.xml release
```

## Version Configuration

All versions are defined in `gmlc/pom.xml`:

```xml
<properties>
    <jss7.restcomm.version>9.0.0-318</jss7.restcomm.version>
    <restcomm.resources.map.version>9.0.0-215</restcomm.resources.map.version>
    <sctp.version>2.0.2-17</sctp.version>
    <!-- ... -->
</properties>
```

## Deploy to WildFly 24

### Option 1: Manual Copy

1. **Copy jSS7 modules**:
```bash
cp -r ../jSS7/service/wildfly/modules/target/module/main/* \
  $WILDFLY_HOME/modules/org/restcomm/ss7/main/
```

2. **Copy jSS7 extension**:
```bash
cp -r ../jSS7/service/wildfly/extension/target/module/main/* \
  $WILDFLY_HOME/modules/org/restcomm/ss7/extension/main/
```

3. **Copy GMLC modules**:
```bash
cp -r core/bootstrap-wildfly/target/module/main/* \
  $WILDFLY_HOME/modules/org/restcomm/gmlc/main/
```

4. **Copy Deployable Units**:
```bash
cp core/slee/services/du/target/gmlc-services-du-6.0.1-SNAPSHOT.jar \
  $WILDFLY_HOME/standalone/deployments/

cp ../jain-slee.ss7/resources/map/du/target/restcomm-slee-ra-map-du-9.0.0-215.jar \
  $WILDFLY_HOME/standalone/deployments/
```

### Option 2: Using Ant Build Script

```bash
cd release-wildfly
ant -f build-local.xml release
```

This will:
- Check prerequisites
- Copy jSS7 modules to WildFly
- Copy MAP RA from local build
- Build GMLC
- Copy GMLC services to WildFly
- Create release package

## Configure WildFly

Edit `$WILDFLY_HOME/standalone/configuration/standalone.xml`:

### 1. Add Extension

```xml
<extensions>
    <!-- existing extensions -->
    <extension module="org.restcomm.ss7.extension"/>
</extensions>
```

### 2. Add Subsystems

```xml
<subsystem xmlns="urn:org.restcomm:ss7:1.0">
    <mbean name="org.restcomm.ss7:restcomm-ss7-service">
        <property name="shellExecutor" value="true"/>
        <property name="sctp" value="true"/>
        <property name="m3ua" value="true"/>
        <property name="sccp" value="true"/>
        <property name="tcap" value="true"/>
        <property name="map" value="true"/>
        <property name="cap" value="true"/>
    </mbean>
</subsystem>
```

### 3. Add Socket Bindings

```xml
<socket-binding-group name="standard-sockets" ...>
    <!-- existing bindings -->
    <socket-binding name="ss7-sctp" port="2905"/>
    <socket-binding name="ss7-shell" port="3435"/>
</socket-binding-group>
```

## Start WildFly

```bash
$WILDFLY_HOME/bin/standalone.bat
```

Or with debugging:

```bash
$WILDFLY_HOME/bin/standalone.bat --debug
```

## Verify Deployment

Check logs for successful deployment:

```
INFO  [org.restcomm.ss7.service.SS7ExtensionService] (MSC service thread 1-2) jSS7 Extension Service started
INFO  [org.restcomm.gmlc] (ServerService Thread Pool -- 20) GMLC service started
```

## Troubleshooting

### Missing Dependencies

If you see `Could not find artifact` errors, ensure:
1. jSS7 is built and installed to local Maven repo
2. jain-slee.ss7 is built and installed
3. SCTP is built and installed

### Version Mismatch

Check that all versions in `gmlc/pom.xml` match:
- `jss7.restcomm.version` = 9.0.0-318
- `restcomm.resources.map.version` = 9.0.0-215
- `sctp.version` = 2.0.2-17

### Module Conflicts

Clean WildFly modules before redeploy:
```bash
rm -rf $WILDFLY_HOME/modules/org/restcomm
rm -rf $WILDFLY_HOME/standalone/deployments/*.jar
```

## Project Structure

```
ethiopia-working-dir/
├── sctp/                     # SCTP library (2.0.2-17)
├── jSS7/                     # jSS7 stack (9.0.0-318)
│   ├── service/wildfly/
│   │   ├── modules/target/
│   │   ├── extension/target/
│   │   └── restcomm-ss7-wildfly-commons/target/
├── jain-slee.ss7/            # MAP RA (9.0.0-215)
│   └── resources/map/du/target/
└── gmlc/                     # GMLC application
    ├── core/
    │   ├── bootstrap-wildfly/
    │   └── slee/services/du/
    └── release-wildfly/
        └── build-local.xml
```

## Build Output

After successful build:
- `gmlc/core/slee/services/du/target/gmlc-services-du-6.0.1-SNAPSHOT.jar`
- `gmlc/core/bootstrap-wildfly/target/module/main/` (GMLC modules)
- `gmlc/Extended-GMLC-6.0.1-SNAPSHOT.zip` (Full release package)
