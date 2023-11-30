plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")

    //id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.todolistapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.todolistapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui:1.6.0-beta01")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3: 1.1.2")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")
    implementation("androidx.compose.runtime:runtime:1.5.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    implementation("androidx.compose.foundation:foundation-android:1.5.4")
    //implementation ("androidx.compose.material:material")


    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.4")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.1")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Retrofit for network requests (Giphy API)
    //implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Giphy API integration (if we use the official SDK)
    //implementation("com.giphy.sdk:ui:2.1.0")

    //Hilt
    //implementation("com.google.dagger:hilt-android:2.48.1")
    //kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    // implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    // kapt ("androidx.hilt:hilt-compiler:1.1.0")
    // implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //Koin
    implementation("io.insert-koin:koin-android:3.5.2-RC1")
    implementation ("io.insert-koin:koin-androidx-compose:3.5.2-RC1")
    // Java Compatibility
    implementation ("io.insert-koin:koin-android-compat:3.5.2-RC1")
    // Jetpack WorkManager
    implementation ("io.insert-koin:koin-androidx-workmanager:3.5.2-RC1")
    // Navigation Graph
    implementation ("io.insert-koin:koin-androidx-navigation:3.5.2-RC1")


}
kapt {
    correctErrorTypes = true
}
//hilt {
  //  enableAggregatingTask = true
//}