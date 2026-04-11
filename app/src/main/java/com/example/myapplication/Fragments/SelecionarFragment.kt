package com.example.myapplication.Fragments

import Treino
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Classes.SelectTreinoP
import com.example.myapplication.R
import com.example.myapplication.adapters.SelectTreinosAdapater


class SelecionarFragment : Fragment(R.layout.fragment_selecionart) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerSelecionar = view.findViewById<RecyclerView>(R.id.recyclerTreinosProntos)

        val list = listOf(
            SelectTreinoP(
                "Treino De Peito",
                "Treino de peito para força e hipertrofia.",
                R.drawable.treino
            ),
            SelectTreinoP(
                "Treino De Costas",
                "Treino de costas para força e hipertrofia.",
                R.drawable.treino
            ),
            SelectTreinoP(
                "Treino De Ombro",
                "Treino de ombros para força e hipertrofia.",
                R.drawable.treino
            ),
            SelectTreinoP(
                "Treino De Bíceps",
                "Treino de bíceps para força e hipertrofia.",
                R.drawable.treino
            ),
            SelectTreinoP(
                "Treino De Tríceps",
                "Treino de tríceps para força e hipertrofia.",
                R.drawable.treino
            ),
            SelectTreinoP(
                "Treino De Quadríceps",
                "Treino de quadríceps para força e hipertrofia.",
                R.drawable.treino
            ),
            SelectTreinoP(
                "Treino De Posterior",
                "Treino de posterior para força e hipertrofia.",
                R.drawable.treino
            ),
            SelectTreinoP(
                "Treino De Glúteos",
                "Treino de glúteos para força e hipertrofia.",
                R.drawable.treino
            )

        )
        recyclerSelecionar.layoutManager = LinearLayoutManager(view.context)
        recyclerSelecionar.adapter = SelectTreinosAdapater(list)



    }
}