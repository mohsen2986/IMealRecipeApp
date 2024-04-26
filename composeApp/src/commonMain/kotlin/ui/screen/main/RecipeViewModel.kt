package ui.screen.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.KtorClient
import data.model.Meals
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val query: String = "" ,
    val searchResult: SearchResult? = null
)

sealed interface SearchResult{
    data object Error: SearchResult
    data object Loading: SearchResult
    data class Success(
        val meals: Meals
    ): SearchResult

}

class RecipeViewModel: ScreenModel{
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun setQuery(string: String){
        _uiState.update {
            it.copy(
                query = string
            )
        }
    }
    
    fun getRecipe(){
        screenModelScope.launch {
            _uiState.update {
                it.copy(
                    searchResult = SearchResult.Loading
                )
            }
            try {
                val callback = KtorClient.client.get("https://www.themealdb.com/api/json/v1/1/search.php?s=${_uiState.value.query}")
                val response: Meals  = callback.body()

                _uiState.update {
                    it.copy(
                        searchResult = SearchResult.Success(response)
                    )
                }
            }catch (e: Exception){
                println(e)
                _uiState.update {
                    it.copy(
                        searchResult = SearchResult.Error
                    )
                }
            }

        }
    }
} 