plugins {
    kotlin("jvm") version Ver.kotlin apply false
    id("org.jetbrains.kotlin.plugin.noarg") version Ver.kotlin apply false
    java
}
//TODO setup: add project description
description = """describe project"""

val buildscriptDir = "${rootProject.projectDir}/buildscripts"

apply(from = "$buildscriptDir/loadEnvironment.gradle.kts")
apply(from = "$buildscriptDir/printGradleInfo.gradle.kts")
apply(from = "$buildscriptDir/checkJdk.gradle.kts")

//apply(from = "$buildscriptDir/sonarqube.gradle.kts")
//apply(from = "$buildscriptDir/jacocoMultimodule.gradle.kts")
//apply(from = "$buildscriptDir/jacocoSingleModule.gradle.kts")

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")

    group = "com.example.mygid" // or "com.example.${rootProject.name}"
    version = "NEXT-SNAPSHOT"

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

//    configure<org.jetbrains.kotlin.noarg.gradle.NoArgExtension> {
//        annotation("x.x.x.NoArg")
//    }
    java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))
    //    java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(Deps.kt_stdlib_jdk8)
        implementation(Deps.apache_http_core)
        implementation(Deps.apache_http_client)

        testImplementation(Deps.junit)
        testImplementation(Deps.junit_engine)
        //for kotlin tests
        testImplementation(Deps.kotlin_reflect)
        testImplementation(Deps.mockito_kotlin)
        testImplementation(Deps.hamkrest)
        //for java tests
        testImplementation(Deps.mockito_core) {
            exclude(module = "hamcrest-core")
            exclude(module = "hamcrest-library")
            exclude(module = "objenesis")
        }
        testImplementation(Deps.hamcrest_integration)
    }

    apply(from = "$buildscriptDir/sourcesJar.gradle.kts")
    apply(from = "$buildscriptDir/testJar.gradle.kts")
    apply(from = "$buildscriptDir/javadocJar.gradle.kts")

    tasks.withType<Test> {
        useJUnitPlatform()
        maxParallelForks = Runtime.getRuntime().availableProcessors() * 3 / 4
    }

    //apply(from = "$buildscriptDir/execSequentialTestsSeparately.gradle.kts")

    //apply from: "$buildscriptDir/logAllTests.gradle"
    apply(from = "$buildscriptDir/ignoreTestFailuresIfConfiguredInEnv.gradle.kts")
    apply(from = "$buildscriptDir/mavenPublish.gradle.kts")
}
//apply(from = "$buildscriptDir/fixJenkins.gradle")
apply(from = "$buildscriptDir/wrapper.gradle.kts")
