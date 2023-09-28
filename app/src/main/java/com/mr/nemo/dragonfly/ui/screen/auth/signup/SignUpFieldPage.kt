package com.mr.nemo.dragonfly.ui.screen.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mr.nemo.dragonfly.R
import com.mr.nemo.dragonfly.domain.entity.auth.signup.PasswordValue
import com.mr.nemo.dragonfly.domain.entity.auth.signup.PhoneValue
import com.mr.nemo.dragonfly.domain.entity.auth.signup.SignUpField
import com.mr.nemo.dragonfly.ui.component.text.TitleText
import com.mr.nemo.dragonfly.ui.component.textfield.BaseTextField
import com.mr.nemo.dragonfly.ui.component.textfield.drowdown.DropDownMenuField
import com.mr.nemo.dragonfly.ui.component.textfield.drowdown.StringItem
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

@Composable
fun <T> SignUpFieldPage(
    field: SignUpField<T>,
    onValueChanged: (field: SignUpField<T>, newValue: T) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(spacing.large))

        TitleText(
            text = field.title,
            style = typography.header1.regular
        )

        Spacer(modifier = Modifier.height(spacing.medium))

        Text(
            text = field.description,
            style = typography.text2.regular,
            color = colors.neutral4
        )

        Spacer(modifier = Modifier.height(spacing.xLarge))

        val fieldModifier = Modifier
            .fillMaxWidth()
            .height(72.dp)

        when (field) {
            is SignUpField.Email -> {
                BaseTextField(
                    value = field.value,
                    onValueChange = { value ->
                        onValueChanged(field, value as T)
                    },
                    modifier = fieldModifier
                        .fillMaxWidth(),
                    placeholder = stringResource(R.string.hint_email)
                )

                Spacer(modifier = Modifier.height(spacing.medium))
            }
            is SignUpField.Username -> {
                BaseTextField(
                    value = field.value,
                    onValueChange = { value ->
                        onValueChanged(field, value as T)
                    },
                    modifier = fieldModifier,
                    placeholder = stringResource(R.string.hint_username)
                )

                Spacer(modifier = Modifier.height(spacing.medium))
            }
            is SignUpField.Password -> {
                Column {
                    BaseTextField(
                        value = field.value.password,
                        onValueChange = { value ->

                        },
                        modifier = fieldModifier
                            .fillMaxWidth(),
                        placeholder = stringResource(R.string.hint_password)
                    )

                    Spacer(modifier = Modifier.height(spacing.medium))

                    BaseTextField(
                        value = field.value.confirmation,
                        onValueChange = { value ->

                        },
                        modifier = fieldModifier
                            .fillMaxWidth(),
                        placeholder = stringResource(R.string.hint_password_confirmation)
                    )

                    Spacer(modifier = Modifier.height(spacing.medium))
                }
            }
            is SignUpField.Phone -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var selectedItem by remember {
                        mutableStateOf(StringItem("+380"))
                    }
                    val textMeasurer = rememberTextMeasurer()
                    val textLayoutResult =
                        textMeasurer.measure(
                            text = AnnotatedString(selectedItem.text.ifBlank { "+380" }),
                            style = typography.text1.regular,
                            maxLines = 1,
                            overflow = TextOverflow.Visible
                        )
                    val textSize = textLayoutResult.size
                    val density = LocalDensity.current
                    DropDownMenuField(
                        items = emptyList(),
                        selectedItem = selectedItem,
                        onItemSelected = {
                            selectedItem = it
                        },
                        modifier = Modifier
                            .height(72.dp)
                            .width(with(density) { textSize.width.toDp() })
                    )

                    Spacer(modifier = Modifier.width(spacing.small))

                    BaseTextField(
                        value = field.value.number,
                        onValueChange = { value ->

                        },
                        modifier = fieldModifier
                            .height(72.dp)
                            .weight(1f),
                        placeholder = stringResource(R.string.hint_phone)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFieldPagePreviewEmail() {
    DragonFlyTheme {
        SignUpFieldPage(
            SignUpField.Email(
                title = "Email",
                description = "Enter your email to register",
                value = ""
            ),
            onValueChanged = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFieldPagePreviewUsername() {
    DragonFlyTheme {
        SignUpFieldPage(
            SignUpField.Username(
                title = "Username",
                description = "Enter your username to register",
                value = ""
            ),
            onValueChanged = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFieldPagePreviewPhone() {
    DragonFlyTheme {
        SignUpFieldPage(
            SignUpField.Phone(
                title = "Phone",
                description = "Enter your phone to register",
                value = PhoneValue()
            ),
            onValueChanged = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFieldPagePreviewPassword() {
    DragonFlyTheme {
        SignUpFieldPage(
            SignUpField.Password(
                title = "Password",
                description = "Enter your password to register",
                value = PasswordValue()
            ),
            onValueChanged = { _, _ -> }
        )
    }
}
