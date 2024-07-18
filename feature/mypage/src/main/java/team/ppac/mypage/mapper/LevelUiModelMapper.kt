package team.ppac.mypage.mapper

import team.ppac.domain.model.User
import team.ppac.mypage.model.LevelUiModel
import team.ppac.mypage.model.MyPageLevel

const val DEFAULT_MEME_COUNT = 0

internal fun User.toLevelUiModel(): LevelUiModel = LevelUiModel(
    myPageLevel = when (levelCount) {
        1 -> MyPageLevel.LEVEL1
        2 -> MyPageLevel.LEVEL2
        3 -> MyPageLevel.LEVEL3
        4 -> MyPageLevel.LEVEL4
        else -> MyPageLevel.LEVEL1
    },
    memeCount = when (levelCount) {
        1 -> watchCount
        2 -> reactionCount
        3 -> shareCount
        4 -> saveCount
        else -> DEFAULT_MEME_COUNT
    },
)