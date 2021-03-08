package com.products.buildsrc

object Apps {

    const val APP_ID = "com.products.buildsrc"
    const val COMPILE_SDK = 30
    const val MIN_SDK = 21
    const val TARGET_SDK = 30
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val buildToolsVersion = "30.0.2"
    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
}

object Versions {
    const val KOTLIN_STANDARD_VERSION = "1.4.20"
    const val APP_COMPAT_VERSION = "1.2.0"
    const val CORE_VERSION = "1.3.2"
    const val MATERIAL_VERSION = "1.2.1"
    const val CONSTRAINT_LAYOUT_VERSION = "2.0.4"
    const val NAVIGATION_VERSION = "2.3.2"
    const val FRAGMENT_NAVIGATION_VERSION = "1.3.0"
    const val ESPRESSO_VERSION = "3.3.0"
    const val LIFE_CYCLE_VERSION = "2.2.0"
    const val FRAGMENT_KTX_VERSION = "1.2.5"
    const val RETROFIT_VERSION = "2.9.0"
    const val RECYCLER_VIEW_VERSION = "1.1.0"
    const val HILT = "2.33-beta"
    const val COROUTINES_VERSION = "1.4.2"
    const val COIL_VERSION = "1.1.1"
    const val HILT_LIFE_CYCLE = "1.0.0-alpha03"

    const val ARCH_CORE_VERSION = "2.1.0"
    const val ROBO_ELECTRIC_VERSION = "4.4"
    const val MOCK_VERSION = "1.10.2"
    const val ASSERT_VERSION = "3.17.2"
    const val MOCK_WEB_SERVER_VERSION = "4.9.0"
    const val COROUTINES_TEST_VERSION = "1.4.3"
    const val DATABINDING_VERSION = "3.1.4"
    const val LOGIN_INTERCEPTOR_VERSION = "4.9.0"

    /* test */
    const val JUNIT_VERSION = "4.12"
    const val JUNIT_EXT_VERSION = "1.1.2"
    const val FRAGMENT_TEST_VERSION = "1.2.5"
    const val TEST_VERSION = "1.3.0"
    const val LOTTIE_VERSION = "2.8.0"
    const val MOCKITO_INLINE_VERSION = "2.23.4"
    const val MOCKITO_KOTLIN_VERSION = "2.0.0"
}

object Libs {
    // Plugins
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    const val KOTLIN_JDK =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN_STANDARD_VERSION}"

    // AndroidX and UI Library
    const val CORE = "androidx.core:core-ktx:${Versions.CORE_VERSION}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
    const val LOTTIE = "com.airbnb.android:lottie:${Versions.LOTTIE_VERSION}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW_VERSION}"

    // Databinding library
    const val DATABINDING_COMPILER =
        "com.android.databinding:compiler:${Versions.DATABINDING_VERSION}"

    // Navigation Library
    const val NAVIGATION_LAYOUT =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_VERSION}"
    const val NAVIGATION_UI_LIB =
        "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_VERSION}"
    const val FRAGMENT_NAVIGATION = "androidx.fragment:fragment:${Versions.FRAGMENT_NAVIGATION_VERSION}"

    // Life cycle libraries
    const val LIFE_CYCLE_EXTENSION =
        "androidx.lifecycle:lifecycle-extensions:${Versions.LIFE_CYCLE_VERSION}"
    const val LIFE_CYCLE_VIEW_MODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFE_CYCLE_VERSION}"

    // Network and sever communication
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX_VERSION}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val RETROFIT_CONVERTER =
        "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"
    const val LOGIN_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Versions.LOGIN_INTERCEPTOR_VERSION}"
    const val MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_VERSION}"


    // Android hilt libraries
    const val CORE_HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${Versions.HILT_LIFE_CYCLE}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    // Coroutines libraries
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"

    // Coil
    const val COIL_LIBRARY = "io.coil-kt:coil:${Versions.COIL_VERSION}"

}

object TestLibs {
    const val TEST_UTILS = ":lib:test_utils"
    const val JUNIT_LIB = "junit:junit:${Versions.JUNIT_VERSION}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT_VERSION}"
    const val ESPRESSO_LIB = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Versions.MOCKITO_INLINE_VERSION}"
    const val MOCKITO_KOTLIN_LIBRARY =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.MOCKITO_KOTLIN_VERSION}"

    const val RUNNER = "androidx.test:runner:${Versions.TEST_VERSION}"
    const val RULES = "androidx.test:rules:${Versions.TEST_VERSION}"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${Versions.FRAGMENT_TEST_VERSION}"

    const val MOCK = "io.mockk:mockk:${Versions.MOCK_VERSION}"
    const val ASSERT_J = "org.assertj:assertj-core:${Versions.ASSERT_VERSION}"
    const val ROBO_ELECTRIC = "org.robolectric:robolectric:${Versions.ROBO_ELECTRIC_VERSION}"
    const val CORE_TEST = "androidx.test:core:${Versions.TEST_VERSION}"
    const val ARCH_CORE = "androidx.arch.core:core-testing:${Versions.ARCH_CORE_VERSION}"
    const val MOCK_WEB_SERVER =
        "com.squareup.okhttp3:mockwebserver:${Versions.MOCK_WEB_SERVER_VERSION}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_TEST_VERSION}"
}