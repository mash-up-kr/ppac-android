package team.ppac.common.kotlin.model

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class ReactionState {
    private val _isFirstClickEvent = AtomicBoolean(true)
    private val _reactionCount = AtomicInteger(0)
    private val _isUpdating = AtomicBoolean(false)
    private var lastClickTime: Long = 0

    val reactionCount: Int
        get() = _reactionCount.get()

    val isUpdating: Boolean
        get() = _isUpdating.get()

    val isFirstClickEvent: Boolean
        get() = _isFirstClickEvent.get()

    fun setIsFirstClickEvent(value: Boolean) {
        _isFirstClickEvent.set(value)
    }

    fun addReactionCount(count: Int) {
        _reactionCount.addAndGet(count)
    }

    fun startUpdate() {
        _isUpdating.compareAndSet(false, true)
            .also { print("isRelease Setting = ${_isUpdating.get()}") }
    }

    fun endUpdate() {
        _isUpdating.set(false)
    }

    fun releaseState() {
        _reactionCount.set(0)
        _isFirstClickEvent.set(true)
    }

    fun isDoubleClickEvent(): Boolean {
        val currentClickTime: Long = System.currentTimeMillis()
        return if (currentClickTime - lastClickTime <= DOUBLE_CLICK_INTERVAL) {
            true
        } else {
            lastClickTime = System.currentTimeMillis()
            false
        }
    }

    companion object {
        private const val DOUBLE_CLICK_INTERVAL = 400
    }
}