package com.mr.nemo.dragonfly.ui.theme.icons

import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

private var _translate: ImageVector? = null

@Suppress("UnusedReceiverParameter")
val DragonFlyIcons.Translate: ImageVector
    get() {
        if (_translate != null) {
            return _translate!!
        }
        _translate = ImageVector.Builder(
            name = "vector",
            defaultWidth = 24.dp,
            defaultHeight = 25.dp,
            viewportWidth = 24f,
            viewportHeight = 25f
        ).apply {
            materialPath {
                moveTo(12.65f, 16.17f)
                curveTo(12.79f, 15.81f, 12.7f, 15.4f, 12.42f, 15.12f)
                lineTo(10.33f, 13.06f)
                lineTo(10.36f, 13.03f)
                curveTo(12.0541f, 11.1471f, 13.3199f, 8.9192f, 14.07f, 6.5f)
                horizontalLineTo(16.01f)
                curveTo(16.55f, 6.5f, 17f, 6.05f, 17f, 5.51f)
                verticalLineTo(5.49f)
                curveTo(17f, 4.95f, 16.55f, 4.5f, 16.01f, 4.5f)
                horizontalLineTo(10f)
                verticalLineTo(3.5f)
                curveTo(10f, 2.95f, 9.55f, 2.5f, 9f, 2.5f)
                curveTo(8.45f, 2.5f, 8f, 2.95f, 8f, 3.5f)
                verticalLineTo(4.5f)
                horizontalLineTo(1.99f)
                curveTo(1.45f, 4.5f, 1f, 4.95f, 1f, 5.49f)
                curveTo(1f, 6.04f, 1.45f, 6.48f, 1.99f, 6.48f)
                horizontalLineTo(12.17f)
                curveTo(11.484f, 8.4644f, 10.406f, 10.2906f, 9f, 11.85f)
                curveTo(8.19f, 10.96f, 7.51f, 9.99f, 6.94f, 8.97f)
                curveTo(6.8648f, 8.8283f, 6.7525f, 8.7098f, 6.6151f, 8.627f)
                curveTo(6.4777f, 8.5442f, 6.3204f, 8.5003f, 6.16f, 8.5f)
                curveTo(5.47f, 8.5f, 5.03f, 9.25f, 5.37f, 9.85f)
                curveTo(6f, 10.98f, 6.77f, 12.06f, 7.67f, 13.06f)
                lineTo(3.3f, 17.37f)
                curveTo(3.205f, 17.4623f, 3.1296f, 17.5726f, 3.078f, 17.6946f)
                curveTo(3.0265f, 17.8166f, 2.9999f, 17.9476f, 2.9999f, 18.08f)
                curveTo(2.9999f, 18.2124f, 3.0265f, 18.3434f, 3.078f, 18.4654f)
                curveTo(3.1296f, 18.5874f, 3.205f, 18.6977f, 3.3f, 18.79f)
                curveTo(3.69f, 19.18f, 4.32f, 19.18f, 4.72f, 18.79f)
                lineTo(9f, 14.5f)
                lineTo(11.02f, 16.52f)
                curveTo(11.53f, 17.03f, 12.4f, 16.84f, 12.65f, 16.17f)
                close()
                moveTo(17.5f, 10.5f)
                curveTo(16.9f, 10.5f, 16.36f, 10.87f, 16.15f, 11.44f)
                lineTo(12.48f, 21.24f)
                curveTo(12.24f, 21.85f, 12.7f, 22.5f, 13.35f, 22.5f)
                curveTo(13.74f, 22.5f, 14.09f, 22.26f, 14.23f, 21.89f)
                lineTo(15.12f, 19.5f)
                horizontalLineTo(19.87f)
                lineTo(20.77f, 21.89f)
                curveTo(20.91f, 22.25f, 21.26f, 22.5f, 21.65f, 22.5f)
                curveTo(22.3f, 22.5f, 22.76f, 21.85f, 22.53f, 21.24f)
                lineTo(18.86f, 11.44f)
                curveTo(18.64f, 10.87f, 18.1f, 10.5f, 17.5f, 10.5f)
                close()
                moveTo(15.88f, 17.5f)
                lineTo(17.5f, 13.17f)
                lineTo(19.12f, 17.5f)
                horizontalLineTo(15.88f)
                close()
            }
        }.build()
        return _translate!!
    }
