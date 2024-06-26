pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PPAC"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":core:common:android")
include(":core:common:kotlin")
include(":core:remote")
include(":core:navigator")
include(":core:local")
include(":feature:detail")
include(":core:error-handling")
include(":feature:sample")
include(":feature:onboard")
include(":feature:recommendation")
include(":feature:search")
include(":feature:mypage")
include(":feature:detail")
