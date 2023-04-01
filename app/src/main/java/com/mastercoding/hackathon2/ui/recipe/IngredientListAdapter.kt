package com.mastercoding.hackathon2.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mastercoding.hackathon2.R

class IngredientListAdapter : RecyclerView.Adapter<IngredientListAdapter.ViewHolder>() {

    var ingredientsList = mutableListOf<Pair<String, String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredientsList[position]
        holder.bind(ingredient)
    }

    override fun getItemCount() = ingredientsList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(ingredient: Pair<String, String>) {
            itemView.ingredientName.text = ingredient.first
            itemView.ingredientManufacturer.text = ingredient.second
        }
    }
}