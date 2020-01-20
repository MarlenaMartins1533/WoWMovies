package com.marlena.wowmovies.repository

import com.marlena.wowmovies.model.domain.Movie
import com.marlena.wowmovies.model.response.GenreResponse
import com.marlena.wowmovies.service.MovieClient

class MovieRepository {

    fun getMovieList(): List<Movie>? {
        val response = MovieClient.instance.callGetMovies()
        return response?.results
    }
    fun getGenreList(): GenreResponse? {
        return MovieClient.instance.callGetGenres()
    }
}