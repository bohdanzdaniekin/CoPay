package ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.main.MainScreenNavigationItem
import ui.screen.main.MainScreenTab
import ui.theme.DragonFlyTheme

@Composable
fun RowScope.NavigationBarTabItem(
    tab: Tab,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    label: @Composable (isSelected: Boolean) -> Unit = { Text(text = tab.options.title) },
    alwaysShowLabel: Boolean = true,
    icon: @Composable (isSelected: Boolean) -> Unit = {
        tab.options.icon?.let { icon ->
            Icon(
                painter = icon,
                contentDescription = tab.options.title,
            )
        }
    },
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors()
) {
    val navigator = LocalTabNavigator.current

    NavigationBarItem(
        modifier = modifier,
        selected = navigator.current == tab,
        onClick = {
            navigator.current = tab
            onClick()
        },
        label = { label(navigator.current == tab) },
        alwaysShowLabel = alwaysShowLabel,
        icon = { icon(navigator.current == tab) },
        colors = colors
    )
}

private val tabs = listOf(
    object : MainScreenTab(MainScreenNavigationItem.home) {
        override val key: ScreenKey
            get() = "Screen#Home"

        @Composable
        override fun Content() {
            Text("Home")
        }
    },
    object : MainScreenTab(MainScreenNavigationItem.pocket) {
        override val key: ScreenKey
            get() = "Screen#Pocket"

        @Composable
        override fun Content() {
            Text("Pocket")
        }
    },
    object : MainScreenTab(MainScreenNavigationItem.inbox) {
        override val key: ScreenKey
            get() = "Screen#Inbox"

        @Composable
        override fun Content() {
            Text("Inbox")
        }
    },
    object : MainScreenTab(MainScreenNavigationItem.profile) {
        override val key: ScreenKey
            get() = "Screen#Profile"

        @Composable
        override fun Content() {
            Text("Profile")
        }
    },
)

@Preview
@Composable
fun NavigationBarTabItemPreview() {
    DragonFlyTheme {
        val spacing = DragonFlyTheme.spacing
        val typography = DragonFlyTheme.typography
        val colors = DragonFlyTheme.colors
        TabNavigator(tabs.first()) {
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                spotColor = Color(0x1F000000),
                                ambientColor = Color(0x1F000000)
                            )
                            .padding(top = spacing.xSmall),
                        containerColor = Color.Transparent,
                        contentColor = colors.neutral8,
                        tonalElevation = 4.dp,
                    ) {
                        tabs.map { item ->
                            NavigationBarTabItem(
                                tab = item,
                                icon = { isSelected ->
                                    item.options.icon?.let { icon ->
                                        BadgedBox(badge = {}) {
                                            AnimatedContent(isSelected) { state ->
                                                Icon(
                                                    painter = icon,
                                                    contentDescription = item.options.title,
                                                    modifier = Modifier
                                                        .size(24.dp)
                                                        .scale(if (state) 1.2f else 1.1f)
                                                )
                                            }
                                        }
                                    }
                                },
                                label = {
                                    Text(
                                        text = item.options.title,
                                        style = typography.text2.regular,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                alwaysShowLabel = true,
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = colors.primary.main,
                                    selectedTextColor = colors.primary.main,
                                    unselectedIconColor = colors.neutral6,
                                    unselectedTextColor = colors.neutral6,
                                    indicatorColor = Color.Transparent
                                )
                            )
                        }
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}