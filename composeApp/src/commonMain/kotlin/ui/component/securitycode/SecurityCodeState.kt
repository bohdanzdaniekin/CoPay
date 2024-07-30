package ui.component.securitycode

import androidx.compose.runtime.Immutable

@Immutable
data class SecurityCodeState(
    val firstDigit: String = "",
    val secondDigit: String = "",
    val thirdDigit: String = "",
    val fourthDigit: String = ""
)
