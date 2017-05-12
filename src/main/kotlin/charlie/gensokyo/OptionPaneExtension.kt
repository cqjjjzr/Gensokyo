package charlie.gensokyo

import java.awt.Component
import javax.swing.Icon
import javax.swing.JOptionPane
import javax.swing.UIManager

class InternalMessageBox(private val type: Int) {
    operator fun invoke(message: String,
                                title: String = UIManager.getString("OptionPane.messageDialogTitle"),
                                icon: Icon? = null,
                                parentComponent: Component? = null) {
        JOptionPane.showMessageDialog(parentComponent, message, title, type, icon)
    }
}

@JvmField val plainMessageBox =    InternalMessageBox(JOptionPane.PLAIN_MESSAGE)
@JvmField val infoMessageBox =     InternalMessageBox(JOptionPane.INFORMATION_MESSAGE)
@JvmField val warnMessageBox =     InternalMessageBox(JOptionPane.WARNING_MESSAGE)
@JvmField val errorMessageBox =    InternalMessageBox(JOptionPane.ERROR_MESSAGE)
@JvmField val questionMessageBox = InternalMessageBox(JOptionPane.QUESTION_MESSAGE)

class InternalInputBox(private val type: Int) {
    operator fun invoke(message: String,
                        title: String,
                        initialValue: String,
                        selectionValues: Array<String>,
                        icon: Icon?,
                        parentComponent: Component?) {
        JOptionPane.showInputDialog(parentComponent, message, title, type, icon, selectionValues, initialValue)
    }
}

@JvmField val plainInputBox =    InternalInputBox(JOptionPane.PLAIN_MESSAGE)
@JvmField val infoInputBox =     InternalInputBox(JOptionPane.INFORMATION_MESSAGE)
@JvmField val warnInputBox =     InternalInputBox(JOptionPane.WARNING_MESSAGE)
@JvmField val errorInputBox =    InternalInputBox(JOptionPane.ERROR_MESSAGE)
@JvmField val questionInputBox = InternalInputBox(JOptionPane.QUESTION_MESSAGE)