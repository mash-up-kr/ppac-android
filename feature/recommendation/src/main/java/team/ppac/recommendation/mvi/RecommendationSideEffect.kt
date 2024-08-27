package team.ppac.recommendation.mvi

import team.ppac.common.android.base.UiSideEffect
import team.ppac.domain.model.Meme

sealed interface RecommendationSideEffect : UiSideEffect {
    data class RunRisingEffect(val meme: Meme) : RecommendationSideEffect
    data class CopyClipBoard(val selectedMemeIndex: Int) : RecommendationSideEffect
    data class ShareLink(val memeId: String) : RecommendationSideEffect
    data object LogHashTagsClicked : RecommendationSideEffect
    data class LogSaveMeme(val meme: Meme) : RecommendationSideEffect
    data class LogSaveMemeCancel(val meme: Meme) : RecommendationSideEffect
}