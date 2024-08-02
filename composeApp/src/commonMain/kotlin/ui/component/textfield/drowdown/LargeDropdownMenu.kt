package ui.component.textfield.drowdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.hint_phone
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.component.textfield.BaseTextField
import ui.theme.DragonFlyTheme

@Composable
fun <T : DropDownMenuItem> LargeDropdownMenu(
    items: List<T>,
    onItemSelected: (index: Int, item: T) -> Unit,
    modifier: Modifier = Modifier,
    selectedIndex: Int = -1,
    itemToString: (T) -> String = { item -> item.text },
    textStyle: TextStyle = DragonFlyTheme.typography.text1.regular,
    placeholder: String? = null,
    notSetLabel: String? = null,
    enabled: Boolean = true,
    drawItem: @Composable (
        item: T,
        isSelected: Boolean,
        isItemEnabled: Boolean,
        onClick: () -> Unit
    ) -> Unit = { item, selected, itemEnabled, onClick ->
        LargeDropdownMenuItem(
            text = itemToString(item),
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    val shapes = DragonFlyTheme.shapes

    var isExpanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.height(IntrinsicSize.Min)) {
        BaseTextField(
            value = items
                .getOrNull(selectedIndex)
                ?.let(itemToString)
                .orEmpty(),
            onValueChange = {},
            modifier = modifier
                .fillMaxWidth(),
            isReadOnly = true,
            textStyle = textStyle,
            placeholder = placeholder,
            label = null,
            trailingIcon = {
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    if (isExpanded) "Collapse" else "Expand",
                    Modifier.rotate(if (isExpanded) 180f else 0f)
                )
            },
            singleLine = true
        )

        // Transparent clickable surface on top of TextField
        Surface(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxSize()
                .padding(top = spacing.xSmall)
                .clip(shapes.extraSmall)
                .clickable(enabled = enabled) { isExpanded = true },
            color = Color.Transparent,
        ) {}
    }

    if (isExpanded) {
        Dialog(
            onDismissRequest = { isExpanded = false },
        ) {
            Surface(
                shape = shapes.small,
            ) {
                val listState = rememberLazyListState()
                if (selectedIndex > -1) {
                    LaunchedEffect("ScrollToSelected") {
                        listState.scrollToItem(index = selectedIndex)
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    state = listState
                ) {
                    if (notSetLabel != null) {
                        item {
                            LargeDropdownMenuItem(
                                text = notSetLabel,
                                selected = false,
                                enabled = false,
                                onClick = { },
                            )
                        }
                    }
                    itemsIndexed(items) { index, item ->
                        val isSelected = index == selectedIndex
                        drawItem(
                            item,
                            isSelected,
                            true
                        ) {
                            onItemSelected(index, item)
                            isExpanded = false
                        }

                        if (index < items.lastIndex) {
                            HorizontalDivider(modifier = Modifier.padding(horizontal = spacing.medium))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LargeDropdownMenuItem(
    text: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = DragonFlyTheme.typography.text1.regular,
) {
    val colors = DragonFlyTheme.colors
    val spacing = DragonFlyTheme.spacing

    val contentColor = when {
        !enabled -> colors.neutral1.copy(alpha = 0.3f)
        selected -> colors.primary.main.copy(alpha = 1f)
        else -> colors.neutral1.copy(alpha = 1f)
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(
            modifier = modifier
                .clickable(enabled, onClick = onClick)
                .fillMaxWidth()
                .padding(spacing.medium)
        ) {
            Text(
                text = text,
                style = textStyle
            )
        }
    }
}

@Preview
@Composable
private fun DropDownMenuPreview() {
    DragonFlyTheme {
        val spacing = DragonFlyTheme.spacing
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentHeight()
        ) {
            var selectedIndex by remember {
                mutableIntStateOf(-1)
            }
            LargeDropdownMenu(
                items = emptyList(),
                selectedIndex = selectedIndex,
                onItemSelected = { index, item ->
                    selectedIndex = index
                },
                placeholder = "",
                modifier = Modifier
                    .height(72.dp)
                    .weight(3f)
            )

            Spacer(modifier = Modifier.width(spacing.small))

            BaseTextField(
                value = "",
                onValueChange = { value ->

                },
                modifier = Modifier
                    .height(72.dp)
                    .weight(7f),
                placeholder = stringResource(resource = Res.string.hint_phone)
            )
        }
        /*LargeDropdownMenu(
            items = ('A'..'Z').map { StringItem(it.toString()) },
            onItemSelected = { index: Int, item: StringItem ->

            },
            selectedIndex = -1,
            placeholder = "Select item"
        )*/
    }
}

@Preview
@Composable
private fun DropDownMenuPreviewSelected() {
    DragonFlyTheme {
        LargeDropdownMenu(
            items = ('A'..'Z').map { StringItem(it.toString()) },
            onItemSelected = { index: Int, item: StringItem ->

            },
            selectedIndex = 1,
            placeholder = "Select item"
        )
    }
}
