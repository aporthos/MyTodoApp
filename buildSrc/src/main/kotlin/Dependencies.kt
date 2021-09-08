import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @author amadeus.portes
 */
object Dependencies {
    val CORE = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}",
        "androidx.core:core-ktx:${Versions.core}",
        "androidx.appcompat:appcompat:${Versions.appCompat}",
        "com.google.android.material:material:${Versions.material}"
    )

    val NAVIGATION = listOf(
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}",
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    )

    val LIVEDATA = listOf(
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    )

    val HILT = listOf(
        "com.google.dagger:hilt-android:${Versions.hilt}",
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    )

    val HILT_KAPT = listOf(
        "com.google.dagger:hilt-android-compiler:${Versions.hilt}",
        "androidx.hilt:hilt-compiler:${Versions.hiltViewModel}"
    )

    val TIMBER = listOf(
        "com.jakewharton.timber:timber:${Versions.timber}"
    )

    val RETROFIT = listOf(
        "com.squareup.retrofit2:retrofit:${Versions.retrofit}",
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    )

    val COROUTINES = listOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    )

    val MOSHI = listOf(
        "com.squareup.moshi:moshi:${Versions.moshi}",
        "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    )

    val MOSHI_KAPT = listOf(
        "com.squareup.moshi:moshi-kotlin-codegen:${Versions.codegen}"
    )

    val INTERCEPTOR = listOf(
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    )

    val LOTTIE = listOf(
        "com.airbnb.android:lottie:${Versions.lottie}"
    )

    val ANDROID_CHART = listOf(
        "com.github.PhilJay:MPAndroidChart:${Versions.androidChart}"
    )

    val SWIPE_REFRESH = listOf(
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
    )

    val TESTS = listOf(
        "junit:junit:${Versions.junit}",
        "org.mockito:mockito-inline:${Versions.mockitoInline}",
        "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    )

    val TESTS_COROUTINES = listOf(
        "androidx.arch.core:core-testing:${Versions.coreTesting}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    )

    fun DependencyHandler.implementation(list: List<String>) {
        list.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    fun DependencyHandler.testImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("testImplementation", dependency)
        }
    }

    fun DependencyHandler.kapt(list: List<String>) {
        list.forEach { dependency ->
            add("kapt", dependency)
        }
    }
}