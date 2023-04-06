package com.example.criticsandreviewes.entity.reviewes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Link(
    @Json(name = "suggested_link_text")
    val suggestedLinkText: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)