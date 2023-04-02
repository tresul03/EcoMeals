package com.mastercoding.hackathon2.ui.recipe

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.mastercoding.hackathon2.models.Ingredient


class addrecipeviewmodel : ViewModel() {

    var recipeImageUri: Uri? = null
    var ingredientsList = mutableListOf<Pair<String, String>>()

    fun addIngredient(name: String, manufacturer: String) {
        ingredientsList.add(Pair(name, manufacturer))
    }

    fun setImage(uri: Uri?) {
        recipeImageUri = uri
    }

    fun submitRecipe(name: String, image: Uri?, ingredients: List<Pair<String, String>>) {
        // Handle recipe submission here



    }

    fun reset() {
        recipeImageUri = null
        ingredientsList.clear()
    }
}