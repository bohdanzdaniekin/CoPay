package utils.extensions

import platform.Foundation.NSUUID

actual fun String.isDigitsOnly(): Boolean = all(Char::isDigit)

actual fun randomUUID(): String = NSUUID().UUIDString()