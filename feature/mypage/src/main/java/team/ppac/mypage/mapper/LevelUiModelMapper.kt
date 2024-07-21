package team.ppac.mypage.mapper

import team.ppac.domain.model.User
import team.ppac.mypage.model.LevelUiModel
import team.ppac.mypage.model.MyPageLevel

const val DEFAULT_MEME_COUNT = 0

internal fun User.toLevelUiModel(): LevelUiModel = LevelUiModel(
    myPageLevel = MyPageLevel.valueOf(level = levelCount),
    memeCount = when (levelCount) {
        MyPageLevel.LEVEL1.levelCount -> watchCount
        MyPageLevel.LEVEL2.levelCount -> reactionCount
        MyPageLevel.LEVEL3.levelCount -> shareCount
        MyPageLevel.LEVEL4.levelCount -> saveCount
        else -> DEFAULT_MEME_COUNT
    },
)