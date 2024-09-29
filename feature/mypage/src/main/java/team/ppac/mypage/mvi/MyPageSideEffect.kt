package team.ppac.mypage.mvi

import team.ppac.common.android.base.UiSideEffect
import team.ppac.domain.model.Meme

sealed class MyPageSideEffect : UiSideEffect {
    data class NavigateToDetail(val contentType: String, val memeId: String) : MyPageSideEffect()
    data object NavigateToSetting : MyPageSideEffect()
    data object NavigateToRegister : MyPageSideEffect()
    data class ShowLevelUpSnackBar(val level: Int) : MyPageSideEffect()
    data class LogClickCopy(val meme: Meme) : MyPageSideEffect()
}