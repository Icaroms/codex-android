package br.com.icaroms.codex.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: RawgApi = Retrofit.Builder()
        .baseUrl("https://api.rawg.io/api/") // Termina com / !
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RawgApi:: class.java)
}