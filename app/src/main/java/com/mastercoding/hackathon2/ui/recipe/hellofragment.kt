package com.mastercoding.hackathon2.ui.recipe

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mastercoding.hackathon2.databinding.FragmentRecipeBinding

class hellofragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(requireContext(), "Button Clicked", Toast.LENGTH_LONG).show()
    }


}