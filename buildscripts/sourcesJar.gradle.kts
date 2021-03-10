val sourceJar: Configuration by configurations.creating

// custom task for creating a sources jar
val sourcesJarTask = task<Jar>("sourcesJar") {
    dependsOn(tasks.named("classes"))
    classifier = "sources"
    from(project.the<SourceSetContainer>()["main"].allSource)
}

// add sources jar task as artifact
artifacts {
    add("sourceJar", sourcesJarTask)
}
