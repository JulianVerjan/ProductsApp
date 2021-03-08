import com.products.buildsrc.Apps.COMPILE_SDK
import com.products.buildsrc.Apps.MIN_SDK
import com.products.buildsrc.Apps.TARGET_SDK
import com.products.buildsrc.Apps.androidTestInstrumentation
import com.products.buildsrc.Libs.APP_COMPAT
import com.products.buildsrc.Libs.COIL_LIBRARY
import com.products.buildsrc.Libs.CONSTRAINT_LAYOUT
import com.products.buildsrc.Libs.CORE
import com.products.buildsrc.Libs.CORE_HILT
import com.products.buildsrc.Libs.COROUTINES_ANDROID
import com.products.buildsrc.Libs.COROUTINES_CORE
import com.products.buildsrc.Libs.DATABINDING_COMPILER
import com.products.buildsrc.Libs.FRAGMENT_KTX
import com.products.buildsrc.Libs.FRAGMENT_NAVIGATION
import com.products.buildsrc.Libs.HILT_ANDROID_COMPILER
import com.products.buildsrc.Libs.HILT_COMPILER
import com.products.buildsrc.Libs.KOTLIN_JDK
import com.products.buildsrc.Libs.LOTTIE
import com.products.buildsrc.Libs.MATERIAL
import com.products.buildsrc.Libs.NAVIGATION_LAYOUT
import com.products.buildsrc.Libs.NAVIGATION_UI_LIB
import com.products.buildsrc.Libs.RECYCLER_VIEW
import com.products.buildsrc.TestLibs.ARCH_CORE
import com.products.buildsrc.TestLibs.COROUTINES_TEST
import com.products.buildsrc.TestLibs.JUNIT_LIB
import com.products.buildsrc.TestLibs.MOCK
import com.products.buildsrc.TestLibs.MOCKITO_INLINE
import com.products.buildsrc.TestLibs.MOCKITO_KOTLIN_LIBRARY

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(COMPILE_SDK)

    defaultConfig {
        minSdkVersion(MIN_SDK)
        targetSdkVersion(TARGET_SDK)
        testInstrumentationRunner = androidTestInstrumentation
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(KOTLIN_JDK)
    api(project(":lib:data"))
    api(project(":lib:network"))
    implementation(project(":commons:resources"))
    implementation(project(":commons:base"))
    implementation(project(":lib:model"))
    implementation(APP_COMPAT)
    implementation(RECYCLER_VIEW)
    implementation(MATERIAL)
    implementation(CORE)
    implementation(NAVIGATION_UI_LIB)
    implementation(COROUTINES_CORE)
    implementation(COROUTINES_ANDROID)
    implementation(CONSTRAINT_LAYOUT)
    implementation(LOTTIE)
    implementation(FRAGMENT_KTX)
    implementation(FRAGMENT_NAVIGATION)
    implementation(NAVIGATION_LAYOUT)
    implementation(MATERIAL)
    implementation(MOCKITO_INLINE)
    implementation(MOCKITO_KOTLIN_LIBRARY)
    implementation(CORE_HILT)
    implementation(COIL_LIBRARY)

    kapt(HILT_COMPILER)
    kapt(HILT_ANDROID_COMPILER)
    kapt(DATABINDING_COMPILER)
    testImplementation(JUNIT_LIB)
    testImplementation(MOCK)
    testImplementation(ARCH_CORE)
    testImplementation("org.mockito:mockito-inline:3.8.0")
    testImplementation(COROUTINES_TEST)
}
