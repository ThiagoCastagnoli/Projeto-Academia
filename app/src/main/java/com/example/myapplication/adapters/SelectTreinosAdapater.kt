package com.example.myapplication.adapters

import Tipos
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Classes.SelectTreinoP
import com.example.myapplication.R
import android.view.LayoutInflater


class SelectTreinosAdapater(
    private val listSelectTreino: List<SelectTreinoP>
) :
    RecyclerView.Adapter<SelectTreinosAdapater.SelectTreinosViewHolder>() {

    class SelectTreinosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val card = itemView.findViewById<CardView>(R.id.card_select)
        val titulo: TextView = view.findViewById<TextView>(R.id.titulo_select_t)
        val subTitulo: TextView = view.findViewById<TextView>(R.id.sub_titulo_select)
        val imagem: ImageView = view.findViewById<ImageView>(R.id.imgTreino)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTreinosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_select_treino, parent, false)
        return SelectTreinosViewHolder(view)


    }

    override fun onBindViewHolder(holder: SelectTreinosViewHolder, position: Int) {
        val item = listSelectTreino[position]
        holder.titulo.text = item.titulo
        holder.subTitulo.text = item.subTitulo
        holder.imagem.setImageResource(item.image)

    }

    override fun getItemCount() = listSelectTreino.size
}



