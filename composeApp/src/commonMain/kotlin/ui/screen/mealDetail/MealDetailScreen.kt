package ui.screen.mealDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.model.Meal
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.screen.main.RecipeViewModel

class MealDetailScreen(
    private val meal: Meal
) : Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            Modifier
                .fillMaxSize()
                .background(
                    color = Color(0xFF2C2F38)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(12.dp)
                    .clickable {
                        navigator.pop()
                    },
                painter = painterResource("images/back_arro.xml"),
                contentDescription = null
            )


            KamelImage(
                modifier = Modifier.height(276.dp),
                resource = asyncPainterResource(meal.strMealThumb),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(top = 19.dp)
                    .align(Alignment.CenterHorizontally),
                text = meal.strMeal,
                style = TextStyle(
                    color = Color.White ,

                )
            )

            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 19.dp, horizontal = 15.dp)
                    .align(Alignment.CenterHorizontally),
                text = meal.strInstructions,
                style = TextStyle(
                    color = Color.White,
                ),
            )
        }
    }

}