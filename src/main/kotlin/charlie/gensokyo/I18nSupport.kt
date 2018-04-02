@file:JvmName("Gensokyo")
@file:JvmMultifileClass

package charlie.gensokyo

import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NonNls
import java.util.*
import java.util.function.Supplier

abstract class I18nSupport {
    @Nls
    abstract fun getString(@NonNls key: String): String

    @Nls
    abstract fun getStringFormatted(@NonNls key: String, vararg parameters: String): String
}

object I18nManager {
    var support: I18nSupport? = null

    fun getString(key: String) = (support ?: throw I18nNotSupportedException()).getString(key)

    fun getStringFormatted(key: String, vararg parameters: String) =
            (support ?: throw I18nNotSupportedException()).getStringFormatted(key, *parameters)
}

class I18nNotSupportedException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)
}
