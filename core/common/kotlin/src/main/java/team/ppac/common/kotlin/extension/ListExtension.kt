package team.ppac.common.kotlin.extension

fun List<String>.truncateDisplayedList(maxSize: Int): List<String> {
    return if (this.size > maxSize) {
        this.subList(0, maxSize)
    } else {
        this
    }
}