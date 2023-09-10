package com.mr.nemo.dragonfly.ui.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class SchemebleColor(
    val main: Color = Color.Unspecified,
    val border: Color = Color.Unspecified,
    val hover: Color = Color.Unspecified,
    val passed: Color = Color.Unspecified
) {

    companion object {

        val Unspecified = SchemebleColor(
            main = Color.Unspecified,
            border = Color.Unspecified,
            hover = Color.Unspecified,
            passed = Color.Unspecified
        )
    }
}
