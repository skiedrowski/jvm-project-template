println(
    """
Gradle Version:       ${gradle.gradleVersion}
Gradle home:          ${gradle.gradleHomeDir}
Gradle user home dir: ${gradle.gradleUserHomeDir}
Base directory:       $projectDir
Running script:       ${relativePath(buildFile)}
Java version:         ${System.getProperty("java.version")}
"""
)