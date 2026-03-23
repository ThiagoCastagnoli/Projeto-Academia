package com.example.myapplication.api

data class RegisterTreinoRequest(
    val user_id: String,
    val tipo_id: String,
    val titulo: String,
    val descricao: String,
    val visibilidade: String,
    val data : String

)