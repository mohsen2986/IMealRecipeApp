package ui.screen.mealDetail

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.KtorClient
import data.model.Meals
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MealDetailScreenUiState(
    val name: String = "",
    val lastname: String = "",
)

class MealDetailViewModel(
) : ScreenModel{
    private val _uiState: MutableStateFlow<MealDetailScreenUiState> = MutableStateFlow(MealDetailScreenUiState())
    val uiState: StateFlow<MealDetailScreenUiState> = _uiState.asStateFlow()


    fun getMealDetail(
        mealId: String
    ){
        screenModelScope.launch {
            val callback = KtorClient.client.get("https://www.themealdb.com/api/json/v1/1/lookup.php?i=${mealId}")
            val response: Meals = callback.body()

            _uiState.update {
                it.copy(
                    name = response.meals.first().strMeal,
                    lastname = response.meals.first().strMeal,
                )
            }
        }
    }
}