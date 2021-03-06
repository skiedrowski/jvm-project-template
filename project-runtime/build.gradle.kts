description = """runtime classes

This module packs all modules needed _at runtime_ into a single dependency. In contrast 
the api module contains all classes needed _at compiletime_.

The runtime module should be used to include the project (library) into a runnable 
artifact (i.e. ear, war, executable jar). By including the runtime module instead of the 
implementation module(s), the structure of the modules is hidden behind the runtime 
facade. This allows refactoring the implementation modules without affecting the runnable 
artifact."""


dependencies {
    runtimeOnly(project(":project-api"))
    runtimeOnly(project(":project-impl"))
}