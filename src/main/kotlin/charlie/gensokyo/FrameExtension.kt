@file:JvmName("Gensokyo")
@file:JvmMultifileClass

package charlie.gensokyo

import java.awt.Container
import java.awt.Frame
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.WindowConstants.*

fun Frame.titleWithI18n(titleKey: String) {
    title = I18nManager.getString(titleKey)
}

inline fun Container.container(constraints: Any? = null,
                               index: Int = -1,
                               init: JComponent.() -> Unit) {
    JLabel().let {
        it.init()
        add(it, constraints, index)
    }
}

val JFrame.exitOnClose: Unit get() {
    defaultCloseOperation = EXIT_ON_CLOSE
}

val JFrame.disposeOnClose: Unit get() {
    defaultCloseOperation = DISPOSE_ON_CLOSE
}

val JFrame.doNothingOnClose: Unit get() {
    defaultCloseOperation = DO_NOTHING_ON_CLOSE
}

val JFrame.hideOnClose: Unit get() {
    defaultCloseOperation = HIDE_ON_CLOSE
}