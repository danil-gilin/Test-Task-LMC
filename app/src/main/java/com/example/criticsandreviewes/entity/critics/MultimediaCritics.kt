package com.example.criticsandreviewes.entity.critics


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
data class MultimediaCritics(
    @Json(name = "resource")
    val resource: Resource
)