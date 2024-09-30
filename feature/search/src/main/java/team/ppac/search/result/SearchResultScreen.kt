package team.ppac.search.result

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeSearchToolbar
import team.ppac.search.result.mvi.SearchResultUiState

@Composable
internal fun SearchResultScreen(
    modifier: Modifier = Modifier,
    uiState: SearchResultUiState,
    onQueryChange: (String) -> Unit,
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            FarmemeSearchToolbar(
                text = uiState.query,
                onTextChanged = onQueryChange
            )
        }
    ) {

    }
}