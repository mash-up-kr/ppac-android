package team.ppac.sample.mvi

import team.ppac.base.UiIntent

sealed class SampleIntent : UiIntent {
    data object ClickGetImagesButton : SampleIntent()
}