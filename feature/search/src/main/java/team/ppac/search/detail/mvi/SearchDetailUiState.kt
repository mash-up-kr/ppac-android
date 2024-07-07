package team.ppac.search.detail.mvi

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.ppac.common.android.base.UiState
import team.ppac.search.detail.model.SearchResultUiModel

data class SearchDetailUiState(
    val memeCategory: String,
    val searchResults: ImmutableList<SearchResultUiModel>,  // TODO(JaesungLeee) : 페이징 넣을 떄 수정 예정
) : UiState {

    companion object {
        val INITIAL_STATE = SearchDetailUiState(
            memeCategory = "",
            searchResults = persistentListOf(
                SearchResultUiModel(
                    memeId = "asdf",
                    imageUrl = "",
                    memeTitle = "asdfasdf",
                    lolCount = 0
                ),
                SearchResultUiModel(
                    memeId = "asdf",
                    imageUrl = "",
                    memeTitle = "asdfasdf",
                    lolCount = 10
                ),
                SearchResultUiModel(
                    memeId = "asdf",
                    imageUrl = "",
                    memeTitle = "asdfasdfasdf",
                    lolCount = 10
                ),
                SearchResultUiModel(
                    memeId = "asdf",
                    imageUrl = "",
                    memeTitle = "asdfasdf",
                    lolCount = 10
                ),
                SearchResultUiModel(
                    memeId = "asdf",
                    imageUrl = "",
                    memeTitle = "asdfasdfasdf",
                    lolCount = 10
                ),
                SearchResultUiModel(
                    memeId = "asdf",
                    imageUrl = "",
                    memeTitle = "asdfasdf",
                    lolCount = 10
                ),
                SearchResultUiModel(
                    memeId = "asdf",
                    imageUrl = "",
                    memeTitle = "asdfasdfasdf",
                    lolCount = 10
                )
            ),
        )
    }
}
