package com.marlena.wowmovies.scenes.moviesList

import com.marlena.wowmovies.model.response.TheMovie

interface MoviesList {
    interface View {
        fun displayFailure(error: Int)
        fun makeRequests()
        fun setList(list: List<TheMovie>)
    }

    interface Presenter {
        fun getMoviesList()
        fun kill()
    }
}