package com.mr.nemo.dragonfly.ui.component.textfield.drowdown

interface DropDownMenuItem {

    val text: String
}

@JvmInline
value class StringItem(override val text: String) : DropDownMenuItem
