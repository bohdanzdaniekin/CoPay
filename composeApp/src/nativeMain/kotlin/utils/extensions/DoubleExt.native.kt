package utils.extensions

import platform.Foundation.NSString
import platform.Foundation.stringWithFormat

actual fun Double.format(scale: Int): String = NSString.stringWithFormat("%.${scale}f", this)