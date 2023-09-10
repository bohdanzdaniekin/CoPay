package com.mr.nemo.dragonfly.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mr.nemo.dragonfly.R
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme
import com.mr.nemo.dragonfly.ui.theme.icons.DragonFlyIcons
import com.mr.nemo.dragonfly.ui.theme.icons.Translate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UntitledTopAppBar(
    onLanguageClicked: () -> Unit,
    onLogoClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {},
        modifier = modifier,
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onLogoClicked)
            )
        },
        actions = {
            Icon(
                imageVector = DragonFlyIcons.Translate,
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onLanguageClicked)
            )
        }
    )
}

@Preview
@Composable
fun UntitledTopAppBarPreview() {
    DragonFlyTheme {
        UntitledTopAppBar(
            onLanguageClicked = {},
            onLogoClicked = {}
        )
    }
}
