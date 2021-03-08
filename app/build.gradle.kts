import com.products.buildsrc.Apps
import com.products.buildsrc.Apps.APP_ID
import com.products.buildsrc.Apps.COMPILE_SDK
import com.products.buildsrc.Apps.MIN_SDK
import com.products.buildsrc.Apps.TARGET_SDK
import com.products.buildsrc.BuildType
import com.products.buildsrc.BuildTypeDebug
import com.products.buildsrc.BuildTypeRelease
import com.products.buildsrc.Libs
import com.products.buildsrc.TestLibs

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {

    compileSdkVersion(COMPILE_SDK)
    defaultConfig {
        applicationId(APP_ID)
        minSdkVersion(MIN_SDK)
        targetSdkVersion(TARGET_SDK)
        buildToolsVersion(buildToolsVersion)
        versionCode = versionCode
        versionName = versionName

        testInstrumentationRunner = Apps.androidTestInstrumentation
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lintOptions {
        baselineFile = File("lint.xml")
        textReport = true
        textOutput("stdout")
        isAbortOnError = false
        isCheckDependencies = true
        isIgnoreTestSources = true
        xmlOutput = File("${project.rootDir}/build/reports/lint-results-all.xml")
        htmlOutput = File("${project.rootDir}/build/reports/lint-results-all.html")
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":commons:resources"))
    implementation(project(":feature:catalog"))
    implementation(Libs.KOTLIN_JDK)
    implementation(Libs.CORE)
    implementation(Libs.CORE_HILT)
    implementation(Libs.NAVIGATION_UI_LIB)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.FRAGMENT_NAVIGATION)
    implementation(Libs.NAVIGATION_LAYOUT)
    implementation(Libs.CONSTRAINT_LAYOUT)

    implementation(Libs.CORE)

    kapt(Libs.HILT_COMPILER)
    kapt(Libs.HILT_ANDROID_COMPILER)

    testImplementation(TestLibs.JUNIT_LIB)
    androidTestImplementation(TestLibs.ESPRESSO_LIB)
    androidTestImplementation(TestLibs.JUNIT_EXT)
}
