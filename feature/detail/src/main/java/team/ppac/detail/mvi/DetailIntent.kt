package team.ppac.detail.mvi

import team.ppac.common.android.base.UiIntent

sealed interface DetailIntent : UiIntent {
    data object ClickFunnyButton : DetailIntent

    data object ClickBackButton : DetailIntent

    sealed interface ClickButtonButton : DetailIntent {
        data object Copy : ClickButtonButton
        data class Farmeme(val isSavedMeme: Boolean) : ClickButtonButton
        data class Share(val memeId: String) : ClickButtonButton
    }
}