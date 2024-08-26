package team.ppac.analytics

import com.google.firebase.analytics.ParametersBuilder
import team.ppac.analytics.type.ActionType
import team.ppac.analytics.type.ScreenType

interface AnalyticsHelper {

    fun reportScreen(screen: ScreenType)
    fun reportAction(
        action: ActionType,
        screen: ScreenType,
        params: ParametersBuilder.() -> Unit = {}
    )
}