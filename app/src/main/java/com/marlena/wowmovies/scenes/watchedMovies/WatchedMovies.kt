package com.marlena.wowmovies.scenes.watchedMovies

import com.marlena.wowmovies.model.domain.ThePicture

interface WatchedMovies {
    interface View {
        fun setAllList(list: List<ThePicture>)
    }

    interface Presenter {
        fun getAllList()
        fun kill()
    }
}