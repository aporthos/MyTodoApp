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

    val LEGACY = listOf(
        "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    )

    val NAVIGATION = listOf(
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}",
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    )

    val LIVEDATA = listOf(
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    )

    val TESTS = listOf(
        "junit:junit:${Versions.junit}",
        "org.mockito:mockito-inline:${Versions.mockitoInline}",
        "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}",
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
}