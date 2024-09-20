plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs")
    id ("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")

//    alias(libs.plugins.android.application)
//    alias(libs.plugins.jetbrains.kotlin.android)
//
//    id("androidx.navigation.safeargs")
//    id("kotlin-parcelize")
//    id("com.google.devtools.ksp")
//    id("com.google.dagger.hilt.android")
}
apply(from = "../shared_dependencies.gradle")
android {
    namespace = "com.example.holyflix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.holyflix"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField ("String", "API_KEY", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MmE0OTcyNDE0ZjQ1ZWEyNDJiY2I4MzIwYzY3YTkwOSIsInN1YiI6IjY1Y2RhNWQ3YzI2Nzk2MDE3ZWM4YzhjOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.M5Kx_3dP_KUnWZcifnXj25RI7S2OAqV28vWSUSIh0Ik\"")
        buildConfigField ("String", "TOKEN_KEY", "\"3af425520cbfdb9551da71e41b2743f5\"")


    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug{
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")

    //room
    ksp("androidx.room:room-compiler:2.6.1")
}