package com.mr.nemo.dragonfly.ui.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorScheme(
    val primary: SchemebleColor = SchemebleColor.Unspecified,
    val secondary: SchemebleColor = SchemebleColor.Unspecified,
    val success: SchemebleColor = SchemebleColor.Unspecified,
    val info: SchemebleColor = SchemebleColor.Unspecified,
    val warning: SchemebleColor = SchemebleColor.Unspecified,
    val danger: SchemebleColor = SchemebleColor.Unspecified,
    val neutral1: Color = Color.Unspecified,
    val neutral2: Color = Color.Unspecified,
    val neutral3: Color = Color.Unspecified,
    val neutral4: Color = Color.Unspecified,
    val neutral5: Color = Color.Unspecified,
    val neutral6: Color = Color.Unspecified,
    val neutral7: Color = Color.Unspecified,
    val neutral8: Color = Color.Unspecified
)

val LocalColorScheme = staticCompositionLocalOf {
    ColorScheme()
}

val colorScheme = ColorScheme(
    primary = SchemebleColor(
        main = Color(0xFF05BE71),
        border = Color(0xFFACE9D0),
        hover = Color(0xFF049E5E),
        passed = Color(0xFF035F39)
    ),
    secondary = SchemebleColor(
        main = Color(0xFF7443FF),
        border = Color(0xFFD1C0FF),
        hover = Color(0xFF6138D4),
        passed = Color(0xFF3A2280)
    ),
    success = SchemebleColor(
        main = Color(0xFF50BC32),
        border = Color(0xFFC5E9BB),
        hover = Color(0xFF439D2A),
        passed = Color(0xFF285E19)
    ),
    info = SchemebleColor(
        main = Color(0xFF1368F9),
        border = Color(0xFFB0CDFD),
        hover = Color(0xFF1057CF),
        passed = Color(0xFF0A347D)
    ),
    warning = SchemebleColor(
        main = Color(0xFFFFC300),
        border = Color(0xFFFFEBAA),
        hover = Color(0xFFD4A200),
        passed = Color(0xFF806200)
    ),
    danger = SchemebleColor(
        main = Color(0xFFFE324E),
        border = Color(0xFFFFBBC4),
        hover = Color(0xFFD42A41),
        passed = Color(0xFF7F1927)
    ),
    neutral1 = Color(0xFF272727),
    neutral2 = Color(0xFF2D2D2D),
    neutral3 = Color(0xFF404040),
    neutral4 = Color(0xFF606060),
    neutral5 = Color(0xFF909090),
    neutral6 = Color(0xFFB1B1B1),
    neutral7 = Color(0xFFECECEC),
    neutral8 = Color(0xFFFFFFFF)
)

val AdBackground = Color(0xFFE2D8FF)
val AdGradient = Color(0xFFC5B1FF)
