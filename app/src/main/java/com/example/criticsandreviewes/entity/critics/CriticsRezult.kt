package com.example.criticsandreviewes.entity.critics


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CriticsRezult(
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "num_results")
    val numResults: Int,
    @Json(name = "results")
    val results: List<Crtitcs>?,
    @Json(name = "status")
    val status: String
)