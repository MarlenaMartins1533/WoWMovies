package com.marlena.wowmovies.scenes.moviesList

import android.content.Context
import com.marlena.wowmovies.model.domain.Genre
import com.marlena.wowmovies.model.domain.Movie

interface MoviesList {
    interface View {
        fun displayFailure(error: Int)
        fun makeRequests()
        fun setList(list: List<Movie>)
        fun setGenresList(genreList: MutableList<Genre>?)
        fun setMoviesListByGenre (list: List<Movie>)
        fun getViewContext(): Context?
    }

    interface Presenter {
        fun getMoviesListByGenre(
            genreId: Int,
            movieList: List<Movie>,
            genreList: MutableList<Genre>?
        )
        fun getMoviesList()
        fun getGenresList()
        fun kill()
    }
}