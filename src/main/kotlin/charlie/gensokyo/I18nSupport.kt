@file:JvmName("Gensokyo")
@file:JvmMultifileClass

package charlie.gensokyo

import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NonNls

abstract class I18nSupport {
    @Nls
    abstract fun getString(@NonNls key: String): String

    @Nls
    abstract fun getStringFormatted(@NonNls key: String, vararg parameters: String): String
}

object I18nManager {
    var support: I18nSupport? = null

    fun getString(key: String) = (support ?: throw I18nUnsupportedException()).getString(key)

    fun getStringFormatted(key: String, vararg parameters: String) =
            (support ?: throw I18nUnsupportedException()).getStringFormatted(key, *parameters)
}

class I18nUnsupportedException : Exception()
