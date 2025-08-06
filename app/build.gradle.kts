import java.util.Properties

plugins {
    id("com.android.application")
}

val localProperties = Properties()

val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use {
        localProperties.load(it)
    }
}

android {
    namespace = "com.example.watchit"
    compileSdk = 36

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.watchit"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "SERVER_PROTO", "\"${localProperties["SERVER_PROTO"]}\"")
        buildConfigField("String", "SERVER_ADDR", "\"${localProperties["SERVER_ADDR"]}\"")
        buildConfigField("String", "SERVER_PORT", "\"${localProperties["SERVER_PORT"]}\"")
        buildConfigField("String", "PROXY_ADDRESS", "\"${localProperties["PROXY_ADDRESS"]}\"")
        buildConfigField("String", "PROXY_PORT", "\"${localProperties["PROXY_PORT"]}\"")
        buildConfigField("String", "PROXY_USERNAME", "\"${localProperties["PROXY_USERNAME"]}\"")
        buildConfigField("String", "PROXY_PASSWORD", "\"${localProperties["PROXY_PASSWORD"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.navigation:navigation-fragment:2.9.3")
    implementation("androidx.navigation:navigation-ui:2.9.3")
    implementation("com.caverock:androidsvg:1.4")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("androidx.room:room-compiler:2.7.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
}