package team.ppac.designsystem.util.extension


internal class MultipleEventsCutter(
    private val debounceMillis: Long
) {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= debounceMillis) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}