package charlie.gensokyo

import java.awt.Container
import java.awt.Frame
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.WindowConstants.*

fun Frame.titleWithI18n(titleKey: String) {
    title = I18nManager.getString(titleKey)
}

val Container.abstractLayout: Unit get() { layout = null }

fun Container.button(text: String = "",
           jButton: JButton = JButton(),
           constraints: Any? = null,
           index: Int = -1,
           init: JButton.() -> Unit): JButton {
    return createButton(text, jButton, init).apply { this@button.add(this, constraints, index) }
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