package charlie.gensokyo

import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.Point
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JTextField

fun Component.size(width: Int, height: Int) = setSize(width, height)
fun Component.minSize(width: Int, height: Int) {
    minimumSize = Dimension(width, height)
}
fun Component.maxSize(width: Int, height: Int) {
    maximumSize = Dimension(width, height)
}
fun Component.location(x: Int, y: Int) = setLocation(x, y)

inline fun Component.size(block: Dimension.() -> Unit) {
    size = Dimension().apply(block)
}

inline fun Component.minSize(block: Dimension.() -> Unit) {
    minimumSize = Dimension().apply(block)
}

inline fun Component.maxSize(block: Dimension.() -> Unit) {
    maximumSize = Dimension().apply(block)
}

inline fun Component.location(block: Point.() -> Unit) {
    location = Point().apply(block)
}

val Component.show: Unit get() { isVisible = true }
val Component.hide: Unit get() { isVisible = false }

operator fun JComponent.contains(key: Any) = getClientProperty(key) != null
operator fun JComponent.get(key: Any) = getClientProperty(key) ?: ""
operator fun JComponent.set(key: Any, value: Any) = putClientProperty(key, value)

fun JComponent.assignID(ID: String) {
    this["gensokyo.compID"] = ID
}

fun Container.componentFromID(ID: String): JComponent {
    fun findInContainer(container: Container): JComponent? {
        container.components.forEach {
            if (it is JComponent && it["gensokyo.compID"] == ID) return it
            if (it is Container) findInContainer(it).apply {
                if (this != null) return this
            }
        }
        return null
    }
    return findInContainer(this) ?: throw NoSuchElementException(ID)
}

fun Container.textFieldFromID(ID: String) = componentFromID(ID) as JTextField
fun Container.buttonFromID(ID: String) = componentFromID(ID) as JButton