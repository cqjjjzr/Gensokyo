package charlie.gensokyo

import java.awt.Container
import javax.swing.*

fun frame(title: String = "",
          show: Boolean = false,
          jFrame: JFrame = JFrame(),
          init: JFrame.() -> Unit): JFrame {
    return jFrame.apply {
        this.title = title
        init()
        if (show) isVisible = true
    }
}

inline fun createButton(text: String = "",
                 jButton: JButton = JButton(text),
                 init: JButton.() -> Unit): JButton {
    return jButton.apply {
        this.text = text
        init()
    }
}

fun Container.button(text: String = "",
                     jButton: JButton = JButton(),
                     constraints: Any? = null,
                     index: Int = -1,
                     init: JButton.() -> Unit): JButton {
    return createButton(text, jButton, init).let {
        beforeAddingComponent(it)
        add(it, constraints, index)
        afterAddingComponent(it)
        it
    }
}

inline fun createLabel(text: String = "",
                       jLabel: JLabel = JLabel(text),
                       init: JLabel.() -> Unit): JLabel {
    return jLabel.apply {
        this.text = text
        init()
    }
}

fun Container.label(text: String = "",
                    jLabel: JLabel = JLabel(text),
                    constraints: Any? = null,
                    index: Int = -1,
                    init: JLabel.() -> Unit = {}): JLabel {
    return createLabel(text, jLabel, init).let {
        beforeAddingComponent(it)
        add(it, constraints, index)
        afterAddingComponent(it)
        it
    }
}

inline fun createTextArea(text: String = "",
                   jTextArea: JTextArea = JTextArea(text),
                   init: JTextArea.() -> Unit): JTextArea {
    return jTextArea.apply {
        this.text = text
        init()
    }
}

fun Container.textArea(text: String = "",
                       jTextArea: JTextArea = JTextArea(text),
                       constraints: Any? = null,
                       index: Int = -1,
                       init: JTextArea.() -> Unit = {}): JTextArea {
    return createTextArea(text, jTextArea, init).let {
        beforeAddingComponent(it)
        add(it, constraints, index)
        afterAddingComponent(it)
        it
    }
}

fun Container.scroll(jComponent: JComponent,
                     jScrollPane: JScrollPane = JScrollPane(jComponent),
                     constraints: Any? = null,
                     index: Int = -1,
                     init: JScrollPane.() -> Unit = {}): JScrollPane {
    return jScrollPane.let {
        it.init()
        jScrollPane.setViewportView(jComponent)
        beforeAddingComponent(it)
        add(it, constraints, index)
        afterAddingComponent(it)
        it
    }
}