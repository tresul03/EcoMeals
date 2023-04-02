package com.mastercoding.hackathon2.ui.recipe

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mastercoding.hackathon2.databinding.FragmentRecipeBinding
import com.mastercoding.hackathon2.ui.recommendation.recommendationviewmodel

class recipefragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val homeViewModel = ViewModelProvider(this).get(recipeviewmodel::class.java)

        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRecipes
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val button: Button = binding.button
        button.setOnClickListener{
            val intent = Intent(requireContext(), addrecipefragment::class.java)
            startActivity(intent)

        }


        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}