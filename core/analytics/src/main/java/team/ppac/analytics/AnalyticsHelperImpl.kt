package team.ppac.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ParametersBuilder
import com.google.firebase.analytics.logEvent
import team.ppac.analytics.type.ActionType
import team.ppac.analytics.action.PAGE
import team.ppac.analytics.type.ScreenType
import javax.inject.Inject

internal class AnalyticsHelperImpl @Inject constructor(
    private val analytics: FirebaseAnalytics
) : AnalyticsHelper {

    override fun reportScreen(screen: ScreenType) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, ScreenType.getTag(screen))
        }
    }

    override fun reportAction(
        action: ActionType,
        screen: ScreenType,
        params: ParametersBuilder.() -> Unit
    ) {
        analytics.logEvent(action.getAction()) {
            param(PAGE, ScreenType.getTag(screen))
            params()
        }
    }
}