package ui.entitiy.main.home

data class Currency(
    val name: String,
    val price: Double,
    val rates: Double
)

data class CurrencyState(
    val currencies: List<Currency> = emptyList(),
    val updatedAt: String = ""
)

val currencies = listOf(
    Currency(
        name = "USD",
        price = 1.00,
        rates = 1.00
    ),
    Currency(
        name = "EURO",
        price = 1.00,
        rates = 0.92
    ),
    Currency(
        name = "POND",
        price = 1.00,
        rates = 0.79
    ),
    Currency(
        name = "JPY",
        price = 1.00,
        rates = 144.34
    )
)
