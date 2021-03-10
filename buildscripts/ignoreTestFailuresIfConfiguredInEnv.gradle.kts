tasks.withType<Test>().configureEach {
    //the more elegant val config by project.rootProject.extra does not compile
    val config = project.rootProject.extra["config"] as Map<String, *>
    ignoreFailures = config["ignoreTestFailures"] as Boolean
}