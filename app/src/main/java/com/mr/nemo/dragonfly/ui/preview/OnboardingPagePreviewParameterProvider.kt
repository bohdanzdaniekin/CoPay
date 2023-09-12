package com.mr.nemo.dragonfly.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class OnboardingPagePreviewParameterProvider : PreviewParameterProvider<Boolean> {

    override val values = sequenceOf(
        true, false
    )
}
