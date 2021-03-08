import com.products.buildsrc.Apps
import com.products.buildsrc.Apps.COMPILE_SDK
import com.products.buildsrc.Apps.androidTestInstrumentation
import com.products.buildsrc.Libs.CORE
import com.products.buildsrc.Libs.CORE_HILT
import com.products.buildsrc.Libs.HILT_ANDROID_COMPILER
import com.products.buildsrc.Libs.HILT_COMPILER
import com.products.buildsrc.TestLibs.ARCH_CORE
import com.products.buildsrc.TestLibs.FRAGMENT_TEST
import com.products.buildsrc.TestLibs.JUNIT_LIB
import com.products.buildsrc.TestLibs.MOCK
import com.products.buildsrc.TestLibs.MOCK_WEB_SERVER
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
        minSdkVersion(Apps.MIN_SDK)
        targetSdkVersion(Apps.TARGET_SDK)
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation(project(":lib:network"))
    implementation(project(":lib:model"))
    implementation(CORE)
    implementation(CORE_HILT)
    kapt(HILT_COMPILER)
    kapt(HILT_ANDROID_COMPILER)

    testImplementation(JUNIT_LIB)
    testImplementation(MOCK)
    testImplementation(ARCH_CORE)
    testImplementation(RULES)
    testImplementation(RUNNER)
    testImplementation(FRAGMENT_TEST)
    testImplementation(MOCK_WEB_SERVER)
    testImplementation(project(TEST_UTILS))
}
