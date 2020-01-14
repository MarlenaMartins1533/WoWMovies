package com.marlena.wowmovies.scenes.moviesList

import com.marlena.wowmovies.core.App
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesListPresenter(private val view: MoviesList.View) : MoviesList.Presenter, CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    private var job: Job? = null

    override fun getMoviesList() {
        job = launch {
            val result = withContext(Dispatchers.IO) {
                App.movieRepository.getMovieList()
            }

            if (result.isNullOrEmpty())
                view.displayFailure(1)
            else
                view.setList(result)
        }
    }

    override fun kill() {
        job?.cancel()
    }
}