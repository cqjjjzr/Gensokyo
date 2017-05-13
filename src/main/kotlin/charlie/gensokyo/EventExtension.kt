package charlie.gensokyo

import java.awt.Event
import java.awt.event.ActionEvent
import javax.swing.AbstractButton

fun AbstractButton.listenAction(block: () -> Unit) {
    addActionListener {
        block()
    }
}

fun AbstractButton.listenActionWithEvent(block: (event: ActionEvent) -> Unit) {
    addActionListener(block)
}

fun AbstractButton.listenActionWithEvent(block: (source: Any, id: Int?, timestamp: Long?, modifiers: ModifierKeys) -> Unit) {
    addActionListener {
        block(it.source, it.id, it.`when`, ModifierKeys(it.modifiers))
    }
}

class ModifierKeys(private val modifiers: Int) {
    fun isShiftPressed() = (modifiers and Event.SHIFT_MASK) != 0
    fun isCtrlPressed() = (modifiers and Event.CTRL_MASK) != 0
    fun isMetaPressed() = (modifiers and Event.META_MASK) != 0
    fun isAltPressed() = (modifiers and Event.ALT_MASK) != 0
}