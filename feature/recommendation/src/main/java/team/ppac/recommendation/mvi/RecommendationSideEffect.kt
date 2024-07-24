package team.ppac.recommendation.mvi

import team.ppac.common.android.base.UiSideEffect

sealed interface RecommendationSideEffect : UiSideEffect {
    data object RunRisingEffect : RecommendationSideEffect
    data class CopyClipBoard(val memeIndex: Int) : RecommendationSideEffect
    data class ShareLink(val memeId: String) : RecommendationSideEffect
}