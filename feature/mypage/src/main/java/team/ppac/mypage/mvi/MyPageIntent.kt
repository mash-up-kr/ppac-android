package team.ppac.mypage.mvi

import team.ppac.analytics.action.MY_PAGE_RECENT_MEME
import team.ppac.analytics.action.MY_PAGE_SAVED_MEME
import team.ppac.analytics.action.MY_PAGE_UPLOADED_MEME
import team.ppac.common.android.base.UiIntent
import team.ppac.domain.model.Meme

sealed class MyPageIntent : UiIntent {
    data object ClickSettingButton : MyPageIntent()
    data class ClickRecentMemeItem(
        val contentType: String = MY_PAGE_RECENT_MEME,
        val memeId: String
    ) : MyPageIntent()

    data class ClickSavedMemeItem(
        val contentType: String = MY_PAGE_SAVED_MEME,
        val memeId: String
    ) : MyPageIntent()

    data class ClickUploadedMemeItem(
        val contentType: String = MY_PAGE_UPLOADED_MEME,
        val memeId: String
    ) : MyPageIntent()

    data object ClickUpload : MyPageIntent()
    data object ClickRetryButton : MyPageIntent()
    data object InitView : MyPageIntent()
    data object RefreshData : MyPageIntent()
    data class ClickCopy(val meme: Meme) : MyPageIntent()
    data class ClickMemesTab(val currentTabType: MyPageTabType) : MyPageIntent()
}