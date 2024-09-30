package team.ppac.register.mvi

import team.ppac.common.android.base.UiIntent

sealed interface RegisterIntent : UiIntent {
    data class SetImageFromGallery(val uri: String) : RegisterIntent
}