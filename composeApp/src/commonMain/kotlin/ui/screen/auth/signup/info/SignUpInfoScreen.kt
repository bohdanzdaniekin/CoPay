package ui.screen.auth.signup.info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import domain.entity.auth.signup.InfoItem
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.content_description_navigate_back
import dragonfly.composeapp.generated.resources.frequently_read_articles
import dragonfly.composeapp.generated.resources.ic_arrow_back
import dragonfly.composeapp.generated.resources.ic_book
import dragonfly.composeapp.generated.resources.information
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ui.component.appbar.TitledTopAppBar
import ui.component.text.TitleText
import ui.entitiy.auth.signup.info.SignUpInfoScreenEvent
import ui.entitiy.auth.signup.info.SignUpInfoScreenState
import ui.theme.DragonFlyTheme
import utils.extensions.collectAsStateWithLifecycle

@Composable
fun SignUpInfoScreen(
    viewModel: SignUpInfoViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SignUpInfoScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpInfoScreen(
    state: SignUpInfoScreenState,
    onEvent: (SignUpInfoScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Scaffold(
        topBar = {
            TitledTopAppBar(
                title = stringResource(resource = Res.string.information),
                navigationIcon = {
                    IconButton(onClick = { onEvent(SignUpInfoScreenEvent.OnBackClicked) }) {
                        Icon(
                            painter = painterResource(resource = Res.drawable.ic_arrow_back),
                            contentDescription = stringResource(resource = Res.string.content_description_navigate_back)
                        )
                    }
                },
                modifier = Modifier.padding(horizontal = spacing.medium)
            )
        },
        containerColor = colors.neutral8,
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = spacing.medium),

            ) {
            Spacer(modifier = Modifier.height(spacing.large))

            TitleText(
                text = stringResource(resource = Res.string.frequently_read_articles),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = typography.subtitle1.regular,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(spacing.xLarge))

            LazyColumn {
                items(state.items) { item ->
                    Spacer(modifier = Modifier.height(spacing.medium))
                    SignUpInfoItem(
                        item = item,
                        modifier = Modifier.clickable {
                            onEvent(SignUpInfoScreenEvent.OnItemClicked(item))
                        }
                    )
                    Spacer(modifier = Modifier.height(spacing.medium))
                    HorizontalDivider(color = colors.neutral7)
                }
            }
        }
    }
}

@Composable
fun SignUpInfoItem(
    item: InfoItem,
    modifier: Modifier = Modifier
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(resource = Res.drawable.ic_book),
                contentDescription = null,
                tint = colors.primary.main
            )

            Spacer(modifier = Modifier.width(spacing.xSmall))

            Text(
                text = item.title,
                style = typography.text1.medium,
                color = colors.neutral2
            )
        }

        Spacer(modifier = Modifier.height(spacing.medium))

        Text(
            text = item.subtitle,
            style = typography.text2.regular,
            color = colors.neutral6
        )
    }
}

@Preview
@Composable
private fun SignUpInfoScreenPreview() {
    DragonFlyTheme {
        SignUpInfoScreen(
            state = SignUpInfoScreenState(
                items = listOf(
                    InfoItem(
                        title = "How to reset password",
                        subtitle = "If you forget your password, you can request a password reset via email or the number you registered"
                    ),
                    InfoItem(
                        title = "How to create a virtual account",
                        subtitle = "To create a virtual account you need to raise your customer status to priority"
                    )
                )
            ),
            onEvent = {}
        )
    }
}
