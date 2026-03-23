package com.example.myapplication.Fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.PrefsKey
import com.example.myapplication.api.LoginRequest
import com.example.myapplication.api.RetrofitInstance
import kotlinx.coroutines.launch

class LoginFramgment:Fragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext()
            .getSharedPreferences("base_de_dados", Context.MODE_PRIVATE)

        val authKey = prefs.getString(PrefsKey.authkey, null)
        val userName = prefs.getString(PrefsKey.username, null)
        val userId = prefs.getInt(PrefsKey.userid, -999)

        if (authKey != null && userName != null && userId != -999){
            val action = LoginFramgmentDirections.actionLoginFramgmentToHomepageFragment(
                userName = userName,
                userId = userId,
                authKey = authKey
            )
            findNavController().navigate(action)
        }

        val editTextEmail = view.findViewById<EditText>(R.id.edit_text_emaillogin)
        val editTextSenha = view.findViewById<EditText>(R.id.editText_senhalogin)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        val saveLogin = view.findViewById<Switch>(R.id.savelogin)
        val buttoncadastro = view.findViewById<Button>(R.id.button_cadastro)

        saveLogin.setOnClickListener {
            Toast.makeText(activity, "login salvo", Toast.LENGTH_SHORT).show()
        }


        buttoncadastro.setOnClickListener {
            val action = LoginFramgmentDirections.actionLoginFramgmentToCadastroFragment()
            findNavController().navigate(action)
        }
        val buttonlogin = view.findViewById<Button>(R.id.button_login)
        buttonlogin.setOnClickListener {
            buttonlogin.visibility = View.GONE
            progressBar.visibility = View.VISIBLE



            lifecycleScope.launch {
                val request = LoginRequest(
                    email = editTextEmail.text.toString(),
                    senha = editTextSenha.text.toString()
                )
                val response = RetrofitInstance.api.login(request)

                progressBar.visibility = View.GONE
                buttonlogin.visibility = View.VISIBLE

                if (response.success == true && response.user != null) {

                    prefs.edit()
                        .putString(PrefsKey.username, response.user.nome)
                        .putInt(PrefsKey.userid, response.user.id)
                        .putString(PrefsKey.authkey, response.user.authKey)
                        .apply()

                    val nomeUser = response.user.nome
                    Toast.makeText(activity, "user logado -> $nomeUser", Toast.LENGTH_SHORT).show()

                    val action = LoginFramgmentDirections.actionLoginFramgmentToHomepageFragment(
                        userName = response.user.nome,
                        userId = response.user.id,
                        authKey = response.user.authKey
                    )
                    findNavController().navigate(action)


                } else {
                    Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                }



            }
        }

    }


}


