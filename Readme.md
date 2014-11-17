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
Read a description of the [build here](docs/gradleBuild.md)
 	
Release
=======
A release may be built by following these steps:
 
* set release version (without -SNAPSHOT) in main build.gradle 
* run `./gradlew clean build` or `upload` if you want to upload to a maven repository 
* set next snapshot version (with -SNAPSHOT) in main build.gradle

Final remarks
=============
Keep in mind that this is just a _template_ collection. It is expected that every concrete project needs to adapt the
scripts and the module structure to its needs. However, it serves as a starting point.
