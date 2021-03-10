// Required properties:
// MVN_PUBLISH_URL_PREFIX     - we'll append "/snapshots" or "/releases" depending on the project.version
// MVN_USER
// MVN_PASSWORD
//
// may be set via gradle.properties (systemProp.MVN_USER=fred) or via commandline (-DMVN_USER=fred)
//
// The script also depends on valid
//      version = <maven version>
//      group = <maven group id>
//
// This script requires a sourcesJar artifact (i.e. as built by sourceJar.gradle) and a testJar artifact (i.e. as built by testJar.gradle)
//
// currently, only one maven repo is supported; to use different servers (i.e. for snapshots/release), we'd need to
// duplicate the properties and further complicate the usage. Let us wait for the use case ;-).
//

apply(plugin = "maven-publish")

// if there is a failure like
//
// * What went wrong:
// Execution failed for task ':script-api:publishMavenPublicationToMavenRepository'.
// > Failed to publish publication 'maven' to repository 'maven'
// > Authentication scheme 'all'(Authentication) is not supported by protocol 'file'
//
// you probably did not set the System properties MVN_*, i.e. MVN_PUBLISH_URL_PREFIX

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["jarTestClasses"])
        }
    }

    repositories {
        maven {
            val mvnPublishUrlPrefix = System.getProperty("MVN_PUBLISH_URL_PREFIX")
            val releasesRepoUrl = "$mvnPublishUrlPrefix/releases"
            val snapshotsRepoUrl = "$mvnPublishUrlPrefix/snapshots"
            url = uri(
                if (project.version.toString().endsWith("-SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            )

            credentials {
                username = System.getProperty("MVN_USER")
                password = System.getProperty("MVN_PASSWORD")
            }
        }
    }
}
