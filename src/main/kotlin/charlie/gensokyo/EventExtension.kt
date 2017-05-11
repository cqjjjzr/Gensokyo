package charlie.gensokyo

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