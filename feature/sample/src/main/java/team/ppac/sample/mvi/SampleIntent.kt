package team.ppac.sample.mvi

import team.ppac.common.android.base.UiIntent

sealed class SampleIntent : UiIntent {
    data object ClickGetImagesButton : SampleIntent()
}