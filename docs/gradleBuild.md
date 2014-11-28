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

### Maven (Upstream)
Maven tasks depend on [mavenUpstream.gradle](../buildscripts/mavenUpstream.md) and the according configuration variables.
Depending on the build, the following artifacts are uploaded: classes, sources, javadoc; snapshots/releases depending on `version`

install into local maven

	gradlew install

upload into nexus

	gradlew upload

###Application Tasks
Application Tasks depend on somethink like [applicationSnippets](../buildscripts/snippets/applicationSnippets.md).

create app in build/install
	
	gradlew installApp
	
create zip containing the whole app (zips the result of installApp, suitable for distribution)
	
	gradlew distZip
 
###Possible  Release Process
 
 * set release version (without -SNAPSHOT) in local build.gradle 
 * commit && push
 * if the version to be released already exists in nexus, it has to be deleted firstly (or you'll get "error code 400: bad request")
 * run PrescriptionOCR buildjob on Jenkins (manually)
 * set next snapshot version (with -SNAPSHOT) in local build.gradle
 * commit && push

the app may be downloaded via the build job. It is currently not deployed to nexus.

