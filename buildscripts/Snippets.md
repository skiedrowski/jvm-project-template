Test
====

Enable Logging
--------------

For all test tasks

    tasks.withType(Test) {
        testLogging {
            events 'passed'
        }
    }

only within a special test task

    task slowTest(type: Test) {
        testLogging {
            events 'passed'
        }
    }

Ignore failures 
---------------
here: depending on configuration (see loadEnvironment.gradle)

Just for task "slowTest"

    task slowTest(type: Test) {
        ignoreFailures = project.rootProject.ext.get('config').ignoreTestFailures
    }

for all tasks of type test

    tasks.withType(Test) {
        ignoreFailures = project.rootProject.ext.get('config').ignoreTestFailures        
    }

Execute tests in parallel
-------------------------

    test.maxParallelForks = 4
    
or

    test.maxParallelForks = Runtime.runtime.availableProcessors() 

Using JUnit Categories
----------------------

see execSlowTestsSeparately.gradle


Maven
=====
Install into local/Upload to remote maven repository

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
    
