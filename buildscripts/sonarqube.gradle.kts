//
//
//
//          not (yet) working (when defined in separate file)
//
//


// add
//
//  plugins {
//        id("org.sonarqube") version Deps.sonarqube_plugin
//  }
//
// to the very top of your buildscript

//tasks.withType<Sonarqube>().configureEach {
//configure<SonarqubeExtension> {
sonarqube {
    properties {
        property("sonar.login", System.getProperty("SONAR_USER"))
        property("sonar.password", System.getProperty("SONAR_PASSWORD"))
        property("sonar.host.url", System.getProperty("SONAR_HOST_URL"))
        property("sonar.jacoco.reportPath", "${project.buildDir}/jacoco/test.exec")
    }
}
