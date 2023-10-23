@file:OptIn(ExperimentalMaterial3Api::class)

package com.mr.nemo.dragonfly.ui.component.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    textStyle: TextStyle = DragonFlyTheme.typography.text1.regular,
    shape: Shape = DragonFlyTheme.shapes.extraSmall,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = DragonFlyTheme.colors.primary.main,
        unfocusedBorderColor = DragonFlyTheme.colors.neutral7,
    ),
    placeholder: String? = null,
    placeholderTextColor: Color = DragonFlyTheme.colors.neutral5,
    placeholderTextStyle: TextStyle = textStyle,
    label: String? = placeholder,
    labelTextColor: Color = placeholderTextColor,
    labelTextStyle: TextStyle = DragonFlyTheme.typography.caption.regular,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isFocused by interactionSource.collectIsFocusedAsState()

    val mergedPlaceholderTextStyle =
        placeholderTextStyle.merge(TextStyle(color = placeholderTextColor))

    val innerLabelTextStyle = if (value.isBlank() && !isFocused) {
        mergedPlaceholderTextStyle
    } else {
        labelTextStyle.merge(TextStyle(labelTextColor))
    }

    val innerPlaceholder = if (placeholder.isNullOrBlank()) {
        label ?: " "
    } else {
        placeholder
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = isEnabled,
        readOnly = isReadOnly,
        textStyle = textStyle,
        shape = shape,
        placeholder = @Composable {
            Text(
                text = innerPlaceholder,
                style = mergedPlaceholderTextStyle,
                maxLines = maxLines
            )
        },
        label = if (!label.isNullOrBlank() && (value.isNotBlank() || innerPlaceholder.isBlank())) {
            @Composable {
                Text(
                    text = label,
                    style = innerLabelTextStyle,
                    maxLines = maxLines
                )
            }
        } else {
            null
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        colors = colors,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource
    )
}

@Preview(showBackground = true)
@Composable
private fun BaseTextFieldPreview() {
    DragonFlyTheme {
        BaseTextField(
            value = "Some Text",
            onValueChange = {},
            modifier = Modifier
                .padding(DragonFlyTheme.spacing.medium)
                .height(72.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseTextFieldPreviewPlaceholder() {
    DragonFlyTheme {
        BaseTextField(
            value = "",
            onValueChange = {},
            placeholder = "Placeholder",
            modifier = Modifier
                .padding(DragonFlyTheme.spacing.medium)
                .fillMaxWidth()
                .height(72.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseTextFieldPreviewLabel() {
    DragonFlyTheme {
        Row {
            BaseTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .padding(DragonFlyTheme.spacing.medium)
                    .height(72.dp)
                    .weight(1f),
                singleLine = true
            )
            BaseTextField(
                value = "",
                onValueChange = {},
                placeholder = "Placeholder",
                modifier = Modifier
                    .padding(DragonFlyTheme.spacing.medium)
                    .height(72.dp)
                    .weight(1f),
                singleLine = true
            )
            BaseTextField(
                value = "",
                onValueChange = {},
                label = "Placeholder",
                modifier = Modifier
                    .padding(DragonFlyTheme.spacing.medium)
                    .height(72.dp)
                    .weight(1f),
                singleLine = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseTextFieldPreviewPlaceholderAndLabel() {
    DragonFlyTheme {
        BaseTextField(
            value = "Some text",
            onValueChange = {},
            placeholder = "Placeholder",
            label = "Label",
            modifier = Modifier
                .padding(DragonFlyTheme.spacing.medium)
                .fillMaxWidth()
                .height(72.dp)
        )
    }
}
