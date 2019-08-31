rootProject.buildFileName = "build.gradle.kts"

include(*ModuleDependency.getAllModules().toTypedArray())

pluginManagement {
    repositories {
        // Gradle Central Plugin Repository
        gradlePluginPortal()

        // gradle-android-junit-jacoco-plugin snapshot
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}
