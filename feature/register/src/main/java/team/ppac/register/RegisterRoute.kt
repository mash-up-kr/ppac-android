package team.ppac.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.common.android.base.BaseComposable
import team.ppac.common.android.util.ComposableLifecycle

@Composable
internal fun RegisterRoute(
//    analyticsHelper: AnalyticsHelper,
    viewModel: RegisterViewModel = hiltViewModel(),
//    navigateToBack: () -> Unit,
) {
    ComposableLifecycle { _, event ->
//        when (event) {
//            Lifecycle.Event.ON_START -> {
//                analyticsHelper.reportScreen(ScreenType.REGISTER)
//            }
//
//            else -> {}
//        }
    }

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
        )
    }
}