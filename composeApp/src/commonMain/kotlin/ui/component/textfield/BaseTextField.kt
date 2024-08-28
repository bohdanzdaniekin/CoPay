package ui.component.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DragonFlyTheme


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

    val showPlaceholder by remember(value, placeholder) {
        derivedStateOf {
            value.isBlank() && !placeholder.isNullOrBlank()
        }
    }

    val showLabel = !label.isNullOrBlank()

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = isEnabled,
        readOnly = isReadOnly,
        textStyle = textStyle,
        shape = shape,
        placeholder = if (showPlaceholder) {
            @Composable {
                Text(
                    text = placeholder.orEmpty(),
                    style = mergedPlaceholderTextStyle,
                    maxLines = maxLines
                )
            }
        } else {
            null
        },
        label = if (showLabel) {
            @Composable {
                Text(
                    text = label.orEmpty(),
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

@Preview
@Composable
private fun BaseTextFieldPreview() {
    DragonFlyTheme {
        Column(
            modifier = Modifier
                .padding(DragonFlyTheme.spacing.medium)
                .fillMaxWidth()
        ) {
            BaseTextField(
                value = "Some Text",
                onValueChange = {},
                modifier = Modifier
                    .padding(DragonFlyTheme.spacing.medium)
                    .height(72.dp)
                    .fillMaxWidth()
            )

            BaseTextField(
                value = "",
                onValueChange = {},
                placeholder = "Placeholder",
                modifier = Modifier
                    .padding(DragonFlyTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(72.dp)
            )

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
}
