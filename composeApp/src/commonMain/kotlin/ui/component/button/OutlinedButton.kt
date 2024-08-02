package ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DragonFlyTheme
import androidx.compose.material3.OutlinedButton as MaterialOutlinedButton

@Composable
fun OutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = DragonFlyTheme.shapes.extraSmall,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val colors = DragonFlyTheme.colors
    val typography = DragonFlyTheme.typography
    MaterialOutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = colors.neutral8,
            contentColor = colors.neutral2
        ),
        border = BorderStroke(1.dp, colors.neutral7)
    ) {
        leadingIcon?.invoke()
        Text(
            text = text,
            color = colors.neutral2,
            style = typography.button
        )
        trailingIcon?.invoke()
    }
}

@Preview
@Composable
private fun OutlinedButtonPreview() {
    DragonFlyTheme {
        OutlinedButton(
            text = "Login",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(56.dp)
        )
    }
}

@Preview
@Composable
private fun OutlinedButtonPreviewLeadingIcon() {
    DragonFlyTheme {
        OutlinedButton(
            text = "Login",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(56.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(resource = Res.drawable.ic_logo),
                    contentDescription = null
                )
            }
        )
    }
}

@Preview
@Composable
private fun OutlinedButtonPreviewTrailingIcon() {
    DragonFlyTheme {
        OutlinedButton(
            text = "Login",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(56.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(resource = Res.drawable.ic_logo),
                    contentDescription = null
                )
            }
        )
    }
}
