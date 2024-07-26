package team.ppac.detail.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class DetailSideEffect : UiSideEffect {
    data object RunRisingEffect : DetailSideEffect()
    data object NavigateToBackEffect : DetailSideEffect()
    data object CopyClipBoard : DetailSideEffect()
    data class ShareLink(val memeId: String) : DetailSideEffect()
}