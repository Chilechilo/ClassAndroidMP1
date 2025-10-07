plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.jorgeromo.androidClassMp1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jorgeromo.androidClassMp1"
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

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    // NUEVA CONFIGURACIÓN AGREGADA
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // BOM de Compose (actualizado)
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))

    // Artefactos Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.foundation:foundation")

    // Icons extendidos
    implementation("androidx.compose.material:material-icons-extended")

    // Navegación
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ViewModel para Compose (versión más reciente)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

    // Retrofit y OkHttp (Networking)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.6.4")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // Splash
    implementation("androidx.activity:activity-compose:1.9.2")

    // Lottie Animation
    val lottieVersion = "6.6.6"
    implementation("com.airbnb.android:lottie-compose:$lottieVersion")

    // Qr Function
    implementation("androidx.camera:camera-core:1.3.0")
    implementation("androidx.camera:camera-camera2:1.3.0")
    implementation("androidx.camera:camera-lifecycle:1.3.0")
    implementation("androidx.camera:camera-view:1.3.0")
    implementation("com.google.mlkit:barcode-scanning:17.0.3")
    implementation("com.google.mlkit:vision-common:17.3.0")

    // Coil para imágenes
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Google Location Services (nuevo)
    implementation("com.google.android.gms:play-services-location:21.3.0")

    // Coroutines (nuevo)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
