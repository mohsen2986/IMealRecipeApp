package di

import ui.screen.main.RecipeViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider

val homeModule = DI.Module(name = "home") {
    bindProvider { RecipeViewModel() }
}