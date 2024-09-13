# Clarabel Solver for Java Native Libraries

[![Build](https://github.com/atraplet/clarabel4j-native/actions/workflows/build.yml/badge.svg)](https://github.com/atraplet/clarabel4j-native/actions/workflows/build.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.ustermetrics/clarabel4j-native)](https://central.sonatype.com/artifact/com.ustermetrics/clarabel4j-native)
[![Apache License, Version 2.0](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://github.com/atraplet/clarabel4j-native/blob/master/LICENSE)

clarabel4j-native (Clarabel Solver for Java Native Libraries) contains shared library release binaries
of [Clarabel](https://clarabel.org) for [clarabel4j](https://github.com/atraplet/clarabel4j) for Linux (linux_64), Windows (windows_64), and MacOS (osx_arm64).

## Usage

### Dependency

Add the latest version from [Maven Central](https://central.sonatype.com/artifact/com.ustermetrics/clarabel4j-native) to
your `pom.xml`

```
<dependency>
    <groupId>com.ustermetrics</groupId>
    <artifactId>clarabel4j-native</artifactId>
    <version>x.y.z</version>
    <scope>runtime</scope>
</dependency>
```

## Build

## Release

Update the version in the `pom.xml`, create a tag, and push it by running

```
export CLARABEL_VERSION=X.Y.Z
export VERSION=X.Y.Z
export VERSION=$VERSION-$CLARABEL_VERSION
git checkout --detach HEAD
sed -i -E "s/<version>[0-9]+\-SNAPSHOT<\/version>/<version>$VERSION<\/version>/g" pom.xml
git commit -m "v$VERSION" pom.xml
git tag v$VERSION
git push origin v$VERSION
```

This will trigger the upload of the package to Maven Central via GitHub Actions.

Then, go to the GitHub repository [releases page](https://github.com/atraplet/clarabel4j-native/releases) and update the
release.

## Credits

This project is based on the native open source mathematical programming
solver [Clarabel](https://clarabel.org), which is developed and maintained by Paul Goulart (main development, maths and
algorithms) and Yuwen Chen (maths and algorithms) from the [Oxford Control Group](http://www.eng.ox.ac.uk/control) of
the Department of Engineering Science at the University of Oxford, and other contributors. For details
see https://clarabel.org, https://github.com/oxfordcontrol/Clarabel.rs,
and https://github.com/oxfordcontrol/Clarabel.cpp.
