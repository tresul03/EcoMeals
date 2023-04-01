package com.mastercoding.hackathon2.ui.recipe

import androidx.lifecycle.ViewModel

class addrecipeviewmodel : ViewModel() {
    val ingredientsList = mutableListOf<Pair<String, String>>()

    fun addIngredient(name: String, manufacturer: String) {
        ingredientsList.add(Pair(name, manufacturer))
    }
}