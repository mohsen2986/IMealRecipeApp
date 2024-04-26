package ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.screen.main.MainScreen

class SplashScreen : Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(Unit) {
            delay(1_000)
            navigator.replace(MainScreen())
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource("images/splash_bg.jpeg"),
                contentScale = ContentScale.FillHeight,
                contentDescription = null
            )



            Box(
//                #606060, #040404C2, #000000D6
                modifier = Modifier.fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0Xd2606060),
                                Color(0Xc2040404),
                                Color(0Xd6000000),

                            )
                        )
                    )
            )

            Image(
                modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
                painter = painterResource("images/clouds.png"),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )

            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource("images/splash_logo.png"),
                contentDescription = null
            )
        }
    }

}