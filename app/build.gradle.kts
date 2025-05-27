plugins {
    id("com.android.application")
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.denunciaciudadana"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.denunciaciudadana"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.preference)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:33.14.0"))
    implementation(platform("com.google.firebase:firebase-database"))
    implementation(platform("com.google.firebase:firebase-messaging"))
    implementation(platform("com.google.firebase:firebase-storage"))
    implementation(platform("com.firebaseui:firebase-ui-storage:9.0.0"))
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.viewpager2:viewpager2:1.0.0'
        // Otras dependencias necesarias
}