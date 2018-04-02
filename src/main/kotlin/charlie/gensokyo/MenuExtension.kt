@file:JvmName("Gensokyo")
@file:JvmMultifileClass

package charlie.gensokyo

import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

inline fun JFrame.menuBar(jMenuBar: JMenuBar = JMenuBar(),
                          block: JMenuBar.() -> Unit) {
    jMenuBar.block()
    this.jMenuBar = jMenuBar
}

inline fun JMenuBar.subMenu(text: String,
                            jMenu: JMenu = JMenu(text),
                            block: JMenu.() -> Unit) {
    jMenu.block()
    add(jMenu)
}

inline fun JMenu.subMenu(text: String,
                         jMenu: JMenu = JMenu(text),
                         block: JMenu.() -> Unit) {
    jMenu.block()
    add(jMenu)
}

inline fun JMenu.item(text: String,
                      jMenuItem: JMenuItem = JMenuItem(text),
                      block: JMenuItem.() -> Unit) {
    jMenuItem.block()
    add(jMenuItem)
}

val JMenu.separator: Unit get() = addSeparator()
