package com.example.myapplication.Fragments



import Treino
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.TreinosAdapter
import com.example.myapplication.api.RetrofitInstance
import kotlinx.coroutines.launch

class HomepageFragment : Fragment(R.layout.fragment_homepage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textviewbemvidno = view.findViewById<TextView>(R.id.textView_Bemvindo)
        val progressBarTreino = view.findViewById<ProgressBar>(R.id.progressBarTreino)
        val buttoncadastrartreino = view.findViewById<Button>(R.id.button_castrar_treinohome)
        val buttonLogout = view.findViewById<Button>(R.id.button_logout)
        val recyclerview = view.findViewById<RecyclerView>(R.id.Recyclerview)

        val argumentos = HomepageFragmentArgs.fromBundle(requireArguments())
        val userName = argumentos.userName

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        progressBarTreino.visibility = View.VISIBLE

        textviewbemvidno.text = "Olá $userName, seja bem-vindo!"

        buttoncadastrartreino.setOnClickListener {
            val action = HomepageFragmentDirections.actionHomepageFragmentToCadastrarTreinoFragment()
            findNavController().navigate(action)
        }

        buttonLogout.setOnClickListener {

            val prefs = requireContext()
                .getSharedPreferences("base_de_dados", Context.MODE_PRIVATE)

            prefs.edit().clear().apply()

            Toast.makeText(requireContext(), "Logout realizado", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_homepageFragment_to_loginFragment)
        }

        lifecycleScope.launch {
            val getTreinos = kotlin.runCatching {
                RetrofitInstance.api.getTreinos(
                    userId = argumentos.userId,
                    authKey = argumentos.authKey
                )
            }

            getTreinos.onSuccess { response ->
                recyclerview.adapter = TreinosAdapter(response.data)
                progressBarTreino.visibility = View.GONE
            }

            getTreinos.onFailure {
                progressBarTreino.visibility = View.GONE
                Toast.makeText(activity, "Crash API. Verifique sua conexão", Toast.LENGTH_SHORT).show()
            }
        }
    }
}






