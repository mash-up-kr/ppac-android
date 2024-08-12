package team.ppac.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

internal class AnalyticsHelperImpl @Inject constructor(
    private val analytics: FirebaseAnalytics
) : AnalyticsHelper {

    override fun screen() {

    }
}