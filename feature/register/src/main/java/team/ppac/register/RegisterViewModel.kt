package team.ppac.register

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableSet
import team.ppac.common.android.base.BaseViewModel
import team.ppac.domain.usecase.GetRecommendKeywordsUseCase
import team.ppac.domain.usecase.UploadMemeUseCase
import team.ppac.errorhandling.FarmemeNetworkException
import team.ppac.errorhandling.FarmemeNetworkException.Companion.UNKNOWN_ERROR
import team.ppac.register.model.RegisterCategoryUiModel
import team.ppac.register.mvi.RegisterIntent
import team.ppac.register.mvi.RegisterSideEffect
import team.ppac.register.mvi.RegisterUiState
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecommendKeywordsUseCase: GetRecommendKeywordsUseCase,
    private val uploadMemeUseCase: UploadMemeUseCase
) : BaseViewModel<RegisterUiState, RegisterSideEffect, RegisterIntent>(savedStateHandle) {

    init {
        getCategories()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): RegisterUiState {
        return RegisterUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {
        println(">> $throwable")
        if (throwable is FarmemeNetworkException) {
            if (throwable.code == UNKNOWN_ERROR) {
                showSnackbar(message = "밈 등록에 실패했어요")
            } else {
                reduce {
                    copy(isError = true)
                }
            }
        } else {
            showSnackbar(message = "밈 등록에 실패했어요")
        }
    }

    private fun getCategories() {
        launch {
            val registerCategories = getRecommendKeywordsUseCase().map { recommendKeyword ->
                RegisterCategoryUiModel(
                    category = recommendKeyword.category,
                    keywords = recommendKeyword.keywords.toImmutableList(),
                )
            }.toImmutableList()
            reduce {
                copy(
                    registerCategories = registerCategories,
                    isLoading = false,
                    isError = false,
                )
            }
        }
    }

    override suspend fun handleIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.SetImageFromGallery -> {
                reduce {
                    copy(imageUri = intent.uri)
                }
            }

            is RegisterIntent.InputSource -> {
                reduce {
                    copy(source = intent.source)
                }
            }

            is RegisterIntent.InputTitle -> {
                reduce {
                    copy(title = intent.title)
                }
            }

            is RegisterIntent.OnKeywordClick -> {
                if (currentState.selectedKeywords.contains(intent.keyword)) {
                    reduce {
                        copy(selectedKeywords = (currentState.selectedKeywords - intent.keyword).toImmutableSet())
                    }
                } else {
                    if (currentState.selectedKeywords.size < 6) {
                        reduce {
                            copy(selectedKeywords = (currentState.selectedKeywords + intent.keyword).toImmutableSet())
                        }
                    } else {
                        showSnackbar(message = "최대 개수를 초과했어요")
                    }
                }
            }

            RegisterIntent.ClickRegister -> {
                val isUploadSuccess = uploadMemeUseCase(
                    keywordIds = currentState.selectedKeywords.map { it.id },
                    memeTitle = currentState.title,
                    memeSource = currentState.source,
                    memeImageUri = currentState.imageUri
                )
                if (isUploadSuccess) {
                    reduce {
                        copy(uploadMemeResultDialogVisible = true)
                    }
                } else {
                    showSnackbar(message = "밈 등록에 실패했어요")
                }
            }

            RegisterIntent.OnRetry -> {
                getCategories()
            }
        }
    }
}