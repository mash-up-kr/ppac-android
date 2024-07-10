package team.ppac.mypage.mvi

import team.ppac.common.android.base.UiSideEffect

sealed class MyPageSideEffect : UiSideEffect {
    data object NavigateToDetail : MyPageSideEffect()
    data object NavigateToSetting : MyPageSideEffect()
}