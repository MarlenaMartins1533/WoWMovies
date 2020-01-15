package com.marlena.wowmovies.scenes.genreLists

import com.marlena.wowmovies.model.domain.Movie

interface MoviesList {
    interface View {
        fun displayFailure(error: Int)
        fun makeRequests()
        fun setList(list: List<Movie>)
    }

    interface Presenter {
        fun getMoviesList()
        fun kill()
    }
}