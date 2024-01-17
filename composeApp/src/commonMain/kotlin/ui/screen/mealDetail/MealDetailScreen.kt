package ui.screen.mealDetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import ui.screen.main.RecipeViewModel

class MealDetailScreen(
    private val mealId: String
): Screen{

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel<MealDetailViewModel>()
        viewModel.getMealDetail(mealId)
        val uiState by viewModel.uiState.collectAsState()

        Text("content id ${uiState.name}")
    }

}