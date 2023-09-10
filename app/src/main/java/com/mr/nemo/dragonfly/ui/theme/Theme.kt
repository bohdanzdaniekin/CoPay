package com.mr.nemo.dragonfly.ui.theme

import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.mr.nemo.dragonfly.ui.theme.color.ColorScheme
import com.mr.nemo.dragonfly.ui.theme.color.LocalColorScheme
import com.mr.nemo.dragonfly.ui.theme.color.colorScheme
import com.mr.nemo.dragonfly.ui.theme.type.LocalTypography
import com.mr.nemo.dragonfly.ui.theme.type.Typography
import com.mr.nemo.dragonfly.ui.theme.type.typography

@Composable
fun DragonFlyTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography,
        LocalShapes provides shapes,
        LocalSpacing provides spacing,
        content = content
    )
}

object DragonFlyTheme {

    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val spacing: Spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current
}


