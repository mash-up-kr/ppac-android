package team.ppac.detail.mvi

import team.ppac.common.android.base.UiIntent

sealed class DetailIntent : UiIntent {
    data class ClickFarmemeButton(
        val isSavedMeme: Boolean,
    ) : DetailIntent()

    data object ClickFunnyButton : DetailIntent()

    data object ClickBackButton : DetailIntent()
}