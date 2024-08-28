package ui.entitiy.main.home

import ui.entitiy.core.UiState
import ui.entitiy.main.home.currencies as fakeCurrencies

data class HomeScreenState(
    val balance: String = "49,250.00",
    val ad: HomeAd = HomeAd(
        title = "Let's connect",
        description = "Connect account with marketplace for automatic payment and get \$25 bonus",
        isVisible = false
    ),
    val currency: CurrencyState = CurrencyState(
        currencies = fakeCurrencies,
        updatedAt = "Updated 2 minutes ago"
    )
) : UiState
