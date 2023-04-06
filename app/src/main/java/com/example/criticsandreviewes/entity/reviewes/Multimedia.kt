package com.example.criticsandreviewes.entity.reviewes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Multimedia(
    @Json(name = "height")
    val height: Int,
    @Json(name = "src")
    val src: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "width")
    val width: Int
)