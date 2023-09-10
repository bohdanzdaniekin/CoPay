package com.mr.nemo.dragonfly.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val xxxSmall: Dp = Dp.Unspecified,
    val xxSmall: Dp = Dp.Unspecified,
    val xSmall: Dp = Dp.Unspecified,
    val small: Dp = Dp.Unspecified,
    val medium: Dp = Dp.Unspecified,
    val large: Dp = Dp.Unspecified,
    val xLarge: Dp = Dp.Unspecified,
    val xxLarge: Dp = Dp.Unspecified,
    val xxxLarge: Dp = Dp.Unspecified
)

val LocalSpacing = staticCompositionLocalOf {
    Spacing()
}

val spacing = Spacing(
    xxxSmall = 2.dp,
    xxSmall = 4.dp,
    xSmall = 8.dp,
    small = 12.dp,
    medium = 16.dp,
    large = 24.dp,
    xLarge = 32.dp,
    xxLarge = 48.dp,
    xxxLarge = 56.dp
)
