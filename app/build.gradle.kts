plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
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
    val activityVersion = "1.8.2"
    val fragmentVersion = "1.6.2"
    val retrofitVersion = "2.9.0"
    val okhttpinterceptorVersion = "4.12.0"
    val moshiKotlinVersion = "1.15.0"
    val navVersion = "2.7.7"
    val lottieVersion = "6.3.0"
    val hiltVersion = "2.48"
    val mockkVersion = "1.13.9"
    val coroutinesTest = "1.7.3"
    val glideVersion = "4.16.0"
    val hamcrestVersion = "2.2"
    val roomVersion = "2.6.1"
    val coreTestingVersion = "2.1.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //activity-ktx
    implementation("androidx.activity:activity-ktx:$activityVersion")
    //fragment-ktx
    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")
    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpinterceptorVersion")

    implementation("com.airbnb.android:lottie:$lottieVersion")

    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    implementation("com.squareup.moshi:moshi-kotlin:$moshiKotlinVersion")

    implementation("com.github.bumptech.glide:glide:$glideVersion")

    //room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.hamcrest:hamcrest:$hamcrestVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTest")
    testImplementation("androidx.arch.core:core-testing:$coreTestingVersion")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}