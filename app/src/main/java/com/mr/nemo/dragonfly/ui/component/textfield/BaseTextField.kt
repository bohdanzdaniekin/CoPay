@file:OptIn(ExperimentalMaterial3Api::class)

package com.mr.nemo.dragonfly.ui.component.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
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
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val innerLabelTextStyle = if (value.isBlank()) {
        placeholderTextStyle
    } else {
        labelTextStyle
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle,
        shape = shape,
        placeholder = if (!placeholder.isNullOrBlank()) {
            placeholder(placeholder, placeholderTextColor, placeholderTextStyle)
        } else {
            null
        },
        label = if (!label.isNullOrBlank()) {
            placeholder(label, labelTextColor, innerLabelTextStyle)
        } else {
            null
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = colors,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
private fun placeholder(
    text: String,
    color: Color,
    style: TextStyle = LocalTextStyle.current
): @Composable () -> Unit = {
    Text(
        text = text,
        color = color,
        style = style
    )
}

@Preview(showBackground = true)
@Composable
fun BaseTextFieldPreview() {
    DragonFlyTheme {
        BaseTextField(
            value = "Some Text",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BaseTextFieldPreviewPlaceholder() {
    DragonFlyTheme {
        BaseTextField(
            value = "",
            onValueChange = {},
            placeholder = "Placeholder"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BaseTextFieldPreviewPlaceholderAndLabel() {
    DragonFlyTheme {
        BaseTextField(
            value = "Some text",
            onValueChange = {},
            placeholder = "Placeholder",
            label = "Label"
        )
    }
}
