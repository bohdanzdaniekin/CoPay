package ui.component

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.app_name_title
import dragonfly.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DragonFlyTheme

@Composable
fun CardTitledLogo(
    modifier: Modifier = Modifier,
    color: Color = DragonFlyTheme.colors.neutral8
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(resource = Res.string.app_name_title).uppercase(),
            style = DragonFlyTheme.typography.subtitle2.bold,
            color = color,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            minLines = 2,
            maxLines = 2,
            modifier = Modifier,
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.width(DragonFlyTheme.spacing.xxSmall))

        VerticalDivider(
            modifier = Modifier
                .width(DividerDefaults.Thickness)
                .fillMaxHeight()
                .padding(vertical = DragonFlyTheme.spacing.xxxSmall),
            color = color
        )

        Spacer(modifier = Modifier.width(DragonFlyTheme.spacing.xxSmall))

        Icon(
            painter = painterResource(resource = Res.drawable.ic_logo),
            contentDescription = stringResource(resource = Res.string.app_name_title),
            tint = color
        )
    }
}

@Preview
@Composable
private fun CardTitledLogoPreview() {
    DragonFlyTheme {
        CardTitledLogo(
            modifier = Modifier
        )
    }
}
