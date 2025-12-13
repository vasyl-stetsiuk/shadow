// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.publishing.CheckSigningTask>().configureEach {
        gradleHomePath.set(gradle.gradleUserHomeDir.absolutePath)
    }
}