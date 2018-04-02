@file:JvmName("Gensokyo")
@file:JvmMultifileClass

package charlie.gensokyo

import java.awt.Component
import java.util.*
import javax.swing.Icon
import javax.swing.JFrame
import javax.swing.JOptionPane.*
import javax.swing.UIManager

class InternalMessageBox(private val type: Int) {
    operator fun invoke(message: String,
                        title: String = UIManager.getString("OptionPane.messageDialogTitle"),
                        icon: Icon? = null,
                        parentComponent: Component? = null) {
        showMessageDialog(parentComponent, message, title, type, icon)
    }
}

class JFrameInternalMessageBox(private val type: Int,
                               private val parentComponent: Component?) {
    operator fun invoke(message: String,
                        title: String = UIManager.getString("OptionPane.messageDialogTitle"),
                        icon: Icon? = null) {
        showMessageDialog(null, message, title, type, icon)
    }
}

val plainMessageBox get() = InternalMessageBox(PLAIN_MESSAGE)
val infoMessageBox get() = InternalMessageBox(INFORMATION_MESSAGE)
val warnMessageBox get() = InternalMessageBox(WARNING_MESSAGE)
val errorMessageBox get() = InternalMessageBox(ERROR_MESSAGE)
val questionMessageBox get() = InternalMessageBox(QUESTION_MESSAGE)

val JFrame.plainMessageBox get() = JFrameInternalMessageBox(PLAIN_MESSAGE, this)
val JFrame.infoMessageBox get() = JFrameInternalMessageBox(INFORMATION_MESSAGE, this)
val JFrame.warnMessageBox get() = JFrameInternalMessageBox(WARNING_MESSAGE, this)
val JFrame.errorMessageBox get() = JFrameInternalMessageBox(ERROR_MESSAGE, this)
val JFrame.questionMessageBox get() = JFrameInternalMessageBox(QUESTION_MESSAGE, this)

@Suppress("UNCHECKED_CAST")
class InternalInputBox(private val type: Int) {
    operator fun <T> invoke(message: String,
                            title: String = UIManager.getString("OptionPane.inputDialogTitle", Locale.getDefault()),
                            initialValue: T? = null,
                            selectionValues: Array<T>? = null,
                            icon: Icon? = null,
                            parentComponent: Component? = null): T
            = showInputDialog(parentComponent, message, title, type, icon, selectionValues, initialValue) as T
}

@Suppress("UNCHECKED_CAST")
class JFrameInternalInputBox(private val type: Int,
                             private val parentComponent: Component?) {
    operator fun <T> invoke(message: String,
                            title: String = UIManager.getString("OptionPane.inputDialogTitle",
                                    if (parentComponent == null) Locale.getDefault() else parentComponent.locale),
                            initialValue: T? = null,
                            selectionValues: Array<T>? = null,
                            icon: Icon? = null): T
            = showInputDialog(parentComponent, message, title, type, icon, selectionValues, initialValue) as T
}

val plainInputBox get() = InternalInputBox(PLAIN_MESSAGE)
val infoInputBox get() = InternalInputBox(INFORMATION_MESSAGE)
val warnInputBox get() = InternalInputBox(WARNING_MESSAGE)
val errorInputBox get() = InternalInputBox(ERROR_MESSAGE)
val questionInputBox get() = InternalInputBox(QUESTION_MESSAGE)

val JFrame.plainInputBox get() = JFrameInternalInputBox(PLAIN_MESSAGE, this)
val JFrame.infoInputBox get() = JFrameInternalInputBox(INFORMATION_MESSAGE, this)
val JFrame.warnInputBox get() = JFrameInternalInputBox(WARNING_MESSAGE, this)
val JFrame.errorInputBox get() = JFrameInternalInputBox(ERROR_MESSAGE, this)
val JFrame.questionInputBox get() = JFrameInternalInputBox(QUESTION_MESSAGE, this)

class ConfirmBoxResult(private val result: Int) {
    private val okButton = LinkedList<() -> Unit>()
    private val cancelButton = LinkedList<() -> Unit>()
    private val yesButton = LinkedList<() -> Unit>()
    private val noButton = LinkedList<() -> Unit>()
    private val closed = LinkedList<() -> Unit>()

    fun okButton(block: () -> Unit) = okButton.add(block)
    fun cancelButton(block: () -> Unit) = cancelButton.add(block)
    fun yesButton(block: () -> Unit) = yesButton.add(block)
    fun noButton(block: () -> Unit) = noButton.add(block)
    fun closed(block: () -> Unit) = closed.add(block)

    internal fun dispatch() {
        when (result) {
            OK_OPTION -> {
                okButton.forEach { it() }; yesButton.forEach { it() }
            }
            CANCEL_OPTION -> cancelButton.forEach { it() }
            NO_OPTION -> noButton.forEach { it() }
            CLOSED_OPTION -> closed.forEach { it() }
        }
    }
}

class InternalConfirmBox(private val optionType: Int) {
    operator fun invoke(message: String,
                        title: String = UIManager.getString("OptionPane.titleText", Locale.getDefault()),
                        type: Int = QUESTION_MESSAGE,
                        icon: Icon? = null,
                        parentComponent: Component? = null,
                        block: ConfirmBoxResult.() -> Unit) {
        ConfirmBoxResult(showConfirmDialog(parentComponent, message, title, optionType, type, icon)).apply(block).dispatch()
    }
}

class JFrameInternalConfirmBox(private val optionType: Int,
                               private val parentComponent: Component?) {
    operator fun invoke(message: String,
                        title: String = UIManager.getString("OptionPane.titleText"),
                        type: Int = QUESTION_MESSAGE,
                        icon: Icon? = null,
                        block: ConfirmBoxResult.() -> Unit) {
        ConfirmBoxResult(showConfirmDialog(parentComponent, message, title, optionType, type, icon)).apply(block).dispatch()
    }
}

val yesNoCancelBox get() = InternalConfirmBox(YES_NO_CANCEL_OPTION)
val yesNoBox get() = InternalConfirmBox(YES_NO_OPTION)
val okCancelBox get() = InternalConfirmBox(OK_CANCEL_OPTION)

val JFrame.yesNoCancelBox get() = JFrameInternalConfirmBox(YES_NO_CANCEL_OPTION, this)
val JFrame.yesNoBox get() = JFrameInternalConfirmBox(YES_NO_OPTION, this)
val JFrame.okCancelBox get() = JFrameInternalConfirmBox(OK_CANCEL_OPTION, this)