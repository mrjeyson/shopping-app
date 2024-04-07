plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.gmsService)
}

android {
    namespace = "com.jsoft.shoppingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jsoft.shoppingapp"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Dagger Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.hilt.compiler)


    //Navigation component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    //loading button
    implementation(libs.loading.button.android)

    //Glide
    implementation(libs.glide)

    //circular image
    implementation(libs.circleimageview)

    //viewpager2 indicatior
    implementation(libs.viewpagerindicator)

    //stepView
    implementation(libs.stepview)

    //Android Ktx
    implementation(libs.androidx.navigation.fragment.ktx)

    //Firebase
    implementation(libs.firebase.auth)

    //Coroutines with firebase
    implementation(libs.kotlinx.coroutines.play.services)

}