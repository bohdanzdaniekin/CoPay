package utils.extensions

import java.util.UUID

actual fun String.isDigitsOnly(): Boolean = all(Char::isDigit)

actual fun randomUUID(): String = randomUUID()