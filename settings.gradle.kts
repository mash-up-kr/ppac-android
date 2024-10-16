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
include(":core:error-handling")
include(":core:datastore")
include(":core:analytics")
include(":core:crashlytics")
include(":feature:sample")
include(":feature:onboard")
include(":feature:recommendation")
include(":feature:search")
include(":feature:mypage")
include(":feature:detail")
include(":feature:setting")
include(":feature:splash")
include(":feature:register")
include(":feature:keyword-collection")
