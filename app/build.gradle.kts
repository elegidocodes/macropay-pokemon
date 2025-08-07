plugins {
    alias(libs.plugins.android.application)
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.elegidocodes.demo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.elegidocodes.demo"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        dataBinding = true
    }
}

dependencies {

    // Default libraries
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // 👇🏼 ~~~~~ Custom libraries ~~~~~ 👇🏼

    // Retrofit for HTTP client
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.scalars)

    // RxJava for Pagination
    implementation(libs.paging.runtime)
    implementation(libs.paging.rxjava3)
    implementation(libs.adapter.rxjava3)

    // Hilt Dagger for dependency injection
    implementation(libs.hilt.android)
    implementation(libs.legacy.support.v4)
    annotationProcessor(libs.hilt.compiler)

    // ViewModel and LiveData for lifecycle
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    // Swipe to refresh for pull to refresh
    implementation(libs.swiperefreshlayout)

    // Glide for image loading
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // Glide for more custom image loading
    implementation(libs.picasso)

    // Firebase Authentification
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}