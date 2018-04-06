@file:JvmName("Gensokyo")
@file:JvmMultifileClass

package charlie.gensokyo

import javax.swing.*

inline fun RootPaneContainer.menuBar(menuBar: JMenuBar = JMenuBar(), block: JMenuBar.() -> Unit): JMenuBar {
    menuBar.block()
    rootPane.jMenuBar = menuBar
    return menuBar
}

inline fun JMenuBar.subMenu(text: String, block: JMenu.() -> Unit) = subMenu(JMenu(text), block)
inline fun JMenuBar.subMenu(menu: JMenu, block: JMenu.() -> Unit): JMenu {
    menu.block()
    add(menu)
    return menu
}

inline fun JMenu.subMenu(text: String, block: JMenu.() -> Unit) = subMenu(JMenu(text), block)
inline fun JMenu.subMenu(menu: JMenu, block: JMenu.() -> Unit): JMenu {
    menu.block()
    add(menu)
    return menu
}

inline fun JMenu.item(text: String, block: JMenuItem.() -> Unit) = item(JMenuItem(text), block)
inline fun JMenu.item(menuItem: JMenuItem, block: JMenuItem.() -> Unit): JMenuItem {
    menuItem.block()
    add(menuItem)
    return menuItem
}

val JMenu.separator get() = JPopupMenu.Separator().also { popupMenu.add(it) }
