plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.plannerproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.plannerproject"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {
    val nav_version = "2.7.4"



    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation ("androidx.compose.material3:material3:1.2.0-alpha11")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("androidx.navigation:navigation-compose:$nav_version")

    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("io.coil-kt:coil-compose:2.2.1")

    // Room Local DB
    val room_version = "2.4.3"

    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    implementation ("androidx.compose.runtime:runtime-livedata:1.2.1")
    annotationProcessor ("androidx.room:room-compiler:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")


    //dagger
    implementation ("com.google.dagger:dagger:2.20")
    kapt ("com.google.dagger:dagger-compiler:2.20")

    implementation ("androidx.compose.material3:material3:1.2.0-alpha11")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}