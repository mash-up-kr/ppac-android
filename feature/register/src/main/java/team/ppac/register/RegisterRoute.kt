package team.ppac.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.common.android.base.BaseComposable

@Composable
internal fun RegisterRoute(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
) {
    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
//                when (sideEffect) {
//
//                }
            }
        }

        RegisterScreen(
            uiState = uiState,
            navigateToBack = navigateToBack,
        )
    }
}