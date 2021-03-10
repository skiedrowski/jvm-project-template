// dependency configuration providing javadocs
val javadocJar: Configuration by configurations.creating

//custom task for creating a javadoc jar
val javadocTask = task<Jar>("javadocJar") {
    description = "Assembles a jar archive containing the javadocs of project ${project.name}"
    classifier = "javadoc"
    dependsOn(JavaPlugin.JAVADOC_TASK_NAME)
    from(tasks["javadoc"])
}

artifacts {
    add("javadocJar", javadocTask)
}

// turn off doclint (JDK8) since it is way too strict
// http://blog.joda.org/2014/02/turning-off-doclint-in-jdk-8-javadoc.html
if (JavaVersion.current().isJava8Compatible) {
    tasks.withType<Javadoc>().configureEach {
        options.quiet().jFlags("Xdoclint:none")
//        options.addStringOption('Xdoclint:none', '-quiet')
    }
}