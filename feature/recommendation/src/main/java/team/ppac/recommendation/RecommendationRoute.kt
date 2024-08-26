package team.ppac.recommendation

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.util.ComposableLifecycle

@Composable
internal fun RecommendationRoute(
    analyticsHelper: AnalyticsHelper,
) {
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                analyticsHelper.reportScreen(ScreenType.RECOMMENDATION)
            }

            else -> {}
        }
    }

    RecommendationScreen()
}