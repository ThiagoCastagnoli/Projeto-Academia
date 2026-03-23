package com.example.myapplication.adapters


import Treino
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.R

class TreinosAdapter (val listaTreinos: List<Treino>):
    RecyclerView.Adapter<TreinosAdapter.TreinoViewHolder>() {

    class TreinoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textViewTitulo = itemView.findViewById<TextView>(R.id.textview_titulo)
        val textViewDescricao = itemView.findViewById<TextView>(R.id.textview_descricao)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreinoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_treino, parent, false)
        return TreinoViewHolder(view)
    }


    override fun getItemCount(): Int {
        return listaTreinos.size
    }

    override fun onBindViewHolder(holder: TreinoViewHolder, position: Int) {

        val treino = listaTreinos[position]

        holder.textViewTitulo.text = treino.titulo
        holder.textViewDescricao.text = treino.descricao
    }



}
