package com.example.criticsandreviewes.entity.reviewes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Review(
    @Json(name = "byline")
    val byline: String,
    @Json(name = "critics_pick")
    val criticsPick: Int,
    @Json(name = "date_updated")
    val dateUpdated: String,
    @Json(name = "display_title")
    val displayTitle: String,
    @Json(name = "headline")
    val headline: String,
    @Json(name = "link")
    val link: Link,
    @Json(name = "mpaa_rating")
    val mpaaRating: String,
    @Json(name = "multimedia")
    val multimedia: Multimedia,
    @Json(name = "opening_date")
    val openingDate: String?,
    @Json(name = "publication_date")
    val publicationDate: String,
    @Json(name = "summary_short")
    val summaryShort: String
)