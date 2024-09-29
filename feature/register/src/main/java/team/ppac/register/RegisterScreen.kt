package team.ppac.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.register.component.RegisterButton
import team.ppac.register.component.RegisterCategoryContent
import team.ppac.register.component.RegisterImageArea
import team.ppac.register.component.RegisterInputArea
import team.ppac.register.component.RegisterKeywordHeader
import team.ppac.register.mvi.RegisterUiState

@Composable
internal fun RegisterScreen(
    uiState: RegisterUiState,
    navigateToBack: () -> Unit,
) {
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
            RegisterButton(
                modifier = Modifier.padding(bottom = 36.dp),
                text = "등록하기",
                enabled = true,
                onClick = {},
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                RegisterImageArea(
                    hasImage = false,
                )
            }
            item { RegisterInputArea() }
            item {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = FarmemeTheme.skeletonColor.primary,
                    thickness = 10.dp,
                )
            }
            item { RegisterKeywordHeader() }
            items(items = uiState.registerCategories) { registerCategory ->
                RegisterCategoryContent(
                    uiModel = registerCategory,
                )
            }
            item { Spacer(modifier = Modifier.height(120.dp)) }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(
        uiState = RegisterUiState.INITIAL_STATE,
        navigateToBack = {},
    )
}