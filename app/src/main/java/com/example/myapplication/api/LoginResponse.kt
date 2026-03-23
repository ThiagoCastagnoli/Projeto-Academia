package com.example.myapplication.api

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val user: User?,
)

data class User(
    val id: Int,
    val nome: String,
    val sobrenome: String,
    val authKey: String,
)
