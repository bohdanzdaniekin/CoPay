package ui.component.securitycode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.DragonFlyTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SecurityCode(
    state: SecurityCodeState,
    onStateChanged: (SecurityCodeState) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        val (firstDigitFocus, secondDigitFocus, thirdDigitFocus, fourthDigitFocus) = remember {
            FocusRequester.createRefs()
        }
        val fieldModifier = Modifier
            .height(40.dp)
            .aspectRatio(1f)

        CodeTextField(
            digit = state.firstDigit,
            onValueChanged = { changed ->
                onStateChanged(state.copy(firstDigit = changed))
            },
            modifier = fieldModifier
                .focusProperties {
                    start = FocusRequester.Cancel
                    previous = FocusRequester.Cancel
                },
            previousFocus = null,
            nextFocus = secondDigitFocus,
            currentFocus = firstDigitFocus
        )

        CodeTextField(
            digit = state.secondDigit,
            onValueChanged = { changed ->
                onStateChanged(state.copy(secondDigit = changed))
            },
            modifier = fieldModifier
                .focusProperties {
                    previous = firstDigitFocus
                }
                .onKeyEvent {
                    if (it.key == Key.Backspace) {
                        state
                            .secondDigit
                            .takeIf(String::isBlank)
                            ?.let { firstDigitFocus.requestFocus() }
                        true
                    } else {
                        false
                    }
                },
            previousFocus = firstDigitFocus,
            nextFocus = thirdDigitFocus,
            currentFocus = secondDigitFocus
        )

        CodeTextField(
            digit = state.thirdDigit,
            onValueChanged = { changed ->
                onStateChanged(state.copy(thirdDigit = changed))
            },
            modifier = fieldModifier
                .focusProperties {
                    previous = secondDigitFocus
                }
                .onKeyEvent {
                    if (it.key == Key.Backspace) {
                        state
                            .thirdDigit
                            .takeIf(String::isBlank)
                            ?.let { secondDigitFocus.requestFocus() }
                        true
                    } else {
                        false
                    }
                },
            previousFocus = secondDigitFocus,
            nextFocus = fourthDigitFocus,
            currentFocus = thirdDigitFocus
        )

        CodeTextField(
            digit = state.fourthDigit,
            onValueChanged = { changed ->
                onStateChanged(state.copy(fourthDigit = changed))
            },
            modifier = fieldModifier
                .focusProperties {
                    previous = thirdDigitFocus
                    next = FocusRequester.Cancel
                }
                .onKeyEvent {
                    if (it.key == Key.Backspace) {
                        state
                            .fourthDigit
                            .takeIf(String::isBlank)
                            ?.let { thirdDigitFocus.requestFocus() }
                        true
                    } else {
                        false
                    }
                },
            previousFocus = thirdDigitFocus,
            nextFocus = null,
            currentFocus = fourthDigitFocus
        )
    }
}

@Preview
@Composable
private fun SmsCodePreview() {
    DragonFlyTheme {
        SecurityCode(
            state = SecurityCodeState("2"),
            onStateChanged = {}
        )
    }
}
