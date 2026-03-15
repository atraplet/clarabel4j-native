# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

clarabel4j-native bundles platform-specific (linux_64, windows_64, osx_arm64) compiled native shared libraries of the 
[Clarabel](https://clarabel.org) mathematical programming solver for use by 
[clarabel4j](https://github.com/atraplet/clarabel4j). 

**This project contains no Java source code in `src/main/java`**", only native binaries (`libclarabel_c.so`, 
`libclarabel_c.dylib`, `clarabel_c.dll`) bundled as resources and a single Java test class verifying native library 
loading. 

## Build Commands

```bash
mvn clean verify          # Full build and test
mvn test                  # Run tests only
mvn package               # Build JARs (produces linux_64, windows_64, osx_arm64 classifiers)
```

**Prerequisite:** Native libraries must exist in `src/main/resources/natives/{linux_64,windows_64,osx_arm64}/` before
tests can run. Native libraries are **not built locally**. They are compiled via GitHub Actions (`build_natives.yml`)
from [Clarabel.cpp](https://github.com/oxfordcontrol/Clarabel.cpp).

## Architecture

- **No production Java code**: `src/main/` contains only native binary resources
- **Single Java test class** verifying native library loading
- **Native libraries** are built from Clarabel.cpp only via GitHub Actions (`build_natives.yml`), not locally
- **SciJava NativeLoader** handles platform detection and library extraction at runtime
- **maven-jar-plugin** creates per-platform JARs using classifiers (`linux_64`, `windows_64`, `osx_arm64`) and a default
  JAR for all platforms
- **Java module system:** Automatic-Module-Name is `com.ustermetrics.clarabel4j_native`. Tests run with 
  `--enable-native-access=ALL-UNNAMED`

## Release Process

Releases are tag-driven. Creating a `v*` tag triggers `release.yml`, which builds natives, signs artifacts with GPG, 
and publishes to Maven Central. See README.md for the exact version/tag commands.
