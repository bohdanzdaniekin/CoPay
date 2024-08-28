package ui.screen.main.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.CrossfadeTransition
import ui.screen.main.MainScreenNavigationItem
import ui.screen.main.MainScreenTab

data object HomeBottomTab : MainScreenTab(MainScreenNavigationItem.home) {

    @Composable
    override fun Content() {
        Navigator(HomeScreen()) { navigator ->
            CrossfadeTransition(navigator)
        }
    }
}