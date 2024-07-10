package team.ppac.mypage.mvi

import team.ppac.common.android.base.UiIntent

sealed class MyPageIntent : UiIntent {
    data object ClickSettingButton : MyPageIntent()
    data object ClickRecentMemeItem : MyPageIntent()
    data object ClickSavedMemeItem : MyPageIntent()
}