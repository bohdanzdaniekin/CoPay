package ui.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.home
import dragonfly.composeapp.generated.resources.ic_home
import dragonfly.composeapp.generated.resources.ic_inbox
import dragonfly.composeapp.generated.resources.ic_pocket
import dragonfly.composeapp.generated.resources.ic_profile
import dragonfly.composeapp.generated.resources.inbox
import dragonfly.composeapp.generated.resources.pocket
import dragonfly.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


data class MainScreenNavigationItem(
    val icon: DrawableResource,
    val title: StringResource,
    val index: Int
) {

    companion object {

        val home: MainScreenNavigationItem
            get() = MainScreenNavigationItem(
                icon = Res.drawable.ic_home,
                title = Res.string.home,
                index = 0
            )

        val pocket: MainScreenNavigationItem
            get() = MainScreenNavigationItem(
                icon = Res.drawable.ic_pocket,
                title = Res.string.pocket,
                index = 1
            )

        val inbox: MainScreenNavigationItem
            get() = MainScreenNavigationItem(
                icon = Res.drawable.ic_inbox,
                title = Res.string.inbox,
                index = 2
            )

        val profile: MainScreenNavigationItem
            get() = MainScreenNavigationItem(
                icon = Res.drawable.ic_profile,
                title = Res.string.profile,
                index = 3
            )
    }
}

@Composable
fun MainScreenNavigationItem.toTabOption(): TabOptions {
    val icon = painterResource(resource = icon)
    val title = stringResource(title)
    return remember {
        TabOptions(
            icon = icon,
            title = title,
            index = index.toUShort()
        )
    }
}

abstract class MainScreenTab(
    private val navigationItem: MainScreenNavigationItem
) : Tab {

    override val options: TabOptions
        @Composable
        get() = navigationItem.toTabOption()
}