plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "team.ppac.local"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(libs.datastore)
    implementation(libs.hilt.android)
    implementation(libs.room.core)
    implementation(libs.datastore)
    implementation(libs.kotlin.serialization)
    ksp(libs.hilt.compiler)
    implementation(libs.timber)
}