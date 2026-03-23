package com.example.myapplication.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import Tipos


class TiposAdapter (
    val listaTipos: List<Tipos>,
    val setOnClickListener: (Tipos) -> Unit
):

    RecyclerView.Adapter<TiposAdapter.TiposviewHolder>() {


    inner class  TiposviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val viewTitulo = itemView.findViewById<TextView>(R.id.textview_tipo_list)

        val viewDescricao = itemView.findViewById<TextView>(R.id.textview_descricao_tipos)
        fun bind(tipos: Tipos){
            viewTitulo.text = tipos.titulo
            viewDescricao.text = tipos.descricao
            itemView.setOnClickListener {
                setOnClickListener(tipos)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiposviewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tipos, parent, false)
        return TiposviewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaTipos.size
    }

    override fun onBindViewHolder(holder: TiposviewHolder, position: Int) {

        val tipos = listaTipos[position]

        holder.bind(tipos)

    }
}
