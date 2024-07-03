package team.ppac.search.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun SearchDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                else -> {}
            }
        }
    }
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    SearchDetailScreen(
        modifier = modifier,
        uiState = uiState,
        navigateBack = navigateBack
    )
}