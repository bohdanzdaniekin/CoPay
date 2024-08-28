package ui.screen.main.inbox

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.CrossfadeTransition
import ui.screen.main.MainScreenNavigationItem
import ui.screen.main.MainScreenTab

data object InboxBottomTab : MainScreenTab(MainScreenNavigationItem.inbox) {

    @Composable
    override fun Content() {
        Navigator(InboxScreen()) { navigator ->
            CrossfadeTransition(navigator)
        }
    }
}