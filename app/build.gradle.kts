import Dependencies.implementation
import Dependencies.testImplementation
import Dependencies.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "net.portes.mytodo"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        buildConfigField("String", "BASE_URL", "\"https://run.mocky.io\"")
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

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Dependencies.CORE)
    implementation(Dependencies.NAVIGATION)
    implementation(Dependencies.LIVEDATA)
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_KAPT)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.MOSHI)
    kapt(Dependencies.MOSHI_KAPT)
    implementation(Dependencies.INTERCEPTOR)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.ANDROID_CHART)
    implementation(Dependencies.SWIPE_REFRESH)
    implementation(Dependencies.EPOXY)
    kapt(Dependencies.EPOXY_KAPT)
    implementation(Dependencies.BIOMETRIC)
    implementation(Dependencies.FIREBASE)

    testImplementation(Dependencies.TESTS)
    testImplementation(Dependencies.TESTS_COROUTINES)

    implementation(project(":ipc"))
    implementation(project(":topten"))
    implementation(project(":login"))
    implementation(project(":shared"))
}