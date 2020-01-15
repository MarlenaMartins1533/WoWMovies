package com.marlena.wowmovies.scenes.theMovie

import com.marlena.wowmovies.model.domain.Movie
import com.marlena.wowmovies.model.entity.InfoEntity

interface TheMovie {
    interface View {
        fun showMessage(message: String)
        fun getEdt(): String
        fun onBackPressed()
    }

    interface Presenter {
        fun insertMyMovie(movie: Movie, description: String)
        fun deleteMovie(poster_path: String)
        fun getMyMovieByUrl(poster_path: String): InfoEntity?
        fun getDescription(poster_path: String): String
    }
}