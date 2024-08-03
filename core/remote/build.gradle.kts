import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "team.ppac.remote"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", getLocalProperties("RELEASE_BASE_URL"))
        }
        release {
            buildConfigField("String", "BASE_URL", getLocalProperties("RELEASE_BASE_URL"))
        }
    }

    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":core:error-handling"))
    implementation(project(":core:datastore"))
    implementation(libs.datastore)
    implementation(libs.retrofit)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.moshi)
    ksp(libs.moshi.codegen)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.timber)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlinx.collections.immutable)
}

fun getLocalProperties(key: String): String {
    val properties = Properties().apply {
        load(FileInputStream(rootProject.file("local.properties")))
    }
    return properties.getProperty(key)
}