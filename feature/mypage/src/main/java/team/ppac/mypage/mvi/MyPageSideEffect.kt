package team.ppac.mypage.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class MyPageSideEffect : UiSideEffect {
    data class NavigateToDetail(
        val memeId: String,
    ) : MyPageSideEffect()
    data object NavigateToSetting : MyPageSideEffect()
}