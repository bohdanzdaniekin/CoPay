package ui.component.textfield.drowdown

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.component.textfield.BaseTextField
import ui.theme.DragonFlyTheme

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
        BaseTextField(
            value = selectedItem?.text.orEmpty(),
            onValueChange = {},
            modifier = Modifier.menuAnchor(),
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
            singleLine = true
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

@Preview
@Composable
private fun DropDownMenuPreview() {
    DragonFlyTheme {
        DropDownMenuField(
            items = ('A'..'Z').map { StringItem(it.toString()) },
            selectedItem = null,
            onItemSelected = {},
            placeholder = "Select item"
        )
    }
}

@Preview
@Composable
private fun DropDownMenuPreviewSelected() {
    DragonFlyTheme {
        DropDownMenuField(
            items = ('A'..'Z').map { StringItem(it.toString()) },
            selectedItem = StringItem("A"),
            onItemSelected = {},
            placeholder = "Select item",
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
        )
    }
}
