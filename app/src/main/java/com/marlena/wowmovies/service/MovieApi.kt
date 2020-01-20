package com.marlena.wowmovies.service

import com.marlena.wowmovies.data.Constants
import com.marlena.wowmovies.model.response.GenreResponse
import com.marlena.wowmovies.model.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("api_key") appIdMovie: String = Constants.appKeyMovie
    ): Call<MovieResponse>

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String = Constants.appKeyMovie
    ): Call<GenreResponse>
}
