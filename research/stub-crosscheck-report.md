# GMLC Stub Cross-Check Report

**Date:** 2026-04-08  
**Project:** RestComm GMLC (restcomm-gmlc-parent 6.0.1-SNAPSHOT)

## 1. Stub Files Analysis

### Location: `map-stub/src/main/java/org/mobicents/protocols/ss7/gmlc/load/`

| File | Size | Purpose |
|------|------|---------|
| `TestHarness.java` | 8,646 bytes | Abstract base class for MAP load testing |
| `Client.java` | 102,890 bytes | Client-side MAP load test implementation |
| `ClientServer.java` | 194,695 bytes | Combined client/server MAP load test |
| `Server.java` | 125,372 bytes | Server-side MAP load test implementation |

### Stub Files Usage Status:
- **All 4 files are NOT used anywhere in the core GMLC codebase**
- These are standalone load testing utilities
- Only imported within the `map-stub` module itself

---

## 2. Dependencies Comparison

### jSS7 (MAP Stack) - Version: 9.0.0-318

**In `map-stub/pom.xml`:**
- `map-api` - MAP API interfaces
- `map-impl` - MAP implementation
- `tcap-api`, `tcap-impl` - TCAP protocol
- `sccp-api`, `sccp-impl` - SCCP protocol
- `m3ua-api`, `m3ua-impl` - M3UA protocol
- `sctp-api`, `sctp-impl` - SCTP transport

### jDiameter - Version: 2.0.0-302

**Used in:**
- `core/slee/services/sbbs/pom.xml` - Diameter RA for LTE location
- `core/bootstrap/config/diameter/*` - Diameter configuration files
- **NOT used in `map-stub`** - map-stub is MAP-only, no Diameter

---

## 3. Key Findings

### Duplicate Stubs: NONE FOUND
- No duplicate definitions of Server, Client, ClientServer, or TestHarness in core
- These are standalone test utilities in `map-stub` module

### Extra Stubs (Unused in Core):
```
map-stub/src/main/java/org/mobicents/protocols/ss7/gmlc/load/
├── TestHarness.java    (base class - NOT used in core)
├── Client.java         (NOT used in core)
├── ClientServer.java   (NOT used in core)
└── Server.java         (NOT used in core)
```

### Recommendations:
1. **Keep stubs** - These are valuable load testing utilities for development
2. **No action needed** - No duplicate/extra stubs that need removal
3. The `map-stub` module is properly isolated and doesn't conflict with core

---

## 4. Version Consistency

| Library | Version | Location |
|---------|---------|----------|
| jSS7 RestComm | 9.0.0-318 | main pom.xml |
| jDiameter | 2.0.0-302 | main pom.xml |
| jain-slee.diameter | 7.3.0-102 | main pom.xml |

All versions are consistent across the project.

---

## 5. Module Dependencies

```
main pom.xml
├── core/ (uses jss7 + jdiameter)
│   └── slee/services/sbbs/ (Diameter RA)
└── map-stub/ (uses jss7 only, NO jdiameter)
```

---

## 6. Cross-Reference Summary

| Stub File | jSS7 Dependency | jDiameter Dependency | Used in Core |
|-----------|-----------------|---------------------|--------------|
| TestHarness.java | Yes | No | No |
| Client.java | Yes | No | No |
| ClientServer.java | Yes | No | No |
| Server.java | Yes | No | No |

---

**Conclusion:** The `map-stub` module contains load testing utilities that are intentionally isolated from the core GMLC codebase. **No duplicate or redundant stubs found that need removal.** The module serves as a standalone load testing harness for MAP protocol testing.
