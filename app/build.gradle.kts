plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    namespace = "com.luismiguel.movieschallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.luismiguel.movieschallenge"
        minSdk = 24
        targetSdk = 34
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
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "API_KEY","\"f46b58478f489737ad5a4651a4b25079\"")
        }
        getByName("debug"){
            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "API_KEY","\"f46b58478f489737ad5a4651a4b25079\"")
        }
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
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
    val activity_version = "1.8.2"
    val fragment_version = "1.5.5"
    val retrofit_version = "2.9.0"
    val okhttpinterceptor_version = "4.12.0"
    val moshi_kotlin_version = "1.15.0"
    val nav_version = "2.7.7"
    val lottie_version = "6.3.0"
    val hilt_version = "2.48"
    val mockk_version = "1.13.9"
    val coroutines_test = "1.7.3"
    val glide_version = "4.16.0"
    val hamcrest_version = "2.2"
    val room_version = "2.6.1"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //activity-ktx
    implementation("androidx.activity:activity-ktx:$activity_version")
    //fragment-ktx
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    //navigation
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit_version")

    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpinterceptor_version")

    implementation("com.airbnb.android:lottie:$lottie_version")

    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")

    implementation("com.squareup.moshi:moshi-kotlin:$moshi_kotlin_version")

    implementation("com.github.bumptech.glide:glide:$glide_version")

    //room
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    testImplementation("io.mockk:mockk:$mockk_version")
    testImplementation("org.hamcrest:hamcrest:$hamcrest_version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_test")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}