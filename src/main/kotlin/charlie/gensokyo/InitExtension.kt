@file:JvmName("Gensokyo")
@file:JvmMultifileClass

package charlie.gensokyo

import java.awt.Container
import javax.swing.*

inline fun frame(title: String = "",
                 show: Boolean = true,
                 jFrame: JFrame = JFrame(),
                 init: JFrame.() -> Unit) = jFrame.apply {
    this.title = title
    init()
    if (show) isVisible = true
}

inline fun createButton(text: String = "",
                        jButton: JButton = JButton(text),
                        init: JButton.() -> Unit) = jButton.apply {
    this.text = text
    init()
}

inline fun Container.button(text: String = "",
                            jButton: JButton = JButton(),
                            constraints: Any? = null,
                            index: Int = -1,
                            init: JButton.() -> Unit) = createButton(text, jButton, init).also {
    add(it, constraints, index)
}

inline fun createLabel(text: String = "",
                       label: JLabel = JLabel(text),
                       init: JLabel.() -> Unit) = label.apply {
    this.text = text
    init()
}

inline fun Container.label(text: String = "",
                           label: JLabel = JLabel(text),
                           constraints: Any? = null,
                           index: Int = -1,
                           init: JLabel.() -> Unit = {}) = createLabel(text, label, init).also {
    add(it, constraints, index)
}

inline fun createTextArea(text: String = "",
                          jTextArea: JTextArea = JTextArea(text),
                          init: JTextArea.() -> Unit) = jTextArea.apply {
    this.text = text
    init()
}

inline fun Container.textArea(text: String = "",
                              textArea: JTextArea = JTextArea(text),
                              constraints: Any? = null,
                              index: Int = -1,
                              init: JTextArea.() -> Unit = {}) = createTextArea(text, textArea, init).also {
    add(it, constraints, index)
}

inline fun Container.scroll(component: JComponent,
                            scrollPane: JScrollPane = JScrollPane(component),
                            constraints: Any? = null,
                            index: Int = -1,
                            init: JScrollPane.() -> Unit = {}) = scrollPane.also {
    it.init()
    scrollPane.setViewportView(component)
    add(it, constraints, index)
}
