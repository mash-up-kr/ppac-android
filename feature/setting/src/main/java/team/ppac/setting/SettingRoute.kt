package team.ppac.setting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.action.SettingsAction
import team.ppac.analytics.type.ScreenType
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.util.ComposableLifecycle
import team.ppac.common.android.util.checkUpdate
import team.ppac.setting.mvi.SettingIntent
import team.ppac.setting.mvi.SettingSideEffect

@Composable
internal fun SettingRoute(
    analyticsHelper: AnalyticsHelper,
    viewModel: SettingViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.intent(SettingIntent.InitView(context))
    }

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                analyticsHelper.reportScreen(ScreenType.SETTINGS)
            }

            else -> {}
        }
    }

    LaunchedEffect(key1 = viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                SettingSideEffect.NavigateToBack -> navigateToBack()
                SettingSideEffect.NavigateToPrivacyPolicy -> navigateToPrivacyPolicy()
                SettingSideEffect.UpdateApp -> {
                    analyticsHelper.reportAction(
                        action = SettingsAction.CLICK_APP_UPDATE,
                        screen = ScreenType.SETTINGS
                    )

                    // TODO(ze-zeh) 여기에 자겁하시면 됩니다~~
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.intent(SettingIntent.CheckNewAppVersionAvailable(context.checkUpdate()))
    }

    BaseComposable(viewModel = viewModel) { uiState ->
        SettingScreen(
            uiState = uiState,
            navigateToBack = { viewModel.intent(SettingIntent.ClickBackButton) },
            navigateToPrivacyPolicy = { viewModel.intent(SettingIntent.ClickPrivacyPolicy) },
            onAppUpdateClick = { viewModel.intent(SettingIntent.ClickAppUpdateButton(context)) }
        )
    }
}
