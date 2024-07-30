package ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.app_name_title
import dragonfly.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DragonFlyTheme

@Composable
fun TitledLogo(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .clickable(onClick = onClick)
            .clip(DragonFlyTheme.shapes.extraSmall),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(resource = Res.drawable.ic_logo),
                contentDescription = stringResource(resource = Res.string.app_name_title),
                tint = DragonFlyTheme.colors.primary.main
            )
        }
        Text(
            text = stringResource(resource = Res.string.app_name_title).uppercase(),
            style = DragonFlyTheme.typography.subtitle2.bold,
            color = DragonFlyTheme.colors.neutral2,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            minLines = 2,
            maxLines = 2,
            modifier = Modifier.offset {
                IntOffset(x = -8.dp.toPx().toInt(), y = 0)
            }
        )
    }
}

@Preview
@Composable
private fun TitledLogoPreview() {
    DragonFlyTheme {
        TitledLogo(
            onClick = {}
        )
    }
}
