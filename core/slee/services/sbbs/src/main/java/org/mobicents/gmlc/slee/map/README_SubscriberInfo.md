# SubscriberInfo Implementation for jSS7 9.2.5 Compatibility

## Problem Statement

The MobileCoreNetworkInterfaceSbb.java in the GMLC project calls many methods on SubscriberInfo that do not exist in jSS7 9.2.5:

- getTimeZone()
- getDaylightSavingTime()
- getIMSVoiceOverPsSessionsIndication()
- getMNPInfoRes()
- getMSClassmark2()
- getGPRSMSClass()
- getLastUEActivityTime()
- getLastRATType()
- getEPSSubscriberState()
- getLocationInformationEPS()
- getLocationInformation5GS()
- getIMSI()
- getMsisdn()

## Solution

This implementation provides a wrapper/adapter pattern that:
1. Wraps the base jSS7 9.2.5 SubscriberInfo
2. Provides all the missing methods
3. Uses reflection to extract values from the jSS7 implementation if available
4. Falls back to null for methods not supported by the runtime jSS7 version

## Files Created

### 1. SubscriberInfoFactory.java
Factory class with:
- extend(SubscriberInfo base) - wraps a base SubscriberInfo
- create(...) - creates with all fields
- uilder() - builder pattern for construction
- ExtendedSubscriberInfo interface defining all methods

### 2. ExtendedSubscriberInfoImpl.java
Implementation that:
- Implements ExtendedSubscriberInfo interface
- Wraps base SubscriberInfo (delegation pattern)
- Uses reflection to extract extended fields from base
- Supports direct field setting for testing
- Immutable once constructed

### 3. SubscriberInfoUsageExample.java
Examples showing:
- Wrapping SubscriberInfo from MAP events
- Building SubscriberInfo programmatically
- Integration with existing code

## Usage in MobileCoreNetworkInterfaceSbb

### Before (using reflection via SubscriberInfoHelper):
`java
SubscriberInfo subscriberInfo = event.getSubscriberInfo();
Object timeZone = SubscriberInfoHelper.getTimeZone(subscriberInfo);
Object daylightSavingTime = SubscriberInfoHelper.getDaylightSavingTime(subscriberInfo);
Object imsVoiceOverPs = SubscriberInfoHelper.getIMSVoiceOverPsSessionsIndication(subscriberInfo);
Object mnpInfoRes = SubscriberInfoHelper.getMNPInfoRes(subscriberInfo);
`

### After (using ExtendedSubscriberInfo):
`java
SubscriberInfo subscriberInfo = event.getSubscriberInfo();
SubscriberInfoFactory.ExtendedSubscriberInfo extended = 
    SubscriberInfoFactory.extend(subscriberInfo);

// Type-safe access - no casting needed
TimeZone timeZone = extended.getTimeZone();
DaylightSavingTime daylightSavingTime = extended.getDaylightSavingTime();
IMSVoiceOverPsSessionsIndication imsVoiceOverPs = extended.getIMSVoiceOverPsSessionsIndication();
MNPInfoRes mnpInfoRes = extended.getMNPInfoRes();

// Standard methods still work
LocationInformation locInfo = extended.getLocationInformation();
SubscriberState subState = extended.getSubscriberState();
IMEI imei = extended.getIMEI();
`

## Migration Guide

### Step 1: Import the new classes
`java
import org.mobicents.gmlc.slee.map.SubscriberInfoFactory;
`

### Step 2: Replace SubscriberInfoHelper calls
Replace:
`java
Object timeZone = SubscriberInfoHelper.getTimeZone(subscriberInfo);
`

With:
`java
SubscriberInfoFactory.ExtendedSubscriberInfo extended = 
    SubscriberInfoFactory.extend(subscriberInfo);
TimeZone timeZone = extended.getTimeZone();
`

### Step 3: Update type declarations
Replace:
`java
Object timeZone = SubscriberInfoHelper.getTimeZone(subscriberInfo);
`

With:
`java
TimeZone timeZone = extended.getTimeZone();
`

## Testing

Create test SubscriberInfo objects:
`java
SubscriberInfoFactory.ExtendedSubscriberInfo testInfo = 
    SubscriberInfoFactory.builder()
        .imei(testImei)
        .imsi(testImsi)
        .msisdn(testMsisdn)
        .locationInformation(testLocInfo)
        .subscriberState(testSubState)
        .timeZone(testTimeZone)
        .locationInformation5GS(testLoc5GS)
        .build();
`

## Backward Compatibility

- Existing code using SubscriberInfo interface continues to work
- The ExtendedSubscriberInfo extends SubscriberInfo
- Base methods are delegated to wrapped object
- New methods return null if not available (same as reflection approach)

## Benefits

1. **Type Safety**: No more Object returns requiring casting
2. **IDE Support**: Autocomplete and compile-time checking
3. **Maintainability**: Clear interface definition
4. **Testability**: Easy to create mock objects
5. **No jSS7 Changes**: Works with unmodified jSS7 9.2.5
