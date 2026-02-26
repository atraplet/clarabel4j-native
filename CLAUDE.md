# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

clarabel4j-native is a distribution-only Maven project that packages pre-compiled native shared libraries of the [Clarabel](https://clarabel.org) solver for use by [clarabel4j](https://github.com/atraplet/clarabel4j). There is no Java source code — only native binaries (`libclarabel_c.so`, `clarabel_c.dll`, `libclarabel_c.dylib`) bundled as resources and a single test class.

## Build Commands

```bash
mvn clean verify          # Full build and test
mvn test                  # Run tests only
mvn package               # Build JARs (produces linux_64, windows_64, osx_arm64 classifiers)
mvn test -Dtest=LoadLibraryTest#loadLibrary  # Run single test method
```

**Prerequisite:** Native libraries must exist in `src/main/resources/natives/{linux_64,windows_64,osx_arm64}/` before tests can run. In CI, the `build_natives.yml` workflow compiles these from [Clarabel.cpp](https://github.com/oxfordcontrol/Clarabel.cpp) and the composite action downloads them.

## Architecture

- **No production Java code** — `src/main/` contains only native binary resources
- **Native libraries** are built from Clarabel.cpp (the version is specified in the `build_natives.yml` workflow) via CMake/Cargo in CI, with patches from `patches/CMakeLists.txt.patch` (disables Eigen3 dep and examples)
- **Platform-specific features:** Linux/Windows enable `faer-sparse,pardiso-mkl`; macOS enables `faer-sparse` only
- **SciJava NativeLoader** handles platform detection and library extraction at runtime
- **maven-jar-plugin** creates per-platform JARs using classifiers (`linux_64`, `windows_64`, `osx_arm64`)
- **Java module system:** Automatic-Module-Name is `com.ustermetrics.clarabel4j_native`; tests run with `--enable-native-access=ALL-UNNAMED`

## Release Process

Releases are tag-driven. Creating a `v*` tag triggers `release.yml`, which builds natives, signs artifacts with GPG, and publishes to Maven Central.

## Key Paths

- `patches/CMakeLists.txt.patch` — CMake patch applied to Clarabel.cpp during native builds
- `src/test/java/com/ustermetrics/clarabel4j_native/LoadLibraryTest.java` — sole test (loads `clarabel_c` via NativeLoader)
- `.github/workflows/build_natives.yml` — compiles native libraries for all three platforms
- `.github/actions/download-libs-and-detect-jdk/` — composite action that downloads native artifacts and detects JDK version from pom.xml
