package ui.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ui.theme.DragonFlyTheme

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun BaseTextFieldPreview() {
    DragonFlyTheme {
        Column(
            modifier = Modifier
                .padding(DragonFlyTheme.spacing.medium)
                .fillMaxWidth()
        ) {

            var value1 by remember {
                mutableStateOf("Some Text")
            }
            BaseTextField(
                value = value1,
                onValueChange = { value1 = it },
                modifier = Modifier
                    .padding(DragonFlyTheme.spacing.medium)
                    .height(72.dp)
                    .fillMaxWidth()
            )

            var value2 by remember {
                mutableStateOf("")
            }
            BaseTextField(
                value = value2,
                onValueChange = { value2 = it },
                placeholder = "Placeholder",
                modifier = Modifier
                    .padding(DragonFlyTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(72.dp)
            )

            Row {
                var value3 by remember {
                    mutableStateOf("")
                }
                var value4 by remember {
                    mutableStateOf("")
                }
                var value5 by remember {
                    mutableStateOf("")
                }
                BaseTextField(
                    value = value3,
                    onValueChange = { value3 = it },
                    modifier = Modifier
                        .padding(DragonFlyTheme.spacing.medium)
                        .height(72.dp)
                        .weight(1f),
                    singleLine = true
                )
                BaseTextField(
                    value = value4,
                    onValueChange = { value4 = it },
                    placeholder = "Placeholder",
                    modifier = Modifier
                        .padding(DragonFlyTheme.spacing.medium)
                        .height(72.dp)
                        .weight(1f),
                    singleLine = true
                )
                BaseTextField(
                    value = value5,
                    onValueChange = { value5 = it },
                    label = "Placeholder",
                    modifier = Modifier
                        .padding(DragonFlyTheme.spacing.medium)
                        .height(72.dp)
                        .weight(1f),
                    singleLine = true
                )
            }

            var value6 by remember {
                mutableStateOf("Some text")
            }
            BaseTextField(
                value = value6,
                onValueChange = { value6 = it },
                placeholder = "Placeholder",
                label = "Label",
                modifier = Modifier
                    .padding(DragonFlyTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(72.dp)
            )
        }
    }
}