package com.mastercoding.hackathon2.ui.recipe

import android.annotation.SuppressLint
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
    private val viewModel: addrecipeviewmodel by activityViewModels()

    private lateinit var recipeImage: ImageView
    private lateinit var addImageButton: ImageButton
    private lateinit var recipeNameInput: EditText

    private lateinit var ingredientNameInput: EditText
    private lateinit var ingredientManufacturerInput: EditText
    private lateinit var addIngredientButton: Button
    private lateinit var ingredientList: RecyclerView
    private lateinit var submitButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        recipeImage = view.findViewById(R.id.recipeImage)
        addImageButton = view.findViewById(R.id.addImageButton)
        recipeNameInput = view.findViewById(R.id.recipeName);
        ingredientNameInput = view.findViewById(R.id.ingredientNameInput)
        ingredientManufacturerInput = view.findViewById(R.id.ingredientManufacturerInput)
        addIngredientButton = view.findViewById(R.id.addIngredientButton)
        ingredientList = view.findViewById(R.id.ingredientList)
        submitButton = view.findViewById(R.id.submit)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView
        ingredientList.layoutManager = LinearLayoutManager(requireContext())
        val adapter = IngredientListAdapter()
        ingredientList.adapter = adapter

        // Handle adding an ingredient
        addIngredientButton.setOnClickListener {
            val name = ingredientNameInput.text.toString()
            val manufacturer = ingredientManufacturerInput.text.toString()
            viewModel.addIngredient(name, manufacturer)
            adapter.submitList(viewModel.ingredients.toList())

            // Clear the EditText fields after adding ingredient
            ingredientNameInput.text.clear()
            ingredientManufacturerInput.text.clear()
        }

        // Handle adding an image
        addImageButton.setOnClickListener {
            // Use an Intent to select an image from the device's gallery
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(intent, REQUEST_CODE_IMAGE)
        }

        // Handle submitting the recipe
        submitButton.setOnClickListener {
            val name = recipeNameInput.text.toString()
            val image = viewModel.recipeImageUri
            val ingredients = viewModel.ingredients.toList()
            viewModel.submitRecipe(name, image, ingredients)

            // Clear the EditText fields and reset the ViewModel after submitting the recipe
            recipeNameInput.text.clear()
            adapter.submitList(emptyList())
            viewModel.reset()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            viewModel.setImage(imageUri)
            recipeImage.setImageURI(imageUri)
        }
    }

    companion object {
        const val REQUEST_CODE_IMAGE = 100
    }
}
}