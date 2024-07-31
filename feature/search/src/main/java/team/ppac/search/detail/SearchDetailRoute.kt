package team.ppac.search.detail

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import team.ppac.common.android.base.BaseComposable
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.search.detail.mvi.SearchDetailIntent
import team.ppac.search.detail.mvi.SearchDetailSideEffect

@Composable
internal fun SearchDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToMemeDetail: (String) -> Unit,
) {
    BaseComposable(viewModel = viewModel) { uiState ->
        LaunchedEffect(key1 = viewModel) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SearchDetailSideEffect.NavigateToMemeDetail ->
                        navigateToMemeDetail(sideEffect.memeId)
                }
            }
        }

        SearchDetailScreen(
            modifier = modifier,
            uiState = uiState,
            onBackClick = navigateBack,
            onMemeClick = { viewModel.intent(SearchDetailIntent.ClickMeme(it)) },
            onCopyClick = {}
        )
    }
}