import java.io.FileInputStream
import java.util.Properties

var properties = Properties()
properties.load(FileInputStream("local.properties"))

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.mapsplotform)
}

android {
    namespace = "com.example.bookki"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
//        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.bookki"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        resValue("String", "GOOGLE_MAPS_API_KEY", properties.getProperty("GOOGLE_MAPS_API_KEY"))
//        buildConfigField("String", "LIBRARY_API_KEY", properties.getProperty("LIBRARY_API_KEY"))
//        buildConfigField("String", "GOOGLE_MAPS_API_KEY", properties.getProperty("GOOGLE_MAPS_API_KEY"))
    }

    buildTypes {
        release {
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit.coroutines.adapter)
    implementation(libs.retrofit)
    implementation(libs.androidx.livedata)
    implementation(libs.androidx.viewmodel)
    implementation(libs.converter.gson)
    implementation(libs.play.services.maps)
    implementation(libs.glide)

    annotationProcessor(libs.glide.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}