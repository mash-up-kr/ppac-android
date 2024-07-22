package team.ppac.common.kotlin.extension

fun String.truncateDisplayedString(maxLength: Int): String {
    return if (this.length > maxLength) {
        this.substring(0, maxLength - 1)
    } else {
        this
    }
}