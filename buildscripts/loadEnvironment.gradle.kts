val environment = if (project.hasProperty("env")) project.property("env") else "dev"
project.extra["environment"] = environment
println("Environment is set to $environment")

apply(from = "gradle/env_$environment.gradle.kts")


// create an env file for each environment in <rootproject>/gradle
//
//project.extra["config"] = mapOf(
//    Pair("ignoreTestFailures", true),
//    Pair("nexus_home", "https://my_mvn_repo")
//)
//
// name the file env_<environment>.gradle.kts
