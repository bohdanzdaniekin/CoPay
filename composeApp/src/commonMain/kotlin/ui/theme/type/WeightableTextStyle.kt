package ui.theme.type

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class WeightableTextStyle(
    val regular: TextStyle = TextStyle.Default,
    val medium: TextStyle = TextStyle.Default,
    val bold: TextStyle = TextStyle.Default
) {

    companion object {

        val Default = WeightableTextStyle(
            regular = TextStyle.Default,
            medium = TextStyle.Default,
            bold = TextStyle.Default
        )
    }
}
