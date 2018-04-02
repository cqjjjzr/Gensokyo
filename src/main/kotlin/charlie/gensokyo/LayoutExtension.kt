@file:JvmName("Gensokyo")
@file:JvmMultifileClass

package charlie.gensokyo

import java.awt.BorderLayout
import java.awt.Container
import java.awt.GridLayout
import java.awt.IllegalComponentStateException
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JLabel

inline val Container.abstractLayout: Unit get() { layout = null }
inline val JFrame.abstractLayout: Unit get() = contentPane.abstractLayout

inline val Container.borderLayout: Unit get() { layout = BorderLayout() }
inline val JFrame.borderLayout: Unit get() = contentPane.abstractLayout

inline fun Container.gridLayout(block: GridLayoutHelper.() -> Unit) {
    layout = BorderLayout()
    removeAll()
    add(GridLayoutHelper().apply(block))
}

inline fun JFrame.gridLayout(block: GridLayoutHelper.() -> Unit) = contentPane.gridLayout(block)

class GridLayoutHelper: JComponent() {
    private var rowIndex = -1
    private var columnIndex = 0
    private var firstRowInserted = false

    fun row(block: GridLayoutHelper.() -> Unit) {
        if (firstRowInserted)
            (layout as GridLayout).rows++
        columnIndex = 0
        rowIndex++
        block()
        firstRowInserted = true
    }

    val gap: Unit get() {
        JLabel().apply {
            this@GridLayoutHelper.beforeAddingComponent(this)
            add(this)
        }
    }

    internal fun ensureColumns() {
        if (++columnIndex > (layout as GridLayout).columns) {
            if (!firstRowInserted)
                (layout as GridLayout).columns++
            else throw IllegalComponentStateException("columns of row under the first row is more than the first row. add gaps. ")
        }
    }

    private fun beforeAddingComponent(comp: JComponent) = ensureColumns()

    init {
        layout = GridLayout()
    }
}