Introduction
============
This project defines a generic project structure which may serve as a template for JVM based projects.

It primarily serves modules and gradle buildscripts.

Modules
=======
The project is composed of 3 modules:
* project-api
* project-impl (depends on project-api)
* project-runtime (depends on project-api and project-impl)

project-api
-----------
Defines the public api of the project. This module/artifact should be used at compiletime. It contains all classes/interfaces/traits/... which are needed for development.

project-impl
------------
This module contains the implementation of project-api. It is needed at runtime. However, one might choose to split the module into multiple implementation modules. 
It defines a dependency to project-api for production code as well as for test code.

project-runtime
---------------
The runtime module does not contain any code. It just defines dependencies to project-api and project-impl (this time only production code).
Its only purpose is to provide the whole project with all classes needed at runtime to a project using our project. By declaring a dependency to project-runtime the using project is not affected by changes to the internal structure of our project.

Gradle Build
============
The template contains a number of gradle build scripts conveniently organized in the `buildscripts` folder.

Each script implements a build feature in a reasonable granularity. They should be included into the main/module build.gradle to keep the build.gradle itself small.

In addition to the *.gradle build scripts, this folder also contains `Snippets.md` with gradle snippets that one might want to copy into a `build.gradle`.

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

Analyse (Checkstyle, PMD, Findbugs, JaCoCo)

     gradlew check

CodeCoverage (JaCoCo)

	gradlew jacocoTestReport

build jar, run tests, run slow tests, run checks

	gradlew build

install into local maven (classes, sources, javadoc)

	gradlew install

upload into nexus (classes, sources, javadoc; snapshots/releases depending on version)
 
	gradlew upload
 	
Release
-------
A release may be built by following these steps:
 
* set release version (without -SNAPSHOT) in main build.gradle 
* run `./gradlew clean build` or `upload` if you want to upload to a maven repository 
* set next snapshot version (with -SNAPSHOT) in main build.gradle

Final remarks
=============
Keep in mind that this is just a _template_ collection. It is expected that every concrete project needs to adapt the
scripts and the module structure to its needs. However, it serves as a starting point.
