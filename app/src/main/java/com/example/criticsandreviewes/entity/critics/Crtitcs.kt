package com.example.criticsandreviewes.entity.critics



import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass




@JsonClass(generateAdapter = true)
data class Crtitcs(
    @Json(name = "bio")
    val bio: String,
    @Json(name = "display_name")
    val displayName: String,
    @Json(name = "multimedia")
    val multimedia: MultimediaCritics?,
    @Json(name = "seo_name")
    val seoName: String,
    @Json(name = "sort_name")
    val sortName: String,
    @Json(name = "status")
    val status: String
)