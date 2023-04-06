package com.example.criticsandreviewes.entity.reviewes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewesRezult(
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "has_more")
    val hasMore: Boolean,
    @Json(name = "num_results")
    val numResults: Int,
    @Json(name = "results")
    val results: List<Review>?,
    @Json(name = "status")
    val status: String
)