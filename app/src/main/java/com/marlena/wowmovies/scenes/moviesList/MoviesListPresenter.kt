package com.marlena.wowmovies.scenes.moviesList

import com.marlena.wowmovies.core.App
import com.marlena.wowmovies.model.domain.Genre
import com.marlena.wowmovies.model.domain.Movie
import com.marlena.wowmovies.model.response.GenreResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesListPresenter(private val view: MoviesList.View): MoviesList.Presenter, CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    private var job: Job? = null

    override fun getMoviesListByGenre(
        page: String,
        movieList: List<Movie>,
        genreResponse: GenreResponse?
    ){
        val list = mutableListOf<Movie>()

        genreResponse?.genres?.forEach { g ->
            if (g.name == page) {
                movieList.forEach { m ->
                    m.genreIds?.forEach { it ->
                        if (it == g.id) list.add(m)
                    }
                }
            }
        }
        view.setMoviesListByGenre(list)
    }

    override fun getMoviesList() {
        job = launch {
            val result = withContext(Dispatchers.IO) {
                App.movieRepository.getMovieList()
            }

            if (result.isNullOrEmpty())
                view.displayFailure(1)
            else view.setList(result)
        }
    }

    override fun getGenresList() {
        job = launch{
            val result = withContext(Dispatchers.IO) {
                App.movieRepository.getGenreList()
            }
            view.setGenresList(result)
        }
    }

    override fun kill() {
        job?.cancel()
    }
}