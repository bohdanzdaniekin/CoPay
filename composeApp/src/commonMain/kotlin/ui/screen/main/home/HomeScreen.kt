package ui.screen.main.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.button_create
import dragonfly.composeapp.generated.resources.button_view_more
import dragonfly.composeapp.generated.resources.content_description_close_ad
import dragonfly.composeapp.generated.resources.content_description_currency
import dragonfly.composeapp.generated.resources.content_description_my_pocket
import dragonfly.composeapp.generated.resources.content_description_navigate_forward
import dragonfly.composeapp.generated.resources.currency
import dragonfly.composeapp.generated.resources.history
import dragonfly.composeapp.generated.resources.ic_arrow_forward
import dragonfly.composeapp.generated.resources.ic_close_square
import dragonfly.composeapp.generated.resources.ic_currency
import dragonfly.composeapp.generated.resources.ic_money_history
import dragonfly.composeapp.generated.resources.ic_money_request
import dragonfly.composeapp.generated.resources.ic_money_send
import dragonfly.composeapp.generated.resources.ic_wallet
import dragonfly.composeapp.generated.resources.my_pocket
import dragonfly.composeapp.generated.resources.request
import dragonfly.composeapp.generated.resources.send
import dragonfly.composeapp.generated.resources.your_balance
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ui.component.AdCard
import ui.component.CreditCardItem
import ui.component.TableCell
import ui.component.text.HideableText
import ui.entitiy.main.home.HomeAd
import ui.entitiy.main.home.HomeScreenEvent
import ui.entitiy.main.home.HomeScreenState
import ui.theme.DragonFlyTheme
import ui.theme.shapes
import utils.extensions.collectAsEffect
import utils.extensions.collectAsStateWithLifecycle
import utils.extensions.format

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        viewModel.effect.collectAsEffect { effect ->
            when (effect) {
                else -> {}
            }
        }

        HomeScreenContent(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .padding(horizontal = spacing.medium),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(spacing.xSmall),
        verticalArrangement = Arrangement.spacedBy(spacing.xSmall)
    ) {

        item(
            span = { GridItemSpan(2) }
        ) {
            HomePageHeader(balance = state.balance,
                ad = state.ad,
                onSend = { onEvent(HomeScreenEvent.SendMoney) },
                onRequest = { onEvent(HomeScreenEvent.RequestMoney) },
                onHistory = { onEvent(HomeScreenEvent.ViewHistory) },
                onCreatePocket = { onEvent(HomeScreenEvent.CreatePocket) },
                onForwardFromAd = { onEvent(HomeScreenEvent.ForwardFromAd) },
                onCloseAd = { onEvent(HomeScreenEvent.CloseAd) }
            )
        }

        items(
            items = (0..3).toList(),
        ) { item ->
            CreditCardItem(

            ) // TODO
        }
        item(
            span = { GridItemSpan(2) }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = stringResource(resource = Res.string.button_view_more),
                        style = typography.text2.medium,
                        color = colors.primary.main
                    )
                }
            }
        }

        item(
            span = { GridItemSpan(2) }
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(resource = Res.drawable.ic_currency),
                        contentDescription = stringResource(resource = Res.string.content_description_currency),
                        tint = colors.primary.main
                    )

                    Spacer(modifier = Modifier.width(spacing.xSmall))

                    Text(
                        text = stringResource(resource = Res.string.currency),
                        style = typography.subtitle2.medium,
                        color = colors.neutral2
                    )
                }

                Spacer(modifier = Modifier.height(spacing.medium))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colors.neutral7,
                            shape = shapes.small
                        )
                        .padding(vertical = spacing.small)
                ) {
                    Row {
                        TableCell(
                            text = "Currency", // TODO
                            weight = 1f,
                            style = typography.text1.regular,
                            color = colors.neutral2
                        )
                        TableCell(
                            text = "Price", // TODO
                            weight = 1f,
                            style = typography.text1.regular,
                            color = colors.neutral2
                        )
                        TableCell(
                            text = "Rates", // TODO
                            weight = 1f,
                            style = typography.text1.regular,
                            color = colors.neutral2
                        )
                    }
                    state.currency.currencies.forEach { currency ->
                        Row(
                            Modifier.padding(top = spacing.small)
                        ) {
                            TableCell(
                                text = currency.name,
                                weight = 1f
                            )
                            TableCell(
                                text = currency.price.format(2),
                                weight = 1f
                            )
                            TableCell(
                                text = currency.rates.format(2),
                                weight = 1f
                            )
                        }
                    }

                    TextButton(
                        onClick = { onEvent(HomeScreenEvent.UpdateCurrencies) },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = state.currency.updatedAt,
                            style = typography.text2.regular,
                            color = colors.primary.main
                        )
                    }
                }

                Spacer(modifier = Modifier.height(spacing.medium))
            }
        }
    }
}

