Gradle Build
============
The template contains a number of gradle build scripts conveniently organized in the `buildscripts` folder.

Each script implements a build feature in a reasonable granularity. They should be included into the main/module build.gradle to keep the build.gradle itself small.

In addition to the *.gradle build scripts, this folder also contains [snippets](../buildscripts/snippets/_info_.md) with gradle snippets that one might want to copy into a `build.gradle`.

The main build.gradle contains a number of `TODO`s which indicate where customization is very likely needed.

Common Gradle Tasks
-------------------
If the build is configured accordingly there are the following common build tasks available:

clean build directory

    gradlew clean

run tests (only those which are NOT annotated with @Category(SlowTest.class))

	gradlew test

run slow tests (only those which are annotated with @Category(SlowTest.class))

	gradlew slowTest

analyse (Checkstyle, PMD, Findbugs, JaCoCo)

     gradlew check

CodeCoverage (JaCoCo)

	gradlew jacocoTestReport

build jar, run tests, run slow tests, run checks

	gradlew build

### Maven
The maven tasks depend on something like [mavenSnippets](../buildscripts/snippets/mavenSnippets.md).
Depending on the build, the following artifacts are uploaded: classes, sources, javadoc; snapshots/releases depending on `version`

install into local maven

	gradlew install

upload into nexus

	gradlew upload


