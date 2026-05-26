plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)

}

android {
    namespace = "com.example.chatbot"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }


    buildFeatures {
        compose = true
        buildConfig = true

    }
    defaultConfig {

        buildConfigField(
            "String",
            "GEMINI_API_KEY",
            "\"${project.properties["GEMINI_API_KEY"]}\""
        )

        applicationId = "com.example.chatbot"

        minSdk = 29
        targetSdk = 36
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

    sourceSets {
        getByName("debug") {
            kotlin.directories.add("build/generated/ksp/debug/kotlin")
            kotlin.directories.add("build/generated/ksp/debug/java")
        }
        getByName("release") {
            kotlin.directories.add("build/generated/ksp/release/kotlin")
            kotlin.directories.add("build/generated/ksp/release/java")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.11.0")
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
    implementation("com.google.android.gms:play-services-ads:24.4.0")
    implementation("androidx.datastore:datastore-preferences:1.2.1")
    implementation("androidx.compose.material:material-icons-extended")
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    ksp(libs.androidx.room.compiler)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}