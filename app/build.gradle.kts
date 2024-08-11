import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.services)
}

val keystorePropertiesFile = rootProject.file("farmeme-keystore.properties")
android {
    namespace = libs.versions.namespace.get()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "team.ppac"
        applicationIdSuffix = ".app"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "DEV_KEY", getLocalProperties("DEV_KEY"))
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))
            storeFile = file("farmeme-keystore.jks")
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storePassword = keystoreProperties.getProperty("storePassword")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:common:android"))
    implementation(project(":core:common:kotlin"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:error-handling"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigator"))
    implementation(project(":core:remote"))
    implementation(project(":core:local"))
    implementation(project(":core:datastore"))
    implementation(project(":feature:sample"))
    implementation(project(":feature:onboard"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:mypage"))
    implementation(project(":feature:recommendation"))
    implementation(project(":feature:search"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:splash"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    implementation(platform(libs.firebase.bom))

    implementation(libs.hilt.android)
    implementation(libs.af.android.sdk)
    kapt(libs.hilt.compiler)

    implementation(libs.timber)
}

fun getLocalProperties(key: String): String {
    val properties = Properties().apply {
        load(FileInputStream(rootProject.file("local.properties")))
    }
    return properties.getProperty(key)
}
