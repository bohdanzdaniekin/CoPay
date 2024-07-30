package utils.extensions

actual fun Double.format(scale: Int): String = "%.${scale}f".format(this)