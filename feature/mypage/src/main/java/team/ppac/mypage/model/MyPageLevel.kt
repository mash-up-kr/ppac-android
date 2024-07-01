package team.ppac.mypage.model

internal enum class MyPageLevel(
    val level: Int,
    val title: String,
    val stepTitle: String,
) {
    LEVEL1(
        level = 1,
        title = "LV.1 호기심 많은 밈린이",
        stepTitle = "밈 20번 보기",
    ),
    LEVEL2(
        level = 2,
        title = "LV.2 은은하게 밈친자",
        stepTitle = "ㅋㅋ 반응 20번 남기기",
    ),
    LEVEL3(
        level = 3,
        title = "LV.3 입담 좋은 밈수저",
        stepTitle = "밈 20번 공유하기",
    ),
    LEVEL4(
        level = 4,
        title = "LV.4 독보적인 밈천재",
        stepTitle = "밈 20번 저장하기",
    ),
}