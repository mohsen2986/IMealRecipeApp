import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import di.homeModule
import org.kodein.di.compose.withDI
import ui.screen.splash.SplashScreen

@Composable
fun App() = withDI(homeModule) {
    MaterialTheme {
        Navigator(SplashScreen())
    }
}