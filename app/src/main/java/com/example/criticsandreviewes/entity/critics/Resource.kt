package com.example.criticsandreviewes.entity.critics


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
data class Resource(
    @Json(name = "credit")
    val credit: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "src")
    val src: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "width")
    val width: Int
)