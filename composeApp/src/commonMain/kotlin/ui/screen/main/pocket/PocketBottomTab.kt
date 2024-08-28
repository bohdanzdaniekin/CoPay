package ui.screen.main.pocket

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.CrossfadeTransition
import ui.screen.main.MainScreenNavigationItem
import ui.screen.main.MainScreenTab

data object PocketBottomTab : MainScreenTab(MainScreenNavigationItem.pocket) {

    @Composable
    override fun Content() {
        Navigator(PocketScreen()) { navigator ->
            CrossfadeTransition(navigator)
        }
    }
}