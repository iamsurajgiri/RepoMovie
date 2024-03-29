plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "kutumb.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.11.0")
    testApi("junit:junit:4.13.2")
    androidTestApi("androidx.test.ext:junit:1.1.5")
    androidTestApi("androidx.test.espresso:espresso-core:3.5.1")


    //Koin
    api("io.insert-koin:koin-android:3.5.3")

    //Retrofit
    api ("com.squareup.retrofit2:retrofit:2.9.0")
    api ("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    //Coroutines
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //Glide
    api ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    //Lifecycle for view model
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    //Navigation
    api("androidx.navigation:navigation-fragment-ktx:2.6.0")
    api("androidx.navigation:navigation-ui-ktx:2.6.0")

}