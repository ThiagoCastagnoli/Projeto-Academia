package com.example.myapplication.Fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R

class CadastroFragment: Fragment(R.layout.fragment_cadastro) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttoncadastro = view.findViewById<Button>(R.id.button_cadastro)
        buttoncadastro.setOnClickListener {
            val action = CadastroFragmentDirections.actionCadastroFragmentToHomepageFragment(
                userName = "",
                userId = 0,
                authKey = ""
            )
            findNavController().navigate(action)

        }

    }
}