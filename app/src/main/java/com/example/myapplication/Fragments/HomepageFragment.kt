package com.example.myapplication.Fragments



import Treino
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.TreinosAdapter
import com.example.myapplication.api.RetrofitInstance
import kotlinx.coroutines.launch

class HomepageFragment: Fragment (R.layout.fragment_homepage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textviewbemvidno = view.findViewById<TextView>(R.id.textView_Bemvindo)
        val progressBarTreino = view.findViewById<ProgressBar>(R.id.progressBarTreino)
        val buttoncadastrartreino = view.findViewById<Button>(R.id.button_castrar_treinohome)
        val recyclerview = view.findViewById<RecyclerView>(R.id.Recyclerview)
        val argumentos  = HomepageFragmentArgs.fromBundle(requireArguments())
        val userName = argumentos.userName


        recyclerview.layoutManager = LinearLayoutManager(context)
        progressBarTreino.visibility = View.VISIBLE

        textviewbemvidno.text = "olá $userName. seja Bem-vindo!"

        buttoncadastrartreino.setOnClickListener {

            val action = HomepageFragmentDirections.actionHomepageFragmentToCadastrarTreinoFragment()
            findNavController().navigate(action)

        }


            lifecycleScope.launch {

                val getTreinos = kotlin.runCatching {
                    RetrofitInstance.api.getTreinos(
                        userId= argumentos.userId,
                        authKey= argumentos.authKey)
                }

                getTreinos.onSuccess { response ->
                    recyclerview.adapter = TreinosAdapter(response.data)
                    progressBarTreino.visibility = View.GONE
                }

                getTreinos.onFailure {
                    Toast.makeText(activity, "Crash API. Verifique sua Conexão", Toast.LENGTH_SHORT).show()
                }


                }


            }


        }






