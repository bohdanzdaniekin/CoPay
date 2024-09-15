package utils.extensions

actual fun String.isDigitsOnly(): Boolean = all(Char::isDigit)