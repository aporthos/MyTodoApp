import Dependencies.implementation
import Dependencies.kapt
import Dependencies.testImplementation

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        consumerProguardFiles(AppConfig.proguardConsumerRules)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
}

dependencies {

    implementation(Dependencies.CORE)
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_KAPT)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.FIREBASE)
    implementation(Dependencies.COROUTINES_PLAY_SERVICES)
    implementation(Dependencies.TIMBER)

    testImplementation(Dependencies.TESTS)
    testImplementation(Dependencies.TESTS_COROUTINES)

    implementation(project(":shared"))
}