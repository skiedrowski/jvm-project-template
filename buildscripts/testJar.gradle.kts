//provide tests for other modules of this project

// dependency configuration providing common test classes
val testJar: Configuration by configurations.creating

val testJarTask = task<Jar>("jarTestClasses") {
    description = "Assembles a jar archive containing common test classes of project ${project.name}"
    group = "build"
    appendix = "tests"
    classifier = "tests"
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    from(project.the<SourceSetContainer>()["test"].output)
    //would be easier, but does not work in separate file?!    from(project.sourceSets.test.get().output)
}

// add test jar task as artifact
artifacts {
    add("testJar", testJarTask)
}
