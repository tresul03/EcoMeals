package com.mastercoding.hackathon2.ui.recipe

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mastercoding.hackathon2.R

class addrecipefragment : Fragment() {
    private lateinit var viewModel: addrecipeviewmodel
    private lateinit var adapter: IngredientListAdapter

    private lateinit var recipeImage: ImageView
    private lateinit var addImageButton: ImageButton
    private lateinit var recipeNameInput: EditText

    private lateinit var ingredientNameInput: EditText
    private lateinit var ingredientManufacturerInput: EditText
    private lateinit var addIngredientButton: Button
    private lateinit var ingredientList: RecyclerView
    private lateinit var submitButton: Button
    private lateinit var button: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("H111")
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
4


        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
//            viewModel.setImage(imageUri)
            recipeImage.setImageURI(imageUri)
        }
    }



    companion object {
        const val REQUEST_CODE_IMAGE = 100
    }
}