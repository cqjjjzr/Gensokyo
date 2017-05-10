package charlie.gensokyo

import java.awt.BorderLayout
import java.awt.Container
import java.awt.GridLayout
import java.awt.IllegalComponentStateException
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

val Container.abstractLayout: Unit get() { layout = null }
val JFrame.abstractLayout: Unit get() { contentPane.layout = null }

fun Container.gridLayout(block: GridLayoutHelper.() -> Unit) {
    layout = BorderLayout()
    removeAll()
    add(GridLayoutHelper().apply(block))
}

fun JFrame.gridLayout(block: GridLayoutHelper.() -> Unit) {
    contentPane.layout = BorderLayout()
    contentPane.removeAll()
    contentPane.add(GridLayoutHelper().apply(block))
}

class GridLayoutHelper: JPanel(GridLayout()) {
    private var rowIndex = -1
    private var columnIndex = 0
    private var firstRowInserted = false

    fun row(block: GridLayoutRow.() -> Unit) {
        if (firstRowInserted)
            (layout as GridLayout).rows++
        columnIndex = 0
        rowIndex++
        GridLayoutRow(this).block()
        firstRowInserted = true
    }

    val gap: Unit get() {
        ensureColumns()
        add(JLabel())
    }

    internal fun ensureColumns() {
        if (++columnIndex > (layout as GridLayout).columns) {
            if (!firstRowInserted)
                (layout as GridLayout).columns++
            else throw IllegalComponentStateException("columns of row under the first row is more than the first row. add gaps. ")
        }
    }
}

class GridLayoutRow(private val parent: GridLayoutHelper) {
    fun button(text: String = "",
               jButton: JButton = JButton(),
               init: JButton.() -> Unit): JButton {
        parent.ensureColumns()
        return createButton(text, jButton, init).apply { parent.add(this) }
    }
}