Maven
=====
Install into local/Upload to remote maven repository using `gradle upload`

    version = <maven version>
    group = <maven group id>

    apply plugin: 'maven'

    //Upload to a maven repository (i.e. nexus) via `./gradlew upload`
    uploadArchives {
        repositories {
            mavenDeployer {
                //deploymentUser and deploymentPassword should be set in ~/.gradle/gradle.properties
                //since they should not end up in the git repository
                repository(url: mavenRepoURL) {
                    authentication(userName: deploymentUser, password: deploymentPassword)
                }
            }
        }
    }

    //Install into local maven repository via `./gradlew install`
    install {
        configuration = configurations.archives
    }
    
