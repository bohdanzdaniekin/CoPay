package domain.entity

import org.jetbrains.compose.resources.DrawableResource

data class OnboardingContent(
    val title: String,
    val description: String,
    val image: DrawableResource,
    val hasMore: Boolean
)
