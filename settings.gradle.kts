rootProject.buildFileName = "build.gradle.kts"

include(*ModuleDependency.getAllModules().toTypedArray())

pluginManagement {
    repositories {
        // Gradle Central Plugin Repository
        gradlePluginPortal()

        // gradle-android-junit-jacoco-plugin snapshot
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }

    // Add resolution strategy for gradle-android-junit-jacoco-plugin, so snapshot version can be properly resolved
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.vanniktech.android.junit") {
                useModule("com.vanniktech:gradle-android-junit-jacoco-plugin:${requested.version}")
            }
        }
    }
}
