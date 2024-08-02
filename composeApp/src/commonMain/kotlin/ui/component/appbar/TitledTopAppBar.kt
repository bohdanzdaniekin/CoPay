package ui.component.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DragonFlyTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TitledTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    titleTextStyle: TextStyle = DragonFlyTheme.typography.subtitle2.medium,
    titleTextColor: Color = DragonFlyTheme.colors.neutral2,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = titleTextStyle,
                color = titleTextColor
            )
        },
        navigationIcon = navigationIcon,
        actions = actions,
        modifier = modifier
    )
}

@Preview
@Composable
private fun TitledTopAppBarPreview() {
    DragonFlyTheme {
        TitledTopAppBar(
            title = stringResource(resource = Res.string.app_name),
            navigationIcon = {

            },
            actions = {

            }
        )
    }
}
