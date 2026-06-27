package br.com.icaroms.codex.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RawgApi {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String,
        @Query("page_size") pageSize: Int
    ): Response<ResponseBody>

    @GET("games")
    suspend fun searchGames(
        @Query("key") apiKey: String,
        @Query("search") term: String,
        @Query("page_size") pageSize: Int
    ): Response<ResponseBody>
}