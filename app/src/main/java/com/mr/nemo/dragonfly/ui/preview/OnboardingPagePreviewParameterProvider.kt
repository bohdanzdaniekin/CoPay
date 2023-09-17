package com.mr.nemo.dragonfly.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mr.nemo.dragonfly.R
import com.mr.nemo.dragonfly.domain.entity.OnboardingContent

class OnboardingPagePreviewParameterProvider : PreviewParameterProvider<OnboardingContent> {

    override val values = sequenceOf(
        OnboardingContent(
            title = "Easy to manage money",
            description = "Transfer and receive your money easily with dragonfly bank",
            image = R.drawable.bg_onboarding_page_1,
            hasMore = true
        ),
        OnboardingContent(
            title = "Transfers between accounts",
            description = "Transferring balances is very easy between dragonfly bank accounts",
            image = R.drawable.bg_onboarding_page_2,
            hasMore = false
        ),
        OnboardingContent(
            title = "Choose as needed",
            description = "Choose the savings you want to open, we have lots of services according to what you need",
            image = R.drawable.bg_onboarding_page_3,
            hasMore = false
        )
    )
}
