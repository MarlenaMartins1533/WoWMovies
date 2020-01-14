package com.marlena.wowmovies.repository

import com.marlena.wowmovies.model.response.TheMovie
import com.marlena.wowmovies.service.MovieClient

class MovieRepository {

    fun getMovieList(): List<TheMovie>? {
        val response = MovieClient.instance.callGetMovies()
        return response?.results
    }
}