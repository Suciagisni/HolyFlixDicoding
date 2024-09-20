// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
    }

    repositories {
        google()
        mavenCentral()

    }
}

plugins {
    id("com.android.application") version "8.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.4.1" apply false
    id("com.android.dynamic-feature") version "8.4.1" apply false
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.jetbrains.kotlin.android) apply false
//    alias(libs.plugins.android.library) apply false
//    alias(libs.plugins.android.dynamic.feature) apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}