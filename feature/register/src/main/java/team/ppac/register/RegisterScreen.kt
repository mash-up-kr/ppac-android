package team.ppac.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.register.mvi.RegisterUiState


@Composable
internal fun RegisterScreen(
    uiState: RegisterUiState,
) {
    FarmemeScaffold(
        modifier = Modifier.fillMaxSize(),
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
    )
}