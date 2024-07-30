package domain.entity.auth.signup

import ui.component.textfield.drowdown.StringItem

sealed interface SignUpField<T> {

    val title: String
    val description: String
    val value: T

    data class Username(
        override val title: String,
        override val description: String,
        override val value: String = ""
    ) : SignUpField<String>

    data class Email(
        override val title: String,
        override val description: String,
        override val value: String = ""
    ) : SignUpField<String>

    data class Phone(
        override val title: String,
        override val description: String,
        val availableCodes: List<StringItem> = emptyList(),
        override val value: PhoneValue = PhoneValue()
    ) : SignUpField<PhoneValue>

    data class Password(
        override val title: String,
        override val description: String,
        override val value: PasswordValue = PasswordValue()
    ) : SignUpField<PasswordValue>
}
