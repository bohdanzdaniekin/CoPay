package ui.screen.main.profile

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.CrossfadeTransition
import ui.screen.main.MainScreenNavigationItem
import ui.screen.main.MainScreenTab

data object ProfileBottomTab : MainScreenTab(MainScreenNavigationItem.profile) {

    @Composable
    override fun Content() {
        Navigator(ProfileScreen()) { navigator ->
            CrossfadeTransition(navigator)
        }
    }
}