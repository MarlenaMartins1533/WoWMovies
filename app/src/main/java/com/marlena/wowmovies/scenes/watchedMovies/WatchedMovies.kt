package com.marlena.wowmovies.scenes.watchedMovies

import com.marlena.wowmovies.model.domain.Movie

interface WatchedMovies {
    interface View {
        fun setAllList(list: List<Movie>)
    }

    interface Presenter {
        fun getAllList()
        fun kill()
    }
}