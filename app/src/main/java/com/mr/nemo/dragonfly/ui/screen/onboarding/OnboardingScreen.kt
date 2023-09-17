package com.mr.nemo.dragonfly.ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.flowWithLifecycle
import com.mr.nemo.dragonfly.R
import com.mr.nemo.dragonfly.domain.entity.OnboardingContent
import com.mr.nemo.dragonfly.ui.component.UntitledTopAppBar
import com.mr.nemo.dragonfly.ui.entitiy.onboarding.OnboardingEffect
import com.mr.nemo.dragonfly.ui.entitiy.onboarding.OnboardingScreenEvent
import com.mr.nemo.dragonfly.ui.entitiy.onboarding.OnboardingScreenState
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner, lifecycleOwner) {
        viewModel
            .effect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collectLatest { effect ->
                when (effect) {
                    is OnboardingEffect.NavigateForward -> {
                        // TODO
                    }
                    is OnboardingEffect.NavigateBackward -> {
                        // TODO
                    }
                }
            }
    }

    val pagerState = rememberPagerState {
        state.pages.size
    }
    // FIXME: Fix animation on fast page scrolling
    LaunchedEffect(key1 = state) {
        snapshotFlow(state::currentPage)
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { currentPage ->
                if (currentPage != pagerState.currentPage) {
                    pagerState.animateScrollToPage(
                        page = currentPage,
                        pageOffsetFraction = pagerState.currentPageOffsetFraction
                    )
                }
            }
    }
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow(pagerState::currentPage)
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { page ->
                viewModel.onEvent(OnboardingScreenEvent.OnCurrentPageUpdated(page))
            }
    }
    OnboardingScreen(
        state = state,
        onEvent = viewModel::onEvent,
        pagerState = pagerState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardingScreen(
    state: OnboardingScreenState,
    onEvent: (event: OnboardingScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState {
        state.pages.size
    }
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Scaffold(
        topBar = {
            UntitledTopAppBar(
                onLanguageClicked = { /*TODO*/ },
                onLogoClicked = { /*TODO*/ },
                modifier = Modifier.padding(horizontal = spacing.medium)
            )
        },
        containerColor = colors.neutral8,
        modifier = modifier
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            contentPadding = paddingValues
        ) { pageIndex ->
            val content = state.pages[pageIndex]
            OnboardingPage(
                content = content,
                pageCount = state.pages.size,
                currentPage = pageIndex,
                onNextClicked = {
                    onEvent(OnboardingScreenEvent.OnScrollForward)
                },
                onBackClicked = {
                    onEvent(OnboardingScreenEvent.OnScrollBackward)
                },
                modifier = Modifier.graphicsLayer {
                    val pageOffset = pagerState.getOffsetFractionForPage(pageIndex)
                    translationX = pageOffset * size.width
                    alpha = 1 - pageOffset.absoluteValue
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    DragonFlyTheme {
        OnboardingScreen(
            state = OnboardingScreenState(
                pages = listOf(
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
            ),
            onEvent = {}
        )
    }
}
