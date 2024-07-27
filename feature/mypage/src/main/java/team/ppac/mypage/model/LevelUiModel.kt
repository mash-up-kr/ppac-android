package team.ppac.mypage.model

data class LevelUiModel(
    val myPageLevel: MyPageLevel = MyPageLevel.LEVEL1,
    val memeCount: Int = 0,
) {
    internal fun isMaxLevel() =
        (this.myPageLevel.levelCount == MyPageLevel.LEVEL4.levelCount && this.memeCount >= MAX_MEME_COUNT)

    companion object {
        const val MAX_MEME_COUNT = 20
    }
}