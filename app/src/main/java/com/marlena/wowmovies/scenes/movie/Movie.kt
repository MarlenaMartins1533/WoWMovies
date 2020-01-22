package com.marlena.wowmovies.scenes.movie

import android.content.Context
import com.marlena.wowmovies.model.domain.Genre
import com.marlena.wowmovies.model.domain.Movie
import com.marlena.wowmovies.model.response.GenreResponse

interface Movie {
    interface View {
        fun getViewContext(): Context?
    }

    interface Presenter {
    }
}