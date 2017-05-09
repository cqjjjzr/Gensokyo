package charlie.gensokyo

import javax.swing.JButton
import javax.swing.JFrame

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
                 jButton: JButton = JButton(),
                 init: JButton.() -> Unit): JButton {
    return jButton.apply {
        this.text = text
        init()
    }
}