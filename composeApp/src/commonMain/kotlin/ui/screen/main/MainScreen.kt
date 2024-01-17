package ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.screen.mealDetail.MealDetailScreen
import kotlin.math.sin

class MainScreen : Screen {

    @OptIn(ExperimentalResourceApi::class, ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel<RecipeViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        val uiState by viewModel.uiState.collectAsState()
        val keyboardController = LocalSoftwareKeyboardController.current

        Box {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(
                        color = Color(0xFF2C2F38)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.padding(top = 13.dp),
                    painter = painterResource("images/splash_logo.png"),
                    contentDescription = null
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(99.dp)
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    value = uiState.query,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF686F82),
                    ),
                    placeholder = {
                        Text(
                            text = "Find your dishes",
                            style = TextStyle(
                                color = Color(0xFF686F82)
                            )
                        )
                    },
                    onValueChange = {
                        viewModel.setQuery(it)
                    },
                    textStyle = TextStyle(
                        color = Color.White,
                    ),
                    singleLine = true,
                    maxLines = 1,
                    trailingIcon = {
                        when (uiState.searchResult) {
                            SearchResult.Error -> {}
                            SearchResult.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(25.dp)
                                        .align(Alignment.CenterHorizontally),
                                    strokeWidth = 2.dp,
                                    color = Color(0xffCC8B28),
//                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                )
                            }

                            is SearchResult.Success, null -> {
                                Icon(
                                    modifier = Modifier.wrapContentSize(),
                                    imageVector = Icons.Filled.Search,
                                    tint = Color(0xffCC8B28),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            viewModel.getRecipe()
                            keyboardController?.hide()
                        }
                    )
                )



                if (uiState.searchResult is SearchResult.Success) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = (uiState.searchResult as SearchResult.Success).meals.meals,
                            key = {
                                it.idMeal
                            }) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                                    .background(
                                        shape = RoundedCornerShape(20.dp),
                                        color = Color(0xff353842),
                                    )
                                    .clickable {
                                        navigator.push(MealDetailScreen(it.idMeal))
                                    }
                                    .clip(RoundedCornerShape(20.dp)),
                            ) {
                                KamelImage(
                                    modifier = Modifier.height(76.dp),
                                    resource = asyncPainterResource(it.strMealThumb),
                                    contentScale = ContentScale.FillWidth,
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier.padding(top = 19.dp)
                                        .align(Alignment.CenterHorizontally),
                                    text = it.strMeal,
                                    style = TextStyle(
                                        color = Color.White
                                    )
                                )

                                Text(
                                    modifier = Modifier.padding(top = 19.dp)
                                        .align(Alignment.CenterHorizontally),
                                    text = it.area + " " + it.category,
                                    style = TextStyle(
                                        color = Color.White
                                    )
                                )
                            }
                        }
                    }
                }

            }

            Button(
                modifier = Modifier
                    .width(160.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                onClick = {
                    viewModel.getRecipe()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffCC8B28) ,
                )
            ) {
                Text(
                    text = "Search" ,
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    }
}