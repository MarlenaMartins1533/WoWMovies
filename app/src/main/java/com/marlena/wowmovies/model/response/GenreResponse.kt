package com.marlena.wowmovies.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.marlena.wowmovies.model.domain.Genre


class GenreResponse {

    @SerializedName("genres")
    @Expose
    var genres: MutableList<Genre>? = null
}