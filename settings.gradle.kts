include(
        ":app",
        ":lib:network",
        ":lib:data",
        ":lib:model",
        ":lib:test_utils",
        ":commons:base",
        ":commons:resources",
        "feature:catalog"
)

rootProject.buildFileName = "build.gradle.kts"