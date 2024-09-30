package team.ppac.search.result

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.ppac.search.result.mvi.SearchResultUiState

@Composable
internal fun SearchResultScreen(
    modifier: Modifier = Modifier,
    uiState: SearchResultUiState,
) {
    Text(text = "검색 결과")
}