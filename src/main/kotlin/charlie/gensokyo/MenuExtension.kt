package charlie.gensokyo

import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

fun JFrame.menuBar(jMenuBar: JMenuBar = JMenuBar(),
                   block: JMenuBar.() -> Unit) {
    jMenuBar.block()
    this.jMenuBar = jMenuBar
}

fun JMenuBar.subMenu(text: String,
                     jMenu: JMenu = JMenu(text),
                     block: JMenu.() -> Unit) {
    jMenu.block()
    add(jMenu)
}

fun JMenu.subMenu(text: String,
                     jMenu: JMenu = JMenu(text),
                     block: JMenu.() -> Unit) {
    jMenu.block()
    add(jMenu)
}

fun JMenu.item(text: String,
               jMenuItem: JMenuItem = JMenuItem(text),
               block: JMenuItem.() -> Unit) {
    jMenuItem.block()
    add(jMenuItem)
}

val JMenu.separator: Unit get() = addSeparator()