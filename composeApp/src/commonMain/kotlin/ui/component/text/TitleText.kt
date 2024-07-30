package ui.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DragonFlyTheme

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = DragonFlyTheme.colors.neutral2,
    textAlign: TextAlign? = null,
    style: TextStyle = DragonFlyTheme.typography.header2.medium
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        style = style
    )
}

@Composable
fun TitleText(
    text: StringResource,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    style: TextStyle = DragonFlyTheme.typography.header2.medium
) {
    TitleText(
        text = stringResource(resource = text),
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        style = style
    )
}

@Preview
@Composable
private fun TitleTextPreview() {
    DragonFlyTheme {
        TitleText(text = "Preview")
    }
}
