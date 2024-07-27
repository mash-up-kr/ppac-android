package team.ppac.mypage.mvi

import team.ppac.common.android.base.UiIntent

sealed class MyPageIntent : UiIntent {
    data object ClickSettingButton : MyPageIntent()
    data class ClickRecentMemeItem(val memeId: String) : MyPageIntent()
    data class ClickSavedMemeItem(val memeId: String) : MyPageIntent()
    data object InitView : MyPageIntent()
    data object RefreshData : MyPageIntent()
    data object ClickRetryButton : MyPageIntent()
}