package charlie.gensokyo

import java.awt.Component
import java.awt.Dimension
import java.awt.Point

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