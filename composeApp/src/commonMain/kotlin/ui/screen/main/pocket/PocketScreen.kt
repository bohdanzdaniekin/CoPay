package ui.screen.main.pocket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.component.CreditCardItem
import ui.theme.DragonFlyTheme

class PocketScreen : Screen {

    @Composable
    override fun Content() {
        PocketScreenContent()
    }
}

@Composable
fun PocketScreenContent(modifier: Modifier = Modifier) {
    val spacing = DragonFlyTheme.spacing
    val typography = DragonFlyTheme.typography
    val colors = DragonFlyTheme.colors
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .padding(horizontal = spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(spacing.xSmall),
        verticalArrangement = Arrangement.spacedBy(spacing.xSmall)
    ) {
        item(
            span = { GridItemSpan(2) }
        ) {
            val filters = listOf("All", "Saving", "Family", "Investment", "Sex", "Dugs", "Rock & Roll")
            var selected by remember {
                mutableStateOf(filters.first())
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                LazyRow(
                ) {
                    items(filters) { filter ->
                        FilterChip(
                            selected = filter == selected,
                            onClick = {
                                selected = filter
                            },
                            label = {
                                Text(
                                    text = filter,
                                    style = typography.text2.medium
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                labelColor = colors.neutral2,
                                selectedLabelColor = colors.primary.main,
                                selectedContainerColor = colors.neutral8
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = selected == filter,
                                borderColor = colors.neutral7,
                                selectedBorderColor = colors.primary.main
                            )
                        )
                        Spacer(Modifier.width(12.dp))
                    }
                    item {
                        Spacer(Modifier.width(24.dp))
                    }
                }
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .background(Color.White)
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add tab"
                    )
                }
            }
        }

        items(
            items = (0..3).toList(),
        ) { item ->
            CreditCardItem() // TODO
        }
    }
}

@Preview
@Composable
fun PocketScreenContentPreview() {
    DragonFlyTheme {
        PocketScreenContent()
    }
}