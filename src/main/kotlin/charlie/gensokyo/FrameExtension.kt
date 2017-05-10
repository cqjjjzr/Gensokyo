package charlie.gensokyo

import java.awt.Container
import java.awt.Frame
import javax.swing.*
import javax.swing.WindowConstants.*

fun Frame.titleWithI18n(titleKey: String) {
    title = I18nManager.getString(titleKey)
}

fun Container.button(text: String = "",
           jButton: JButton = JButton(),
           constraints: Any? = null,
           index: Int = -1,
           init: JButton.() -> Unit): JButton {
    return createButton(text, jButton, init).apply {
        this@button.beforeAddingComponent(this)
        this@button.add(this, constraints, index)
        this@button.afterAddingComponent(this)
    }
}

fun Container.container(constraints: Any? = null,
                        index: Int = -1,
                        init: JComponent.() -> Unit) {
    JLabel().apply {
        this@container.beforeAddingComponent(this)
        init()
        this@container.add(this, constraints, index)
        this@container.afterAddingComponent(this)
    }
}

internal fun Container.beforeAddingComponent(comp: JComponent) {  }

internal fun Container.afterAddingComponent(comp: JComponent) {  }

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