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
    navigateToMemeDetail: (String) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                else -> {}
            }
        }
    }

        SearchDetailScreen(
            modifier = modifier,
            uiState = uiState,
            onBackClick = navigateBack,
            onMemeClick = navigateToMemeDetail,
            onCopyClick = {
                viewModel.showSnackbar(
                    message = "이미지를 클립보드에 복사했어요",
                    icon = {
                        FarmemeIcon.Copy(Modifier.size(20.dp))
                    }
                )
            }
        )
    }
}