package team.ppac.analytics.type

enum class ScreenType(private val tag: String) {
    RECOMMENDATION("recommend"),
    SEARCH("search"),
    SEARCH_DETAIL("search_detail"),
    MEME_DETAIL("meme_detail"),
    MY_PAGE("my_page"),
    SETTINGS("settings");

    companion object {
        fun getTag(type: ScreenType): String = type.tag
    }
}
