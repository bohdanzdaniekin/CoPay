package com.mr.nemo.dragonfly.ui.component.textfield.drowdown

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mr.nemo.dragonfly.ui.component.textfield.BaseTextField
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <I : DropDownMenuItem> DropDownMenuField(
    items: List<I>,
    selectedItem: I?,
    onItemSelected: (item: I) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = DragonFlyTheme.typography.text1.regular,
    placeholder: String? = null
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = !isExpanded
        },
        modifier = modifier
    ) {
        val measurableText = selectedItem?.text ?: placeholder
        val textMeasurer = rememberTextMeasurer()
        val density = LocalDensity.current
        val textLayoutResult =
            textMeasurer.measure(
                text = AnnotatedString(measurableText?.ifBlank { "+380" } ?: "+380"),
                style = textStyle,
                maxLines = 1,
                density = density,
                overflow = TextOverflow.Visible
            )
        val textSize = textLayoutResult.size

        BaseTextField(
            value = selectedItem?.text.orEmpty(),
            onValueChange = {},
            isReadOnly = true,
            textStyle = textStyle,
            placeholder = placeholder,
            label = null,
            trailingIcon = {
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    null,
                    Modifier.rotate(if (isExpanded) 180f else 0f)
                )
            },
            singleLine = true,
            modifier = Modifier
                .menuAnchor()
                .height(72.dp)
                .requiredWidth(with(density) { textSize.width.toDp() })
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.exposedDropdownSize()
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.text,
                            style = textStyle
                        )
                    },
                    onClick = {
                        onItemSelected(item)
                        isExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuPreview() {
    DragonFlyTheme {
        DropDownMenuField(
            items = ('A'..'Z').map { StringItem(it.toString()) },
            selectedItem = null,
            onItemSelected = {},
            placeholder = "Select item"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuPreviewSelected() {
    DragonFlyTheme {
        DropDownMenuField(
            items = ('A'..'Z').map { StringItem(it.toString()) },
            selectedItem = StringItem(""),
            onItemSelected = {},
            placeholder = "Select item",
            modifier = Modifier
                .height(72.dp)
        )
    }
}