@Composable
private fun HomePageHeader(
    balance: String,
    ad: HomeAd,
    modifier: Modifier = Modifier,
    onSend: () -> Unit,
    onRequest: () -> Unit,
    onHistory: () -> Unit,
    onCreatePocket: () -> Unit,
    onCloseAd: () -> Unit = {},
    onForwardFromAd: () -> Unit = {}
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(spacing.medium))

        Balance(
            balance = "\$ $balance" // TODO
        )

        Spacer(modifier = Modifier.height(spacing.medium))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colors.neutral7,
                    shape = shapes.small
                )
                .padding(vertical = spacing.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MoneyOperationItem(
                text = stringResource(resource = Res.string.send),
                icon = painterResource(resource = Res.drawable.ic_money_send),
                onClick = onSend
            )
            MoneyOperationItem(
                text = stringResource(resource = Res.string.request),
                icon = painterResource(resource = Res.drawable.ic_money_request),
                onClick = onRequest
            )
            MoneyOperationItem(
                text = stringResource(resource = Res.string.history),
                icon = painterResource(resource = Res.drawable.ic_money_history),
                onClick = onHistory
            )
        }

        Spacer(modifier = Modifier.height(spacing.medium))

        AnimatedVisibility(visible = ad.isVisible) {
            AdBanner(
                title = ad.title,
                description = ad.description,
                onClose = onCloseAd,
                onNavigateForward = onForwardFromAd,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }

        Spacer(modifier = Modifier.height(spacing.medium))

        MyPocket(
            onCreate = onCreatePocket
        )
    }
}

@Composable
private fun MyPocket(
    onCreate: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(resource = Res.drawable.ic_wallet),
                contentDescription = stringResource(resource = Res.string.content_description_my_pocket),
                modifier = Modifier.padding(spacing.xSmall),
                tint = colors.primary.main
            )
            Text(
                text = stringResource(resource = Res.string.my_pocket),
                style = typography.subtitle2.medium,
                color = colors.neutral2
            )
        }

        TextButton(onClick = onCreate) {
            Text(
                text = stringResource(resource = Res.string.button_create),
                style = typography.text2.medium,
                color = colors.primary.main
            )
        }
    }
}

@Composable
private fun AdBanner(
    title: String,
    description: String,
    onClose: () -> Unit,
    onNavigateForward: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    AdCard(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(spacing.large))

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(spacing.large))

                Text(
                    text = title,
                    style = typography.subtitle1.medium,
                    color = colors.secondary.main
                )

                Spacer(modifier = Modifier.height(spacing.medium))

                Text(
                    text = description,
                    style = typography.text2.regular,
                    color = colors.neutral5
                )

                Spacer(modifier = Modifier.height(DragonFlyTheme.spacing.large))
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = onClose
                ) {
                    Icon(
                        painter = painterResource(resource = Res.drawable.ic_close_square),
                        contentDescription = stringResource(resource = Res.string.content_description_close_ad)
                    )
                }

                IconButton(
                    onClick = onNavigateForward
                ) {
                    Icon(
                        painter = painterResource(resource = Res.drawable.ic_arrow_forward),
                        contentDescription = stringResource(resource = Res.string.content_description_navigate_forward)
                    )
                }
            }
        }
    }
}

@Composable
private fun Balance(
    balance: String,
    modifier: Modifier = Modifier,
    maskChar: Char = '*'
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(resource = Res.string.your_balance),
            style = typography.text2.regular,
            color = colors.neutral5
        )

        Spacer(modifier = Modifier.height(spacing.xSmall))

        HideableText(
            text = balance,
            maskChar = maskChar,
            textStyle = typography.header2.regular,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    DragonFlyTheme {
        HomeScreenContent(
            state = HomeScreenState(),
            onEvent = {}
        )
    }
}
