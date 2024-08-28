package ui.screen.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.ic_money_send
import dragonfly.composeapp.generated.resources.send
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DragonFlyTheme

@Composable
fun MoneyOperationItem(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical =
        Arrangement.spacedBy(DragonFlyTheme.spacing.small)
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = null,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = verticalArrangement,
        ) {
            Icon(
                painter = icon,
                contentDescription = text,
                tint = Color.Unspecified
            )

            Text(
                text = text,
                style = DragonFlyTheme.typography.text2.regular,
                color = DragonFlyTheme.colors.neutral2
            )
        }
    }
}

@Preview
@Composable
private fun MoneyOperationItemPreview() {
    DragonFlyTheme {
        MoneyOperationItem(
            text = stringResource(resource = Res.string.send),
            icon = painterResource(resource = Res.drawable.ic_money_send),
            onClick = {}
        )
    }
}
