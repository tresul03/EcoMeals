package com.mastercoding.hackathon2.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mastercoding.hackathon2.R
import com.mastercoding.hackathon2.models.Ingredient

class IngredientListAdapter(private val ingredientList: MutableList<Ingredient>) :
    RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ingredient_list_item, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = ingredientList[position]
        holder.ingredientName.text = ingredient.name
        holder.ingredientManufacturer.text = ingredient.manufacturer
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientName: TextView = itemView.findViewById(R.id.ingredientName)
        val ingredientManufacturer: TextView =
            itemView.findViewById(R.id.ingredientManufacturer)
    }
}