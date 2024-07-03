package team.ppac.search.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.search.detail.mvi.SearchDetailUiState

@Composable
internal fun SearchDetailScreen(
    modifier: Modifier = Modifier,
    uiState: SearchDetailUiState,
    navigateBack: () -> Unit,
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = rememberScaffoldState(),
        topBar = {
            FarmemeBackToolBar(
                title = uiState.memeCategory,
                onClickBackIcon = navigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }

    }

}