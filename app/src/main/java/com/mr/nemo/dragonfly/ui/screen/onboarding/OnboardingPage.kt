package com.mr.nemo.dragonfly.ui.screen.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.mr.nemo.dragonfly.R
import com.mr.nemo.dragonfly.ui.component.button.PrimaryButton
import com.mr.nemo.dragonfly.ui.component.text.TitleText
import com.mr.nemo.dragonfly.ui.preview.OnboardingPagePreviewParameterProvider
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

@Composable
fun OnboardingPage(
    title: String,
    description: String,
    image: Painter,
    isSwipeble: Boolean,
    pageCount: Int,
    currentPage: Int,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    val spacing = DragonFlyTheme.spacing

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(spacing.xLarge))

        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            contentScale = ContentScale.FillHeight
        )

        Spacer(modifier = Modifier.height(spacing.large))

        Column(
            modifier = Modifier
                .padding(horizontal = spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitleText(
                text = title,
                color = colors.neutral2,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            Text(
                text = description,
                style = typography.text2.regular,
                color = colors.neutral4,
                textAlign = TextAlign.Center
            )

            AnimatedVisibility(visible = isSwipeble) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(spacing.xLarge))

                    Text(
                        text = stringResource(R.string.swipe_for_more),
                        style = typography.text1.medium,
                        color = colors.neutral2,
                    )
                    Spacer(modifier = Modifier.height(spacing.xSmall))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = stringResource(R.string.content_description_swipe_down_for_more),
                        tint = colors.primary.main
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing.xLarge))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val buttonText = if (currentPage == pageCount) {
                    R.string.button_get_started
                } else {
                    R.string.button_next
                }
                if (currentPage > 0) {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
                PrimaryButton(
                    text = stringResource(id = buttonText),
                    onClick = onNextClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(56.dp)
                )
            }

            Spacer(modifier = Modifier.height(spacing.large))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPagePreview(
    @PreviewParameter(OnboardingPagePreviewParameterProvider::class)
    isSwipeble: Boolean
) {
    DragonFlyTheme {
        OnboardingPage(
            title = "Easy to manage money",
            description = "Transfer and receive your money easily with dragonfly bank",
            image = painterResource(id = R.drawable.bg_onboarding_page_1),
            isSwipeble = isSwipeble,
            pageCount = 2,
            currentPage = 1,
            onNextClicked = {},
            onBackClicked = {}
        )
    }
}
