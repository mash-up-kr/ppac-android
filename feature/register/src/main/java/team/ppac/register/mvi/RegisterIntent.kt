package team.ppac.register.mvi

import team.ppac.common.android.base.UiIntent
import team.ppac.domain.model.Keyword

sealed interface RegisterIntent : UiIntent {
    data class SetImageFromGallery(val uri: String) : RegisterIntent
    data class InputTitle(val title: String) : RegisterIntent
    data class InputSource(val source: String) : RegisterIntent
    data class OnKeywordClick(val keyword: Keyword) : RegisterIntent
    data object ClickRegister : RegisterIntent
    data object OnRetry : RegisterIntent
}