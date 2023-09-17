package com.mr.nemo.dragonfly.domain.entity

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class OnboardingContent(
    val title: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val hasMore: Boolean
)
