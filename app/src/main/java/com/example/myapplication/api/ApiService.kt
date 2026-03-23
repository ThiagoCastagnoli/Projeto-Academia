package com.example.myapplication.api


import RegisterTreinoResponse
import TiposResponse
import TreinosResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    suspend fun login(@Body request: LoginRequest) : LoginResponse

    @GET("treinos")
    suspend fun getTreinos(
        @Query("userId") userId: Int,
        @Header("authKey") authKey: String

    ) : TreinosResponse

    @POST ("treinos/register")
    suspend fun register (@Body request: RegisterTreinoRequest) : RegisterTreinoResponse

    @GET("tipos")
    suspend fun getTipos(): TiposResponse
}