package ui.screen.main

import androidx.compose.runtime.Composable
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
import org.jetbrains.compose.resources.stringResource

data class MainScreenNavigationItem(
    val icon: DrawableResource,
    val title: String
) {


    companion object {

        @Composable
        fun items(): List<MainScreenNavigationItem> {
            return listOf(
                MainScreenNavigationItem(
                    icon = Res.drawable.ic_home,
                    title = stringResource(resource = Res.string.home)
                ),
                MainScreenNavigationItem(
                    icon = Res.drawable.ic_pocket,
                    title = stringResource(resource = Res.string.pocket)
                ),
                MainScreenNavigationItem(
                    icon = Res.drawable.ic_inbox,
                    title = stringResource(resource = Res.string.inbox)
                ),
                MainScreenNavigationItem(
                    icon = Res.drawable.ic_profile,
                    title = stringResource(resource = Res.string.profile)
                )
            )
        }
    }
}
