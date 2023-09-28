@file:OptIn(ExperimentalMaterial3Api::class)

package com.mr.nemo.dragonfly.ui.component.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

@OptIn(ExperimentalMaterial3Api::class)
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
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val textColor = textStyle.color.takeOrElse {
        MaterialTheme.colorScheme.onSurface
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    val isFocused by interactionSource.collectIsFocusedAsState()

    val mergedPlaceholderTextStyle =
        placeholderTextStyle.merge(TextStyle(color = placeholderTextColor))

    val innerLabelTextStyle = if (value.isBlank() && !isFocused) {
        mergedPlaceholderTextStyle
    } else {
        labelTextStyle.merge(TextStyle(labelTextColor))
    }

    BasicTextField(
        value = value,
        modifier = if (label != null) {
            modifier
                .semantics(mergeDescendants = true) {}
                .padding(
                    top = if (value.isBlank()) {
                        DragonFlyTheme.spacing.medium
                    } else {
                        DragonFlyTheme.spacing.xSmall
                    }
                )
        } else {
            modifier
        }
            .defaultMinSize(
                minWidth = OutlinedTextFieldDefaults.MinWidth,
                minHeight = OutlinedTextFieldDefaults.MinHeight
            ),
        onValueChange = onValueChange,
        enabled = isEnabled,
        readOnly = isReadOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = if (!placeholder.isNullOrBlank()) {
                    @Composable {
                        Text(
                            text = placeholder,
                            style = mergedPlaceholderTextStyle,
                            maxLines = maxLines
                        )
                    }
                } else {
                    null
                },
                label = if (!label.isNullOrBlank() && (value.isNotBlank() || placeholder.isNullOrBlank())) {
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
                prefix = prefix,
                suffix = suffix,
                supportingText = supportingText,
                singleLine = singleLine,
                enabled = isEnabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                contentPadding = if (label == null) {
                    TextFieldDefaults.contentPaddingWithoutLabel(
                        start = DragonFlyTheme.spacing.small,
                        end = DragonFlyTheme.spacing.small,
                        top = DragonFlyTheme.spacing.large,
                        bottom = DragonFlyTheme.spacing.small
                    )
                } else {
                    TextFieldDefaults.contentPaddingWithLabel(
                        start = DragonFlyTheme.spacing.small,
                        end = DragonFlyTheme.spacing.small,
                        top = DragonFlyTheme.spacing.medium,
                        bottom = DragonFlyTheme.spacing.medium
                    )
                },
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = isEnabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = shape
                    )
                }
            )
        }
    )
}

@Composable
private fun cursorColor(isError: Boolean): State<Color> {
    return rememberUpdatedState(
        if (isError) {
            MaterialTheme.colorScheme.error
        } else {
            MaterialTheme.colorScheme.primary
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BaseTextFieldPreview() {
    DragonFlyTheme {
        BaseTextField(
            value = "Some Text",
            onValueChange = {},
            modifier = Modifier
                .padding(DragonFlyTheme.spacing.medium)
                .fillMaxWidth()
                .height(72.dp)
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
fun BaseTextFieldPreviewLabel() {
    DragonFlyTheme {
        BaseTextField(
            value = "",
            onValueChange = {},
            label = "Placeholder",
            modifier = Modifier
                .padding(DragonFlyTheme.spacing.medium)
                .fillMaxWidth()
                .height(72.dp)
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
            label = "Label",
            modifier = Modifier
                .padding(DragonFlyTheme.spacing.medium)
                .fillMaxWidth()
                .height(72.dp)
        )
    }
}
