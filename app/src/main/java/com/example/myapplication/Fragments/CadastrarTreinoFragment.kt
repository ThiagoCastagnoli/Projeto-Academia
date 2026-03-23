package com.example.myapplication.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextClock
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.PrefsKey
import com.example.myapplication.R
import com.example.myapplication.api.RegisterTreinoRequest
import com.example.myapplication.api.RetrofitInstance
import kotlinx.coroutines.launch

class CadastrarTreinoFragment: Fragment(R.layout.fragment_cadastro_treino) {

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext()
            .getSharedPreferences("base_de_dados", Context.MODE_PRIVATE)

        val titulo = view.findViewById<EditText>(R.id.edittext_titulotreino)
        val descricaotreino = view.findViewById<EditText>(R.id.edittext_descricaotreino)
        val buttonCadastroTreino = view.findViewById<Button>(R.id.button_cadastrar_treino)
        val datatreino = view.findViewById<TextClock>(R.id.textClock)
        val userpredsId = prefs.getInt(PrefsKey.userid, -999)
        val buttonSelecionarTreino = view.findViewById<Button>(R.id.button_listtipos)

            buttonSelecionarTreino.setOnClickListener {
                val action = CadastrarTreinoFragmentDirections.actionCadastrarTreinoFragmentToTiposFragment()
                findNavController().navigate(action)
            }

        parentFragmentManager.setFragmentResultListener("resultTipos", this) { _, bundle ->

            val tipoId = bundle.getString("keyId")
            Toast.makeText(activity, "tipoId: $tipoId", Toast.LENGTH_SHORT).show()

        }




            buttonCadastroTreino.setOnClickListener {
                lifecycleScope.launch {
                    val request = RegisterTreinoRequest(
                        user_id = userpredsId.toString(),
                        tipo_id = "1",
                        titulo = titulo.text.toString(),
                        descricao = descricaotreino.text.toString(),
                        visibilidade = "publico",
                        data = "11/12/25"
                    )
                    val response = RetrofitInstance.api.register(request)
                    if (response != null && response.success == true && response.id != null) {
                        Toast.makeText(activity, "Treino Registrado com sucesso!", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().popBackStack()

                    } else {
                        Toast.makeText(
                            activity,
                            "Treino não Registrado, tente novamente!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

            }




        }

    }
}
