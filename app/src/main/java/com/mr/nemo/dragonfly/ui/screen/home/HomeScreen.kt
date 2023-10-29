package com.mr.nemo.dragonfly.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.nemo.dragonfly.R
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

// TODO: Unfinished. Only base structure of screen for a while
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    // TODO: Extract into separate title logo component
                    Row(
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_logo),
                                contentDescription = null,
                                tint = colors.primary.main
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.app_name_title).uppercase(),
                            style = typography.subtitle2.bold,
                            color = colors.neutral2,
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            minLines = 2,
                            maxLines = 2,
                            modifier = Modifier.offset {
                                IntOffset(x = -8.dp.toPx().toInt(), y = 0)
                            }
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_scan),
                            contentDescription = stringResource(id = R.string.content_description_scan),
                            tint = Color.Unspecified
                        )
                    }
                }
            )
        },
        bottomBar = {

        },
        containerColor = colors.neutral8
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = spacing.medium)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.your_balance),
                style = typography.text2.regular,
                color = colors.neutral5
            )

            Spacer(modifier = Modifier.height(spacing.xSmall))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var isVisible by rememberSaveable {
                    mutableStateOf(true)
                }

                Text(
                    text = "\$ 49,250.00",
                    style = typography.header2.regular,
                    color = colors.neutral2
                )

                IconButton(onClick = { isVisible = !isVisible }) {
                    Icon(
                        imageVector = if (isVisible) {
                            Icons.Outlined.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        },
                        contentDescription = if (isVisible) {
                            stringResource(R.string.content_description_hide_balance)
                        } else {
                            stringResource(R.string.content_description_show_balance)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing.medium))

            Row {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DragonFlyTheme {
        HomeScreen()
    }
}
