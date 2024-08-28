package ui.screen.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import dragonfly.composeapp.generated.resources.Res
import dragonfly.composeapp.generated.resources.content_description_scan
import dragonfly.composeapp.generated.resources.ic_scan
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.component.NavigationBarTabItem
import ui.component.TitledLogo
import ui.screen.main.home.HomeBottomTab
import ui.screen.main.inbox.InboxBottomTab
import ui.screen.main.pocket.PocketBottomTab
import ui.screen.main.profile.ProfileBottomTab
import ui.theme.DragonFlyTheme

class MainScreen : Screen {

    @Composable
    override fun Content() {
        TabNavigator(HomeBottomTab) {
            MainScreen {
                CurrentTab()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors

    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Hidden,
        skipHiddenState = false
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    
    LaunchedEffect(true) {
        scaffoldState.bottomSheetState.hide()
    }

    BottomSheetScaffold(
        sheetContent = {

        },
        modifier = modifier,
        scaffoldState = scaffoldState
    ) {
        Scaffold(
            modifier = Modifier,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {},
                    navigationIcon = {
                        TitledLogo(
                            onClick = {

                            }
                        )
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(resource = Res.drawable.ic_scan),
                                contentDescription = stringResource(resource = Res.string.content_description_scan),
                                tint = Color.Unspecified
                            )
                        }
                    }
                )
            },
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
                    listOf(
                        HomeBottomTab,
                        PocketBottomTab,
                        InboxBottomTab,
                        ProfileBottomTab
                    ).map { item ->
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
            },
            containerColor = colors.neutral8
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues),
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    DragonFlyTheme {
        MainScreen()
    }
}