package ui.screen.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import domain.entity.auth.signup.SignUpField
import domain.entity.auth.signup.SignUpPageContent
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.button_next
import dragonfly.composeapp.generated.resources.content_description_navigate_back
import dragonfly.composeapp.generated.resources.content_description_sign_up_faq
import dragonfly.composeapp.generated.resources.ic_arrow_back
import dragonfly.composeapp.generated.resources.ic_info
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ui.component.appbar.TitledTopAppBar
import ui.component.button.PrimaryButton
import ui.entitiy.signup.SignUpScreenEvent
import ui.entitiy.signup.SignUpScreenState
import ui.theme.DragonFlyTheme
import utils.extensions.collectAsStateWithLifecycle

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SignUpScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignUpScreen(
    state: SignUpScreenState,
    onEvent: (SignUpScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors

    val page = state.pages[state.currentPage]

    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                TitledTopAppBar(
                    title = page.title,
                    navigationIcon = {
                        IconButton(onClick = { onEvent(SignUpScreenEvent.OnBackClicked) }) {
                            Icon(
                                painter = painterResource(resource = Res.drawable.ic_arrow_back),
                                contentDescription = stringResource(resource = Res.string.content_description_navigate_back)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { onEvent(SignUpScreenEvent.OnInfoClicked as SignUpScreenEvent.OnInfoClicked) }) {
                            Icon(
                                painter = painterResource(resource = Res.drawable.ic_info),
                                contentDescription = stringResource(resource = Res.string.content_description_sign_up_faq),
                                tint = Color.Unspecified
                            )
                        }
                    }
                )
            }
        },
        containerColor = colors.neutral8
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            AnimatedVisibility(visible = page.isProgressVisible) {
                LinearProgressIndicator(
                    progress = { state.progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(8.dp),
                    color = colors.primary.main,
                    trackColor = colors.neutral7,
                    strokeCap = StrokeCap.Square,
                )
            }

            Spacer(modifier = Modifier.height(spacing.medium))

            Column(
                modifier = Modifier
                    .padding(horizontal = spacing.medium)
            ) {

                HorizontalPager(
                    state = rememberPagerState {
                        state.pages.size
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.Top,
                    userScrollEnabled = false
                ) { page ->
                    when (val currentPage = state.pages[page]) {
                        is SignUpPageContent.RegisterPageContent<*> -> {
                            SignUpFieldPage(
                                field = currentPage.field,
                                onValueChanged = { field ->
                                    /*onEvent(
                                        SignUpScreenEvent.OnFieldUpdated(field) as SignUpScreenEvent.OnFieldUpdated
                                    )*/
                                }
                            )
                        }
                        is SignUpPageContent.VerificationPageContent -> {
                            SignUpVerificationPage(
                                state = currentPage.state,
                                onEvent = onEvent
                            )
                        }
                    }
                }

                PrimaryButton(
                    text = stringResource(resource = Res.string.button_next),
                    onClick = {
                        onEvent(SignUpScreenEvent.OnNextClicked)
                    },
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(56.dp)
                )

                Spacer(modifier = Modifier.height(spacing.large))
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    DragonFlyTheme {
        val currentPage = 1
        val steps = signUpSteps(false)
        SignUpScreen(
            state = SignUpScreenState(
                currentPage = currentPage,
                pages = steps,
                progress = (currentPage.toFloat() + 1f) / steps.size
            ),
            onEvent = {}
        )
    }
}
