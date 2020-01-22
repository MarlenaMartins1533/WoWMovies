package com.marlena.wowmovies.scenes.moviesList

import android.content.Context
import com.marlena.wowmovies.model.domain.Genre
import com.marlena.wowmovies.model.domain.Movie
import com.marlena.wowmovies.model.response.GenreResponse

interface MoviesList {
    interface View {
        fun displayFailure(error: Int)
        fun makeRequests()
        fun setList(list: List<Movie>)
        fun setGenresList(genreResponse: GenreResponse?)
        fun setMoviesListByGenre (list: List<Movie>)
        fun getViewContext(): Context?
    }

    interface Presenter {
        fun getMoviesListByGenre(
            page: String,
            movieList: List<Movie>,
            genreResponse: GenreResponse?
        )
        fun getMoviesList()
        fun getGenresList()
        fun kill()
    }
}