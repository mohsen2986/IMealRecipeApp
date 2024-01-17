package di

import ui.screen.main.RecipeViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance
import ui.screen.mealDetail.MealDetailScreen
import ui.screen.mealDetail.MealDetailViewModel

val homeModule = DI.Module(name = "home") {
    bindProvider { RecipeViewModel() }
    bindProvider { MealDetailViewModel() }
}