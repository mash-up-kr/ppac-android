package team.ppac.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun DetailRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                else -> {}
            }
        }
    }

    DetailScreen(
        modifier = modifier,
        uiState = uiState
    )
}