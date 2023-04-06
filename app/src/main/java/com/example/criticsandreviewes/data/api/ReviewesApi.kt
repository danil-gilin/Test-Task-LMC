package com.example.criticsandreviewes.data.api

import com.example.criticsandreviewes.entity.reviewes.ReviewesRezult
import retrofit2.http.GET
import retrofit2.http.Query


const val apiKey = "VmfWBxfgJebqmAtvDKlIV9ZoJ7Czp1rI"
const val apiKeySearch = "lNs9pDT1pEOhITxwGlvI7EX6B0Dj1Nd1"
const val apiKeyCriticsPreviews = "GW5a0tJfWOcfQ7k3dpQizIsrmpZ33Bmm"
interface ReviewesApi {
    @GET("reviews/search.json?api-key=$apiKeySearch")
    suspend fun getReviewesSearchNotEmpty(
        @Query("query") query: String,
        @Query("publication-date") publication_date: String,
        @Query("offset") opening_date: Int
    ): ReviewesRezult

    @GET("reviews/search.json?api-key=$apiKey")
    suspend fun getReviewesSearch(
        @Query("query") query: String,
        @Query("publication-date") publication_date: String,
        @Query("offset") opening_date: Int
    ): ReviewesRezult

    @GET("reviews/search.json?api-key=$apiKeyCriticsPreviews")
    suspend fun getReviewesCritic(@Query("reviewer")criticName: String,@Query("offset") offset: Int): ReviewesRezult
}