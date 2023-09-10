package com.mr.nemo.dragonfly.ui.component.securitycode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.mr.nemo.dragonfly.ui.component.textfield.BaseTextField
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CodeTextField(
    digit: String,
    onValueChanged: (String) -> Unit,
    currentFocus: FocusRequester,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    previousFocus: FocusRequester? = null,
    nextFocus: FocusRequester? = null,
    visualTransformation: VisualTransformation = PasswordVisualTransformation()
) {
    val colors = DragonFlyTheme.colors
    val typography = DragonFlyTheme.typography
    val keyboardManager = LocalSoftwareKeyboardController.current

    val borderColor by remember {
        derivedStateOf {
            if (digit.isNotBlank()) {
                colors.primary.main
            } else {
                colors.neutral7
            }
        }
    }
    BaseTextField(
        value = digit,
        onValueChange = { value ->
            handleValueChange(
                previousFocus = previousFocus,
                nextFocus = nextFocus,
                currentFocus = currentFocus,
                value = value,
                keyboardManager
            ) { changed ->
                onValueChanged(changed)
            }
        },
        modifier = modifier
            .height(size)
            .aspectRatio(1f)
            .then(
                if (digit.isBlank()) {
                    Modifier
                } else {
                    Modifier.drawWithContent {
                        drawContent()
                        drawCircle(
                            color = colors.primary.main,
                            radius = this.size.height / 5.0f
                        )
                    }
                }
            )
            .focusRequester(currentFocus),
        textStyle = typography.header1.regular.copy(textAlign = TextAlign.Center),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            focusedTextColor = colors.primary.main,
            unfocusedTextColor = colors.primary.main
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
private fun handleValueChange(
    previousFocus: FocusRequester? = null,
    nextFocus: FocusRequester? = null,
    currentFocus: FocusRequester,
    value: String,
    keyboardManager: SoftwareKeyboardController?,
    onValueChanged: (String) -> Unit
) {
    if (value.isDigitsOnly()) {
        if (value.length > 1) {
            onValueChanged(value.last().toString())
            nextFocus?.requestFocus() ?: keyboardManager?.hide()
            return
        }
        if (currentFocus.freeFocus()) {
            onValueChanged(value)
            if (value.isBlank() && previousFocus != null) {
                previousFocus.requestFocus()
            } else if (value.isNotBlank()) {
                nextFocus?.requestFocus() ?: keyboardManager?.hide()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CodeTextField() {
    DragonFlyTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            CodeTextField(
                digit = "1",
                onValueChanged = {},
                modifier = Modifier
                    .height(80.dp)
                    .aspectRatio(1f),
                currentFocus = FocusRequester.Default
            )
            CodeTextField(
                digit = "",
                onValueChanged = {},
                modifier = Modifier
                    .height(80.dp)
                    .aspectRatio(1f),
                currentFocus = FocusRequester.Default
            )
        }
    }
}
