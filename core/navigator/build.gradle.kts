plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "team.ppac.navigator"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {
    implementation(project(":core:common:kotlin"))

    implementation(libs.timber)
}