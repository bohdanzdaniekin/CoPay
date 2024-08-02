package ui.entitiy.signup.info

import androidx.compose.runtime.Immutable
import domain.entity.auth.signup.InfoItem
import ui.entitiy.core.UiState

@Immutable
data class SignUpInfoScreenState(
    val items: List<InfoItem> = emptyList()
) : UiState
