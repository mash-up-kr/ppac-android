package team.ppac.detail.mvi

import team.ppac.common.android.base.UiIntent

sealed interface DetailIntent : UiIntent {
    data object ClickFunnyButton : DetailIntent

    data object ClickBackButton : DetailIntent

    data object ClickRetryButton : DetailIntent

    data object ClickHashtags : DetailIntent

    sealed interface ClickBottomButton : DetailIntent {
        data object Copy : ClickBottomButton
        data class Farmeme(val isSavedMeme: Boolean) : ClickBottomButton
        data class Share(val memeId: String) : ClickBottomButton
    }
}