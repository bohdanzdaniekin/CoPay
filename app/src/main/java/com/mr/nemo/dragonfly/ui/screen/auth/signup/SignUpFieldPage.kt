package com.mr.nemo.dragonfly.ui.screen.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mr.nemo.dragonfly.R
import com.mr.nemo.dragonfly.domain.entity.auth.signup.PasswordValue
import com.mr.nemo.dragonfly.domain.entity.auth.signup.PhoneValue
import com.mr.nemo.dragonfly.domain.entity.auth.signup.SignUpField
import com.mr.nemo.dragonfly.ui.component.text.TitleText
import com.mr.nemo.dragonfly.ui.component.textfield.BaseTextField
import com.mr.nemo.dragonfly.ui.component.textfield.drowdown.LargeDropdownMenu
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

@Composable
fun <T, F : SignUpField<T>> SignUpFieldPage(
    field: F,
    onValueChanged: (field: F) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Column(modifier = modifier) {
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
                        onValueChanged(
                            field.copy(value = value) as F
                        )
                    },
                    modifier = fieldModifier
                        .fillMaxWidth(),
                    placeholder = stringResource(R.string.hint_email)
                )
            }
            is SignUpField.Username -> {
                BaseTextField(
                    value = field.value,
                    onValueChange = { value ->
                        onValueChanged(
                            field.copy(value = value) as F
                        )
                    },
                    modifier = fieldModifier,
                    placeholder = stringResource(R.string.hint_username)
                )
            }
            is SignUpField.Password -> {
                Column {
                    BaseTextField(
                        value = field.value.password,
                        onValueChange = { value ->
                            onValueChanged(
                                field.copy(
                                    value = field.value.copy(
                                        password = value
                                    )
                                ) as F
                            )
                        },
                        modifier = fieldModifier
                            .fillMaxWidth(),
                        placeholder = stringResource(R.string.hint_password)
                    )

                    Spacer(modifier = Modifier.height(spacing.medium))

                    BaseTextField(
                        value = field.value.confirmation,
                        onValueChange = { value ->
                            onValueChanged(
                                field.copy(
                                    value = field.value.copy(
                                        confirmation = value
                                    )
                                ) as F
                            )
                        },
                        modifier = fieldModifier
                            .fillMaxWidth(),
                        placeholder = stringResource(R.string.hint_password_confirmation)
                    )
                }
            }
            is SignUpField.Phone -> {
                // TODO: Optimize providing and selecting items
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                ) {
                    LargeDropdownMenu(
                        items = field.availableCodes,
                        selectedIndex = field.availableCodes.indexOfFirst { item ->
                            field.value.code.contentEquals(item.text)
                        },
                        onItemSelected = { _, item ->
                            onValueChanged(
                                field.copy(
                                    value = field.value.copy(code = item.text)
                                ) as F
                            )
                        },
                        modifier = Modifier
                            .height(72.dp)
                            .weight(3f)
                    )

                    Spacer(modifier = Modifier.width(spacing.small))

                    BaseTextField(
                        value = field.value.number,
                        onValueChange = { value ->
                            onValueChanged(
                                field.copy(
                                    value = field.value.copy(number = value)
                                ) as F
                            )
                        },
                        modifier = fieldModifier
                            .height(72.dp)
                            .weight(7f),
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
            onValueChanged = { }
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
            onValueChanged = { }
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
            onValueChanged = { }
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
            onValueChanged = { }
        )
    }
}
