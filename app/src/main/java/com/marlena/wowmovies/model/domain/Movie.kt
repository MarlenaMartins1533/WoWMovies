package com.marlena.wowmovies.model.domain

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("overview")
    val overview: String = "unknown"
)