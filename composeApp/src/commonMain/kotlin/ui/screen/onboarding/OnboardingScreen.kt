package ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import domain.entity.OnboardingContent
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.bg_onboarding_page_1
import dragonfly.composeapp.generated.resources.bg_onboarding_page_2
import dragonfly.composeapp.generated.resources.bg_onboarding_page_3
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ui.component.appbar.UntitledTopAppBar
import ui.entitiy.onboarding.OnboardingScreenEffect
import ui.entitiy.onboarding.OnboardingScreenEvent
import ui.entitiy.onboarding.OnboardingScreenState
import ui.theme.DragonFlyTheme
import utils.extensions.collectAsEffect
import utils.extensions.collectAsStateWithLifecycle
import kotlin.math.absoluteValue

class OnboardingScreen : Screen {

    @Composable
    override fun Content() {
        TODO("Not yet implemented")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is OnboardingScreenEffect.NavigateForward -> {
                // TODO
            }
            is OnboardingScreenEffect.NavigateBackward -> {
                // TODO
            }
        }
    }

    val pagerState = rememberPagerState {
        state.pages.size
    }
    // FIXME: Fix animation on fast page scrolling
    LaunchedEffect(key1 = state.currentPage) {
        if (state.currentPage != pagerState.currentPage) {
            pagerState.animateScrollToPage(
                page = state.currentPage
            )
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
                onLogoClicked = { /*TODO*/ }
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
@Preview
@Composable
private fun OnboardingScreenPreview() {
    DragonFlyTheme {
        OnboardingScreen(
            state = OnboardingScreenState(
                pages = listOf(
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
            ),
            onEvent = {}
        )
    }
}
