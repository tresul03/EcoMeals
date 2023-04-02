package com.mastercoding.hackathon2.ui.recipe

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Chilli Con Carne")
            .setMessage("Ingredients:CarbonRating\nMince Meat: 42\nChorizo: 42\nKidney Beans: 97\nTomatoes: 97\nOnions: 97\nPeppers: 97\n")
            .setPositiveButton("OK") { _, _ ->
                // Do something when OK is clicked
            }
            .setNegativeButton("Cancel") { _, _ ->
                // Do something when Cancel is clicked
            }
            .create()
    }
}
