package ui.preview

import domain.entity.OnboardingContent
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.bg_onboarding_page_1
import dragonfly.composeapp.generated.resources.bg_onboarding_page_2
import dragonfly.composeapp.generated.resources.bg_onboarding_page_3
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class OnboardingPagePreviewParameterProvider : PreviewParameterProvider<OnboardingContent> {

    override val values = sequenceOf(
        OnboardingContent(
            title = "Easy to manage money",
            description = "Transfer and receive your money easily with dragonfly bank",
            image = Res.drawable.bg_onboarding_page_1,
            hasMore = true
        ),
        OnboardingContent(
            title = "Transfers between accounts",
            description = "Transferring balances is very easy between dragonfly bank accounts",
            image = Res.drawable.bg_onboarding_page_2,
            hasMore = false
        ),
        OnboardingContent(
            title = "Choose as needed",
            description = "Choose the savings you want to open, we have lots of services according to what you need",
            image = Res.drawable.bg_onboarding_page_3,
            hasMore = false
        )
    )
}
