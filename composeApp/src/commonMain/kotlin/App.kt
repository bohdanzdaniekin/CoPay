import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.onboarding.OnboardingScreen
import ui.theme.DragonFlyTheme

@Composable
@Preview
fun App() {
    DragonFlyTheme {
        OnboardingScreen()
    }
}