package team.ppac.search.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.common.android.component.error.FarmemeErrorScreen
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.list.FarmemeListHeader
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.tabbar.TabBarHeight
import team.ppac.designsystem.component.textfield.FarmemeSearchTextField
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.util.extension.ColumnSpacerByWeightWithMinHeight
import team.ppac.search.main.component.HotKeywordContent
import team.ppac.search.main.component.MemeCategoryContent
import team.ppac.search.main.component.SearchLoadingContent
import team.ppac.search.main.mvi.SearchUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onQueryChanged: (String) -> Unit,
    onInputDone: (String) -> Unit,
    onKeywordClick: (String, String) -> Unit,
    onHotKeywordMemeClick: (String) -> Unit,
    onRetryClick: () -> Unit,
) {
    FarmemeScaffold(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        isIncludeHorizontalPadding = false,
    ) {
        when {
            uiState.isError -> {
                FarmemeErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = TabBarHeight),
                    title = "정보를 불러오지 못 했어요.\n새로고침 해주세요.",
                    onRetryClick = onRetryClick
                )
            }

            uiState.isLoading -> {
                SearchLoadingContent(
                    modifier = Modifier.fillMaxSize(),
                    uiState = uiState
                )
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = TabBarHeight),
                ) {
                    stickyHeader {
                        Column(
                            modifier = Modifier.background(FarmemeTheme.backgroundColor.white)
                        ) {
                            FarmemeSearchTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 20.dp,
                                        vertical = 6.dp
                                    ),
                                text = uiState.query,
                                onTextChanged = onQueryChanged,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = { onInputDone(uiState.query) }
                                ),
                            )
                            Divider(
                                modifier = Modifier.fillMaxWidth(),
                                color = FarmemeTheme.backgroundColor.assistive,
                            )
                        }
                    }
                    item {
                        FarmemeListHeader(
                            title = "두둥! 요즘 핫한 #키워드",
                            leadingIcon = { FarmemeIcon.Special(Modifier.size(20.dp)) }
                        )
                    }
                    item {
                        HotKeywordContent(
                            modifier = Modifier,
                            keywords = uiState.hotKeywords,
                            onHotKeywordMemeClick = onHotKeywordMemeClick
                        )
                    }
                    item { Spacer(modifier = Modifier.size(40.dp)) }
                    item {
                        FarmemeListHeader(
                            title = "무슨 밈 찾아?",
                            leadingIcon = { FarmemeIcon.Category(Modifier.size(20.dp)) }
                        )
                    }
                    items(items = uiState.memeCategories) { memeCategory ->
                        MemeCategoryContent(
                            uiModel = memeCategory,
                            onKeywordClick = { keyword ->
                                onKeywordClick(memeCategory.category, keyword)
                            }
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                    }
                    item {
                        Column {
                            ColumnSpacerByWeightWithMinHeight(
                                weight = 1f,
                                minHeight = 50.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    SearchScreen(
        uiState = SearchUiState.INITIAL_STATE,
        onQueryChanged = {},
        onInputDone = {},
        onKeywordClick = { _, _ -> },
        onHotKeywordMemeClick = {},
        onRetryClick = {}
    )
}