package com.mastercoding.hackathon2.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mastercoding.hackathon2.R
import com.mastercoding.hackathon2.databinding.FragmentRecipeBinding
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class recipefragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get the database


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val homeViewModel = ViewModelProvider(this).get(recipeviewmodel::class.java)

        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val button: Button = binding.button
        button.setOnClickListener {
            val newFragment = addrecipefragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_containing, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}