package com.example.criticsandreviewes.data.api

import com.example.criticsandreviewes.entity.critics.CriticsRezult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val apiKeyCritics = "GW5a0tJfWOcfQ7k3dpQizIsrmpZ33Bmm"
interface CriticsApi {
    @GET("critics/all.json?api-key=$apiKeyCritics")
    suspend fun getCritics():CriticsRezult

    @GET("critics/{query}.json?api-key=$apiKeyCritics")
    suspend fun getCriticsSearch(@Path("query") query: String): CriticsRezult
}