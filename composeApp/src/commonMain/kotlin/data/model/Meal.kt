package data.model

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class Meals(
    @SerialName("meals")
    val meals: List<Meal> = emptyList()
)

@Serializable
data class Meal(
    @SerialName("idMeal")
    val idMeal: String,
    @SerialName("strCategory")
    val category: String,
    @SerialName("strMealThumb")
    val strMealThumb: String,
    @SerialName("strInstructions")
    val strInstructions: String,
    @SerialName("strMeal")
    val strMeal: String,
    @SerialName("strArea")
    val area: String ,
    @SerialName("strSource")
    val strSource: String,
    @SerialName("strTags")
    val strTags: String?,
    @SerialName("strYoutube")
    val strYoutube: String
)
