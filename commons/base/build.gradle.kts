import com.products.buildsrc.Apps.COMPILE_SDK
import com.products.buildsrc.Apps.MIN_SDK
import com.products.buildsrc.Apps.TARGET_SDK
import com.products.buildsrc.Apps.androidTestInstrumentation
import com.products.buildsrc.Libs.CORE
import com.products.buildsrc.Libs.CORE_HILT
import com.products.buildsrc.Libs.DATABINDING_COMPILER
import com.products.buildsrc.Libs.FRAGMENT_KTX
import com.products.buildsrc.Libs.HILT_ANDROID_COMPILER
import com.products.buildsrc.Libs.HILT_COMPILER
import com.products.buildsrc.Libs.KOTLIN_JDK
import com.products.buildsrc.Libs.LIFE_CYCLE_EXTENSION
import com.products.buildsrc.Libs.LIFE_CYCLE_VIEW_MODEL
import com.products.buildsrc.Libs.LOTTIE
import com.products.buildsrc.TestLibs.ARCH_CORE
import com.products.buildsrc.TestLibs.FRAGMENT_TEST
import com.products.buildsrc.TestLibs.JUNIT_LIB
import com.products.buildsrc.TestLibs.MOCK
import com.products.buildsrc.TestLibs.RULES
import com.products.buildsrc.TestLibs.RUNNER
import com.products.buildsrc.TestLibs.TEST_UTILS

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(COMPILE_SDK)

    defaultConfig {
        minSdkVersion(MIN_SDK)
        targetSdkVersion(TARGET_SDK)
        testInstrumentationRunner = androidTestInstrumentation
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
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

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(project(":commons:resources"))
    implementation(LIFE_CYCLE_EXTENSION)
    implementation(LIFE_CYCLE_VIEW_MODEL)
    implementation(CORE)
    implementation(FRAGMENT_KTX)
    implementation(LOTTIE)
    implementation(KOTLIN_JDK)
    implementation(CORE_HILT)

    kapt(HILT_COMPILER)
    kapt(HILT_ANDROID_COMPILER)
    kapt(DATABINDING_COMPILER)

    testImplementation(JUNIT_LIB)
    testImplementation(MOCK)
    testImplementation(ARCH_CORE)
    testImplementation(RULES)
    testImplementation(RUNNER)
    testImplementation(FRAGMENT_TEST)
    testImplementation(project(TEST_UTILS))
}
