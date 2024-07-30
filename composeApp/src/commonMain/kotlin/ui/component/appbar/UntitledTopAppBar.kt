package ui.component.appbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.app_name
import dragonfly.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DragonFlyTheme
import ui.theme.icons.DragonFlyIcons
import ui.theme.icons.Translate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UntitledTopAppBar(
    onLanguageClicked: () -> Unit,
    onLogoClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {},
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onLogoClicked) {
                Icon(
                    painter = painterResource(resource = Res.drawable.ic_logo),
                    contentDescription = stringResource(resource = Res.string.app_name),
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = onLanguageClicked) {
                Icon(
                    imageVector = DragonFlyIcons.Translate,
                    contentDescription = stringResource(resource = Res.string.app_name),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}

@Preview
@Composable
private fun UntitledTopAppBarPreview() {
    DragonFlyTheme {
        UntitledTopAppBar(
            onLanguageClicked = {},
            onLogoClicked = {}
        )
    }
}
