package charlie.gensokyo

import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NonNls
import java.util.*
import java.util.function.Supplier

abstract class I18nSupport {
    @Nls abstract fun getString(@NonNls key: String): String
    @Nls abstract fun getStringFormatted(@NonNls key: String, vararg parameters: String): String
}

object I18nManager {
    var support: Optional<I18nSupport> = Optional.empty()

    fun getString(key: String) = support.orElseThrow(I18nNotSupportedExceptionSupplier).getString(key)

    fun getStringFormatted(key: String, vararg parameters: String) =
            support.orElseThrow(I18nNotSupportedExceptionSupplier).getStringFormatted(key, *parameters)
}

val i18n = I18nManager::getString
val i18nFormat = I18nManager::getStringFormatted

class I18nNotSupportedException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)
}

object I18nNotSupportedExceptionSupplier: Supplier<I18nNotSupportedException> {
    override fun get(): I18nNotSupportedException = I18nNotSupportedException()
}