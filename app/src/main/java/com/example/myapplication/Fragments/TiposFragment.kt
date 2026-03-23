package com.example.myapplication.Fragments


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.TiposAdapter
import com.example.myapplication.api.RetrofitInstance
import kotlinx.coroutines.launch
import Tipos
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class TiposFragment: Fragment(R.layout.fragment_tipos) {

    private val listaTipos = mutableListOf<Tipos>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressbar = view.findViewById<ProgressBar>(R.id.progressBar_selctTreino)

        val recyclerview = view.findViewById<RecyclerView>(R.id.Recyclerview_Tipos)
        recyclerview.layoutManager = LinearLayoutManager(context)

        progressbar.visibility = View.VISIBLE



        lifecycleScope.launch {
            val getTipos = kotlin.runCatching {
                RetrofitInstance.api.getTipos()

            }
            getTipos.onSuccess { response ->
                recyclerview.adapter = TiposAdapter(response.data) { tipos ->
                    val bundle = Bundle().apply {

                        putString("keyId", tipos.id)
                    }
                    parentFragmentManager.setFragmentResult("resultTipos", bundle)
                    findNavController().popBackStack()

                }
                progressbar.visibility = View.GONE
            }
        }


    }


}
