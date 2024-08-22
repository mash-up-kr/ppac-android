package team.ppac.splash.mvi

import team.ppac.common.android.base.UiIntent

sealed interface SplashIntent : UiIntent {
    object ClickDialogConfirm : SplashIntent
}