package com.mastercoding.hackathon2.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mastercoding.hackathon2.databinding.FragmentRecipecreateBinding

class displayIngredientsFragment : Fragment() {

    private lateinit var binding: FragmentRecipecreateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipecreateBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set up the UI elements and event listeners here


        return view
    }


}