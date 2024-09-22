package team.ppac.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.register.mvi.RegisterUiState

@Composable
internal fun RegisterScreen(
    uiState: RegisterUiState,
    navigateToBack: () -> Unit,
) {
    FarmemeScaffold(
        modifier = Modifier.fillMaxSize(),
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
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                Text(text = "RegisterScreen")
            }
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