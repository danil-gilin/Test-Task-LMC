package com.example.criticsandreviewes.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitReviewAndCritics {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nytimes.com/svc/movies/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val reviewesApi: ReviewesApi = retrofit.create(ReviewesApi::class.java)
    val criticsApi: CriticsApi = retrofit.create(CriticsApi::class.java)
}