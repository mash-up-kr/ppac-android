package team.ppac.recommendation.mvi

import team.ppac.common.android.base.UiIntent
import team.ppac.domain.model.Meme

sealed interface RecommendationIntent : UiIntent {
    data class MovePage(val meme: Meme) : RecommendationIntent

    sealed interface ClickButton : RecommendationIntent {
        data class LoL(val meme: Meme) : ClickButton
        data class Copy(val meme: Meme) : ClickButton
        data class Share(val meme: Meme) : ClickButton
        data class BookMark(val meme: Meme) : ClickButton
    }
}