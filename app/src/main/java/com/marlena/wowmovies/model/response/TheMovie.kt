package com.marlena.wowmovies.model.response

import com.google.gson.annotations.SerializedName

data class TheMovie(

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("overview")
    val overview: String = "unknown"
)