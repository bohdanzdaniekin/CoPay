package utils.extensions

import androidx.core.text.isDigitsOnly
import java.util.UUID

actual fun String.isDigitsOnly(): Boolean = isDigitsOnly()

actual fun randomUUID(): String = UUID.randomUUID().toString()