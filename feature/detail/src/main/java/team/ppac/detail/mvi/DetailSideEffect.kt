package team.ppac.detail.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class DetailSideEffect : UiSideEffect {
    data object RunRisingEffect : DetailSideEffect()
}