// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.diffplug.spotless") version "5.7.0"
}

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.33-beta")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.3")
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.4.31")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {

    project.pluginManager.apply("com.diffplug.spotless")

    spotless {
        java {
            // This is required otherwise the code in android modules isn't picked up by spotless.
            target("**/*.java")
            targetExclude("**/build/**/*.java")
            trimTrailingWhitespace()
            removeUnusedImports()
            googleJavaFormat()
        }

        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint("0.39.0").userData(hashMapOf(
                "indent_size" to "4",
                "android" to "true",
                "max_line_length" to "200"
            ))
        }

        kotlinGradle {
            target("**/*.gradle.kts")
            ktlint("0.39.0").userData(hashMapOf(
                "indent_size" to "4",
                "android" to "true",
                "max_line_length" to "200"
            ))
        }

        format("misc") {
            target("**/.gitignore", "**/*.gradle", "**/*.md", "**/*.sh", "**/*.yml")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}