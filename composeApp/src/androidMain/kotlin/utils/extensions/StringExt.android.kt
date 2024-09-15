package utils.extensions

import androidx.core.text.isDigitsOnly
import java.util.UUID

actual fun String.isDigitsOnly(): Boolean = isDigitsOnly()