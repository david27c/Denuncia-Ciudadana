plugins {
    alias(libs.plugins.androidApplication)
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
    // Android UI y Componentes
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.preference)

    // Firebase BOM (para manejar versiones automáticamente)
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))

    // Firebase APIs
    implementation(libs.firebaseAuth)
    implementation(libs.firebaseFirestore)
    implementation(libs.firebaseDatabase)
    implementation(libs.firebaseMessaging)
    implementation(libs.firebaseStorage)
    implementation(libs.firebaseUiStorage)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.glideCompiler)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.extJunit)
    androidTestImplementation(libs.espressoCore)
}