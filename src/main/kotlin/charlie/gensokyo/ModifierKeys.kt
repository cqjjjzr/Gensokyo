package charlie.gensokyo

import java.awt.Event

class ModifierKeys(private val modifiers: Int) {
    fun isShiftPressed() = (modifiers and Event.SHIFT_MASK) != 0
    fun isCtrlPressed() = (modifiers and Event.CTRL_MASK) != 0
    fun isMetaPressed() = (modifiers and Event.META_MASK) != 0
    fun isAltPressed() = (modifiers and Event.ALT_MASK) != 0
}