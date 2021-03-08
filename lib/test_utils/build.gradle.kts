import com.products.buildsrc.Apps.COMPILE_SDK
import com.products.buildsrc.Libs.CORE
import com.products.buildsrc.Libs.CORE_HILT
import com.products.buildsrc.Libs.FRAGMENT_KTX
import com.products.buildsrc.Libs.HILT_ANDROID_COMPILER
import com.products.buildsrc.Libs.HILT_COMPILER
import com.products.buildsrc.Libs.KOTLIN_JDK
import com.products.buildsrc.Libs.NAVIGATION_UI_LIB
import com.products.buildsrc.TestLibs.ARCH_CORE
import com.products.buildsrc.TestLibs.ASSERT_J
import com.products.buildsrc.TestLibs.CORE_TEST
import com.products.buildsrc.TestLibs.ESPRESSO_LIB
import com.products.buildsrc.TestLibs.FRAGMENT_TEST
import com.products.buildsrc.TestLibs.JUNIT_EXT
import com.products.buildsrc.TestLibs.JUNIT_LIB
import com.products.buildsrc.TestLibs.MOCK
import com.products.buildsrc.TestLibs.MOCK_WEB_SERVER
import com.products.buildsrc.TestLibs.ROBO_ELECTRIC
import com.products.buildsrc.TestLibs.RULES
import com.products.buildsrc.TestLibs.RUNNER

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(COMPILE_SDK)

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
    implementation(NAVIGATION_UI_LIB)
    implementation(FRAGMENT_KTX)
    implementation(ASSERT_J)
    implementation(ROBO_ELECTRIC)
    implementation(CORE)
    implementation(ARCH_CORE)
    implementation(RULES)
    implementation(RUNNER)
    implementation(JUNIT_LIB)
    implementation(MOCK_WEB_SERVER)
    implementation(MOCK)
    implementation(CORE_TEST)

    implementation(KOTLIN_JDK)
    implementation(CORE_HILT)

    kapt(HILT_COMPILER)
    kapt(HILT_ANDROID_COMPILER)

    testImplementation(JUNIT_LIB)
    testImplementation(MOCK)
    testImplementation(ASSERT_J)
    testImplementation(ROBO_ELECTRIC)
    testImplementation(CORE)
    testImplementation(ARCH_CORE)
    testImplementation(RULES)
    testImplementation(RUNNER)
    testImplementation(FRAGMENT_TEST)
    testImplementation(JUNIT_EXT)
    testImplementation(MOCK_WEB_SERVER)

    androidTestImplementation(ESPRESSO_LIB)
    androidTestImplementation(RUNNER)
    androidTestImplementation(RULES)
    androidTestImplementation(JUNIT_LIB)
    androidTestImplementation(FRAGMENT_TEST)
}
