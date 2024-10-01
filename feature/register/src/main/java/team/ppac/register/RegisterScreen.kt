package team.ppac.register

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.register.component.RegisterButton
import team.ppac.register.component.RegisterCategoryContent
import team.ppac.register.component.RegisterImageArea
import team.ppac.register.component.RegisterInputArea
import team.ppac.register.component.RegisterKeywordHeader
import team.ppac.register.component.UploadMemeResultDialog
import team.ppac.register.mvi.RegisterIntent
import team.ppac.register.mvi.RegisterUiState

@Composable
internal fun RegisterScreen(
    uiState: RegisterUiState,
    navigateToBack: () -> Unit,
    onIntent: (RegisterIntent) -> Unit,
) {
    val imagePicker =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                onIntent(RegisterIntent.SetImageFromGallery(uri.toString()))
            }
        }
    FarmemeScaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            Column {
                FarmemeBackToolBar(
                    title = "밈 등록",
                    onBackIconClick = navigateToBack,
                )
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = FarmemeTheme.backgroundColor.assistive
                )
            }
        },
        bottomBar = {
            if (uiState.isError.not()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    0f to FarmemeTheme.backgroundColor.white.copy(alpha = 0f),
                                    1f to FarmemeTheme.backgroundColor.white.copy(alpha = 1f),
                                )
                            )
                    )
                    RegisterButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 36.dp),
                        text = "등록하기",
                        enabled = true,
                        onClick = {
                            onIntent(RegisterIntent.ClickRegister)
                        },
                    )
                }
            }
        }
    ) {
        if (uiState.isError) {
            FarmemeErrorScreen(
                modifier = Modifier.fillMaxSize(),
                title = "정보를 불러오지 못 했어요.\n 새로고침 해주세요.",
                onRetryClick = {
                    onIntent(RegisterIntent.OnRetry)
                }
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    RegisterImageArea(
                        loadImage = { imagePicker.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                        imageUri = uiState.imageUri,
                    )
                }
                item {
                    RegisterInputArea(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        title = uiState.title,
                        onTitleChanged = { onIntent(RegisterIntent.InputTitle(it)) },
                        source = uiState.source,
                        onSourceChanged = { onIntent(RegisterIntent.InputSource(it)) },
                    )
                }
                item {
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = FarmemeTheme.skeletonColor.primary,
                        thickness = 10.dp,
                    )
                }
                item {
                    RegisterKeywordHeader(modifier = Modifier.padding(horizontal = 20.dp))
                }
                items(items = uiState.registerCategories) { registerCategory ->
                    RegisterCategoryContent(
                        uiModel = registerCategory,
                        selectedKeywords = uiState.selectedKeywords.toImmutableList(),
                        onKeywordClick = {
                            onIntent(RegisterIntent.OnKeywordClick(it))
                        }
                    )
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .height(120.dp)
                            .imePadding()
                    )
                }
            }
        }
    }
    if (uiState.uploadMemeResultDialogVisible) {
        UploadMemeResultDialog(
            onConfirmClick = {
                navigateToBack()
            },
            onDismiss = {
                navigateToBack()
            }
        )
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(
        uiState = RegisterUiState.INITIAL_STATE,
        navigateToBack = {},
        onIntent = {},
    )
}