package ui.component.textfield.drowdown

import kotlin.jvm.JvmInline

interface DropDownMenuItem {

    val text: String
}

@JvmInline
value class StringItem(override val text: String) : DropDownMenuItem
