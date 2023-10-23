package com.mr.nemo.dragonfly.ui.entitiy.signup.info

import androidx.compose.runtime.Immutable
import com.mr.nemo.dragonfly.domain.entity.auth.signup.InfoItem
import com.mr.nemo.dragonfly.ui.entitiy.core.UiState

@Immutable
data class SignUpInfoScreenState(
    val items: List<InfoItem> = emptyList()
) : UiState
